package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.D.dc;

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

//DC销售退货
public class DC_SalesReturnActivity extends Activity{
    Context context;
    @BindView(R.id.webView)
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dc_sales_return_activity);
        AnnotateUtil.inject(this);
        context= DC_SalesReturnActivity.this;
        initView();
    }

    private void initView() {
        webView.getSettings().setJavaScriptEnabled(true);
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/DC_SalesReturnController/getDC_SalesReturn";//销售出库
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        webView.addJavascriptInterface(this, "jsa");
        webView.requestFocus(View.FOCUS_DOWN);
        webView.loadUrl(url);
    }
    @MyClick(R.id.newAdd)
    public void newAdd(View view) {//新增DC销售退货
        Intent intent=new Intent(context,DC_AddSalesReturnFormActivity.class);
        startActivity(intent);
    }
    @JavascriptInterface
    public void cancelAndroid(String msg) {
//        Toast.makeText(context,"JS调用了Android的方法",Toast.LENGTH_SHORT).show();
        finish();
    }
}
