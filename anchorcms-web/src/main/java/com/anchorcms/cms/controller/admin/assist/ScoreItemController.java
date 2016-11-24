package com.anchorcms.cms.controller.admin.assist;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsScoreItem;
import com.anchorcms.cms.service.assist.ScoreItemService;
import com.anchorcms.cms.service.assist.ScoreGroupService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.web.WebErrors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/24 11:24
 * @Desc 评分项controller
 */

@Controller
public class ScoreItemController {
	private static final Logger log = LoggerFactory.getLogger(ScoreItemController.class);

	@RequiresPermissions("scoreitem:v_list")
	@RequestMapping("/scoreitem/v_list.do")
	public String list(Integer groupId,Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = scoreItemService.getPage(groupId,cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("groupId",groupId);
		return "scoreitem/list";
	}

	@RequiresPermissions("scoreitem:v_add")
	@RequestMapping("/scoreitem/v_add.do")
	public String add(Integer groupId,ModelMap model) {
		model.addAttribute("groupId",groupId);
		return "scoreitem/add";
	}

	@RequiresPermissions("scoreitem:v_edit")
	@RequestMapping("/scoreitem/v_edit.do")
	public String edit(Integer id, Integer groupId,Integer pageNo, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("item", scoreItemService.findById(id));
		model.addAttribute("groupId",groupId);
		model.addAttribute("pageNo",pageNo);
		return "scoreitem/edit";
	}

	@RequiresPermissions("scoreitem:o_save")
	@RequestMapping("/scoreitem/o_save.do")
	public String save(CmsScoreItem bean, Integer groupId, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean.setGroup(scoreGroupService.findById(groupId));
		bean = scoreItemService.save(bean);
		log.info("save CmsScoreItem id={}", bean.getScoreItemId());
		return "redirect:v_list.do?groupId="+groupId;
	}

	@RequiresPermissions("scoreitem:o_update")
	@RequestMapping("/scoreitem/o_update.do")
	public String update(CmsScoreItem bean,Integer groupId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getScoreItemId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = scoreItemService.update(bean);
		log.info("update CmsScoreItem id={}.", bean.getScoreItemId());
		return list(groupId,pageNo, request, model);
	}

	@RequiresPermissions("scoreitem:o_delete")
	@RequestMapping("/scoreitem/o_delete.do")
	public String delete(Integer groupId,Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsScoreItem[] beans = scoreItemService.deleteByIds(ids);
		for (CmsScoreItem bean : beans) {
			log.info("delete CmsScoreItem id={}", bean.getScoreItemId());
		}
		return list(groupId,pageNo, request, model);
	}

	private WebErrors validateSave(CmsScoreItem bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}
	
	private WebErrors validateEdit(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getSiteId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getSiteId(), errors)) {
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, site.getSiteId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsScoreItem entity = scoreItemService.findById(id);
		if(errors.ifNotExist(entity, CmsScoreItem.class, id)) {
			return true;
		}
		return false;
	}
	
	@Resource
	private ScoreItemService scoreItemService;
	@Resource
	private ScoreGroupService scoreGroupService;
}