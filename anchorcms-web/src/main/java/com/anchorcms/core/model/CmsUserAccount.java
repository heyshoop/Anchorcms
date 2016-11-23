package com.anchorcms.core.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.Constraint;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/23
 * @Desc 用户稿费收费配置
 */
@Entity
@Table(name = "c_user_account")
public class CmsUserAccount implements Serializable{
    private static final long serialVersionUID = -6338259894120791786L;
    private int userId;
    private String accountWeixin;
    private String accountWeixinOpenId;
    private String accountAlipy;
    private Short drawAccount;
    private Double contentTotalAmount;
    private Double contentNoPayAmount;
    private double contentYearAmount;
    private double contentMonthAmount;
    private double contentDayAmount;
    private Integer contentBuyCount;
    private Integer drawCount;
    private Date lastDrawTime;
    private Date lastBuyTime;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "account_weixin")
    public String getAccountWeixin() {
        return accountWeixin;
    }

    public void setAccountWeixin(String accountWeixin) {
        this.accountWeixin = accountWeixin;
    }

    @Basic
    @Column(name = "account_weixin_openId")
    public String getAccountWeixinOpenId() {
        return accountWeixinOpenId;
    }

    public void setAccountWeixinOpenId(String accountWeixinOpenId) {
        this.accountWeixinOpenId = accountWeixinOpenId;
    }

    @Basic
    @Column(name = "account_alipy")
    public String getAccountAlipy() {
        return accountAlipy;
    }

    public void setAccountAlipy(String accountAlipy) {
        this.accountAlipy = accountAlipy;
    }

    @Basic
    @Column(name = "draw_account")
    public Short getDrawAccount() {
        return drawAccount;
    }

    public void setDrawAccount(Short drawAccount) {
        this.drawAccount = drawAccount;
    }

    @Basic
    @Column(name = "content_total_amount")
    public Double getContentTotalAmount() {
        return contentTotalAmount;
    }

    public void setContentTotalAmount(Double contentTotalAmount) {
        this.contentTotalAmount = contentTotalAmount;
    }

    @Basic
    @Column(name = "content_no_pay_amount")
    public Double getContentNoPayAmount() {
        return contentNoPayAmount;
    }

    public void setContentNoPayAmount(Double contentNoPayAmount) {
        this.contentNoPayAmount = contentNoPayAmount;
    }

    @Basic
    @Column(name = "content_year_amount")
    public double getContentYearAmount() {
        return contentYearAmount;
    }

    public void setContentYearAmount(double contentYearAmount) {
        this.contentYearAmount = contentYearAmount;
    }

    @Basic
    @Column(name = "content_month_amount")
    public double getContentMonthAmount() {
        return contentMonthAmount;
    }

    public void setContentMonthAmount(double contentMonthAmount) {
        this.contentMonthAmount = contentMonthAmount;
    }

    @Basic
    @Column(name = "content_day_amount")
    public double getContentDayAmount() {
        return contentDayAmount;
    }

    public void setContentDayAmount(double contentDayAmount) {
        this.contentDayAmount = contentDayAmount;
    }

    @Basic
    @Column(name = "content_buy_count")
    public Integer getContentBuyCount() {
        return contentBuyCount;
    }

    public void setContentBuyCount(Integer contentBuyCount) {
        this.contentBuyCount = contentBuyCount;
    }

    @Basic
    @Column(name = "draw_count")
    public Integer getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(Integer drawCount) {
        this.drawCount = drawCount;
    }

    @Basic
    @Column(name = "last_draw_time")
    public Date getLastDrawTime() {
        return lastDrawTime;
    }

    public void setLastDrawTime(Date lastDrawTime) {
        this.lastDrawTime = lastDrawTime;
    }

    @Basic
    @Column(name = "last_buy_time")
    public Date getLastBuyTime() {
        return lastBuyTime;
    }

    public void setLastBuyTime(Date lastBuyTime) {
        this.lastBuyTime = lastBuyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsUserAccount that = (CmsUserAccount) o;

        if (userId != that.userId) return false;
        if (drawAccount != that.drawAccount) return false;
        if (Double.compare(that.contentYearAmount, contentYearAmount) != 0) return false;
        if (Double.compare(that.contentMonthAmount, contentMonthAmount) != 0) return false;
        if (Double.compare(that.contentDayAmount, contentDayAmount) != 0) return false;
        if (accountWeixin != null ? !accountWeixin.equals(that.accountWeixin) : that.accountWeixin != null)
            return false;
        if (accountWeixinOpenId != null ? !accountWeixinOpenId.equals(that.accountWeixinOpenId) : that.accountWeixinOpenId != null)
            return false;
        if (accountAlipy != null ? !accountAlipy.equals(that.accountAlipy) : that.accountAlipy != null) return false;
        if (contentTotalAmount != null ? !contentTotalAmount.equals(that.contentTotalAmount) : that.contentTotalAmount != null)
            return false;
        if (contentNoPayAmount != null ? !contentNoPayAmount.equals(that.contentNoPayAmount) : that.contentNoPayAmount != null)
            return false;
        if (contentBuyCount != null ? !contentBuyCount.equals(that.contentBuyCount) : that.contentBuyCount != null)
            return false;
        if (drawCount != null ? !drawCount.equals(that.drawCount) : that.drawCount != null) return false;
        if (lastDrawTime != null ? !lastDrawTime.equals(that.lastDrawTime) : that.lastDrawTime != null) return false;
        if (lastBuyTime != null ? !lastBuyTime.equals(that.lastBuyTime) : that.lastBuyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        result = 31 * result + (accountWeixin != null ? accountWeixin.hashCode() : 0);
        result = 31 * result + (accountWeixinOpenId != null ? accountWeixinOpenId.hashCode() : 0);
        result = 31 * result + (accountAlipy != null ? accountAlipy.hashCode() : 0);
        result = 31 * result + (int) drawAccount;
        result = 31 * result + (contentTotalAmount != null ? contentTotalAmount.hashCode() : 0);
        result = 31 * result + (contentNoPayAmount != null ? contentNoPayAmount.hashCode() : 0);
        temp = Double.doubleToLongBits(contentYearAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(contentMonthAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(contentDayAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (contentBuyCount != null ? contentBuyCount.hashCode() : 0);
        result = 31 * result + (drawCount != null ? drawCount.hashCode() : 0);
        result = 31 * result + (lastDrawTime != null ? lastDrawTime.hashCode() : 0);
        result = 31 * result + (lastBuyTime != null ? lastBuyTime.hashCode() : 0);
        return result;
    }
    private CmsUser user;

    @OneToOne
    @JoinColumn(name = "user_id")
    public CmsUser getUser() {
        return user;
    }

    public void setUser(CmsUser user) {
        this.user = user;
    }

    public static final byte DRAW_WEIXIN=0;

    public static final byte DRAW_ALIPY=1;
}
