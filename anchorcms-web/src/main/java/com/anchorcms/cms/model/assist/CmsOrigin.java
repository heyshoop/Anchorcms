package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/24
 * @Desc 文章来源信息表
 */
@Entity
@Table(name = "c_origin")
public class CmsOrigin implements Serializable{
    private static final long serialVersionUID = 2192641102044569722L;
    private int originId;
    private String originName;
    private int refCount;

    @Id
    @Column(name = "origin_id")
    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    @Basic
    @Column(name = "origin_name")
    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    @Basic
    @Column(name = "ref_count")
    public int getRefCount() {
        return refCount;
    }

    public void setRefCount(int refCount) {
        this.refCount = refCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsOrigin cmsOrigin = (CmsOrigin) o;

        if (originId != cmsOrigin.originId) return false;
        if (refCount != cmsOrigin.refCount) return false;
        if (originName != null ? !originName.equals(cmsOrigin.originName) : cmsOrigin.originName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = originId;
        result = 31 * result + (originName != null ? originName.hashCode() : 0);
        result = 31 * result + refCount;
        return result;
    }
}
