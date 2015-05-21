package cn.itcast.oa.domain;

import java.util.HashSet;
import java.util.Set;

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
