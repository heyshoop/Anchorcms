package com.anchorcms.cms.model.assist;

import com.anchorcms.cms.model.main.Content;
import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-7
 * @Desc CMS评论表
 */
@Entity
@Table(name = "c_comment", schema = "db_cms")
public class CmsComment implements Serializable{
    private static final long serialVersionUID = 850793090492422718L;
    private int commentId;
    private Integer commentUserId;
    private Integer replyUserId;
    private int contentId;
    private int siteId;
    private Serializable createTime;
    private Serializable replyTime;
    private Short ups;
    private Short downs;
    private Boolean isRecommend;
    private Boolean isChecked;
    private Integer score;
    private Integer parentId;
    private Integer replyCount;

    @Id
    @Column(name = "comment_id")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "comment_user_id")
    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    @Basic
    @Column(name = "reply_user_id")
    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
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
    @Column(name = "site_id")
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "create_time")
    public Serializable getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Serializable createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "reply_time")
    public Serializable getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Serializable replyTime) {
        this.replyTime = replyTime;
    }

    @Basic
    @Column(name = "ups")
    public Short getUps() {
        return ups;
    }

    public void setUps(Short ups) {
        this.ups = ups;
    }

    @Basic
    @Column(name = "downs")
    public Short getDowns() {
        return downs;
    }

    public void setDowns(Short downs) {
        this.downs = downs;
    }

    @Basic
    @Column(name = "is_recommend")
    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Basic
    @Column(name = "is_checked")
    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "reply_count")
    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsComment cmsComment = (CmsComment) o;

        if (commentId != cmsComment.commentId) return false;
        if (contentId != cmsComment.contentId) return false;
        if (siteId != cmsComment.siteId) return false;
        if (ups != cmsComment.ups) return false;
        if (downs != cmsComment.downs) return false;
        if (isRecommend != cmsComment.isRecommend) return false;
        if (isChecked != cmsComment.isChecked) return false;
        if (commentUserId != null ? !commentUserId.equals(cmsComment.commentUserId) : cmsComment.commentUserId != null)
            return false;
        if (replyUserId != null ? !replyUserId.equals(cmsComment.replyUserId) : cmsComment.replyUserId != null)
            return false;
        if (createTime != null ? !createTime.equals(cmsComment.createTime) : cmsComment.createTime != null) return false;
        if (replyTime != null ? !replyTime.equals(cmsComment.replyTime) : cmsComment.replyTime != null) return false;
        if (score != null ? !score.equals(cmsComment.score) : cmsComment.score != null) return false;
        if (parentId != null ? !parentId.equals(cmsComment.parentId) : cmsComment.parentId != null) return false;
        if (replyCount != null ? !replyCount.equals(cmsComment.replyCount) : cmsComment.replyCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (commentUserId != null ? commentUserId.hashCode() : 0);
        result = 31 * result + (replyUserId != null ? replyUserId.hashCode() : 0);
        result = 31 * result + contentId;
        result = 31 * result + siteId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        result = 31 * result + (int) ups;
        result = 31 * result + (int) downs;
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (replyCount != null ? replyCount.hashCode() : 0);
        return result;
    }

    private Content content;

    private CmsSite site;

    private CmsUser commentUser;

    private CmsComment parent;

    private CmsCommentExt commentExt;
    @OneToOne
    public CmsCommentExt getCommentExt() {
        return commentExt;
    }

    public void setCommentExt(CmsCommentExt commentExt) {
        this.commentExt = commentExt;
    }
    @OneToOne
    public CmsComment getParent() {
        return parent;
    }

    public void setParent(CmsComment parent) {
        this.parent = parent;
    }
    @ManyToOne
    public CmsUser getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(CmsUser commentUser) {
        this.commentUser = commentUser;
    }
    @ManyToOne
    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }
    @ManyToOne
    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
    public void init() {
        short zero = 0;
        if (getDowns() == null) {
            setDowns(zero);
        }
        if (getUps() == null) {
            setUps(zero);
        }
        if(getReplyCount()==null){
            setReplyCount(0);
        }
        if (getIsChecked() == null) {
            setIsChecked(false);
        }
        if (getIsRecommend() == null) {
            setIsRecommend(false);
        }
        if (getCreateTime() == null) {
            setCreateTime(new Timestamp(System.currentTimeMillis()));
        }
    }
}
