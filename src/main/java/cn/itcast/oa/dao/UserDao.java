package cn.itcast.oa.dao;




import cn.itcast.oa.domain.User;
/**
 * aim:用户接口
 * @author lizhehong
 *
 */
public interface UserDao extends BaseDao<User>{
	public User getByNameAndPassword(String username,String password);
}
