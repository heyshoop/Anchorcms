package com.anchorcms.core.manager.impl;

import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.core.dao.CmsSiteCompanyDao;
import com.anchorcms.core.manager.CmsSiteCompanyMng;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsSiteCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CmsSiteCompanyMngImpl implements CmsSiteCompanyMng {
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

	private CmsSiteCompanyDao dao;

	@Autowired
	public void setDao(CmsSiteCompanyDao dao) {
		this.dao = dao;
	}
}