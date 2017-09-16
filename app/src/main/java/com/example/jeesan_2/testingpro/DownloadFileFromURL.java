package com.example.jeesan_2.testingpro;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeesan_2.testingpro.CustomUtility.PermissionCheck;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadFileFromURL extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    ProgressDialog mProgressDialog;
    DownloadTask downloadTask;
    String image_url = "https://upload.wikimedia.org/wikipedia/commons/b/b4/JPEG_example_JPG_RIP_100.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

// instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(DownloadFileFromURL.this);
        mProgressDialog.setMessage("Downloading...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                downloadTask.cancel(true);
                mProgressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.btn)
    public void submit() {
        tv.setText("Started");
//        mProgressDialog.show();
//        downloadTask = new DownloadTask(DownloadFileFromURL.this);
//        downloadTask.execute(image_url);

        // Method 2 :
        if (PermissionCheck.readAndWriteExternalStorage(DownloadFileFromURL.this))
            downlaodViaDownloadManager();


    }

    public static boolean isDownloadManagerAvailable(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }
        return false;
    }

    private void downlaodViaDownloadManager() {
        String url = image_url;
        String filename = URLUtil.guessFileName(url, null, MimeTypeMap.getFileExtensionFromUrl(url));
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("HapHealth Document Download");
        request.setTitle(filename);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filename);
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    public boolean haveStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.e("Permission error", "You have permission");
                return true;
            } else {

                Log.e("Permission error", "You have asked for permission");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //you dont need to worry about these stuff below api level 23
            Log.e("Permission error", "You already have the permission");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 1) {
                //you have the permission now.
                downlaodViaDownloadManager();
            }

        } else
            Toast.makeText(this, "Permission Denied by user so operation can't be performed.", Toast.LENGTH_LONG).show();
    }


}