package com.anchorcms.cms.service.assist;


import com.anchorcms.cms.model.assist.CmsAccountDraw;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.core.model.CmsUser;

import java.util.Date;


public interface AccountDrawService {
	
	public CmsAccountDraw draw(CmsUser user, Double amount, String applyAccount);
	
	public Double getAppliedSum(Integer userId);
	
	public Pagination getPage(Integer userId, Short applyStatus,
							  Date applyTimeBegin, Date applyTimeEnd, int pageNo, int pageSize);

	public CmsAccountDraw findById(Integer id);

	public CmsAccountDraw save(CmsAccountDraw bean);

	public CmsAccountDraw update(CmsAccountDraw bean);

	public CmsAccountDraw deleteById(Integer id);
	
	public CmsAccountDraw[] deleteByIds(Integer[] ids);
}