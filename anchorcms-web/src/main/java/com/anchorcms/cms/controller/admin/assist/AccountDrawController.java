package com.anchorcms.cms.controller.admin.assist;


import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsAccountDraw;
import com.anchorcms.cms.service.assist.AccountDrawService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;
import com.anchorcms.core.service.UserService;
import com.anchorcms.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/23 16:16
 * @Desc 账户提现controller
 */

@Controller
public class AccountDrawController {
	private static final Logger log = LoggerFactory.getLogger(AccountDrawController.class);

	@RequiresPermissions("accountPay:draw_apply_list")
	@RequestMapping("/accountPay/draw_apply_list.do")
	public String list(String queryUsername,Short status,
			Date timeBegin,Date timeEnd,Integer pageNo, 
			HttpServletRequest request, ModelMap model) {
		Integer userId=null;
		if(StringUtils.isNotBlank(queryUsername)){
			CmsUser user=userService.findByUsername(queryUsername);
			if(user!=null){
				userId=user.getUserId();
			}else{
				userId=0;
			}
		}
		Pagination pagination = accountDrawService.getPage(userId,status
				,timeBegin,timeEnd,cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination",pagination);
		model.addAttribute("pageNo",pagination.getPageNo());
		model.addAttribute("queryUsername",queryUsername);
		model.addAttribute("status",status);
		model.addAttribute("timeBegin",timeBegin);
		model.addAttribute("timeEnd",timeEnd);
		return "accountPay/draw_apply_list";
	}

	/**
	 * 申请受理审核
	 * @param ids
	 */
	@RequiresPermissions("accountPay:draw_apply_check")
	@RequestMapping("/accountPay/draw_apply_check.do")
	public String checkApply(String queryUsername,Short status,
			Date timeBegin,Date timeEnd,
			Integer[] ids, Boolean[] checks,
			Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		for(int i=0;i<ids.length;i++){
			CmsAccountDraw bean=accountDrawService.findById(ids[i]);
			if(checks[i]!=null){
				if(checks[i]){
					bean.setApplyStatus(CmsAccountDraw.CHECKED_SUCC);
				}else{
					bean.setApplyStatus(CmsAccountDraw.CHECKED_FAIL);
				}
			}
			accountDrawService.update(bean);
		}
		return list(queryUsername,status,timeBegin,timeEnd,pageNo, request, model);
	}
	
	@RequiresPermissions("accountPay:draw_apply_delete")
	@RequestMapping("/accountPay/draw_apply_delete.do")
	public String delete(String queryUsername,Short status,
			Date timeBegin,Date timeEnd,Integer[] ids, 
			Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsAccountDraw[] beans = accountDrawService.deleteByIds(ids);
		for (CmsAccountDraw bean : beans) {
			log.info("delete CmsAccountDraw id={}", bean.getAccountDrawId());
		}
		return list(queryUsername,status,timeBegin,timeEnd,pageNo, request, model);
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
		CmsAccountDraw entity = accountDrawService.findById(id);
		if(errors.ifNotExist(entity, CmsAccountDraw.class, id)) {
			return true;
		}
		return false;
	}
	
	@Resource
	private AccountDrawService accountDrawService;
	@Resource
	private UserService userService;
}