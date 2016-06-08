package com.example.chenlei1_iri.androiddemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by chenlei1-iri on 2016/6/6.
 */
public class MyLinearLayout2 extends LinearLayout {
    private static final String TAG = MyLinearLayout2.class.getSimpleName();

    private float x1;
    private float x2;
    private float y1;
    private float y2;
    public MyLinearLayout2(Context context) {
        super(context);
    }

    public MyLinearLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyLinearLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
                if((Math.abs(x2 - x1) < 20)&&(y2 - y1 > 50)){
                    Toast.makeText(getContext(), TAG + " down", Toast.LENGTH_SHORT).show();
                    return true;
                }else if((Math.abs(x2 - x1) < 20)&&(y1 - y2 > 50)){
                    Toast.makeText(getContext(), TAG + " up", Toast.LENGTH_SHORT).show();
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
