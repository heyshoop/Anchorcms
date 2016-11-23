package com.anchorcms.cms.controller.admin.assist;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.assist.CmsAcquisition;
import com.anchorcms.cms.model.assist.CmsAcquisitionHistory;
import com.anchorcms.cms.model.assist.CmsAcquisitionTemp;
import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.model.main.ContentType;
import com.anchorcms.cms.service.assist.AcquisitionHistoryService;
import com.anchorcms.cms.service.assist.AcquisitionService;
import com.anchorcms.cms.service.assist.AcquisitionSvc;
import com.anchorcms.cms.service.assist.AcquisitionTempService;
import com.anchorcms.cms.service.main.ChannelService;
import com.anchorcms.cms.service.main.ContentTypeService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.web.WebErrors;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/23 13:21
 * @Desc 采集controller
 */
@Controller
public class AcquisitionController {
	private static final Logger log = LoggerFactory
			.getLogger(AcquisitionController.class);
	
	@RequiresPermissions("acquisition:v_list")
	@RequestMapping("/acquisition/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<CmsAcquisition> list = acquisitionService.getList(site.getSiteId());
		model.addAttribute("list", list);
		return "acquisition/list";
	}

	@RequiresPermissions("acquisition:v_add")
	@RequestMapping("/acquisition/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		// 内容类型
		List<ContentType> typeList = contentTypeService.getList(false);
		// 栏目列表
		List<Channel> topList = channelService.getTopList(site.getSiteId(), true);
		List<Channel> channelList = Channel.getListForSelect(topList, null,
				true);
		model.addAttribute("channelList", channelList);
		model.addAttribute("typeList", typeList);
		return "acquisition/add";
	}

	@RequiresPermissions("acquisition:v_edit")
	@RequestMapping("/acquisition/v_edit.do")
	public String edit(Integer id, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateEdit(id, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsSite site = CmsUtils.getSite(request);
		// 内容类型
		List<ContentType> typeList = contentTypeService.getList(false);
		// 栏目列表
		List<Channel> topList = channelService.getTopList(site.getSiteId(), true);
		List<Channel> channelList = Channel.getListForSelect(topList, null,
				true);
		model.addAttribute("channelList", channelList);
		model.addAttribute("typeList", typeList);
		model.addAttribute("cmsAcquisition", acquisitionService.findById(id));
		return "acquisition/edit";
	}

	@RequiresPermissions("acquisition:o_save")
	@RequestMapping("/acquisition/o_save.do")
	public String save(CmsAcquisition bean, Integer channelId, Integer typeId,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Integer siteId = CmsUtils.getSiteId(request);
		Integer userId = CmsUtils.getUserId(request);
		bean = acquisitionService.save(bean, channelId, typeId, userId, siteId);
		log.info("save CmsAcquisition id={}", bean.getAcquisitionId());
		logService.operating(request, "cmsAcquisition.log.save", "id="
				+ bean.getAcquisitionId() + ";name=" + bean.getAcqName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("acquisition:o_update")
	@RequestMapping("/acquisition/o_update.do")
	public String update(CmsAcquisition bean, Integer channelId,
			Integer typeId, HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getAcquisitionId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean = acquisitionService.update(bean, channelId, typeId);
		log.info("update CmsAcquisition id={}.", bean.getAcquisitionId());
		logService.operating(request, "cmsAcquisition.log.update", "id="
				+ bean.getAcquisitionId() + ";name=" + bean.getAcqName());
		return list(request, model);
	}

	@RequiresPermissions("acquisition:o_delete")
	@RequestMapping("/acquisition/o_delete.do")
	public String delete(Integer[] ids, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsAcquisition[] beans = acquisitionService.deleteByIds(ids);
		for (CmsAcquisition bean : beans) {
			log.info("delete CmsAcquisition id={}", bean.getAcquisitionId());
			logService.operating(request, "cmsAcquisition.log.delete", "id="
					+ bean.getAcquisitionId() + ";name=" + bean.getAcqName());
		}
		return list(request, model);
	}

	@RequiresPermissions("acquisition:o_start")
	@RequestMapping("/acquisition/o_start.do")
	public String start(Integer[] ids, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Integer siteId = CmsUtils.getSiteId(request);
		WebErrors errors = validateStart(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		Integer queueNum = acquisitionService.hasStarted(siteId);
		if(queueNum==0){
			acquisitionSvc.start(ids[0]);
		}
		acquisitionService.addToQueue(ids, queueNum);
		log.info("start CmsAcquisition ids={}", Arrays.toString(ids));
		return "acquisition/progress";
	}

	@RequiresPermissions("acquisition:o_end")
	@RequestMapping("/acquisition/o_end.do")
	public String end(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		Integer siteId = CmsUtils.getSiteId(request);
		if (vldExist(id, siteId, errors)) {
			return errors.showErrorPage(model);
		}
		acquisitionService.end(id);
		CmsAcquisition acqu = acquisitionService.popAcquFromQueue(siteId);
		if (acqu != null) {
			Integer acquId = acqu.getAcquisitionId();
			acquisitionSvc.start(acquId);
		}
		log.info("end CmsAcquisition id={}", id);
		return "redirect:v_list.do";
	}

	@RequiresPermissions("acquisition:o_pause")
	@RequestMapping("/acquisition/o_pause.do")
	public String pause(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		Integer siteId = CmsUtils.getSiteId(request);
		if (vldExist(id, siteId, errors)) {
			return errors.showErrorPage(model);
		}
		acquisitionService.pause(id);
		log.info("pause CmsAcquisition id={}", id);
		return "redirect:v_list.do";
	}

	@RequiresPermissions("acquisition:o_cancel")
	@RequestMapping("/acquisition/o_cancel.do")
	public String cancel(Integer id, Integer sortId, Integer pageNo,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		WebErrors errors = WebErrors.create(request);
		Integer siteId = CmsUtils.getSiteId(request);
		if (vldExist(id, siteId, errors)) {
			return errors.showErrorPage(model);
		}
		acquisitionService.cancel(siteId, id);
		log.info("cancel CmsAcquisition id={}", id);
		return "redirect:v_list.do";
	}

	@RequiresPermissions("acquisition:v_check_complete")
	@RequestMapping("/acquisition/v_check_complete.do")
	public void checkComplete(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject json = new JSONObject();
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getSiteId();
		CmsAcquisition acqu = acquisitionService.getStarted(siteId);
		json.put("completed", acqu == null ? true : false);
		ResponseUtils.renderJson(response, json.toString());
	}

	@RequiresPermissions("acquisition:v_progress_data")
	@RequestMapping("/acquisition/v_progress_data.do")
	public String progressData(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getSiteId();
		CmsAcquisition acqu = acquisitionService.getStarted(siteId);
		List<CmsAcquisitionTemp> list = acquisitionTempService.getList(siteId);
		model.put("percent", acquisitionTempService.getPercent(siteId));
		model.put("acqu", acqu);
		model.put("list", list);
		return "acquisition/progress_data";
	}

	@RequiresPermissions("acquisition:v_progress")
	@RequestMapping("/acquisition/v_progress.do")
	public String progress(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getSiteId();
		CmsAcquisition acqu = acquisitionService.getStarted(siteId);
		if (acqu == null) {
			acquisitionTempService.clear(siteId);
		}
		return "acquisition/progress";
	}

	@RequiresPermissions("acquisition:v_history")
	@RequestMapping("/acquisition/v_history.do")
	public String history(Integer acquId, Integer pageNo,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		Integer siteId = site.getSiteId();
		Pagination pagination = acquisitionHistoryService.getPage(siteId,
				acquId, cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pagination.getPageNo());
		return "acquisition/history";
	}

	@RequiresPermissions("acquisition:o_delete_history")
	@RequestMapping("/acquisition/o_delete_history.do")
	public String deleteHistory(Integer[] ids, Integer pageNo,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		WebErrors errors = validateHistoryDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsAcquisitionHistory[] beans = acquisitionHistoryService
				.deleteByIds(ids);
		for (CmsAcquisitionHistory bean : beans) {
			log.info("delete CmsAcquisitionHistory id={}", bean.getHistoryId());
			logService.operating(request, "cmsAcquisitionHistory.log.delete",
					"id=" + bean.getHistoryId() + ";name=" + bean.getTitle());
		}
		return history(null, pageNo, request, response, model);
	}

	private WebErrors validateSave(CmsAcquisition bean,
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

	private WebErrors validateStart(Integer[] ids, HttpServletRequest request) {
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
		CmsAcquisition entity = acquisitionService.findById(id);
		if (errors.ifNotExist(entity, CmsAcquisition.class, id)) {
			return true;
		}
		if (!entity.getSite().getSiteId().equals(siteId)) {
			errors.notInSite(CmsAcquisition.class, id);
			return true;
		}
		return false;
	}

	private WebErrors validateHistoryDelete(Integer[] ids,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		CmsSite site = CmsUtils.getSite(request);
		if (errors.ifEmpty(ids, "ids")) {
			return errors;
		}
		for (Integer id : ids) {
			vldHistoryExist(id, site.getSiteId(), errors);
		}
		return errors;
	}

	private boolean vldHistoryExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsAcquisitionHistory entity = acquisitionHistoryService.findById(id);
		if (errors.ifNotExist(entity, CmsAcquisitionHistory.class, id)) {
			return true;
		}
		if (!entity.getAcquisition().getSite().getSiteId().equals(siteId)) {
			errors.notInSite(CmsAcquisitionHistory.class, id);
			return true;
		}
		return false;
	}

	@Resource
	private ContentTypeService contentTypeService;
	@Resource
	private ChannelService channelService;
	@Resource
	private AcquisitionSvc acquisitionSvc;
	@Resource
	private LogService logService;
	@Resource
	private AcquisitionService acquisitionService;
	@Resource
	private AcquisitionHistoryService acquisitionHistoryService;
	@Resource
	private AcquisitionTempService acquisitionTempService;
}