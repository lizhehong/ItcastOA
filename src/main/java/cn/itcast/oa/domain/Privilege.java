package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Privilege implements Serializable{
	private Long id;
	/**
	 * NameSpace+ActionName
	 */
	private String url;
	private String name;

	private Set<Role> roles = new HashSet<Role>(0);
	private Privilege parent;

	private Set<Privilege> children = new HashSet<Privilege>(0);

	/**
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description: 用于spring调用默认构造器
	 * </p>
	 */
	public Privilege() {

	}

	/**
	 * <p>
	 * Title: 安装
	 * </p>
	 * <p>
	 * Description: 用于installer 也就是简化web服务程序的安装
	 * </p>
	 * 
	 * @param uri
	 * @param name
	 * @param parent
	 */
	public Privilege(String name, String url, Privilege parent) {
		this.url = url;
		this.name = name;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Privilege getParent() {
		return parent;
	}

	public void setParent(Privilege parent) {
		this.parent = parent;
	}

	public Set<Privilege> getChildren() {
		return children;
	}

	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}

}
