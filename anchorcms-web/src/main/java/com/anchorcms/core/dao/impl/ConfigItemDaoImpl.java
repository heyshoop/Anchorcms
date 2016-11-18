package com.anchorcms.core.dao.impl;

import com.anchorcms.common.hibernate.HibernateBaseDao;
import com.anchorcms.core.dao.ConfigItemDao;
import com.anchorcms.core.model.CmsConfigItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConfigItemDaoImpl extends
		HibernateBaseDao<CmsConfigItem, Integer> implements ConfigItemDao {
	@SuppressWarnings("unchecked")
	public List<CmsConfigItem> getList(Integer configId, Integer category) {
		StringBuilder sb = new StringBuilder(
				"from CmsConfigItem bean where bean.config.id=? and bean.category=?");
		sb.append(" order by bean.priority asc,bean.id asc");
		return find(sb.toString(),configId,category);
	}

	public CmsConfigItem findById(Integer id) {
		CmsConfigItem entity = get(id);
		return entity;
	}

	public CmsConfigItem save(CmsConfigItem bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsConfigItem deleteById(Integer id) {
		CmsConfigItem entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsConfigItem> getEntityClass() {
		return CmsConfigItem.class;
	}
}