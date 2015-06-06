package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Department implements Serializable{
	/**
	 * 部门主键
	 */
	private Long id;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 部门说明
	 */
	private String description;
	/**
	 * 部门内的人员
	 */
	private Set<User> users = new HashSet<User>(0);
	/**
	 * 对应的上一级部门 一个
	 */
	private Department parent;
	/**
	 * 对应的下级部门 多个
	 */
	private Set<Department> childrens = new HashSet<Department>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Set<Department> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<Department> childrens) {
		this.childrens = childrens;
	}




}
