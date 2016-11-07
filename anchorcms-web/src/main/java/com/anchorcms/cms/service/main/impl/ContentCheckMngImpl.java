package com.anchorcms.cms.service.main.impl;

import com.anchorcms.cms.dao.main.ContentCheckDao;
import com.anchorcms.cms.service.main.ContentCheckMng;
import com.anchorcms.cms.model.main.Content;
import com.anchorcms.cms.model.main.ContentCheck;
import com.anchorcms.common.hibernate.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ContentCheckMngImpl implements ContentCheckMng {
	public ContentCheck save(ContentCheck check, Content content) {
		check.setContent(content);
		check.init();
		dao.save(check);
		content.setContentCheck(check);
		return check;
	}

	public ContentCheck update(ContentCheck bean) {
		Updater<ContentCheck> updater = new Updater<ContentCheck>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	private ContentCheckDao dao;

	@Autowired
	public void setDao(ContentCheckDao dao) {
		this.dao = dao;
	}
}