package com.example.chenlei1_iri.androiddemo.application;

import android.app.Application;

import com.buptant.customsplash.SplashUtils.SplashConfiguration;
import com.example.chenlei1_iri.androiddemo.R;
import com.example.chenlei1_iri.androiddemo.activity.MainActivity;

/**
 * Created by chenlei1-iri on 2016/8/11.
 */
public class MyApplication extends Application{

    private int[] imgId;

    @Override
    public void onCreate() {
        super.onCreate();

        imgId = new int[]{R.drawable.bg1, R.drawable.bg2, R.drawable.bg3, R.drawable.bg4};

        SplashConfiguration splashConfiguration = new SplashConfiguration(MainActivity.class, SplashConfiguration.ROATTE_TRANSFORM, imgId);
        splashConfiguration.init();
    }
}
