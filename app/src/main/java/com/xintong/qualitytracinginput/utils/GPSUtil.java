package com.xintong.qualitytracinginput.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.Toast;

public class GPSUtil {

    public static void isGotoOpenGPS(final Context context) {  
        LocationManager locationManager = (LocationManager) context  
                .getSystemService(Context.LOCATION_SERVICE);  
        // 判断GPS模块是否开启，如果没有则开启  
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  
            Toast.makeText(context, "请打开GPS",  
                    Toast.LENGTH_SHORT).show();  
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);  
            dialog.setMessage("请打开GPS");  
            dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {  
                @Override  
                public void onClick(DialogInterface arg0, int arg1) {  
                    // 转到手机设置界面，用户设置GPS  
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);  
                    ((Activity)context).startActivityForResult(intent, 0); // 设置完成后返回到原来的界面  
                }  
            });  
            dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {  
                @Override  
                public void onClick(DialogInterface arg0, int arg1) {  
                    arg0.dismiss();  
                }  
            } );  
            dialog.show();  
        } else {  
            // 弹出Toast  
//          Toast.makeText(TrainDetailsActivity.this, "GPS is ready",  
//                  Toast.LENGTH_LONG).show();  
//          // 弹出对话框  
//          new AlertDialog.Builder(this).setMessage("GPS is ready")  
//                  .setPositiveButton("OK", null).show();  
        }  
    }  
}
