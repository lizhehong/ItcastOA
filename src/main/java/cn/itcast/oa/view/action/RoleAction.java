package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>  {

	public RoleAction() {
		LoggerFactory.getLogger(RoleAction.class).info(
				"====== RoleAction constructor OK  ======");
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 列岗位出所有的岗位
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String list() throws Exception {
		// 准备数据
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		LoggerFactory.getLogger(RoleAction.class).info("======列岗位出所有的岗位======");
		return "list";
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除岗位
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String del() throws Exception {
		roleService.del(model.getId());
		LoggerFactory.getLogger(RoleAction.class).info("======删除一个岗位======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 添加岗位
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String add() throws Exception {
		// 1.添加对象
		// 2.保存到数据库
		roleService.save(model);
		LoggerFactory.getLogger(RoleAction.class).info("======添加一个岗位======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: 修改
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String edit() throws Exception {
		Role role = roleService.getById(model.getId());
		if (role != null) {
			role.setDescription(model.getDescription());
			role.setName(model.getName());
			roleService.update(role);
			LoggerFactory.getLogger(RoleAction.class).info("======进行修改岗位======");
		}else
			LoggerFactory.getLogger(RoleAction.class).info("======不进行修改岗位======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: addUI
	 * @Description: 重定向到添加界面
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String addUI() throws Exception {
		LoggerFactory.getLogger(RoleAction.class).info("======重定向到添加界面======");
		return "saveUI";
	}

	/**
	 * 
	 * @Title: editUI
	 * @Description: 重定向到修改界面
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String editUI() throws Exception {
		Role role = roleService.getById(model.getId());
		// 将role放在栈顶，用于回显
		ActionContext.getContext().getValueStack().push(role);
		LoggerFactory.getLogger(RoleAction.class).info("======重定向到修改界面======");
		return "saveUI";
	}
}
