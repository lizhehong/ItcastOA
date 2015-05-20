package cn.itcast.oa.domain;
/**
 * 
* @ClassName: Role 
* @Description:  作为岗位的实体类
* @author lizhehong
* @date 2015年5月20日 下午10:05:46 
*
 */
public class Role {
	private Long id;
	private String name;
	private String descirption;

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

	public String getDescirption() {
		return descirption;
	}

	public void setDescirption(String descirption) {
		this.descirption = descirption;
	}

}
