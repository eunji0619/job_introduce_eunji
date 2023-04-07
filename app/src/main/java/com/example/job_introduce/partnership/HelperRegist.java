package com.example.job_introduce.partnership;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class HelperRegist extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    RadioGroup RadioGenderGroup, RadioCareerGroup, RadioSalGroup, RadioRegistGroup;
    CheckBox weekdayCheck, weekendCheck, monCheck, tueCheck, wedCheck, thuCheck, friCheck
            , satCheck, sunCheck, alldayCheck, morningCheck, lunchCheck, afternoonCheck, dinnerCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_regi);

        // 희망 근무 요일 체크박스
        weekdayCheck = (CheckBox)findViewById(R.id.cb_weekday);
        weekdayCheck.setOnCheckedChangeListener(this);
        weekendCheck = (CheckBox)findViewById(R.id.cb_weekend);
        weekendCheck.setOnCheckedChangeListener(this);
        monCheck = (CheckBox)findViewById(R.id.cb_mon);
        monCheck.setOnCheckedChangeListener(this);
        tueCheck = (CheckBox)findViewById(R.id.cb_tue);
        tueCheck.setOnCheckedChangeListener(this);
        wedCheck = (CheckBox)findViewById(R.id.cb_wed);
        wedCheck.setOnCheckedChangeListener(this);
        thuCheck = (CheckBox)findViewById(R.id.cb_thu);
        thuCheck.setOnCheckedChangeListener(this);
        friCheck = (CheckBox)findViewById(R.id.cb_fri);
        friCheck.setOnCheckedChangeListener(this);
        satCheck = (CheckBox)findViewById(R.id.cb_sat);
        satCheck.setOnCheckedChangeListener(this);
        sunCheck = (CheckBox)findViewById(R.id.cb_sun);
        sunCheck.setOnCheckedChangeListener(this);

        // 연락가능시간 체크박스
        alldayCheck = (CheckBox)findViewById(R.id.cb_allday);
        alldayCheck.setOnCheckedChangeListener(this);
        morningCheck = (CheckBox)findViewById(R.id.cb_morning);
        morningCheck.setOnCheckedChangeListener(this);
        lunchCheck = (CheckBox)findViewById(R.id.cb_lunch);
        lunchCheck.setOnCheckedChangeListener(this);
        afternoonCheck = (CheckBox)findViewById(R.id.cb_afternoon);
        afternoonCheck.setOnCheckedChangeListener(this);
        dinnerCheck = (CheckBox)findViewById(R.id.cb_dinner);
        dinnerCheck.setOnCheckedChangeListener(this);


    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }
}
