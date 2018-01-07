package com.example.jeesan_2.testingpro.Concepts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.jeesan_2.testingpro.R;
import com.example.jeesan_2.testingpro.services.MyIntentServiceSample;
import com.example.jeesan_2.testingpro.services.MyServiceSample;

/**
 * Created by KAMAL on 1/7/2018.
 */

public class ServiceSample extends AppCompatActivity implements View.OnClickListener {

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
                startService(new Intent(this, MyIntentServiceSample.class));
        } else if (view == buttonStop) {
            //stopping service
//            stopService(new Intent(this, MyServiceSample.class));
        }
    }

}
