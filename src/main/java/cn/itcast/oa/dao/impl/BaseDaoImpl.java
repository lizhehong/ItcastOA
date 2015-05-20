package cn.itcast.oa.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.oa.dao.BaseDao;
import cn.itcast.oa.util.ArrayToStringUtil;

/**
 * condition:一般在点击实现接口方法的时候，如果父接口有泛型,则先定义子接口泛型和父接口泛型<T> aim:基础接口
 * aim:abstract是为了不让spring对BaseDaoImpl实现，而是让子类的实现让它实现。不然clazz会因为null而报错
 * @author lizhehong
 *
 * @param <T>
 */
@Repository("baseDao")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource
	/**
	 * aim:只给同包或子类用
	 */
	protected HibernateTemplate hibernateTemplate;
	//只有子类能访问
	protected Class<T> clazz = null;
	/**
	 * aim:通过反射获取T的真实的值
	 * 
	 */
	public BaseDaoImpl(){
		//1.获取泛型父类的信息
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		
		//2.获取真实对象
		clazz =  (Class<T>) pt.getActualTypeArguments()[0];
		Logger log =  LoggerFactory.getLogger(clazz);
		
		log.info("调用的子类-------->"+clazz); 
	}
	
	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	public void delete(Long id) {
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

		return (T) hibernateTemplate.get(clazz, id);
	}

	public List<T> findAll() {
		
		return hibernateTemplate.find(//
				"from " + clazz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	public List<T> getByIds(Long[] ids) {

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
