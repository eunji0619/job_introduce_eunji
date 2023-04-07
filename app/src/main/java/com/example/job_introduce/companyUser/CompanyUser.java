package com.example.job_introduce.companyUser;

import com.google.gson.annotations.SerializedName;

public class CompanyUser {

    @SerializedName("cuser_id")
    private String cuserId;

    @SerializedName("bnum")
    private String bNum;

    @SerializedName("cuserPwd")
    private String cuserPwd;

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

    public CompanyUser(String cuserId, String bNum, String cuserPwd, String email, String userCode, String name, String status, String birth, Character gender, String phone, Character infoDate, Character smsCheck, Long point, String coupon) {
        this.cuserId = cuserId;
        this.bNum = bNum;
        this.cuserPwd = cuserPwd;
        this.email = email;
        this.userCode = userCode;
        this.name = name;
        this.status = status;
        this.birth = birth;
        this.gender = gender;
        this.phone = phone;
        this.infoDate = infoDate;
        this.smsCheck = smsCheck;
        this.point = point;
        this.coupon = coupon;
    }

    public CompanyUser(String cuserId) {
        this.cuserId = cuserId;
    }

    public String getCuserId() {
        return cuserId;
    }

    public String getbNum() {
        return bNum;
    }

    public String getCuserPwd() {
        return cuserPwd;
    }

    public String getEmail() {
        return email;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getBirth() {
        return birth;
    }

    public Character getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public Character getInfoDate() {
        return infoDate;
    }

    public Character getSmsCheck() {
        return smsCheck;
    }

    public Long getPoint() {
        return point;
    }

    public String getCoupon() {
        return coupon;
    }
}
