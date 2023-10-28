package com.speedtest;

import static androidx.core.content.ContextCompat.getSystemService;
import com.facebook.react.bridge.Callback;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CustomModule extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactContext;
    CustomModule(ReactApplicationContext context){
        super(context);
        reactContext=context;
    }
    @NonNull
    @Override
    public String getName() {
        return "CustomModule";
    }

    @ReactMethod
    public void getSpeed(Callback successCallback){
        ConnectivityManager connectivityManager = (ConnectivityManager) reactContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            // Connected to mobile data
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {
                int maxDownloadSpeed = networkCapabilities.getLinkDownstreamBandwidthKbps();
                int maxUploadSpeed = networkCapabilities.getLinkUpstreamBandwidthKbps();
                // Max download and upload speeds in Kbps
                // System.out.println(maxDownloadSpeed+"this is speed "+maxUploadSpeed);
                Toast.makeText(reactContext,"hello"+maxDownloadSpeed,Toast.LENGTH_LONG).show();
                successCallback.invoke(maxDownloadSpeed, maxUploadSpeed);
            }
        }
         else {
            // Not connected to mobile data or no network connection
            Toast.makeText(reactContext,"not connectedsss",Toast.LENGTH_LONG).show();
        }

    }

}
//
//    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//
//        if (networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                // Connected to mobile data
//                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
//                if (networkCapabilities != null) {
//                int maxDownloadSpeed = networkCapabilities.getLinkDownstreamBandwidthKbps();
//                int maxUploadSpeed = networkCapabilities.getLinkUpstreamBandwidthKbps();
//                // Max download and upload speeds in Kbps
//                System.out.println(maxDownloadSpeed+"this is speed "+maxUploadSpeed);
//                networkSpeedTextView.setText("Network Speed\nDownload: " + maxDownloadSpeed + " Kbps\nUpload: " + maxUploadSpeed + " Kbps");
//                }
//                } else {
//                // Not connected to mobile data or no network connection
//                networkSpeedTextView.setText("Not connected to mobile data");
//                }
