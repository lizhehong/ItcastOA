package cn.itcast.oa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.dao.UserDao;
import cn.itcast.oa.domain.User;

/**
 * aim:用户的独立接口
 * 
 * @author lizhehong
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	/**
	 * aim:通过用户名和密码得到用户
	 */
	public User getByNameAndPassword(String username, String password) {
		return null;
	}

}
