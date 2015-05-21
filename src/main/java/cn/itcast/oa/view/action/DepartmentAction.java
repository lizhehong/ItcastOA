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
		if (department != null) {
			
			// 2.设置修改的属性
			department.setDescription(model.getDescription());
			department.setName(model.getName());
			
			// 3.更新到数据库
			departmentService.update(department);
			LoggerFactory.getLogger(DepartmentAction.class).info(
					"======进行部门修改======");
		} else
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
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======重定向到部门增加界面======");
		return "saveUI";
	}

	/**
	 * 
	 * @Title: editUI
	 * @Description: 重定向到修改部门的页面
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String editUI() throws Exception {
		Department department = departmentService.getById(model.getId());
		// 准备回显得数据
		ActionContext.getContext().getValueStack().push(department);
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======重定向到部门修改界面======");
		return "saveUI";
	}

}
