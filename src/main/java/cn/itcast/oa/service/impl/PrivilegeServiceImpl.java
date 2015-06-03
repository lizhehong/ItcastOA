package cn.itcast.oa.service.impl;

import java.util.List;

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
				.find("from Privilege p where p.parent IS null");
	}

}
