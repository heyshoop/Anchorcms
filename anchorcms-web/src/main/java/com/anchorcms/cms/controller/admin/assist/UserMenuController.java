package com.anchorcms.cms.controller.admin.assist;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.assist.CmsUserMenu;
import com.anchorcms.cms.service.assist.UserMenuService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;
import com.anchorcms.core.web.WebErrors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/24 13:49
 * @Desc 用户菜单controller
 */

@Controller
public class UserMenuController {
	private static final Logger log = LoggerFactory.getLogger(UserMenuController.class);
	
	@RequiresPermissions("menu:v_list")
	@RequestMapping("/menu/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		CmsUser user= CmsUtils.getUser(request);
		Pagination pagination = userMenuService.getPage(user.getUserId(),cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "menu/list";
	}

	@RequiresPermissions("menu:v_ajax_edit")
	@RequestMapping("/menu/v_ajax_edit.do")
	public void ajaxEdit(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject object = new JSONObject();
		CmsUserMenu menu=userMenuService.findById(id);
		if(menu!=null){
			object.put("id", menu.getMenuId());
			object.put("name", menu.getMenuName());
			object.put("priority", menu.getPriority());
			object.put("url", menu.getMenuUrl());
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequiresPermissions("menu:o_save")
	@RequestMapping("/menu/o_save.do")
	public String save(CmsUserMenu bean, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean.setUser(CmsUtils.getUser(request));
		bean = userMenuService.save(bean);
		log.info("save CmsUserMenu id={}", bean.getMenuId());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("menu:o_update")
	@RequestMapping("/menu/o_update.do")
	public String update(CmsUserMenu bean, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateUpdate(bean.getMenuId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = userMenuService.update(bean);
		log.info("update CmsUserMenu id={}.", bean.getMenuId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("menu:o_delete")
	@RequestMapping("/menu/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUserMenu[] beans = userMenuService.deleteByIds(ids);
		for (CmsUserMenu bean : beans) {
			log.info("delete CmsUserMenu id={}", bean.getMenuId());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(CmsUserMenu bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
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
		CmsUserMenu entity = userMenuService.findById(id);
		if(errors.ifNotExist(entity, CmsUserMenu.class, id)) {
			return true;
		}
		return false;
	}
	
	@Resource
	private UserMenuService userMenuService;
}