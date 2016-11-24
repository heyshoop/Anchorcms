package com.anchorcms.cms.controller.admin.assist;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsSensitivity;
import com.anchorcms.cms.service.assist.SensitivityService;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.web.WebErrors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SensitivityController {
	private static final Logger log = LoggerFactory
			.getLogger(SensitivityController.class);

	@RequiresPermissions("sensitivity:v_list")
	@RequestMapping("/sensitivity/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		List<CmsSensitivity> list = sensitivityService.getList(false);
		model.addAttribute("list", list);
		return "sensitivity/list";
	}

	@RequiresPermissions("sensitivity:o_save")
	@RequestMapping("/sensitivity/o_save.do")
	public String save(CmsSensitivity bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = sensitivityService.save(bean);
		model.addAttribute("message", "global.success");
		log.info("save CmsSensitivity id={}", bean.getSensitivityId());
		logService.operating(request, "cmsSensitivity.log.save", "id="
				+ bean.getSensitivityId() + ";name=" + bean.getSearch());
		return list(request, model);
	}

	@RequiresPermissions("sensitivity:o_update")
	@RequestMapping("/sensitivity/o_update.do")
	public String update(Integer[] id, String[] search, String[] replacement,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(id, search, replacement, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		sensitivityService.updateEnsitivity(id, search, replacement);
		model.addAttribute("message", "global.success");
		log.info("update CmsSensitivity.");
		logService.operating(request, "cmsSensitivity.log.save", null);
		return list(request, model);
	}

	@RequiresPermissions("sensitivity:o_delete")
	@RequestMapping("/sensitivity/o_delete.do")
	public String delete(Integer[] ids, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsSensitivity[] beans = sensitivityService.deleteByIds(ids);
		for (CmsSensitivity bean : beans) {
			log.info("delete CmsSensitivity id={}", bean.getSensitivityId());
			logService.operating(request, "cmsSensitivity.log.delete", "id="
					+ bean.getSensitivityId() + ";name=" + bean.getSearch());
		}
		model.addAttribute("message", "global.success");
		return list(request, model);
	}

	private WebErrors validateSave(CmsSensitivity bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		return errors;
	}

	private WebErrors validateUpdate(Integer[] ids, String[] searchs,
			String[] replacements, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(ids, "id")) {
			return errors;
		}
		if (errors.ifEmpty(searchs, "name")) {
			return errors;
		}
		if (errors.ifEmpty(replacements, "url")) {
			return errors;
		}
		if (ids.length != searchs.length || ids.length != replacements.length) {
			errors.addErrorString("id, searchs, replacements length"
					+ " not equals");
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, errors);
			return errors;
		}
		return errors;
	}

	private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldExist(id, errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsSensitivity entity = sensitivityService.findById(id);
		if (errors.ifNotExist(entity, CmsSensitivity.class, id)) {
			return true;
		}
		return false;
	}

	@Resource
	private LogService logService;
	@Resource
	private SensitivityService sensitivityService;
}