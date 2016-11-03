package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-3
 * @Desc CMS内容计数表
 */
@Entity
@Table(name = "c_content_count", schema = "db_cms")
public class ContentCount implements Serializable{
    private static final long serialVersionUID = -7649522000147440059L;
    private int contentId;
    private int views;
    private int viewsMonth;
    private int viewsWeek;
    private int viewsDay;
    private int comments;
    private int commentsMonth;
    private short commentsWeek;
    private short commentsDay;
    private int downloads;
    private int downloadsMonth;
    private short downloadsWeek;
    private short downloadsDay;
    private int ups;
    private int upsMonth;
    private short upsWeek;
    private short upsDay;
    private int downs;

    @Id
    @Column(name = "content_id")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "views")
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Basic
    @Column(name = "views_month")
    public int getViewsMonth() {
        return viewsMonth;
    }

    public void setViewsMonth(int viewsMonth) {
        this.viewsMonth = viewsMonth;
    }

    @Basic
    @Column(name = "views_week")
    public int getViewsWeek() {
        return viewsWeek;
    }

    public void setViewsWeek(int viewsWeek) {
        this.viewsWeek = viewsWeek;
    }

    @Basic
    @Column(name = "views_day")
    public int getViewsDay() {
        return viewsDay;
    }

    public void setViewsDay(int viewsDay) {
        this.viewsDay = viewsDay;
    }

    @Basic
    @Column(name = "comments")
    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    @Basic
    @Column(name = "comments_month")
    public int getCommentsMonth() {
        return commentsMonth;
    }

    public void setCommentsMonth(int commentsMonth) {
        this.commentsMonth = commentsMonth;
    }

    @Basic
    @Column(name = "comments_week")
    public short getCommentsWeek() {
        return commentsWeek;
    }

    public void setCommentsWeek(short commentsWeek) {
        this.commentsWeek = commentsWeek;
    }

    @Basic
    @Column(name = "comments_day")
    public short getCommentsDay() {
        return commentsDay;
    }

    public void setCommentsDay(short commentsDay) {
        this.commentsDay = commentsDay;
    }

    @Basic
    @Column(name = "downloads")
    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    @Basic
    @Column(name = "downloads_month")
    public int getDownloadsMonth() {
        return downloadsMonth;
    }

    public void setDownloadsMonth(int downloadsMonth) {
        this.downloadsMonth = downloadsMonth;
    }

    @Basic
    @Column(name = "downloads_week")
    public short getDownloadsWeek() {
        return downloadsWeek;
    }

    public void setDownloadsWeek(short downloadsWeek) {
        this.downloadsWeek = downloadsWeek;
    }

    @Basic
    @Column(name = "downloads_day")
    public short getDownloadsDay() {
        return downloadsDay;
    }

    public void setDownloadsDay(short downloadsDay) {
        this.downloadsDay = downloadsDay;
    }

    @Basic
    @Column(name = "ups")
    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    @Basic
    @Column(name = "ups_month")
    public int getUpsMonth() {
        return upsMonth;
    }

    public void setUpsMonth(int upsMonth) {
        this.upsMonth = upsMonth;
    }

    @Basic
    @Column(name = "ups_week")
    public short getUpsWeek() {
        return upsWeek;
    }

    public void setUpsWeek(short upsWeek) {
        this.upsWeek = upsWeek;
    }

    @Basic
    @Column(name = "ups_day")
    public short getUpsDay() {
        return upsDay;
    }

    public void setUpsDay(short upsDay) {
        this.upsDay = upsDay;
    }

    @Basic
    @Column(name = "downs")
    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentCount that = (ContentCount) o;

        if (contentId != that.contentId) return false;
        if (views != that.views) return false;
        if (viewsMonth != that.viewsMonth) return false;
        if (viewsWeek != that.viewsWeek) return false;
        if (viewsDay != that.viewsDay) return false;
        if (comments != that.comments) return false;
        if (commentsMonth != that.commentsMonth) return false;
        if (commentsWeek != that.commentsWeek) return false;
        if (commentsDay != that.commentsDay) return false;
        if (downloads != that.downloads) return false;
        if (downloadsMonth != that.downloadsMonth) return false;
        if (downloadsWeek != that.downloadsWeek) return false;
        if (downloadsDay != that.downloadsDay) return false;
        if (ups != that.ups) return false;
        if (upsMonth != that.upsMonth) return false;
        if (upsWeek != that.upsWeek) return false;
        if (upsDay != that.upsDay) return false;
        if (downs != that.downs) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentId;
        result = 31 * result + views;
        result = 31 * result + viewsMonth;
        result = 31 * result + viewsWeek;
        result = 31 * result + viewsDay;
        result = 31 * result + comments;
        result = 31 * result + commentsMonth;
        result = 31 * result + (int) commentsWeek;
        result = 31 * result + (int) commentsDay;
        result = 31 * result + downloads;
        result = 31 * result + downloadsMonth;
        result = 31 * result + (int) downloadsWeek;
        result = 31 * result + (int) downloadsDay;
        result = 31 * result + ups;
        result = 31 * result + upsMonth;
        result = 31 * result + (int) upsWeek;
        result = 31 * result + (int) upsDay;
        result = 31 * result + downs;
        return result;
    }
}
