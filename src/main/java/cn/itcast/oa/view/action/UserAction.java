package cn.itcast.oa.view.action;


import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.DepartmentUtil;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	/**
	 * aim:用于用户对应的部门id
	 */
	private Long departmentId;
	/**
	 * 当页面没有传值过来时，这个数组是空的，压根没有数组对象
	 */
	private Long[] roleIds;

	/**
	 * 
	 * @Title: list
	 * @Description: 列用户出所有的用户
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String list() throws Exception {
		ActionContext.getContext().put("userList", userService.findAll());
		return "list";
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除用户
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String del() throws Exception {
		userService.del(model.getId());
		return "tolist";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 添加用户 所以此刻 的保存 是默认的密码
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String add() throws Exception {
		// 别遗漏了 除了User自身的东东，还有对应Department,Role
		// 处理关联的一个部门
		model.setDepartment(departmentService.getById(departmentId));
		// 处理关联的多个岗位
		model.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));
		userService.save(model);
		return "tolist";
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: 修改用户
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String edit() throws Exception {
		User user = userService.getById(model.getId());
		//user外接设置
		user.setDepartment(departmentService.getById(departmentId));
		user.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));
		//user设置
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		userService.update(user);
		return "tolist";

	}

	/**
	 * 
	 * @Title: initPassword
	 * @Description: 相当于新建的时候的保存哦 初始化用户密码 1234
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String initPassword() throws Exception {
		User user = userService.getById(model.getId());
		userService.save(user);
		return "tolist";
	}

	/**
	 * 
	 * @Title: addUI
	 * @Description: 重定向到用户添加界面
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String addUI() throws Exception {
		// 准备数据:departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.getAllDepartmentList(
				topList, null);
		// 准备数据:roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI";
	}

	/**
	 * 
	 * @Title: editUI
	 * @Description: 重定向到用户修改界面
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String editUI() throws Exception {
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		//处理部门
		if(user.getDepartment()!=null){
			departmentId = user.getDepartment().getId();
		}
		//处理岗位
		roleIds = new Long[user.getRoles().size()];
		int index = 0;
		for(Role role:user.getRoles()){
			roleIds[index++] =  role.getId();		
		}
		// 回显数据
		// 准备数据:departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtil.getAllDepartmentList(
				topList, null);
		// 准备数据:roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		ActionContext.getContext().put("departmentList", departmentList);

		return "saveUI";

	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
