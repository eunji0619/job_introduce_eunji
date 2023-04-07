package com.example.job_introduce.generalUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.MainActivity;
import com.example.job_introduce.PreferenceManager;
import com.example.job_introduce.R;
import com.example.job_introduce.login.JoinActivity;
import com.example.job_introduce.login.LoginResponse;
import com.example.job_introduce.server.RetrofitClient;
import com.example.job_introduce.server.ServiceApi;
import com.example.job_introduce.StartActivity;
import com.example.job_introduce.companyUser.CompanyLoginActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralLoginActivity extends AppCompatActivity {

    public static Activity _GeneralLogin_Activity;
    private Context mContext;
    StartActivity SA;
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    String id;
    String pwd;
    Gson gson = new Gson();
    //MainActivity MA;
    EditText et_id;
    EditText et_pwd;
    Button btn_login;
    CheckBox cb_autologin;

    //mainActivity = (MainActivity)MainActivity._Main_Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_login);
        mContext = this;
        _GeneralLogin_Activity = GeneralLoginActivity.this;
        SA = (StartActivity) StartActivity._Start_Activity;

        et_id = (EditText)findViewById(R.id.et_id);
        et_pwd = (EditText)findViewById(R.id.et_pwd);
        btn_login = (Button)findViewById(R.id.btn_login);
        cb_autologin = (CheckBox)findViewById(R.id.cb_autologin);

        boolean boo = PreferenceManager.getBoolean(mContext, "check"); //로그인 정보 기억하기 체크 유무 확인
        if(boo){ // 체크가 되어있다면 아래 코드를 수행
            //저장된 아이디와 암호를 가져와 셋팅한다.
            et_id.setText(PreferenceManager.getString(mContext, "id"));
            et_pwd.setText(PreferenceManager.getString(mContext, "pwd"));
            cb_autologin.setChecked(true); //체크박스는 여전히 체크 표시 하도록 셋팅
        }


        Button btn_generalLogin = findViewById(R.id.btn_login);
        btn_generalLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //로그인 버튼 눌렀을 때 동작
                //아이디 암호 입력창에서 텍스트를 가져와 PreferenceManager에 저장함
                PreferenceManager.setString(mContext, "id", et_id.getText().toString()); //id라는 키값으로 저장
                PreferenceManager.setString(mContext, "pwd", et_pwd.getText().toString()); //pwd라는 키값으로 저장

                // 저장한 키 값으로 저장된 아이디와 암호를 불러와 String 값에 저장
                String checkId = PreferenceManager.getString(mContext, "id");
                String checkPwd = PreferenceManager.getString(mContext, "pwd");

                //아이디와 암호가 비어있는 경우를 체크
                if (TextUtils.isEmpty(checkId) || TextUtils.isEmpty(checkPwd)){
                    //아이디나 암호 둘 중 하나가 비어있으면 토스트메시지를 띄운다
                    Toast.makeText(GeneralLoginActivity.this, "아이디/암호를 입력해주세요",
                            Toast.LENGTH_SHORT).show();
                }else { //둘 다 충족하면 다음 동작을 구현해놓음
                GeneralLoginData data = new GeneralLoginData(checkId, checkPwd);
                String objJson = gson.toJson(data);
                System.out.println(objJson);
                startLogin(objJson);
                }
            }
        });

        //로그인 기억하기 체크박스 유무에 따른 동작 구현
        cb_autologin.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()) { // 체크박스 체크 되어 있으면
                    //editText에서 아이디와 암호 가져와 PreferenceManager에 저장한다.
                    PreferenceManager.setString(mContext, "id", et_id.getText().toString()); //id 키값으로 저장
                    PreferenceManager.setString(mContext, "pwd", et_pwd.getText().toString()); //pw 키값으로 저장
                    PreferenceManager.setBoolean(mContext, "check", cb_autologin.isChecked()); //현재 체크박스 상태 값 저장
                } else { //체크박스가 해제되어있으면
                    PreferenceManager.setBoolean(mContext, "check", cb_autologin.isChecked()); //현재 체크박스 상태 값 저장
                    PreferenceManager.clear(mContext); //로그인 정보를 모두 날림
                }
            }
        }) ;
    }
    private void startLogin(String json) {
        Call<LoginResponse> login = service.guserLogin(json);
        login.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                try{
                    if(response.body().getCode() == 1){
                        Toast.makeText(GeneralLoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        //MA.finish();
                        Intent inLogin = new Intent(getApplicationContext(), MainActivity.class);
                        inLogin.putExtra("islogin", 1);
                        inLogin.putExtra("loginedId", response.body().getGuserId());
                        inLogin.putExtra("loginedName", response.body().getName());
                        startActivity(inLogin);
                        finish();
                        SA.finish();
                    }else{
                        Toast.makeText(GeneralLoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("log",t.getMessage());
                Toast.makeText(GeneralLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void nextLayout_join(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, JoinActivity.class);
        startActivity(it);
    }

    public void nextLayout_comLogin(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, CompanyLoginActivity.class);
        startActivity(it);
    }
}