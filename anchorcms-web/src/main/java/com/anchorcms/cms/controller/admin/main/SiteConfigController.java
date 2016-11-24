package com.anchorcms.cms.controller.admin.main;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.utils.CoreUtils;
import com.anchorcms.core.model.CmsDictionary;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsSiteCompany;
import com.anchorcms.core.model.Ftp;
import com.anchorcms.core.service.*;
import com.anchorcms.core.web.WebErrors;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.anchorcms.common.constants.Constants.TPLDIR_INDEX;
import static com.anchorcms.common.constants.Constants.TPL_BASE;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/24 16:45
 * @Desc 样式配置controller
 */

@Controller
public class SiteConfigController {
	private static final Logger log = LoggerFactory
			.getLogger(SiteConfigController.class);

	@RequiresPermissions("site_config:v_base_edit")
	@RequestMapping("/site_config/v_base_edit.do")
	public String baseEdit(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<Ftp> ftpList = ftpService.getList();
		List<String>indexTplList=getTplIndex(site,null);
		// 当前模板，去除基本路径
		int tplPathLength = site.getTplPath().length();
		String tplIndex = site.getTplIndex();
		if (!StringUtils.isBlank(tplIndex)) {
			tplIndex = tplIndex.substring(tplPathLength);
		}
		model.addAttribute("ftpList", ftpList);
		model.addAttribute("indexTplList",indexTplList);
		model.addAttribute("config", configService.get());
		model.addAttribute("cmsSite", site);
		model.addAttribute("tplIndex", tplIndex);
		return "site_config/base_edit";
	}

	@RequiresPermissions("site_config:o_base_update")
	@RequestMapping("/site_config/o_base_update.do")
	public String baseUpdate(CmsSite bean, Integer uploadFtpId,Integer syncPageFtpId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateBaseUpdate(bean, request);
		String tplPath = bean.getTplPath();
		if (!StringUtils.isBlank(bean.getTplIndex())) {
			bean.setTplIndex(tplPath + bean.getTplIndex());
		}
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsSite site = CmsUtils.getSite(request);
		bean.setSiteId(site.getSiteId());
		bean = siteService.update(bean, uploadFtpId,syncPageFtpId);
		model.addAttribute("message", "global.success");
		log.info("update CmsSite success. id={}", site.getSiteId());
		logService.operating(request, "cmsSiteConfig.log.updateBase", null);
		return baseEdit(request, model);
	}

	@RequiresPermissions("site_config:v_company_edit")
	@RequestMapping("/site_config/v_company_edit.do")
	public String companyEdit(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		CmsSiteCompany company = site.getSiteCompany();
		List<CmsDictionary> scales = dictionaryService.getList("scale");
		List<CmsDictionary> natures = dictionaryService.getList("nature");
		List<CmsDictionary> industrys = dictionaryService.getList("industry");
		model.addAttribute("site",CmsUtils.getSite(request));
		model.addAttribute("company", company);
		model.addAttribute("scales", scales);
		model.addAttribute("natures", natures);
		model.addAttribute("industrys", industrys);
		model.addAttribute("sessionId",request.getSession().getId());
		return "site_config/company_edit";
	}

	@RequiresPermissions("site_config:o_company_update")
	@RequestMapping("/site_config/o_company_update.do")
	public String companyUpdate(CmsSiteCompany company,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateCompanyUpdate(site, company, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		siteCompanyService.update(company);
		model.addAttribute("message", "global.success");
		log.info("update CmsSite success. id={}", site.getSiteId());
		logService.operating(request, "cmsSiteConfig.log.updateBase", null);
		return companyEdit(request, model);
	}
	
	private List<String> getTplIndex(CmsSite site,String tpl) {
		String path=site.getSitePath();
		List<String> tplList = tplService.getNameListByPrefix(site.getTplIndexPrefix(TPLDIR_INDEX));
		return CoreUtils.tplTrim(tplList,getTplPath(path), tpl);
	}

	private WebErrors validateCompanyUpdate(CmsSite site,
			CmsSiteCompany company, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (!company.getSiteId().equals(site.getSiteId())) {
			errors.addErrorCode("error.notInSite", CmsSiteCompany.class,
					company.getSiteId());
		}
		return errors;
	}

	private WebErrors validateBaseUpdate(CmsSite bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}
	
	private String getTplPath(String path){
		return TPL_BASE + "/" + path;
	}

	@Resource
	private FtpService ftpService;
	@Resource
	private LogService logService;
	@Resource
	private SiteService siteService;
	@Resource
	private SiteCompanyService siteCompanyService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private TplService tplService;
	@Resource
	private ConfigService configService;

}