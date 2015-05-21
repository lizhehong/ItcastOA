package cn.itcast.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.dao.DepartmentDao;
import cn.itcast.oa.domain.Department;
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department>  implements DepartmentDao {

}
