package com.anchorcms.cms.service.assist.impl;

import com.anchorcms.cms.dao.assist.DirectiveTplDao;
import com.anchorcms.cms.model.assist.CmsDirectiveTpl;
import com.anchorcms.cms.service.assist.DirectiveTplService;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class DirectiveTplServiceImpl implements DirectiveTplService {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<CmsDirectiveTpl> getList(int count){
		return dao.getList(count);
	}

	@Transactional(readOnly = true)
	public CmsDirectiveTpl findById(Integer id) {
		CmsDirectiveTpl entity = dao.findById(id);
		return entity;
	}

	public CmsDirectiveTpl save(CmsDirectiveTpl bean) {
		dao.save(bean);
		return bean;
	}

	public CmsDirectiveTpl update(CmsDirectiveTpl bean) {
		Updater<CmsDirectiveTpl> updater = new Updater<CmsDirectiveTpl>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsDirectiveTpl deleteById(Integer id) {
		CmsDirectiveTpl bean = dao.deleteById(id);
		return bean;
	}
	
	public CmsDirectiveTpl[] deleteByIds(Integer[] ids) {
		CmsDirectiveTpl[] beans = new CmsDirectiveTpl[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	@Resource
	private DirectiveTplDao dao;

	public void setDao(DirectiveTplDao dao) {
		this.dao = dao;
	}
}