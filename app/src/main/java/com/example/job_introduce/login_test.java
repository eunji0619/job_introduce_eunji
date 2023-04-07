package com.example.job_introduce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class login_test extends AppCompatActivity {

    Button btn_generaluser_t, btn_companyuser_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);

        btn_generaluser_t = (Button) findViewById(R.id.btn_generaluser_t);
        btn_companyuser_t = (Button) findViewById(R.id.btn_companyuser_t);

        btn_generaluser_t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                generallogin_test generallogin_test = new generallogin_test();
                transaction.replace(R.id.fragment_layout,generallogin_test);
                transaction.commit();
                btn_generaluser_t.setTextColor(Color.parseColor("#000000"));
                btn_generaluser_t.setBackgroundResource(R.drawable.a_tempcolor_border2);
                btn_companyuser_t.setBackgroundResource(R.drawable.no_choice_btn);
                btn_companyuser_t.setTextColor(Color.parseColor("#808080"));
            }
        });

        btn_companyuser_t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                companylogin_test companylogin_test = new companylogin_test();
                transaction.replace(R.id.fragment_layout,companylogin_test);
                transaction.commit();
                btn_generaluser_t.setTextColor(Color.parseColor("#808080"));
                btn_generaluser_t.setBackgroundResource(R.drawable.no_choice_btn);
                btn_companyuser_t.setBackgroundResource(R.drawable.company_border);
                btn_companyuser_t.setTextColor(Color.parseColor("#000000"));
            }
        });

    }
}