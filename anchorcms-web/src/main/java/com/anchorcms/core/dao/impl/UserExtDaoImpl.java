package com.anchorcms.core.dao.impl;

import com.anchorcms.common.hibernate.HibernateBaseDao;
import com.anchorcms.core.dao.UserExtDao;
import com.anchorcms.core.model.CmsUserExt;
import org.springframework.stereotype.Repository;


@Repository
public class UserExtDaoImpl extends HibernateBaseDao<CmsUserExt, Integer> implements UserExtDao {
	public CmsUserExt findById(Integer id) {
		CmsUserExt entity = get(id);
		return entity;
	}

	public CmsUserExt save(CmsUserExt bean) {
		getSession().save(bean);
		return bean;
	}
	
	@Override
	protected Class<CmsUserExt> getEntityClass() {
		return CmsUserExt.class;
	}
}