package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Department;

public interface DepartmentService {
	/**
	 * 
	* @Title: findAll 
	* @Description: 找到所有的部门 
	* @param @return    设定文件 
	* @return List<Department>    返回类型 
	* @throws
	 */
	List<Department> findAll();
	/**
	 * 
	* @Title: del 
	* @Description: 删除 部门
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void del(Long id);
	/**
	 * 
	* @Title: save 
	* @Description: 保存 部门 
	* @param @param model    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void save(Department department);
	/**
	 * 
	* @Title: getById 
	* @Description: 得到部门 
	* @param @param id
	* @param @return    设定文件 
	* @return Department    返回类型 
	* @throws
	 */
	Department getById(Long id);
	/**
	 * 
	* @Title: update 
	* @Description: 更新部门 
	* @param @param department    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void update(Department department);

}
