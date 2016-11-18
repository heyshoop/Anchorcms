package com.anchorcms.cms.service.assist.impl;

import com.anchorcms.cms.dao.assist.CommentExtDao;
import com.anchorcms.cms.model.assist.CmsComment;
import com.anchorcms.cms.model.assist.CmsCommentExt;
import com.anchorcms.cms.service.assist.CommentExtService;
import com.anchorcms.common.hibernate.Updater;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional
public class CommentExtServiceImpl implements CommentExtService {
	public CmsCommentExt save(String ip, String text, CmsComment comment) {
		CmsCommentExt ext = new CmsCommentExt();
		ext.setText(text);
		ext.setIp(ip);
		ext.setComment(comment);
		comment.setCommentExt(ext);
		dao.save(ext);
		return ext;
	}

	public CmsCommentExt update(CmsCommentExt bean) {
		Updater<CmsCommentExt> updater = new Updater<CmsCommentExt>(bean);
		bean = dao.updateByUpdater(updater);
		return bean;
	}

	public int deleteByContentId(Integer contentId) {
		return dao.deleteByContentId(contentId);
	}

	@Resource
	private CommentExtDao dao;

}