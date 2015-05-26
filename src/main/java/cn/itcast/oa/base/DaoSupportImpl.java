package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.util.ArrayToStringUtil;

/**
 * 通过BaseDaoImpl改造而来
 * 
 * @author lizhehong
 *
 * @param <T>
 */
@Transactional
public class DaoSupportImpl<T> implements DaoSupport<T> {
	@Resource
	/**
	 * aim:只给同包或子类用
	 */
	protected HibernateTemplate hibernateTemplate;
	// 只有子类能访问
	protected Class<T> clazz = null;

	/**
	 * aim:通过反射获取T的真实的值
	 * 
	 */
	public DaoSupportImpl() {
		// 1.获取泛型父类的信息
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();

		// 2.获取真实对象
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		Logger log = LoggerFactory.getLogger(clazz);

		log.info("调用的子类-------->" + clazz);
	}

	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	public void del(Long id) {
		if (id != null) {
			T entity = (T) getById(id);
			if (entity != null)
				hibernateTemplate.delete(entity);
		} else {
			return;
		}

	}

	public void update(T entity) {
		hibernateTemplate.update(entity);

	}

	@SuppressWarnings("unchecked")
	public T getById(Long id) {
		if (id != null)
			return (T) hibernateTemplate.get(clazz, id);
		else
			return null;
	}

	public List<T> findAll() {

		return hibernateTemplate.find(//
				"from " + clazz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			//空集合的常量,不要放回空哦，免得调用者要再去判断
			return Collections.EMPTY_LIST; 
		}
		return hibernateTemplate.find(//
				"from "
						+ clazz.getSimpleName()//
						+ " where id in (" //
						+ ArrayToStringUtil.arrayToStringBySeparator(ids, "?",
								",") //
						+ ")"//
				, ids);//

	}

}
