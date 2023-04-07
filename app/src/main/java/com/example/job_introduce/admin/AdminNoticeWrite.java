package com.example.job_introduce.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class AdminNoticeWrite extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_noticewrite);

        EditText et_notice_title = findViewById(R.id.et_adwrite_title);
        EditText et_notice_content = findViewById(R.id.et_adwrite_content);

        Button bt_notice_register = findViewById(R.id.bt_adwrite_register);
        bt_notice_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AdminNotice.class);
                startActivity(intent);
                // + 공지 등록 기능
            }
        });
    }
}