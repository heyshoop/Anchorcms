package com.anchorcms.cms.service.assist.impl;

import java.util.List;

import com.anchorcms.cms.dao.assist.OriginDao;
import com.anchorcms.cms.model.assist.CmsOrigin;
import com.anchorcms.cms.service.assist.OriginService;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OriginServiceImpl implements OriginService {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<CmsOrigin> getList(String name){
		return dao.getList(name);
	}

	@Transactional(readOnly = true)
	public CmsOrigin findById(Integer id) {
		CmsOrigin entity = dao.findById(id);
		return entity;
	}

	public CmsOrigin save(CmsOrigin bean) {
		dao.save(bean);
		return bean;
	}

	public CmsOrigin update(CmsOrigin bean) {
		Updater<CmsOrigin> updater = new Updater<CmsOrigin>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsOrigin deleteById(Integer id) {
		CmsOrigin bean = dao.deleteById(id);
		return bean;
	}

	public CmsOrigin[] deleteByIds(Integer[] ids) {
		CmsOrigin[] beans = new CmsOrigin[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	private OriginDao dao;

	@Autowired
	public void setDao(OriginDao dao) {
		this.dao = dao;
	}
}