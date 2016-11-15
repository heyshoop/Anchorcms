package com.anchorcms.cms.model.main;

import javax.persistence.*;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-15
 * @Desc
 */
@Entity
@Table(name = "c_contenttag", schema = "db_cms")
public class CContenttag {
    private int contentId;
    private int tagId;
    private int priority;

    @Basic
    @Column(name = "content_id")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "tag_id")
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CContenttag that = (CContenttag) o;

        if (contentId != that.contentId) return false;
        if (tagId != that.tagId) return false;
        if (priority != that.priority) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentId;
        result = 31 * result + tagId;
        result = 31 * result + priority;
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
