package com.example.jeesan_2.testingpro.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by KAMAL on 1/8/2018.
 */

/* For using in ServiceSample.java class*/
public class MyIntentServiceSample extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyIntentServiceSample() {
        super("MyIntentServiceSample");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("MyIntentServiceSample","onCreate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.w("MyIntentServiceSample","onHandleIntent");

        int i = 10 * 1000;
        for (int j = 1; j <= 5; j++) {
            try {
                Log.w("MyIntentServiceSample",""+j);
                Thread.sleep(i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w("MyIntentServiceSample","onDestroy");
    }
}
