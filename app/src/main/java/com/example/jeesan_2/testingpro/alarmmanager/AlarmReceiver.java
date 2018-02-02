package com.example.jeesan_2.testingpro.alarmmanager;

/**
 * Created by KAMAL on 1/31/2018.
 */

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Message;
import android.os.PowerManager;
import android.os.Vibrator;
import android.widget.Toast;

import com.example.jeesan_2.testingpro.R;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: 2/1/2018 This is another type where we are awaking the device if it is sleeping.
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TAG");
        //Acquire the lock
        wl.acquire();
        Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
        wl.release();

        // TODO: 2/1/2018 the below intent is another way of showing dialog with tranparent activity
//        Intent i = new Intent(context, DummyActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);

        // TODO: 2/1/2018 This is a type where we play a music on the given alarm goes off
//        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//        MediaPlayer thePlayer = MediaPlayer.create(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
//
//        try {
//            thePlayer.setVolume((float) (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) / 7.0),
//                    (float) (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) / 7.0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        thePlayer.start();
        // Vibrate the mobile phone
//        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(2000);

    }
}

