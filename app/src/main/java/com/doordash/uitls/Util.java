package com.doordash.uitls;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Util {
    public static String TAG="Util";
    public static boolean isNetWorkConnected(Application context) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        Log.d(TAG, "*** isNetWorkConnected: " +
                (networkInfo != null && networkInfo.isConnected()) + " ***");
        return (networkInfo != null && networkInfo.isConnected());
    }//isNetWorkConnected
}
