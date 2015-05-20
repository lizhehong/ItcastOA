package cn.itcast.oa.base;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.itcast.oa.dao.RoleDao;
import cn.itcast.oa.dao.UserDao;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.util.ArrayToStringUtil;

public class BaseDaoTest {

	@Test
	public void testGetById() {
		UserDao userDao = null;
		RoleDao roleDao = null;
		assertNotEquals(userDao.getById(1L), roleDao.getById(1L));
	}
}
