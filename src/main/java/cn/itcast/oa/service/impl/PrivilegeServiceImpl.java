package cn.itcast.oa.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

/**
 * 
 * @ClassName: PrivilegeServiceImpl
 * @Description: 用于PrivilegeService的实现
 * @author lizhehong
 * @date 2015年6月2日 上午8:45:51
 *
 */
@Service("privilegeService")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements
		PrivilegeService {

	public List<Privilege> findTopList() {

		return hibernateTemplate
				.find("FROM Privilege p WHERE p.parent IS NULL");
	}

	public List<String> getAllPrivilegeUrls() {
		return hibernateTemplate.find("SELECT p.url FROM Privilege p WHERE p.url IS NOT NULL");
		
	}

}
