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
	/**
	 * 
	* @Title: save 
	* @Description: 保存岗位 
	* @param @param role    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void save(Role role);
	/**
	 * 
	* @Title: getById 
	* @Description: 通过id得到岗位 
	* @param @param id
	* @param @return    设定文件 
	* @return Role    返回类型 
	* @throws
	 */
	Role getById(Long id);
	/**
	 * 
	* @Title: update 
	* @Description: 更新从页面来的岗位信息
	* @param @param role    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	void update(Role role);

}
