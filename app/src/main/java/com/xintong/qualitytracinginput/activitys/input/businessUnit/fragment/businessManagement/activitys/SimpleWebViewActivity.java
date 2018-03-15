package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.bindView.AnnotateUtil;
import com.xintong.qualitytracinginput.utils.bindView.BindView;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

import java.net.URLEncoder;

//一个通用的webview 页用于
public class SimpleWebViewActivity extends Activity {
    Context context;
    @BindView(R.id.webView)
    WebView webView;//商品信息列表
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.simple_webview_activity);
        context=SimpleWebViewActivity.this;
        AnnotateUtil.inject(this);
        initView();
    }
    private void initView() {
        String url= getIntent().getExtras().getString("url");//通用的url形式
        System.out.println("######接收的url:"+url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        //获取webView的cookie
        CookieManager cookieManager = CookieManager.getInstance();
        String CookieStr = cookieManager.getCookie(url);
        System.out.println("####AB的Cookies = " + CookieStr);
        webView.addJavascriptInterface(this, "jsa");
        webView.loadUrl(url);//供应商基本信息

    }
    @JavascriptInterface
    public void showMessage(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
