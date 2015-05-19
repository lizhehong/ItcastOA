package cn.itcast.oa.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
/**
 * aim:测试testService 是否被spring注入,struts2是否正常
 * @author lizhehong
 *
 */
@Controller
public class TestAction extends ActionSupport {
	@Resource
	private TestService testService;
	@Override
	public String execute() throws Exception {
		System.out.println("---------->=经过TestService");
		System.out.println("---------->="+testService);
		return SUCCESS;
	}
}
