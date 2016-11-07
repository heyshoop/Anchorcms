package com.anchorcms.core.model;

import com.anchorcms.cms.model.main.Channel;
import com.anchorcms.cms.model.main.Content;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-2
 * @Desc 用户实体类
 */
@Entity
@Table(name = "c_user", schema = "db_cms")
public class CmsUser implements Serializable{
    private static final long serialVersionUID = -2256496850453558311L;
    private int userId;
    private int groupId;
    private String username;
    private String email;
    private Serializable registerTime;
    private String registerIp;
    private Date lastLoginTime;
    private String lastLoginIp;
    private int loginCount;
    private Integer rank;
    private Long uploadTotal;
    private Integer uploadSize;
    private Date uploadDate;
    private Boolean isAdmin;
    private Boolean isSelfAdmin;
    private Boolean isDisabled;
    private String sessionId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "register_time")
    public Serializable getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Serializable registerTime) {
        this.registerTime = registerTime;
    }

    @Basic
    @Column(name = "register_ip")
    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    @Basic
    @Column(name = "last_login_time")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "last_login_ip")
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Basic
    @Column(name = "login_count")
    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "upload_total")
    public Long getUploadTotal() {
        return uploadTotal;
    }

    public void setUploadTotal(Long uploadTotal) {
        this.uploadTotal = uploadTotal;
    }

    @Basic
    @Column(name = "upload_size")
    public Integer getUploadSize() {
        return uploadSize;
    }

    public void setUploadSize(Integer uploadSize) {
        this.uploadSize = uploadSize;
    }

    @Basic
    @Column(name = "upload_date")
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Basic
    @Column(name = "is_admin")
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Basic
    @Column(name = "is_self_admin")
    public Boolean getIsSelfAdmin() {
        return isSelfAdmin;
    }

    public void setIsSelfAdmin(Boolean isSelfAdmin) {
        this.isSelfAdmin = isSelfAdmin;
    }

    @Basic
    @Column(name = "is_disabled")
    public Boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(Boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    @Basic
    @Column(name = "session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsUser cmsUser = (CmsUser) o;

        if (userId != cmsUser.userId) return false;
        if (groupId != cmsUser.groupId) return false;
        if (loginCount != cmsUser.loginCount) return false;
        if (rank != cmsUser.rank) return false;
        if (uploadTotal != cmsUser.uploadTotal) return false;
        if (uploadSize != cmsUser.uploadSize) return false;
        if (isAdmin != cmsUser.isAdmin) return false;
        if (isSelfAdmin != cmsUser.isSelfAdmin) return false;
        if (isDisabled != cmsUser.isDisabled) return false;
        if (username != null ? !username.equals(cmsUser.username) : cmsUser.username != null) return false;
        if (email != null ? !email.equals(cmsUser.email) : cmsUser.email != null) return false;
        if (registerTime != null ? !registerTime.equals(cmsUser.registerTime) : cmsUser.registerTime != null) return false;
        if (registerIp != null ? !registerIp.equals(cmsUser.registerIp) : cmsUser.registerIp != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(cmsUser.lastLoginTime) : cmsUser.lastLoginTime != null)
            return false;
        if (lastLoginIp != null ? !lastLoginIp.equals(cmsUser.lastLoginIp) : cmsUser.lastLoginIp != null) return false;
        if (uploadDate != null ? !uploadDate.equals(cmsUser.uploadDate) : cmsUser.uploadDate != null) return false;
        if (sessionId != null ? !sessionId.equals(cmsUser.sessionId) : cmsUser.sessionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + groupId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (registerTime != null ? registerTime.hashCode() : 0);
        result = 31 * result + (registerIp != null ? registerIp.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (lastLoginIp != null ? lastLoginIp.hashCode() : 0);
        result = 31 * result + loginCount;
        result = 31 * result + rank;
        result = 31 * result + (int) (uploadTotal ^ (uploadTotal >>> 32));
        result = 31 * result + uploadSize;
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (isAdmin != true ? 0 :1);
        result = 31 * result + (isSelfAdmin != true ? 0 :1);
        result = 31 * result + (isDisabled != true ? 0 :1);
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        return result;
    }

    private Map<String, String> attr;
    @OneToMany /*一对多*/
    private Set<CmsRole> roles;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
    @JoinColumn(name = "group_id")
    private CmsGroup group;
    @Transient /*标注为非数据库字段*/
    public Map<String, String> getAttr() {
        return attr;
    }
    @OneToMany
    private Set<CmsUserExt> userExtSet;
    @ManyToMany
    private Set<Channel> channels;
    @OneToMany
    private Set<Content> collectContents;
    @OneToMany
    private Set<CmsUserSite> userSites;
    @Transient
    private Boolean selfAdmin;

    public Boolean getSelfAdmin() {
        return selfAdmin;
    }

    public void setSelfAdmin(Boolean selfAdmin) {
        this.selfAdmin = selfAdmin;
    }

    public Set<CmsUserSite> getUserSites() {
        return userSites;
    }

    public void setUserSites(Set<CmsUserSite> userSites) {
        this.userSites = userSites;
    }

    public Set<Content> getCollectContents() {
        return collectContents;
    }

    public void setCollectContents(Set<Content> collectContents) {
        this.collectContents = collectContents;
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    public Set<CmsUserExt> getUserExtSet() {
        return userExtSet;
    }

    public Set<CmsRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<CmsRole> roles) {
        this.roles = roles;
    }

    public void setUserExtSet(Set<CmsUserExt> userExtSet) {
        this.userExtSet = userExtSet;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public CmsGroup getGroup() {
        return group;
    }

    public void setGroup(CmsGroup group) {
        this.group = group;
    }

    public void forMember(UnifiedUser u) {
        forUser(u);
        setIsAdmin(false);
        setRank(0);
        setIsSelfAdmin(false);
    }
    public void forAdmin(UnifiedUser u, boolean viewonly, boolean selfAdmin,int rank) {
        forUser(u);
        setIsAdmin(true);
        setRank(rank);
        setIsSelfAdmin(selfAdmin);
    }
    public void forUser(UnifiedUser u) {
        setIsDisabled(false);
        setUserId(u.getUserId());
        setUsername(u.getUsername());
        setEmail(u.getEmail());
        setRegisterIp(u.getRegisterIp());
        setRegisterTime(u.getRegisterTime());
        setLastLoginIp(u.getLastLoginIp());
        setLastLoginTime(u.getLastLoginTime());
        setLoginCount(0);
    }
    /**
     * @Author 阁楼麻雀
     * @Date 2016-11-2 14:56
     * @Desc 初始化
     */
    public void init() {
        if (getUploadTotal() == null) {
            setUploadTotal(0L);
        }
        if (getUploadSize() == null) {
            setUploadSize(0);
        }
        if (getUploadDate() == null) {
            setUploadDate(new java.sql.Date(System.currentTimeMillis()));
        }
        if (getIsAdmin() == null) {
            setIsAdmin(false);
        }
        if (getRank() == null) {
            setRank(0);
        }
        if (getIsSelfAdmin() == null) {
            setIsSelfAdmin(false);
        }
        if (getIsDisabled() == null) {
            setIsDisabled(false);
        }
    }

    /**
     * 是否是今天。根据System.currentTimeMillis() / 1000 / 60 / 60 / 24计算。
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        long day = date.getTime() / 1000 / 60 / 60 / 24;
        long currentDay = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
        return day==(currentDay-1);
    }

    public void addToRoles(CmsRole role) {
        if (role == null) {
            return;
        }
        Set<CmsRole> set = getRoles();
        if (set == null) {
            set = new HashSet<CmsRole>();
            setRoles(set);
        }
        set.add(role);
    }

    public void addToChannels(Channel channel) {
        if (channel == null) {
            return;
        }
        Set<Channel> set = getChannels();
        if (set == null) {
            set = new HashSet<Channel>();
            setChannels(set);
        }
        set.add(channel);
    }

    public CmsUserExt getUserExt() {
        Set<CmsUserExt> set = getUserExtSet();
        if (set != null && set.size() > 0) {
            return set.iterator().next();
        } else {
            return null;
        }
    }

    public void addToCollection(Content content) {
        if (content == null) {
            return;
        }
        Set<Content> set =getCollectContents();
        if (set == null) {
            set = new HashSet<Content>();
            setCollectContents(set);
        }
        set.add(content);
    }
    public void delFromCollection(Content content) {
        if (content == null) {
            return;
        }
        Set<Content> set =getCollectContents();
        if (set == null) {
            return;
        }else{
            set.remove(content);
        }
    }
    public void clearCollection() {
        getCollectContents().clear();
    }

    public CmsUserSite getUserSite(Integer siteId) {
        Set<CmsUserSite> set = getUserSites();
        for (CmsUserSite us : set) {
            if (us.getSite().getSiteId().equals(siteId)) {
                return us;
            }
        }
        return null;
    }
    public Byte getCheckStep(Integer siteId) {
        CmsUserSite us = getUserSite(siteId);
        if (us != null) {
            return getUserSite(siteId).getCheckStep();
        } else {
            return null;
        }
    }
    public Set<String> getPerms(Integer siteId,Set<String>perms) {
        if(getIsDisabled()){
            return null;
        }
        Set<CmsUserSite> userSits=getUserSites();
        if(userSits==null){
            return null;
        }
        Set<CmsRole> roles = getRoles();
        if (roles == null) {
            return null;
        }
        boolean hasSitePermission=false;
        for(CmsUserSite cus:userSits){
            if(cus.getSite().getSiteId().equals(siteId)){
                hasSitePermission=true;
            }
        }
        if(!hasSitePermission){
            return null;
        }
        boolean isSuper = false;
        Set<String> allPerms = new HashSet<String>();
        for (CmsRole role : getRoles()) {
            if(role.getIsSuper()){
                isSuper=true;
                break;
            }
            allPerms.addAll(role.getPerms());
        }
        if (isSuper) {
            allPerms.clear();
            allPerms.add("*");
        }
        return allPerms;
    }
}