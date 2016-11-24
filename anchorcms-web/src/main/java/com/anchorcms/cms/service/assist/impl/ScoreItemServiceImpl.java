package com.anchorcms.cms.service.assist.impl;

import com.anchorcms.cms.dao.assist.ScoreItemDao;
import com.anchorcms.cms.model.assist.CmsScoreItem;
import com.anchorcms.cms.service.assist.ScoreItemService;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ScoreItemServiceImpl implements ScoreItemService {
	@Transactional(readOnly = true)
	public Pagination getPage(Integer groupId, int pageNo, int pageSize) {
		Pagination page = dao.getPage(groupId,pageNo, pageSize);
		return page;
	}

	@Transactional(readOnly = true)
	public CmsScoreItem findById(Integer id) {
		CmsScoreItem entity = dao.findById(id);
		return entity;
	}

	public CmsScoreItem save(CmsScoreItem bean) {
		dao.save(bean);
		return bean;
	}

	public CmsScoreItem update(CmsScoreItem bean) {
		Updater<CmsScoreItem> updater = new Updater<CmsScoreItem>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsScoreItem deleteById(Integer id) {
		CmsScoreItem bean = dao.deleteById(id);
		return bean;
	}
	
	public CmsScoreItem[] deleteByIds(Integer[] ids) {
		CmsScoreItem[] beans = new CmsScoreItem[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private ScoreItemDao dao;

	@Autowired
	public void setDao(ScoreItemDao dao) {
		this.dao = dao;
	}
}