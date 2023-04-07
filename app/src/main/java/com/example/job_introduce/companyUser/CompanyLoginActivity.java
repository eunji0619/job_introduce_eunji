package com.example.job_introduce.companyUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;
import com.example.job_introduce.generalUser.GeneralLoginActivity;

public class CompanyLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
    }

    public void nextLayout_generalLogin(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, GeneralLoginActivity.class);
        startActivity(it);
    }
}