package com.anchorcms.core.model;

import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

import static com.anchorcms.common.constants.Constants.RES_PATH;
import static com.anchorcms.common.constants.Constants.TPL_BASE;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-2
 * @Desc CMS站点信息实体类
 */
@Entity
@Table(name = "c_site", schema = "db_cms")
public class CmsSite implements Serializable {
    private static final long serialVersionUID = -5898275025484020911L;
    private Integer siteId;
    private String domain;
    private String sitePath;
    private String siteName;
    private String shortName;
    private String protocol;
    private String dynamicSuffix;
    private String staticSuffix;
    private String staticDir;
    private String isIndexToRoot;
    private String isStaticIndex;
    private String localeAdmin;
    private String localeFront;
    private String tplSolution;
    private Byte finalStep;
    private byte afterCheck;
    private String isRelativePath;
    private String isRecycleOn;
    private String domainAlias;
    private String domainRedirect;
    private String tplIndex;
    private String keywords;
    private String description;
    private String tplMobileSolution;
    private String mobileStaticDir;
    private byte mobileStaticSync;
    private Integer ftpSyncPageId;
    private byte pageIsSyncFtp;
    private Boolean resouceIsSyncFtp;

    @Id
    @Column(name = "site_id")
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "site_path")
    public String getSitePath() {
        return sitePath;
    }

    public void setSitePath(String sitePath) {
        this.sitePath = sitePath;
    }

    @Basic
    @Column(name = "site_name")
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    @Basic
    @Column(name = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "protocol")
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Basic
    @Column(name = "dynamic_suffix")
    public String getDynamicSuffix() {
        return dynamicSuffix;
    }

    public void setDynamicSuffix(String dynamicSuffix) {
        this.dynamicSuffix = dynamicSuffix;
    }

    @Basic
    @Column(name = "static_suffix")
    public String getStaticSuffix() {
        return staticSuffix;
    }

    public void setStaticSuffix(String staticSuffix) {
        this.staticSuffix = staticSuffix;
    }

    @Basic
    @Column(name = "static_dir")
    public String getStaticDir() {
        return staticDir;
    }

    public void setStaticDir(String staticDir) {
        this.staticDir = staticDir;
    }

    @Basic
    @Column(name = "is_index_to_root")
    public String getIsIndexToRoot() {
        return isIndexToRoot;
    }

    public void setIsIndexToRoot(String isIndexToRoot) {
        this.isIndexToRoot = isIndexToRoot;
    }

    @Basic
    @Column(name = "is_static_index")
    public String getIsStaticIndex() {
        return isStaticIndex;
    }

    public void setIsStaticIndex(String isStaticIndex) {
        this.isStaticIndex = isStaticIndex;
    }

    @Basic
    @Column(name = "locale_admin")
    public String getLocaleAdmin() {
        return localeAdmin;
    }

    public void setLocaleAdmin(String localeAdmin) {
        this.localeAdmin = localeAdmin;
    }

    @Basic
    @Column(name = "locale_front")
    public String getLocaleFront() {
        return localeFront;
    }

    public void setLocaleFront(String localeFront) {
        this.localeFront = localeFront;
    }

    @Basic
    @Column(name = "tpl_solution")
    public String getTplSolution() {
        return tplSolution;
    }

    public void setTplSolution(String tplSolution) {
        this.tplSolution = tplSolution;
    }

    @Basic
    @Column(name = "final_step")
    public Byte getFinalStep() {
        return finalStep;
    }

    public void setFinalStep(Byte finalStep) {
        this.finalStep = finalStep;
    }

    @Basic
    @Column(name = "after_check")
    public byte getAfterCheck() {
        return afterCheck;
    }

    public void setAfterCheck(byte afterCheck) {
        this.afterCheck = afterCheck;
    }

    @Basic
    @Column(name = "is_relative_path")
    public String getIsRelativePath() {
        return isRelativePath;
    }

    public void setIsRelativePath(String isRelativePath) {
        this.isRelativePath = isRelativePath;
    }

    @Basic
    @Column(name = "is_recycle_on")
    public String getIsRecycleOn() {
        return isRecycleOn;
    }

    public void setIsRecycleOn(String isRecycleOn) {
        this.isRecycleOn = isRecycleOn;
    }

    @Basic
    @Column(name = "domain_alias")
    public String getDomainAlias() {
        return domainAlias;
    }

    public void setDomainAlias(String domainAlias) {
        this.domainAlias = domainAlias;
    }

    @Basic
    @Column(name = "domain_redirect")
    public String getDomainRedirect() {
        return domainRedirect;
    }

    public void setDomainRedirect(String domainRedirect) {
        this.domainRedirect = domainRedirect;
    }

    @Basic
    @Column(name = "tpl_index")
    public String getTplIndex() {
        return tplIndex;
    }

    public void setTplIndex(String tplIndex) {
        this.tplIndex = tplIndex;
    }

    @Basic
    @Column(name = "keywords")
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "tpl_mobile_solution")
    public String getTplMobileSolution() {
        return tplMobileSolution;
    }

    public void setTplMobileSolution(String tplMobileSolution) {
        this.tplMobileSolution = tplMobileSolution;
    }

    @Basic
    @Column(name = "mobile_static_dir")
    public String getMobileStaticDir() {
        return mobileStaticDir;
    }

    public void setMobileStaticDir(String mobileStaticDir) {
        this.mobileStaticDir = mobileStaticDir;
    }

    @Basic
    @Column(name = "mobile_static_sync")
    public byte getMobileStaticSync() {
        return mobileStaticSync;
    }

    public void setMobileStaticSync(byte mobileStaticSync) {
        this.mobileStaticSync = mobileStaticSync;
    }

    @Basic
    @Column(name = "ftp_sync_page_id")
    public Integer getFtpSyncPageId() {
        return ftpSyncPageId;
    }

    public void setFtpSyncPageId(Integer ftpSyncPageId) {
        this.ftpSyncPageId = ftpSyncPageId;
    }

    @Basic
    @Column(name = "page_is_sync_ftp")
    public byte getPageIsSyncFtp() {
        return pageIsSyncFtp;
    }

    public void setPageIsSyncFtp(byte pageIsSyncFtp) {
        this.pageIsSyncFtp = pageIsSyncFtp;
    }

    @Basic
    @Column(name = "resouce_is_sync_ftp")
    public Boolean getResouceIsSyncFtp() {
        return resouceIsSyncFtp;
    }

    public void setResouceIsSyncFtp(Boolean resouceIsSyncFtp) {
        this.resouceIsSyncFtp = resouceIsSyncFtp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsSite cmsSite = (CmsSite) o;

        if (siteId != cmsSite.siteId) return false;
        if (finalStep != cmsSite.finalStep) return false;
        if (afterCheck != cmsSite.afterCheck) return false;
        if (mobileStaticSync != cmsSite.mobileStaticSync) return false;
        if (pageIsSyncFtp != cmsSite.pageIsSyncFtp) return false;
        if (resouceIsSyncFtp != cmsSite.resouceIsSyncFtp) return false;
        if (domain != null ? !domain.equals(cmsSite.domain) : cmsSite.domain != null) return false;
        if (sitePath != null ? !sitePath.equals(cmsSite.sitePath) : cmsSite.sitePath != null) return false;
        if (siteName != null ? !siteName.equals(cmsSite.siteName) : cmsSite.siteName != null) return false;
        if (shortName != null ? !shortName.equals(cmsSite.shortName) : cmsSite.shortName != null) return false;
        if (protocol != null ? !protocol.equals(cmsSite.protocol) : cmsSite.protocol != null) return false;
        if (dynamicSuffix != null ? !dynamicSuffix.equals(cmsSite.dynamicSuffix) : cmsSite.dynamicSuffix != null)
            return false;
        if (staticSuffix != null ? !staticSuffix.equals(cmsSite.staticSuffix) : cmsSite.staticSuffix != null) return false;
        if (staticDir != null ? !staticDir.equals(cmsSite.staticDir) : cmsSite.staticDir != null) return false;
        if (isIndexToRoot != null ? !isIndexToRoot.equals(cmsSite.isIndexToRoot) : cmsSite.isIndexToRoot != null)
            return false;
        if (isStaticIndex != null ? !isStaticIndex.equals(cmsSite.isStaticIndex) : cmsSite.isStaticIndex != null)
            return false;
        if (localeAdmin != null ? !localeAdmin.equals(cmsSite.localeAdmin) : cmsSite.localeAdmin != null) return false;
        if (localeFront != null ? !localeFront.equals(cmsSite.localeFront) : cmsSite.localeFront != null) return false;
        if (tplSolution != null ? !tplSolution.equals(cmsSite.tplSolution) : cmsSite.tplSolution != null) return false;
        if (isRelativePath != null ? !isRelativePath.equals(cmsSite.isRelativePath) : cmsSite.isRelativePath != null)
            return false;
        if (isRecycleOn != null ? !isRecycleOn.equals(cmsSite.isRecycleOn) : cmsSite.isRecycleOn != null) return false;
        if (domainAlias != null ? !domainAlias.equals(cmsSite.domainAlias) : cmsSite.domainAlias != null) return false;
        if (domainRedirect != null ? !domainRedirect.equals(cmsSite.domainRedirect) : cmsSite.domainRedirect != null)
            return false;
        if (tplIndex != null ? !tplIndex.equals(cmsSite.tplIndex) : cmsSite.tplIndex != null) return false;
        if (keywords != null ? !keywords.equals(cmsSite.keywords) : cmsSite.keywords != null) return false;
        if (description != null ? !description.equals(cmsSite.description) : cmsSite.description != null) return false;
        if (tplMobileSolution != null ? !tplMobileSolution.equals(cmsSite.tplMobileSolution) : cmsSite.tplMobileSolution != null)
            return false;
        if (mobileStaticDir != null ? !mobileStaticDir.equals(cmsSite.mobileStaticDir) : cmsSite.mobileStaticDir != null)
            return false;
        if (ftpSyncPageId != null ? !ftpSyncPageId.equals(cmsSite.ftpSyncPageId) : cmsSite.ftpSyncPageId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = siteId;
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (sitePath != null ? sitePath.hashCode() : 0);
        result = 31 * result + (siteName != null ? siteName.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        result = 31 * result + (dynamicSuffix != null ? dynamicSuffix.hashCode() : 0);
        result = 31 * result + (staticSuffix != null ? staticSuffix.hashCode() : 0);
        result = 31 * result + (staticDir != null ? staticDir.hashCode() : 0);
        result = 31 * result + (isIndexToRoot != null ? isIndexToRoot.hashCode() : 0);
        result = 31 * result + (isStaticIndex != null ? isStaticIndex.hashCode() : 0);
        result = 31 * result + (localeAdmin != null ? localeAdmin.hashCode() : 0);
        result = 31 * result + (localeFront != null ? localeFront.hashCode() : 0);
        result = 31 * result + (tplSolution != null ? tplSolution.hashCode() : 0);
        result = 31 * result + (int) finalStep;
        result = 31 * result + (int) afterCheck;
        result = 31 * result + (isRelativePath != null ? isRelativePath.hashCode() : 0);
        result = 31 * result + (isRecycleOn != null ? isRecycleOn.hashCode() : 0);
        result = 31 * result + (domainAlias != null ? domainAlias.hashCode() : 0);
        result = 31 * result + (domainRedirect != null ? domainRedirect.hashCode() : 0);
        result = 31 * result + (tplIndex != null ? tplIndex.hashCode() : 0);
        result = 31 * result + (keywords != null ? keywords.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tplMobileSolution != null ? tplMobileSolution.hashCode() : 0);
        result = 31 * result + (mobileStaticDir != null ? mobileStaticDir.hashCode() : 0);
        result = 31 * result + (int) mobileStaticSync;
        result = 31 * result + (ftpSyncPageId != null ? ftpSyncPageId.hashCode() : 0);
        result = 31 * result + (int) pageIsSyncFtp;
        return result;
    }
    @ManyToOne
    private Ftp uploadFtp;
    @ManyToOne
    private Ftp syncPageFtp;
    @Transient
    private Map<String,String> attr;
    @OneToOne
    private CmsSiteCompany siteCompany;
    @ManyToOne
    private CmsConfig config;

    public CmsConfig getConfig() {
        return config;
    }

    public void setConfig(CmsConfig config) {
        this.config = config;
    }

    public CmsSiteCompany getSiteCompany() {
        return siteCompany;
    }

    public void setSiteCompany(CmsSiteCompany siteCompany) {
        this.siteCompany = siteCompany;
    }

    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }

    public Ftp getSyncPageFtp() {
        return syncPageFtp;
    }

    public void setSyncPageFtp(Ftp syncPageFtp) {
        this.syncPageFtp = syncPageFtp;
    }

    public Ftp getUploadFtp() {
        return uploadFtp;
    }

    public void setUploadFtp(Ftp uploadFtp) {
        this.uploadFtp = uploadFtp;
    }
    public void init() {
        if (StringUtils.isBlank(getProtocol())) {
            setProtocol("http://");
        }
        if (StringUtils.isBlank(getTplSolution())) {
            //默认路径名作为方案名
            setTplSolution(getSitePath());
            //setTplSolution(DEFAULT);
        }
        if (StringUtils.isBlank(getTplMobileSolution())) {
            //默认路径名作为方案名
            setTplMobileSolution(getSitePath());
        }
        if (getFinalStep() == null) {
            byte step = 2;
            setFinalStep(step);
        }
    }
    public String getTplPath() {
        return TPL_BASE + "/" + getSitePath();
    }
    /**
     * 获得模板资源路径。如：/r/cms/www
     *
     * @return
     */
    public String getResPath() {
        return RES_PATH + "/" + getSitePath();
    }
    public String getMobileSolutionPath() {
        return TPL_BASE + "/" + getSitePath() + "/" + getTplMobileSolution();
    }
    public String getSolutionPath() {
        return TPL_BASE + "/" + getSitePath() + "/" + getTplSolution();
    }
    public String getLoginUrl() {
        CmsConfig config = getConfig();
        if (config != null) {
            return config.getLoginUrl();
        } else {
            return null;
        }
    }
    public String getProcessUrl() {
        CmsConfig config = getConfig();
        if (config != null) {
            return config.getProcessUrl();
        } else {
            return null;
        }
    }
    public String getContextPath() {
        CmsConfig config = getConfig();
        if (config != null) {
            return config.getContextPath();
        } else {
            return null;
        }
    }
}
