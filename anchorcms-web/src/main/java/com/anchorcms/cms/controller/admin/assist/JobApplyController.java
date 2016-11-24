package com.anchorcms.cms.controller.admin.assist;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsJobApply;
import com.anchorcms.cms.model.assist.CmsUserResume;
import com.anchorcms.cms.model.main.Content;
import com.anchorcms.cms.service.assist.JobApplyService;
import com.anchorcms.cms.service.main.ContentService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;
import com.anchorcms.core.service.UserService;
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
 * @Date 2016/11/24 9:16
 * @Desc 工作申请controller
 */
 
@Controller
public class JobApplyController {
	private static final Logger log = LoggerFactory.getLogger(JobApplyController.class);

	@RequiresPermissions("jobapply:v_list")
	@RequestMapping("/jobapply/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = jobApplyService.getPage(null,null, CmsUtils.getSiteId(request),true,cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		return "jobapply/list";
	}
	
	@RequiresPermissions("jobapply:o_delete")
	@RequestMapping("/jobapply/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsJobApply[] beans = jobApplyService.deleteByIds(ids);
		for (CmsJobApply bean : beans) {
			log.info("delete CmsJobApply id={}", bean.getJobApplyId());
		}
		return list(pageNo, request, model);
	}
	
	
	@RequiresPermissions("jobapply:v_view")
	@RequestMapping("/jobapply/v_view.do")
	public String viewResume(Integer userId,Integer contentId, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateViewResume(userId,contentId,request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUserResume resume = userService.findById(userId).getUserResume();
		model.addAttribute("resume", resume);
		return "jobapply/viewresume";
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
	
	private WebErrors validateViewResume(Integer userId,Integer contentId,HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifNull(userId,"user")) {
			return errors;
		}
		if (errors.ifNull(contentId,"contentId")) {
			return errors;
		}
		CmsUser u=userService.findById(userId);
		if(u==null){
			errors.addErrorCode("cmsJobApply.nouser");
			return errors;
		}
		Content c=contentService.findById(contentId);
		if(c==null){
			errors.addErrorCode("cmsJobApply.nocontent");
			return errors;
		}
		if(!c.getSite().equals(site)){
			errors.addErrorCode("cmsJobApply.notinsite",u.getUsername());
			return errors;
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsJobApply entity = jobApplyService.findById(id);
		if(errors.ifNotExist(entity, CmsJobApply.class, id)) {
			return true;
		}
		return false;
	}
	
	@Resource
	private JobApplyService jobApplyService;
	@Resource
	private UserService userService;
	@Resource
	private ContentService contentService;
}