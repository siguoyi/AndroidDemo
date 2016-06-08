package com.example.chenlei1_iri.androiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OnTouchActivity extends AppCompatActivity {
    private static final String TAG = OnTouchActivity.class.getSimpleName();

    private MyTextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_touch);

        myTextView = (MyTextView) findViewById(R.id.mytextview);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
