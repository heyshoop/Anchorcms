package com.anchorcms.cms.controller.admin.assist;


import java.io.File;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.assist.CmsAccountDraw;
import com.anchorcms.cms.model.assist.CmsAccountPay;
import com.anchorcms.cms.model.assist.CmsConfigContentCharge;
import com.anchorcms.cms.service.assist.AccountDrawService;
import com.anchorcms.cms.service.assist.AccountPayService;
import com.anchorcms.cms.service.assist.ConfigContentChargeService;
import com.anchorcms.common.constants.Constants;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.security.encoder.Md5PwdEncoder;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.utils.Num62;
import com.anchorcms.common.utils.PropertyUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.common.web.mvc.RealPathResolver;
import com.anchorcms.common.web.session.SessionProvider;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;
import com.anchorcms.core.service.UserService;
import com.anchorcms.core.web.WebErrors;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/23 16:43
 * @Desc 用户账户提现支付controller
 */

@Controller
public class AccountPayController {
	private static final Logger log = LoggerFactory.getLogger(AccountPayController.class);
	public static final String PAY_LOGIN = "pay_login";
	public static final String WEIXIN_PAY_URL="weixin.transfer.url";
	
	@RequiresPermissions("accountPay:payLogin")
	@RequestMapping(value="/accountPay/payLogin.do", method = RequestMethod.GET)
	public String payLogin(HttpServletRequest request,
			ModelMap model) {
		return "accountPay/pay_login";
	}
	
	@RequiresPermissions("accountPay:payLogin")
	@RequestMapping(value="/accountPay/payLogin.do", method = RequestMethod.POST)
	public String payLoginSubmit(String password, String captcha,
			HttpServletRequest request,HttpServletResponse response,
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		try {
			if (!imageCaptchaService.validateResponseForID(session
					.getSessionId(request, response), captcha)) {
				errors.addErrorCode("error.invalidCaptcha");
			}
		} catch (CaptchaServiceException e) {
			errors.addErrorCode("error.exceptionCaptcha");
		}
		if(!errors.hasErrors()){
			CmsConfigContentCharge config=configContentChargeService.getDefault();
			if(pwdEncoder.encodePassword(password).equals(config.getPayTransferPassword())){
				session.setAttribute(request, response, PAY_LOGIN, true);
			}else{
				errors.addErrorCode("error.invalidPassword");
			}
		}
		if(errors.hasErrors()){
			model.addAttribute("errors",errors.getErrors());
			return "accountPay/pay_error";
		}else{
			return list(null, null, null, null, null, 1, request, model);
		}
	}
	
	@RequiresPermissions("accountPay:goToPay")
	@RequestMapping("/accountPay/goToPay.do")
	public String goToPay(Integer id, 
			Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Boolean is_login=(Boolean) session.getAttribute(request, PAY_LOGIN);
		if(is_login!=null&&is_login){
			CmsAccountDraw bean = accountDrawService.findById(id);
			model.addAttribute("draw", bean);
			return "accountPay/pay";
		}else{
			return payLogin(request, model);
		}
	}
	
	//每次转账均需要输入密码和验证码
	@RequiresPermissions("accountPay:payByWeiXin")
	@RequestMapping("/accountPay/payByWeiXin.do")
	public String payByWeiXin(Integer drawId, String password,String captcha,
			Integer pageNo, HttpServletRequest request,HttpServletResponse response,
			ModelMap model) {
		CmsUser user= CmsUtils.getUser(request);
		Boolean is_login=(Boolean) session.getAttribute(request, PAY_LOGIN);
		if(is_login!=null&&is_login){
			WebErrors errors = WebErrors.create(request);
			try {
				if (!imageCaptchaService.validateResponseForID(session
						.getSessionId(request, response), captcha)) {
					errors.addErrorCode("error.invalidCaptcha");
				}
			} catch (CaptchaServiceException e) {
				errors.addErrorCode("error.exceptionCaptcha");
			}
			if(!errors.hasErrors()){
				CmsConfigContentCharge config=configContentChargeService.getDefault();
				if(pwdEncoder.encodePassword(password).equals(config.getPayTransferPassword())){
					session.setAttribute(request, response, PAY_LOGIN, true);
				}else{
					errors.addErrorCode("error.invalidPassword");
				}
			}
			if(errors.hasErrors()){
				model.addAttribute("errors",errors.getErrors());
				return "accountPay/pay_error";
			}else{
				CmsAccountDraw bean = accountDrawService.findById(drawId);
				model.addAttribute("draw", bean);
				if(StringUtils.isBlank(getWeixinPayUrl())){
					setWeixinPayUrl(PropertyUtils.getPropertyValue(
							new File(realPathResolver.get(Constants.ANCHORCMS_CONFIG)),WEIXIN_PAY_URL));
				}
				String status=accountPayService.weixinTransferPay(getWeixinPayUrl(), drawId, bean.getDrawUser(), user, bean.getApplyAmount(), 
						System.currentTimeMillis()+RandomStringUtils.random(5, Num62.N10_CHARS), request, response, model);
				model.addAttribute("status", status);
				return "accountPay/pay_status";
			}
		}else{
			return payLogin(request, model);
		}
	}
	
	@RequiresPermissions("accountPay:v_list")
	@RequestMapping("/accountPay/v_list.do")
	public String list(String drawNum,String payUserName,String drawUserName,
			Date payTimeBegin,Date payTimeEnd,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		Boolean is_login=(Boolean) session.getAttribute(request, PAY_LOGIN);
		if(is_login!=null&&is_login){
			Integer payUserId=null;
			Integer drawUserId=null;
			if(StringUtils.isNotBlank(payUserName)){
				CmsUser payUser=userService.findByUsername(payUserName);
				if(payUser!=null){
					payUserId=payUser.getUserId();
				}else{
					payUserId=0;
				}
			}
			if(StringUtils.isNotBlank(drawUserName)){
				CmsUser drawUser=userService.findByUsername(drawUserName);
				if(drawUser!=null){
					drawUserId=drawUser.getUserId();
				}else{
					drawUserId=0;
				}
			}
			Pagination pagination = accountPayService.getPage(drawNum,payUserId
					,drawUserId,payTimeBegin,payTimeEnd,
					cpn(pageNo), CookieUtils.getPageSize(request));
			model.addAttribute("pagination",pagination);
			model.addAttribute("pageNo",pagination.getPageNo());
			model.addAttribute("drawNum",drawNum);
			model.addAttribute("payUserName",payUserName);
			model.addAttribute("drawUserName",drawUserName);
			model.addAttribute("payTimeBegin",payTimeBegin);
			model.addAttribute("payTimeEnd",payTimeEnd);
			return "accountPay/pay_list";
		}else{
			return payLogin(request, model);
		}
	}
	
	@RequiresPermissions("accountPay:o_delete")
	@RequestMapping("/accountPay/o_delete.do")
	public String delete(String drawNum,String payUserName,String drawUserName,
			Date payTimeBegin,Date payTimeEnd,
			Long[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Boolean is_login=(Boolean) session.getAttribute(request, PAY_LOGIN);
		if(is_login!=null&&is_login){
			CmsAccountPay[] beans = accountPayService.deleteByIds(ids);
			for (CmsAccountPay bean : beans) {
				log.info("delete CmsAccountPay id={}", bean.getAccountPayId());
			}
			return list(drawNum, payUserName,drawUserName,
					payTimeBegin, payTimeEnd, pageNo, request, model);
		}else{
			return payLogin(request, model);
		}
	}
	
	
	
	


	private WebErrors validateDelete(Long[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Long id : ids) {
			vldExist(id, site.getSiteId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Long id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsAccountPay entity = accountPayService.findById(id);
		if(errors.ifNotExist(entity, CmsAccountPay.class, id)) {
			return true;
		}
		return false;
	}

	
	private String weixinPayUrl;


	public String getWeixinPayUrl() {
		return weixinPayUrl;
	}

	public void setWeixinPayUrl(String weixinPayUrl) {
		this.weixinPayUrl = weixinPayUrl;
	}

	@Resource
	private AccountDrawService accountDrawService;
	@Resource
	private AccountPayService accountPayService;
	@Resource
	private SessionProvider session;
	@Resource
	private Md5PwdEncoder pwdEncoder;
	@Resource
	private RealPathResolver realPathResolver;
	@Resource
	private ConfigContentChargeService configContentChargeService;
	@Resource
	private UserService userService;

	private ImageCaptchaService imageCaptchaService;

	public ImageCaptchaService getImageCaptchaService() {
		return imageCaptchaService;
	}

	public void setImageCaptchaService(ImageCaptchaService imageCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
	}
}