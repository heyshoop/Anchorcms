package com.anchorcms.cms.controller.admin.main;

import static com.anchorcms.common.constants.Constants.TPLDIR_TOPIC;
import static com.anchorcms.cms.controller.front.FrontTopicController.TOPIC_DEFAULT;
import static com.anchorcms.cms.controller.front.FrontTopicController.TOPIC_INDEX;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.model.main.CmsTopic;
import com.anchorcms.cms.service.assist.FileService;
import com.anchorcms.cms.service.main.ChannelService;
import com.anchorcms.cms.service.main.TopicService;
import com.anchorcms.common.utils.CmsUtils;
import com.anchorcms.common.utils.CoreUtils;
import com.anchorcms.common.web.CookieUtils;
import com.anchorcms.common.web.ResponseUtils;
import com.anchorcms.common.web.mvc.MessageResolver;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.service.LogService;
import com.anchorcms.core.service.TplService;
import com.anchorcms.core.web.WebErrors;
import com.anchorcms.common.page.Pagination;

import static com.anchorcms.common.page.SimplePage.cpn;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainTopicController {
    private static final Logger log = LoggerFactory.getLogger(MainTopicController.class);

    @RequiresPermissions("topic:v_list")
    @RequestMapping("/topic/v_list.do")
    public String list(Integer pageNo, HttpServletRequest request,
                       ModelMap model) {
        Pagination pagination = manager.getPage(cpn(pageNo), CookieUtils
                .getPageSize(request));
        model.addAttribute("pagination", pagination);
        return "topic/list";
    }

    @RequiresPermissions("topic:v_add")
    @RequestMapping("/topic/v_add.do")
    public String add(HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        // 模板
        List<String> tplList = getTplList(request, site, null);
        // 栏目
        List<Channel> topList = channelService.getTopList(site.getSiteId(), true);
        List<Channel> channelList = Channel.getListForSelect(topList, null,
                true);
        model.addAttribute("tplList", tplList);
        model.addAttribute("channelList", channelList);
        return "topic/add";
    }

    @RequiresPermissions("topic:v_edit")
    @RequestMapping("/topic/v_edit.do")
    public String edit(Integer id, HttpServletRequest request, ModelMap model) {
        WebErrors errors = validateEdit(id, request);
        if (errors.hasErrors()) {
            return errors.showErrorPage(model);
        }
        CmsSite site = CmsUtils.getSite(request);
        CmsTopic topic = manager.findById(id);
        // 模板
        List<String> tplList = getTplList(request, site, topic.getTplContent());
        // 栏目
        Integer siteId;
        Channel channel = topic.getChannel();
        if (channel != null) {
            siteId = channel.getSite().getSiteId();
        } else {
            siteId = site.getSiteId();
        }
        List<Channel> topList = channelService.getTopList(siteId, true);
        List<Channel> channelList = Channel.getListForSelect(topList, null,
                true);
        model.addAttribute("tplList", tplList);
        model.addAttribute("channelList", channelList);
        model.addAttribute("tplContent", topic.getTplContentShort(site.getTplPath()));
        //model.addAttribute("tplContent", topic.getTplContent(site.getTplPath()));
        model.addAttribute("cmsTopic", topic);
        return "topic/edit";
    }

    @RequiresPermissions("topic:o_save")
    @RequestMapping("/topic/o_save.do")
    public String save(CmsTopic bean, Integer channelId,
                       Integer[] channelIds,
                       HttpServletRequest request, ModelMap model) {
        WebErrors errors = validateSave(bean, request);
        if (errors.hasErrors()) {
            return errors.showErrorPage(model);
        }
        CmsSite site = CmsUtils.getSite(request);
        if (!StringUtils.isBlank(bean.getTplContent())) {
            bean.setTplContent(site.getTplPath() + bean.getTplContent());
        }
        bean = manager.save(bean, channelId, channelIds);
        fileMng.updateFileByPath(bean.getContentImg(), true, null);
        fileMng.updateFileByPath(bean.getTitleImg(), true, null);
        log.info("save CmsTopic id={}", bean.getTopicId());
        logService.operating(request, "cmsTopic.log.save", "id=" + bean.getTopicId()
                + ";name=" + bean.getTopicName());
        return "redirect:v_list.do";
    }

    @RequiresPermissions("topic:o_update")
    @RequestMapping("/topic/o_update.do")
    public String update(CmsTopic bean, Integer channelId, Integer[] channelIds,
                         String oldTitleImg, String oldContentImg,
                         Integer pageNo, HttpServletRequest request, ModelMap model) {
        WebErrors errors = validateUpdate(bean.getTopicId(), request);
        if (errors.hasErrors()) {
            return errors.showErrorPage(model);
        }
        CmsSite site = CmsUtils.getSite(request);
        if (!StringUtils.isBlank(bean.getTplContent())) {
            bean.setTplContent(site.getTplPath() + bean.getTplContent());
        }
        bean = manager.update(bean, channelId, channelIds);
        //旧标题图
        fileMng.updateFileByPath(oldTitleImg, false, null);
        //旧内容图
        fileMng.updateFileByPath(oldContentImg, false, null);
        fileMng.updateFileByPath(bean.getContentImg(), true, null);
        fileMng.updateFileByPath(bean.getTitleImg(), true, null);
        log.info("update CmsTopic id={}.", bean.getTopicId());
        logService.operating(request, "cmsTopic.log.update", "id="
                + bean.getTopicId() + ";name=" + bean.getTopicName());
        return list(pageNo, request, model);
    }

    @RequiresPermissions("topic:o_delete")
    @RequestMapping("/topic/o_delete.do")
    public String delete(Integer[] ids, Integer pageNo,
                         HttpServletRequest request, ModelMap model) {
        WebErrors errors = validateDelete(ids, request);
        if (errors.hasErrors()) {
            return errors.showErrorPage(model);
        }
        CmsTopic[] beans = manager.deleteByIds(ids);
        for (CmsTopic bean : beans) {
            fileMng.updateFileByPath(bean.getContentImg(), false, null);
            fileMng.updateFileByPath(bean.getTitleImg(), false, null);
            log.info("delete CmsTopic id={}", bean.getTopicId());
            logService.operating(request, "cmsTopic.log.delete", "id="
                    + bean.getTopicId() + ";name=" + bean.getTopicName());
        }
        return list(pageNo, request, model);
    }

    @RequiresPermissions("topic:o_priority")
    @RequestMapping("/topic/o_priority.do")
    public String priority(Integer[] wids, Integer[] priority, Integer pageNo,
                           HttpServletRequest request, ModelMap model) {
        WebErrors errors = validatePriority(wids, priority, request);
        if (errors.hasErrors()) {
            return errors.showErrorPage(model);
        }
        manager.updatePriority(wids, priority);
        model.addAttribute("message", "global.success");
        return list(pageNo, request, model);
    }

    @RequiresPermissions("topic:by_channel")
    @RequestMapping("/topic/by_channel.do")
    public void topicsByChannel(Integer channelId, HttpServletResponse response)
            throws JSONException {
        JSONArray arr = new JSONArray();
        JSONObject o;
        List<CmsTopic> list;
        if (channelId != null) {
            list = manager.getListByChannel(channelId);
        } else {
            list = manager.getListForTag(null, false, Integer.MAX_VALUE);
        }
        for (CmsTopic t : list) {
            o = new JSONObject();
            o.put("id", t.getTopicId());
            o.put("name", t.getTopicName());
            arr.put(o);
        }
        ResponseUtils.renderJson(response, arr.toString());
    }

    private List<String> getTplList(HttpServletRequest request, CmsSite site,
                                    String tpl) {
        List<String> tplList = tplManager.getNameListByPrefix(site
                .getSolutionPath()
                + "/" + TPLDIR_TOPIC + "/");
        String tplIndex = MessageResolver.getMessage(request, TOPIC_INDEX);
        String tplDefault = MessageResolver.getMessage(request, TOPIC_DEFAULT);
        tplList = CoreUtils.tplTrim(tplList, site.getTplPath(), tpl, tplIndex,
                tplDefault);
        return tplList;
    }

    private WebErrors validateSave(CmsTopic bean, HttpServletRequest request) {
        WebErrors errors = WebErrors.create(request);
        return errors;
    }

    private WebErrors validateEdit(Integer id, HttpServletRequest request) {
        WebErrors errors = WebErrors.create(request);
        if (vldExist(id, errors)) {
            return errors;
        }
        return errors;
    }

    private WebErrors validateUpdate(Integer id, HttpServletRequest request) {
        WebErrors errors = WebErrors.create(request);
        if (vldExist(id, errors)) {
            return errors;
        }
        return errors;
    }

    private WebErrors validateDelete(Integer[] ids, HttpServletRequest request) {
        WebErrors errors = WebErrors.create(request);
        errors.ifEmpty(ids, "ids");
        for (Integer id : ids) {
            vldExist(id, errors);
        }
        return errors;
    }

    private WebErrors validatePriority(Integer[] wids, Integer[] priority,
                                       HttpServletRequest request) {
        WebErrors errors = WebErrors.create(request);
        if (errors.ifEmpty(wids, "wids")) {
            return errors;
        }
        if (errors.ifEmpty(priority, "priority")) {
            return errors;
        }
        if (wids.length != priority.length) {
            errors.addErrorString("wids length not equals priority length");
            return errors;
        }
        for (int i = 0, len = wids.length; i < len; i++) {
            if (vldExist(wids[i], errors)) {
                return errors;
            }
            if (priority[i] == null) {
                priority[i] = 0;
            }
        }
        return errors;
    }

    private boolean vldExist(Integer id, WebErrors errors) {
        if (errors.ifNull(id, "id")) {
            return true;
        }
        CmsTopic entity = manager.findById(id);
        if (errors.ifNotExist(entity, CmsTopic.class, id)) {
            return true;
        }
        return false;
    }

    @Resource
    private TplService tplManager;
    @Resource
    private ChannelService channelService;
    @Resource
    private LogService logService;
    @Resource(name = "TopicService")
    private TopicService manager;
    @Resource
    private FileService fileMng;
}