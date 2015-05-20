package cn.itcast.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport {
	@Resource
	private RoleService roleService;
	private Long id;
	private Logger logger = LoggerFactory.getLogger(RoleAction.class);

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
		logger.info("======列岗位出所有的岗位======");
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
		roleService.del(id);
		logger.info("======删除岗位======");
		return "tolist";
	}

	/**
	 * 
	 * @Title: add
	 * @Description: 添加
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public String add() throws Exception {

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
		return "addUI";
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
		return "editUI";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
