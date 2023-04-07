package com.example.job_introduce.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;
import com.example.job_introduce.generalUser.WorkerServiceActivity;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        // 회원 이름 가져오기
        Intent login_info = getIntent(); // 메인 액티비티에서 전송한 인텐트를 가져온다.

        String user_id = login_info.getStringExtra("loginedId");
        String user_name = login_info.getStringExtra("loginedName");
        // 가져온 인텐트에서 loginedName 로그인한 계정의 이름 정보를 가져온다.
        TextView mp_name = (TextView) findViewById(R.id.tv_mp_name);
        mp_name.setText(user_name + "님");
        // 마이페이지의 회원이름 텍스트박스를 위의 내용으로 설정한다.

        // 계정관리 시작
        ImageView modify_information_btn = (ImageView) findViewById(R.id.iv_mp_settings);
        modify_information_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ModifyUserInfoActivity.class);
                intent.putExtra("loginedId", user_id); // 로그인한 ID가져온다.
                startActivity(intent);
            }
        });
        // 계정관리 끝
    }

    public void nextLayout_service(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, WorkerServiceActivity.class);
        startActivity(it);
    }
}