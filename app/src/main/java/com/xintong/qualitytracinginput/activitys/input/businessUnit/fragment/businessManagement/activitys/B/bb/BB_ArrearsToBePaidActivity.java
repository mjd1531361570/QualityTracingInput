package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.bb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.ba.BA_AddPurchasingAndWarehousFormActivity;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.bindView.AnnotateUtil;
import com.xintong.qualitytracinginput.utils.bindView.BindView;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/5.
 */
//欠款待付
public class BB_ArrearsToBePaidActivity  extends Activity {
    Context context;
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bb_arrears_to_be_paid_activity);
        AnnotateUtil.inject(this);
        context=BB_ArrearsToBePaidActivity.this;
        initView();
    }
    private void initView() {
        webView.getSettings().setJavaScriptEnabled(true);
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/BB_ArrearsToBePaidController/getBB_ArrearsToBePaidList";//
//        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/AB_CommodityBasicInfoController/getAB_CommodityBasicInfoForm";//
//        CookieSyncManager.createInstance(this);
//        CookieSyncManager.getInstance().startSync();
//        CookieManager.getInstance().removeSessionCookie();
//        webView.clearCache(true);
//        webView.clearHistory();
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
//        Map<String,String> map=new HashMap<String,String>();
//        StringBuffer cookie=new StringBuffer();
//        cookie.append("JSESSIONID"+"=");
//        cookie.append(MyPreference.getInstance(context).getCookie()+";");
//        map.put("Cookie",cookie.toString());
        webView.addJavascriptInterface(this, "jsa");
        webView.requestFocus(View.FOCUS_DOWN);
        webView.loadUrl(url);
    }
    @JavascriptInterface
    public void cancelAndroid(String msg) {
//        Toast.makeText(context,"JS调用了Android的方法",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CookieSyncManager.getInstance().stopSync();
    }

    @Override
    protected void onStop() {
        super.onStop();
        webView.destroy();
        this.finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        CookieSyncManager.getInstance().sync();
    }
}

