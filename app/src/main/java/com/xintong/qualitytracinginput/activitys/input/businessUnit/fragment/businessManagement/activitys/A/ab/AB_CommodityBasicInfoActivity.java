package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.A.ab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.SimpleWebViewActivity;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.bindView.AnnotateUtil;
import com.xintong.qualitytracinginput.utils.bindView.BindView;
import com.xintong.qualitytracinginput.utils.bindView.MyClick;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
//Created by 马军达 on 2018/1/29.

//商品基本信息
public class AB_CommodityBasicInfoActivity extends Activity{
    Context context;
//    @BindView(R.id.newAdd)
//    Button newAdd;//新增商品信息
    @BindView(R.id.webView)
    WebView webView;//商品信息列表
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ab_commodity_basic_info_activity);
        context=AB_CommodityBasicInfoActivity.this;
        AnnotateUtil.inject(this);
        initView();
    }
    private void initView() {
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/AB_CommodityBasicInfoController/getAB_CommodityBasicInfoList";
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
    public void showMsg(String msg) {
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public void newAdd(String msg,String selectedId) {//新添加商品
        Toast.makeText(context,msg+"  selectedId:"+selectedId,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context,AB_AddCommodityBasicInfoFormActivity.class);
        intent.putExtra("selectedId",selectedId);
        startActivity(intent);
    }
    @JavascriptInterface
    public void edit(String msg,String selectedId) {//编辑
        Toast.makeText(context,msg+"  selectedId:"+selectedId,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context,AB_EditCommodityBasicInfoFormActivity.class);
        intent.putExtra("selectedId",selectedId);
        startActivity(intent);
    }
   @JavascriptInterface
    public void seeDetail(String msg,String row,String url) {//查看详情
        Toast.makeText(context,msg+"  row:"+row,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context,SimpleWebViewActivity.class);
//        row="111";
       try {
           row= URLEncoder.encode(row,"utf-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       String url1=ConstNumbers.Urls.ip_address.substring(0,ConstNumbers.Urls.ip_address.length()-1)+url+"?row="+row;
        intent.putExtra("url",url1);
        startActivity(intent);
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
