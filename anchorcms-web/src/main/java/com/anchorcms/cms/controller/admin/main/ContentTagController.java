package com.anchorcms.cms.controller.admin.main;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.main.ContentTag;
import com.anchorcms.cms.service.main.ContentTagService;
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
 * @Date 2016/11/24 17:10
 * @Desc 内容标签
 */

@Controller
public class ContentTagController {
	private static final Logger log = LoggerFactory.getLogger(ContentTagController.class);

	@RequiresPermissions("tag:v_list")
	@RequestMapping("/tag/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		String queryName = RequestUtils.getQueryParam(request, "queryName");
		Pagination pagination = contentTagService.getPage(queryName, cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		if (!StringUtils.isBlank(queryName)) {
			model.addAttribute("queryName", queryName);
		}
		return "tag/list";
	}

	@RequiresPermissions("tag:v_add")
	@RequestMapping("/tag/v_add.do")
	public String add(ModelMap model) {
		return "tag/add";
	}

	@RequiresPermissions("tag:v_edit")
	@RequestMapping("/tag/v_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("contentTag", contentTagService.findById(id));
		String queryName = RequestUtils.getQueryParam(request, "queryName");
		if (!StringUtils.isBlank(queryName)) {
			model.addAttribute("queryName", queryName);
		}
		return "tag/edit";
	}
	
	@RequiresPermissions("tag:v_ajax_edit")
	@RequestMapping("/tag/v_ajax_edit.do")
	public void ajaxEdit(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject object = new JSONObject();
		ContentTag tag=contentTagService.findById(id);
		String queryName = RequestUtils.getQueryParam(request, "queryName");
		if (!StringUtils.isBlank(queryName)) {
			model.addAttribute("queryName", queryName);
		}
		if(tag!=null){
			object.put("id", tag.getTagId());
			object.put("name", tag.getTagName());
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequiresPermissions("tag:o_save")
	@RequestMapping("/tag/o_save.do")
	public String save(ContentTag bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = contentTagService.save(bean);
		log.info("save ContentTag id={}", bean.getTagId());
		logService.operating(request, "contentTag.log.save", "id="
				+ bean.getTagId() + ";name=" + bean.getTagName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("tag:o_update")
	@RequestMapping("/tag/o_update.do")
	public String update(ContentTag bean, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getTagId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = contentTagService.update(bean);
		log.info("update ContentTag id={}.", bean.getTagId());
		logService.operating(request, "contentTag.log.update", "id="
				+ bean.getTagId() + ";name=" + bean.getTagName());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("tag:o_delete")
	@RequestMapping("/tag/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ContentTag[] beans = contentTagService.deleteByIds(ids);
		for (ContentTag bean : beans) {
			log.info("delete ContentTag id={}", bean.getTagId());
			logService.operating(request, "contentTag.log.delete", "id="
					+ bean.getTagId() + ";name=" + bean.getTagName());
		}
		return list(pageNo, request, model);
	}
	
	@RequiresPermissions("tag:v_check_tag")
	@RequestMapping(value = "/tag/v_check_tag.do")
	public void checkTagname(Integer tagId,HttpServletRequest request, HttpServletResponse response) {
		String tag=RequestUtils.getQueryParam(request,"name");
		String pass;
		if (StringUtils.isBlank(tag)) {
			pass = "false";
		} else {
			ContentTag ctag=contentTagService.findByNameForTag(tag);
			if(ctag!=null){
				if(ctag.getTagId().equals(tagId)){
					pass = "true";
				}else{
					pass = "false";
				}
			}else{
				pass = "true";
			}
		}
		ResponseUtils.renderJson(response, pass);
	}

	private WebErrors validateSave(ContentTag bean, HttpServletRequest request) {
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
		ContentTag entity = contentTagService.findById(id);
		if (errors.ifNotExist(entity, ContentTag.class, id)) {
			return true;
		}
		return false;
	}

	@Resource
	private LogService logService;
	@Resource
	private ContentTagService contentTagService;
}