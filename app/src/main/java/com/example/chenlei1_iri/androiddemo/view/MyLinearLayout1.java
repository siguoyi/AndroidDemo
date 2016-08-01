package com.example.chenlei1_iri.androiddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by chenlei1-iri on 2016/6/6.
 */
public class MyLinearLayout1 extends LinearLayout{

    private float x1;
    private float x2;
    private float y1;
    private float y2;

    private static final String TAG = MyLinearLayout1.class.getSimpleName();

    public MyLinearLayout1(Context context) {
        super(context);
    }

    public MyLinearLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout1(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyLinearLayout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, TAG + "--dispatchTouchEvent--" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG, TAG + "--onInterceptTouchEvent--" + super.onInterceptTouchEvent(ev));
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = ev.getX();
                y1 = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = ev.getX();
                y2 = ev.getY();
                if((x2 - x1 >50)&&(Math.abs(y2 - y1) < 20)){
                    Toast.makeText(getContext(), TAG + " right", Toast.LENGTH_SHORT).show();
                    return true;
                }else if((x1 - x2 >50)&&(Math.abs(y2 - y1) < 20)){
                    Toast.makeText(getContext(), TAG + " left", Toast.LENGTH_SHORT).show();
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, TAG + "--onTouchEvent--" + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
