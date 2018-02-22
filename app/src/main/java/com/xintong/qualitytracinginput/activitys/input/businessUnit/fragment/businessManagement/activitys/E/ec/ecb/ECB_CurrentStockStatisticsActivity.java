package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.E.ec.ecb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.bindView.AnnotateUtil;
import com.xintong.qualitytracinginput.utils.bindView.BindView;
import com.xintong.qualitytracinginput.utils.bindView.MyClick;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

//ECB当前库存盘点
public class ECB_CurrentStockStatisticsActivity extends Activity{
    Context context;
    @BindView(R.id.webView)
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ecb_current_stock_statistics_activity);
        AnnotateUtil.inject(this);
        context= ECB_CurrentStockStatisticsActivity.this;
        initView();
    }

    private void initView() {
        webView.getSettings().setJavaScriptEnabled(true);
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/DA_SellingOutOfTheTreasuryController/getDA_SellingOutOfTheTreasury";//ECB当前库存盘点
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
