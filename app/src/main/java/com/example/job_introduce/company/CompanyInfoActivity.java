package com.example.job_introduce.company;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import com.example.job_introduce.R;
import com.example.job_introduce.companyUser.CompanyUser;
import com.example.job_introduce.server.RetrofitClient;
import com.example.job_introduce.server.ServiceApi;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyInfoActivity extends AppCompatActivity {
    Button completBtn, backBtn;
    RadioGroup companyTypeGroup;
    RadioButton major, medium, small, indivi;
    ArrayAdapter<CharSequence> num_adapter;
    EditText etCompName, etCompLoc, etCompDetail, etRepName, etStartPhone, etMidPhone, etEndPhone, etEmpNum;
    Spinner spinnerNum;
    String repPhone, result, fPhone, compType, compLoc, compDetail, compName, loc, repName;
    Intent com_intent;

    private AlertDialog dialog;
    Gson gson = new Gson();
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);

        this.InitializeEditText();
        this.InitializeBtn();
        this.InitializeRadioBtn();

        //com_intent = getIntent();
        String loginedId = "tester1";

        CompanyUser cuser = new CompanyUser(loginedId); // 로그인한 아이디로 CompanyUser 객체 생성
        String objJson = gson.toJson(cuser); // 객체를 Json 문자열로 변환

        // 기업 정보 유무
        Call<ChkResponse> chkRes = service.isPreCompInfo(objJson); // 기업정보 유무 체크
        chkRes.enqueue(new Callback<ChkResponse>() {
            @Override
            public void onResponse(Call<ChkResponse> call, Response<ChkResponse> response) {
                try {
                    if (response.body().getCode() == 0) { // 기업정보 있음: 수정화면
                        Long comp_id = response.body().getCompId(); // 서버로부터 compId 받아옴
                        Company comp = new Company(comp_id); // compId로 Company 객체 생성
                        String objJson = gson.toJson(comp); // 객체를 Json 문자열로 변환

                        Call<CompanyResponse> compRes = service.getCompInfo(objJson); // 기업정보 가져오기
                        compRes.enqueue(new Callback<CompanyResponse>() {
                            @Override
                            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {

                                // spinner변경
                                etStartPhone.setVisibility(View.VISIBLE);
                                spinnerNum.setVisibility(View.INVISIBLE);

                                etCompName.setText(response.body().getCompName()); // 이름

                                compType = response.body().getCompType(); // 기업 형태
                                if (compType.equals("대기업")) {
                                    major.setChecked(true);
                                } else if (compType.equals("중견기업")) {
                                    medium.setChecked(true);
                                } else if (compType.equals("중소기업")) {
                                    small.setChecked(true);
                                } else if (compType.equals("개인")) {
                                    indivi.setChecked(true);
                                }

                                //전화번호 나눠서 setText
                                String[] phoneArr = response.body().getRepPhone().split("-");
                                String firsttphone = phoneArr[0];
                                String midphone = phoneArr[1];
                                String endphone = phoneArr[2];
                                etStartPhone.setText(firsttphone);
                                etMidPhone.setText(midphone);
                                etEndPhone.setText(endphone);

                                etRepName.setText(response.body().getRepName()); // 대표자명

                                //주소 나눠서 setText
                                String[] areaArr = response.body().getLoc().split("/");
                                compLoc = areaArr[0];
                                compDetail = areaArr[1];
                                etCompLoc.setText(compLoc);
                                etCompDetail.setText(compDetail);

                                etEmpNum.setText(response.body().getEmpNum()); // 사원수
                            }

                            @Override
                            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                                Log.e("log", t.getMessage());
                                Toast.makeText(CompanyInfoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else if (response.body().getCode() == 1) { // 기업정보 없음: 등록화면
                        Toast.makeText(CompanyInfoActivity.this, "등록된 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CompanyInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ChkResponse> call, Throwable t) {
                Log.e("log", t.getMessage());
                Toast.makeText(CompanyInfoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // 담당자 휴대폰 Spinner
        spinnerNum = (Spinner) findViewById(R.id.sp_cominfo_spinphone);
        num_adapter = ArrayAdapter.createFromResource(this,
                R.array.region_array, android.R.layout.simple_spinner_item);
        num_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNum.setAdapter(num_adapter);

        spinnerNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // System.out.println("선택 내용 : " + spinnerNum.getSelectedItem().toString());
                // 근데 이걸(if문) 여기다 써도되는지 모르겟음
                fPhone = spinnerNum.getSelectedItem().toString();

                if (etMidPhone.equals("") || etEndPhone.equals("")) {
                    Toast.makeText(CompanyInfoActivity.this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 전번 인증되면 true로 ..
                    repPhone = fPhone + "-" + etEndPhone.toString() + "-" + etEndPhone.toString(); //01011112222 형식
                    System.out.println("대표자 전번 : " + repPhone);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(CompanyInfoActivity.this, "전화번호를 선택하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        // 채용 형태 라디오
        companyTypeGroup = findViewById(R.id.rg_cominfo_comtype);
        companyTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_cominfo_major:
                        System.out.println("R.대기업");
                        result = "대기업";
                        break;
                    case R.id.rb_cominfo_medium:
                        System.out.println("R.중견기업");
                        result = "중견기업";
                        break;
                    case R.id.rb_cominfo_small:
                        System.out.println("R.중소기업");
                        result = "중소기업";
                        break;
                    case R.id.rb_cominfo_indivi:
                        System.out.println("R.개인");
                        result = "개인";
                        break;
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 뒤로가기
                finish();
            }
        });

        completBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                compName = etCompName.getText().toString();
                //System.out.println("기업명");
                compType = result;
                //System.out.println("기업형태");
                compLoc = etCompLoc.getText().toString();
                //System.out.println("주소");
                compDetail = etCompDetail.getText().toString();
                //System.out.println("상세주소");

                loc = compLoc + "/" + compDetail;
                System.out.println("주소 확인 : " + loc);
                //도로명 + / + 상세주소

                repName = etRepName.getText().toString();
                //System.out.println("대표자명");

                /**
                 * 이 부분이 필요한지 모르겠음. 이미 tell 변수에 전화번호 다 합쳐놨음.
                 * 이미 작성한거 안되면 주석 풀기
                 // tell = fPhone + "-" + etEndPhone.toString() + "-" + etEndPhone.toString();
                 **/

//                String compPhone = repPhone;
                System.out.println("전화번호 확인 : " + repPhone);

                // 밑에 싹 수정해야함
                if (compName.equals("")) {

                    etCompName.setSelection(0);
                    return;
                }
                if (compLoc.equals("")) {

                    etCompLoc.setSelection(0);
                    return;
                }
                if (compDetail.equals("")) {

                    etCompDetail.setSelection(0);
                    return;
                }
                if (repName.equals("")) {

                    etRepName.setSelection(0);
                    return;
                }
                if (etMidPhone.equals("") || etEndPhone.equals("")) {

                    if(etEndPhone.equals(""))
                        etEndPhone.setSelection(0);
                    else
                        etMidPhone.setSelection(0);

                    return;
                }
                if (etEmpNum.equals("")) {

                    etEmpNum.setSelection(0);
                    return;
                }

                // 입력 완료 시
                Toast.makeText(CompanyInfoActivity.this, "등록 되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
                int empNum = Integer.parseInt(etEmpNum.getText().toString());

                Company regObj = new Company(compName, compType, loc, repName, repPhone, empNum);
                String objJson = gson.toJson(regObj); // 객체를 Json 문자열로 변환

                Call<ChkResponse> chkRes = service.regCompInfo(objJson); // 입력한 기업정보 서버에 전송
                chkRes.enqueue(new Callback<ChkResponse>() {
                    @Override
                    public void onResponse(Call<ChkResponse> call, Response<ChkResponse> response) {
                        if (response.body().getCode() == 0) { // 성공
                            Toast.makeText(CompanyInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        } else if (response.body().getCode() == 1) { // 실패
                            Toast.makeText(CompanyInfoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ChkResponse> call, Throwable t) {
                        Toast.makeText(CompanyInfoActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    public void InitializeEditText() {
        etCompName = findViewById(R.id.et_cominfo_comname); // 회사명
        etCompLoc = findViewById(R.id.et_cominfo_comarea); // 회사 주소
        etCompDetail = findViewById(R.id.et_cominfo_comdetail); // 상세 주소
        etRepName = findViewById(R.id.et_cominfo_representname); // 대표자명
        etStartPhone = findViewById(R.id.et_cominfo_strphone); // 회사 처음 번호
        etMidPhone = findViewById(R.id.et_cominfo_midphone); // 중간번호
        etEndPhone = findViewById(R.id.et_cominfo_endphone); // 마지막 번호
        etEmpNum = findViewById(R.id.et_cominfo_employeenum); // 사원 수
    }

    public void InitializeBtn() {
        completBtn = findViewById(R.id.bt_cominfo_modi); // 작성 완료
        backBtn = findViewById(R.id.bt_cominfo_cancel); // 뒤로, 취소하기
    }

    public void InitializeRadioBtn() {
        major = findViewById(R.id.rb_cominfo_major);
        medium = findViewById(R.id.rb_cominfo_medium);
        small = findViewById(R.id.rb_cominfo_small);
        indivi = findViewById(R.id.rb_cominfo_indivi);
    }

}
