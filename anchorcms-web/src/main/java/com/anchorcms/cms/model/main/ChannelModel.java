package com.anchorcms.cms.model.main;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-5
 * @Desc 栏目可选内容模型表
 */
@Entity
@Table(name = "c_channel_model")
public class ChannelModel implements Serializable{
    private static final long serialVersionUID = -3949115942501407989L;
    private int channelId;
    private int modelId;
    private String tplContent;
    private int priority;
    private String tplMobileContent;

    @Id
    @Column(name = "channel_id")
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    @Basic
    @Column(name = "model_id")
    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    @Basic
    @Column(name = "tpl_content")
    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    @Id
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "tpl_mobile_content")
    public String getTplMobileContent() {
        return tplMobileContent;
    }

    public void setTplMobileContent(String tplMobileContent) {
        this.tplMobileContent = tplMobileContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChannelModel that = (ChannelModel) o;

        if (channelId != that.channelId) return false;
        if (modelId != that.modelId) return false;
        if (priority != that.priority) return false;
        if (tplContent != null ? !tplContent.equals(that.tplContent) : that.tplContent != null) return false;
        if (tplMobileContent != null ? !tplMobileContent.equals(that.tplMobileContent) : that.tplMobileContent != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = channelId;
        result = 31 * result + modelId;
        result = 31 * result + (tplContent != null ? tplContent.hashCode() : 0);
        result = 31 * result + priority;
        result = 31 * result + (tplMobileContent != null ? tplMobileContent.hashCode() : 0);
        return result;
    }
    private CmsModel model;
    @ManyToOne
    @JoinColumn(name="model_id",insertable = false,updatable = false)
    public CmsModel getModel() {
        return model;
    }

    public void setModel(CmsModel model) {
        this.model = model;
    }
}
