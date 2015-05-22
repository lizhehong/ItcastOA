package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

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
	 * 作用: 1.对于list.jsp页面 显示界面的总入口变量，用于判断显示无子部门的列表还是有对应子部门的列表 影响因素 1.add()函数
	 * 暂时没有传parentId 2.edit()函数 暂时没有传parentId 3.具体界面的具体部门href 会传过来parentId
	 * 4.返回上一级的按钮 2.对于saveUI.jsp页面 默认选项框的值 影响因素 1.addUI()函数 "新建按钮传过来的"
	 * 
	 */
	private Long parentId;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Department getModel() {
		LoggerFactory.getLogger(DepartmentAction.class).info(
				"======得到部门模型======");
		return model;
	}

	/**
	 * 
	 * @Title: list
	 * @Description: 只列出一层(同级的)部门数据，默认显示最顶级的部门列表
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String list() throws Exception {
		List<Department> departmentList = null;
		// 区分list.jsp显示的问题
		if (parentId == null) {// 默认显示顶级部门列表 在list.jsp中 只有一种情况
								// 其实是利用第一次不可能传值parentId来作为判断依据的
			departmentList = departmentService.findTopList();
			LoggerFactory.getLogger(DepartmentAction.class).info(
					"======得到无父部门的部门列表======");
		} else {
			// 浏览器第二次请求开始
			// 显示指定部门的子部门列表 在list.jsp中 可以通过parentId的具体指 访问任何一级的子列表
			departmentList = departmentService.findChildrenList(parentId);
			// 用于"返回上一级"按钮的传递参数
			Long callUpLevelValue = null;
			if (departmentList.size() != 0) {
				// 存在子部门
				LoggerFactory.getLogger(DepartmentAction.class).info(
						"======得到Name="
								+ departmentList.get(0).getParent().getName()
								+ "的子部门列表======");
				// 存储按下的的部门名,的父级部门
				Department department = departmentList.get(0).getParent()
						.getParent();
				// 存在父类
				if (department != null)
					callUpLevelValue = department.getId();
			} else {
				// 不存在子部门
				// 存储按下的的部门名,的父级部门
				Department department = departmentService.getById(parentId)
						.getParent();
				// 存在父类
				if (department != null)
					callUpLevelValue = department.getId();
				LoggerFactory.getLogger(DepartmentAction.class).info(
						"======得到无子部门的部门列表======");
			}
			// 用于"返回上一级"按钮传参数的设置
			ActionContext.getContext().put("callUpLevel", callUpLevelValue);
			// 补充作为"新建按钮的传递参数"，具体按了哪个部门的id
			ActionContext.getContext().put("parentId", parentId);
		}
		ActionContext.getContext().put("departmentList", departmentList);
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
		// 找到所有的部门
		List<Department> departmentList = departmentService.findAll();
		// 获取"新建"按钮的对应父类的id值,然后放于选项框的默认值
		// ActionContext.getContext().put("parentId", parentId);
		// 作为上级选择列表的值
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

}
