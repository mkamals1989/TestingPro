package com.example.jeesan_2.testingpro.concepts;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jeesan_2.testingpro.asynctask.DownloadTask;
import com.example.jeesan_2.testingpro.customutility.PermissionCheck;
import com.example.jeesan_2.testingpro.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DownloadFileFromURL extends AppCompatActivity {

    // TODO: 11/5/2017  Checking first commit from laptop
    @BindView(R.id.tv)
    TextView tv;

    public ProgressDialog mProgressDialog;
    public DownloadTask downloadTask;
    private String image_url = "https://upload.wikimedia.org/wikipedia/commons/b/b4/JPEG_example_JPG_RIP_100.jpg";

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionCheck.readAndWriteExternalStorage(DownloadFileFromURL.this))
                downlaodViaDownloadManager();
        } else
            downlaodViaDownloadManager();


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
