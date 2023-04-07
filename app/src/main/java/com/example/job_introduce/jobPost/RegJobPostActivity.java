package com.example.job_introduce.jobPost;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.job_introduce.R;
import com.example.job_introduce.server.RetrofitClient;
import com.example.job_introduce.server.ServiceApi;
import com.google.gson.Gson;

import java.util.Calendar;

public class RegJobPostActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    private boolean comregiInfo = false; // 기업 정보 등록 여부
    private boolean representPhone = false; // 담당자 휴대폰 번호 인증 여부

    private int postId = 10000;
    private Character workDay = '0'; // 0:평일, 1:월욜 ~ 7:일욜, 8:주말
    private Character education = '0'; // 0:무관, 1:초졸, 2:중졸, 3:고졸, 4:대졸(2,3), 5:대졸(4년이상)
    private String career = "0"; // 0:무관 1:신입 2:경력(+n년이상)
    private Character workType = '0'; // 0:정규직, 1:계약, 2:단기근무
    private Character recruitment = '0'; // 0:선착, 1:이력서, 2:면접
    private Character salaryType = '0'; // 0:시급, 1:일급, 2:주급, 3:월급
    private Character resume = 'N'; // Y/N
    private String regist = "1"; // 1:1주, 2:2주, 3:주, 4:1개월 5:직접입력(/날짜)
    private String managerPhone, startTime, endTime, finaledu, workPeriod, workday= "";


    Gson gson = new Gson();
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    TextView startworkDate, endworkDate, registDate, compaName, comType, comArea, comAddr, representName, phone1, phone11, phone12, salary, employNum ;
    Button calbtn, infomodibtn, regibtn;
    EditText experEdit, titleEdit, personnameEdit, phone21Edit, phone22Edit, writeExEdit, recruitNumEdit, salaryEdit, detaileEdit, occupEdit;
    CheckBox weekdayCheck, weekendCheck, monCheck, tueCheck, wedCheck, thuCheck, friCheck, satCheck, sunCheck;
    RadioGroup RadioRecruitGroup, RadioMoneyGroup, RadioResumeGroup, RadioRegistGroup, RadioCareerGroup, RadioEmploGroup;
    ArrayAdapter<CharSequence> num2_adapter, finaledu_adapter, startTime_adapter, endTime_adapter;
    ConstraintLayout datelayout;
    Spinner spinnerNum2, spinnerStartTime, spinnerEndTime, spinnerFinalEdu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_regi);

        this.InitializeElements(); // 요소 정의
        this.companyInfoIsReg(); // 기업정보 등록여부 체크
        this.modifyCompInfo(); // 회사 정보 수정 버튼
        this.setMPhoneSpinner(); // 담당자 휴대폰 Spinner
        this.setWorkTimeSpinner(); // 근무 시간 Spinner
        this.setEducationSpinner(); // 최종 학력 Spinner
        this.setWorkPeriodDialog(); // 캘린더 Dialog, 근무 기간, 공고 등록 기간
        this.setRecTypeRadio(); // 채용 방식 Radio
        this.setSalaryTypeRadio(); // 급여 형태 Radio
        this.setResumeRadio(); // 이력서여부 Radio
        this.setCareerRadio(); // 경력 Radio
        this.setEmpTypeRadio(); // 고용형태 Radio
        this.setRegPeriodRadio(); // 등록기간 Radio
        this.chkIsNull(); // 등록 버튼 클릭시 이벤트 처리, null 체크
    }

    // 요소 정의
    public void InitializeElements() {
        compaName = (TextView) findViewById(R.id.et_name);
        comType = (TextView) findViewById(R.id.tv_comtype);
        comArea = (TextView) findViewById(R.id.sp_area);
        comAddr = (TextView) findViewById(R.id.et_addr);
        representName = (TextView) findViewById(R.id.et_representname);
        phone1 = (TextView) findViewById(R.id.sp_phone1);
        phone11 = (TextView) findViewById(R.id.et_phone11);
        phone12 = (TextView) findViewById(R.id.et_phone12);
        phone21Edit = (EditText) findViewById(R.id.et_phone21);
        phone22Edit = (EditText) findViewById(R.id.et_phone22);
        salary = (TextView) findViewById(R.id.et_sales);
        employNum = (TextView) findViewById(R.id.et_num_employee);
        startworkDate = findViewById(R.id.tv_startworkdate); // 근무 기간 (시작)
        endworkDate = findViewById(R.id.tv_endworkdate); // 근무 기간 (종료)
        registDate = findViewById(R.id.tv_regist_period); // 공고 등록 기간
        //calbtn = findViewById(R.id.btn_regist_calendar);
        titleEdit = findViewById(R.id.et_title);
        personnameEdit = findViewById(R.id.et_personname);


        // 희망 근무 요일 체크박스
        weekdayCheck = (CheckBox) findViewById(R.id.cb_weekday);
        weekdayCheck.setOnCheckedChangeListener(this);
        weekendCheck = (CheckBox) findViewById(R.id.cb_weekend);
        weekendCheck.setOnCheckedChangeListener(this);
        monCheck = (CheckBox) findViewById(R.id.cb_mon);
        monCheck.setOnCheckedChangeListener(this);
        tueCheck = (CheckBox) findViewById(R.id.cb_tue);
        tueCheck.setOnCheckedChangeListener(this);
        wedCheck = (CheckBox) findViewById(R.id.cb_wed);
        wedCheck.setOnCheckedChangeListener(this);
        thuCheck = (CheckBox) findViewById(R.id.cb_thu);
        thuCheck.setOnCheckedChangeListener(this);
        friCheck = (CheckBox) findViewById(R.id.cb_fri);
        friCheck.setOnCheckedChangeListener(this);
        satCheck = (CheckBox) findViewById(R.id.cb_sat);
        satCheck.setOnCheckedChangeListener(this);
        sunCheck = (CheckBox) findViewById(R.id.cb_sun);
        sunCheck.setOnCheckedChangeListener(this);
    }

    // 기업정보 등록여부 체크
    public void companyInfoIsReg() {
        if(!compaName.equals("")&&!comType.equals("")&&!comArea.equals("")&&
                !comAddr.equals("")&&!representName.equals("")&&!phone1.equals("")&&
                !phone11.equals("")&&!phone12.equals("")&&!salary.equals("")&&!employNum.equals("")){
            comregiInfo = true;
        }else{
            comregiInfo = false;
        }
    }

    // 회사 정보 수정 버튼
    public void modifyCompInfo() {
        infomodibtn = (Button)findViewById(R.id.bt_modify_com);
        infomodibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회사 정보 수정 화면으로 이동
            }
        });
    }

    // 담당자 휴대폰 Spinner
    public void setMPhoneSpinner() {
        spinnerNum2 = (Spinner) findViewById(R.id.sp_phone2);
        num2_adapter = ArrayAdapter.createFromResource(this,
                R.array.phone_array, android.R.layout.simple_spinner_item);
        num2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNum2.setAdapter(num2_adapter);

        spinnerNum2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // System.out.println("선택 내용 : " + spinnerNum2.getSelectedItem().toString());

                if(phone21Edit.equals("")||phone22Edit.equals("")){
                    Toast.makeText(RegJobPostActivity.this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    representPhone = false;
                }else{
                    // 인증되면 true로 ..
                    representPhone = true;
                    managerPhone = spinnerNum2.toString() + phone22Edit.toString() + phone22Edit.toString();
                    System.out.println("대표자 전번 : "+managerPhone);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(RegJobPostActivity.this, "전화번호를 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 근무 시간 Spinner
    public void setWorkTimeSpinner() {
        spinnerStartTime = (Spinner) findViewById(R.id.sp_starttime);
        spinnerEndTime = (Spinner) findViewById(R.id.sp_endtime);
        // 시작 시간
        startTime_adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_starttime, android.R.layout.simple_spinner_item);
        startTime_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStartTime.setAdapter(startTime_adapter);

        spinnerStartTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerStartTime.equals("시작시간")){
                    Toast.makeText(RegJobPostActivity.this, "근무시작시간을 선택하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    startTime = spinnerStartTime.getSelectedItem().toString();
                    System.out.println("근무시작시간 : " + startTime);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(RegJobPostActivity.this, "근무시작시간을 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 종료 시간
        endTime_adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_endtime, android.R.layout.simple_spinner_item);
        endTime_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEndTime.setAdapter(endTime_adapter);

        spinnerEndTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(endTime_adapter.equals("종료시간")){
                    Toast.makeText(RegJobPostActivity.this, "근무종료시간을 선택하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    endTime = spinnerEndTime.getSelectedItem().toString();
                    System.out.println("근무종료시간 : " + endTime);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(RegJobPostActivity.this, "근무종료시간을 선택하세요.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // 최종 학력 spinner
    public void setEducationSpinner() {
        spinnerFinalEdu = (Spinner) findViewById(R.id.sp_final_edu);
        finaledu_adapter = ArrayAdapter.createFromResource(this,
                R.array.finalEdu, android.R.layout.simple_spinner_item);
        finaledu_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFinalEdu.setAdapter(finaledu_adapter);

        spinnerFinalEdu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerFinalEdu.equals("최종학력 선택")){
                    Toast.makeText(RegJobPostActivity.this, "최종학력을 선택하세요.", Toast.LENGTH_SHORT).show();
                }else{
                    finaledu = spinnerFinalEdu.getSelectedItem().toString();
                    System.out.println("최종학력 : " + finaledu);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(RegJobPostActivity.this, "최종학력을 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 캘린더 Dialog
    // 근무 기간, 공고 등록 기간
    public void setWorkPeriodDialog() {
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // 근무 기간 _ start
        startworkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegJobPostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String chanmonth = String.format("%02d", month);
                        String date = year + "/" + chanmonth + "/" + dayOfMonth;
                        System.out.println("btn1 date : " + date);
                        startworkDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        // 근무 기간 _ end
        endworkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegJobPostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String chanmonth = String.format("%02d", month);
                        String date = year + "/" + chanmonth + "/" + dayOfMonth;
                        System.out.println("btn1 date : " + date);
                        endworkDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        workPeriod = startworkDate.toString()+"~"+endworkDate.toString();
        System.out.println("근무 기간 : "+workPeriod);

        // 공고 등록 기간
        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegJobPostActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String chanmonth = String.format("%02d", month);
                        String date = year + "/" + chanmonth + "/" + dayOfMonth;
                        System.out.println("btn2 date : " + date);
                        registDate.setText(date);
                        date = "5/" + date;
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    // 채용 형태 라디오
    public void setRecTypeRadio() {
        RadioRecruitGroup = findViewById(R.id.rg_recruit);
        RadioRecruitGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_firstcome:
                        System.out.println("선착순 선택");
                        break;
                    case R.id.rb_resume:
                        System.out.println("이력서 선택");
                        break;
                    case R.id.rb_interview:
                        System.out.println("면접 선택");
                        break;
                }
            }
        });
    }

    // 급여 형태 라디오
    public void setSalaryTypeRadio() {
        RadioMoneyGroup = findViewById(R.id.rg_money);
        RadioMoneyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hourly:
                        System.out.println("시급 선택");
                        break;
                    case R.id.rb_everyday:
                        System.out.println("일급 선택");
                        break;
                    case R.id.rb_weekly:
                        System.out.println("주급 선택");
                        break;
                    case R.id.rb_monthly:
                        System.out.println("월급 선택");
                        break;
                }
            }
        });
    }

    // 이력서 라디오
    public void setResumeRadio() {
        RadioResumeGroup = findViewById(R.id.rg_resume_status);
        RadioResumeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_yesresume:
                        System.out.println("필요 선택");
                        break;
                    case R.id.rb_noresume:
                        System.out.println("불필요 선택");
                        break;
                }
            }
        });
    }

    // 경력 라디오
    public void setCareerRadio() {
        RadioCareerGroup = findViewById(R.id.rg_careerType);
        experEdit = findViewById(R.id.et_write_ex);
        RadioCareerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_irr_experience:
                        experEdit.setText(null);
                        experEdit.setClickable(false);
                        experEdit.setFocusableInTouchMode(false);
                        experEdit.setFocusable(false);
                        System.out.println("경력무관 선택");
                        break;
                    case R.id.rb_newcomer:
                        experEdit.setText(null);
                        experEdit.setClickable(false);
                        experEdit.setFocusableInTouchMode(false);
                        experEdit.setFocusable(false);
                        System.out.println("신입 선택");
                        break;
                    case R.id.rb_experience:
                        experEdit.setClickable(true);
                        experEdit.setFocusableInTouchMode(true);
                        experEdit.setFocusable(true);
                        System.out.println("경력 선택");
                        break;
                }
            }
        });
    }

    // 고용 형태 라디오
    public void setEmpTypeRadio() {
        RadioEmploGroup = findViewById(R.id.rg_emploType);
        RadioEmploGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_permanent:
                        System.out.println("정규직 선택");
                        break;
                    case R.id.rb_contract:
                        System.out.println("계약직 선택");
                        break;
                    case R.id.rb_shortwork:
                        System.out.println("단기근무 선택");
                        break;
                }
            }
        });
    }

    // 등록 기간 라디오
    public void setRegPeriodRadio() {
        RadioRegistGroup = findViewById(R.id.rg_regist_period);
        datelayout = findViewById(R.id.layout_calchoice);
        RadioRegistGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_oneweek:
                        System.out.println("1주 선택");
                        datelayout.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_twoweek:
                        System.out.println("2주 선택");
                        datelayout.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_threeweek:
                        System.out.println("3주 선택");
                        datelayout.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_onemonth:
                        System.out.println("1개월 선택");
                        datelayout.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rb_choiceday:
                        System.out.println("날짜선택");
                        datelayout.setVisibility(View.VISIBLE);
                        registDate.setText(null);
                        break;
                }
            }
        });
    }

    //등록버튼 클릭시 이벤트, null 체크
    public void chkIsNull() {
        regibtn = (Button) findViewById(R.id.bt_opening_regist);
        regibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!titleEdit.equals("")){
                    // 공고 제목 입력
                    if(comregiInfo){
                        // 기업 정보 등록 여부
                        if(!personnameEdit.equals("")){
                            // 담당자명 입력
                            if(representPhone){
                                // 담당자 휴대폰 인증 여부
                                if(!startworkDate.equals("")&&!endworkDate.equals("")){
                                    // 근무 기간
                                    if(weekdayCheck.isChecked()||weekendCheck.isChecked()||monCheck.isChecked()||
                                            tueCheck.isChecked()||wedCheck.isChecked()||thuCheck.isChecked()||
                                            friCheck.isChecked()||satCheck.isChecked()||sunCheck.isChecked()){
                                        // 근무 요일 체크
                                        if(!spinnerStartTime.equals("시작시간")&&!spinnerEndTime.equals("종료시간")){
                                            // 근무 시간
                                            if(!spinnerFinalEdu.equals("최종학력 선택")){
                                                // 최종학력 선택
                                                if(!recruitNumEdit.equals("")){
                                                    // 고용인원
                                                    if(!salaryEdit.equals("")){
                                                        // 급여
                                                        if(!detaileEdit.equals("")){
                                                            // 상세 내용
                                                            if(!occupEdit.equals("")){
                                                                // 업직종
                                                                postId += 1; // 게시글 번호 +1

                                                                // JobPostData jobPostData = new JobPostData(postId, titleEdit);
                                                                System.out.println("공고등록 완료");
                                                                finish();

                                                            }else
                                                                Toast.makeText(RegJobPostActivity.this, "업직종을 입력하세요.", Toast.LENGTH_SHORT).show();
                                                        }else
                                                            Toast.makeText(RegJobPostActivity.this, "상세정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                                                    }else
                                                        Toast.makeText(RegJobPostActivity.this, "급여를 입력하세요.", Toast.LENGTH_SHORT).show();
                                                }else
                                                    Toast.makeText(RegJobPostActivity.this, "고용인원을 입력하세요.", Toast.LENGTH_SHORT).show();
                                            }else
                                                Toast.makeText(RegJobPostActivity.this, "최종학력을 선택하세요.", Toast.LENGTH_SHORT).show();
                                        }else
                                            Toast.makeText(RegJobPostActivity.this, "근무시간을 선택하세요.", Toast.LENGTH_SHORT).show();
                                    }else
                                        Toast.makeText(RegJobPostActivity.this, "근무요일을 선택하세요.", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(RegJobPostActivity.this, "근무기간을 선택하세요.", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(RegJobPostActivity.this, "휴대폰인증을 하세요.", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(RegJobPostActivity.this, "담당자명을 입력하세요.", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(RegJobPostActivity.this, "기업정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(RegJobPostActivity.this, "공고제목을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (weekdayCheck.isChecked()) {
            workday += "평일 ";
        }
        if (weekendCheck.isChecked()) {
            workday += "주말 ";
        }
        if (monCheck.isChecked()) {
            workday += "월 ";
        }
        if (tueCheck.isChecked()) {
            workday += "화 ";
        }
        if (wedCheck.isChecked()) {
            workday += "수 ";
        }
        if (thuCheck.isChecked()) {
            workday += "목 ";
        }
        if (friCheck.isChecked()) {
            workday += "금 ";
        }
        if (satCheck.isChecked()) {
            workday += "토 ";
        }
        if (sunCheck.isChecked()) {
            workday += "일 ";
        }

        System.out.println("result : " + workday);

    }
}
