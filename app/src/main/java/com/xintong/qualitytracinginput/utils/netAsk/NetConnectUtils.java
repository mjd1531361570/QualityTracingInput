package com.xintong.qualitytracinginput.utils.netAsk;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetConnectUtils {
	/**
	 * 判断是否有任何一种网络连接
	 */
	public static boolean checkNetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();// 返回网络状态信息
		if (info != null && info.isConnected()) {
			// 网络连接可用
			return true;
		}
		return false;
	}

	/**
	 * 判断wifi 是否可用
	 */
	public static boolean isWifiDataEnable(Context context) throws Exception {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean isWifiDataEnable = false;
		isWifiDataEnable = connectivityManager.getNetworkInfo(
				ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		return isWifiDataEnable;
	}

	// 判断是否为wifi 并且网络是链接的
	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}
	//判断是否为移动网络
	public static boolean isMobNet(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkINfo = cm.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}
	// 判断Gps是否可用    
	public static boolean isGpsEnable(Context context) {    
        LocationManager locationManager =     
                ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));    
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);    
    }    
}
