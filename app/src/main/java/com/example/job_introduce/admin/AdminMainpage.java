package com.example.job_introduce.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class AdminMainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mainpage);

        Button bt_admin_notice = findViewById(R.id.bt_admin_notice);
        bt_admin_notice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AdminNotice.class);
                startActivity(intent);
            }
        });

        Button bt_admin_user = findViewById(R.id.bt_admin_user);
        bt_admin_user.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AdminUser.class);
                startActivity(intent);
            }
        });

        /*Button bt_admin_board = findViewById(R.id.bt_admin_board);

        bt_admin_board.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), .class);
                startActivity(intent);
            }
        });

        Button bt_admin_report = findViewById(R.id.bt_admin_report);

        bt_admin_report.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), .class);
                startActivity(intent);
            }
        });

        Button bt_admin_post = findViewById(R.id.bt_admin_post);

        bt_admin_post.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), .class);
                startActivity(intent);
            }
        });*/
    }
}