package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.A.aa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.bindView.AnnotateUtil;
import com.xintong.qualitytracinginput.utils.bindView.BindView;
import com.xintong.qualitytracinginput.utils.bindView.MyClick;
import com.xintong.qualitytracinginput.utils.view.KeyBoardListener;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

//Created by 马军达 on 2018/1/29.

//供应商基本信息
public class AA_SupplierBasicInformationActivity extends Activity{
    Context context;
    @BindView(R.id.webView)
    public WebView webView;
    @BindView(R.id.newAdd)
    public WebView newAdd;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.aa_supplier_basic_information_activity);
//        AndroidBug5497Workaround.assistActivity(this);
        context=AA_SupplierBasicInformationActivity.this;
        AnnotateUtil.inject(this);
        initViews();
    }
    private void initViews() {
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/AA_SupplierBasicInfoController/getAA_SupplierBasicInfo";
        webView.getSettings().setJavaScriptEnabled(true);
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        webView.addJavascriptInterface(this, "jsa");
        webView.loadUrl(url);//供应商基本信息
    }
    @MyClick(R.id.newAdd)
    public void newAdd(View view){//新增供应商基本信息
        View alertView= LayoutInflater.from(context).inflate(R.layout.alert_web_view_layout,null);
        WebView webView=alertView.findViewById(R.id.webView);
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/AA_SupplierBasicInfoController/getAA_SupplierBasicInfoForm";//新增供应商基本信息,获取表单
        webView.getSettings().setJavaScriptEnabled(true);
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        webView.addJavascriptInterface(this, "jsa");
        webView.loadUrl(url);
        alertDialog=new AlertDialog.Builder(context).setView(alertView).create();
        alertDialog.show();
    }
    @JavascriptInterface
    public void cancelAndroidAlert(String msg) {
//        Toast.makeText(context,"JS调用了Android的方法",Toast.LENGTH_SHORT).show();
//        alertDialog.cancel();
    }
    // 继承自Object类
    public class AndroidtoJs extends Object {//js-->android
        // 定义JS需要调用的方法
        // 被JS调用的方法必须加入@JavascriptInterface注解
        @JavascriptInterface
        public void hello(String msg) {
            Toast.makeText(context,"JS调用了Android的hello方法",Toast.LENGTH_SHORT).show();
        }
    }
}
