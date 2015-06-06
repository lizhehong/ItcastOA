package cn.itcast.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @ClassName: Role
 * @Description: 作为岗位的实体类
 * @author lizhehong
 * @date 2015年5月20日 下午10:05:46
 *
 */
public class Role implements Serializable{
	/**
	 * 岗位主键
	 */
	private Long id;
	/**
	 * 岗位名称
	 */
	private String name;
	/**
	 * 岗位描述
	 */
	private String description;
	/**
	 * 岗位对应的就职者 多个
	 */
	private Set<User> users = new HashSet<User>(0);
	/**
	 * 岗位对应多个权限
	 */
	private Set<Privilege> privileges;
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

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
