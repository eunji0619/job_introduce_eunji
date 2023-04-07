package com.example.job_introduce.company;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job_introduce.R;

public class CompanyLocInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_loc_info);

        WebView wb_searcharea_area = findViewById(R.id.wb_searcharea_area);
        wb_searcharea_area.getSettings().setJavaScriptEnabled(true); //웹페이지 세팅
        wb_searcharea_area.addJavascriptInterface(new BridgeInterface(),"Android");

        wb_searcharea_area.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url){ //콜백 형태 -> 자바 스크립트 로딩끝났을때 호출함
                super.onPageFinished(view, url);
                //안드로이드에서 자바 스크립트 함수 호출
                wb_searcharea_area.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        //최초 웹뷰 로드
        wb_searcharea_area.loadUrl("https://searcharea-1f3c0.web.app");
    }

    private class BridgeInterface {
        @JavascriptInterface
        public void processDATA(String data){
            //다음(카카오) 주소 검색 api의 결과 값이 브릿지 통로를 통해 전달 받는다.(from javascript)
            Intent intent = new Intent();
            intent.putExtra("data", data);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}