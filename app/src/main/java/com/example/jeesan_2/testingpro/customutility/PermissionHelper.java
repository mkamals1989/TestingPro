package com.example.jeesan_2.testingpro.customutility;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class PermissionHelper {

    public final static int TAG_LOCATION_RESULTCODE = 101;
    public final static int TAG_STORAGE_READ_WRITE_RESULTCODE = 102;
    public final static int TAG_CAMERA_RESULTCODE = 103;
    public final static int TAG_RECOERD_AUDIO_RESULTCODE = 104;
    public final static int TAG_SMS_RESULTCODE = 105;
    public final static int TAG_CONTACTS_RESULTCODE = 106;
    public final static int TAG_SENSORS_RESULTCODE = 107;
    public final static int TAG_CALLPHONE_RESULTCODE = 108;

    @SuppressLint("InlinedApi")
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static String[] PERMISSIONS_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION};

    private static String[] PERMISSIONS_CAMERA = {Manifest.permission.CAMERA};

    private static String[] PERMISSIONS_RECORD_AUDIO = {Manifest.permission.RECORD_AUDIO};

    private static String[] PERMISSIONS_SMS = {Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS};

    private static String[] PERMISSIONS_CONTACTS = {Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS};

    private static String[] PERMISSIONS_SENSORS = {Manifest.permission.BODY_SENSORS};

    private static String[] PERMISSIONS_CALL = {Manifest.permission.CALL_PHONE};

    private Activity mActivity = null;

    public PermissionHelper(Activity mActivity) {
        this.mActivity = (Activity) mActivity;
    }

    public PermissionHelper(AppCompatActivity mActivity) {
        this.mActivity = (Activity) mActivity;
    }

    public PermissionHelper(FragmentActivity mActivity) {
        this.mActivity = (Activity) mActivity;
    }

    // Check Location Permission
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                ) {

            if (ActivityCompat
                    .shouldShowRequestPermissionRationale(mActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_LOCATION,
                        TAG_LOCATION_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_LOCATION,
                        TAG_LOCATION_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

    // Check Storage Permission
    public boolean checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) || ActivityCompat
                    .shouldShowRequestPermissionRationale(mActivity,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_STORAGE,
                        TAG_STORAGE_READ_WRITE_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_STORAGE,
                        TAG_STORAGE_READ_WRITE_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

    // Check Camera Permission
    public boolean checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_CAMERA,
                        TAG_CAMERA_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_CAMERA,
                        TAG_CAMERA_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

    // Check Record Audio Permission
    public boolean checkRecordAudioPermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.RECORD_AUDIO)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_RECORD_AUDIO,
                        TAG_RECOERD_AUDIO_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_RECORD_AUDIO,
                        TAG_RECOERD_AUDIO_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

    // Check SMS Permission
    public boolean checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.READ_SMS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                            Manifest.permission.SEND_SMS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                            Manifest.permission.RECEIVE_SMS)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_SMS,
                        TAG_SMS_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_SMS,
                        TAG_SMS_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

    // Check Contact Permission
    public boolean checkContactsPermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(mActivity,
                        Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.READ_CONTACTS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                            Manifest.permission.WRITE_CONTACTS) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                            Manifest.permission.GET_ACCOUNTS)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_CONTACTS,
                        TAG_CONTACTS_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_CONTACTS,
                        TAG_CONTACTS_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

    // Check Sensor Permission
    public boolean checkSensorPermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.BODY_SENSORS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.BODY_SENSORS)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_SENSORS,
                        TAG_SENSORS_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_SENSORS,
                        TAG_SENSORS_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

    // Check Call Permission
    public boolean checkCallPhonePermission() {
        if (ContextCompat.checkSelfPermission(mActivity,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_CALL,
                        TAG_CALLPHONE_RESULTCODE);
            } else {
                ActivityCompat.requestPermissions(mActivity, PERMISSIONS_CALL,
                        TAG_CALLPHONE_RESULTCODE);
            }
            return false;
        } else {
            return true;
        }
    }

}
