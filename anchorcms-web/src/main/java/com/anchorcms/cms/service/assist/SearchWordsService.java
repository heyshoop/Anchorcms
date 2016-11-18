package com.anchorcms.cms.service.assist;

import java.util.List;

import com.anchorcms.cms.model.assist.CmsSearchWords;
import com.anchorcms.common.page.Pagination;
import net.sf.ehcache.Ehcache;


public interface SearchWordsService {
	public Pagination getPage(Integer siteId, String name, Integer recommend
			, Integer orderBy, int pageNo, int pageSize);

	public List<CmsSearchWords> getList(Integer siteId, String name,
										Integer recommend, Integer orderBy, Integer count, boolean cacheable);
	
	public CmsSearchWords findById(Integer id);

	public CmsSearchWords save(CmsSearchWords bean);

	public CmsSearchWords update(CmsSearchWords bean);

	public CmsSearchWords deleteById(Integer id);
	
	public CmsSearchWords[] deleteByIds(Integer[] ids);
	
	public int freshCacheToDB(Ehcache cache);

}