package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-9
 * @Desc CMS广告属性表
 */
@Entity
@Table(name = "c_advertising_attr")
public class CmsAdvertisingAttr implements Serializable{
    private static final long serialVersionUID = 7124576385897921946L;
    private int advertisingId;
    private String attrName;
    private String attrValue;

    @Basic
    @Column(name = "advertising_id")
    public int getAdvertisingId() {
        return advertisingId;
    }

    public void setAdvertisingId(int advertisingId) {
        this.advertisingId = advertisingId;
    }

    @Basic
    @Column(name = "attr_name")
    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    @Basic
    @Column(name = "attr_value")
    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsAdvertisingAttr that = (CmsAdvertisingAttr) o;

        if (advertisingId != that.advertisingId) return false;
        if (attrName != null ? !attrName.equals(that.attrName) : that.attrName != null) return false;
        if (attrValue != null ? !attrValue.equals(that.attrValue) : that.attrValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = advertisingId;
        result = 31 * result + (attrName != null ? attrName.hashCode() : 0);
        result = 31 * result + (attrValue != null ? attrValue.hashCode() : 0);
        return result;
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
