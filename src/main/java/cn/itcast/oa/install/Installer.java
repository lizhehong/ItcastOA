package cn.itcast.oa.install;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.base.DaoSupportImpl;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.User;

/**
 * 
 * @ClassName: Install
 * @Description: 安装程序（初始化数据专用）
 * @author lizhehong
 * @date 2015年5月28日 下午1:20:33
 *
 */
@Component
public class Installer implements InstallerService{
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Transactional
	public void install() {
		// ========================================================
		// 1.超级管理员
		User user = new User();
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin"));
		hibernateTemplate.save(user);
		// ========================================================
		// 2.权限数据
		Privilege menu, menu1, menu2, menu3, menu4, menu5;
		// menu 这是一个分类的作用，一点击就会出现岗位管理和部门管理，用户管理，所以uri=null
		menu = new Privilege("系统管理", null, null);
		menu1 = new Privilege("岗位管理", "/role_list", menu);
		menu2 = new Privilege("部门管理", "/department_list", menu);
		menu3 = new Privilege("用户管理", "/user_list", menu);
		hibernateTemplate.save(menu);
		hibernateTemplate.save(menu1);
		hibernateTemplate.save(menu2);
		hibernateTemplate.save(menu3);

		hibernateTemplate.save(new Privilege("岗位列表", "/role_list", menu1));
		hibernateTemplate.save(new Privilege("岗位删除", "/role_delete", menu1));
		// add和edit 其实分别会另外对应addUI和editUI所以一个功能对应两个地址
		// 为了拦截器(权限),容易是实现，这里只取不带UI,到时候拦截器，拦截了UI的在判断去除UI
		hibernateTemplate.save(new Privilege("岗位添加", "/role_add", menu1));
		hibernateTemplate.save(new Privilege("岗位修改", "/role_edit", menu1));

		hibernateTemplate
				.save(new Privilege("部门列表", "/department_list", menu2));
		hibernateTemplate.save(new Privilege("部门删除", "/department_delete",
				menu2));
		// add和edit 其实分别会另外对应addUI和editUI所以一个功能对应两个地址
		// 为了拦截器(权限),容易是实现，这里只取不带UI,到时候拦截器，拦截了UI的在判断去除UI
		hibernateTemplate.save(new Privilege("部门添加", "/department_add", menu2));
		hibernateTemplate
				.save(new Privilege("部门修改", "/department_edit", menu2));

		hibernateTemplate.save(new Privilege("用户列表", "/user_list", menu3));
		hibernateTemplate.save(new Privilege("用户删除", "/user_delete", menu3));
		// add和edit 其实分别会另外对应addUI和editUI所以一个功能对应两个地址
		// 为了拦截器(权限),容易是实现，这里只取不带UI,到时候拦截器，拦截了UI的在判断去除UI
		hibernateTemplate.save(new Privilege("用户添加", "/user_add", menu3));
		hibernateTemplate.save(new Privilege("用户修改", "/user_edit", menu3));
		hibernateTemplate.save(new Privilege("用户初始化密码", "/user_initPassword",
				menu));

		// ========================================================

		menu = new Privilege("网上交流", null, null);
		menu1 = new Privilege("论坛管理", "/formManage_list", menu);
		menu2 = new Privilege("论坛", "/form_list", menu);
		hibernateTemplate.save(menu);
		hibernateTemplate.save(menu1);
		hibernateTemplate.save(menu2);

		// ========================================================

		menu = new Privilege("审批流转", null, null);
		menu1 = new Privilege("审批流程管理", "/processDefinition_List", menu);
		menu2 = new Privilege("申请模板管理", "/template_List", menu);
		menu3 = new Privilege("起草申请", "/flow_templateList", menu);
		menu4 = new Privilege("待我审批", "/flow_myTaskList", menu);
		menu5 = new Privilege("我的申请查询", "/flow_myApplicationList", menu);
		hibernateTemplate.save(menu);
		hibernateTemplate.save(menu1);
		hibernateTemplate.save(menu2);
		hibernateTemplate.save(menu3);
		hibernateTemplate.save(menu4);
		hibernateTemplate.save(menu5);
	}

	public static void main(String[] args) {
		LoggerFactory.getLogger(Installer.class).info("======安装开始======");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		InstallerService service =(InstallerService) context.getBean("installer");
		service.install();
		LoggerFactory.getLogger(Installer.class).info("======安装结束======");
	}
}
