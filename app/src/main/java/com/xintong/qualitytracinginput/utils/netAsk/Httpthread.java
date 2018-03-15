package com.xintong.qualitytracinginput.utils.netAsk;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.entity.MyPreference;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
public class Httpthread {
	RequestParams params;
	Handler handler;
	Context context;
	LoadDialogDao lDialog;
	Message msg;
	public Httpthread(Context context,Handler handler,RequestParams params,LoadDialogDao lDialog){
		this.params = params;
		this.handler = handler;
		this.context = context;
		this.lDialog = lDialog;
		msg = new Message();
	}
	
	public void httpthread(final String url){
		if(lDialog!=null){
			lDialog.show();
		}
		System.out.println("####开始执行请求");
		final HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 7200); //设置超时时间   7200s
		if(!url.equals(ConstNumbers.Urls.ip_address+"login/app/appClient2/login/getUserRoleByLoginName")
				||!url.equals(ConstNumbers.Urls.ip_address+"login/app/appClient2/login/appClient2Login")){//除获取角色和登录外要传其它情况都要传cookie
//			DefaultHttpClient httpClient = (DefaultHttpClient) http.getHttpClient();
//			CookieStore  cookieStore=httpClient.getCookieStore();
//			cookieStore= (CookieStore) MyPreference.getInstance(context);
//			http.configCookieStore(cookieStore);
			StringBuffer cookie=new StringBuffer();
			cookie.append("JSESSIONID"+"=");
			cookie.append(MyPreference.getInstance(context).getCookie()+";");
			params.addHeader("Cookie", cookie.toString());
		}

		http.send(HttpMethod.POST, url, params,  new RequestCallBack<String>(){

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				System.out.println("###网络失败原因arg0:"+arg0);
				System.out.println("###网络失败原因arg1:"+arg1);
//				Toast.makeText(context, "图片选择未完成", Toast.LENGTH_SHORT).show();
				msg.what=1000;
				handler.sendMessage(msg);
				if(lDialog!=null){
					lDialog.hide();
				}
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				super.onLoading(total, current, isUploading);
				System.out.println("####正在传输传输百分比:"+(int) (((int) current / (float) total) * 100));
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				msg.what=1;
				Bundle data=new Bundle();
				data.putCharSequence("result", arg0.result);
				msg.setData(data);
				handler.sendMessage(msg);
				if(lDialog!=null){
					lDialog.hide();
				}
				if(url.equals(ConstNumbers.Urls.ip_address+"login/app/appClient2/login/appClient2Login")){//登录时记录获取cookie
//					if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
						DefaultHttpClient httpClient=(DefaultHttpClient) http.getHttpClient();
						CookieStore cookieStore = httpClient.getCookieStore();
						System.out.println("###返回存储的cookieStore："+cookieStore);
						String value=cookieStore.getCookies().get(0).getValue();
						System.out.println("###MyPreference存储的value："+value);
						MyPreference.getInstance(context).setCookie(value);
						http.configCookieStore(cookieStore);
//					}
				}

			}});
	}
}
