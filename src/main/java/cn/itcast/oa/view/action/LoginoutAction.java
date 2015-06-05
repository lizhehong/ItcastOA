package cn.itcast.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.User;
@Controller
@Scope("prototype")
public class LoginoutAction extends BaseAction<User> {
	/**
	 * 
	* @Title: login 
	* @Description: 用于用户登录 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String login() throws Exception {
		//验证用户与密码，如果正确就返回这个用户，否则返回null
		User user = userService.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		//如果用户不存在，就转回登录页面，并提示错误消息
		if(user==null){
			addFieldError("login", "登录名或密码不正确");
			return "loginUI";
		}
		//如果登录名与密码都正确，就登录用户
		else{
			ActionContext.getContext().getSession().put("user", user);
			return "toHome";
		}
	}
	/**
	 * 
	* @Title: loginUI 
	* @Description: 用于用户登录显示的界面 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String loginUI() throws Exception {
		// TODO Auto-generated method stub
		return "loginUI";
	}
	/**
	 * 
	* @Title: logout 
	* @Description: 用户注销 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	
}
