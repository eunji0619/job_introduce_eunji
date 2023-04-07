package com.example.job_introduce.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class CompanyInfo extends AppCompatActivity {

    EditText et_cominfo_comarea;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);

        et_cominfo_comarea = findViewById(R.id.et_cominfo_comarea);
        et_cominfo_comarea.setFocusable(false);
        et_cominfo_comarea.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                // 주소 검색 웹뷰 화면으로 이동
                Intent intent = new Intent(CompanyInfo.this, CompanyLocInfoActivity.class);
                getSearchResult.launch(intent);
            }
        });
    }
    //콜백을 받는 부분
    private final ActivityResultLauncher<Intent> getSearchResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> { //람다식
                // SearchArea 로부터의 결과 값이 이곳으로 전달 됨(setResult)
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData()!=null){ //객체가 비어있지 않다면
                        String data = result.getData().getStringExtra("data");
                        et_cominfo_comarea.setText(data);
                    }
                }
            }
    );


    /*// 인터넷 연결 확인
    public class NetworkStatus {
        public static final int TYPE_WIFI = 1;
        public static final int TYPE_MOBILE = 2;
        public static final int TYPE_NOT_CONNECTED = 3;

        public int getConnectivityStatus(Context context){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo != null){
                int type = networkInfo.getType();
                if(type == ConnectivityManager.TYPE_MOBILE){ //모바일 (LTE, 5G)
                    return TYPE_MOBILE;
                }else if(type == ConnectivityManager.TYPE_WIFI){ //WIFI
                    return TYPE_WIFI;
                }
            }
            return TYPE_NOT_CONNECTED;  //연결 X
        }
    }*/

}