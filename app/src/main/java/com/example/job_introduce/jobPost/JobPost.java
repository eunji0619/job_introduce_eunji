package com.example.job_introduce.jobPost;

import com.example.job_introduce.company.Company;
import com.google.gson.annotations.SerializedName;

public class JobPost {
    @SerializedName("postId")
    private Long postId;

    @SerializedName("compId")
    private Company compId;

    @SerializedName("title")
    private String title;

    @SerializedName("managerName")
    private String managerName;

    @SerializedName("managerPhone")
    private String managerPhone;

    @SerializedName("managerEmail")
    private String managerEmail;

    @SerializedName("workPeriod")
    private String workPeriod;

    @SerializedName("workDay")
    private String workDay;

    @SerializedName("workStart")
    private String workStart;

    @SerializedName("workEnd")
    private String workEnd;

    @SerializedName("education")
    private String education;

    @SerializedName("career")
    private String career;

    @SerializedName("recNum")
    private String recNum;

    @SerializedName("recType")
    private String recType;

    @SerializedName("salaryType")
    private String salaryType;

    @SerializedName("salary")
    private Long salary;

    @SerializedName("infoDetail")
    private String infoDetail;

    @SerializedName("resumeCheck")
    private Character resumeCheck;

    @SerializedName("jobType")
    private String jobType;

    @SerializedName("endDate")
    private String endDate;

    @SerializedName("content")
    private String content;

    public JobPost(Long postId, Company compId, String title, String managerName, String managerPhone, String managerEmail, String workPeriod, String workDay, String workStart, String workEnd, String education, String career, String recNum, String recType, String salaryType, Long salary, String infoDetail, Character resumeCheck, String jobType, String endDate, String content) {
        this.postId = postId;
        this.compId = compId;
        this.title = title;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
        this.managerEmail = managerEmail;
        this.workPeriod = workPeriod;
        this.workDay = workDay;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.education = education;
        this.career = career;
        this.recNum = recNum;
        this.recType = recType;
        this.salaryType = salaryType;
        this.salary = salary;
        this.infoDetail = infoDetail;
        this.resumeCheck = resumeCheck;
        this.jobType = jobType;
        this.endDate = endDate;
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public Company getCompId() {
        return compId;
    }

    public String getTitle() {
        return title;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public String getWorkPeriod() {
        return workPeriod;
    }

    public String getWorkDay() {
        return workDay;
    }

    public String getWorkStart() {
        return workStart;
    }

    public String getWorkEnd() {
        return workEnd;
    }

    public String getEducation() {
        return education;
    }

    public String getCareer() {
        return career;
    }

    public String getRecNum() {
        return recNum;
    }

    public String getRecType() {
        return recType;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public Long getSalary() {
        return salary;
    }

    public String getInfoDetail() {
        return infoDetail;
    }

    public Character getResumeCheck() {
        return resumeCheck;
    }

    public String getJobType() {
        return jobType;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getContent() {
        return content;
    }
}
