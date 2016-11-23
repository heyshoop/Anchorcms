package com.anchorcms.cms.dao.assist.impl;

import java.util.Date;
import java.util.List;

import com.anchorcms.cms.dao.assist.AccountDrawDao;
import com.anchorcms.cms.model.assist.CmsAccountDraw;
import com.anchorcms.common.hibernate.Finder;
import com.anchorcms.common.hibernate.HibernateBaseDao;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.DateUtils;
import org.springframework.stereotype.Repository;


@Repository
public class AccountDrawDaoImpl extends HibernateBaseDao<CmsAccountDraw, Integer> implements AccountDrawDao {
	public Pagination getPage(Integer userId, Short applyStatus,
							  Date applyTimeBegin, Date applyTimeEnd, int pageNo, int pageSize) {
		String hql="select bean  from CmsAccountDraw bean where 1=1 ";
		Finder f=Finder.create(hql);
		if(userId!=null){
			if(userId!=0){
				f.append(" and bean.drawUser.userIduserId").setParam("userId", userId);
			}else{
				f.append(" and 1!=1");
			}
		}
		if(applyStatus!=null&&applyStatus!=-1){
			f.append(" and bean.applyStatus=:applyStatus").setParam("applyStatus", applyStatus);
		}
		if(applyTimeBegin!=null){
			f.append(" and bean.applyTime>=:applyTimeBegin")
			.setParam("applyTimeBegin", DateUtils.getStartDate(applyTimeBegin));
		}
		if(applyTimeEnd!=null){
			f.append(" and bean.applyTime<=:applyTimeEnd")
			.setParam("applyTimeEnd", DateUtils.getFinallyDate(applyTimeEnd));
		}
		f.setCacheable(true);
		return find(f, pageNo, pageSize);
	}
	
	public List<CmsAccountDraw> getList(Integer userId,Short[] status,Integer count){
		String hql="select bean  from CmsAccountDraw bean where 1=1 ";
		Finder f=Finder.create(hql);
		if(userId!=null){
			f.append(" and bean.drawUser.userId=:userId").setParam("userId", userId);
		}
		if(status!=null){
			f.append(" and bean.applyStatus in(:status)").setParamList("status", status);
		}
		f.setCacheable(true);
		f.setMaxResults(count);
		return find(f);
	}

	public CmsAccountDraw findById(Integer id) {
		CmsAccountDraw entity = get(id);
		return entity;
	}

	public CmsAccountDraw save(CmsAccountDraw bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsAccountDraw deleteById(Integer id) {
		CmsAccountDraw entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<CmsAccountDraw> getEntityClass() {
		return CmsAccountDraw.class;
	}
}