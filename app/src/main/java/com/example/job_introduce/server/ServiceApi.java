package com.example.job_introduce.server;

import com.example.job_introduce.company.ChkResponse;
import com.example.job_introduce.generalUser.GeneralUserListItem;
import com.example.job_introduce.generalUser.GuserGetInfoResponse;
import com.example.job_introduce.company.CompanyResponse;
import com.example.job_introduce.jobPost.JobPostListItem;
import com.example.job_introduce.login.DelResponse;
import com.example.job_introduce.login.DupResponse;
import com.example.job_introduce.login.JoinResponse;
import com.example.job_introduce.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("/general/login")
    Call<LoginResponse> guserLogin(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/general/joindupchk")
    Call<DupResponse> guserIdDupChk(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/general/join")
    Call<JoinResponse> guserJoin(@Field("objJson") String objJson);

    @GET("/jobPostList/load")
    Call<JobPostListItem> jobPostListLoad();

//    @GET("/noticeList/load")
//    Call<NoticeListItem> noticeListLoad();

    @FormUrlEncoded
    @POST("/general/getinfo")
    Call<GuserGetInfoResponse> getguserInfo(@Field("objJson") String objJson);

    @GET("/general/getInfoList")
    Call<GeneralUserListItem> getGuserInfoList();

    @FormUrlEncoded
    @POST("/general/deleteUser")
    Call<DelResponse> delGeneralUser(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/general/modifyUser")
    Call<DelResponse> modGeneralUser(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/company/isPresent")
    Call<ChkResponse> isPreCompInfo(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/company/getCompInfo")
    Call<CompanyResponse> getCompInfo(@Field("objJson") String objJson);

    @FormUrlEncoded
    @POST("/company/regCompInfo")
    Call<ChkResponse> regCompInfo(@Field("objJson") String objJson);
}
