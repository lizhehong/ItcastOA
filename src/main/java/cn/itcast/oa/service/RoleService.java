package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Role;

public interface RoleService {
	/**
	 * 
	* @Title: findAll 
	* @Description: 查询所有的岗位信息 
	* @param @return    设定文件 
	* @return List<Role>    返回类型 
	* @throws
	 */
	List<Role> findAll();
	/**
	 * 
	* @Title: del 
	* @Description: 删除岗位信息 
	* @param @param id    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void del(Long id);

}
