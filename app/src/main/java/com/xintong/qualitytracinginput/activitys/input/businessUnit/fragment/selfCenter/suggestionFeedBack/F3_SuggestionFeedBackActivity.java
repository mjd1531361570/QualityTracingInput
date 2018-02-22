package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.selfCenter.suggestionFeedBack;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.webkit.WebView;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

public class F3_SuggestionFeedBackActivity extends Activity{
    Context context;
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.f3_suggestion_feedback_activity);
        findView();
        initView();
    }

    private void findView() {
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/BA_PurchasingAndWarehousController/getBA_PurchasingAndWarehous";
        webView=findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        webView.loadUrl(url);
    }

    private void initView() {
    }
}
