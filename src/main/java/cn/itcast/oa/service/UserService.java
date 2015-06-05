package cn.itcast.oa.service;

import cn.itcast.oa.base.DaoSupport;
import cn.itcast.oa.domain.User;

public interface UserService extends DaoSupport<User> {
	/**
	 * 
	* @Title: findByLoginNameAndPassword 
	* @Description: 验证用户名与密码，如果正确就返回这个用户，否则返回null 
	* @param @param loginName
	* @param @param password	明文密码
	* @param @return    设定文件 
	* @return User    返回类型 
	* @throws
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
