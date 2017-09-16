package com.example.jeesan_2.testingpro.CustomUtility;

import android.content.Context;
import android.os.Build;

/**
 * Created by Jeesan_2 on 16/09/2017.
 */

public class CommonUtilities {


    public static boolean isDownloadManagerAvailable(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return true;
        }
        return false;
    }
}
