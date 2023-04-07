package com.example.job_introduce.generalUser;

import com.google.gson.annotations.SerializedName;

public class GeneralLoginData {
    @SerializedName("guserId")
    String guserId;

    @SerializedName("guserPwd")
    String guserPwd;

    public GeneralLoginData(String guserID, String guserPWD) {
        this.guserId = guserID;
        this.guserPwd = guserPWD;
    }

    public GeneralLoginData(String guserID) {
        this.guserId = guserID;
    }
}
