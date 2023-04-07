package com.example.job_introduce.myPage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.generalUser.GeneralUser;
import com.example.job_introduce.generalUser.GuserGetInfoResponse;
import com.example.job_introduce.R;
import com.example.job_introduce.generalUser.GeneralLoginData;
import com.example.job_introduce.login.DelResponse;
import com.example.job_introduce.server.RetrofitClient;
import com.example.job_introduce.server.ServiceApi;
import com.google.gson.Gson;

import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyUserInfoActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private Button button;
    private ImageView imageView;
    private Character gender;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();
    EditText etName, etBirth, etEmail, etPhone, etPw, etPwChk;
    RadioGroup radioGenderGroup;
    Button btnDelUser, btnModify;
    String userCode, status, coupon;
    Character infoDate, smsCheck;
    Long point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_information);

        Intent login_info = getIntent(); // 메인 액티비티에서 전송한 인텐트를 가져온다.

        String user_id = login_info.getStringExtra("loginedId");
        etName = findViewById(R.id.et_name);
        etBirth = findViewById(R.id.et_birth);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPw = findViewById(R.id.et_passwd);
        etPwChk = findViewById(R.id.ed_passwdcheck);

        GeneralLoginData generalLoginData = new GeneralLoginData(user_id);
        String objJson = gson.toJson(generalLoginData);
        Call<GuserGetInfoResponse> call = service.getguserInfo(objJson);
        call.enqueue(new Callback<GuserGetInfoResponse>() {
            @Override
            public void onResponse(Call<GuserGetInfoResponse> call, Response<GuserGetInfoResponse> response) {
                String str = gson.toJson(response.body());
                System.out.println("json 문자열: " + str);

                GuserGetInfoResponse guserGetInfoResponse = gson.fromJson(str, GuserGetInfoResponse.class);

                etName.setText(guserGetInfoResponse.getName());
                etBirth.setText(guserGetInfoResponse.getBirth());
                etEmail.setText(guserGetInfoResponse.getEmail());
                etPhone.setText(guserGetInfoResponse.getPhone());
                infoDate = guserGetInfoResponse.getInfoDate();;
                smsCheck = guserGetInfoResponse.getSmsCheck();
                point = guserGetInfoResponse.getPoint();
                coupon = guserGetInfoResponse.getCoupon();
                userCode = guserGetInfoResponse.getUserCode();
                status = guserGetInfoResponse.getStatus();
//                etPw.setText(guserGetInfoResponse.getGuserPwd());
//                etPwChk.setText(guserGetInfoResponse.getGuserPwd());
            }

            @Override
            public void onFailure(Call<GuserGetInfoResponse> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });

        imageView = findViewById(R.id.iv_myImage);
        button = findViewById(R.id.bt_fixpic);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE, null);
            }
        });
/*
        btnDelUser = findViewById(R.id.btn_deleteUser);
        btnDelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUser user = new GeneralUser(user_id);
                String objJson = gson.toJson(user);
                Call<DelResponse> del = service.delGeneralUser(objJson);
                del.enqueue(new Callback<DelResponse>() {
                    @Override
                    public void onResponse(Call<DelResponse> call, Response<DelResponse> response) {
                        try {
                            if (response.body().getCode() == 1) {
                                Toast.makeText(ModifyUserInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(ModifyUserInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<DelResponse> call, Throwable t) {

                    }
                });
            }
        });
*/
        btnModify = findViewById(R.id.btn_modify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etPw.getText().toString().equals(etPwChk.getText().toString())) {
                    Toast.makeText(ModifyUserInfoActivity.this, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    String modName = etName.getText().toString();
                    String modBirth = etBirth.getText().toString();
                    String modEmail = etEmail.getText().toString();
                    String modPhone = etPhone.getText().toString();
                    String modPwdChk = etPwChk.getText().toString();
                    GeneralUser user = new GeneralUser(user_id, modPwdChk, modEmail, userCode, modName, status, modBirth, gender, modPhone, infoDate, smsCheck, point, coupon);
                    String objJson = gson.toJson(user);
                    Call<DelResponse> mod = service.modGeneralUser(objJson);
                    mod.enqueue(new Callback<DelResponse>() {
                        @Override
                        public void onResponse(Call<DelResponse> call, Response<DelResponse> response) {
                            try {
                                if (response.body().getCode() == 1) {
                                    Toast.makeText(ModifyUserInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(ModifyUserInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<DelResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });

        radioGenderGroup = findViewById(R.id.rg_category);
        radioGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_male:
                        System.out.println("남성 선택");
                        gender = 'M';
                        break;
                    case R.id.rb_female:
                        System.out.println("여성 선택");
                        gender = 'F';
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}