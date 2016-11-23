package com.anchorcms.cms.model.assist;

import com.anchorcms.core.model.CmsUser;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/23
 * @Desc 用户账户提现支付
 */
@Entity
@Table(name = "c_account_pay")
public class CmsAccountPay implements Serializable{
    private static final long serialVersionUID = 6774429343492291967L;
    private long accountPayId;
    private String drawNum;
    private int payUserId;
    private int drawUserId;
    private String payAccount;
    private String drawAccount;
    private Date payTime;
    private String weixinNum;
    private String alipayNum;

    @Id
    @Column(name = "account_pay_id")
    public long getAccountPayId() {
        return accountPayId;
    }

    public void setAccountPayId(long accountPayId) {
        this.accountPayId = accountPayId;
    }

    @Basic
    @Column(name = "draw_num")
    public String getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(String drawNum) {
        this.drawNum = drawNum;
    }

    @Basic
    @Column(name = "pay_user_id")
    public int getPayUserId() {
        return payUserId;
    }

    public void setPayUserId(int payUserId) {
        this.payUserId = payUserId;
    }

    @Basic
    @Column(name = "draw_user_id")
    public int getDrawUserId() {
        return drawUserId;
    }

    public void setDrawUserId(int drawUserId) {
        this.drawUserId = drawUserId;
    }

    @Basic
    @Column(name = "pay_account")
    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    @Basic
    @Column(name = "draw_account")
    public String getDrawAccount() {
        return drawAccount;
    }

    public void setDrawAccount(String drawAccount) {
        this.drawAccount = drawAccount;
    }

    @Basic
    @Column(name = "pay_time")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "weixin_num")
    public String getWeixinNum() {
        return weixinNum;
    }

    public void setWeixinNum(String weixinNum) {
        this.weixinNum = weixinNum;
    }

    @Basic
    @Column(name = "alipay_num")
    public String getAlipayNum() {
        return alipayNum;
    }

    public void setAlipayNum(String alipayNum) {
        this.alipayNum = alipayNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsAccountPay that = (CmsAccountPay) o;

        if (accountPayId != that.accountPayId) return false;
        if (payUserId != that.payUserId) return false;
        if (drawUserId != that.drawUserId) return false;
        if (drawNum != null ? !drawNum.equals(that.drawNum) : that.drawNum != null) return false;
        if (payAccount != null ? !payAccount.equals(that.payAccount) : that.payAccount != null) return false;
        if (drawAccount != null ? !drawAccount.equals(that.drawAccount) : that.drawAccount != null) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;
        if (weixinNum != null ? !weixinNum.equals(that.weixinNum) : that.weixinNum != null) return false;
        if (alipayNum != null ? !alipayNum.equals(that.alipayNum) : that.alipayNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (accountPayId ^ (accountPayId >>> 32));
        result = 31 * result + (drawNum != null ? drawNum.hashCode() : 0);
        result = 31 * result + payUserId;
        result = 31 * result + drawUserId;
        result = 31 * result + (payAccount != null ? payAccount.hashCode() : 0);
        result = 31 * result + (drawAccount != null ? drawAccount.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (weixinNum != null ? weixinNum.hashCode() : 0);
        result = 31 * result + (alipayNum != null ? alipayNum.hashCode() : 0);
        return result;
    }

    private CmsUser payUser;

    private CmsUser drawUser;

    @ManyToOne
    @JoinColumn(name = "pay_user_id",insertable = false,updatable = false)
    public CmsUser getPayUser() {
        return payUser;
    }

    public void setPayUser(CmsUser payUser) {
        this.payUser = payUser;
    }

    @ManyToOne
    @JoinColumn(name = "draw_user_id",insertable = false,updatable = false)
    public CmsUser getDrawUser() {
        return drawUser;
    }

    public void setDrawUser(CmsUser drawUser) {
        this.drawUser = drawUser;
    }
}
