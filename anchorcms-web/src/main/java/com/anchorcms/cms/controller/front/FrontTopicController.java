package com.anchorcms.cms.controller.front;

import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.model.main.CmsTopic;
import com.anchorcms.cms.service.main.ChannelService;
import com.anchorcms.cms.service.main.TopicService;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.utils.FrontUtils;
import com.anchorcms.core.model.CmsSite;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.anchorcms.common.constants.Constants.TPLDIR_TOPIC;


@Controller
public class FrontTopicController {

	public static final String TOPIC_INDEX = "tpl.topicIndex";
	public static final String TOPIC_CHANNEL = "tpl.topicChannel";
	public static final String TOPIC_DEFAULT = "tpl.topicDefault";

	@RequestMapping(value = "/topic*.jspx", method = RequestMethod.GET)
	public String index(Integer channelId, Integer topicId,
						HttpServletRequest request, HttpServletResponse response,
						ModelMap model) {
		// 全部？按站点？按栏目？要不同模板？
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		if (topicId != null) {
			CmsTopic topic = topicService.findById(topicId);
			if (topic == null) {
				return FrontUtils.pageNotFound(request, response, model);
			}
			model.addAttribute("topic", topic);
			String tpl = topic.getTplContent();
			if (StringUtils.isBlank(tpl)) {
				tpl = FrontUtils.getTplPath(request, site.getSolutionPath(),
						TPLDIR_TOPIC, TOPIC_DEFAULT);
			}
			return tpl;
		} else if (channelId != null) {
			Channel channel = channelService.findById(channelId);
			if (channel != null) {
				model.addAttribute("channel", channel);
			} else {
				return FrontUtils.pageNotFound(request, response, model);
			}
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TOPIC_CHANNEL);
		} else {
			return FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TOPIC_INDEX);
		}
	}

	@RequestMapping(value = "/topic/{id}.jspx", method = RequestMethod.GET)
	public String topic(@PathVariable String id, HttpServletRequest request,
						HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		if (id == null) {
			return FrontUtils.pageNotFound(request, response, model);
		}
		Integer topicId=null;
		try {
			topicId= Integer.parseInt(id);
		} catch (Exception e) {
			return FrontUtils.pageNotFound(request, response, model);
		}
		CmsTopic topic=null;
		if(topicId!=null){
			topic = topicService.findById(topicId);
		}
		if (topic == null) {
			return FrontUtils.pageNotFound(request, response, model);
		}
		model.addAttribute("topic", topic);
		String tpl = topic.getTplContent();
		if (StringUtils.isBlank(tpl)) {
			tpl = FrontUtils.getTplPath(request, site.getSolutionPath(),
					TPLDIR_TOPIC, TOPIC_DEFAULT);
		}
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return tpl;
	}

	@Resource
	private TopicService topicService;
	@Resource
	private ChannelService channelService;
}
