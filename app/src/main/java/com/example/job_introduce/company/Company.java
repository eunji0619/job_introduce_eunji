package com.example.job_introduce.company;

import com.example.job_introduce.companyUser.CompanyUser;
import com.google.gson.annotations.SerializedName;

public class Company {

    @SerializedName("compId")
    private Long compId;

    @SerializedName("cuserId")
    private CompanyUser cuserId;

    @SerializedName("compName")
    private String compName;

    @SerializedName("compType")
    private String compType;

    @SerializedName("loc")
    private String loc;

    @SerializedName("repName")
    private String repName;

    @SerializedName("repPhone")
    private String repPhone;

    @SerializedName("empNum")
    private int empNum;

    public Company(String compName, String compType, String loc, String repName, String repPhone, int empNum) {
        this.compName = compName;
        this.compType = compType;
        this.loc = loc;
        this.repName = repName;
        this.repPhone = repPhone;
        this.empNum = empNum;
    }

    public Company(Long compId) {
        this.compId = compId;
    }

    public Long getCompId() {
        return compId;
    }

    public String getCompName() {
        return compName;
    }

    public String getCompType() {
        return compType;
    }

    public String getLoc() {
        return loc;
    }

    public String getRepName() {
        return repName;
    }

    public String getRepPhone() {
        return repPhone;
    }

    public int getEmpNum() {
        return empNum;
    }
}
