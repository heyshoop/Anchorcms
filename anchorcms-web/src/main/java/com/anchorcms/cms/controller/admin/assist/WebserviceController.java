package com.anchorcms.cms.controller.admin.assist;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsWebservice;
import com.anchorcms.cms.service.main.WebserviceService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.web.CookieUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀 
 * @Date 2016/11/24 14:05
 * @Desc webservice
 */
 
@Controller
public class WebserviceController {
	private static final Logger log = LoggerFactory.getLogger(WebserviceController.class);

	@RequiresPermissions("webservice:v_list")
	@RequestMapping("/webservice/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = webserviceService.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "webservice/list";
	}

	@RequiresPermissions("webservice:v_add")
	@RequestMapping("/webservice/v_add.do")
	public String add(ModelMap model) {
		return "webservice/add";
	}

	@RequiresPermissions("webservice:v_edit")
	@RequestMapping("/webservice/v_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		model.addAttribute("cmsWebservice", webserviceService.findById(id));
		return "webservice/edit";
	}

	@RequiresPermissions("webservice:o_save")
	@RequestMapping("/webservice/o_save.do")
	public String save(CmsWebservice bean, String[] paramName, String[] defaultValue,
					   HttpServletRequest request, ModelMap model) {
		bean = webserviceService.save(bean,paramName,defaultValue);
		log.info("save CmsWebservice id={}", bean.getServiceId());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("webservice:o_update")
	@RequestMapping("/webservice/o_update.do")
	public String update(CmsWebservice bean,String[] paramName, String[] defaultValue,
			Integer pageNo, HttpServletRequest request,ModelMap model) {
		bean = webserviceService.update(bean,paramName,defaultValue);
		log.info("update CmsWebservice id={}.", bean.getServiceId());
		return list(pageNo, request, model);
	}

	@RequiresPermissions("webservice:o_delete")
	@RequestMapping("/webservice/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		webserviceService.deleteByIds(ids);
		return  list(pageNo, request, model);
	}
	
	@Resource
	private WebserviceService webserviceService;
}