package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.bc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.xintong.qualitytracinginput.utils.bindView.MyClick;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

/**
 * Created by Administrator on 2018/2/5.
 */
//采购退货
public class BC_PurchaseOfReturnActivity extends Activity {
    Context context;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.newAdd)
    WebView newAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bc_purchase_of_return_activity);
        AnnotateUtil.inject(this);
        context=BC_PurchaseOfReturnActivity.this;
        initView();
    }
    private void initView() {
        webView.getSettings().setJavaScriptEnabled(true);
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/BC_PurchaseOfReturnController/getPurchaseOfReturnForm";//新增供应商基本信息,获取表单
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        webView.addJavascriptInterface(this, "jsa");
        webView.requestFocus(View.FOCUS_DOWN);
        webView.loadUrl(url);
    }
    @MyClick(R.id.newAdd)
    public void newAdd(View view) {//新增采购信息
        Intent intent=new Intent(context,BA_AddPurchasingAndWarehousFormActivity.class);
        startActivity(intent);
    }
    @JavascriptInterface
    public void cancelAndroid(String msg) {
//        Toast.makeText(context,"JS调用了Android的方法",Toast.LENGTH_SHORT).show();
        finish();
    }
}

