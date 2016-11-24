package com.anchorcms.cms.model.assist;

import com.anchorcms.cms.model.main.Content;
import com.anchorcms.core.model.CmsUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/24
 * @Desc 职务申请
 */
@Entity
@Table(name = "c_job_apply")
public class CmsJobApply implements Serializable{
    private static final long serialVersionUID = -7939927444344043563L;
    private int jobApplyId;
    private int userId;
    private int contentId;
    private Date applyTime;

    @Id
    @Column(name = "job_apply_id")
    public int getJobApplyId() {
        return jobApplyId;
    }

    public void setJobApplyId(int jobApplyId) {
        this.jobApplyId = jobApplyId;
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
    @Column(name = "content_id")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "apply_time")
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsJobApply cmsJobApply = (CmsJobApply) o;

        if (jobApplyId != cmsJobApply.jobApplyId) return false;
        if (userId != cmsJobApply.userId) return false;
        if (contentId != cmsJobApply.contentId) return false;
        if (applyTime != null ? !applyTime.equals(cmsJobApply.applyTime) : cmsJobApply.applyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jobApplyId;
        result = 31 * result + userId;
        result = 31 * result + contentId;
        result = 31 * result + (applyTime != null ? applyTime.hashCode() : 0);
        return result;
    }
    private Content content;
    private CmsUser user;

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    public CmsUser getUser() {
        return user;
    }

    public void setUser(CmsUser user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "content_id",insertable = false,updatable = false)
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
