package com.example.job_introduce.admin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.job_introduce.R;

public class AdminNoticeData {

    private int iv_adnotice_image;
    private String tv_adnotice_title;
    private String tv_adnotice_date;

    public AdminNoticeData(int iv_adnotice_image, String tv_adnotice_title, String tv_adnotice_date){
        this.iv_adnotice_image = iv_adnotice_image;
        this.tv_adnotice_title = tv_adnotice_title;
        this.tv_adnotice_date = tv_adnotice_date;
    }


    /*public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_adnotice_image;
        protected TextView tv_adnotice_title;
        protected TextView tv_adnotice_date;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_adnotice_image = (ImageView) itemView.findViewById(R.id.iv_adnotice_image);
        }
    }*/
}
