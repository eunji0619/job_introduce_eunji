package com.example.job_introduce.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;
import com.example.job_introduce.generalUser.GeneralJoinData;
import com.example.job_introduce.generalUser.GeneralLoginActivity;
import com.example.job_introduce.server.RetrofitClient;
import com.example.job_introduce.server.ServiceApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private boolean dupchk = false;

    private int userCat = 0; // 0: 일반, 1: 기업
    private Character gender = 'M'; // M: 남자, F: 여자
    private Character infoDate = '0'; // 0: 1년, 1: 3년, 2: 회원탈퇴시
    private Character smsChk = 'N';
    RadioGroup radioTypeGroup, radioGenderGroup, radioValidityGroup;
    CheckBox cb_all, cb_adult, cb_service, cb_info, cb_sms;
    EditText et_userid, et_pw, et_repw, et_name, et_businum, et_birth, et_email, et_phone, et_certifynum;
    Button btn_dupChk, btn_join;
    ImageView iv_pwsee, iv_repwsee;
    RadioButton rb_individual, rb_corporation, rb_male, rb_female, rb_1year, rb_3year, rb_withdraw;

    Gson gson = new Gson();
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        initializeChkBox();
        initializeEditText();
        initializeBtn();
        initializeView();

        if (userCat == 0) {
            rb_individual.setChecked(true);
            et_businum.setClickable(false);
            et_businum.setFocusable(false);
            et_businum.setHint("-");
            et_businum.setTextColor(Color.parseColor("#808080"));
        }

        if (gender == 'M') {
            rb_male.setChecked(true);
        }

        if (infoDate == '0') {
            rb_1year.setChecked(true);
        }

        btn_join.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String joinId = et_userid.getText().toString();
                String joinPw = et_pw.getText().toString();
                String joinPwCheck = et_repw.getText().toString();
                String joinEmail = et_email.getText().toString();
                String joinName = et_name.getText().toString();
                String joinBirth = et_birth.getText().toString();
                Character joinGender = gender;
                String joinPhone = et_phone.getText().toString();
                Character joinInfoDate = infoDate;
                Character joinSmsChk = smsChk;

                if (cb_adult.isChecked() && cb_service.isChecked() && cb_info.isChecked()) {
                    //일반회원가입
                    if (userCat == 0) {
                        //아이디 중복확인
                        if (dupchk) {
                            //비밀번호 공백체크, 비밀번호 확인여부 체크
                            if (!joinPw.equals("") && joinPw.equals(joinPwCheck)) {
                                GeneralJoinData user;
                                // 이메일 입력 o
                                if (!joinEmail.equals("")) {
                                    if (!joinName.equals("")) {
                                        if (!joinBirth.equals("")) {
                                            if (!joinPhone.equals("")) {
                                                user = new GeneralJoinData(joinId, joinPw, joinEmail, joinName, joinBirth, joinGender, joinPhone, joinInfoDate, joinSmsChk);
                                                String objJson = gson.toJson(user);
                                                generalJoin(objJson);
                                                Intent intent = new Intent(getApplicationContext(), GeneralLoginActivity.class);
                                                startActivity(intent);
                                                System.out.println("회원가입성공");
                                                finish();
                                            } else
                                                Toast.makeText(JoinActivity.this, "휴대폰번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                                        } else
                                            Toast.makeText(JoinActivity.this, "생년월일을 입력하세요.", Toast.LENGTH_SHORT).show();

                                    } else
                                        Toast.makeText(JoinActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                                }
                                // 이메일 입력 x
                                else {
                                    if (!joinName.equals("")) {
                                        if (!joinBirth.equals("")) {
                                            if (!joinPhone.equals("")) {
                                                user = new GeneralJoinData(joinId, joinPw, joinName, joinBirth, joinGender, joinPhone, joinInfoDate, joinSmsChk);
                                                String objJson = gson.toJson(user);
                                                generalJoin(objJson);
                                                Intent intent = new Intent(getApplicationContext(), GeneralLoginActivity.class);
                                                startActivity(intent);
                                                System.out.println("회원가입성공");
                                                finish();
                                            } else
                                                Toast.makeText(JoinActivity.this, "휴대폰번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                                        } else
                                            Toast.makeText(JoinActivity.this, "생년월일을 입력하세요.", Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(JoinActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                                }
                                System.out.println("회원가입실패");
                            } else {
                                Toast.makeText(JoinActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                                System.out.println("비밀번호 확인해라");
                            }


                        } else {
                            Toast.makeText(JoinActivity.this, "아이디 중복체크를 해주세요.", Toast.LENGTH_SHORT).show();
                            System.out.println("아이디 중복체크해라");
                        }
                    }
                } else {
                    System.out.println("동의안했음");
                    Toast.makeText(JoinActivity.this, "필수 약관에 동의하여주십시오.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //아이디 중복확인
        btn_dupChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joinId = et_userid.getText().toString();
                System.out.println(joinId);
                if (!joinId.equals("")) {
                    Call<DupResponse> dup = service.guserIdDupChk(joinId);
                    dup.enqueue(new Callback<DupResponse>() {
                        @Override
                        public void onResponse(Call<DupResponse> call, Response<DupResponse> response) {
                            if (response.body().getCode() == 1) {
                                dupchk = true;
                                //btn_dupChk.setClickable(false);
                                Toast.makeText(JoinActivity.this, "사용하실 수 있는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            } else if (response.body().getCode() == 0){
                                Toast.makeText(JoinActivity.this, "이미 가입된 아이디입니다.", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(JoinActivity.this, "R통신 오류.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<DupResponse> call, Throwable t) {
                            Toast.makeText(JoinActivity.this, "통신 오류", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else
                    Toast.makeText(JoinActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 비밀번호 입력
        iv_pwsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (iv_pwsee.getTag().equals("0")) {  //비밀번호 안 보일때
                    iv_pwsee.setTag("1");
                    iv_pwsee.setImageResource(R.drawable.ic_eye);//켜져있는 아이콘으로 바꿈
                    et_pw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//비밀번호를 보이게
                } else { //비밀번호 보일때
                    iv_pwsee.setTag("0");
                    iv_pwsee.setImageResource(R.drawable.ic_no_eye);//꺼져있는 아이콘으로 바꿈
                    et_pw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);//비밀번호 숨김
                }
                et_repw.setSelection(et_repw.getText().length()); //커서 맨 뒤로

            }
        });

        // 비밀번호 재입력
        iv_repwsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iv_repwsee.getTag().equals("0")) {
                    iv_repwsee.setTag("1");
                    iv_repwsee.setImageResource(R.drawable.ic_eye);
                    et_repw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    iv_repwsee.setTag("0");
                    iv_repwsee.setImageResource(R.drawable.ic_no_eye);
                    et_repw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                et_repw.setSelection(et_repw.getText().length());
            }
        });

        // 개인, 기업 라디오
        radioTypeGroup = findViewById(R.id.rg_usertype);
        radioTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_individual:
                        System.out.println("개인 선택 완료");
                        et_businum.setClickable(false);
                        et_businum.setFocusable(false);
                        et_businum.setHint("-");
                        et_businum.setTextColor(Color.parseColor("#808080"));
                        setUserCat(0);

                        break;
                    case R.id.rb_corporation:
                        System.out.println("기업 선택 완료");
                        et_businum.setFocusableInTouchMode(true);
                        et_businum.setFocusable(true);
                        et_businum.setHint("사업자등록번호(-제외 번호입력)");
                        et_businum.setTextColor(Color.parseColor("#808080"));
                        setUserCat(1);
                        break;
                }
            }
        });

        // 성별 라디오
        radioGenderGroup = findViewById(R.id.rg_gender);
        radioGenderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_male:
                        System.out.println("남성 선택");
                        setGender('M');
                        break;
                    case R.id.rb_female:
                        System.out.println("여성 선택");
                        setGender('F');
                        break;
                }
            }
        });

        // 유효기간 라디오
        radioValidityGroup = findViewById(R.id.rg_validity);
        radioValidityGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1year:
                        System.out.println("1년 선택");
                        infoDate = '0';
                        break;
                    case R.id.rb_3year:
                        System.out.println("3년 선택");
                        infoDate = '1';
                        break;
                    case R.id.rb_withdraw:
                        System.out.println("회원 탈퇴시 선택");
                        infoDate = '2';
                        break;
                }
            }
        });
    }

    public void setUserCat(int userCat) {
        this.userCat = userCat;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public void setInfoDate(Character infoDate) {
        this.infoDate = infoDate;
    }

    public void setSmsChk(Character smsChk) {
        this.smsChk = smsChk;
    }

    public void initializeChkBox() {
        // 체크박스
        cb_all = (CheckBox) findViewById(R.id.cb_all);
        cb_adult = (CheckBox) findViewById(R.id.cb_adult);
        cb_service = (CheckBox) findViewById(R.id.cb_service);
        cb_info = (CheckBox) findViewById(R.id.cb_info);
        cb_sms = (CheckBox) findViewById(R.id.cb_sms);
        cb_all.setOnCheckedChangeListener(this);
        cb_adult.setOnCheckedChangeListener(this);
        cb_service.setOnCheckedChangeListener(this);
        cb_info.setOnCheckedChangeListener(this);
        cb_sms.setOnCheckedChangeListener(this);
    }

    public void initializeEditText() {
        et_userid = findViewById(R.id.et_userid);
        et_pw = findViewById(R.id.et_pw);
        et_repw = findViewById(R.id.et_repw);
        et_name = findViewById(R.id.et_name);
        et_businum = findViewById(R.id.et_businum);
        et_birth = findViewById(R.id.et_birth);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
    }

    public void initializeBtn() {
        btn_dupChk = findViewById(R.id.btn_dupChk);
        btn_join = findViewById(R.id.btn_join);

        rb_individual = findViewById(R.id.rb_individual);
        rb_corporation = findViewById(R.id.rb_corporation);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);
        rb_1year = findViewById(R.id.rb_1year);
        rb_3year = findViewById(R.id.rb_3year);
        rb_withdraw = findViewById(R.id.rb_withdraw);
    }

    public void initializeView() {
        iv_pwsee = findViewById(R.id.iv_pwsee);
        iv_repwsee = findViewById(R.id.iv_repwsee);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String result = "";

        if (cb_all.isChecked()) {
            cb_adult.setChecked(true);
            cb_service.setChecked(true);
            cb_info.setChecked(true);
            cb_sms.setChecked(true);
            result += "1 ";
        }
//        else {
//            cb_adult.setChecked(false);
//            cb_service.setChecked(false);
//            cb_info.setChecked(false);
//            cb_sms.setChecked(false);
//        }

        if (cb_adult.isChecked())
            result += "2 ";

        if (cb_service.isChecked())
            result += "3 ";

        if (cb_info.isChecked())
            result += "4 ";

        if (cb_sms.isChecked()) {
            result += "5 ";
            setSmsChk('T');
        } else
            setSmsChk('F');

        System.out.println("result : " + result);
    }

    private void generalJoin(String json) {
        Call<JoinResponse> join = service.guserJoin(json);
        join.enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                try {
                    if (response.body().getCode() == 1) {
                        Toast.makeText(JoinActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(JoinActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {

            }
        });
    }
}
