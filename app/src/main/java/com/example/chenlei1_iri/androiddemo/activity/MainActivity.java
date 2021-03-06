package com.example.chenlei1_iri.androiddemo.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.chenlei1_iri.androiddemo.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
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
    @BindView(R.id.btn_date_picker)
    Button btnDatePicker;
    @BindView(R.id.pb)
    ProgressBar pb;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @OnClick({R.id.onTouchDemo, R.id.rxJavaDemo, R.id.rxImageLoadDemo, R.id.rxTransformDemo, R.id.btn_butterknife,
            R.id.btn_svg, R.id.btn_number_picker, R.id.btn_binder, R.id.btn_mvp, R.id.btn_ndk, R.id.btn_aidl, R.id.btn_date_picker})
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
            case R.id.btn_date_picker:
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DATE);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                if (++count % 2 == 1) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.MyDialogTheme, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Toast.makeText(MainActivity.this, "The selected date is " + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
                        }
                    }, year, month, day);

                    datePickerDialog.show();
                } else {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.MyDialogTheme, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            Toast.makeText(MainActivity.this, "The selected time is " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                        }
                    }, hour, minute, true);
                    timePickerDialog.show();
                    break;
                }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode != KeyEvent.KEYCODE_BACK) {
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

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.VISIBLE);
                return true;

            case R.id.action_search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
                return true;
        }

        return false;
    }
}
