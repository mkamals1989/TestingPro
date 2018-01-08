package com.example.jeesan_2.testingpro.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by KAMAL on 1/8/2018.
 */

public class MyBoundService extends Service {

    MyIBinder myIBinder = new MyIBinder();
    int i = 10;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w("MyBoundService","onBind");
        return myIBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("MyBoundService","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("MyBoundService","onStartCommand");

        i = i*i;
        Log.w("MyBoundService","onStartCommand : "+i);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("MyBoundService","onDestroy");
    }

    public class MyIBinder extends Binder {

       public MyBoundService getService() {
            return MyBoundService.this;
        }

    }

    public int getValueOfI() {
        return i;
    }
}
