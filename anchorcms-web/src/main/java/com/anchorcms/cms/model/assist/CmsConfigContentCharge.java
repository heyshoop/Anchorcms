package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/23
 * @Desc 收费内容
 */
@Entity
@Table(name = "c_config_content_charge")
public class CmsConfigContentCharge implements Serializable{
    private static final long serialVersionUID = -3204285504030959950L;
    private int configContentId;
    private String weixinAppid;
    private String weixinSecret;
    private String weixinAccount;
    private String weixinPassword;
    private String alipayPartnerId;
    private String alipayAccount;
    private String alipayKey;
    private String alipayAppid;
    private String alipayPublicKey;
    private String alipayPrivateKey;
    private double chargeRatio;
    private double minDrawAmount;
    private double commissionTotal;
    private double commissionYear;
    private double commissionMonth;
    private double commissionDay;
    private Date lastBuyTime;
    private String payTransferPassword;
    private String transferApiPassword;
    private double rewardMin;
    private double rewardMax;

    @Id
    @Column(name = "config_content_id")
    public int getConfigContentId() {
        return configContentId;
    }

    public void setConfigContentId(int configContentId) {
        this.configContentId = configContentId;
    }

    @Basic
    @Column(name = "weixin_appid")
    public String getWeixinAppid() {
        return weixinAppid;
    }

    public void setWeixinAppid(String weixinAppid) {
        this.weixinAppid = weixinAppid;
    }

    @Basic
    @Column(name = "weixin_secret")
    public String getWeixinSecret() {
        return weixinSecret;
    }

    public void setWeixinSecret(String weixinSecret) {
        this.weixinSecret = weixinSecret;
    }

    @Basic
    @Column(name = "weixin_account")
    public String getWeixinAccount() {
        return weixinAccount;
    }

    public void setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
    }

    @Basic
    @Column(name = "weixin_password")
    public String getWeixinPassword() {
        return weixinPassword;
    }

    public void setWeixinPassword(String weixinPassword) {
        this.weixinPassword = weixinPassword;
    }

    @Basic
    @Column(name = "alipay_partner_id")
    public String getAlipayPartnerId() {
        return alipayPartnerId;
    }

    public void setAlipayPartnerId(String alipayPartnerId) {
        this.alipayPartnerId = alipayPartnerId;
    }

    @Basic
    @Column(name = "alipay_account")
    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    @Basic
    @Column(name = "alipay_key")
    public String getAlipayKey() {
        return alipayKey;
    }

    public void setAlipayKey(String alipayKey) {
        this.alipayKey = alipayKey;
    }

    @Basic
    @Column(name = "alipay_appid")
    public String getAlipayAppid() {
        return alipayAppid;
    }

    public void setAlipayAppid(String alipayAppid) {
        this.alipayAppid = alipayAppid;
    }

    @Basic
    @Column(name = "alipay_public_key")
    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    @Basic
    @Column(name = "alipay_private_key")
    public String getAlipayPrivateKey() {
        return alipayPrivateKey;
    }

    public void setAlipayPrivateKey(String alipayPrivateKey) {
        this.alipayPrivateKey = alipayPrivateKey;
    }

    @Basic
    @Column(name = "charge_ratio")
    public double getChargeRatio() {
        return chargeRatio;
    }

    public void setChargeRatio(double chargeRatio) {
        this.chargeRatio = chargeRatio;
    }

    @Basic
    @Column(name = "min_draw_amount")
    public double getMinDrawAmount() {
        return minDrawAmount;
    }

    public void setMinDrawAmount(double minDrawAmount) {
        this.minDrawAmount = minDrawAmount;
    }

    @Basic
    @Column(name = "commission_total")
    public double getCommissionTotal() {
        return commissionTotal;
    }

    public void setCommissionTotal(double commissionTotal) {
        this.commissionTotal = commissionTotal;
    }

    @Basic
    @Column(name = "commission_year")
    public double getCommissionYear() {
        return commissionYear;
    }

    public void setCommissionYear(double commissionYear) {
        this.commissionYear = commissionYear;
    }

    @Basic
    @Column(name = "commission_month")
    public double getCommissionMonth() {
        return commissionMonth;
    }

    public void setCommissionMonth(double commissionMonth) {
        this.commissionMonth = commissionMonth;
    }

    @Basic
    @Column(name = "commission_day")
    public double getCommissionDay() {
        return commissionDay;
    }

    public void setCommissionDay(double commissionDay) {
        this.commissionDay = commissionDay;
    }

    @Basic
    @Column(name = "last_buy_time")
    public Date getLastBuyTime() {
        return lastBuyTime;
    }

    public void setLastBuyTime(Date lastBuyTime) {
        this.lastBuyTime = lastBuyTime;
    }

    @Basic
    @Column(name = "pay_transfer_password")
    public String getPayTransferPassword() {
        return payTransferPassword;
    }

    public void setPayTransferPassword(String payTransferPassword) {
        this.payTransferPassword = payTransferPassword;
    }

    @Basic
    @Column(name = "transfer_api_password")
    public String getTransferApiPassword() {
        return transferApiPassword;
    }

    public void setTransferApiPassword(String transferApiPassword) {
        this.transferApiPassword = transferApiPassword;
    }

    @Basic
    @Column(name = "reward_min")
    public double getRewardMin() {
        return rewardMin;
    }

    public void setRewardMin(double rewardMin) {
        this.rewardMin = rewardMin;
    }

    @Basic
    @Column(name = "reward_max")
    public double getRewardMax() {
        return rewardMax;
    }

    public void setRewardMax(double rewardMax) {
        this.rewardMax = rewardMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsConfigContentCharge that = (CmsConfigContentCharge) o;

        if (configContentId != that.configContentId) return false;
        if (Double.compare(that.chargeRatio, chargeRatio) != 0) return false;
        if (Double.compare(that.minDrawAmount, minDrawAmount) != 0) return false;
        if (Double.compare(that.commissionTotal, commissionTotal) != 0) return false;
        if (Double.compare(that.commissionYear, commissionYear) != 0) return false;
        if (Double.compare(that.commissionMonth, commissionMonth) != 0) return false;
        if (Double.compare(that.commissionDay, commissionDay) != 0) return false;
        if (Double.compare(that.rewardMin, rewardMin) != 0) return false;
        if (Double.compare(that.rewardMax, rewardMax) != 0) return false;
        if (weixinAppid != null ? !weixinAppid.equals(that.weixinAppid) : that.weixinAppid != null) return false;
        if (weixinSecret != null ? !weixinSecret.equals(that.weixinSecret) : that.weixinSecret != null) return false;
        if (weixinAccount != null ? !weixinAccount.equals(that.weixinAccount) : that.weixinAccount != null)
            return false;
        if (weixinPassword != null ? !weixinPassword.equals(that.weixinPassword) : that.weixinPassword != null)
            return false;
        if (alipayPartnerId != null ? !alipayPartnerId.equals(that.alipayPartnerId) : that.alipayPartnerId != null)
            return false;
        if (alipayAccount != null ? !alipayAccount.equals(that.alipayAccount) : that.alipayAccount != null)
            return false;
        if (alipayKey != null ? !alipayKey.equals(that.alipayKey) : that.alipayKey != null) return false;
        if (alipayAppid != null ? !alipayAppid.equals(that.alipayAppid) : that.alipayAppid != null) return false;
        if (alipayPublicKey != null ? !alipayPublicKey.equals(that.alipayPublicKey) : that.alipayPublicKey != null)
            return false;
        if (alipayPrivateKey != null ? !alipayPrivateKey.equals(that.alipayPrivateKey) : that.alipayPrivateKey != null)
            return false;
        if (lastBuyTime != null ? !lastBuyTime.equals(that.lastBuyTime) : that.lastBuyTime != null) return false;
        if (payTransferPassword != null ? !payTransferPassword.equals(that.payTransferPassword) : that.payTransferPassword != null)
            return false;
        if (transferApiPassword != null ? !transferApiPassword.equals(that.transferApiPassword) : that.transferApiPassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = configContentId;
        result = 31 * result + (weixinAppid != null ? weixinAppid.hashCode() : 0);
        result = 31 * result + (weixinSecret != null ? weixinSecret.hashCode() : 0);
        result = 31 * result + (weixinAccount != null ? weixinAccount.hashCode() : 0);
        result = 31 * result + (weixinPassword != null ? weixinPassword.hashCode() : 0);
        result = 31 * result + (alipayPartnerId != null ? alipayPartnerId.hashCode() : 0);
        result = 31 * result + (alipayAccount != null ? alipayAccount.hashCode() : 0);
        result = 31 * result + (alipayKey != null ? alipayKey.hashCode() : 0);
        result = 31 * result + (alipayAppid != null ? alipayAppid.hashCode() : 0);
        result = 31 * result + (alipayPublicKey != null ? alipayPublicKey.hashCode() : 0);
        result = 31 * result + (alipayPrivateKey != null ? alipayPrivateKey.hashCode() : 0);
        temp = Double.doubleToLongBits(chargeRatio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(minDrawAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(commissionTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(commissionYear);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(commissionMonth);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(commissionDay);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (lastBuyTime != null ? lastBuyTime.hashCode() : 0);
        result = 31 * result + (payTransferPassword != null ? payTransferPassword.hashCode() : 0);
        result = 31 * result + (transferApiPassword != null ? transferApiPassword.hashCode() : 0);
        temp = Double.doubleToLongBits(rewardMin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rewardMax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
