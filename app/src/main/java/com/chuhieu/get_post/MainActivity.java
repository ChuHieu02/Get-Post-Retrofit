package com.chuhieu.get_post;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chuhieu.get_post.activity.GetActivity;
import com.chuhieu.get_post.adapter.GetAdapter;


public class MainActivity extends AppCompatActivity {
    private GetAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Post(View view) {

    }

    public void Get(View view) {
        startActivity(new Intent(MainActivity.this, GetActivity.class));
    }
}
