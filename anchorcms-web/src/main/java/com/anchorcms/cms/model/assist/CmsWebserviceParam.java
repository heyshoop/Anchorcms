package com.anchorcms.cms.model.assist;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by smt on 2016/11/17.
 */
@Entity
@Table(name = "c_webservice_param")
public class CmsWebserviceParam implements Serializable {
    private static final long serialVersionUID = -3876785730367045606L;
    private Integer priority;
    private String paramName;
    private String defaultValue;

    private String serviceId;

    @Id
    @Column(name = "service_id", nullable = false)
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "priority", nullable = false)
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "param_name", nullable = false, length = 100)
    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Basic
    @Column(name = "default_value", nullable = true, length = 255)
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsWebserviceParam that = (CmsWebserviceParam) o;

        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (paramName != null ? !paramName.equals(that.paramName) : that.paramName != null) return false;
        if (defaultValue != null ? !defaultValue.equals(that.defaultValue) : that.defaultValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = priority != null ? priority.hashCode() : 0;
        result = 31 * result + (paramName != null ? paramName.hashCode() : 0);
        result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
        return result;
    }

}
