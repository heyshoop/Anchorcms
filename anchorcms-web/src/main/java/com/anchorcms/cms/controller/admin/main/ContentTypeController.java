package com.anchorcms.cms.controller.admin.main;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.main.ContentType;
import com.anchorcms.cms.service.main.ContentTypeService;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.web.WebErrors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 阁楼麻雀 
 * @Date 2016/11/24 17:14
 * @Desc 内容类型
 */
 
@Controller
public class ContentTypeController {
	private static final Logger log = LoggerFactory.getLogger(ContentTypeController.class);
	
	@RequiresPermissions("type:v_list")
	@RequestMapping("/type/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		List<ContentType> list = contentTypeService.getList(true);
		model.addAttribute("list", list);
		return "type/list";
	}

	@RequiresPermissions("type:v_add")
	@RequestMapping("/type/v_add.do")
	public String add(ModelMap model) {
		return "type/add";
	}

	@RequiresPermissions("type:v_edit")
	@RequestMapping("/type/v_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("contentType", contentTypeService.findById(id));
		return "type/edit";
	}

	@RequiresPermissions("type:o_save")
	@RequestMapping("/type/o_save.do")
	public String save(ContentType bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = contentTypeService.save(bean);
		log.info("save ContentType id={}", bean.getTypeId());
		logService.operating(request, "contentType.log.save", "id="
				+ bean.getTypeId() + ";name=" + bean.getTypeName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("type:o_update")
	@RequestMapping("/type/o_update.do")
	public String update(ContentType bean, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getTypeId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = contentTypeService.update(bean);
		log.info("update ContentType id={}.", bean.getTypeId());
		logService.operating(request, "contentType.log.update", "id="
				+ bean.getTypeId() + ";name=" + bean.getTypeName());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("type:o_delete")
	@RequestMapping("/type/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		ContentType[] beans = contentTypeService.deleteByIds(ids);
		for (ContentType bean : beans) {
			log.info("delete ContentType id={}", bean.getTypeId());
			logService.operating(request, "contentType.log.delete", "id="
					+ bean.getTypeId() + ";name=" + bean.getTypeName());
		}
		return list(pageNo, request, model);
	}
	
	@RequiresPermissions("type:v_check_id")
	@RequestMapping(value = "/type/v_check_id.do")
	public void checkPath(Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		String pass;
		if (id==null) {
			pass = "false";
		} else {
			ContentType c = contentTypeService.findById(id);
			if(c==null){
				pass="true" ;
			}else{
				pass="false";
			}
		}
		ResponseUtils.renderJson(response, pass);
	}

	private WebErrors validateSave(ContentType bean, HttpServletRequest request) {
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
		ContentType entity = contentTypeService.findById(id);
		if (errors.ifNotExist(entity, ContentType.class, id)) {
			return true;
		}
		return false;
	}

	@Resource
	private LogService logService;
	@Resource
	private ContentTypeService contentTypeService;
}