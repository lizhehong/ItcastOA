package cn.itcast.oa.dao;

import java.util.List;
/**
 * aim:公共接口,提供给了其他接口的共同的方法
 * @author lizhehong
 *
 * @param <T>
 */
public interface BaseDao<T> {
	/**
	 * aim:保存实体
	 * @param entity
	 */
	public void save(T entity);
	/**
	 * aim:删除实体
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * aim:更新实体
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * aim:根据id查找实体
	 * @param id
	 * @return id为空则返回null不查数据库
	 */
	public T getById(Long id);
	/**
	 * aim:查询所有
	 * @return
	 */
	public List<T> findAll();
	/**
	 * aim:根据id数组查询多个
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(Long[] ids);
}