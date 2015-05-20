package cn.itcast.oa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.oa.dao.RoleDao;
import cn.itcast.oa.domain.Role;

/**
 * aim:权限的独立接口
 * 
 * @author lizhehong
 *
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

}
