package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-3
 * @Desc CMS内容TAG表
 */
@Entity
@Table(name = "c_content_tag", schema = "db_cms")
public class ContentTag implements Serializable{
    private static final long serialVersionUID = 7928904425697695411L;
    private int tagId;
    private String tagName;
    private int refCounter;

    @Id
    @Column(name = "tag_id")
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Basic
    @Column(name = "ref_counter")
    public int getRefCounter() {
        return refCounter;
    }

    public void setRefCounter(int refCounter) {
        this.refCounter = refCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentTag that = (ContentTag) o;

        if (tagId != that.tagId) return false;
        if (refCounter != that.refCounter) return false;
        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        result = 31 * result + refCounter;
        return result;
    }
}
