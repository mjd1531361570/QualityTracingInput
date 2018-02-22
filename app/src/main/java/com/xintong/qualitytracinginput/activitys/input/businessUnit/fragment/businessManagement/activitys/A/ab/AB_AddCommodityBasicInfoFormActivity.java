package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.A.ab;

import android.app.Activity;
import android.content.Context;
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
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

/**
 * Created by Administrator on 2018/2/5.
 */
//添加商品基本信息表单
public class AB_AddCommodityBasicInfoFormActivity extends Activity{
    Context context;
    @BindView(R.id.webView)
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ab_commodity_basicinfo_form_activity);
        AnnotateUtil.inject(this);
        context=AB_AddCommodityBasicInfoFormActivity.this;
        initView();
    }

    private void initView() {
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/AB_CommodityBasicInfoController/getAB_CommodityBasicInfoForm";//新增供应商基本信息,获取表单
        webView.getSettings().setJavaScriptEnabled(true);
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
