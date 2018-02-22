package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.A.ab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.bindView.AnnotateUtil;
import com.xintong.qualitytracinginput.utils.bindView.BindView;
import com.xintong.qualitytracinginput.utils.bindView.MyClick;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

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
        String url= ConstNumbers.Urls.ip_address+"login/app/appClient3/AB_CommodityBasicInfoController/getAB_CommodityBasicInfo";
        webView.getSettings().setJavaScriptEnabled(true);
        WebViewUtil.setCookie(context, MyPreference.getCookie(),url);//设置cookie
        webView.addJavascriptInterface(this, "jsa");
        webView.loadUrl(url);//供应商基本信息

    }
//    @MyClick(R.id.newAdd)
//    public void addNewCommodity(View view){
//        Toast.makeText(context,"添加",Toast.LENGTH_SHORT).show();
//        Intent intent=new Intent(context,AB_AddCommodityBasicInfoFormActivity.class);
//        startActivity(intent);
//    }
    @JavascriptInterface
    public void newAdd(String msg) {//新添加商品
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(context,AB_AddCommodityBasicInfoFormActivity.class);
        startActivity(intent);
    }
}
