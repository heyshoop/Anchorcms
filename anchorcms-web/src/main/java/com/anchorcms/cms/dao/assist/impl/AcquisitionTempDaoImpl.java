package com.anchorcms.cms.dao.assist.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.anchorcms.cms.dao.assist.AcquisitionTempDao;
import com.anchorcms.cms.model.assist.CmsAcquisitionTemp;
import com.anchorcms.common.hibernate.Finder;
import com.anchorcms.common.hibernate.HibernateBaseDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


@Repository
public class AcquisitionTempDaoImpl extends
		HibernateBaseDao<CmsAcquisitionTemp, Integer> implements
		AcquisitionTempDao {
	@SuppressWarnings("unchecked")
	public List<CmsAcquisitionTemp> getList(Integer siteId) {
		Finder f = Finder.create("from CmsAcquisitionTemp bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.siteId=:siteId");
			f.setParam("siteId", siteId);
		}
		f.append(" order by bean.tempId desc");
		return find(f);
	}

	public CmsAcquisitionTemp findById(Integer id) {
		CmsAcquisitionTemp entity = get(id);
		return entity;
	}

	public CmsAcquisitionTemp save(CmsAcquisitionTemp bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsAcquisitionTemp deleteById(Integer id) {
		CmsAcquisitionTemp entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	public Integer getPercent(Integer siteId) {
		Query query = getSession()
				.createQuery(
						"select max(finishPercent) from CmsAcquisitionTemp where site.siteId=:siteId")
				.setParameter("siteId", siteId);
		return (Integer) (query.uniqueResult() == null ? 0 : query
				.uniqueResult());
	}

	public void clear(Integer siteId, String channelUrl) {
		StringBuilder sb = new StringBuilder(100);
		Map<String, Object> params = new HashMap<String, Object>();
		sb.append("delete from CmsAcquisitionTemp where site.siteId=:siteId");
		params.put("siteId", siteId);
		if (StringUtils.isNotBlank(channelUrl)) {
			sb.append(" and channelUrl!=:channelUrl");
			params.put("channelUrl", channelUrl);
		}
		Query query = getSession().createQuery(sb.toString());
		for (Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		query.executeUpdate();
	}

	@Override
	protected Class<CmsAcquisitionTemp> getEntityClass() {
		return CmsAcquisitionTemp.class;
	}

}