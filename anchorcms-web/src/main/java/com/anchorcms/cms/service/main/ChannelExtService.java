package com.anchorcms.cms.service.main;


import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.model.main.ChannelExt;

public interface ChannelExtService {
	public ChannelExt save(ChannelExt ext, Channel channel);

	public ChannelExt update(ChannelExt ext);
}