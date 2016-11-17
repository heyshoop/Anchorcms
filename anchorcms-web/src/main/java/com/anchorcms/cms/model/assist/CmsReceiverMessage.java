package com.anchorcms.cms.model.assist;

import com.anchorcms.core.model.CmsSite;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-16
 * @Desc 发信的信件id
 */
@Entity
@Table(name = "c_receiver_message")
public class CmsReceiverMessage implements Serializable{
    private static final long serialVersionUID = 1440780072112690391L;
    private int msgReId;
    private String msgTitle;
    private String msgContent;
    private Date sendTime;
    private int msgSendUser;
    private int msgReceiverUser;
    private int siteId;
    private Boolean msgStatus;
    private int msgBox;
    private Integer msgId;

    @Id
    @Column(name = "msg_re_id")
    public int getMsgReId() {
        return msgReId;
    }

    public void setMsgReId(int msgReId) {
        this.msgReId = msgReId;
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

    @Basic
    @Column(name = "msg_id")
    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsReceiverMessage that = (CmsReceiverMessage) o;

        if (msgReId != that.msgReId) return false;
        if (msgSendUser != that.msgSendUser) return false;
        if (msgReceiverUser != that.msgReceiverUser) return false;
        if (siteId != that.siteId) return false;
        if (msgStatus != that.msgStatus) return false;
        if (msgBox != that.msgBox) return false;
        if (msgTitle != null ? !msgTitle.equals(that.msgTitle) : that.msgTitle != null) return false;
        if (msgContent != null ? !msgContent.equals(that.msgContent) : that.msgContent != null) return false;
        if (sendTime != null ? !sendTime.equals(that.sendTime) : that.sendTime != null) return false;
        if (msgId != null ? !msgId.equals(that.msgId) : that.msgId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = msgReId;
        result = 31 * result + (msgTitle != null ? msgTitle.hashCode() : 0);
        result = 31 * result + (msgContent != null ? msgContent.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + msgSendUser;
        result = 31 * result + msgReceiverUser;
        result = 31 * result + siteId;
        result = 31 * result + msgBox;
        result = 31 * result + (msgId != null ? msgId.hashCode() : 0);
        return result;
    }
    private CmsSite site;

    private CmsMessage message;

    @ManyToOne
    @JoinColumn(name = "msg_id",insertable = false,updatable = false)
    public CmsMessage getMessage() {
        return message;
    }

    public void setMessage(CmsMessage message) {
        this.message = message;
    }

    @ManyToOne
    @JoinColumn(name = "site_id",insertable = false,updatable = false)
    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }

    public CmsReceiverMessage(CmsMessage message) {
        setMsgId(message.getMsgId());
        setMsgReceiverUser(message.getMsgReceiverUser());
        setMsgSendUser(message.getMsgSendUser());
        setSite(message.getSite());
        setMsgTitle(message.getMsgTitle());
        setMsgContent(message.getMsgContent());
        setSendTime(message.getSendTime());
        setMsgStatus(message.getMsgStatus());
        setMsgBox(message.getMsgBox());
    }
    public CmsReceiverMessage() {
        initialize();
    }
    protected void initialize () {}
}
