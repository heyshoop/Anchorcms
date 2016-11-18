package com.anchorcms.cms.service.main.impl;

import java.util.List;
import java.util.Set;

import com.anchorcms.cms.dao.main.MainTopicDao;
import com.anchorcms.cms.service.main.ChannelService;
import com.anchorcms.cms.service.main.TopicService;
import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.model.main.CmsTopic;
import com.anchorcms.cms.service.main.ChannelDeleteCheckerService;
import com.anchorcms.common.hibernate.Updater;
import com.anchorcms.common.page.Pagination;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("TopicService")
@Transactional
public class TopicServiceImpl implements TopicService, ChannelDeleteCheckerService {
	@Transactional(readOnly = true)
	public List<CmsTopic> getListForTag(Integer channelId, boolean recommend,
										Integer count) {
		return dao.getList(channelId, recommend, count, true);
	}

	@Transactional(readOnly = true)
	public Pagination getPageForTag(Integer channelId, boolean recommend,
									int pageNo, int pageSize) {
		return dao.getPage(channelId, recommend, pageNo, pageSize, true);
	}

	@Transactional(readOnly = true)
	public Pagination getPage(int pageNo, int pageSize) {
		Pagination page = dao.getPage(null, false, pageNo, pageSize, false);
		return page;
	}

	@Transactional(readOnly = true)
	public List<CmsTopic> getListByChannel(Integer channelId) {
		List<CmsTopic> list = dao.getGlobalTopicList();
		Channel c = channelService.findById(channelId);
		list.addAll(dao.getListByChannelIds(c.getNodeIds()));
		return list;
	}

	@Transactional(readOnly = true)
	public CmsTopic findById(Integer id) {
		CmsTopic entity = dao.findById(id);
		return entity;
	}

	public CmsTopic save(CmsTopic bean, Integer channelId,Integer[]channelIds) {
		if (channelId != null) {
			bean.setChannel(channelService.findById(channelId));
		}
		bean.init();
		bean=dao.save(bean);
		if (channelIds != null && channelIds.length > 0) {
			for (Integer cid : channelIds) {
				bean.addToChannels(channelService.findById(cid));
			}
		}
		return bean;
	}

	public CmsTopic update(CmsTopic bean, Integer channelId,Integer[]channelIds) {
		Updater<CmsTopic> updater = new Updater<CmsTopic>(bean);
		CmsTopic entity = dao.updateByUpdater(updater);
		if (channelId != null) {
			entity.setChannel(channelService.findById(channelId));
		} else {
			entity.setChannel(null);
		}
		entity.blankToNull();
		Set<Channel> channels = entity.getChannels();
		channels.clear();
		if (channelIds != null && channelIds.length > 0) {
			for (Integer cid : channelIds) {
				channels.add(channelService.findById(cid));
			}
		}
		return entity;
	}

	public CmsTopic deleteById(Integer id) {
		dao.deleteContentRef(id);
		CmsTopic bean = dao.deleteById(id);
		return bean;
	}

	public CmsTopic[] deleteByIds(Integer[] ids) {
		CmsTopic[] beans = new CmsTopic[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	public CmsTopic[] updatePriority(Integer[] ids, Integer[] priority) {
		int len = ids.length;
		CmsTopic[] beans = new CmsTopic[len];
		for (int i = 0; i < len; i++) {
			beans[i] = findById(ids[i]);
			beans[i].setPriority(priority[i]);
		}
		return beans;
	}

	public String checkForChannelDelete(Integer channelId) {
		if (dao.countByChannelId(channelId) > 0) {
			return "cmsTopic.error.cannotDeleteChannel";
		} else {
			return null;
		}
	}
	@Resource
	private ChannelService channelService;
	@Resource
	private MainTopicDao dao;
}