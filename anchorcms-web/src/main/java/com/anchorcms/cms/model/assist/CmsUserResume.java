package com.anchorcms.cms.model.assist;

import com.anchorcms.core.model.CmsUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author 阁楼麻雀
 * @Email netuser.orz@icloud.com
 * @Date 2016/11/24
 * @Desc 简历信息表
 */
@Entity
@Table(name = "c_user_resume")
public class CmsUserResume implements Serializable{
    private static final long serialVersionUID = 793222931975886315L;
    private int userId;
    private String resumeName;
    private String targetWorknature;
    private String targetWorkplace;
    private String targetCategory;
    private String targetSalary;
    private String eduSchool;
    private Date eduGraduation;
    private String eduBack;
    private String eduDiscipline;
    private String recentCompany;
    private String companyIndustry;
    private String companyScale;
    private String jobName;
    private String jobCategory;
    private Date jobStart;
    private String subordinates;
    private String jobDescription;
    private String selfEvaluation;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "resume_name")
    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    @Basic
    @Column(name = "target_worknature")
    public String getTargetWorknature() {
        return targetWorknature;
    }

    public void setTargetWorknature(String targetWorknature) {
        this.targetWorknature = targetWorknature;
    }

    @Basic
    @Column(name = "target_workplace")
    public String getTargetWorkplace() {
        return targetWorkplace;
    }

    public void setTargetWorkplace(String targetWorkplace) {
        this.targetWorkplace = targetWorkplace;
    }

    @Basic
    @Column(name = "target_category")
    public String getTargetCategory() {
        return targetCategory;
    }

    public void setTargetCategory(String targetCategory) {
        this.targetCategory = targetCategory;
    }

    @Basic
    @Column(name = "target_salary")
    public String getTargetSalary() {
        return targetSalary;
    }

    public void setTargetSalary(String targetSalary) {
        this.targetSalary = targetSalary;
    }

    @Basic
    @Column(name = "edu_school")
    public String getEduSchool() {
        return eduSchool;
    }

    public void setEduSchool(String eduSchool) {
        this.eduSchool = eduSchool;
    }

    @Basic
    @Column(name = "edu_graduation")
    public Date getEduGraduation() {
        return eduGraduation;
    }

    public void setEduGraduation(Date eduGraduation) {
        this.eduGraduation = eduGraduation;
    }

    @Basic
    @Column(name = "edu_back")
    public String getEduBack() {
        return eduBack;
    }

    public void setEduBack(String eduBack) {
        this.eduBack = eduBack;
    }

    @Basic
    @Column(name = "edu_discipline")
    public String getEduDiscipline() {
        return eduDiscipline;
    }

    public void setEduDiscipline(String eduDiscipline) {
        this.eduDiscipline = eduDiscipline;
    }

    @Basic
    @Column(name = "recent_company")
    public String getRecentCompany() {
        return recentCompany;
    }

    public void setRecentCompany(String recentCompany) {
        this.recentCompany = recentCompany;
    }

    @Basic
    @Column(name = "company_industry")
    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    @Basic
    @Column(name = "company_scale")
    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    @Basic
    @Column(name = "job_name")
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "job_category")
    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    @Basic
    @Column(name = "job_start")
    public Date getJobStart() {
        return jobStart;
    }

    public void setJobStart(Date jobStart) {
        this.jobStart = jobStart;
    }

    @Basic
    @Column(name = "subordinates")
    public String getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(String subordinates) {
        this.subordinates = subordinates;
    }

    @Basic
    @Column(name = "job_description")
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Basic
    @Column(name = "self_evaluation")
    public String getSelfEvaluation() {
        return selfEvaluation;
    }

    public void setSelfEvaluation(String selfEvaluation) {
        this.selfEvaluation = selfEvaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmsUserResume that = (CmsUserResume) o;

        if (userId != that.userId) return false;
        if (resumeName != null ? !resumeName.equals(that.resumeName) : that.resumeName != null) return false;
        if (targetWorknature != null ? !targetWorknature.equals(that.targetWorknature) : that.targetWorknature != null)
            return false;
        if (targetWorkplace != null ? !targetWorkplace.equals(that.targetWorkplace) : that.targetWorkplace != null)
            return false;
        if (targetCategory != null ? !targetCategory.equals(that.targetCategory) : that.targetCategory != null)
            return false;
        if (targetSalary != null ? !targetSalary.equals(that.targetSalary) : that.targetSalary != null) return false;
        if (eduSchool != null ? !eduSchool.equals(that.eduSchool) : that.eduSchool != null) return false;
        if (eduGraduation != null ? !eduGraduation.equals(that.eduGraduation) : that.eduGraduation != null)
            return false;
        if (eduBack != null ? !eduBack.equals(that.eduBack) : that.eduBack != null) return false;
        if (eduDiscipline != null ? !eduDiscipline.equals(that.eduDiscipline) : that.eduDiscipline != null)
            return false;
        if (recentCompany != null ? !recentCompany.equals(that.recentCompany) : that.recentCompany != null)
            return false;
        if (companyIndustry != null ? !companyIndustry.equals(that.companyIndustry) : that.companyIndustry != null)
            return false;
        if (companyScale != null ? !companyScale.equals(that.companyScale) : that.companyScale != null) return false;
        if (jobName != null ? !jobName.equals(that.jobName) : that.jobName != null) return false;
        if (jobCategory != null ? !jobCategory.equals(that.jobCategory) : that.jobCategory != null) return false;
        if (jobStart != null ? !jobStart.equals(that.jobStart) : that.jobStart != null) return false;
        if (subordinates != null ? !subordinates.equals(that.subordinates) : that.subordinates != null) return false;
        if (jobDescription != null ? !jobDescription.equals(that.jobDescription) : that.jobDescription != null)
            return false;
        if (selfEvaluation != null ? !selfEvaluation.equals(that.selfEvaluation) : that.selfEvaluation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (resumeName != null ? resumeName.hashCode() : 0);
        result = 31 * result + (targetWorknature != null ? targetWorknature.hashCode() : 0);
        result = 31 * result + (targetWorkplace != null ? targetWorkplace.hashCode() : 0);
        result = 31 * result + (targetCategory != null ? targetCategory.hashCode() : 0);
        result = 31 * result + (targetSalary != null ? targetSalary.hashCode() : 0);
        result = 31 * result + (eduSchool != null ? eduSchool.hashCode() : 0);
        result = 31 * result + (eduGraduation != null ? eduGraduation.hashCode() : 0);
        result = 31 * result + (eduBack != null ? eduBack.hashCode() : 0);
        result = 31 * result + (eduDiscipline != null ? eduDiscipline.hashCode() : 0);
        result = 31 * result + (recentCompany != null ? recentCompany.hashCode() : 0);
        result = 31 * result + (companyIndustry != null ? companyIndustry.hashCode() : 0);
        result = 31 * result + (companyScale != null ? companyScale.hashCode() : 0);
        result = 31 * result + (jobName != null ? jobName.hashCode() : 0);
        result = 31 * result + (jobCategory != null ? jobCategory.hashCode() : 0);
        result = 31 * result + (jobStart != null ? jobStart.hashCode() : 0);
        result = 31 * result + (subordinates != null ? subordinates.hashCode() : 0);
        result = 31 * result + (jobDescription != null ? jobDescription.hashCode() : 0);
        result = 31 * result + (selfEvaluation != null ? selfEvaluation.hashCode() : 0);
        return result;
    }
    private CmsUser user;

    @OneToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    public CmsUser getUser() {
        return user;
    }

    public void setUser(CmsUser user) {
        this.user = user;
    }
}
