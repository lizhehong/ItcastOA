package cn.itcast.oa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void del(Long id) {
		departmentDao.delete(id);
	}
	@Transactional
	public void save(Department department) {
		departmentDao.save(department);
	}

	public Department getById(Long id) {
		return departmentDao.getById(id);
	}
	@Transactional
	public void update(Department department) {
		departmentDao.update(department);
	}

	public List<Department> findTopList() {
		return departmentDao.findTopList();
	}

	public List<Department> findChildrenList(Long parentId) {
		
		return departmentDao.findChildrenList(parentId);
	}

}
