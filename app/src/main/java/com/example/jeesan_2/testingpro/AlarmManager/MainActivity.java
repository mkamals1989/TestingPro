package com.example.jeesan_2.testingpro.AlarmManager;

import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.jeesan_2.testingpro.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by KAMAL on 1/24/2018.
 */

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    Context context;
    @BindView(R.id.timepicker)
    TimePicker picker;

    @BindView(R.id.tvstatus)
    TextView tvStatus;

    Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        ButterKnife.bind(this);
        context = this;
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        calendar = Calendar.getInstance();
    }

    public void setAlarm(View view) {

        calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
        calendar.set(Calendar.MINUTE,picker.getMinute());
        setAlarmStatusText("Alarm is ON!!! " + picker.getHour() + ":"+picker.getMinute());
    }

    public void cancelAlarm(View view) {
        setAlarmStatusText("Alarm is OFF!!!");
    }

    private void setAlarmStatusText(String s) {
        tvStatus.setText(s);
    }
}
