package com.anchorcms.cms.controller.admin.assist;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsKeyword;
import com.anchorcms.cms.service.assist.KeywordService;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.web.WebErrors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/24 10:11
 * @Desc 关键词
 */

@Controller
public class KeywordController {
	private static final Logger log = LoggerFactory
			.getLogger(KeywordController.class);

	@RequiresPermissions("keyword:v_list")
	@RequestMapping("/keyword/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsKeyword> list = keywordService.getListBySiteId(site.getSiteId(), false,
				false);
		model.addAttribute("list", list);
		return "keyword/list";
	}

	@RequiresPermissions("keyword:o_save")
	@RequestMapping("/keyword/o_save.do")
	public String save(CmsKeyword bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = keywordService.save(bean);
		model.addAttribute("message", "global.success");
		log.info("save CmsKeyword id={}", bean.getKeywordId());
		logService.operating(request, "cmsKeyword.log.save", "id="
				+ bean.getKeywordId() + ";name=" + bean.getKeywordName());
		return list(request, model);
	}

	@RequiresPermissions("keyword:o_update")
	@RequestMapping("/keyword/o_update.do")
	public String update(Integer[] id, String[] name, String[] url,
			Boolean[] disabled, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(id, name, url, disabled, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		if (id != null && id.length > 0) {
			keywordService.updateKeywords(id, name, url, disabled);
		}
		log.info("update CmsKeyword");
		model.addAttribute("message", "global.success");
		logService.operating(request, "cmsKeyword.log.update", null);
		return list(request, model);
	}

	@RequiresPermissions("keyword:o_delete")
	@RequestMapping("/keyword/o_delete.do")
	public String delete(Integer[] ids, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsKeyword[] beans = keywordService.deleteByIds(ids);
		for (CmsKeyword bean : beans) {
			log.info("delete CmsKeyword id={}", bean.getKeywordId());
			logService.operating(request, "cmsKeyword.log.delete", "id="
					+ bean.getKeywordId() + ";name=" + bean.getKeywordName());
		}
		return list(request, model);
	}

	private WebErrors validateSave(CmsKeyword bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateUpdate(Integer[] ids, String[] names,
			String[] urls, Boolean[] disalbeds, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "id")) {
			return errors;
		}
		if (errors.ifEmpty(names, "name")) {
			return errors;
		}
		if (errors.ifEmpty(urls, "url")) {
			return errors;
		}
		if (ids.length != names.length || ids.length != urls.length) {
			errors.addErrorString("id, name, url length not equals");
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, site.getSiteId(), errors);
			return errors;
		}
		return errors;
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
		CmsKeyword entity = keywordService.findById(id);
		if (errors.ifNotExist(entity, CmsKeyword.class, id)) {
			return true;
		}
		// TODO 暂时不判断站点相关权限
		// if (!entity.getSite().getId().equals(siteId)) {
		// errors.notInSite(CmsKeyword.class, id);
		// return true;
		// }
		return false;
	}

	@Resource
	private LogService logService;
	@Resource
	private KeywordService keywordService;
}