package com.anchorcms.cms.controller.admin.assist;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.assist.CmsOrigin;
import com.anchorcms.cms.service.assist.OriginService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.common.web.RequestUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
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
 * @Date 2016/11/24 10:58
 * @Desc 文章来源controller
 */
 
@Controller
public class OriginController {
	private static final Logger log = LoggerFactory
			.getLogger(OriginController.class);
	
	@RequiresPermissions("origin:v_list")
	@RequestMapping("/origin/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		String queryName = RequestUtils.getQueryParam(request, "queryName");
		Pagination pagination = originService.getPage(cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		if (!StringUtils.isBlank(queryName)) {
			model.addAttribute("queryName", queryName);
		}
		return "origin/list";
	}

	@RequiresPermissions("content:v_origin_list")
	@RequestMapping("/origin/v_ajax_list.do")
	public void ajaxList(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject object = new JSONObject();
		Map<String,String>originMap=new HashMap<String, String>();
		String origin=RequestUtils.getQueryParam(request, "term");
		List<CmsOrigin>origins=originService.getList(origin);
		for(CmsOrigin ori:origins){
			originMap.put(ori.getOriginName(), ori.getOriginName());
		}
		object.put("origin", originMap);
		ResponseUtils.renderJson(response, object.get("origin").toString());
	}
	
	@RequiresPermissions("origin:v_ajax_edit")
	@RequestMapping("/origin/v_ajax_edit.do")
	public void ajaxEdit(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject object = new JSONObject();
		CmsOrigin tag=originService.findById(id);
		String queryName = RequestUtils.getQueryParam(request, "queryName");
		if (!StringUtils.isBlank(queryName)) {
			model.addAttribute("queryName", queryName);
		}
		if(tag!=null){
			object.put("id", tag.getOriginId());
			object.put("name", tag.getOriginName());
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequiresPermissions("origin:o_save")
	@RequestMapping("/origin/o_save.do")
	public String save(CmsOrigin bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean.setRefCount(0);
		bean = originService.save(bean);
		log.info("save CmsOrigin id={}", bean.getOriginId());
		logService.operating(request, "CmsOrigin.log.save", "id="
				+ bean.getOriginId() + ";name=" + bean.getOriginName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("origin:o_update")
	@RequestMapping("/origin/o_update.do")
	public String update(CmsOrigin bean, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getOriginId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = originService.update(bean);
		log.info("update CmsOrigin id={}.", bean.getOriginId());
		logService.operating(request, "CmsOrigin.log.update", "id="
				+ bean.getOriginId() + ";name=" + bean.getOriginName());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("origin:o_delete")
	@RequestMapping("/origin/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsOrigin[] beans = originService.deleteByIds(ids);
		for (CmsOrigin bean : beans) {
			log.info("delete CmsOrigin id={}", bean.getOriginId());
			logService.operating(request, "CmsOrigin.log.delete", "id="
					+ bean.getOriginId() + ";name=" + bean.getOriginName());
		}
		return list(pageNo, request, model);
	}

	private WebErrors validateSave(CmsOrigin bean, HttpServletRequest request) {
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
		errors.ifEmpty(ids, "ids");
		for (Integer id : ids) {
			vldExist(id, site.getSiteId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsOrigin entity = originService.findById(id);
		if (errors.ifNotExist(entity, CmsOrigin.class, id)) {
			return true;
		}
		return false;
	}
	
	@Resource
	private OriginService originService;
	@Resource
	private LogService logService;
}