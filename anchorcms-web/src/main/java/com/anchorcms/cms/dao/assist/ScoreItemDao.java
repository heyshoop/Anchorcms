package com.anchorcms.cms.dao.assist;


import com.anchorcms.cms.model.assist.CmsScoreItem;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;

public interface ScoreItemDao {
	public Pagination getPage(Integer groupId, int pageNo, int pageSize);

	public CmsScoreItem findById(Integer id);

	public CmsScoreItem save(CmsScoreItem bean);

	public CmsScoreItem updateByUpdater(Updater<CmsScoreItem> updater);

	public CmsScoreItem deleteById(Integer id);
}