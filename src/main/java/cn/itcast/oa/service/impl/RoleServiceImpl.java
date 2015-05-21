package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.oa.dao.RoleDao;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public void del(Long id) {
		roleDao.delete(id);

	}

	public void save(Role role) {
		roleDao.save(role);

	}

	public Role getById(Long id) {

		return roleDao.getById(id);
	}

	public void update(Role role) {
		roleDao.update(role);
		
	}

}
