package com.example.jeesan_2.testingpro.concepts;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jeesan_2.testingpro.customutility.CommonUtils;
import com.example.jeesan_2.testingpro.R;
import com.example.jeesan_2.testingpro.services.MyBoundService;
import com.example.jeesan_2.testingpro.services.MyServiceSample;

/**
 * Created by KAMAL on 1/7/2018.
 */

public class ServiceSample extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    //button objects
    private Button buttonStart;
    private Button buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_sample);

        //getting buttons from xml
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);

        //attaching onclicklistener to buttons
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonStart) {
            //starting service
//            startService(new Intent(this, MyServiceSample.class));
//                startService(new Intent(this, MyIntentServiceSample.class));
            startService(new Intent(this, MyBoundService.class));
            bindService(new Intent(this, MyBoundService.class), this, BIND_AUTO_CREATE);
        } else if (view == buttonStop) {
            //stopping service
            stopService(new Intent(this, MyServiceSample.class));
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyBoundService.MyIBinder myIBinder = (MyBoundService.MyIBinder) service;
        MyBoundService myBoundService = myIBinder.getService();
        Toast.makeText(this, "Connected.", Toast.LENGTH_SHORT).show();
        CommonUtils.showToast(this, "Value of I from bound service is : " + myBoundService.getValueOfI());
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }
}
