package cn.itcast.oa.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
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

}
