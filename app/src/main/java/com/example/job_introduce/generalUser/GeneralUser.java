package com.example.job_introduce.generalUser;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Date;

public class GeneralUser implements Serializable {

    private String guserId;
    private String guserPwd;
    private String email;
    private String userCode;
    private String name;
    private String status;
    private String birth;
    private Character gender;
    private String phone;
    private Character infoDate;
    private Character smsCheck;
    private Long point;
    private String coupon;

    public GeneralUser(String guserId) {
        this.guserId = guserId;
    }

    public GeneralUser(String guserId, String guserPwd, String email, String userCode, String name, String status, String birth, Character gender, String phone, Character infoDate, Character smsCheck, Long point, String coupon) {
        this.guserId = guserId;
        this.guserPwd = guserPwd;
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


}
