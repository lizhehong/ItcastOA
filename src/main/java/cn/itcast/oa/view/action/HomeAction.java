package cn.itcast.oa.view.action;


import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
//没有公共资源所以用默认的单例
@Controller
public class HomeAction extends ActionSupport{
	
	
	public String index() throws Exception {
		return "index";
	}
	public String top() throws Exception {
		return "top";
	}
	public String bottom() throws Exception {
		return "bottom";
	}
	public String left() throws Exception {
		return "left";
	}
	public String right() throws Exception {
		return "right";
	}
}
