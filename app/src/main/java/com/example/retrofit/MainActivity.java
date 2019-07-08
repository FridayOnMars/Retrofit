package com.example.retrofit;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements TasksAdapter.ItemFromActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent = new Intent(MainActivity.this, ItemActivity.class);
        ContentFragment fragCnt = new ContentFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contentFragment, fragCnt).commit();
    }

    @Override
    public void getInfoForNewActivity(int id) {
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
