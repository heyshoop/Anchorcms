package com.anchorcms.cms.service.assist;

import com.anchorcms.cms.model.assist.CmsDirectiveTpl;
import com.anchorcms.common.page.Pagination;

import java.util.List;


public interface DirectiveTplService {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<CmsDirectiveTpl> getList(int count);

	public CmsDirectiveTpl findById(Integer id);

	public CmsDirectiveTpl save(CmsDirectiveTpl bean);

	public CmsDirectiveTpl update(CmsDirectiveTpl bean);

	public CmsDirectiveTpl deleteById(Integer id);
	
	public CmsDirectiveTpl[] deleteByIds(Integer[] ids);
}