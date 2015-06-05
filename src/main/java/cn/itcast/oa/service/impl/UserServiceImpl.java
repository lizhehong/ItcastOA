package cn.itcast.oa.service.impl;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.stereotype.Service;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;

@Service("userService")
public class UserServiceImpl extends DaoSupportImpl<User> implements
		UserService {

	@Override
	public void save(User user) {
		user.setPassword(DigestUtils.md5Hex("1234"));
		hibernateTemplate.save(user);
	}

	public User findByLoginNameAndPassword(String loginName, String password) {
		String md5 = DigestUtils.md5Hex(password);
		String[] args = new String[]{loginName,md5};
		List<User> userList =  hibernateTemplate.find("FROM User u WHERE u.loginName = ? AND u.password = ?",args);
		if(!userList.isEmpty()){
			return userList.get(0);
		}else{
			return null;
		}
	}

}
