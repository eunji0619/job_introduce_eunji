package com.example.job_introduce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.job_introduce.partnership.HelperList;
import com.example.job_introduce.jobPost.JobPostActivity;
import com.example.job_introduce.jobPost.JobPostListActivity;
import com.example.job_introduce.jobSeeker.SeekerRegist;
import com.example.job_introduce.generalUser.GeneralLoginActivity;
import com.example.job_introduce.myPage.MyPageActivity;
import com.example.job_introduce.server.RetrofitClient;
import com.example.job_introduce.server.ServiceApi;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private BackKeyHandler backKeyHandler = new BackKeyHandler(this);
    public static Activity _Main_Activity;
    private Intent main_intent;
    private int isLogin = 0;
    private String loginedId;
    private String loginedName;
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();
    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    //private ConstraintLayout layout_main;
    private BottomNavigationView bottomNavigationView;
    final String TAG = this.getClass().getSimpleName();
    GeneralLoginActivity GLA;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private final HomeFragment homeFragment = new HomeFragment();
    private final ScrapFragment scrapFragment = new ScrapFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _Main_Activity = MainActivity.this;
        GLA = (GeneralLoginActivity) GeneralLoginActivity._GeneralLogin_Activity;
        //GLA.finish();
        main_intent = getIntent();
        isLogin = main_intent.getIntExtra("islogin",0);
        loginedId = main_intent.getStringExtra("loginedId");
        loginedName = main_intent.getStringExtra("loginedName");
        LoginCheck();
        this.InitializeDrawerLayout();

        getHashKey();
        init(); //객체 정의
        SettingListener(); //리스너 등록
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main, homeFragment).commitAllowingStateLoss();
        //맨 처음 시작할 탭 설정
        bottomNavigationView.setSelectedItemId(R.id.home);

        //마이페이지 버튼
        ImageView mypage_btn = (ImageView) findViewById(R.id.iv_toolbar_mypage);
        mypage_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                intent.putExtra("islogin", isLogin);
                intent.putExtra("loginedId", loginedId);
                intent.putExtra("loginedName", loginedName);
                startActivity(intent);
            }
        });
    }

    public void LoginCheck() {
        if (isLogin == 0) {
            Intent inLogin = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(inLogin);
            finish();
        }
        else
            return;
    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    private void init() {
        //layout_main = findViewById(R.id.layout_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void SettingListener() {
        //선택 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new TabSelectedListener());
    }

    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (menuItem.getItemId()) {
                case R.id.tab_home: {
                    transaction.replace(R.id.fl_main, homeFragment).commitAllowingStateLoss();

                    break;
                }
                case R.id.tab_scrap: {
                    transaction.replace(R.id.fl_main, scrapFragment).commitAllowingStateLoss();

                    break;
                }
                case R.id.tab_refresh: {
                    transaction.replace(R.id.fl_main, scrapFragment).commitAllowingStateLoss();
                    break;
                }
            }
            return true;
        }
    }

    public void nextLayout_regSeek(View v) {
        int id = v.getId();

        Intent it = new Intent(this, SeekerRegist.class);
        startActivity(it);
    }

    public void nextLayout_search(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, SearchActivity.class);
        startActivity(it);
    }

    public void nextLayout_helper(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, HelperList.class);
        startActivity(it);
    }

    public void nextLayout_jobList(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, JobPostListActivity.class);
        startActivity(it);
    }

    public void nextLayout_jobPost(View v) {
        int id = v.getId();
        View view = v.findViewById(id);

        Intent it = new Intent(this, JobPostActivity.class);
        startActivity(it);
    }

    //뒤로가기
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backKeyHandler.onBackPressed();
            //super.onBackPressed();
        }
    }

    @SuppressLint({"NonConstantResourceId", "RtlHardcoded"})
    public void InitializeDrawerLayout() {
        ivMenu = findViewById(R.id.iv_menu);
        drawerLayout = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        ivMenu.setOnClickListener(v -> {
            Log.d(TAG, "onClick: 클릭됨");
            drawerLayout.openDrawer(Gravity.LEFT);
        });

        //네비게이션 뷰 아이템 클릭시 동작 수행
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);

            String title = menuItem.getTitle().toString();
            switch(menuItem.getItemId())
            {
                case R.id.menu_my_info:
                    Toast.makeText(getApplicationContext(), title + "를 확인합니다", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "마이페이지");
                    return true;
//                    if(isLogin == 0){
//                        Intent it = new Intent(this, LoginActivity.class);
//                        startActivity(it);
//                    }else{
//                        Intent it = new Intent(this, MyPageActivity.class);
//                        it.putExtra("loginedId", loginedId);
//                        it.putExtra("loginedName", loginedName);
//                        it.putExtra("islogin", isLogin);
//                        startActivity(it);
//                    }
//                    return true;

                case R.id.menu_notice:
                    Toast.makeText(getApplicationContext(), title + "을 확인합니다", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "공지사항");
//                    Intent intent_an = new Intent(getApplicationContext(), AnnouncementActivity.class);
//                    startActivity(intent_an);
                    return true;

                case R.id.menu_scrap:
                    Toast.makeText(getApplicationContext(), title + "을 확인합니다", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "스크랩");
//                    Intent intent_an = new Intent(getApplicationContext(), AnnouncementActivity.class);
//                    startActivity(intent_an);
                    return true;

                case R.id.menu_service_center:
                    Toast.makeText(getApplicationContext(), title + "에 접속합니다", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "고객센터");
//                    Intent intent_sc = new Intent(this, ServiceCenter.class);
//                    startActivity(intent_sc);
                    return true;

                case R.id.menu_policy:
                    Toast.makeText(getApplicationContext(), title + "을 확인합니다", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "약관 및 정책");
                    return true;

                case R.id.menu_logout:
                    Toast.makeText(getApplicationContext(), title + "합니다", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "로그아웃");
                    return true;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }
}