package com.example.job_introduce.jobPost;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.job_introduce.company.Company;
import com.example.job_introduce.R;
import com.example.job_introduce.server.RetrofitClient;
import com.example.job_introduce.server.ServiceApi;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobPostListActivity extends AppCompatActivity {
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    Gson gson = new Gson();
    JobPostListItem jobPostListItem;
    List<JobPost> jobPostList;
    Company company;
    Long postId, compId, salary;
    String compName, title, career, recType, loc, endDate, salaryType, education, education2, compType,
            locDetail, repName, repPhone, content, managerName, managerPhone, managerEmail, workPeriod, workDay, jobType, recNum, workStart, workEnd;
    char resumeCheck;
    RecyclerView recyclerView;
    JobPostAdapter jobPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_list);

        jobPostList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_jobPostList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Call<JobPostListItem> call = service.jobPostListLoad();
        Log.d("JobPostListActivity: ", "Request 전");
        call.enqueue(new Callback<JobPostListItem>() {
            @Override
            public void onResponse(Call<JobPostListItem> call, Response<JobPostListItem> response) {
                String str = gson.toJson(response.body());
                System.out.println("json 문자열: " + str);

                jobPostListItem = gson.fromJson(str, JobPostListItem.class);
                Log.d("MainActivity", jobPostListItem.toString());
                jobPostList = jobPostListItem.body;

                jobPostAdapter = new JobPostAdapter(getApplicationContext(), jobPostList);
                recyclerView.setAdapter(jobPostAdapter);

                jobPostAdapter.setOnItemClickListener(new JobPostAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int pos) {
                        JobPost item = jobPostList.get(pos);
                        company = jobPostList.get(pos).getCompId();

                        postId = item.getPostId();
                        title = item.getTitle();
                        career = item.getCareer();
                        recType = item.getRecType();
                        endDate = item.getEndDate();
                        salary = item.getSalary();
                        salaryType = item.getSalaryType();
                        education = item.getEducation();
                        managerName = item.getManagerName();
                        managerPhone = item.getManagerPhone();
                        managerEmail = item.getManagerEmail();
                        workPeriod = item.getWorkPeriod();
                        workDay = item.getWorkDay();
                        resumeCheck = item.getResumeCheck();
                        jobType = item.getJobType();
                        recNum = item.getRecNum();
                        workStart = item.getWorkStart();
                        workEnd = item.getWorkEnd();
                        content = item.getContent();

                        compId = company.getCompId();
                        compName = company.getCompName();
                        loc = company.getLoc();
                        compType = company.getCompType();
                        repName = company.getRepName();
                        repPhone = company.getRepPhone();

                        System.out.println("JobPostList: " + content);
                        Toast.makeText(JobPostListActivity.this, item.getTitle() + " Click event", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(v.getContext(), JobPostActivity.class);
                        intent.putExtra("postId", postId);
                        intent.putExtra("compId", compId);
                        intent.putExtra("compName", compName);
                        intent.putExtra("title", title);
                        intent.putExtra("career", career);
                        intent.putExtra("recType", recType);
                        intent.putExtra("loc", loc);
                        intent.putExtra("endDate", endDate);
                        intent.putExtra("salary", salary);
                        intent.putExtra("salaryType", salaryType);
                        intent.putExtra("education", education);
                        intent.putExtra("managerName", managerName);
                        intent.putExtra("managerPhone", managerPhone);
                        intent.putExtra("managerEmail", managerEmail);
                        intent.putExtra("workPeriod", workPeriod);
                        intent.putExtra("workDay", workDay);
                        intent.putExtra("resumeCheck", resumeCheck);
                        intent.putExtra("jobType", jobType);
                        intent.putExtra("recNum", recNum);
                        intent.putExtra("workStart", workStart);
                        intent.putExtra("workEnd", workEnd);
                        intent.putExtra("content", content);
                        intent.putExtra("compId", compId);
                        intent.putExtra("compType", compType);
                        intent.putExtra("repName", repName);
                        intent.putExtra("repPhone", repPhone);

                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<JobPostListItem> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
}
