package com.example.job_introduce.jobPost;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobPostListItem {

    @SerializedName("body")
    public List<JobPost> body;


    @Override
    public String toString() {
        return "JobPostListItem {" + "body=" + body + '}';
    }

    public List<JobPost> getJobPostList() {
        return body;
    }

}
