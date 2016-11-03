package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-3
 * @Desc 收费内容配置表
 */
@Entity
@Table(name = "c_content_charge", schema = "db_cms")
public class ContentCharge implements Serializable{
    private static final long serialVersionUID = -870499369012687532L;
    public static final Short MODEL_FREE=0;
    public static final Short MODEL_CHARGE=1;
    public static final Short MODEL_REWARD=2;
    private int contentId;
    private double chargeAmount;
    private double totalAmount;
    private double yearAmount;
    private double monthAmount;
    private double dayAmount;
    private Timestamp lastBuyTime;
    private byte chargeReward;

    @Id
    @Column(name = "content_id")
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "charge_amount")
    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    @Basic
    @Column(name = "total_amount")
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "year_amount")
    public double getYearAmount() {
        return yearAmount;
    }

    public void setYearAmount(double yearAmount) {
        this.yearAmount = yearAmount;
    }

    @Basic
    @Column(name = "month_amount")
    public double getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(double monthAmount) {
        this.monthAmount = monthAmount;
    }

    @Basic
    @Column(name = "day_amount")
    public double getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(double dayAmount) {
        this.dayAmount = dayAmount;
    }

    @Basic
    @Column(name = "last_buy_time")
    public Timestamp getLastBuyTime() {
        return lastBuyTime;
    }

    public void setLastBuyTime(Timestamp lastBuyTime) {
        this.lastBuyTime = lastBuyTime;
    }

    @Basic
    @Column(name = "charge_reward")
    public byte getChargeReward() {
        return chargeReward;
    }

    public void setChargeReward(byte chargeReward) {
        this.chargeReward = chargeReward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentCharge that = (ContentCharge) o;

        if (contentId != that.contentId) return false;
        if (Double.compare(that.chargeAmount, chargeAmount) != 0) return false;
        if (Double.compare(that.totalAmount, totalAmount) != 0) return false;
        if (Double.compare(that.yearAmount, yearAmount) != 0) return false;
        if (Double.compare(that.monthAmount, monthAmount) != 0) return false;
        if (Double.compare(that.dayAmount, dayAmount) != 0) return false;
        if (chargeReward != that.chargeReward) return false;
        if (lastBuyTime != null ? !lastBuyTime.equals(that.lastBuyTime) : that.lastBuyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = contentId;
        temp = Double.doubleToLongBits(chargeAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yearAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(monthAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(dayAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (lastBuyTime != null ? lastBuyTime.hashCode() : 0);
        result = 31 * result + (int) chargeReward;
        return result;
    }
}
