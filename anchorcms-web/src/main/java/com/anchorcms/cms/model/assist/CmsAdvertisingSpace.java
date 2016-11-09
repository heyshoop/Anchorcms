package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-9
 * @Desc CMS广告版位表
 */
@Entity
@Table(name = "c_advertising_space", schema = "db_cms")
public class CmsAdvertisingSpace implements Serializable{
    private static final long serialVersionUID = 2579814099100568796L;
    private int adspaceId;
    private int siteId;
    private String adName;
    private String description;
    private String isEnabled;

    @Id
    @Column(name = "adspace_id")
    public int getAdspaceId() {
        return adspaceId;
    }

    public void setAdspaceId(int adspaceId) {
        this.adspaceId = adspaceId;
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
    @Column(name = "ad_name")
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "is_enabled")
    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsAdvertisingSpace that = (CmsAdvertisingSpace) o;

        if (adspaceId != that.adspaceId) return false;
        if (siteId != that.siteId) return false;
        if (adName != null ? !adName.equals(that.adName) : that.adName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (isEnabled != null ? !isEnabled.equals(that.isEnabled) : that.isEnabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adspaceId;
        result = 31 * result + siteId;
        result = 31 * result + (adName != null ? adName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isEnabled != null ? isEnabled.hashCode() : 0);
        return result;
    }
}
