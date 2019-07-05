package com.example.retrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class ButtonFragment extends Fragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_button, container, false);
        rootView.findViewById(R.id.bntClick).setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bntClick){
            Toast toast = Toast.makeText(getActivity(), "Извините, данный функционал еще в разработке", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
