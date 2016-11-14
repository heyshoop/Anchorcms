package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016-11-5
 * @Desc 插件信息表
 */
@Entity
@Table(name = "c_plug")
public class CmsPlug implements Serializable{
    private static final long serialVersionUID = 2581059661504463160L;
    private int plugId;
    private String name;
    private String path;
    private String description;
    private String author;
    private Date uploadTime;
    private Date installTime;
    private Date uninstallTime;
    private byte fileConflict;
    private byte isUsed;
    private String plugPerms;
    private byte plugRepair;

    @Id
    @Column(name = "plug_id")
    public int getPlugId() {
        return plugId;
    }

    public void setPlugId(int plugId) {
        this.plugId = plugId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "upload_time")
    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "install_time")
    public Date getInstallTime() {
        return installTime;
    }

    public void setInstallTime(Date installTime) {
        this.installTime = installTime;
    }

    @Basic
    @Column(name = "uninstall_time")
    public Date getUninstallTime() {
        return uninstallTime;
    }

    public void setUninstallTime(Date uninstallTime) {
        this.uninstallTime = uninstallTime;
    }

    @Basic
    @Column(name = "file_conflict")
    public byte getFileConflict() {
        return fileConflict;
    }

    public void setFileConflict(byte fileConflict) {
        this.fileConflict = fileConflict;
    }

    @Basic
    @Column(name = "is_used")
    public byte getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(byte isUsed) {
        this.isUsed = isUsed;
    }

    @Basic
    @Column(name = "plug_perms")
    public String getPlugPerms() {
        return plugPerms;
    }

    public void setPlugPerms(String plugPerms) {
        this.plugPerms = plugPerms;
    }

    @Basic
    @Column(name = "plug_repair")
    public byte getPlugRepair() {
        return plugRepair;
    }

    public void setPlugRepair(byte plugRepair) {
        this.plugRepair = plugRepair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsPlug cmsPlug = (CmsPlug) o;

        if (plugId != cmsPlug.plugId) return false;
        if (fileConflict != cmsPlug.fileConflict) return false;
        if (isUsed != cmsPlug.isUsed) return false;
        if (plugRepair != cmsPlug.plugRepair) return false;
        if (name != null ? !name.equals(cmsPlug.name) : cmsPlug.name != null) return false;
        if (path != null ? !path.equals(cmsPlug.path) : cmsPlug.path != null) return false;
        if (description != null ? !description.equals(cmsPlug.description) : cmsPlug.description != null) return false;
        if (author != null ? !author.equals(cmsPlug.author) : cmsPlug.author != null) return false;
        if (uploadTime != null ? !uploadTime.equals(cmsPlug.uploadTime) : cmsPlug.uploadTime != null) return false;
        if (installTime != null ? !installTime.equals(cmsPlug.installTime) : cmsPlug.installTime != null) return false;
        if (uninstallTime != null ? !uninstallTime.equals(cmsPlug.uninstallTime) : cmsPlug.uninstallTime != null)
            return false;
        if (plugPerms != null ? !plugPerms.equals(cmsPlug.plugPerms) : cmsPlug.plugPerms != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = plugId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (installTime != null ? installTime.hashCode() : 0);
        result = 31 * result + (uninstallTime != null ? uninstallTime.hashCode() : 0);
        result = 31 * result + (int) fileConflict;
        result = 31 * result + (int) isUsed;
        result = 31 * result + (plugPerms != null ? plugPerms.hashCode() : 0);
        result = 31 * result + (int) plugRepair;
        return result;
    }
}
