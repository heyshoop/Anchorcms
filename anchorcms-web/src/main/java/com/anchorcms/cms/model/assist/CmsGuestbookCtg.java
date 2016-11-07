package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-7
 * @Desc CMS留言类别
 */
@Entity
@Table(name = "c_guestbook_ctg", schema = "db_cms")
public class CmsGuestbookCtg implements Serializable{
    private static final long serialVersionUID = 6861658165437643760L;
    private int guestbookctgId;
    private int siteId;
    private String ctgName;
    private int priority;
    private String description;

    @Id
    @Column(name = "guestbookctg_id")
    public int getGuestbookctgId() {
        return guestbookctgId;
    }

    public void setGuestbookctgId(int guestbookctgId) {
        this.guestbookctgId = guestbookctgId;
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
    @Column(name = "ctg_name")
    public String getCtgName() {
        return ctgName;
    }

    public void setCtgName(String ctgName) {
        this.ctgName = ctgName;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsGuestbookCtg that = (CmsGuestbookCtg) o;

        if (guestbookctgId != that.guestbookctgId) return false;
        if (siteId != that.siteId) return false;
        if (priority != that.priority) return false;
        if (ctgName != null ? !ctgName.equals(that.ctgName) : that.ctgName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guestbookctgId;
        result = 31 * result + siteId;
        result = 31 * result + (ctgName != null ? ctgName.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
