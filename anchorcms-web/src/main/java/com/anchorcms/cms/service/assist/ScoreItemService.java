package com.anchorcms.cms.service.assist;


import com.anchorcms.cms.model.assist.CmsScoreItem;
import com.anchorcms.common.page.Pagination;

public interface ScoreItemService {
	public Pagination getPage(Integer groupId, int pageNo, int pageSize);

	public CmsScoreItem findById(Integer id);

	public CmsScoreItem save(CmsScoreItem bean);

	public CmsScoreItem update(CmsScoreItem bean);

	public CmsScoreItem deleteById(Integer id);
	
	public CmsScoreItem[] deleteByIds(Integer[] ids);
}