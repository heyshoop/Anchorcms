package com.anchorcms.core.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-5
 * @Desc CMS站点公司信息表
 */
@Entity
@Table(name = "c_site_company", schema = "db_cms")
public class CmsSiteCompany implements Serializable{
    private static final long serialVersionUID = 7675721008971582865L;
    private int siteId;
    private String name;
    private String scale;
    private String nature;
    private String industry;
    private String contact;
    private String description;
    private String address;
    private Double longitude;
    private Double latitude;

    @Id
    @Column(name = "site_id")
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "scale")
    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    @Basic
    @Column(name = "nature")
    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    @Basic
    @Column(name = "industry")
    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsSiteCompany that = (CmsSiteCompany) o;

        if (siteId != that.siteId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;
        if (nature != null ? !nature.equals(that.nature) : that.nature != null) return false;
        if (industry != null ? !industry.equals(that.industry) : that.industry != null) return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = siteId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (nature != null ? nature.hashCode() : 0);
        result = 31 * result + (industry != null ? industry.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        return result;
    }

    private CmsSite site;
    @OneToOne
    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }
}
