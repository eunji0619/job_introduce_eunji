package com.example.job_introduce.company;

import com.google.gson.annotations.SerializedName;

public class ChkResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("compId")
    private Long compId;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Long getCompId() {
        return compId;
    }
}
