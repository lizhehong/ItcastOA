package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements
		ModelDriven<Department> {
	@Resource
	private DepartmentService departmentService;
	private Department model = new Department();
	/**
	 * 父部门的id
	 */
	private Long parentId;

	public DepartmentAction() {
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"====== DepartmentAction constructor OK  ======");
	}

	public Department getModel() {
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======得到部门模型======");
		return model;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 列出所有的部门
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String list() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======得到所有部门======");
		return "list";
	}

	/**
	 * 
	 * @Title: del
	 * @Description: 删除部门
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String del() throws Exception {
		departmentService.del(model.getId());
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======删除一个部门======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 增加一个部门
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String add() throws Exception {
		// 处理上级部门
		Department department = departmentService.getById(parentId);
		model.setParent(department);
		departmentService.save(model);
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======添加一个部门======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: edit
	 * @Description: 修改一个部门
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String edit() throws Exception {
		// 1.从数据库取出要修改的原始数据
		Department department = departmentService.getById(model.getId());
		// 页面上的选择框会返回父部门的id，也就是对应部门Department的唯一标识符id，所以查找对应的部门
		Department parentDepartment = departmentService.getById(parentId);
		// 判断此部门是否存在
		if (department != null) {
			// 此部门存在
			// 2.填充父部门到此部门
			department.setParent(parentDepartment);
			// 3.设置修改的属性
			department.setDescription(model.getDescription());
			department.setName(model.getName());

			// 4.更新到数据库
			departmentService.update(department);
			LoggerFactory.getLogger(DepartmentAction.class).info(
					"======进行部门修改======");
		} else
			// 此部门不存在
			LoggerFactory.getLogger(DepartmentAction.class).info(
					"======不进行部门修改======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: addUI
	 * @Description: 重定向到添加部门的页面
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String addUI() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======重定向到部门增加界面======");
		return "saveUI";
	}

	/**
	 * 
	 * @Title: editUI
	 * @Description: 重定向到修改部门的页面 并且如果有父部门则显示父部门卫默认的选择框显示，没有则默认选择框是"==请选择部门=="
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String editUI() throws Exception {
		// 用于部门选择框的内容而查看所有部门
		List<Department> departmentList = departmentService.findAll();
		// 根据选择的部门id查询数据库对应的部门
		Department department = departmentService.getById(model.getId());
		// 准备回显得数据
		ActionContext.getContext().getValueStack().push(department);
		// 当此部门存在父部们时才去查看父部门id用于选项框的初始值，用于防止没有上级部门的情况
		if (department.getParent() != null) {
			// 用于回显选项框的默认值
			this.parentId = department.getParent().getId();
		}

		// 用于部门选择框的内容 填充
		ActionContext.getContext().put("departmentList", departmentList);
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======重定向到部门修改界面======");
		return "saveUI";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
