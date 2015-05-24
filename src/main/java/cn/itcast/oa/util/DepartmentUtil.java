package cn.itcast.oa.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtil {
	/**
	 * 
	 * @Title: getAllEntityListByTop
	 * @Description: 迭代树状结构,把feature作为每个节点的前端特征
	 * @param topList 需要迭代的树状结构
	 * @param removeDepartment 需要移除掉的分支,如果为空，表示没有要移除的部门
	 * @return List<Department> 返回类型
	 * @throws
	 */
	public static List<Department> getAllDepartmentList(
			List<Department> topList,Department removeDepartment) {
		List<Department> aimList = new ArrayList<Department>();
		walkTree(topList,aimList,"|-",removeDepartment);
		return aimList;
	}
	private static void walkTree(Collection<Department> topList,List<Department> aimList, String prefix,Department removeDepartment){
		for(Department top:topList){
			
			//不要修改pojo 要copy 防止 后边调用事务的时候 提交到数据库
			Department copy = new Department();
			//去掉部门分支,所以在程序中，应该是不再循环，进入下一个同级部门的循环
				if(removeDepartment!=null && top.getId().equals(removeDepartment.getId())){
					continue;
				}
				copy.setName(prefix+top.getName());
				copy.setId(top.getId());
			//时刻注意 页面需要什么信息 我们就给什么信息，如果页面要的效果是数据库没有的，我们不应该往数据库加
			//而是应该copy一份新的 返回 这样就不会再以后如果用到pojo的时候影响到数据库的原本信息
			aimList.add(copy);
			//为了在网页中有空格的效果，我们不应该+" "而是应该+"全角的空格"也就是输入法月亮型变成圆形
			//要不然，页面是不会显示空格的
			walkTree(top.getChildrens(), aimList,"　"+prefix,removeDepartment);
		}
	}
}
