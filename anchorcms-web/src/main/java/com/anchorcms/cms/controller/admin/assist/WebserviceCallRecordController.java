package com.anchorcms.cms.controller.admin.assist;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.anchorcms.cms.service.main.WebserviceCallRecordService;
import com.anchorcms.common.page.Pagination;
import com.anchorcms.common.web.CookieUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.anchorcms.common.page.SimplePage.cpn;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/24 14:09
 * @Desc webservice调用记录controller
 */

@Controller
public class WebserviceCallRecordController {

	@RequiresPermissions("webserviceCallRecord:v_list")
	@RequestMapping("/webserviceCallRecord/v_list.do")
	public String list(Integer pageNo, HttpServletRequest request, ModelMap model) {
		Pagination pagination = webserviceCallRecordService.getPage(cpn(pageNo), CookieUtils
				.getPageSize(request));
		model.addAttribute("pagination",pagination);
		return "webserviceCallRecord/list";
	}
	
	@RequiresPermissions("webserviceCallRecord:o_delete")
	@RequestMapping("/webserviceCallRecord/o_delete.do")
	public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		webserviceCallRecordService.deleteByIds(ids);
		return list(pageNo, request, model);
	}
	@Resource
	private WebserviceCallRecordService webserviceCallRecordService;
}