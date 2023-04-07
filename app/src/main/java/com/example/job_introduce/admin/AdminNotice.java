package com.example.job_introduce.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class AdminNotice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice);

        ImageButton ib_notice_write = findViewById(R.id.ib_adnotice_write);

        ib_notice_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminNoticeWrite.class);
                startActivity(intent);
            }
        });
    }
}