package com.anchorcms.core.service;


import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsSiteCompany;

public interface CmsSiteCompanyMng {
	public CmsSiteCompany save(CmsSite site, CmsSiteCompany bean);

	public CmsSiteCompany update(CmsSiteCompany bean);
}