package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.apache.struts2.views.jsp.ParamTag;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.UserService;
import cn.itcast.oa.view.action.DepartmentAction;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {
	// ===================== 声明Service =====================//
	@Resource
	protected RoleService roleService;
	
	@Resource
	protected DepartmentService departmentService;

	@Resource
	protected UserService userService;
	// ====================== 对ModelDriven的支持 =====================//

	protected T model;

	public BaseAction(){
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T>clazz =  (Class<T>) pt.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public T getModel() {
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======得到部门模型======");
		return model;
	}
}
