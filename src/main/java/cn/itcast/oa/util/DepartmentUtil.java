package cn.itcast.oa.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.itcast.oa.domain.Department;

public class DepartmentUtil {
	/**
	 * 
	 * @Title: getAllEntityListByTop
	 * @Description: 迭代树状结构,把feature作为每个节点的前端特征，separator作为不同级节点的区别
	 * @param @param topList 需要迭代的树状结构
	 * @param @param feature 特征
	 * @param @param separator 分隔符
	 * @param @return 设定文件
	 * @return List<Department> 返回类型
	 * @throws
	 */
	public static List<Department> getAllDepartmentList(
			List<Department> topList) {
		List<Department> aimList = new ArrayList<Department>();
		walkTree(topList,aimList,"|-");
		return aimList;
	}
	private static void walkTree(Collection<Department> topList,List<Department> aimList, String prefix){
		for(Department top:topList){
			
			//不要修改pojo 要copy 防止 后边调用事务的时候 提交到数据库
			Department copy = new Department();
			copy.setName(prefix+top.getName());
			copy.setId(top.getId());
			//时刻注意 页面需要什么信息 我们就给什么信息，如果页面要的效果是数据库没有的，我们不应该往数据库加
			//而是应该copy一份新的 返回 这样就不会再以后如果用到pojo的时候影响到数据库的原本信息
			aimList.add(copy);
			//为了在网页中有空格的效果，我们不应该+" "而是应该+"全角的空格"也就是输入法月亮型变成圆形
			//要不然，页面是不会显示空格的
			walkTree(top.getChildrens(), aimList,"　"+prefix);
		}
	}
}
