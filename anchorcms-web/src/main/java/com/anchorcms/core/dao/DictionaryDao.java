package com.anchorcms.core.dao;

import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.core.model.CmsDictionary;

import java.util.List;


public interface DictionaryDao {
	public Pagination getPage(String queryType, int pageNo, int pageSize);
	
	public List<CmsDictionary> getList(String type);
	
	public List<String> getTypeList();

	public CmsDictionary findById(Integer id);
	
	public CmsDictionary findByValue(String type, String value);

	public CmsDictionary save(CmsDictionary bean);

	public CmsDictionary updateByUpdater(Updater<CmsDictionary> updater);

	public CmsDictionary deleteById(Integer id);

	public int countByValue(String value, String type);
}