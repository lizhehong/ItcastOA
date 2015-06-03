package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	/**
	 * 使用数组的Long数组的形式接收前台的参数
	 */
	private Long[] privilegeIds;

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
			LoggerFactory.getLogger(RoleAction.class)
					.info("======进行修改岗位======");
		} else
			LoggerFactory.getLogger(RoleAction.class).info(
					"======不进行修改岗位======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: setPrivilege
	 * @Description: 为岗位设置权限
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String setPrivilege() throws Exception {
		Role role = roleService.getById(model.getId());
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		if (role != null) {
			role.setPrivileges(new HashSet<Privilege>(privilegeList));
			roleService.update(role);
			LoggerFactory.getLogger(RoleAction.class).info(
					"======进行修改岗位权限======");
		} else
			LoggerFactory.getLogger(RoleAction.class).info(
					"======不进行修改岗位权限======");
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

	/**
	 * 
	 * @Title: setPrivilegeUI
	 * @Description: 为岗位设置权限的UI界面
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String setPrivilegeUI() throws Exception {
		//准备数据
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", topPrivilegeList);
		// 根据界面传过来的id值来进行判断是哪个岗位
		Role role = roleService.getById(model.getId());
		// 将role放在栈顶，用于回显
		ActionContext.getContext().getValueStack().push(role);

		// 迭代当前岗位的所有权限,用于前面板的显示
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for (Privilege p : role.getPrivileges()) {
			privilegeIds[index++] = p.getId();
		}

		// 记录日志
		LoggerFactory.getLogger(RoleAction.class).info(
				"======重定向到修改岗位权限界面======");
		return "setPrivilegeUI";
	}

	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
