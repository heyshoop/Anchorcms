package com.anchorcms.core.service;

import com.anchorcms.common.page.Pagination;
import com.anchorcms.core.model.CmsDictionary;

import java.util.List;


public interface DictionaryService {
	public Pagination getPage(String queryType, int pageNo, int pageSize);
	
	public List<CmsDictionary> getList(String queryType);
	
	public List<String> getTypeList();

	public CmsDictionary findById(Integer id);
	
	public CmsDictionary findByValue(String type, String value);

	public CmsDictionary save(CmsDictionary bean);

	public CmsDictionary update(CmsDictionary bean);

	public CmsDictionary deleteById(Integer id);
	
	public CmsDictionary[] deleteByIds(Integer[] ids);
	
	public boolean dicDeplicateValue(String value, String type);
}