package com.example.api_integration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.api_integration.api.Api;
import com.example.api_integration.api.Api_Interface;
import com.example.api_integration.model.imageModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<imageModel> list;
    private GridLayoutManager manager;
    private ImageAdapter adapter;
    private int page = 1;
    private ProgressDialog dialog;
    private int pagesize = 30;
    private boolean isLoading;
    private boolean isLastPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        list = new ArrayList<>();
        adapter = new ImageAdapter(this,list);
        manager= new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleitem = manager.getChildCount();
                int totalitem = manager.getItemCount();
                int firstvisibleitempos = manager.findFirstVisibleItemPosition();

                if (isLoading && isLastPage) {
                    if ((visibleitem+firstvisibleitempos >= totalitem)
                        && firstvisibleitempos >= 0 &&
                            totalitem >= pagesize){

                        page++;
                        getData();
                    }
                }
            }
        });
    }

    private void getData() {
        isLoading =true;
        Api.getApiInterface().getImages(page,30)
                .enqueue(new Callback<List<imageModel>>() {
                    @Override
                    public void onResponse(Call<List<imageModel>> call, Response<List<imageModel>> response) {
                        if (response.body() != null){
                            list.addAll(response.body());
                            adapter.notifyDataSetChanged();
                        }
                        isLoading = false;
                        dialog.dismiss();

                        if(list.size()>0){
                            isLastPage = list.size()<pagesize;
                        }else isLastPage=true;
                    }

                    @Override
                    public void onFailure(Call<List<imageModel>> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"Error: "+t.getMessage() ,Toast.LENGTH_SHORT);
                    }
                });
    }
}