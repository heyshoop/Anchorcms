package com.anchorcms.cms.manager.main.impl;

import com.anchorcms.cms.dao.main.ChannelExtDao;
import com.anchorcms.cms.manager.main.ChannelExtMng;
import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.model.main.ChannelExt;
import com.anchorcms.common.hibernate.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ChannelExtMngImpl implements ChannelExtMng {
	public ChannelExt save(ChannelExt ext, Channel channel) {
		channel.setChannelExt(ext);
		ext.setChannel(channel);
		ext.init();
		dao.save(ext);
		return ext;
	}

	public ChannelExt update(ChannelExt ext) {
		Updater<ChannelExt> updater = new Updater<ChannelExt>(ext);
		updater.include(ChannelExt.PROP_FINAL_STEP);
		updater.include(ChannelExt.PROP_AFTER_CHECK);
		ChannelExt entity = dao.updateByUpdater(updater);
		entity.blankToNull();
		return entity;
	}

	private ChannelExtDao dao;

	@Autowired
	public void setDao(ChannelExtDao dao) {
		this.dao = dao;
	}
}