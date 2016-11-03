package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-3
 * @Desc CMS专题表
 */
@Entity
@Table(name = "c_topic", schema = "db_cms")
public class CmsTopic implements Serializable{
    private static final long serialVersionUID = 5568493810872848877L;
    private int topicId;

    @Id
    @Column(name = "topic_id")
    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    private Integer channelId;

    @Basic
    @Column(name = "channel_id")
    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    private String topicName;

    @Basic
    @Column(name = "topic_name")
    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    private String shortName;

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    private String keywords;

    @Basic
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    private String description;

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String titleImg;

    @Basic
    @Column(name = "title_img")
    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    private String contentImg;

    @Basic
    @Column(name = "content_img")
    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }

    private String tplContent;

    @Basic
    @Column(name = "tpl_content")
    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    private int priority;

    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }    private byte isRecommend;

    @Basic
    @Column(name = "is_recommend")
    public byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsTopic cmsTopic = (CmsTopic) o;

        if (topicId != cmsTopic.topicId) return false;
        if (priority != cmsTopic.priority) return false;
        if (isRecommend != cmsTopic.isRecommend) return false;
        if (channelId != null ? !channelId.equals(cmsTopic.channelId) : cmsTopic.channelId != null) return false;
        if (topicName != null ? !topicName.equals(cmsTopic.topicName) : cmsTopic.topicName != null) return false;
        if (shortName != null ? !shortName.equals(cmsTopic.shortName) : cmsTopic.shortName != null) return false;
        if (keywords != null ? !keywords.equals(cmsTopic.keywords) : cmsTopic.keywords != null) return false;
        if (description != null ? !description.equals(cmsTopic.description) : cmsTopic.description != null) return false;
        if (titleImg != null ? !titleImg.equals(cmsTopic.titleImg) : cmsTopic.titleImg != null) return false;
        if (contentImg != null ? !contentImg.equals(cmsTopic.contentImg) : cmsTopic.contentImg != null) return false;
        if (tplContent != null ? !tplContent.equals(cmsTopic.tplContent) : cmsTopic.tplContent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = topicId;
        result = 31 * result + (channelId != null ? channelId.hashCode() : 0);
        result = 31 * result + (topicName != null ? topicName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (titleImg != null ? titleImg.hashCode() : 0);
        result = 31 * result + (contentImg != null ? contentImg.hashCode() : 0);
        result = 31 * result + (tplContent != null ? tplContent.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (int) isRecommend;
        return result;
    }
}
