package com.example.job_introduce.jobPost;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.job_introduce.R;
import com.example.job_introduce.generalUser.WorkerServiceActivity;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class JobPostActivity extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener{
    private MapView mapView;
    private ViewGroup mapViewContainer;;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private static final String LOG_TAG = "TAG";
    private static final String APP_KEY = "2ef955007924551ed7c49f840f3a83cb";
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION};
    Long postId, compId, salary;
    String compName, title, salaryType, career, repName, repPhone, content, workStart, workEnd, managerEmail,
            education, education2, recType, loc, endDate, managerName, managerPhone, workPeriod, workDay, jobType, recNum;
    TextView tvCompanyName, tvTitle, tvSalary, tvSalaryType, tvCareer, tvCompType, tvRepName, tvRepPhone, tvContent, tvRecNum, tvWorkStart, tvWorkEnd, tvManagerEmail,
            tvEducation, tvEducation2, tvRecType, tvLoc, tvEndDate, tvManagerName, tvManagerPhone, tvWorkPeriod, tvWorkDay, tvResumeCheck, tvJobType, tvEndDate2;
    char resumeCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobpost);

        this.InitializeTextView();
        this.getIntentData();
        this.setTextView();

        Button btn_apply_for = findViewById(R.id.btn_jp_applyFor);
        btn_apply_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkerServiceActivity.class);
                intent.putExtra("managerName", managerName);
                intent.putExtra("managerPhone", managerPhone);
                //intent.putExtra("managerPhone", email);
                intent.putExtra("compName", compName);
                intent.putExtra("loc", loc);
                intent.putExtra("workPeriod", workPeriod);
                intent.putExtra("workDay", workDay);
                intent.putExtra("workStart", workStart);
                intent.putExtra("workEnd", workEnd);
                intent.putExtra("workDay", workDay);
                intent.putExtra("salaryType", salaryType);
                intent.putExtra("salary", salary);
                startActivity(intent);
            }
        });
        MapViewController();
    }

    public void InitializeTextView() {
        tvCompanyName = findViewById(R.id.tv_jp_companyName);
        tvTitle = findViewById(R.id.tv_jp_title);
        tvSalary = findViewById(R.id.tv_jp_salary);
        tvSalaryType = findViewById(R.id.tv_jp_salaryType);
        tvCareer = findViewById(R.id.tv_jp_career);
        tvEducation = findViewById(R.id.tv_jp_education);
        tvEducation2 = findViewById(R.id.tv_jp_education2);
        tvRecType = findViewById(R.id.tv_jp_recruitType2);
        tvLoc = findViewById(R.id.tv_jp_loc2);
        tvEndDate = findViewById(R.id.tv_jp_endDate2);
        tvEndDate2 = findViewById(R.id.tv_jp_endDate4);
        tvCompType = findViewById(R.id.tv_jp_comType2);
        tvRepName = findViewById(R.id.tv_jp_repName2);
        tvManagerName = findViewById(R.id.tv_jp_manager2);
        tvManagerPhone = findViewById(R.id.tv_jp_phone4);
        tvWorkDay = findViewById(R.id.tv_jp_workDay2);
        tvResumeCheck = findViewById(R.id.tv_jp_resume2);
        tvWorkStart = findViewById(R.id.tv_jp_workStart);
        tvWorkEnd = findViewById(R.id.tv_jp_workEnd);
        tvContent = findViewById(R.id.tv_jp_content);
        //tvWorkPeriod
        //tvJobType
    }

    public void getIntentData() {
        Intent postInfo = getIntent();
        postId = postInfo.getLongExtra("postId", 1);
        compId = postInfo.getLongExtra("compId",1);
        compName = postInfo.getStringExtra("compName");
        title = postInfo.getStringExtra("title");
        career = postInfo.getStringExtra("career");
        recType = postInfo.getStringExtra("recType");
        loc = postInfo.getStringExtra("loc");
        endDate = postInfo.getStringExtra("endDate");
        salary = postInfo.getLongExtra("salary", 0);
        salaryType = postInfo.getStringExtra("salaryType");
        education = postInfo.getStringExtra("education");
        repName = postInfo.getStringExtra("repName");
        managerName = postInfo.getStringExtra("managerName");
        managerPhone = postInfo.getStringExtra("managerPhone");
        managerEmail = postInfo.getStringExtra("managerEmail");
        workPeriod = postInfo.getStringExtra("workPeriod");
        workDay = postInfo.getStringExtra("workDay");
        resumeCheck = postInfo.getCharExtra("resumeCheck", 'X');
        jobType = postInfo.getStringExtra("jobType");
        workStart = postInfo.getStringExtra("workStart");
        workEnd = postInfo.getStringExtra("workEnd");
        content = postInfo.getStringExtra("content");
        System.out.println("JobPost: " + content);
        //repPhone = postInfo.getStringExtra("repPhone");
    }

    public void setTextView() {
        tvCompanyName.setText(compName);
        tvTitle.setText(title);
        tvCareer.setText(career);
        tvRecType.setText(recType);
        tvLoc.setText(loc);
        tvEndDate.setText(endDate);
        tvEndDate2.setText(endDate);
        tvSalary.setText(String.valueOf(salary));
        tvSalaryType.setText(salaryType);
        tvEducation.setText(education);
        tvRepName.setText(repName);
        tvManagerName.setText(managerName);
        tvManagerPhone.setText(managerPhone);
        tvWorkDay.setText(workDay);
        tvResumeCheck.setText(String.valueOf(resumeCheck));
        workStart += " ~ ";
        tvWorkStart.setText(workStart);
        tvWorkEnd.setText(workEnd);
        tvContent.setText(content);
    }

    public void MapViewController() {
        mapView = new MapView(this);
        mapViewContainer = findViewById(R.id.cl_map);
        //ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.cl_map);
        mapViewContainer.addView(mapView);
        mapView.setZoomLevel(2, true);
        mapView.setMapViewEventListener(this);
        //mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.1855, 129.0879), true);
        if (!checkLocationServicesStatus()) {
            showDialogForLocationServiceSetting();
        }else {
            checkRunTimePermission();
        }
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(35.1855, 129.0879);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("부산광역시 고분로 50-1");
        marker.setTag(0);
        marker.setMapPoint(mapPoint);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);
    }

    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {
        MapPoint.GeoCoordinate mapPointGeo = currentLocation.getMapPointGeoCoord();
        Log.i(LOG_TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
    }
    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {
    }

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {
    }

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {
    }


    private void onFinishReverseGeoCoding(String result) {
//        Toast.makeText(LocationDemoActivity.this, "Reverse Geo-coding : " + result, Toast.LENGTH_SHORT).show();
    }

    // ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드
    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        super.onRequestPermissionsResult(permsRequestCode, permissions, grandResults);
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            boolean check_result = true;

            // 모든 퍼미션을 허용했는지 체크합니다.
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            if (check_result) {
                Log.d("@@@", "start");
                //위치 값을 가져올 수 있음

            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있다
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])) {
                    Toast.makeText(JobPostActivity.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(JobPostActivity.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(JobPostActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED ) {
            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)
            // 3.  위치 값을 가져올 수 있음

        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.
            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(JobPostActivity.this, REQUIRED_PERMISSIONS[0])) {
                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(JobPostActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(JobPostActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(JobPostActivity.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }

    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(JobPostActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하시겠습니까?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case GPS_ENABLE_REQUEST_CODE:
                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {
                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }
                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {


    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }
}
