package com.anchorcms.cms.manager.main;


import com.anchorcms.cms.model.main.Content;
import com.anchorcms.cms.model.main.ContentTxt;

public interface ContentTxtMng {
	public ContentTxt save(ContentTxt txt, Content content);

	public ContentTxt update(ContentTxt txt, Content content);
}