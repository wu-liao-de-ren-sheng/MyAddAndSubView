package com.example.jianpan.myaddandsubview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddAndSubView addAndSubView = (AddAndSubView) findViewById(R.id.view);
        addAndSubView.setMax(5);
        addAndSubView.setMinNum(1);
        addAndSubView.setOnNumChangeListener(new AddAndSubView.OnNumChangeListener() {
            @Override
            public void onNumChange(View view, int num) {
                Toast.makeText(MainActivity.this, String.valueOf(num), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMin(View view, int num) {
                Toast.makeText(MainActivity.this, "onMin", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMax(View view, int num) {
                Toast.makeText(MainActivity.this, "onMax", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
