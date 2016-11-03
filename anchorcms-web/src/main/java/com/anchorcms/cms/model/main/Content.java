package com.anchorcms.cms.model.main;

import com.anchorcms.core.model.CmsGroup;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-3
 * @Desc CMS内容表
 */
@Entity
@Table(name = "c_content", schema = "db_cms")
public class Content implements Serializable{
    private static final long serialVersionUID = 6895381296604577589L;
    private int contentId;
    private int channelId;
    private int userId;
    private int typeId;
    private int modelId;
    private int siteId;
    private Date sortDate;
    private Byte topLevel;
    private Boolean hasTitleImg;
    private Boolean isRecommend;
    private byte status;
    private Integer viewsDay;
    private Short commentsDay;
    private Short downloadsDay;
    private Short upsDay;
    private Integer score;
    private Byte recommendLevel;

    @Id
    @Column(name = "content_id")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "channel_id")
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "type_id")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
    @Column(name = "sort_date")
    public Date getSortDate() {
        return sortDate;
    }

    public void setSortDate(Date sortDate) {
        this.sortDate = sortDate;
    }

    @Basic
    @Column(name = "top_level")
    public Byte getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(Byte topLevel) {
        this.topLevel = topLevel;
    }

    @Basic
    @Column(name = "has_title_img")
    public Boolean getHasTitleImg() {
        return hasTitleImg;
    }

    public void setHasTitleImg(Boolean hasTitleImg) {
        this.hasTitleImg = hasTitleImg;
    }

    @Basic
    @Column(name = "is_recommend")
    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "views_day")
    public Integer getViewsDay() {
        return viewsDay;
    }

    public void setViewsDay(Integer viewsDay) {
        this.viewsDay = viewsDay;
    }

    @Basic
    @Column(name = "comments_day")
    public Short getCommentsDay() {
        return commentsDay;
    }

    public void setCommentsDay(Short commentsDay) {
        this.commentsDay = commentsDay;
    }

    @Basic
    @Column(name = "downloads_day")
    public Short getDownloadsDay() {
        return downloadsDay;
    }

    public void setDownloadsDay(Short downloadsDay) {
        this.downloadsDay = downloadsDay;
    }

    @Basic
    @Column(name = "ups_day")
    public Short getUpsDay() {
        return upsDay;
    }

    public void setUpsDay(Short upsDay) {
        this.upsDay = upsDay;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "recommend_level")
    public Byte getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(Byte recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (contentId != content.contentId) return false;
        if (channelId != content.channelId) return false;
        if (userId != content.userId) return false;
        if (typeId != content.typeId) return false;
        if (modelId != content.modelId) return false;
        if (siteId != content.siteId) return false;
        if (topLevel != content.topLevel) return false;
        if (hasTitleImg != content.hasTitleImg) return false;
        if (isRecommend != content.isRecommend) return false;
        if (status != content.status) return false;
        if (viewsDay != content.viewsDay) return false;
        if (commentsDay != content.commentsDay) return false;
        if (downloadsDay != content.downloadsDay) return false;
        if (upsDay != content.upsDay) return false;
        if (score != content.score) return false;
        if (recommendLevel != content.recommendLevel) return false;
        if (sortDate != null ? !sortDate.equals(content.sortDate) : content.sortDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentId;
        result = 31 * result + channelId;
        result = 31 * result + userId;
        result = 31 * result + typeId;
        result = 31 * result + modelId;
        result = 31 * result + siteId;
        result = 31 * result + (sortDate != null ? sortDate.hashCode() : 0);
        result = 31 * result + (int) topLevel;
        result = 31 * result + (int) status;
        result = 31 * result + viewsDay;
        result = 31 * result + (int) commentsDay;
        result = 31 * result + (int) downloadsDay;
        result = 31 * result + (int) upsDay;
        result = 31 * result + score;
        result = 31 * result + (int) recommendLevel;
        return result;
    }

    @OneToMany
    private Set<Channel> channels;
    @ManyToMany
    private Set<CmsTopic> topics;
    @OneToMany
    private Set<ContentCheck> contentCheckSet;
    @ManyToMany
    private Set<CmsGroup> viewGroups;
    @ManyToMany
    private List<ContentTag> tags;
    @OneToMany
    private List<ContentAttachment> attachments;
    @OneToMany
    private List<ContentPicture> pictures;
    @ManyToOne
    private Channel channel;
    @ManyToOne
    private ContentType type;
    @ManyToOne
    private CmsUser user;
    @ManyToOne
    private CmsSite site;
    @Transient
    private Map<String,String> attr;
    @ManyToMany
    private Set<CmsUser> collectUsers;
    @OneToOne
    private ContentExt contentExt;
    @OneToMany
    private Set<CmsFile> files;

    public Set<CmsFile> getFiles() {
        return files;
    }

    public void setFiles(Set<CmsFile> files) {
        this.files = files;
    }

    public ContentExt getContentExt() {
        return contentExt;
    }

    public void setContentExt(ContentExt contentExt) {
        this.contentExt = contentExt;
    }

    public Set<CmsUser> getCollectUsers() {
        return collectUsers;
    }

    public void setCollectUsers(Set<CmsUser> collectUsers) {
        this.collectUsers = collectUsers;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }

    public CmsUser getUser() {
        return user;
    }

    public void setUser(CmsUser user) {
        this.user = user;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<ContentPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ContentPicture> pictures) {
        this.pictures = pictures;
    }

    public List<ContentAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<ContentAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<ContentTag> getTags() {
        return tags;
    }

    public void setTags(List<ContentTag> tags) {
        this.tags = tags;
    }

    public Set<CmsGroup> getViewGroups() {
        return viewGroups;
    }

    public void setViewGroups(Set<CmsGroup> viewGroups) {
        this.viewGroups = viewGroups;
    }

    public Set<ContentCheck> getContentCheckSet() {
        return contentCheckSet;
    }

    public void setContentCheckSet(Set<ContentCheck> contentCheckSet) {
        this.contentCheckSet = contentCheckSet;
    }

    public Set<CmsTopic> getTopics() {
        return topics;
    }

    public void setTopics(Set<CmsTopic> topics) {
        this.topics = topics;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    public void init() {
        short zero = 0;
        byte bzero = 0;
        if (getViewsDay() == null) {
            setViewsDay(0);
        }
        if (getCommentsDay() == null) {
            setCommentsDay(zero);
        }
        if (getDownloadsDay() == null) {
            setDownloadsDay(zero);
        }
        if (getUpsDay() == null) {
            setUpsDay(zero);
        }
        if (getHasTitleImg() == null) {
            setHasTitleImg(false);
        }
        if (getIsRecommend() == null) {
            setIsRecommend(false);
        }
        if (getSortDate() == null) {
            setSortDate(new Timestamp(System.currentTimeMillis()));
        }
        if (getTopLevel() == null) {
            setTopLevel(bzero);
        }
        // 保存后立即生成静态化，如果这些值为null，则需要在模板中增加判断，使模板编写变得复杂。
        if (getChannels() == null) {
            setChannels(new HashSet<Channel>());
        }
        if (getTopics() == null) {
            setTopics(new HashSet<CmsTopic>());
        }
        if (getViewGroups() == null) {
            setViewGroups(new HashSet<CmsGroup>());
        }
        if (getTags() == null) {
            setTags(new ArrayList<ContentTag>());
        }
        if (getPictures() == null) {
            setPictures(new ArrayList<ContentPicture>());
        }
        if (getAttachments() == null) {
            setAttachments(new ArrayList<ContentAttachment>());
        }
        if(getScore()==null){
            setScore(0);
        }
        if(getRecommendLevel()==null){
            setRecommendLevel(bzero);
        }
    }

    /**
     * 状态
     */
    public enum ContentStatus {
        /**
         * 所有
         */
        all,
        /**
         * 草稿
         */
        draft,
        /**
         * 待审核
         */
        prepared,
        /**
         * 已审
         */
        passed,
        /**
         * 终审
         */
        checked,
        /**
         * 退回
         */
        rejected,
        /**
         * 回收
         */
        recycle,
        /**
         * 投稿
         */
        contribute,
        /**
         * 归档
         */
        pigeonhole
    };
    public void addToChannels(Channel channel) {
        Set<Channel> channels = getChannels();
        if (channels == null) {
            channels = new HashSet<Channel>();
            setChannels(channels);
        }
        channels.add(channel);
    }
    public void addToTopics(CmsTopic topic) {
        Set<CmsTopic> topics = getTopics();
        if (topics == null) {
            topics = new HashSet<CmsTopic>();
            setTopics(topics);
        }
        topics.add(topic);
    }
    public ContentCheck getContentCheck() {
        Set<ContentCheck> set = getContentCheckSet();
        if (set != null && set.size() > 0) {
            return set.iterator().next();
        } else {
            return null;
        }
    }
    public void addToGroups(CmsGroup group) {
        Set<CmsGroup> groups = getViewGroups();
        if (groups == null) {
            groups = new HashSet<CmsGroup>();
            setViewGroups(groups);
        }
        groups.add(group);
    }
    public void addToAttachmemts(String path, String name, String filename) {
        List<ContentAttachment> list = getAttachments();
        if (list == null) {
            list = new ArrayList<ContentAttachment>();
            setAttachments(list);
        }
        ContentAttachment ca = new ContentAttachment(path, name, 0);
        if (!StringUtils.isBlank(filename)) {
            ca.setFilename(filename);
        }
        list.add(ca);
    }
    public void addToPictures(String path, String desc) {
        List<ContentPicture> list = getPictures();
        if (list == null) {
            list = new ArrayList<ContentPicture>();
            setPictures(list);
        }
        ContentPicture cp = new ContentPicture();
        cp.setImgPath(path);
        cp.setDescription(desc);
        list.add(cp);
    }
    public Byte getCheckStep() {
        ContentCheck check = getContentCheck();
        if (check != null) {
            return check.getCheckStep();
        } else {
            return null;
        }
    }
    public Boolean getRejected() {
        ContentCheck check = getContentCheck();
        if (check != null) {
            return check.getIsRejected();
        } else {
            return null;
        }
    }
    public void clear(){
        getCollectUsers().clear();
    }
    public String getTitle() {
        ContentExt ext = getContentExt();
        if (ext != null) {
            return ext.getTitle();
        } else {
            return null;
        }
    }
    public void setContentTxt(ContentTxt txt) {
        Set<ContentTxt> set = getContentTxtSet();
        if (set == null) {
            set = new HashSet<ContentTxt>();
            setContentTxtSet(set);
        }
        if (!set.isEmpty()) {
            set.clear();
        }
        set.add(txt);
    }
}
