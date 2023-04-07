package com.example.job_introduce.admin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class AdminUserAdd extends AppCompatActivity {


    EditText et_useradd_name,et_useradd_midphonenum,et_useradd_endphonenum,et_useradd_email,et_useradd_birth;
    RadioGroup radioGenderGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_useradd);

        et_useradd_name = findViewById(R.id.et_aduseradd_name);
        et_useradd_email = findViewById(R.id.et_aduseradd_email);
        et_useradd_birth = findViewById(R.id.et_aduseradd_birth);

        /*Spinner spinner = (Spinner) findViewById(R.id.et_aduseradd_spinphone);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phone_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);*/

        radioGenderGroup = findViewById(R.id.rg_aduseradd_gender);
        radioGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_aduseradd_male:
                        System.out.println("남자 선택");
                        break;
                    case R.id.rb_aduseradd_female:
                        System.out.println("여자 선택");
                        break;
                }
            }
        });
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
}