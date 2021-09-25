package com.example.cifretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

//    private TextView textViewResult;

    public RecyclerView recyclerView;
    public ArrayList<Model> modelClassArrayList = new ArrayList<>();
    public LinearLayoutManager linearLayoutManager;
    public Adapter adapter;
    private ApiInterface apiInterface;
    ApiResponse apiResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_View);
//        modelClassArrayList =
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(MainActivity.this,modelClassArrayList);
        recyclerView.setAdapter(adapter);


//        textViewResult = findViewById(R.id.text_View_Results);
/*        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface jsonPlaceHolderApi = retrofit.create(ApiInterface.class);
        Call<List<Model>> call = jsonPlaceHolderApi.getPosts();*/
        Call<ApiResponse> call = RetrofitInstance.getRetrofitInstance().getPosts();
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
//                    textViewResult.setText("Code: " + response.code());
//                    return;
                    if (response.body() != null) {
                        modelClassArrayList.addAll(response.body().getPosts());
                    }
                    Toast.makeText(MainActivity.this,"working",Toast.LENGTH_SHORT).show();


                }
//                if(response.body() != null){
//                }
//                List<Model> posts = response.body();
//                for (Model post : posts) {
//                    String content = "";
//                    content += "ID: " + post.getId() + "\n";
//                    content += "User ID: " + post.getUserId() + "\n";
//                    content += "Title: " + post.getTitle() + "\n";
//                    content += "Text: " + post.getText() + "\n\n";
//                    textViewResult.append(content);
//                }
            }
            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
                Toast.makeText(MainActivity.this,"not working",Toast.LENGTH_SHORT).show();

            }
        });
    }
}