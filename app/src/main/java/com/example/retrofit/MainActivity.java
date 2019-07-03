package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<GetData> posts = new ArrayList<>();
    private String[] status = {"Все", "Открытые", "Занятые", "Закрытые"};
    Adapter adapter;
    private String getStatus = "open";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this, posts);
        recyclerView.setAdapter(adapter);

        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        final ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);
        // Определяем разметку для использования при выборе элемента
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapt);
        spinner.setSelection(1);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                int item = parent.getSelectedItemPosition();
                switch (item){
                    case 0: getStatus = "all"; break;
                    case 1: getStatus = "open"; break;
                    case 2: getStatus = "in_progress"; break;
                    case 3: getStatus = "closed"; break;
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
                    if (post != null) {
                        posts.clear();
                        for (Post itemId : post.getData()){
                            if (getStatus.equals("all"))
                                posts.add(new GetData(itemId.getId(), itemId.getTitle(), itemId.getActual_time(), itemId.getStatus(), itemId.getLocation()));
                            else if(itemId.getStatus().equals(getStatus))
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
}
