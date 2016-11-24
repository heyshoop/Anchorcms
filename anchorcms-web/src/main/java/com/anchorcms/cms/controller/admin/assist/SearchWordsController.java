package com.anchorcms.cms.controller.admin.assist;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.assist.CmsSearchWords;
import com.anchorcms.cms.service.assist.SearchWordsService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.utils.ChineseCharToEn;
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
 * @Date 2016/11/24 11:56
 * @Desc 查询关键词
 */

@Controller
public class SearchWordsController {
	private static final Logger log = LoggerFactory
			.getLogger(SearchWordsController.class);
	
	@RequiresPermissions("searchwords:v_list")
	@RequestMapping("/searchwords/v_list.do")
	public String list(String qname,Integer qrecommend,Integer orderBy,
			Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		Pagination pagination = searchWordsService.getPage(CmsUtils.getSiteId(request)
				,qname,qrecommend,orderBy,
				cpn(pageNo), CookieUtils.getPageSize(request));
		model.addAttribute("pagination", pagination);
		model.addAttribute("qname", qname);
		model.addAttribute("qrecommend", qrecommend);
		model.addAttribute("orderBy", orderBy);
		return "searchwords/list";
	}
	
	@RequiresPermissions("searchwords:v_ajax_edit")
	@RequestMapping("/searchwords/v_ajax_edit.do")
	public void ajaxEdit(Integer id, HttpServletRequest request,HttpServletResponse response, ModelMap model) throws JSONException {
		JSONObject object = new JSONObject();
		CmsSearchWords word=searchWordsService.findById(id);
		if(word!=null){
			object.put("id", word.getWordId());
			object.put("name", word.getName());
			object.put("priority", word.getPriority());
			object.put("recommend", word.getIsRecommend());
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequiresPermissions("searchwords:o_save")
	@RequestMapping("/searchwords/o_save.do")
	public String save(CmsSearchWords bean, HttpServletRequest request,
			ModelMap model) {
		WebErrors errors = validateSave(bean, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean.setHitCount(0);
		bean.setSite(CmsUtils.getSite(request));
		bean.setNameInitial(ChineseCharToEn.getAllFirstLetter(bean.getName()));
		bean = searchWordsService.save(bean);
		log.info("save CmsSearchWords id={}", bean.getWordId());
		logService.operating(request, "CmsSearchWords.log.save", "id="
				+ bean.getWordId() + ";name=" + bean.getName());
		return "redirect:v_list.do";
	}

	@RequiresPermissions("searchwords:o_update")
	@RequestMapping("/searchwords/o_update.do")
	public String update(CmsSearchWords bean,String qname,Integer qrecommend,
			Integer orderBy,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateUpdate(bean.getWordId(), request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		bean.setNameInitial(ChineseCharToEn.getAllFirstLetter(bean.getName()));
		bean = searchWordsService.update(bean);
		log.info("update CmsSearchWords id={}.", bean.getWordId());
		logService.operating(request, "CmsSearchWords.log.update", "id="
				+ bean.getWordId() + ";name=" + bean.getName());
		return list(qname,qrecommend,orderBy,pageNo, request, model);
	}

	@RequiresPermissions("searchwords:o_delete")
	@RequestMapping("/searchwords/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo,
			String qname,Integer qrecommend,Integer orderBy,
			HttpServletRequest request, ModelMap model) {
		WebErrors errors = validateDelete(ids, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model);
		}
		CmsSearchWords[] beans = searchWordsService.deleteByIds(ids);
		for (CmsSearchWords bean : beans) {
			log.info("delete CmsSearchWords id={}", bean.getWordId());
			logService.operating(request, "CmsSearchWords.log.delete", "id="
					+ bean.getWordId() + ";name=" + bean.getName());
		}
		return list(qname,qrecommend,orderBy,pageNo, request, model);
	}

	private WebErrors validateSave(CmsSearchWords bean, HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
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
		errors.ifEmpty(ids, "ids");
		for (Integer id : ids) {
			vldExist(id, site.getSiteId(), errors);
		}
		return errors;
	}

	private boolean vldExist(Integer id, Integer siteId, WebErrors errors) {
		if (errors.ifNull(id, "id")) {
			return true;
		}
		CmsSearchWords entity = searchWordsService.findById(id);
		if (errors.ifNotExist(entity, CmsSearchWords.class, id)) {
			return true;
		}
		return false;
	}
	
	@Resource
	private SearchWordsService searchWordsService;
	@Resource
	private LogService logService;
}