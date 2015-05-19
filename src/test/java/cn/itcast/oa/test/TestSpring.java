package cn.itcast.oa.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	/**
	 * aim:			测试SessionFacory是否启动成功
	 * condition:	配置好spring与hibernate和之间联系
	 * problem:		找不到slf4 impl 只要去mvn下载对应需要的日志 这里使用slf4j-log4j12
	 */
	@Test
	public void testSessionFactory() {
		assertNotNull(context.getBean("sessionFactory"));
	}
	/**
	 * aim:测试事务管理
	 * @throws Exception
	 */
	@Test
	public void testTX() throws Exception{
		TestService service = (TestService) context.getBean("testService");
		service.saveTwoUsers();
	}
}
