package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Adapter.ItemFromActivity {

    private List<GetData> posts = new ArrayList<>();
    private String[] status = {"Все", "Открытые", "Занятые", "Закрытые"};
    Adapter adapter;
    private String getStatusSpinner = "open";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, MoreInformationItem.class);
        setSpinner();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this, posts);
        recyclerView.setAdapter(adapter);
    }


    private void setSpinner(){
        Spinner spinner = findViewById(R.id.spinner);
        final ArrayAdapter<String> adapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, status);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapt);
        spinner.setSelection(1);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int item = parent.getSelectedItemPosition();
                switch (item){
                    case 0: getStatusSpinner = "all"; break;
                    case 1: getStatusSpinner = "open"; break;
                    case 2: getStatusSpinner = "in_progress"; break;
                    case 3: getStatusSpinner = "closed"; break;
                }
                setPosts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    private void setPosts(){
        NetworkService.getInstance()
            .getJSONApi()
            .getAllList()
            .enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    Post post = response.body();
                    if (post.getStatus()) {
                        posts.clear();
                        for (DataStorage itemId : post.getData()){
                            if (getStatusSpinner.equals("all"))
                                posts.add(new GetData(itemId.getId(), itemId.getTitle(), itemId.getActual_time(), itemId.getStatus(), itemId.getLocation()));
                            else if(itemId.getStatus().equals(getStatusSpinner))
                                posts.add(new GetData(itemId.getId(), itemId.getTitle(), itemId.getActual_time(), itemId.getStatus(), itemId.getLocation()));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    t.printStackTrace();
                }
            }
        );
    }

    @Override
    public void getInfoForNewActivity(int id) {
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
