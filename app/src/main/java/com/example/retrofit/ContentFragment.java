package com.example.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentFragment extends Fragment{

    private List<PostsAdapter> posts = new ArrayList<>();
    private TasksAdapter adapter;
    private String getStatusSpinner = "open";
    private View rootView;
    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_NUMBER = "Number";
<<<<<<< HEAD
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat time = new SimpleDateFormat("dd MM yyyy, HH:mm");
=======
>>>>>>> 5a99cb1... add sort of actual time

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_fragment, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        adapter = new TasksAdapter(getContext(), posts);
        recyclerView.setAdapter(adapter);
        setSpinner();
        return rootView;
    }

    private void setSpinner(){
        final SharedPreferences mSettings = Objects.requireNonNull(this.getActivity()).getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Spinner spinner = rootView.findViewById(R.id.spinner);
        final ArrayAdapter<?> adapt = ArrayAdapter.createFromResource(Objects.requireNonNull(getContext()), R.array.status, android.R.layout.simple_spinner_item);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapt);
        if(mSettings.contains(APP_PREFERENCES_NUMBER))
            spinner.setSelection(mSettings.getInt(APP_PREFERENCES_NUMBER, 0));
        else
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
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putInt(APP_PREFERENCES_NUMBER, item);
                editor.apply();
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
                .enqueue(new Callback<PostList>() {
                             @Override
                             public void onResponse(@NonNull Call<PostList> call, @NonNull Response<PostList> response) {
                                 PostList post = response.body();
                                 assert post != null;
                                 int i=-1;
                                 String[] status = getResources().getStringArray(R.array.status);
                                 if (post.getStatus()) {
                                     posts.clear();
                                     for (PostListItem itemId : post.getData()){
                                         for(String stat: getResources().getStringArray(R.array.status_translate)){
                                             i++;
                                             if(stat.equals(itemId.getStatus())){
                                                 break;
                                             }
                                         }
                                         if (getStatusSpinner.equals("all"))
                                             posts.add(new PostsAdapter(itemId.getId(), String.format(getString(R.string.format_title),itemId.getTitle()), itemId.getActual_time()*1000, String.format(getString(R.string.format_status),status[i]), String.format(getString(R.string.format_location),itemId.getLocation())));
                                         else if(itemId.getStatus().equals(getStatusSpinner))
                                             posts.add(new PostsAdapter(itemId.getId(), String.format(getString(R.string.format_title),itemId.getTitle()), itemId.getActual_time()*1000, String.format(getString(R.string.format_status),status[i]), String.format(getString(R.string.format_location),itemId.getLocation())));
                                         i=-1;
                                     }
                                     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                         posts.sort(new Comparator<PostsAdapter>() {
                                             @Override
                                             public int compare(PostsAdapter o1, PostsAdapter o2) {
                                                 if(o1.getActualTime() == o2.getActualTime())
                                                     return 0;
                                                 else if(o1.getActualTime()>o2.getActualTime())
                                                     return 1;
                                                 else
                                                     return -1;
                                             }
                                         });
                                     }
                                     adapter.notifyDataSetChanged();
                                 }
                             }

                             @Override
                             public void onFailure(@NonNull Call<PostList> call, @NonNull Throwable t) {
                                 t.printStackTrace();
                                 Toast toast = Toast.makeText(getActivity(), "Ошибка подключения. Повторите позже", Toast.LENGTH_SHORT);
                                 toast.show();
                             }
                         }
                );
    }
}
