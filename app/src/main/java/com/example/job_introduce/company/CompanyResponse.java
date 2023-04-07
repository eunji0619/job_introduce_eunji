package com.example.job_introduce.company;

import com.example.job_introduce.companyUser.CompanyUser;
import com.google.gson.annotations.SerializedName;

public class CompanyResponse {

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

    public Long getCompId() {
        return compId;
    }

    public CompanyUser getCuserId() {
        return cuserId;
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
