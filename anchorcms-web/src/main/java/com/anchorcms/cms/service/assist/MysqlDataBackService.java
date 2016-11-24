package com.anchorcms.cms.service.assist;

import com.anchorcms.cms.model.back.CmsField;

import java.sql.SQLException;
import java.util.List;


public interface MysqlDataBackService {
	public List<String> listTabels(String catalog);

	public List<CmsField> listFields(String tablename);

	public List<String> listDataBases();

	public String createTableDDL(String tablename);

	public List<Object[]> createTableData(String tablename);
	
	public String getDefaultCatalog()throws SQLException;
	
	public void setDefaultCatalog(String catalog) throws SQLException;

	public Boolean executeSQL(String sql);
}