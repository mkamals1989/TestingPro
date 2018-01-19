package com.example.jeesan_2.testingpro.Handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jeesan_2.testingpro.R;

/**
 * Created by KAMAL on 1/20/2018.
 */

public class MainActivity extends AppCompatActivity {

    Thread thread;
    ProgressBar progressBar1;
    Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        progressBar1 = findViewById(R.id.progressBar1);



        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progressBar1.setProgress(msg.arg1);
            }
        };
    }

    public void onButtonClick(View view) {
        thread = new Thread(new MyThread());
        thread.start();
    }

    private class MyThread implements Runnable {

        @Override
        public void run() {

//            Message msg = mHandler.obtainMessage();
            for (int i = 0; i < 100; i++) {
                Message msg = Message.obtain();
                msg.arg1 = i;
                mHandler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
