package com.anchorcms.cms.controller.admin.assist;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.model.assist.CmsFriendlink;
import com.anchorcms.cms.model.assist.CmsFriendlinkCtg;
import com.anchorcms.cms.service.assist.FileService;
import com.anchorcms.cms.service.assist.FriendlinkCtgService;
import com.anchorcms.cms.service.assist.FriendlinkService;
import com.anchorcms.common.utils.CmsUtils;
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
 * @Date 2016/11/23 18:17
 * @Desc 友链配置
 */

@Controller
public class FriendlinkController {
	private static final Logger log = LoggerFactory
			.getLogger(FriendlinkController.class);

	@RequiresPermissions("friendlink:v_list")
	@RequestMapping("/friendlink/v_list.do")
	public String list(Integer queryCtgId, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsFriendlink> list = friendlinkService.getList(site.getSiteId(), queryCtgId,
				null);
		List<CmsFriendlinkCtg> ctgList = friendlinkCtgService.getList(site
				.getSiteId());
		model.addAttribute("list", list);
		model.addAttribute("ctgList", ctgList);
		if (queryCtgId != null) {
			model.addAttribute("queryCtgId", queryCtgId);
		}
		return "friendlink/list";
	}

	@RequiresPermissions("friendlink:v_add")
	@RequestMapping("/friendlink/v_add.do")
	public String add(ModelMap model, HttpServletRequest request) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateAdd(request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		List<CmsFriendlinkCtg> ctgList = friendlinkCtgService.getList(site
				.getSiteId());
		model.addAttribute("ctgList", ctgList);
		return "friendlink/add";
	}

	@RequiresPermissions("friendlink:v_edit")
	@RequestMapping("/friendlink/v_edit.do")
	public String edit(Integer id, Integer queryCtgId,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		model.addAttribute("cmsFriendlink", friendlinkService.findById(id));
		List<CmsFriendlinkCtg> ctgList = friendlinkCtgService.getList(site
				.getSiteId());
		model.addAttribute("ctgList", ctgList);
		if (queryCtgId != null) {
			model.addAttribute("queryCtgId", queryCtgId);
		}
		return "friendlink/edit";
	}

	@RequiresPermissions("friendlink:o_save")
	@RequestMapping("/friendlink/o_save.do")
	public String save(CmsFriendlink bean, Integer ctgId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = friendlinkService.save(bean, ctgId);
		fileService.updateFileByPath(bean.getLogo(), true, null);
		log.info("save CmsFriendlink id={}", bean.getFriendlinkId());
		logService.operating(request, "cmsFriendlink.log.save", "id="
				+ bean.getFriendlinkId() + ";name=" + bean.getSiteName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("friendlink:o_update")
	@RequestMapping("/friendlink/o_update.do")
	public String update(CmsFriendlink bean, Integer ctgId, Integer queryCtgId,
			String oldLog,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getFriendlinkId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = friendlinkService.update(bean, ctgId);
		fileService.updateFileByPath(oldLog, false, null);
		fileService.updateFileByPath(bean.getLogo(), true, null);
		log.info("update CmsFriendlink id={}.", bean.getFriendlinkId());
		logService.operating(request, "cmsFriendlink.log.update", "id="
				+ bean.getFriendlinkId() + ";name=" + bean.getSiteName());
		return list(queryCtgId, request, model);
	}

	@RequiresPermissions("friendlink:o_priority")
	@RequestMapping("/friendlink/o_priority.do")
	public String priority(Integer[] wids, Integer[] priority,
			Integer queryCtgId, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validatePriority(wids, priority, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		friendlinkService.updatePriority(wids, priority);
		log.info("update CmsFriendlink priority.");
		return list(queryCtgId, request, model);
	}

	@RequiresPermissions("friendlink:o_delete")
	@RequestMapping("/friendlink/o_delete.do")
	public String delete(Integer[] ids, Integer queryCtgId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsFriendlink[] beans = friendlinkService.deleteByIds(ids);
		for (CmsFriendlink bean : beans) {
			fileService.updateFileByPath(bean.getLogo(), false, null);
			log.info("delete CmsFriendlink id={}", bean.getFriendlinkId());
			logService.operating(request, "cmsFriendlink.log.delete", "id="
					+ bean.getFriendlinkId() + ";name=" + bean.getSiteName());
		}
		return list(queryCtgId, request, model);
	}

	private WebErrors validateAdd(HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (friendlinkCtgService.countBySiteId(site.getSiteId()) <= 0) {
			errors.addErrorCode("cmsFriendlink.error.addFriendlinkCtgFirst");
			return errors;
		}
		return errors;
	}

	private WebErrors validateSave(CmsFriendlink bean,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		bean.setSite(site);
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

	private WebErrors validatePriority(Integer[] ids, Integer[] priorities,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
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
		CmsFriendlink entity = friendlinkService.findById(id);
		if (errors.ifNotExist(entity, CmsFriendlink.class, id)) {
			return true;
		}
		if (!entity.getSite().getSiteId().equals(siteId)) {
			errors.notInSite(CmsFriendlink.class, id);
			return true;
		}
		return false;
	}

	@Autowired
	private FriendlinkCtgService friendlinkCtgService;
	@Autowired
	private LogService logService;
	@Autowired
	private FriendlinkService friendlinkService;
	@Autowired
	private FileService fileService;
}