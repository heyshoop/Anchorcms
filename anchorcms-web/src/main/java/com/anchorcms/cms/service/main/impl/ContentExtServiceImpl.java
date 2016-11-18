package com.anchorcms.cms.service.main.impl;

import com.anchorcms.cms.dao.main.ContentExtDao;
import com.anchorcms.cms.service.main.ContentExtService;
import com.anchorcms.cms.model.main.Content;
import com.anchorcms.cms.model.main.ContentExt;
import com.anchorcms.common.hibernate.Updater;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class ContentExtServiceImpl implements ContentExtService {
	public ContentExt save(ContentExt ext, Content content) {
		content.setContentExt(ext);
		ext.setContent(content);
		if (ext.getReleaseDate() == null) {
			ext.setReleaseDate(content.getSortDate());
		}
		ext.init();
		dao.save(ext);
		content.setContentExt(ext);
		return ext;
	}

	public ContentExt update(ContentExt bean) {
		Updater<ContentExt> updater = new Updater<ContentExt>(bean);
		bean = dao.updateByUpdater(updater);
		bean.blankToNull();
		// 修改后需要重新生成静态页
		bean.setNeedRegenerate(true);
		return bean;
	}
	@Resource
	private ContentExtDao dao;
}