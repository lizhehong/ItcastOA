package cn.itcast.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.dao.impl.BaseDaoImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DepartmentDao departmentDao;

	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	public void del(Long id) {
		departmentDao.delete(id);
	}

	public void save(Department department) {
		departmentDao.save(department);
	}

	public Department getById(Long id) {
		return departmentDao.getById(id);
	}

	public void update(Department department) {
		departmentDao.update(department);
	}

}
