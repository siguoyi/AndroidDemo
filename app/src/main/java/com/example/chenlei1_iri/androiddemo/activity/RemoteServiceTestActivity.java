package com.example.chenlei1_iri.androiddemo.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chenlei1_iri.androiddemo.R;
import com.example.chenlei1_iri.androiddemo.binder.RemoteService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenlei1-iri on 2016/8/1.
 */
public class RemoteServiceTestActivity extends AppCompatActivity {
    public static final String TAG = "RemoteServiceTest";
    @BindView(R.id.tv_binder)
    TextView tvBinder;
    @BindView(R.id.btn_bind)
    Button btnBind;
    private ServiceConnection serviceConnection;
    public static final int SAY_HELLO_TO_CLIENT = 0;

    @OnClick(R.id.btn_bind)
    public void onClick() {
        Intent service = new Intent(this.getApplicationContext(), RemoteService.class);
        this.bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * Handler of incoming messages from service.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAY_HELLO_TO_CLIENT:
                    tvBinder.setText("Hello World Remote Client!");
                    Toast.makeText(RemoteServiceTestActivity.this.getApplicationContext(), "Hello World Remote Client!",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    Messenger messenger_reciever = new Messenger(new IncomingHandler());

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder);
        ButterKnife.bind(this);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "service connected");
                Messenger messenger = new Messenger(service);
                Message msg = new Message();
                msg.what = RemoteService.MSG_SAY_HELLO;
                msg.replyTo = messenger_reciever;
                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
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
        //must unbind the service otherwise the ServiceConnection will be leaked.
        this.unbindService(serviceConnection);
    }
}
