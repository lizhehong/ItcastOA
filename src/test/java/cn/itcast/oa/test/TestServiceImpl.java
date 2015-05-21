package cn.itcast.oa.test;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import cn.itcast.oa.domain.User;




@Service("testService")
/**
 * aim: implements TestService 为了能让spring使用jdk代理
 * @author lizhehong
 *
 */
public class TestServiceImpl implements TestService {
	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	/**
	 * condition:只用spring相关的，开启事务。
	 * problem: aim:测试事务是否能保持 一个事务中的所有操作同步
	 */
	public void saveTwoUsers() {
		hibernateTemplate.save(new User());
	}
}
