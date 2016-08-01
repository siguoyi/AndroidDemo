package com.example.chenlei1_iri.androiddemo.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.chenlei1_iri.androiddemo.activity.RemoteServiceTestActivity;

/**
 * Created by chenlei1-iri on 2016/8/1.
 */
public class RemoteService extends Service {
    public static final int MSG_SAY_HELLO = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messager.getBinder();
    }

    Handler IncomingHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if(msg.replyTo != null){
                Message msg_client = this.obtainMessage();
                msg_client.what = RemoteServiceTestActivity.SAY_HELLO_TO_CLIENT;
                try {
                    ((Messenger)msg.replyTo).send(msg_client);
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(RemoteService.this.getApplicationContext(), "Hello World Remote Service!",
                            Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }

    };

    Messenger  messager = new Messenger(IncomingHandler);
}
