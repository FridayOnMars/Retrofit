package com.example.retrofit;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ItemActivity extends AppCompatActivity implements ItemContentFragment.FragmentCallToActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        ItemContentFragment fragmentContent = new ItemContentFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frag, fragmentContent).commit();
        int id = getIntent().getIntExtra("id", 0);
        Bundle btnBundle = new Bundle();
        btnBundle.putInt("id", id);
        fragmentContent.setArguments(btnBundle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialog(boolean a) {
        if(a){
            final Dialog dialog = new Dialog(ItemActivity.this);
            dialog.setContentView(R.layout.dialog_item_content);
            TextView tvText = dialog.findViewById(R.id.textContent);
            Button btnClose = dialog.findViewById(R.id.buttonContent);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            tvText.setText("Извините, данный функционал еще в разработке");
            dialog.show();
        }
    }
}
