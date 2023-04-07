package com.example.job_introduce.generalUser;

import com.example.job_introduce.generalUser.GeneralUser;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneralUserListItem {

    // Oepn List Item (구인 리스트 아이템)
    // 기업명, 구인글제목, 경력, 고용형태, 지역명, 마감일, 사진 순

    @SerializedName("temp")
    private List<GeneralUser> temp;


    @Override
    public String toString() {
        return "JobPostListItem {" + "body=" + temp + '}';
    }
}
