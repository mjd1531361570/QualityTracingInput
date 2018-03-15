package com.xintong.qualitytracinginput.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xintong.qualitytracinginput.service.KeepSessionService;

//每隔10分钟请求keepsession
public class KeepSessionAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent in = new Intent(context, KeepSessionService.class);
        context.startService(in);
    }
}

