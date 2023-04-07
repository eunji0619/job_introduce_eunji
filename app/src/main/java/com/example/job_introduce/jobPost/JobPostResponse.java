package com.example.job_introduce.jobPost;

import com.google.gson.annotations.SerializedName;

public class JobPostResponse {
    @SerializedName("postId")
    private Long postId;

    @SerializedName("comId")
    private Long compId;

    @SerializedName("title")
    private String title;

    @SerializedName("managerName")
    private String managerName;

    @SerializedName("managerPhone")
    private String managerPhone;

    @SerializedName("workPeriod")
    private String workPeriod;

    @SerializedName("workDay")
    private String workDay;

    @SerializedName("workStart")
    private int workStart;

    @SerializedName("workEnd")
    private int workEnd;

    @SerializedName("education")
    private String education;

    @SerializedName("career")
    private String career;

    @SerializedName("recNUm")
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

    public Long getPostId() {
        return postId;
    }

    public Long getCompId() {
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

    public String getWorkPeriod() {
        return workPeriod;
    }

    public String getWorkDay() {
        return workDay;
    }

    public int getWorkStart() {
        return workStart;
    }

    public int getWorkEnd() {
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
