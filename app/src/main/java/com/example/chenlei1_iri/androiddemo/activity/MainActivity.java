package com.example.chenlei1_iri.androiddemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chenlei1_iri.androiddemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private long firstTime = 0;

    @BindView(R.id.onTouchDemo)
    Button onTouchDemo;
    @BindView(R.id.rxJavaDemo)
    Button rxJavaDemo;
    @BindView(R.id.rxImageLoadDemo)
    Button rxImageLoadDemo;
    @BindView(R.id.rxTransformDemo)
    Button rxTransformDemo;
    @BindView(R.id.btn_butterknife)
    Button btnButterknife;
    @BindView(R.id.btn_svg)
    Button btnSvg;
    @BindView(R.id.btn_number_picker)
    Button btnNumberPicker;
    @BindView(R.id.btn_binder)
    Button btnBinder;
    @BindView(R.id.btn_mvp)
    Button btnMvp;
    @BindView(R.id.btn_ndk)
    Button btnNdk;
    @BindView(R.id.btn_aidl)
    Button btnAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.onTouchDemo, R.id.rxJavaDemo, R.id.rxImageLoadDemo, R.id.rxTransformDemo, R.id.btn_butterknife,
            R.id.btn_svg, R.id.btn_number_picker, R.id.btn_binder, R.id.btn_mvp, R.id.btn_ndk, R.id.btn_aidl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.onTouchDemo:
                Intent onTouchIntent = new Intent(MainActivity.this, OnTouchActivity.class);
                startActivity(onTouchIntent);
                break;
            case R.id.rxJavaDemo:
                Intent rxJavaIntent = new Intent(MainActivity.this, RxJavaActivity.class);
                startActivity(rxJavaIntent);
                break;
            case R.id.rxImageLoadDemo:
                Intent rxImageLoadIntent = new Intent(MainActivity.this, RxImageLoadActivity.class);
                startActivity(rxImageLoadIntent);
                break;
            case R.id.rxTransformDemo:
                Intent rxJavaTransformIntent = new Intent(MainActivity.this, RxJavaTransformActivity.class);
                startActivity(rxJavaTransformIntent);
                break;
            case R.id.btn_butterknife:
                Intent butterKnifeIntent = new Intent(MainActivity.this, ButterKnifeActivity.class);
                startActivity(butterKnifeIntent);
                break;
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
            case R.id.btn_ndk:
//                Intent ndkIntent = new Intent(MainActivity.this, NDKActivity.class);
//                startActivity(ndkIntent);
                break;
            case R.id.btn_aidl:
                Intent aidlIntent = new Intent(MainActivity.this, AIDLActivity.class);
                startActivity(aidlIntent);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode != KeyEvent.KEYCODE_BACK) {
            return false;
        }
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {                                         //如果两次按键时间间隔大于2秒，则不退出
            Toast.makeText(this, "再按一次退出程序！", Toast.LENGTH_SHORT).show();
            firstTime = secondTime;//更新firstTime
            return true;
        } else {                                                    //两次按键小于2秒时，退出应用
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }
}
