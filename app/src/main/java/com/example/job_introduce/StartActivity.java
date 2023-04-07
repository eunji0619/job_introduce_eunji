package com.example.job_introduce;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.company.CompanyInfo;
import com.example.job_introduce.generalUser.GeneralLoginActivity;
import com.example.job_introduce.partnership.PartnershipInquiryActivity;
import com.example.job_introduce.jobPost.RegJobPostActivity;
import com.example.job_introduce.login.JoinActivity;

public class StartActivity extends AppCompatActivity {

    public static Activity _Start_Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //계정관리 - button2        제휴문의 - button3
        Button button2 = findViewById(R.id.btn_compInfo);

        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), CompanyInfo.class);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), PartnershipInquiryActivity.class);
                startActivity(intent);
            }
        });

        Button button4 = findViewById(R.id.btn_regJobPost);

        button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RegJobPostActivity.class);
                startActivity(intent);
            }
        });

        Button btn_signIN = findViewById(R.id.bt_company_signin);

        btn_signIN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

    }

    //  로그인하기 클릭 시 회원 로그인 창으로 이동
    public void nextLayout_Login(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, GeneralLoginActivity.class);
        startActivity(it);
        finish();
    }
}