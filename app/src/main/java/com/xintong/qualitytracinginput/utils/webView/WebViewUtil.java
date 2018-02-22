package com.xintong.qualitytracinginput.utils.webView;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xintong.qualitytracinginput.utils.view.CommonWebView;
import com.xintong.qualitytracinginput.utils.view.ProgressWebView;

/**
 * Created by Administrator on 2018/1/30.
 */

public class WebViewUtil {

    public WebView setSettings(WebView webView) {
        //		String url="http://jgsb.agri.cn/controller?SERVICE_ID=REGISTRY_JCSJ_MRHQ_SHOW_SERVICE&recordperpage=15&newsearch=true&login_result_sign=nologin";
        // 设置WebView属性，能够执行JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
//        webView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
//        webView.getSettings().setBuiltInZoomControls(true);
        // 为图片添加放大缩小功能
//        webView.getSettings().setUseWideViewPort(true);
//        webView.setInitialScale(70);   //100代表不缩放
        return webView;
    }

    public static void setCookie(Context context,String cookieString,String url) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(context);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url, cookieString);//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可

    }
}
