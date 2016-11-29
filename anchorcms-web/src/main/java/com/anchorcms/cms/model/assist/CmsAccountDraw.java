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
 * @Desc 用户账户提现申请
 */
@Entity
@Table(name = "c_account_draw")
public class CmsAccountDraw implements Serializable{
    private static final long serialVersionUID = -5418511100259501185L;
    private int accountDrawId;
    private int drawUserId;
    private String applyAccount;
    private double applyAmount;
    private Short applyStatus;
    private Integer accountPayId;
    private Date applyTime;

    @Id
    @Column(name = "account_draw_id")
    public int getAccountDrawId() {
        return accountDrawId;
    }

    public void setAccountDrawId(int accountDrawId) {
        this.accountDrawId = accountDrawId;
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
    @Column(name = "apply_account")
    public String getApplyAccount() {
        return applyAccount;
    }

    public void setApplyAccount(String applyAccount) {
        this.applyAccount = applyAccount;
    }

    @Basic
    @Column(name = "apply_amount")
    public double getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(double applyAmount) {
        this.applyAmount = applyAmount;
    }

    @Basic
    @Column(name = "apply_status")
    public Short getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Short applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Basic
    @Column(name = "account_pay_id")
    public Integer getAccountPayId() {
        return accountPayId;
    }

    public void setAccountPayId(Integer accountPayId) {
        this.accountPayId = accountPayId;
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

        CmsAccountDraw that = (CmsAccountDraw) o;

        if (accountDrawId != that.accountDrawId) return false;
        if (drawUserId != that.drawUserId) return false;
        if (Double.compare(that.applyAmount, applyAmount) != 0) return false;
        if (applyStatus != that.applyStatus) return false;
        if (applyAccount != null ? !applyAccount.equals(that.applyAccount) : that.applyAccount != null) return false;
        if (accountPayId != null ? !accountPayId.equals(that.accountPayId) : that.accountPayId != null) return false;
        if (applyTime != null ? !applyTime.equals(that.applyTime) : that.applyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accountDrawId;
        result = 31 * result + drawUserId;
        result = 31 * result + (applyAccount != null ? applyAccount.hashCode() : 0);
        temp = Double.doubleToLongBits(applyAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) applyStatus;
        result = 31 * result + (accountPayId != null ? accountPayId.hashCode() : 0);
        result = 31 * result + (applyTime != null ? applyTime.hashCode() : 0);
        return result;
    }
    private CmsUser drawUser;

    private CmsAccountPay accountPay;

    @ManyToOne
    @JoinColumn(name = "account_pay_id",insertable = false,updatable = false)
    public CmsAccountPay getAccountPay() {
        return accountPay;
    }

    public void setAccountPay(CmsAccountPay accountPay) {
        this.accountPay = accountPay;
    }
    @ManyToOne
    @JoinColumn(name = "draw_user_id",insertable = false,updatable = false)
    public CmsUser getDrawUser() {
        return drawUser;
    }

    public void setDrawUser(CmsUser drawUser) {
        this.drawUser = drawUser;
    }

    public static final Short CHECKING = 0;
    public static final Short CHECKED_SUCC = 1;
    public static final Short CHECKED_FAIL = 2;
    public static final Short DRAW_SUCC = 3;
}
