package com.anchorcms.cms.controller.admin.main;

import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.service.main.ChannelService;
import com.anchorcms.cms.service.main.WebserviceService;
import com.anchorcms.common.web.RequestUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.CmsUser;
import com.anchorcms.core.security.CmsAuthorizingRealm;
import com.anchorcms.core.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminAbstrController {
	protected String channelsAddJson(Integer siteId,
									 HttpServletRequest request, HttpServletResponse response,
									 ModelMap model) {
		List<Channel> channelList = channelService.getTopList(siteId, false);
		model.addAttribute("channelList", channelList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "admin/channels_add";
	}

	protected String channelsEditJson(Integer userId, Integer siteId,
									  HttpServletRequest request, HttpServletResponse response,
									  ModelMap model) {
		List<Channel> channelList = channelService.getTopList(siteId, false);
		CmsUser user = manager.findById(userId);
		model.addAttribute("channelList", channelList);
		model.addAttribute("channelIds", user.getChannelIds(siteId));
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "admin/channels_edit";
	}
	

	protected void checkUserJson(HttpServletRequest request,HttpServletResponse response) {
		String username= RequestUtils.getQueryParam(request,"username");
		String pass;
		if (StringUtils.isBlank(username)) {
			pass = "false";
		} else {
			pass = manager.usernameNotExist(username) ? "true" : "false";
		}
		ResponseUtils.renderJson(response, pass);
	}

	protected void checkEmailJson(String email, HttpServletResponse response) {
		String pass;
		if (StringUtils.isBlank(email)) {
			pass = "false";
		} else {
			pass = manager.emailNotExist(email) ? "true" : "false";
		}
		ResponseUtils.renderJson(response, pass);
	}

	@Resource
	protected SiteService siteService;
	@Resource
	protected ChannelService channelService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected GroupService groupService;
	@Resource
	protected LogService logService;
	@Resource
	protected UserService manager;
	@Resource
	protected WebserviceService webserviceService;
	@Resource
	protected CmsAuthorizingRealm authorizingRealm;
}
