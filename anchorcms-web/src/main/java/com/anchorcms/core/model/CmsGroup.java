package com.anchorcms.core.model;

import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.common.hibernate.PriorityComparator;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-2
 * @Desc
 */
@Entity
@Table(name = "c_group")
public class CmsGroup implements Serializable{
    private Integer groupId;
    private String groupName;
    private int priority;
    private Boolean needCaptcha;
    private Boolean needCheck;
    private int allowPerDay;
    private int allowMaxFile;
    private String allowSuffix;
    private Boolean isRegDef;

    @Id
    @Column(name = "group_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
    @Column(name = "need_captcha")
    public Boolean getNeedCaptcha() {
        return needCaptcha;
    }

    public void setNeedCaptcha(Boolean needCaptcha) {
        this.needCaptcha = needCaptcha;
    }

    @Basic
    @Column(name = "need_check")
    public Boolean getNeedCheck() {
        return needCheck;
    }

    public void setNeedCheck(Boolean needCheck) {
        this.needCheck = needCheck;
    }

    @Basic
    @Column(name = "allow_per_day")
    public int getAllowPerDay() {
        return allowPerDay;
    }

    public void setAllowPerDay(int allowPerDay) {
        this.allowPerDay = allowPerDay;
    }

    @Basic
    @Column(name = "allow_max_file")
    public int getAllowMaxFile() {
        return allowMaxFile;
    }

    public void setAllowMaxFile(int allowMaxFile) {
        this.allowMaxFile = allowMaxFile;
    }

    @Basic
    @Column(name = "allow_suffix")
    public String getAllowSuffix() {
        return allowSuffix;
    }

    public void setAllowSuffix(String allowSuffix) {
        this.allowSuffix = allowSuffix;
    }

    @Basic
    @Column(name = "is_reg_def")
    public Boolean getIsRegDef() {
        return isRegDef;
    }

    public void setIsRegDef(Boolean isRegDef) {
        this.isRegDef = isRegDef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsGroup cmsGroup = (CmsGroup) o;

        if (groupId != cmsGroup.groupId) return false;
        if (priority != cmsGroup.priority) return false;
        if (needCaptcha != cmsGroup.needCaptcha) return false;
        if (needCheck != cmsGroup.needCheck) return false;
        if (allowPerDay != cmsGroup.allowPerDay) return false;
        if (allowMaxFile != cmsGroup.allowMaxFile) return false;
        if (isRegDef != cmsGroup.isRegDef) return false;
        if (groupName != null ? !groupName.equals(cmsGroup.groupName) : cmsGroup.groupName != null) return false;
        if (allowSuffix != null ? !allowSuffix.equals(cmsGroup.allowSuffix) : cmsGroup.allowSuffix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + allowPerDay;
        result = 31 * result + allowMaxFile;
        result = 31 * result + (allowSuffix != null ? allowSuffix.hashCode() : 0);
        return result;
    }

    private Set<Channel> viewChannels;

    private Set<Channel> contriGroups;

    private Set<Channel> contriChannels;

    @ManyToMany
    @JoinTable(name = "c_chnl_group_view",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")})
    public Set<Channel> getViewChannels() {
        return viewChannels;
    }

    public void setViewChannels(Set<Channel> viewChannels) {
        this.viewChannels = viewChannels;
    }

    @ManyToMany
    public Set<Channel> getContriGroups() {
        return contriGroups;
    }

    public void setContriGroups(Set<Channel> contriGroups) {
        this.contriGroups = contriGroups;
    }

    @ManyToMany
    @JoinTable(name = "c_chnl_group_contri",
            joinColumns = {@JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    public Set<Channel> getContriChannels() {
        return contriChannels;
    }

    public void setContriChannels(Set<Channel> contriChannels) {
        this.contriChannels = contriChannels;
    }

    /**
     * @Author 阁楼麻雀
     * @Date 2016-11-2 15:24
     * @Desc 初始化
     */
    public void init() {
        if (getIsRegDef() == null) {
            setIsRegDef(false);
        }
    }

    public void addToViewChannels(Channel channel) {
        Set<Channel> channels = getViewChannels();
        if (channels == null) {
            channels = new TreeSet<Channel>((Collection<? extends Channel>) new PriorityComparator());
            setViewChannels(channels);
        }
        channels.add(channel);
        channel.getViewGroups().add(this);
    }
    public void addToContriChannels(Channel channel) {
        Set<Channel> channels = getContriChannels();
        if (channels == null) {
            channels = new TreeSet<Channel>((Collection<? extends Channel>) new PriorityComparator());
            setContriChannels(channels);
        }
        channels.add(channel);
        channel.getContriGroups().add(this);
    }
    /**
     * 从集合中提取ID数组
     *
     * @param groups
     * @return
     */
    public static Integer[] fetchIds(Collection<CmsGroup> groups) {
        Integer[] ids = new Integer[groups.size()];
        int i = 0;
        for (CmsGroup g : groups) {
            ids[i++] = g.getGroupId();
        }
        return ids;
    }
    /**
     * 是否允许上传后缀
     *
     * @param ext
     * @return
     */
    public boolean isAllowSuffix(String ext) {
        String suffix = getAllowSuffix();
        if (StringUtils.isBlank(suffix)) {
            return true;
        }
        String[] attr = StringUtils.split(suffix, ",");
        for (int i = 0, len = attr.length; i < len; i++) {
            if (attr[i].equals(ext)) {
                return true;
            }
        }
        return false;
    }
    @Transient
    public Set<Integer> getViewChannelIds(Integer siteId) {
        Set<Channel> channels = getViewChannels();
        Set<Integer> ids = new HashSet<Integer>();
        for (Channel c : channels) {
            if (c.getSite().getSiteId().equals(siteId)) {
                ids.add(c.getChannelId());
            }
        }
        return ids;
    }
    @Transient
    public Set<Integer> getContriChannelIds(Integer siteId) {
        Set<Channel> channels = getContriChannels();
        Set<Integer> ids = new HashSet<Integer>();
        for (Channel c : channels) {
            if (c.getSite().getSiteId().equals(siteId)) {
                ids.add(c.getChannelId());
            }
        }
        return ids;
    }
}
