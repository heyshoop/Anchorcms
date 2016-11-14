package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-3
 * @Desc CMS内容附件表
 */
@Entity
@Table(name = "c_content_attachment")
public class ContentAttachment implements Serializable {
    private static final long serialVersionUID = 7935335726226625516L;
    private int contentId;
    private int priority;
    private String attachmentPath;
    private String attachmentName;
    private String filename;
    private int downloadCount;
    private String id;


    public ContentAttachment() {
    }
    public ContentAttachment (String path,String name,Integer count) {
        this.setAttachmentPath(path);
        this.setFilename(name);
        this.setDownloadCount(count);
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content_id")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
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
    @Column(name = "attachment_path")
    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    @Basic
    @Column(name = "attachment_name")
    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    @Basic
    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "download_count")
    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentAttachment that = (ContentAttachment) o;

        if (contentId != that.contentId) return false;
        if (priority != that.priority) return false;
        if (downloadCount != that.downloadCount) return false;
        if (attachmentPath != null ? !attachmentPath.equals(that.attachmentPath) : that.attachmentPath != null)
            return false;
        if (attachmentName != null ? !attachmentName.equals(that.attachmentName) : that.attachmentName != null)
            return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentId;
        result = 31 * result + priority;
        result = 31 * result + (attachmentPath != null ? attachmentPath.hashCode() : 0);
        result = 31 * result + (attachmentName != null ? attachmentName.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        result = 31 * result + downloadCount;
        return result;
    }



}
