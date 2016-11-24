package com.anchorcms.cms.controller.admin.main;

import com.anchorcms.cms.model.assist.CmsWebservice;
import com.anchorcms.cms.service.main.WebserviceService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.common.web.RequestUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.*;
import com.anchorcms.core.service.ConfigItemService;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.service.UserService;
import com.anchorcms.core.service.GroupService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/24 16:34
 * @Desc 人员管理
 */

@Controller
public class MemberController {
	private static final Logger log = LoggerFactory
			.getLogger(MemberController.class);

	@RequiresPermissions("member:v_list")
	@RequestMapping("/member/v_list.do")
	public String list(String queryUsername, String queryEmail,
					   Integer queryGroupId, Boolean queryDisabled, Integer pageNo,
					   HttpServletRequest request, ModelMap model) {
		Pagination pagination = userService.getPage(queryUsername, queryEmail,
				null, queryGroupId, queryDisabled, false, null, 
				null,null,null,cpn(pageNo),
				CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);

		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryEmail", queryEmail);
		model.addAttribute("queryGroupId", queryGroupId);
		model.addAttribute("queryDisabled", queryDisabled);
		model.addAttribute("groupList", groupService.getList());
		return "member/list";
	}

	@RequiresPermissions("member:v_add")
	@RequestMapping("/member/v_add.do")
	public String add(ModelMap model, HttpServletRequest request) {
		CmsSite site= CmsUtils.getSite(request);
		List<CmsGroup> groupList = groupService.getList();
		List<CmsConfigItem> registerItems=configItemService.getList(site.getConfig().getConfigId(), CmsConfigItem.CATEGORY_REGISTER);
		model.addAttribute("registerItems", registerItems);
		model.addAttribute("groupList", groupList);
		return "member/add";
	}

	@RequiresPermissions("member:v_edit")
	@RequestMapping("/member/v_edit.do")
	public String edit(Integer id, Integer queryGroupId, Boolean queryDisabled,
					   HttpServletRequest request, ModelMap model) {
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		CmsSite site= CmsUtils.getSite(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser user=userService.findById(id);
		List<CmsGroup> groupList = groupService.getList();
		List<CmsConfigItem> registerItems=configItemService.getList(site.getConfig().getConfigId(), CmsConfigItem.CATEGORY_REGISTER);
		List<String> userAttrValues=new ArrayList<String>();
		for(CmsConfigItem item:registerItems){
			userAttrValues.add(user.getAttr().get(item.getField()));
		}
		model.addAttribute("queryUsername", queryUsername);
		model.addAttribute("queryEmail", queryEmail);
		model.addAttribute("queryGroupId", queryGroupId);
		model.addAttribute("queryDisabled", queryDisabled);
		model.addAttribute("groupList", groupList);
		model.addAttribute("cmsMember", user);
		model.addAttribute("registerItems", registerItems);
		model.addAttribute("userAttrValues", userAttrValues);
		return "member/edit";
	}

	@RequiresPermissions("member:o_save")
	@RequestMapping("/member/o_save.do")
	public String save(CmsUser bean, CmsUserExt ext, String username,
					   String email, String password, Integer groupId, Integer grain,
					   HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		String ip = RequestUtils.getIpAddr(request);
		Map<String,String> attrs= RequestUtils.getRequestMap(request, "attr_");
		bean = userService.registerMember(username, email, password, ip, groupId,grain,false,ext,attrs);
		webserviceService.callWebService("false",username, password, email, ext, CmsWebservice.SERVICE_TYPE_ADD_USER);
		log.info("save CmsMember id={}", bean.getUserId());
		logService.operating(request, "cmsMember.log.save", "id=" + bean.getUserId()
				+ ";username=" + bean.getUsername());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("member:o_update")
	@RequestMapping("/member/o_update.do")
	public String update(Integer id, String email, String password,
						 Boolean disabled, CmsUserExt ext, Integer groupId, Integer grain,
						 String queryUsername, String queryEmail, Integer queryGroupId,
						 Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
						 ModelMap model) {
		WebErrors errors = validateUpdate(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Map<String,String> attrs= RequestUtils.getRequestMap(request, "attr_");
		CmsUser bean = userService.updateMember(id, email, password, disabled, ext,groupId,grain,attrs);
		webserviceService.callWebService("false",bean.getUsername(), password, email, ext, CmsWebservice.SERVICE_TYPE_UPDATE_USER);
		log.info("update CmsMember id={}.", bean.getUserId());
		logService.operating(request, "cmsMember.log.update", "id="
				+ bean.getUserId() + ";username=" + bean.getUsername());
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}

	@RequiresPermissions("member:o_delete")
	@RequestMapping("/member/o_delete.do")
	public String delete(Integer[] ids, Integer queryGroupId,
						 Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
						 ModelMap model) {
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsUser[] beans = userService.deleteByIds(ids);
		for (CmsUser bean : beans) {
			Map<String,String> paramsValues=new HashMap<String, String>();
			paramsValues.put("username", bean.getUsername());
			paramsValues.put("admin", "false");
			webserviceService.callWebService(CmsWebservice.SERVICE_TYPE_DELETE_USER, paramsValues);
			log.info("delete CmsMember id={}", bean.getUserId());
			logService.operating(request, "cmsMember.log.delete", "id="
					+ bean.getUserId() + ";username=" + bean.getUsername());
		}
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}
	
	@RequiresPermissions("member:o_check")
	@RequestMapping("/member/o_check.do")
	public String check(Integer[] ids, Integer queryGroupId,
						Boolean queryDisabled, Integer pageNo, HttpServletRequest request,
						ModelMap model) {
		String queryUsername = RequestUtils.getQueryParam(request,
				"queryUsername");
		String queryEmail = RequestUtils.getQueryParam(request, "queryEmail");
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		for(Integer id:ids){
			CmsUser user=userService.findById(id);
			user.setIsDisabled(false);
			userService.updateUser(user);
			log.info("check CmsMember id={}", user.getUserId());
			logService.operating(request, "cmsMember.log.delete", "id="
					+ user.getUserId() + ";username=" + user.getUsername());
		}
		return list(queryUsername, queryEmail, queryGroupId, queryDisabled,
				pageNo, request, model);
	}

	@RequiresPermissions("member:v_check_username")
	@RequestMapping(value = "/member/v_check_username.do")
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
		String username= RequestUtils.getQueryParam(request,"username");
		String pass;
		if (StringUtils.isBlank(username)) {
			pass = "false";
		} else {
			pass = userService.usernameNotExist(username) ? "true" : "false";
		}
		ResponseUtils.renderJson(response, pass);
	}
	
	

	private WebErrors validateSave(CmsUser bean, HttpServletRequest request) {
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
		// TODO 验证是否为管理员，管理员不允许修改。
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if(!errors.ifEmpty(ids, "ids")){
			for (Integer id : ids) {
				vldExist(id, errors);
			}
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsUser entity = userService.findById(id);
		if (errors.ifNotExist(entity, CmsUser.class, id)) {
			return true;
		}
		return false;
	}

	@Resource
	private GroupService groupService;
	@Resource
	private LogService logService;
	@Resource
	private UserService userService;
	@Resource
	private ConfigItemService configItemService;
	@Resource
	private WebserviceService webserviceService;
}