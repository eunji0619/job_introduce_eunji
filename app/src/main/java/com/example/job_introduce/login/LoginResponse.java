package com.example.job_introduce.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("guserId")
    private String guserId;

    @SerializedName("name")
    private String name;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getGuserId() {
        return guserId;
    }

    public String getName() {
        return name;
    }
}
