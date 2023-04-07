package com.example.job_introduce.generalUser;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class GeneralJoinData {
    @SerializedName("guserId")
    private String guserId;

    @SerializedName("guserPwd")
    private String guserPwd;

    @SerializedName("email")
    private String email;

    @SerializedName("userCode")
    private String userCode;

    @SerializedName("name")
    private String name;

    @SerializedName("status")
    private String status;

    @SerializedName("birth")
    private String birth;

    @SerializedName("gender")
    private Character gender;

    @SerializedName("phone")
    private String phone;

    @SerializedName("infoDate")
    private Character infoDate;

    @SerializedName("smsCheck")
    private Character smsCheck;


    @SerializedName("point")
    private Long point;

    @SerializedName("coupon")
    private String coupon;

    public GeneralJoinData(String guserId, String guserPwd, String name, String birth, Character gender, String phone, Character infoDate, Character smsCheck) {
        this.guserId = guserId;
        this.guserPwd = guserPwd;
        this.email = "";
        this.userCode = "10001";
        this.name = name;
        this.status = "30000";
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.infoDate = infoDate;
        this.smsCheck = smsCheck;
        this.point = 0L;
        this.coupon = "";
    }

    public GeneralJoinData(String guserId, String guserPwd, String email, String name, String birth, Character gender, String phone, Character infoDate, Character smsCheck) {
        this.guserId = guserId;
        this.guserPwd = guserPwd;
        this.email = email;
        this.userCode = "10001";
        this.name = name;
        this.status = "30000";
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.infoDate = infoDate;
        this.smsCheck = smsCheck;
        this.point = 0L;
        this.coupon = "";
    }
}
