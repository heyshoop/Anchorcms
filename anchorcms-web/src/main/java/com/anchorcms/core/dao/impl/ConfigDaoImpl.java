package com.anchorcms.core.dao.impl;

import java.util.List;

import com.anchorcms.common.hibernate.HibernateBaseDao;
import com.anchorcms.core.dao.ConfigDao;
import com.anchorcms.core.model.Config;
import org.springframework.stereotype.Repository;


@Repository
public class ConfigDaoImpl extends HibernateBaseDao<Config, String> implements ConfigDao {
	@SuppressWarnings("unchecked")
	public List<Config> getList() {
		String hql = "from Config";
		return find(hql);
	}

	public Config findById(String id) {
		Config entity = get(id);
		return entity;
	}

	public Config save(Config bean) {
		getSession().save(bean);
		return bean;
	}

	public Config deleteById(String id) {
		Config entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<Config> getEntityClass() {
		return Config.class;
	}
}