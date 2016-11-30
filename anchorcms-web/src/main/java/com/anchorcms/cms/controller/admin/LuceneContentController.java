package com.anchorcms.cms.controller.admin;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.lucene.LuceneContentSvc;
import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.service.main.ChannelService;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.core.model.CmsSite;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 阁楼麻雀
 * @Date 2016/11/29 14:38
 * @Desc 全文检索controller
 */

@Controller
public class LuceneContentController {
	private static final Logger log = LoggerFactory.getLogger(LuceneContentController.class);

	@RequiresPermissions("lucene:v_index")
	@RequestMapping(value = "/lucene/v_index.do")
	public String index(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);

		List<Channel> topList = channelService.getTopList(site.getSiteId(), true);
		List<Channel> channelList = Channel.getListForSelect(topList, null,
				true);
		model.addAttribute("site", site);
		model.addAttribute("channelList", channelList);
		return "lucene/index";
	}

	@RequiresPermissions("lucene:o_create")
	@RequestMapping(value = "/lucene/o_create.do")
	public void create(Integer siteId, Integer channelId, Date startDate,
			Date endDate, Integer startId, Integer max,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws JSONException {
		try {
			Integer lastId = luceneContentSvc.createIndex(siteId, channelId,
					startDate, endDate, startId, max);
			JSONObject json = new JSONObject();
			json.put("success", true);
			if (lastId != null) {
				json.put("lastId", lastId);
			}
			ResponseUtils.renderJson(response, json.toString());
		} catch (CorruptIndexException e) {
			JSONObject json = new JSONObject();
			json.put("success", false).put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
			log.error("", e);
		} catch (LockObtainFailedException e) {
			JSONObject json = new JSONObject();
			json.put("success", false).put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
			log.error("", e);
		} catch (IOException e) {
			JSONObject json = new JSONObject();
			json.put("success", false).put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
			log.error("", e);
		} catch (ParseException e) {
			JSONObject json = new JSONObject();
			json.put("success", false).put("msg", e.getMessage());
			ResponseUtils.renderJson(response, json.toString());
			log.error("", e);
		}
	}

	@Resource
	private LuceneContentSvc luceneContentSvc;

	@Resource
	private ChannelService channelService;
}
