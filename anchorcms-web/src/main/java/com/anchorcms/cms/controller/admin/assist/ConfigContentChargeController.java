package com.anchorcms.cms.controller.admin.assist;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsConfigContentCharge;
import com.anchorcms.cms.service.assist.ConfigContentChargeService;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.web.WebErrors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 阁楼麻雀 
 * @Date 2016/11/23 17:48
 * @Desc 内容收费配置controller
 */
 
@Controller
public class ConfigContentChargeController {
	private static final Logger log = LoggerFactory.getLogger(ConfigContentChargeController.class);

	@RequiresPermissions("config:v_content_charge")
	@RequestMapping("/config/v_content_charge.do")
	public String edit(HttpServletRequest request, ModelMap model) {
		model.addAttribute("cmsConfigContentCharge", configContentChargeService.getDefault());
		return "config/content_charge";
	}

	/**
	 * @param weixinPassword 微信支付商户密钥
	 * @param weixinSecret 微信公众号secret
	 * @param alipayKey 阿里支付密钥
	 * @param transferApiPassword 企业转账接口API密钥
	 * @param payTransferPassword 转账登陆密码
	 * @return
	 */
	@RequiresPermissions("config:o_content_charge_update")
	@RequestMapping("/config/o_content_charge_update.do")
	public String update(CmsConfigContentCharge bean, String weixinPassword,
						 String weixinSecret, String alipayKey, String alipayPublicKey,
						 String alipayPrivateKey,
						 String transferApiPassword, String payTransferPassword,
						 HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getConfigContentId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Map<String,String>attrs=new HashMap<String,String>();
		attrs.put("weixinPassword", weixinPassword);
		attrs.put("weixinSecret", weixinSecret);
		attrs.put("alipayKey", alipayKey);
		attrs.put("alipayPublicKey", alipayPublicKey);
		attrs.put("alipayPrivateKey", alipayPrivateKey);
		attrs.put("transferApiPassword", transferApiPassword);
		bean = configContentChargeService.update(bean,payTransferPassword,attrs);
		log.info("update CmsConfigContentCharge id={}.", bean.getConfigContentId());
		return edit(request, model);
	}
	
	private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (vldExist(id, site.getSiteId(), errors)) {
			return errors;
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsConfigContentCharge entity = configContentChargeService.findById(id);
		if(errors.ifNotExist(entity, CmsConfigContentCharge.class, id)) {
			return true;
		}
		return false;
	}
	
	@Resource
	private ConfigContentChargeService configContentChargeService;
}