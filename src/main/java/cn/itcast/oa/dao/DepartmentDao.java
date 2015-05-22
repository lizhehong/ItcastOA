package cn.itcast.oa.dao;

import java.util.List;

import cn.itcast.oa.domain.Department;

public interface DepartmentDao extends BaseDao<Department>{
	/**
	 * 
	* @Title: findTopList 
	* @Description: 查询没有子部门的部门列表 
	* @param @return    设定文件 
	* @return List<Department>    返回类型 
	* @throws
	 */
	List<Department> findTopList();
	/**
	 * 
	* @Title: findChildrenList 
	* @Description: 通过ID查询子部门列表
	* @param @param parentId
	* @param @return    设定文件 
	* @return List<Department>    返回类型 
	* @throws
	 */
	List<Department> findChildrenList(Long parentId);

}
