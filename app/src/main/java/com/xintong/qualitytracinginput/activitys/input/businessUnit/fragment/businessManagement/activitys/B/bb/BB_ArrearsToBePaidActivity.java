package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.bb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.ba.BA_AddPurchasingAndWarehousFormActivity;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.bindView.AnnotateUtil;
import com.xintong.qualitytracinginput.utils.bindView.BindView;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

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
        setContentView(R.layout.bb_arrears_to_be_paid_activity);
        AnnotateUtil.inject(this);
        context=BB_ArrearsToBePaidActivity.this;
        initView();
    }
    private void initView() {
        webView.getSettings().setJavaScriptEnabled(true);
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/BB_ArrearsToBePaidController/getBB_ArrearsToBePaidList";//
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        webView.addJavascriptInterface(this, "jsa");
        webView.requestFocus(View.FOCUS_DOWN);
        webView.loadUrl(url);
    }
    @JavascriptInterface
    public void cancelAndroid(String msg) {
//        Toast.makeText(context,"JS调用了Android的方法",Toast.LENGTH_SHORT).show();
        finish();
    }
}

