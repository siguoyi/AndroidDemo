package com.example.chenlei1_iri.androiddemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.chenlei1_iri.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.btn_svg)
    Button btnSvg;
    @BindView(R.id.btn_number_picker)
    Button btnNumberPicker;
    @BindView(R.id.btn_binder)
    Button btnBinder;
    @BindView(R.id.btn_mvp)
    Button btnMvp;

    private Button btn_onTouch;
    private Button btn_rxJava;
    private Button btn_rxImageload;
    private Button btn_rxJavaTransform;
    private Button btn_butterknife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        btn_onTouch = (Button) findViewById(R.id.onTouchDemo);
        btn_rxJava = (Button) findViewById(R.id.rxJavaDemo);
        btn_rxImageload = (Button) findViewById(R.id.rxImageLoadDemo);
        btn_rxJavaTransform = (Button) findViewById(R.id.rxTransformDemo);
        btn_butterknife = (Button) findViewById(R.id.btn_butterknife);

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

        btn_rxImageload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rxImageLoadIntent = new Intent(MainActivity.this, RxImageLoadActivity.class);
                startActivity(rxImageLoadIntent);
            }
        });

        btn_rxJavaTransform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rxJavaTransformIntent = new Intent(MainActivity.this, RxJavaTransformActivity.class);
                startActivity(rxJavaTransformIntent);
            }
        });

        btn_butterknife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent butterKnifeIntent = new Intent(MainActivity.this, ButterKnifeActivity.class);
                startActivity(butterKnifeIntent);
            }
        });
    }

    @OnClick({R.id.btn_svg, R.id.btn_number_picker, R.id.btn_binder, R.id.btn_mvp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_svg:
                Intent svgIntent = new Intent(MainActivity.this, SVGActivity.class);
                startActivity(svgIntent);
                break;
            case R.id.btn_number_picker:
                Intent numberPickerIntent = new Intent(MainActivity.this, NumberPickerActivity.class);
                startActivity(numberPickerIntent);
                break;
            case R.id.btn_binder:
                Intent binderIntent = new Intent(MainActivity.this, RemoteServiceTestActivity.class);
                startActivity(binderIntent);
                break;
            case R.id.btn_mvp:
                Intent mvpIntent = new Intent(MainActivity.this, MVPActivity.class);
                startActivity(mvpIntent);
                break;
        }
    }
}
