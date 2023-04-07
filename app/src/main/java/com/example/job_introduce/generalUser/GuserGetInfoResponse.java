package com.example.job_introduce.generalUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class GuserGetInfoResponse {

    @SerializedName("guserId")
    private String guserId;

    @SerializedName("guserPwd")
    private String guserPwd;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("userCode")
    @Expose
    private String userCode;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("birth")
    @Expose
    private String birth;

    @SerializedName("gender")
    @Expose
    private Character gender;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("infoDate")
    @Expose
    private Character infoDate;

    @SerializedName("smsCheck")
    @Expose
    private Character smsCheck;

    @SerializedName("point")
    @Expose
    private Long point;

    @SerializedName("coupon")
    @Expose
    private String coupon;

    @SerializedName("regDate")
    @Expose
    private LocalDateTime regDate;

    public String getGuserId() {
        return guserId;
    }

    public String getGuserPwd() {
        return guserPwd;
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

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setGuserId(String guserId) {
        this.guserId = guserId;
    }

    public void setGuserPwd(String guserPwd) {
        this.guserPwd = guserPwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setInfoDate(Character infoDate) {
        this.infoDate = infoDate;
    }

    public void setSmsCheck(Character smsCheck) {
        this.smsCheck = smsCheck;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

}
