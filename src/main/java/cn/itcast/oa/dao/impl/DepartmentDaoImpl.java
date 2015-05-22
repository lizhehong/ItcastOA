package cn.itcast.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.domain.Department;

@Repository("departmentDao")
@SuppressWarnings("unchecked")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements
		DepartmentDao {

	public List<Department> findTopList() {

		return hibernateTemplate
				.find("FROM Department d WHERE d.parent IS null");
	}

	public List<Department> findChildrenList(Long parentId) {

		return hibernateTemplate.find(
				"FROM Department d WHERE d.parent.id = ?", parentId);
	}

}
