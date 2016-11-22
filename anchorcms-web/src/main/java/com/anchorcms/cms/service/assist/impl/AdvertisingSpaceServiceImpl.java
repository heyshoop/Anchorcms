package com.anchorcms.cms.service.assist.impl;

import com.anchorcms.cms.dao.assist.AdvertisingSpaceDao;
import com.anchorcms.cms.model.assist.CmsAdvertisingSpace;
import com.anchorcms.cms.service.assist.AdvertisingSpaceService;
import com.anchorcms.common.hibernate.Updater;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
public class AdvertisingSpaceServiceImpl implements AdvertisingSpaceService {
	@Transactional(readOnly = true)
	public List<CmsAdvertisingSpace> getList(Integer siteId) {
		return dao.getList(siteId);
	}

	@Transactional(readOnly = true)
	public CmsAdvertisingSpace findById(Integer id) {
		CmsAdvertisingSpace entity = dao.findById(id);
		return entity;
	}

	public CmsAdvertisingSpace save(CmsAdvertisingSpace bean) {
		dao.save(bean);
		return bean;
	}

	public CmsAdvertisingSpace update(CmsAdvertisingSpace bean) {
		Updater<CmsAdvertisingSpace> updater = new Updater<CmsAdvertisingSpace>(
				bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public CmsAdvertisingSpace deleteById(Integer id) {
		CmsAdvertisingSpace bean = dao.deleteById(id);
		return bean;
	}

	public CmsAdvertisingSpace[] deleteByIds(Integer[] ids) {
		CmsAdvertisingSpace[] beans = new CmsAdvertisingSpace[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Resource
	private AdvertisingSpaceDao dao;
}