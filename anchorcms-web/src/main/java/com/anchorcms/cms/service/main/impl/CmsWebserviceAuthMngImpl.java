package com.anchorcms.cms.service.main.impl;

import com.anchorcms.cms.dao.main.CmsWebserviceAuthDao;
import com.anchorcms.cms.model.assist.CmsWebserviceAuth;
import com.anchorcms.cms.service.main.CmsWebserviceAuthMng;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.security.encoder.PwdEncoder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class CmsWebserviceAuthMngImpl implements CmsWebserviceAuthMng {
	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public boolean isPasswordValid(String username, String password){
		CmsWebserviceAuth auth=findByUsername(username);
		if(auth!=null&&auth.getEnable()){
			return pwdEncoder.isPasswordValid(auth.getPassword(), password);
		}else{
			return false;
		}
	}

	@Transactional(readOnly = true)
	public CmsWebserviceAuth findByUsername(String username) {
		CmsWebserviceAuth entity = dao.findByUsername(username);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public CmsWebserviceAuth findById(Integer id) {
		CmsWebserviceAuth entity = dao.findById(id);
		return entity;
	}

	public CmsWebserviceAuth save(CmsWebserviceAuth bean) {
		dao.save(bean);
		return bean;
	}

	public CmsWebserviceAuth update(CmsWebserviceAuth bean) {
		Updater<CmsWebserviceAuth> updater = new Updater<CmsWebserviceAuth>(bean);
		CmsWebserviceAuth entity = dao.updateByUpdater(updater);
		return entity;
	}
	
	public CmsWebserviceAuth update(Integer id, String username, String password, String system, Boolean enable){
		CmsWebserviceAuth entity =findById(id);
		if(StringUtils.isNotBlank(username)){
			entity.setUsername(username);
		}
		if(StringUtils.isNotBlank(password)){
			entity.setPassword(pwdEncoder.encodePassword(password));
		}
		if(StringUtils.isNotBlank(system)){
			entity.setSystem(system);
		}
		if(enable!=null){
			entity.setEnable(enable);
		}
		return entity;
	}

	public CmsWebserviceAuth deleteById(Integer id) {
		CmsWebserviceAuth bean = dao.deleteById(id);
		return bean;
	}
	
	public CmsWebserviceAuth[] deleteByIds(Integer[] ids) {
		CmsWebserviceAuth[] beans = new CmsWebserviceAuth[ids.length];
		for (int i = 0,len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	@Resource
	private PwdEncoder pwdEncoder;
	private CmsWebserviceAuthDao dao;

	@Resource
	public void setDao(CmsWebserviceAuthDao dao) {
		this.dao = dao;
	}
}