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

}
