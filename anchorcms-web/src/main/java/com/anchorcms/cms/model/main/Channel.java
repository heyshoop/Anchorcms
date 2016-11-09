package com.anchorcms.cms.model.main;

import com.anchorcms.common.hibernate.PriorityComparator;
import com.anchorcms.common.utils.StaticPageUtils;
import com.anchorcms.core.model.CmsGroup;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static com.anchorcms.cms.web.CmsThreadVariable.getSite;
import static com.anchorcms.common.constants.Constants.INDEX;
import static com.anchorcms.common.constants.Constants.SPT;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-2
 * @Desc CMS栏目表
 */
@Entity
@Table(name = "c_channel", schema = "db_cms")
public class Channel implements Serializable{
    private static final long serialVersionUID = -9066154332731793820L;
    private Integer channelId;
    private int modelId;
    private int siteId;
    private Integer parentId;
    private String channelPath;
    private int lft;
    private int rgt;
    private Integer priority;
    private Boolean hasContent;
    private Boolean isDisplay;

    @Id
    @Column(name = "channel_id")
    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Basic
    @Column(name = "model_id")
    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    @Basic
    @Column(name = "site_id")
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "channel_path")
    public String getChannelPath() {
        return channelPath;
    }

    public void setChannelPath(String channelPath) {
        this.channelPath = channelPath;
    }

    @Basic
    @Column(name = "lft")
    public int getLft() {
        return lft;
    }

    public void setLft(int lft) {
        this.lft = lft;
    }

    @Basic
    @Column(name = "rgt")
    public int getRgt() {
        return rgt;
    }

    public void setRgt(int rgt) {
        this.rgt = rgt;
    }

    @Basic
    @Column(name = "priority")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "has_content")
    public Boolean getHasContent() {
        return hasContent;
    }

    public void setHasContent(Boolean hasContent) {
        this.hasContent = hasContent;
    }

    @Basic
    @Column(name = "is_display")
    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Channel channel = (Channel) o;

        if (channelId != channel.channelId) return false;
        if (modelId != channel.modelId) return false;
        if (siteId != channel.siteId) return false;
        if (lft != channel.lft) return false;
        if (rgt != channel.rgt) return false;
        if (priority != channel.priority) return false;
        if (hasContent != channel.hasContent) return false;
        if (isDisplay != channel.isDisplay) return false;
        if (parentId != null ? !parentId.equals(channel.parentId) : channel.parentId != null) return false;
        if (channelPath != null ? !channelPath.equals(channel.channelPath) : channel.channelPath != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = channelId;
        result = 31 * result + modelId;
        result = 31 * result + siteId;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (channelPath != null ? channelPath.hashCode() : 0);
        result = 31 * result + lft;
        result = 31 * result + rgt;
        result = 31 * result + priority;
        return result;
    }
    @ManyToMany
    private Set<CmsGroup> viewGroups;
    @ManyToMany
    private Set<CmsGroup> contriGroups;
    @ManyToMany
    private Set<CmsUser> users;
    @OneToOne
    private ChannelExt channelExt;
    @ManyToOne
    private Channel parent;
    @ManyToOne
    private CmsSite site;
    @ManyToOne
    private CmsModel model;
    @OneToMany
    private List<ChannelModel> channelModels;
    @OneToMany
    private Set<ChannelTxt> channelTxtSet;
    @OneToOne
    private ChannelCount channelCount;
    @Transient
    private Map<String,String> attr;

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public ChannelCount getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(ChannelCount channelCount) {
        this.channelCount = channelCount;
    }

    public Set<ChannelTxt> getChannelTxtSet() {
        return channelTxtSet;
    }

    public void setChannelTxtSet(Set<ChannelTxt> channelTxtSet) {
        this.channelTxtSet = channelTxtSet;
    }

    public List<ChannelModel> getChannelModels() {
        return channelModels;
    }

    public void setChannelModels(List<ChannelModel> channelModels) {
        this.channelModels = channelModels;
    }

    public CmsModel getModel() {
        return model;
    }

    public void setModel(CmsModel model) {
        this.model = model;
    }

    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }

    public Channel getParent() {
        return parent;
    }

    public void setParent(Channel parent) {
        this.parent = parent;
    }

    public ChannelExt getChannelExt() {
        return channelExt;
    }

    public void setChannelExt(ChannelExt channelExt) {
        this.channelExt = channelExt;
    }

    public Set<CmsUser> getUsers() {
        return users;
    }

    public void setUsers(Set<CmsUser> users) {
        this.users = users;
    }

    public Set<CmsGroup> getViewGroups() {
        return viewGroups;
    }

    public void setViewGroups(Set<CmsGroup> viewGroups) {
        this.viewGroups = viewGroups;
    }

    public Set<CmsGroup> getContriGroups() {
        return contriGroups;
    }

    public void setContriGroups(Set<CmsGroup> contriGroups) {
        this.contriGroups = contriGroups;
    }

    /**
     * 审核后内容修改方式
     */
    public static enum AfterCheckEnum {
        /**
         * 不能修改，不能删除。
         */
        CANNOT_UPDATE,
        /**
         * 可以修改，可以删除。 修改后文章的审核级别将退回到修改人级别的状态。如果修改人的级别高于当前文章的审核级别，那么文章审核级别将保持不变。
         */
        BACK_UPDATE,
        /**
         * 可以修改，可以删除。 修改后文章保持原状态。
         */
        KEEP_UPDATE
    }

    public  void removeViewGroup(CmsGroup group) {
        Set<CmsGroup>viewGroups=getViewGroups();
        viewGroups.remove(group);
    }
    public  void removeContriGroup(CmsGroup group) {
        Set<CmsGroup>contriGroups=getContriGroups();
        contriGroups.remove(group);
    }
    public void addToUsers(CmsUser user) {
        Set<CmsUser> set = getUsers();
        if (set == null) {
            set = new TreeSet<CmsUser>((Collection<? extends CmsUser>) new PriorityComparator());
            setUsers(set);
        }
        set.add(user);
        user.addToChannels(this);
    }
    /**
     * 获得栏目终审级别
     *
     * @return
     */
    public Byte getFinalStepExtends() {
        Byte step = getFinalStep();
        if (step == null) {
            Channel parent = getParent();
            if (parent == null) {
                return getSite().getFinalStep();
            } else {
                return parent.getFinalStepExtends();
            }
        } else {
            return step;
        }
    }
    public Byte getFinalStep() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getFinalStep();
        } else {
            return null;
        }
    }
    /**
     * 获得审核后修改方式的枚举值。如果该值为null则取父级栏目，父栏目为null则取站点相关设置。
     *
     * @return
     */
    public AfterCheckEnum getAfterCheckEnum() {
        Byte after = getChannelExt().getAfterCheck();
        Channel channel = getParent();
        // 如果为null，则查找父栏目。
        while (after == null && channel != null) {
            after = channel.getAfterCheck();
            channel = channel.getParent();
        }
        // 如果依然为null，则查找站点设置
        if (after == null) {
            after = getSite().getAfterCheck();
        }
        if (after == 1) {
            return AfterCheckEnum.CANNOT_UPDATE;
        } else if (after == 2) {
            return AfterCheckEnum.BACK_UPDATE;
        } else if (after == 3) {
            return AfterCheckEnum.KEEP_UPDATE;
        } else {
            // 默认为不可改、不可删
            return AfterCheckEnum.CANNOT_UPDATE;
        }
    }
    public Byte getAfterCheck() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getAfterCheck();
        } else {
            return null;
        }
    }
    public Boolean getStaticContent() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getIsStaticContent();
        } else {
            return null;
        }
    }
    /**
     * 获得节点列表ID。从父节点到自身。
     *
     * @return
     */
    public Integer[] getNodeIds() {
        List<Channel> channels = getNodeList();
        Integer[] ids = new Integer[channels.size()];
        int i = 0;
        for (Channel c : channels) {
            ids[i++] = c.getChannelId();
        }
        return ids;
    }
    /**
     * 获得节点列表。从父节点到自身。
     *
     * @return
     */
    public List<Channel> getNodeList() {
        LinkedList<Channel> list = new LinkedList<Channel>();
        Channel node = this;
        while (node != null) {
            list.addFirst(node);
            node = node.getParent();
        }
        return list;
    }
    public void init() {
        if (getPriority() == null) {
            setPriority(10);
        }
        if (getIsDisplay() == null) {
            setIsDisplay(true);
        }
    }
    public void addToViewGroups(CmsGroup group) {
        Set<CmsGroup> groups = getViewGroups();
        if (groups == null) {
            groups = new TreeSet<CmsGroup>((Collection<? extends CmsGroup>) new PriorityComparator());
            setViewGroups(groups);
        }
        groups.add(group);
        group.getViewChannels().add(this);
    }
    public void addToContriGroups(CmsGroup group) {
        Set<CmsGroup> groups = getContriGroups();
        if (groups == null) {
            groups = new TreeSet<CmsGroup>((Collection<? extends CmsGroup>) new PriorityComparator());
            setContriGroups(groups);
        }
        groups.add(group);
        group.getContriChannels().add(this);
    }
    public void addToChannelModels(CmsModel model,String tpl,String mtpl) {
        List<ChannelModel> list = getChannelModels();
        if (list == null) {
            list = new ArrayList<ChannelModel>();
            setChannelModels(list);
        }
        ChannelModel cm = new ChannelModel();
        cm.setTplContent(tpl);
        cm.setTplMobileContent(mtpl);
        cm.setModelId(model.getModelId());
        list.add(cm);
    }
    public String getContentRule() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getContentRule();
        } else {
            return null;
        }
    }
    public String getLink() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getLink();
        } else {
            return null;
        }
    }
    public Boolean getStaticChannel() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getIsStaticChannel();
        } else {
            return null;
        }
    }
    public Boolean getListChild() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getIsListChild();
        } else {
            return null;
        }
    }
    public Integer getPageSize() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getPageSize();
        } else {
            return null;
        }
    }
    public String getStaticFilename(int pageNo) {
        CmsSite site = getSite();
        StringBuilder url = new StringBuilder();
        String staticDir = site.getStaticDir();
        if (!StringUtils.isBlank(staticDir)) {
            url.append(staticDir);
        }
        String filename = getStaticFilenameByRule();
        if (!StringUtils.isBlank(filename)) {
            int index = filename.indexOf(".", filename.lastIndexOf("/"));
            if (pageNo > 1) {
                if (index != -1) {
                    url.append(filename.substring(0, index)).append("_")
                            .append(pageNo).append(filename.substring(index));
                } else {
                    url.append(filename).append("_").append(pageNo);
                }
            } else {
                url.append(filename);
            }
        } else {
            // 默认静态页面访问路径
            url.append(SPT).append(getChannelPath());
            String suffix = site.getStaticSuffix();
            if (getHasContent()) {
                url.append(SPT).append(INDEX);
                if (pageNo > 1) {
                    url.append("_").append(pageNo);
                }
                url.append(suffix);
            } else {
                if (pageNo > 1) {
                    url.append("_").append(pageNo);
                }
                url.append(suffix);
            }
        }
        return url.toString();
    }
    public String getStaticFilenameByRule() {
        String rule = getChannelRule();
        if (StringUtils.isBlank(rule)) {
            return null;
        }
        CmsModel model = getModel();
        String url = StaticPageUtils.staticUrlRule(rule, model.getModelId(), model
                .getModelPath(), getChannelId(), getChannelPath(), null, null);
        return url;
    }
    public String getChannelRule() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getChannelRule();
        } else {
            return null;
        }
    }
    public String getMobileStaticFilename(int pageNo) {
        CmsSite site = getSite();
        StringBuilder url = new StringBuilder();
        String staticDir = site.getMobileStaticDir();
        if (!StringUtils.isBlank(staticDir)) {
            url.append(staticDir);
        }
        String filename = getStaticFilenameByRule();
        if (!StringUtils.isBlank(filename)) {
            int index = filename.indexOf(".", filename.lastIndexOf("/"));
            if (pageNo > 1) {
                if (index != -1) {
                    url.append(filename.substring(0, index)).append("_")
                            .append(pageNo).append(filename.substring(index));
                } else {
                    url.append(filename).append("_").append(pageNo);
                }
            } else {
                url.append(filename);
            }
        } else {
            // 默认静态页面访问路径
            url.append(SPT).append(getChannelPath());
            String suffix = site.getStaticSuffix();
            if (getHasContent()) {
                url.append(SPT).append(INDEX);
                if (pageNo > 1) {
                    url.append("_").append(pageNo);
                }
                url.append(suffix);
            } else {
                if (pageNo > 1) {
                    url.append("_").append(pageNo);
                }
                url.append(suffix);
            }
        }
        return url.toString();
    }
    public String getTplContentOrDef(CmsModel contentModel) {
        String tpl = getModelTpl(contentModel);
        if (!StringUtils.isBlank(tpl)) {
            return tpl;
        } else {
            String sol = getSite().getSolutionPath();
            return contentModel.getTplContent(sol, true);
        }
    }
    public String getModelTpl(CmsModel model){
        List<ChannelModel>list=getChannelModelsExtend();
        if(list!=null){
            for(ChannelModel cm:list){
                if(cm.getModel().equals(model)){
                    return cm.getTplContent();
                }
            }
        }
        return null;
    }
    public List<ChannelModel> getChannelModelsExtend() {
        List<ChannelModel>list=getChannelModels();
        //没有配置栏目模型默认父栏目配置
        if (list == null||list.size()<=0) {
            Channel parent = getParent();
            if (parent == null) {
                return null;
            } else {
                return parent.getChannelModelsExtend();
            }
        } else {
            return list;
        }
    }
    public String getMobileTplContentOrDef(CmsModel contentModel) {
        String tpl = getModelMobileTpl(contentModel);
        if (!StringUtils.isBlank(tpl)) {
            return tpl;
        } else {
            String sol = getSite().getMobileSolutionPath();
            return contentModel.getTplContent(sol, true);
        }
    }
    public String getModelMobileTpl(CmsModel model){
        List<ChannelModel>list=getChannelModelsExtend();
        if(list!=null){
            for(ChannelModel cm:list){
                if(cm.getModel().equals(model)){
                    return cm.getTplMobileContent();
                }
            }
        }
        return null;
    }
    public String getMobileTplChannelOrDef() {
        String tpl = getMobileTplChannel();
        if (!StringUtils.isBlank(tpl)) {
            return tpl;
        } else {
            String sol = getSite().getMobileSolutionPath();
            return getModel().getTplChannel(sol, true);
        }
    }
    public String getMobileTplChannel() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getTplMobileChannel();
        } else {
            return null;
        }
    }
    public String getTplChannelOrDef() {
        String tpl = getTplChannel();
        if (!StringUtils.isBlank(tpl)) {
            return tpl;
        } else {
            String sol = getSite().getSolutionPath();
            return getModel().getTplChannel(sol, true);
        }
    }
    public String getTplChannel() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getTplChannel();
        } else {
            return null;
        }
    }
    public String getUrlStatic(int pageNo) {
        return getUrlStatic(null, pageNo);
    }
    public String getUrlStatic(Boolean whole, int pageNo) {
        if (!StringUtils.isBlank(getLink())) {
            return getLink();
        }
        CmsSite site = getSite();
        StringBuilder url = site.getUrlBuffer(false, whole, false);
        String filename = getStaticFilenameByRule();
        if (!StringUtils.isBlank(filename)) {
            if (pageNo > 1) {
                int index = filename.indexOf(".", filename.lastIndexOf("/"));
                if (index != -1) {
                    url.append(filename.substring(0, index));
                    url.append("_").append(pageNo);
                    url.append(filename.substring(index));
                } else {
                    url.append("_").append(pageNo);
                }
            } else {
                if (getAccessByDir()) {
                    url.append(filename.substring(0,
                            filename.lastIndexOf("/") + 1));
                } else {
                    url.append(filename);
                }
            }
        } else {
            // 默认静态页面访问路径
            url.append(SPT).append(getChannelPath());
            if (pageNo > 1) {
                url.append("_").append(pageNo);
                url.append(site.getStaticSuffix());
            } else {
                if (getHasContent()) {
                    url.append(SPT);
                } else {
                    url.append(site.getStaticSuffix());
                }
            }
        }
        return url.toString();
    }
    public Boolean getAccessByDir() {
        ChannelExt ext = getChannelExt();
        if (ext != null) {
            return ext.getIsAccessByDir();
        } else {
            return null;
        }
    }
}
