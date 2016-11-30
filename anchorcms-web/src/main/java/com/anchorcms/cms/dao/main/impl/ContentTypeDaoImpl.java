package com.anchorcms.cms.dao.main.impl;

import java.util.List;

import com.anchorcms.cms.dao.main.ContentTypeDao;
import com.anchorcms.cms.model.main.ContentType;
import com.anchorcms.common.hibernate.Finder;
import com.anchorcms.common.hibernate.HibernateBaseDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


@Repository
public class ContentTypeDaoImpl extends HibernateBaseDao<ContentType, Integer>
		implements ContentTypeDao {
	@SuppressWarnings("unchecked")
	public List<ContentType> getList(boolean containDisabled) {
		Finder f = Finder.create("from ContentType bean");
		if (!containDisabled) {
			f.append(" where bean.isDisabled=false");
		}
		f.append(" order by bean.typeId asc");
		return find(f);
	}

	public ContentType getDef() {
		String hql = "from ContentType bean"
				+ " where bean.isDisabled=false order by bean.typeId asc";
		Query query = getSession().createQuery(hql).setMaxResults(1);
		List<?> list = query.list();
		if (list.size() > 0) {
			return (ContentType) list.get(0);
		} else {
			return null;
		}
	}

	public ContentType findById(Integer id) {
		ContentType entity = get(id);
		return entity;
	}

	public ContentType save(ContentType bean) {
		getSession().save(bean);
		return bean;
	}

	public ContentType deleteById(Integer id) {
		ContentType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<ContentType> getEntityClass() {
		return ContentType.class;
	}
}