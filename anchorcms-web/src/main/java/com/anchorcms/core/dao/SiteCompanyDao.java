package com.anchorcms.core.dao;


import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.core.model.CmsSiteCompany;

public interface SiteCompanyDao {

	public CmsSiteCompany save(CmsSiteCompany bean);

	public CmsSiteCompany updateByUpdater(Updater<CmsSiteCompany> updater);
}