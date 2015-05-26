package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service("departmentService")
@SuppressWarnings("unchecked")
public class DepartmentServiceImpl extends DaoSupportImpl<Department> implements
		DepartmentService {

	public List<Department> findTopList() {
		return hibernateTemplate
				.find("FROM Department d WHERE d.parent IS null");
	}

	public List<Department> findChildrenList(Long parentId) {

		return hibernateTemplate.find(
				"FROM Department d WHERE d.parent.id = ?", parentId);
	}

}
