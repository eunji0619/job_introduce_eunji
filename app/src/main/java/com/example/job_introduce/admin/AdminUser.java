package com.example.job_introduce.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class AdminUser extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_useradmin);

        Button bt_user_total = findViewById(R.id.bt_aduser_total);
        Button bt_user_general = findViewById(R.id.bt_aduser_general);
        Button bt_user_adver = findViewById(R.id.bt_aduser_adver);
        Button bt_user_temp = findViewById(R.id.bt_aduser_temp);
        Button bt_user_restric = findViewById(R.id.bt_aduser_restric);
        Button bt_user_stop = findViewById(R.id.bt_aduser_stop);

        EditText et_user_search = findViewById(R.id.et_aduser_search);
        ImageButton iv_user_search = findViewById(R.id.iv_aduser_search);
        /*iv_user_search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });*/

        Button bt_user_delete = findViewById(R.id.bt_aduser_delete);
        /*bt_user_delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });*/

        Button bt_user_add = findViewById(R.id.bt_aduser_add);
        bt_user_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminUserAdd.class);
                startActivity(intent);
            }
        });
    }
}