package com.anchorcms.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-3
 * @Desc CMS管理员站点表
 */
@Entity
@Table(name = "c_user_site", schema = "db_cms")
public class CmsUserSite implements Serializable{
    private static final long serialVersionUID = 2314702830912783727L;
    private int usersiteId;
    private int userId;
    private int siteId;
    private byte checkStep;
    private byte isAllChannel;

    @Id
    @Column(name = "usersite_id")
    public int getUsersiteId() {
        return usersiteId;
    }

    public void setUsersiteId(int usersiteId) {
        this.usersiteId = usersiteId;
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
    @Column(name = "site_id")
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "check_step")
    public byte getCheckStep() {
        return checkStep;
    }

    public void setCheckStep(byte checkStep) {
        this.checkStep = checkStep;
    }

    @Basic
    @Column(name = "is_all_channel")
    public byte getIsAllChannel() {
        return isAllChannel;
    }

    public void setIsAllChannel(byte isAllChannel) {
        this.isAllChannel = isAllChannel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsUserSite cmsUserSite = (CmsUserSite) o;

        if (usersiteId != cmsUserSite.usersiteId) return false;
        if (userId != cmsUserSite.userId) return false;
        if (siteId != cmsUserSite.siteId) return false;
        if (checkStep != cmsUserSite.checkStep) return false;
        if (isAllChannel != cmsUserSite.isAllChannel) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = usersiteId;
        result = 31 * result + userId;
        result = 31 * result + siteId;
        result = 31 * result + (int) checkStep;
        result = 31 * result + (int) isAllChannel;
        return result;
    }


    @ManyToOne
    private CmsSite site;
    @Transient
    private Boolean allChannel;

    public Boolean getAllChannel() {
        return allChannel;
    }

    public void setAllChannel(Boolean allChannel) {
        this.allChannel = allChannel;
    }

    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }
}
