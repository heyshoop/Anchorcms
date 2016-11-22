package com.anchorcms.cms.controller.admin.main;

import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.service.main.ChannelService;
import com.anchorcms.common.web.RequestUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.CmsGroup;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.service.GroupService;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.service.SiteService;
import com.anchorcms.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
public class GroupController {
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);

	@RequiresPermissions("group:v_list")
	@RequestMapping("/group/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<CmsGroup> list = groupService.getList();
		model.addAttribute("list", list);
		return "group/list";
	}

	@RequiresPermissions("group:v_add")
	@RequestMapping("/group/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		List<CmsSite> siteList = siteService.getList();
		model.addAttribute("siteList", siteList);
		return "group/add";
	}

	@RequiresPermissions("group:v_edit")
	@RequestMapping("/group/v_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsGroup group=groupService.findById(id);
		List<CmsSite> siteList = siteService.getList();
		Map<String, Set<Integer>> viewChannelsMap=new HashMap<String, Set<Integer>>();
		Map<String, Set<Integer>> contriChannelsMap=new HashMap<String, Set<Integer>>();
		for(CmsSite site:siteList){
			viewChannelsMap.put(site.getSiteId().toString(), group.getViewChannelIds(site.getSiteId()));
			contriChannelsMap.put(site.getSiteId().toString(), group.getContriChannelIds(site.getSiteId()));
		}
		model.addAttribute("siteList", siteList);
		model.addAttribute("cmsGroup", group);
		model.addAttribute("viewGroupIds", viewChannelsMap);
		model.addAttribute("contriGroupIds",contriChannelsMap);
		return "group/edit";
	}

	@RequiresPermissions("group:o_save")
	@RequestMapping("/group/o_save.do")
	public String save(CmsGroup bean, Integer[] viewChannelIds, Integer[] contriChannelIds,
					   HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = groupService.save(bean,viewChannelIds,contriChannelIds);
		log.info("save CmsGroup id={}", bean.getGroupId());
		logService.operating(request, "cmsGroup.log.save", "id=" + bean.getGroupId()
				+ ";name=" + bean.getGroupName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("group:o_update")
	@RequestMapping("/group/o_update.do")
	public String update(CmsGroup bean, Integer[] viewChannelIds, Integer[] contriChannelIds,
						 HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getGroupId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = groupService.update(bean,viewChannelIds,contriChannelIds);
		log.info("update CmsGroup id={}.", bean.getGroupId());
		logService.operating(request, "cmsGroup.log.update", "id="
				+ bean.getGroupId() + ";name=" + bean.getGroupName());
		return list(request, model);
	}

	@RequiresPermissions("group:o_delete")
	@RequestMapping("/group/o_delete.do")
	public String delete(Integer[] ids, HttpServletRequest request,
						 ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsGroup[] beans = groupService.deleteByIds(ids);
		for (CmsGroup bean : beans) {
			log.info("delete CmsGroup id={}", bean.getGroupId());
			logService.operating(request, "cmsGroup.log.delete", "id="
					+ bean.getGroupId() + ";name=" + bean.getGroupName());
		}
		return list(request, model);
	}

	@RequiresPermissions("group:o_priority")
	@RequestMapping("/group/o_priority.do")
	public String priority(Integer[] wids, Integer[] priority,
						   Integer regDefId, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		groupService.updatePriority(wids, priority);
		groupService.updateRegDef(regDefId);
		model.addAttribute("message", "global.success");
		return list(request, model);
	}
	
	@RequiresPermissions("group:v_site_list")
	@RequestMapping(value = "/group/v_site_list.do")
	public String siteViewChannelTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<CmsSite> siteList= siteService.getList();
		model.addAttribute("siteList", siteList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "group/viewsites";
	}
	
	@RequiresPermissions("group:v_site_contri_list")
	@RequestMapping(value = "/group/v_site_contri_list.do")
	public String siteContriChannelTree(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<CmsSite> siteList= siteService.getList();
		model.addAttribute("siteList", siteList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "group/contrisites";
	}
	
	@RequiresPermissions("group:v_channels_add")
	@RequestMapping(value = "/group/v_channels_add.do")
	public String channelsAdd(Integer siteId, HttpServletRequest request,
							  HttpServletResponse response, ModelMap model) {
		return channelsAddJson(siteId, request, response, model);
	}

	@RequiresPermissions("group:v_channels_edit")
	@RequestMapping(value = "/group/v_channels_edit.do")
	public String channelsEdit(Integer groupId, Integer siteId, Integer type,
							   HttpServletRequest request, HttpServletResponse response,
							   ModelMap model) {
		return channelsEditJson(groupId, siteId,type, request, response, model);
	}
	
	@RequiresPermissions("group:v_check_name")
	@RequestMapping(value = "/group/v_check_name.do")
	public void checkname(Integer gid, HttpServletRequest request, HttpServletResponse response) {
		String name= RequestUtils.getQueryParam(request,"name");
		String pass;
		if (StringUtils.isBlank(name)) {
			pass = "false";
		} else {
			CmsGroup group=groupService.findByName(name);
			if(group==null){
				pass = "true";
			}else{
				if(group.getGroupId().equals(gid)){
					pass = "true";
				}else{
					pass = "false";
				}
			}
		}
		ResponseUtils.renderJson(response, pass);
	}
	
	private String channelsAddJson(Integer siteId,
								   HttpServletRequest request, HttpServletResponse response,
								   ModelMap model) {
		List<Channel> channelList = channelService.getTopList(siteId, false);
		model.addAttribute("channelList", channelList);
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "group/channels_add";
	}

	private String channelsEditJson(Integer groupId, Integer siteId, Integer type,
									HttpServletRequest request, HttpServletResponse response,
									ModelMap model) {
		List<Channel> channelList = channelService.getTopList(siteId, false);
		CmsGroup group = groupService.findById(groupId);
		model.addAttribute("channelList", channelList);
		if(type.equals(1)){
			model.addAttribute("channelIds", group.getViewChannelIds(siteId));
		}else{
			model.addAttribute("channelIds", group.getContriChannelIds(siteId));
		}
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json;charset=UTF-8");
		return "group/channels_edit";
	}

	private WebErrors validateSave(CmsGroup bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (vldExist(id, errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private WebErrors validatePriority(Integer[] wids, Integer[] priority,
									   HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(wids, "wids")) {
			return errors;
		}
		if (errors.ifEmpty(priority, "priority")) {
			return errors;
		}
		if (wids.length != priority.length) {
			errors.addErrorString("wids length not equals priority length");
			return errors;
		}
		for (int i = 0, len = wids.length; i < len; i++) {
			if (vldExist(wids[i], errors)) {
				return errors;
			}
			if (priority[i] == null) {
				priority[i] = 0;
			}
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsGroup entity = groupService.findById(id);
		if (errors.ifNotExist(entity, CmsGroup.class, id)) {
			return true;
		}
		return false;
	}

	@Resource
	private LogService logService;
	@Resource
	private GroupService groupService;
	@Resource
	private ChannelService channelService;
	@Resource
	protected SiteService siteService;
}