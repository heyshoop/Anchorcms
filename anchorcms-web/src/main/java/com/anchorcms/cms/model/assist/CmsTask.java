package com.anchorcms.cms.model.assist;

import com.anchorcms.core.model.CmsSite;
import com.anchorcms.core.model.CmsUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/24
 * @Desc 任务调度信息表
 */
@Entity
@Table(name = "c_task")
public class CmsTask implements Serializable{
    private static final long serialVersionUID = 7195457001547909134L;
    private int taskId;
    private String taskCode;
    private Integer taskType;
    private String taskName;
    private String jobClass;
    private Integer execycle;
    private Integer dayOfMonth;
    private Byte dayOfWeek;
    private Integer hour;
    private Integer minute;
    private Integer intervalHour;
    private Integer intervalMinute;
    private Integer taskIntervalUnit;
    private String cronExpression;
    private Boolean isEnable;
    private String taskRemark;
    private int siteId;
    private int userId;
    private Serializable createTime;

    @Id
    @Column(name = "task_id")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "task_code")
    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    @Basic
    @Column(name = "task_type")
    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    @Basic
    @Column(name = "task_name")
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Basic
    @Column(name = "job_class")
    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    @Basic
    @Column(name = "execycle")
    public Integer getExecycle() {
        return execycle;
    }

    public void setExecycle(Integer execycle) {
        this.execycle = execycle;
    }

    @Basic
    @Column(name = "day_of_month")
    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Basic
    @Column(name = "day_of_week")
    public Byte getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Byte dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Basic
    @Column(name = "hour")
    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    @Basic
    @Column(name = "minute")
    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    @Basic
    @Column(name = "interval_hour")
    public Integer getIntervalHour() {
        return intervalHour;
    }

    public void setIntervalHour(Integer intervalHour) {
        this.intervalHour = intervalHour;
    }

    @Basic
    @Column(name = "interval_minute")
    public Integer getIntervalMinute() {
        return intervalMinute;
    }

    public void setIntervalMinute(Integer intervalMinute) {
        this.intervalMinute = intervalMinute;
    }

    @Basic
    @Column(name = "task_interval_unit")
    public Integer getTaskIntervalUnit() {
        return taskIntervalUnit;
    }

    public void setTaskIntervalUnit(Integer taskIntervalUnit) {
        this.taskIntervalUnit = taskIntervalUnit;
    }

    @Basic
    @Column(name = "cron_expression")
    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Basic
    @Column(name = "is_enable")
    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Basic
    @Column(name = "task_remark")
    public String getTaskRemark() {
        return taskRemark;
    }

    public void setTaskRemark(String taskRemark) {
        this.taskRemark = taskRemark;
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
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "create_time")
    public Serializable getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Serializable createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsTask cmsTask = (CmsTask) o;

        if (taskId != cmsTask.taskId) return false;
        if (taskType != cmsTask.taskType) return false;
        if (execycle != cmsTask.execycle) return false;
        if (isEnable != cmsTask.isEnable) return false;
        if (siteId != cmsTask.siteId) return false;
        if (userId != cmsTask.userId) return false;
        if (taskCode != null ? !taskCode.equals(cmsTask.taskCode) : cmsTask.taskCode != null) return false;
        if (taskName != null ? !taskName.equals(cmsTask.taskName) : cmsTask.taskName != null) return false;
        if (jobClass != null ? !jobClass.equals(cmsTask.jobClass) : cmsTask.jobClass != null) return false;
        if (dayOfMonth != null ? !dayOfMonth.equals(cmsTask.dayOfMonth) : cmsTask.dayOfMonth != null) return false;
        if (dayOfWeek != null ? !dayOfWeek.equals(cmsTask.dayOfWeek) : cmsTask.dayOfWeek != null) return false;
        if (hour != null ? !hour.equals(cmsTask.hour) : cmsTask.hour != null) return false;
        if (minute != null ? !minute.equals(cmsTask.minute) : cmsTask.minute != null) return false;
        if (intervalHour != null ? !intervalHour.equals(cmsTask.intervalHour) : cmsTask.intervalHour != null) return false;
        if (intervalMinute != null ? !intervalMinute.equals(cmsTask.intervalMinute) : cmsTask.intervalMinute != null)
            return false;
        if (taskIntervalUnit != null ? !taskIntervalUnit.equals(cmsTask.taskIntervalUnit) : cmsTask.taskIntervalUnit != null)
            return false;
        if (cronExpression != null ? !cronExpression.equals(cmsTask.cronExpression) : cmsTask.cronExpression != null)
            return false;
        if (taskRemark != null ? !taskRemark.equals(cmsTask.taskRemark) : cmsTask.taskRemark != null) return false;
        if (createTime != null ? !createTime.equals(cmsTask.createTime) : cmsTask.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (taskCode != null ? taskCode.hashCode() : 0);
        result = 31 * result + (int) taskType;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (jobClass != null ? jobClass.hashCode() : 0);
        result = 31 * result + (int) execycle;
        result = 31 * result + (dayOfMonth != null ? dayOfMonth.hashCode() : 0);
        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        result = 31 * result + (minute != null ? minute.hashCode() : 0);
        result = 31 * result + (intervalHour != null ? intervalHour.hashCode() : 0);
        result = 31 * result + (intervalMinute != null ? intervalMinute.hashCode() : 0);
        result = 31 * result + (taskIntervalUnit != null ? taskIntervalUnit.hashCode() : 0);
        result = 31 * result + (cronExpression != null ? cronExpression.hashCode() : 0);
        result = 31 * result + (taskRemark != null ? taskRemark.hashCode() : 0);
        result = 31 * result + siteId;
        result = 31 * result + userId;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
    private Map<String,String> attr;
    private CmsSite site;
    private CmsUser user;

    @ManyToOne
    @JoinColumn(name = "site_id",insertable = false,updatable = false)
    public CmsSite getSite() {
        return site;
    }

    public void setSite(CmsSite site) {
        this.site = site;
    }

    @ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    public CmsUser getUser() {
        return user;
    }

    public void setUser(CmsUser user) {
        this.user = user;
    }

    @ElementCollection(fetch= FetchType.LAZY, //加载策略,延迟加载
            targetClass=String.class) //指定集合中元素的类型
    @JoinTable(name="c_task_attr", joinColumns={ @JoinColumn(nullable=false, name="task_id")})//指定集合生成的表
    @MapKeyColumn(name="param_name")//指定map的key生成的列
    @Column(name = "param_value")
    public Map<String, String> getAttr() {
        return attr;
    }

    public void setAttr(Map<String, String> attr) {
        this.attr = attr;
    }


    /**
     * 任务执行周期cron表达式
     */
    public static int EXECYCLE_CRON=2;
    /**
     * 任务执行周期自定义
     */
    public static int EXECYCLE_DEFINE=1;
    /**
     * 执行周期-分钟
     */
    public static int EXECYCLE_MINUTE=1;
    /**
     * 执行周期-小时
     */
    public static int EXECYCLE_HOUR=2;
    /**
     * 执行周期-日
     */
    public static int EXECYCLE_DAY=3;
    /**
     * 执行周期-月
     */
    public static int EXECYCLE_WEEK=4;
    /**
     * 执行周期-月
     */
    public static int EXECYCLE_MONTH=5;
    /**
     * 首页静态任务
     */
    public static int TASK_STATIC_INDEX=1;
    /**
     * 栏目页静态化任务
     */
    public static int TASK_STATIC_CHANNEL=2;
    /**
     * 内容页静态化任务
     */
    public static int TASK_STATIC_CONTENT=3;
    /**
     * 采集类任务
     */
    public static int TASK_ACQU=4;
    /**
     * 分发类任务
     */
    public static int TASK_DISTRIBUTE=5;
    /**
     * 采集源ID
     */
    public static String TASK_PARAM_ACQU_ID="acqu_id";
    /**
     * 分发FTP ID
     */
    public static String TASK_PARAM_FTP_ID="ftp_id";
    /**
     * 站点 ID
     */
    public static String TASK_PARAM_SITE_ID="site_id";
    /**
     * 栏目 ID
     */
    public static String TASK_PARAM_CHANNEL_ID="channel_id";
    /**
     * 分发到FTP 的文件夹参数前缀
     */
    public static String TASK_PARAM_FOLDER_PREFIX="floder_";
    @Transient
    public void init(CmsSite site,CmsUser user){
        if(getCreateTime()==null){
            setCreateTime(Calendar.getInstance().getTime());
        }
        if(getUser()==null){
            setUser(user);
        }
        if(getSite()==null){
            setSite(site);
        }
    }
}
