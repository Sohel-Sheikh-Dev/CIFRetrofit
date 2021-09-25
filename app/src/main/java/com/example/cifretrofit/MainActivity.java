package com.example.cifretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Model> modelClassArrayList;
    private Adapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        findNews();

    }

    private void init() {
        modelClassArrayList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        progressBar = findViewById(R.id.progress_Bar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(MainActivity.this, modelClassArrayList);
        recyclerView.setAdapter(adapter);
    }


    private void findNews() {
        Call<ApiResponse> call = RetrofitInstance.getRetrofitInstance().getPosts();
        call.enqueue(new Callback<ApiResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        progressBar.setVisibility(View.GONE);
                        modelClassArrayList.addAll(response.body().getPosts());
                        Toast.makeText(MainActivity.this, "working", Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "not working", Toast.LENGTH_SHORT).show();
            }
        });
    }
}