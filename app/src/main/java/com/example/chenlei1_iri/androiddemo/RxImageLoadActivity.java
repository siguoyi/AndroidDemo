package com.example.chenlei1_iri.androiddemo;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxImageLoadActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = RxImageLoadActivity.class.getSimpleName();

    private Button btn_imageLoad;
    private ImageView iv_image;
    private MySubscriber mySubscriber;
    private RequestQueue mQueue;
    private ImageRequest imageRequest;

    private String IMAGE_URL = "http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_image_load);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btn_imageLoad = (Button) findViewById(R.id.bt_imageload);
        iv_image = (ImageView) findViewById(R.id.rx_image);
        btn_imageLoad.setOnClickListener(this);
        mQueue = Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_imageload:
                Log.d(TAG, "onClick");
                iv_image.setImageResource(R.mipmap.ic_launcher) ;
                mySubscriber = new MySubscriber();
                Observable.create(new Observable.OnSubscribe<Drawable>() {
                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        subscriber.onNext(loadImage());
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(mySubscriber);
                break;
        }
    }

    private Drawable loadImage(){
        final BitmapDrawable[] mDrawable = new BitmapDrawable[1];
        imageRequest = new ImageRequest(IMAGE_URL, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Log.d(TAG, "onResponse");
                iv_image.setImageBitmap(response);
                mDrawable[0] = new BitmapDrawable(response);
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iv_image.setImageResource(R.mipmap.ic_launcher);
            }
        });

        mQueue.add(imageRequest);
        return mDrawable[0];
    }

    class MySubscriber extends Subscriber<Drawable>{
        @Override
        public void onCompleted() {
            Toast.makeText(RxImageLoadActivity.this, "Image load complete !", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Drawable drawable) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            iv_image.setImageDrawable(drawable);
        }
    };
}
