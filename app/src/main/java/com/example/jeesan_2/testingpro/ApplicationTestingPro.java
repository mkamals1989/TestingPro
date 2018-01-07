package com.example.jeesan_2.testingpro;

import android.app.Application;
import android.util.Log;

/**
 * Created by KAMAL on 1/7/2018.
 */

public class ApplicationTestingPro extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w("TestingPro","onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.w("TestingPro","onTerminate");
    }

}
