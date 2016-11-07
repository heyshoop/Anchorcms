package com.anchorcms.cms.service.assist;

import com.anchorcms.cms.model.assist.CmsPlug;
import com.anchorcms.common.page.Pagination;

import java.util.List;


public interface CmsPlugMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<CmsPlug> getList(String author, Boolean used);

	public CmsPlug findById(Integer id);
	
	public CmsPlug findByPath(String plugPath);

	public CmsPlug save(CmsPlug bean);

	public CmsPlug update(CmsPlug bean);

	public CmsPlug deleteById(Integer id);
	
	public CmsPlug[] deleteByIds(Integer[] ids);
}