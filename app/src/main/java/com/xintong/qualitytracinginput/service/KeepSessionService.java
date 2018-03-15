package com.xintong.qualitytracinginput.service;

import java.util.Date;
import java.util.Random;

import com.lidroid.xutils.http.RequestParams;
import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.receiver.KeepSessionAlarmReceiver;
import com.xintong.qualitytracinginput.utils.netAsk.Httpthread;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

//https://www.jianshu.com/p/4b10de256ddf
public class KeepSessionService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT) @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("####KeepSessionService 运行了 at：" + new Date().toString());
		RequestParams params=new RequestParams();
		String url= ConstNumbers.Urls.ip_address+"login/keepsession";
		params.addBodyParameter("RandStr", new Random().nextInt()+"");
		Httpthread httpthread=new Httpthread(getApplicationContext(),
				new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					System.out.println("####KeepSession成功");
					break;
				case 1000:
					System.out.println("####KeepSession网络错误成功");
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
		if(ConstNumbers.isCanceledAlarmManager==true){
			
		}else{
			AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
			int tenMinute = 10 * 60 * 1000; // 这是10分钟的毫秒数
			long triggerAtTime = SystemClock.elapsedRealtime() + tenMinute;
			Intent i = new Intent(this, KeepSessionAlarmReceiver.class);
			PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
//        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
			manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);//准确的计时
		}
        return super.onStartCommand(intent, flags, startId);
    }
}

