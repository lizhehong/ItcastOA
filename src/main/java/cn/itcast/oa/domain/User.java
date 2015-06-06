package cn.itcast.oa.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class User {
	/**
	 * 用户主键
	 */
	private Long id;
	/**
	 * 用户登录名
	 */
	private String loginName;
	/**
	 * 登陆密码 默认:1234
	 */
	private String password;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 用户性别
	 */
	private String gender;
	/**
	 * 用户手机号码
	 */
	private String phoneNumber;
	/**
	 * 用户邮箱地址
	 */
	private String email;
	/**
	 * 用户描述
	 */
	private String description;
	/**
	 * 用户所属部门 一个
	 */
	private Department department;
	/**
	 * 用户所属岗位
	 */
	private Set<Role> roles = new HashSet<Role>(0);

	/**
	 * 
	 * @Title: hasPrivilegeByName
	 * @Description: 为left.jsp提供OGNL直接调用 判断当前用户是否有指定名称的权限
	 * @param @param privilegeName
	 * @param @return
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean hasPrivilegeByName(String privilegeName) {
		// 如果是超级管理员,就有所有的权限
		if (isAdmin()) {
			return true;
		}
		// 如果不是超级管理员
		for (Role role : roles) {
			for (Privilege privilege : role.getPrivileges()) {
				if (privilege.getName().equals(privilegeName)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * @Title: hasPrivilegeByUrl
	 * @Description: 当前用户是否有 指定的URL权限
	 * @param @param privUrl
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
		// 如果是超级管理员,就有所有的全新啊
		if (isAdmin()) {
			return true;
		}

		// 1.去掉后面的参数字符串(如果有)
		int pos = privUrl.indexOf("?");
		if (pos > -1)
			privUrl = privUrl.substring(0, pos);
		// 2.去掉后面的ui后缀(如果有)
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}

		// 如果是普通用户，则需要判断权限
		// a.如果这个URL是不需要控制的功能(登录后就能直接使用的)，应该返回true
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		} else {
			// b.如果这个URL是需要控制的功能(登录后，还得有对应的权限使用的)，这是应该判断权限的
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}

			return false;
		}
	}

	private boolean isAdmin() {
		return "admin".equals(loginName);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
