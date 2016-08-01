package com.example.chenlei1_iri.androiddemo.activity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.chenlei1_iri.androiddemo.view.CustomNumberPicker;
import com.example.chenlei1_iri.androiddemo.R;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumberPickerActivity extends AppCompatActivity {
    private static final String TAG = NumberPickerActivity.class.getSimpleName();
    @BindView(R.id.btn_num)
    Button btnNum;

    private CustomNumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        numberPicker = (CustomNumberPicker) findViewById(R.id.number_picker);
        if (numberPicker == null) {
            Toast.makeText(this, "number picker is null!", Toast.LENGTH_LONG).show();
        }
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(5);
        setNumberPickerDividerColor(numberPicker);
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(this.getResources().getColor(R.color.line)));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @OnClick(R.id.btn_num)
    public void onClick() {
        showUseCouponDialog();
    }

    private void showUseCouponDialog(){
        AlertDialog dialog = null;
        AlertDialog.Builder builder = null;
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_coupon, null);
        final CustomNumberPicker numberPicker = (CustomNumberPicker) view.findViewById(R.id.hour_picker);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setValue(0);
        setNumberPickerDividerColor(numberPicker);
        builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.coupon_hours));
        builder.setView(view);
        builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hours = numberPicker.getValue();
                Toast.makeText(NumberPickerActivity.this, "Current valuse is " + hours, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialog = builder.create();
        dialog.show();
    }
}
