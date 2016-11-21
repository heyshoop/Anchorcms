package com.anchorcms.cms.model.main;

import com.anchorcms.core.model.CmsSite;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;

import static com.anchorcms.common.constants.Constants.*;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-5
 * @Desc CMS模型表
 */
@Entity
@Table(name = "c_model")
public class CmsModel implements Serializable{
    private static final long serialVersionUID = -4132299743911946924L;
    private Integer modelId;
    private String modelName;
    private String modelPath;
    private String tplChannelPrefix;
    private String tplContentPrefix;
    private int titleImgWidth;
    private int titleImgHeight;
    private int contentImgWidth;
    private int contentImgHeight;
    private int priority;
    private Boolean hasContent;
    private Boolean isDisabled;
    private Boolean isDef;
    private Boolean isGlobal;
    private Integer siteId;

    @Id
    @Column(name = "model_id")
    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    @Basic
    @Column(name = "model_name")
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Basic
    @Column(name = "model_path")
    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    @Basic
    @Column(name = "tpl_channel_prefix")
    public String getTplChannelPrefix() {
        return tplChannelPrefix;
    }

    public void setTplChannelPrefix(String tplChannelPrefix) {
        this.tplChannelPrefix = tplChannelPrefix;
    }

    @Basic
    @Column(name = "tpl_content_prefix")
    public String getTplContentPrefix() {
        return tplContentPrefix;
    }

    public void setTplContentPrefix(String tplContentPrefix) {
        this.tplContentPrefix = tplContentPrefix;
    }

    @Basic
    @Column(name = "title_img_width")
    public int getTitleImgWidth() {
        return titleImgWidth;
    }

    public void setTitleImgWidth(int titleImgWidth) {
        this.titleImgWidth = titleImgWidth;
    }

    @Basic
    @Column(name = "title_img_height")
    public int getTitleImgHeight() {
        return titleImgHeight;
    }

    public void setTitleImgHeight(int titleImgHeight) {
        this.titleImgHeight = titleImgHeight;
    }

    @Basic
    @Column(name = "content_img_width")
    public int getContentImgWidth() {
        return contentImgWidth;
    }

    public void setContentImgWidth(int contentImgWidth) {
        this.contentImgWidth = contentImgWidth;
    }

    @Basic
    @Column(name = "content_img_height")
    public int getContentImgHeight() {
        return contentImgHeight;
    }

    public void setContentImgHeight(int contentImgHeight) {
        this.contentImgHeight = contentImgHeight;
    }

    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "has_content")
    public Boolean getHasContent() {
        return hasContent;
    }

    public void setHasContent(Boolean hasContent) {
        this.hasContent = hasContent;
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
    @Column(name = "is_def")
    public Boolean getIsDef() {
        return isDef;
    }

    public void setIsDef(Boolean isDef) {
        this.isDef = isDef;
    }

    @Basic
    @Column(name = "is_global")
    public Boolean getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(Boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    @Basic
    @Column(name = "site_id")
    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsModel cmsModel = (CmsModel) o;

        if (modelId != cmsModel.modelId) return false;
        if (titleImgWidth != cmsModel.titleImgWidth) return false;
        if (titleImgHeight != cmsModel.titleImgHeight) return false;
        if (contentImgWidth != cmsModel.contentImgWidth) return false;
        if (contentImgHeight != cmsModel.contentImgHeight) return false;
        if (priority != cmsModel.priority) return false;
        if (hasContent != cmsModel.hasContent) return false;
        if (isDisabled != cmsModel.isDisabled) return false;
        if (isDef != cmsModel.isDef) return false;
        if (isGlobal != cmsModel.isGlobal) return false;
        if (modelName != null ? !modelName.equals(cmsModel.modelName) : cmsModel.modelName != null) return false;
        if (modelPath != null ? !modelPath.equals(cmsModel.modelPath) : cmsModel.modelPath != null) return false;
        if (tplChannelPrefix != null ? !tplChannelPrefix.equals(cmsModel.tplChannelPrefix) : cmsModel.tplChannelPrefix != null)
            return false;
        if (tplContentPrefix != null ? !tplContentPrefix.equals(cmsModel.tplContentPrefix) : cmsModel.tplContentPrefix != null)
            return false;
        if (siteId != null ? !siteId.equals(cmsModel.siteId) : cmsModel.siteId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = modelId;
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        result = 31 * result + (modelPath != null ? modelPath.hashCode() : 0);
        result = 31 * result + (tplChannelPrefix != null ? tplChannelPrefix.hashCode() : 0);
        result = 31 * result + (tplContentPrefix != null ? tplContentPrefix.hashCode() : 0);
        result = 31 * result + titleImgWidth;
        result = 31 * result + titleImgHeight;
        result = 31 * result + contentImgWidth;
        result = 31 * result + contentImgHeight;
        result = 31 * result + priority;
        result = 31 * result + (siteId != null ? siteId.hashCode() : 0);
        return result;
    }

    private CmsSite site;

    @ManyToOne
    @JoinColumn(name = "site_id",insertable = false,updatable = false)
    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }

    public void init() {
        if (getIsDisabled() == null) {
            setIsDisabled(false);
        }
        if (getIsDef() == null) {
            setIsDef(false);
        }
    }
    public String getTplContent(String solution, boolean def) {
        StringBuilder t = new StringBuilder();
        t.append(solution).append("/");
        t.append(TPLDIR_CONTENT);
        t.append("/");
        String prefix = getTplContentPrefix();
        if (def) {
            if (!StringUtils.isBlank(prefix)) {
                t.append(prefix);
            } else {
                t.append(DEFAULT);
            }
            t.append(TPL_SUFFIX);
        } else {
            if (!StringUtils.isBlank(prefix)) {
                t.append(prefix);
            }
        }
        return t.toString();

    }
    public String getTplChannel(String solution, boolean def) {
        StringBuilder t = new StringBuilder();
        t.append(solution).append("/");
        if (getHasContent()) {
            t.append(TPLDIR_CHANNEL);
        } else {
            t.append(TPLDIR_ALONE);
        }
        t.append("/");
        String prefix = getTplChannelPrefix();
        if (def) {
            if (!StringUtils.isBlank(prefix)) {
                t.append(prefix);
            } else {
                t.append(DEFAULT);
            }
            t.append(TPL_SUFFIX);
        } else {
            if (!StringUtils.isBlank(prefix)) {
                t.append(prefix);
            }
        }
        return t.toString();
    }
}
