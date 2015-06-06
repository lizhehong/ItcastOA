package cn.itcast.oa.util;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

/**
 * 
 * @ClassName: OAInitListenter
 * @Description: 用于权限的加载，因为对于一个系统来说，权限是在系统启动后，就不会改变的了
 * @author lizhehong
 * @date 2015年6月4日 下午11:59:56
 *
 */
public class OAInitListenter implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		// 1.保持整个工程中只有一个工厂，不能new classpathXml...(获取Spring的监听器中创建的Spring容器对象)
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(application);
		// 2.从Spring容易中取出对象
		PrivilegeService privilegeService = (PrivilegeService) context
				.getBean("privilegeService");
		// 3.查询所有的顶层的权限列表，并放到application作用域当中
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		// 4.放入作用域
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		LoggerFactory.getLogger(OAInitListenter.class).info(
				"======Tomcat 启动 将顶层权限放入 application作用域中 ======");
		// 5.查询出所有的权限的URL集合，并放到Application作用域中，用于，判断是否是登陆后需要控制的权限，用于区分基础功能
		List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		LoggerFactory.getLogger(OAInitListenter.class).info(
				"======Tomcat 启动 将登陆后需要控制的基本权限放入 application作用域中 ======");
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
