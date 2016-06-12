package com.example.chenlei1_iri.androiddemo;

import android.content.pm.ActivityInfo;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func0;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

public class RxJavaActivity extends AppCompatActivity {

    private static final String TAG = RxJavaActivity.class.getSimpleName();
    private static final int TYPE_CREATE = 0x11;
    private static final int TYPE_JUST = 0x12;
    private static final int TYPE_FROM = 0x13;

    private Looper backgroundLooper;
    private MySubscriber<String> subscriber;

    private Button btn_rx;
    private TextView tv_rx;

    private static String[] words = {"from 1", "from 2", "from 3", "from 4", "from 5"};
    private int count = 0;
    private int type;
    private StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btn_rx = (Button) findViewById(R.id.button_run_scheduler);
        tv_rx = (TextView) findViewById(R.id.tv_rx_text);

        sb = new StringBuilder();

        BackgroundThread backgroundThread = new BackgroundThread();
        backgroundThread.start();
        backgroundLooper = backgroundThread.getLooper();

        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                sb.append("onNextAction " + s + "\n");
                tv_rx.setText(sb.toString());
                Log.d(TAG, "onNextAction " + s + "\n");
            }
        };

        Action1 onComplete = new Action1() {
            @Override
            public void call(Object o) {
                Log.d(TAG, "complete");
            }
        };

        Observable.from(words).subscribe(onNextAction, onComplete);

        btn_rx.setOnClickListener(new View.OnClickListener() {
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
            sb.append("onCompleted()" + "\n");
            tv_rx.setText(sb.toString());
            Log.d(TAG, "onCompleted()" + "\n");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError()", e);
        }

        @Override
        public void onNext(String s) {
            sb.append("onNext(" + s + ")" + "\n");
            tv_rx.setText(sb.toString());
            Log.d(TAG, "onNext(" + s + ")" + "\n");
        }
    }
}
