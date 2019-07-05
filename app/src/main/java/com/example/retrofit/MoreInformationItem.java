package com.example.retrofit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreInformationItem extends AppCompatActivity {
    TextView tvItemTitle;
    TextView tvItemActualTime;
    TextView tvItemStatus;
    TextView tvItemLocation;
    TextView tvItemDescription;
    TextView tvItemSpecialist;
    ButtonFragment fragmentBnt = new ButtonFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);
        int id = getIntent().getIntExtra("id", 0);
        tvItemTitle = findViewById(R.id.tvItemTitle);
        tvItemActualTime = findViewById(R.id.tvItemActualTime);
        tvItemStatus = findViewById(R.id.tvItemStatus);
        tvItemLocation = findViewById(R.id.tvItemLocation);
        tvItemDescription = findViewById(R.id.tvItemDescription);
        tvItemSpecialist = findViewById(R.id.tvItemSpecialist);
        getContent(id);
    }
    private void getContent(int id){
        @SuppressLint("SimpleDateFormat")
        final SimpleDateFormat time = new SimpleDateFormat("dd MM yyyy, HH:mm");
        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(id)
                .enqueue(new Callback<PostItem>() {
                             @Override
                             public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                                 PostItem post = response.body();
                                 assert post != null;
                                 if (post.getStatus()) {
                                     tvItemTitle.setText(String.format(getString(R.string.format_ItemTitle),post.getData().getTitle()));
                                     tvItemActualTime.setText(String.format(getString(R.string.format_ActualTime), time.format(post.getData().getActual_time()*1000)));
                                     tvItemStatus.setText(String.format(getString(R.string.format_ItemStatus),post.getData().getStatus()));
                                     tvItemLocation.setText(String.format(getString(R.string.format_ItemLocation),post.getData().getLocation()));
                                     tvItemDescription.setText(String.format(getString(R.string.format_ItemDescription),post.getData().getDescription()));
                                     if(!post.getData().getStatus().equals("open")){
                                         tvItemSpecialist.setText(String.format(getString(R.string.format_ItemSpecialist),post.getData().getSpecialist().getFirstName(), post.getData().getSpecialist().getLastName()));
                                     }
                                     else {
                                         tvItemSpecialist.setText("");
                                         getSupportFragmentManager().beginTransaction().replace(R.id.frag, fragmentBnt).commit();
                                     }
                                 }
                                 else {
                                     tvItemTitle.setText(post.getError());
                                     tvItemStatus.setText("");
                                     tvItemActualTime.setText("");
                                     tvItemLocation.setText("");
                                     tvItemDescription.setText("");
                                     tvItemSpecialist.setText("");
                                 }
                             }

                             @Override
                             public void onFailure(Call<PostItem> call, Throwable t) {
                                 t.printStackTrace();
                             }
                         }
                );
    }
}
