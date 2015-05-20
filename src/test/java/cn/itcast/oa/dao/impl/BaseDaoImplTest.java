package cn.itcast.oa.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.itcast.oa.domain.Role;

public class BaseDaoImplTest {

	@Test
	/**
	 * condition:效果请看日志的东东，
	 * aim 测试
	 */
	public void testBaseDaoImpl() {
		
		new UserDaoImpl();
		new RoleDaoImpl();
		
	}

}
