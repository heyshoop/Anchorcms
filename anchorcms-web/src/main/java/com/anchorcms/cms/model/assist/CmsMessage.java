package com.anchorcms.cms.model.assist;

import com.anchorcms.common.utils.StrUtil;
import com.anchorcms.core.model.CmsSite;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-16
 * @Desc CMS站内信
 */
@Entity
@Table(name = "c_message")
public class CmsMessage implements Serializable{
    private static final long serialVersionUID = -1190737340595777602L;
    private int msgId;
    private String msgTitle;
    private String msgContent;
    private Date sendTime;
    private int msgSendUser;
    private int msgReceiverUser;
    private int siteId;
    private Boolean msgStatus;
    private int msgBox;

    @Id
    @Column(name = "msg_id")
    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    @Basic
    @Column(name = "msg_title")
    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    @Basic
    @Column(name = "msg_content")
    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Basic
    @Column(name = "send_time")
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "msg_send_user")
    public int getMsgSendUser() {
        return msgSendUser;
    }

    public void setMsgSendUser(int msgSendUser) {
        this.msgSendUser = msgSendUser;
    }

    @Basic
    @Column(name = "msg_receiver_user")
    public int getMsgReceiverUser() {
        return msgReceiverUser;
    }

    public void setMsgReceiverUser(int msgReceiverUser) {
        this.msgReceiverUser = msgReceiverUser;
    }

    @Basic
    @Column(name = "site_id")
    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Basic
    @Column(name = "msg_status")
    public Boolean getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Boolean msgStatus) {
        this.msgStatus = msgStatus;
    }

    @Basic
    @Column(name = "msg_box")
    public int getMsgBox() {
        return msgBox;
    }

    public void setMsgBox(int msgBox) {
        this.msgBox = msgBox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsMessage cmsMessage = (CmsMessage) o;

        if (msgId != cmsMessage.msgId) return false;
        if (msgSendUser != cmsMessage.msgSendUser) return false;
        if (msgReceiverUser != cmsMessage.msgReceiverUser) return false;
        if (siteId != cmsMessage.siteId) return false;
        if (msgStatus != cmsMessage.msgStatus) return false;
        if (msgBox != cmsMessage.msgBox) return false;
        if (msgTitle != null ? !msgTitle.equals(cmsMessage.msgTitle) : cmsMessage.msgTitle != null) return false;
        if (msgContent != null ? !msgContent.equals(cmsMessage.msgContent) : cmsMessage.msgContent != null) return false;
        if (sendTime != null ? !sendTime.equals(cmsMessage.sendTime) : cmsMessage.sendTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = msgId;
        result = 31 * result + (msgTitle != null ? msgTitle.hashCode() : 0);
        result = 31 * result + (msgContent != null ? msgContent.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + msgSendUser;
        result = 31 * result + msgReceiverUser;
        result = 31 * result + siteId;
        result = 31 * result + msgBox;
        return result;
    }
    private CmsSite site;

    private Set<CmsReceiverMessage> receiverMsgs;

    @OneToMany
    @JoinColumn(name = "msg_id",insertable = false,updatable = false)
    public Set<CmsReceiverMessage> getReceiverMsgs() {
        return receiverMsgs;
    }

    public void setReceiverMsgs(Set<CmsReceiverMessage> receiverMsgs) {
        this.receiverMsgs = receiverMsgs;
    }

    @ManyToOne
    @JoinColumn(name = "site_id",insertable = false,updatable = false)
    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }

    @Transient
    public String getContentHtml() {
        return StrUtil.txt2htm(getMsgContent());
    }
}
