package cn.itcast.oa.util;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		// 当前登陆用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		// 当前访问的URL地址
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();

		// 用于package 的 namespace属性没有写具体的范围
		if (namespace == null || "".equals(namespace.trim())) {
			namespace = "/";
		}
		// 用于package 的 namespace属性有写具体的范围
		if (!namespace.endsWith("/")) {
			namespace += "/";
		}
		String url = namespace + actionName;

		// 1.如果用户未登陆，转向登陆页面
		if (user == null) {
			if (url.startsWith("/loginout_login")) {// 包含loginout_loginUI或loginout_login
				// a.如果当前访问的是登陆功能，放行
				return invocation.invoke();
			} else {
				// b.如果当前访问的不是登陆功能，就转向登陆页面
				return "loginUI";
			}
		}

		// 2.如果用户已登陆，判断权限
		else {
			if(user.hasPrivilegeByUrl(url)){
			// a.如果有权限访问当前的URL，则放行
				return invocation.invoke();
			}else{
			// b.如果没有权限访问当前的URL，则转到提示消息的页面
				return "noPrivilegeUI";
			}
		}
	}
}
