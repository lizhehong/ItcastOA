package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.Privilege;



public interface PrivilegeService extends DaoSupport<Privilege>{
	/**
	 * 
	* @Title: findTopList 
	* @Description: 找到顶层的权限Privilege 
	* @param @return    设定文件 
	* @return List<Privilege>    返回类型 
	* @throws
	 */
	List<Privilege> findTopList();
	/**
	 * 
	* @Title: getAllPrivilegeUrls 
	* @Description: 找到所有权限的URL名字 ,不包含空值,不包含重复的值
	* @param 		null
	* @return	    URL字符串集合
	* @return List<String>    返回类型 
	* @throws
	 */
	List<String> getAllPrivilegeUrls();

}
