package com.example.job_introduce.login;

import com.google.gson.annotations.SerializedName;

public class DelResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
