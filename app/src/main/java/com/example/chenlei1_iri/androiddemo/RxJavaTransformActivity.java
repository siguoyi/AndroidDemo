package com.example.chenlei1_iri.androiddemo;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class RxJavaTransformActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = RxJavaTransformActivity.class.getSimpleName();

    private Button btn_map;
    private Button btn_flatMap;
    private Button btn_lift;
    private TextView tv_rx_text;

    private StringBuilder sb;
    private Student[] student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_transform);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_map = (Button) findViewById(R.id.btn_map);
        btn_flatMap = (Button) findViewById(R.id.btn_flatmap);
        btn_lift = (Button) findViewById(R.id.btn_lift);
        tv_rx_text = (TextView) findViewById(R.id.tv_rx_text);

        btn_map.setOnClickListener(this);
        btn_flatMap.setOnClickListener(this);
        btn_lift.setOnClickListener(this);

        sb = new StringBuilder();
//        student[0] = new Student(1, 23, "curry", "male");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_map:
                testMap();
                break;
            case R.id.btn_flatmap:
                testFlatMap();
                break;
            case R.id.btn_lift:
                testLift();
                break;
        }
    }

    private void testMap(){
        Observable.just("test map").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "test";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                sb.append(s).append("\n");
                tv_rx_text.setText(sb.toString());
            }
        });
    }

    private void testFlatMap(){
    }

    private void testLift(){

    }
}
