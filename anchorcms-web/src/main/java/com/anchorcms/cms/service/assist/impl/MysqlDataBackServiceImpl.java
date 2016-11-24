package com.anchorcms.cms.service.assist.impl;

import java.sql.SQLException;
import java.util.List;

import com.anchorcms.cms.dao.assist.MysqlDataBackDao;
import com.anchorcms.cms.model.back.CmsField;
import com.anchorcms.cms.service.assist.MysqlDataBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class MysqlDataBackServiceImpl implements MysqlDataBackService {

	@Transactional(readOnly = true)
	public String createTableDDL(String tablename) {
		return dao.createTableDDL(tablename);
	}

	@Transactional(readOnly = true)
	public List<Object[]> createTableData(String tablename) {
		return dao.createTableData(tablename);
	}

	@Transactional(readOnly = true)
	public List<CmsField> listFields(String tablename) {
		return dao.listFields(tablename);
	}

	@Transactional(readOnly = true)
	public List<String> listTabels(String catalog) {
		return dao.listTables(catalog);
	}

	@Transactional(readOnly = true)
	public List<String> listDataBases() {
		return dao.listDataBases();
	}

	@Transactional(readOnly = true)
	public String getDefaultCatalog() throws SQLException {
		return dao.getDefaultCatalog();
	}
	
	public void setDefaultCatalog(String catalog) throws SQLException{
		 dao.setDefaultCatalog(catalog);
	}

	public Boolean executeSQL(String sql) {
		return dao.executeSQL(sql);
	}

	private MysqlDataBackDao dao;

	@Autowired
	public void setDao(MysqlDataBackDao dao) {
		this.dao = dao;
	}

}