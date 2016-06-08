package com.example.chenlei1_iri.androiddemo;

import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = RxJavaActivity.class.getSimpleName();
    private static final int TYPE_CREATE = 0x11;
    private static final int TYPE_JUST = 0x12;
    private static final int TYPE_FROM = 0x13;

    private Looper backgroundLooper;
    private MySubscriber<String> subscriber;

    private static String[] words = {"from 1", "from 2", "from 3", "from 4", "from 5"};
    private int count = 0;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();

        findViewById(R.id.button_run_scheduler).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if(count % 3 == 0){
                    type = TYPE_CREATE;
                }else if(count % 3 == 1){
                    type = TYPE_JUST;
                }else{
                    type = TYPE_FROM;
                }
                subscriber = new MySubscriber<>();
                onRunSchedulerExampleButtonClicked(type);
                count++;
            }
        });
    }

    void onRunSchedulerExampleButtonClicked(int type) {
                sampleObservable(type)
                // Run on a background thread
                .subscribeOn(AndroidSchedulers.from(backgroundLooper))
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    static Observable<String> sampleObservable(final int type) {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override public Observable<String> call() {
                try {
                    // Do some long running operation
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                return getObservable(type);
            }
        });
    }

    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }

    private static Observable getObservable(int type){
        switch (type){
            case TYPE_CREATE:
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("create 1");
                        subscriber.onNext("create 2");
                        subscriber.onNext("create 3");
                        subscriber.onNext("create 4");
                        subscriber.onNext("create 5");
                        subscriber.onCompleted();
                    }
                });
            case TYPE_JUST:
                return Observable.just("just 1", "just 2", "just 3", "just 4", "just 5");
            case TYPE_FROM:
                return Observable.from(words);
            default:
                return null;
        }
    }

    class MySubscriber<String> extends Subscriber<String>{

        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted()");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError()", e);
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext(" + s + ")");
        }
    }
}
