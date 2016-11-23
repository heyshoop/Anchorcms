package com.anchorcms.cms.dao.assist;


import com.anchorcms.cms.model.assist.CmsAccountPay;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;

import java.util.Date;

public interface AccountPayDao {
	public Pagination getPage(String drawNum, Integer payUserId, Integer drawUserId,
							  Date payTimeBegin, Date payTimeEnd, int pageNo, int pageSize);

	public CmsAccountPay findById(Long id);

	public CmsAccountPay save(CmsAccountPay bean);

	public CmsAccountPay updateByUpdater(Updater<CmsAccountPay> updater);

	public CmsAccountPay deleteById(Long id);
}