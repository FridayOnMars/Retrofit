package com.example.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemContentFragment extends Fragment implements View.OnClickListener {
    private FragmentCallToActivity connect;
    private TextView tvItemTitle;
    private TextView tvItemActualTime;
    private TextView tvItemStatus;
    private TextView tvItemLocation;
    private TextView tvItemDescription;
    private TextView tvItemSpecialist;
    private Button btn;
    private int id;
    private String status;

    public interface FragmentCallToActivity{
        void onDialog(boolean a);
        void onCheckInternet(boolean a);
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentCallToActivity){
            connect = (FragmentCallToActivity) context;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.item_content_fragment, container, false);
        if(getArguments() != null){
            id = getArguments().getInt("id");
            status = getArguments().getString("status");
        }
        tvItemTitle = rootView.findViewById(R.id.tvItemTitle);
        tvItemActualTime = rootView.findViewById(R.id.tvItemActualTime);
        tvItemStatus = rootView.findViewById(R.id.tvItemStatus);
        tvItemLocation = rootView.findViewById(R.id.tvItemLocation);
        tvItemDescription = rootView.findViewById(R.id.tvItemDescription);
        tvItemSpecialist = rootView.findViewById(R.id.tvItemSpecialist);
        btn = rootView.findViewById(R.id.bntClick);
        getContent(id);
        rootView.findViewById(R.id.bntClick).setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bntClick){
            connect.onDialog(true);
        }
    }

    private void getContent(int id){
        @SuppressLint("SimpleDateFormat")
        final SimpleDateFormat time = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(id)
                .enqueue(new Callback<PostItem>() {
                             @Override
                             public void onResponse(@NonNull Call<PostItem> call, @NonNull Response<PostItem> response) {
                                 PostItem post = response.body();
                                 assert post != null;
                                 if (post.getStatus()) {
                                     tvItemTitle.setText(String.format(getString(R.string.format_ItemTitle),post.getData().getTitle()));
                                     tvItemActualTime.setText(String.format(getString(R.string.format_ActualTime), time.format(post.getData().getActual_time()*1000)));
                                     tvItemStatus.setText(String.format(getString(R.string.format_ItemStatus),status));
                                     tvItemLocation.setText(String.format(getString(R.string.format_ItemLocation),post.getData().getLocation()));
                                     tvItemDescription.setText(String.format(getString(R.string.format_ItemDescription),post.getData().getDescription()));
                                     if(!post.getData().getStatus().equals("open")){
                                         tvItemSpecialist.setText(String.format(getString(R.string.format_ItemSpecialist),post.getData().getSpecialist().getFirstName(), post.getData().getSpecialist().getLastName()));
                                         btn.setVisibility(View.INVISIBLE);
                                     }
                                     else {
                                         tvItemSpecialist.setText("");
                                     }
                                 }
                                 else {
                                     tvItemTitle.setText(post.getError());
                                     tvItemStatus.setText("");
                                     tvItemActualTime.setText("");
                                     tvItemLocation.setText("");
                                     tvItemDescription.setText("");
                                     tvItemSpecialist.setText("");
                                     btn.setVisibility(View.INVISIBLE);
                                 }
                             }

                             @Override
                             public void onFailure(@NonNull Call<PostItem> call, @NonNull Throwable t) {
                                 t.printStackTrace();
                                 Toast toast = Toast.makeText(getActivity(), "Ошибка подключения. Повторите позже", Toast.LENGTH_SHORT);
                                 toast.show();
                                 connect.onCheckInternet(false);
                             }
                         }
                );
    }
}
