package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-16
 * @Desc 第三方平台账号登陆
 */
@Entity
@Table(name = "c_third_account")
public class CmsThirdAccount implements Serializable{
    private static final long serialVersionUID = -3524232764331416097L;
    private long accountId;
    private String accountKey;
    private String username;
    private String source;

    @Id
    @Column(name = "account_id")
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    @Basic
    @Column(name = "account_key")
    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsThirdAccount that = (CmsThirdAccount) o;

        if (accountId != that.accountId) return false;
        if (accountKey != null ? !accountKey.equals(that.accountKey) : that.accountKey != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (source != null ? !source.equals(that.source) : that.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (accountKey != null ? accountKey.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        return result;
    }
    public static final String QQ_KEY="openId";
    public static final String SINA_KEY="uid";
    public static final String QQ_PLAT="QQ";
    public static final String SINA_PLAT="SINA";
    public static final String QQ_WEBO_KEY="weboOpenId";
    public static final String QQ_WEBO_PLAT="QQWEBO";
}
