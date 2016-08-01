package com.example.chenlei1_iri.androiddemo.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by chenlei1-iri on 2016/8/1.
 */
public class LocalService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public String sayHelloWorld(){
        Toast.makeText(this.getApplicationContext(), "Hello World Local Service!", Toast.LENGTH_SHORT).show();
        return "Hello World Local Service!";
    }

    public class LocalBinder extends Binder{
        public LocalService getService(){
            return LocalService.this;
        }
    }
}
