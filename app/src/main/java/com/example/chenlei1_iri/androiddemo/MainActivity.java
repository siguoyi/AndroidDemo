package com.example.chenlei1_iri.androiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btn_onTouch;
    private Button btn_rxJava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_onTouch = (Button) findViewById(R.id.onTouchDemo);
        btn_rxJava = (Button) findViewById(R.id.rxJavaDemo);

        btn_onTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onTouchIntent = new Intent(MainActivity.this, OnTouchActivity.class);
                startActivity(onTouchIntent);
            }
        });

        btn_rxJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rxJavaIntent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(rxJavaIntent);
            }
        });
    }
}
