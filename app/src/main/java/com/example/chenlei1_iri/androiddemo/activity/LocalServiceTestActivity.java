package com.example.chenlei1_iri.androiddemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.chenlei1_iri.androiddemo.R;
import com.example.chenlei1_iri.androiddemo.binder.LocalService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenlei1-iri on 2016/8/1.
 */
public class LocalServiceTestActivity extends AppCompatActivity {
    public static final String TAG = LocalServiceTestActivity.class.getSimpleName();
    @BindView(R.id.tv_binder)
    TextView tvBinder;
    @BindView(R.id.btn_bind)
    Button btnBind;

    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        ButterKnife.bind(this);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "service connected");
                LocalService ss = ((LocalService.LocalBinder) service).getService();
                String s = ss.sayHelloWorld();
                tvBinder.setText(s);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "service disconnected");
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.unbindService(serviceConnection);
    }

    @OnClick(R.id.btn_bind)
    public void onClick() {
        Intent service = new Intent(this.getApplicationContext(), LocalService.class);
        this.bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
