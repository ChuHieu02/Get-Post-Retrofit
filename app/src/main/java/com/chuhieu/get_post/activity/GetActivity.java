package com.chuhieu.get_post.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;


import com.chuhieu.get_post.R;
import com.chuhieu.get_post.adapter.GetAdapter;
import com.chuhieu.get_post.api.GetDataService;
import com.chuhieu.get_post.api.RetrofitClient;
import com.chuhieu.get_post.model.RetroPhoto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetActivity extends AppCompatActivity {
    TextView textViewcount;

    private GetAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        textViewcount = findViewById(R.id.count);

        progressDoalogg();

        GetDataServicee();

    }

    private void GetDataServicee() {
        GetDataService service = RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {

                generateDataList(response.body());

                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getApplication(), "Co loi xay ra!", Toast.LENGTH_SHORT).show();
            }
        });
    }
// lay list data hien thi len recycleview
    private void generateDataList(List<RetroPhoto> photoList) {
        recyclerView = findViewById(R.id.Rc_list);
        adapter = new GetAdapter(this, photoList);
        textViewcount.setText(photoList.size()+"");
        LinearLayoutManager layoutManager = new LinearLayoutManager(GetActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void progressDoalogg() {
        progressDoalog = new ProgressDialog(GetActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
    }
}
