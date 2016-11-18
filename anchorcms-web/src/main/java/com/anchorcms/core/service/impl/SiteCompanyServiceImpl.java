package com.anchorcms.core.service.impl;

import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.core.dao.SiteCompanyDao;
import com.anchorcms.core.service.SiteCompanyService;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsSiteCompany;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class SiteCompanyServiceImpl implements SiteCompanyService {
	public CmsSiteCompany save(CmsSite site, CmsSiteCompany bean) {
		site.setSiteCompany(bean);
		bean.setSite(site);
		dao.save(bean);
		return bean;
	}

	public CmsSiteCompany update(CmsSiteCompany bean) {
		Updater<CmsSiteCompany> updater = new Updater<CmsSiteCompany>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	@Resource
	private SiteCompanyDao dao;

}