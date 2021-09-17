package com.example.loadinglayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity   {

    private Button mClick1;
    private Button mClick2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mClick1 = findViewById(R.id.click1);
        mClick2 = findViewById(R.id.click2);
        mClick1.setOnClickListener(this::onClick);
        mClick2.setOnClickListener(this::onClick);
    }


    public void onClick(View view) {

        switch (view.getId()){
            case R.id.click1:
//                startActivity(new Intent(MainActivity.this,Loading_1_Activity.class));
                break;
            case R.id.click2:
                startActivity(new Intent(MainActivity.this,Loading_2_Activity.class));
                break;
        }
    }
}