package com.anchorcms.cms.service.assist.impl;

import com.anchorcms.cms.dao.assist.JobApplyDao;
import com.anchorcms.cms.model.assist.CmsJobApply;
import com.anchorcms.cms.service.assist.JobApplyService;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class JobApplyServiceImpl implements JobApplyService {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer userId, Integer contentId, Integer siteId, boolean cacheable, int pageNo, int pageSize) {
		Pagination page = dao.getPage(userId,contentId,siteId,cacheable,pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public CmsJobApply findById(Integer id) {
		CmsJobApply entity = dao.findById(id);
		return entity;
	}

	public CmsJobApply save(CmsJobApply bean) {
		dao.save(bean);
		return bean;
	}

	public CmsJobApply update(CmsJobApply bean) {
		Updater<CmsJobApply> updater = new Updater<CmsJobApply>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsJobApply deleteById(Integer id) {
		CmsJobApply bean = dao.deleteById(id);
		return bean;
	}
	
	public CmsJobApply[] deleteByIds(Integer[] ids) {
		CmsJobApply[] beans = new CmsJobApply[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private JobApplyDao dao;

	@Autowired
	public void setDao(JobApplyDao dao) {
		this.dao = dao;
	}
}