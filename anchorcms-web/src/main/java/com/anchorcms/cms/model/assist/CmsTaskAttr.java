package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/24
 * @Desc 任务调度配置表
 */
@Entity
@Table(name = "c_task_attr")
public class CmsTaskAttr implements Serializable{
    private static final long serialVersionUID = -2497678404046681863L;
    private int taskId;
    private String paramName;
    private String paramValue;

    @Basic
    @Column(name = "task_id")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "param_name")
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Basic
    @Column(name = "param_value")
    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsTaskAttr cmsTaskAttr = (CmsTaskAttr) o;

        if (taskId != cmsTaskAttr.taskId) return false;
        if (paramName != null ? !paramName.equals(cmsTaskAttr.paramName) : cmsTaskAttr.paramName != null) return false;
        if (paramValue != null ? !paramValue.equals(cmsTaskAttr.paramValue) : cmsTaskAttr.paramValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (paramName != null ? paramName.hashCode() : 0);
        result = 31 * result + (paramValue != null ? paramValue.hashCode() : 0);
        return result;
    }

    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
