package com.anchorcms.cms.model.main;

import com.anchorcms.core.model.CmsGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-2
 * @Desc
 */
@Entity
@Table(name = "c_channel", schema = "db_cms")
public class Channel implements Serializable{
    private int channelId;
    private int modelId;
    private int siteId;
    private Integer parentId;
    private String channelPath;
    private int lft;
    private int rgt;
    private int priority;
    private byte hasContent;
    private byte isDisplay;

    @Id
    @Column(name = "channel_id")
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
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
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "has_content")
    public byte getHasContent() {
        return hasContent;
    }

    public void setHasContent(byte hasContent) {
        this.hasContent = hasContent;
    }

    @Basic
    @Column(name = "is_display")
    public byte getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(byte isDisplay) {
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
        result = 31 * result + (int) hasContent;
        result = 31 * result + (int) isDisplay;
        return result;
    }
    @ManyToMany
    private Set<CmsGroup> viewGroups;
    @ManyToMany
    private Set<CmsGroup> contriGroups;

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

    public  void removeViewGroup(CmsGroup group) {
        Set<CmsGroup>viewGroups=getViewGroups();
        viewGroups.remove(group);
    }
    public  void removeContriGroup(CmsGroup group) {
        Set<CmsGroup>contriGroups=getContriGroups();
        contriGroups.remove(group);
    }
}
