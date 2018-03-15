package com.xintong.qualitytracinginput;

import android.os.Build;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.InputBusinessUnitMainActivity;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.MD5Util;
import com.xintong.qualitytracinginput.utils.netAsk.Httpthread;
import com.xintong.qualitytracinginput.utils.netAsk.LoadDialogDao;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

//登录界面，马军达2018年1月3日10:55:41
public class MainActivity extends Activity implements OnClickListener{
    Context context;
    Button loginButton;
    EditText userAcountEdit;
    EditText userPasswordEdit;
    Spinner userRoleSpinner;
    String userRoleSelected;//用户角色
    String userRoleIdSelected;//角色id
    InputMethodManager manager;
    Switch rememberPasswordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        initView();
    }
    private void initView() {
        findViewById(R.id.login_button).setOnClickListener(this);
        userAcountEdit = (EditText) findViewById(R.id.user_acount);
        //监听账户编辑框焦点变化
        userAcountEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()!=0){
                    askNetGetUserRole(s+"");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        userPasswordEdit = (EditText) findViewById(R.id.user_password);
        userRoleSpinner=(Spinner) findViewById(R.id.userRoleSpinner);
        rememberPasswordBox = (Switch) findViewById(R.id.remember_password_check);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (MyPreference.getInstance(context).getRememberPassword().equals("Yes")) {
            rememberPasswordBox.setChecked(true);
            userAcountEdit.setText(MyPreference.getUserAcount());
            userPasswordEdit.setText(MyPreference.getUserPassword());
        } else {
            rememberPasswordBox.setChecked(true);
        }
    }
    public void askNetGetUserRole(String userAcount) {//获取用户角色
        String url = ConstNumbers.Urls.ip_address + "login/app/appClient2/login/getUserRoleByLoginName";
        LoadDialogDao lDialog = new LoadDialogDao(this, "正在加载...");
        RequestParams params = new RequestParams();
        params.addBodyParameter("loginName", userAcount);
        Httpthread httpthread = new Httpthread(this, new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        String resp = msg.getData().get("result").toString();
                        System.out.println("###获取用户角色成功返回数据resp:" + resp.toString());
                        try {
                            JSONObject resultJSON = new JSONObject(resp);
                            int code = Integer.parseInt(resultJSON.getString("code"));
                            switch (code) {
                                case ConstNumbers.ClientResult.result_ok:
                                    JSONArray jsonContent = resultJSON.getJSONArray("content");
                                    String[] ssRole=new String[jsonContent.length()];
                                    String[] ssRoleId=new String[jsonContent.length()];
                                    for(int i=0;i<jsonContent.length();i++){
                                        ssRole[i]=jsonContent.getJSONObject(i).getString("name");//角色
                                        ssRoleId[i]=jsonContent.getJSONObject(i).getString("id");
                                    }
                                    initUserRoleSpinner(ssRole,ssRoleId);
                                    break;
                                case ConstNumbers.ClientResult.result_ok_no_value:
                                    Toast.makeText(context, "该用户尚未分配角色", Toast.LENGTH_SHORT).show();
                                    JSONArray jsonContent2 = resultJSON.getJSONArray("content");
                                    String[] ssRole2=new String[jsonContent2.length()];
                                    String[] ssRoleId2=new String[jsonContent2.length()];
                                    for(int i=0;i<jsonContent2.length();i++){
                                        ssRole2[i]=jsonContent2.getJSONObject(i).getString("name");//角色
                                        ssRoleId2[i]=jsonContent2.getJSONObject(i).getString("id");
                                    }
                                    initUserRoleSpinner(ssRole2,ssRoleId2);//其实是让这里数据为空适配
                                    break;
                                default:
                                    break;
                            }
                            System.out.println("#####返回数据:" + resultJSON);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 1000:
                        System.out.println("###请求数据失败");
                        Toast.makeText(MainActivity.this, "网络请求失败",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, params, null);
        httpthread.httpthread(url);

    }
    protected void initUserRoleSpinner(String[] ssRole,String[] ssRoleId) {//用户角色列表
        final String[] userRoleSpinnerData=ssRole;//用户角色文字
        final String[] userRoleSpinnerDataKeys=ssRoleId;//用户角色id
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,userRoleSpinnerData);
        userRoleSpinner.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();//每次请求后数据重置
        userRoleSelected=null;
        userRoleIdSelected=null;
        userRoleSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                userRoleSelected=userRoleSpinnerData[position];
                userRoleIdSelected=userRoleSpinnerDataKeys[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                if (!(userAcountEdit.getText().toString().length() > 0 && userPasswordEdit.getText().toString().length() > 0)) {
                    Toast.makeText(getApplicationContext(), "账号和密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (userRoleSelected==null) {
                    Toast.makeText(getApplicationContext(), "用户未分配角色",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(rememberPasswordBox.isChecked()){
                    MyPreference.getInstance(MainActivity.this).setUserAcount(userAcountEdit.getText().toString());
                    MyPreference.getInstance(MainActivity.this).setRememberPassword(userPasswordEdit.getText().toString());
                }
                AskNetToLogin();
                break;
            default:
                break;
        }
    }
    private void AskNetToLogin() {//app登录
        if (rememberPasswordBox.isChecked()) {
            MyPreference.getInstance(getApplicationContext()).setRememberPassword("Yes");
        } else {
            MyPreference.getInstance(getApplicationContext()).setRememberPassword("No");
        }
        String url = ConstNumbers.Urls.ip_address + "login/app/appClient2/login/appClient2Login";
        LoadDialogDao lDialog = new LoadDialogDao(this, "正在加载...");
        RequestParams params = new RequestParams();
        params.addBodyParameter("loginName", userAcountEdit.getText().toString());
        params.addBodyParameter("password", MD5Util.getMD5(userPasswordEdit.getText().toString()));
        params.addBodyParameter("lastLoginRoleId",userRoleIdSelected);//最后登录的角色id
        // StringBuffer cookie=new StringBuffer();
        // cookie.append("JSESSIONID"+"=");
        // cookie.append(MyPreference.getInstance(getApplicationContext()).getCookie()+";");
        // params.addHeader("Cookie", cookie.toString());
        Httpthread httpthread = new Httpthread(this, new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        String resp = msg.getData().get("result").toString();
                        System.out.println("###app登陆成功返回数据resp:" + resp.toString());
                        try {
                            JSONObject resultJSON = new JSONObject(resp);
                            int code = Integer.parseInt(resultJSON.getString("code"));
                            switch (code) {
                                case ConstNumbers.ClientResult.result_ok:
                                    Toast.makeText(MainActivity.this,"登陆成功跳转", Toast.LENGTH_SHORT).show();
                                    MyPreference.getInstance(getApplicationContext()).setUserAcount(userAcountEdit.getText().toString());
                                    MyPreference.getInstance(getApplicationContext()).setUserPassword(userPasswordEdit.getText().toString());
                                    JSONObject jsonContent = resultJSON.getJSONObject("content");
                                    MyPreference.getInstance(context).setUserName(jsonContent.getString("username"));
                                    MyPreference.getInstance(context).setUserAcount("loginname");
////							// 存储用户权限的id sys_role
                                    MyPreference.getInstance(context).setLastLoginRole(jsonContent.getString("lastloginrole"));
//							// 行业类型 1综合 admin 2.种植业 3.畜牧业 4.渔业
                                    String industries =jsonContent.getString("industries");
//							// 用户类型0 监管人员 1.服务对象
                                    int userType = Integer.parseInt(jsonContent.getString("usertype"));
                                    MyPreference.getInstance(context).setUnit(jsonContent.getString("unit"));//id
                                    MyPreference.getInstance(context).setUnitName(jsonContent.getString("unitname"));//文字
                                    MyPreference.getInstance(context).setUserType(jsonContent.getString("usertype"));
                                    MyPreference.getInstance(context).setRegion(jsonContent.getString("region"));
                                    MyPreference.getInstance(context).setUserId(jsonContent.getString("id"));
                                    MyPreference.getInstance(context).setIndustries(String.valueOf(industries));
                                    MyPreference.getInstance(context).setIsLogined("Yes");//存储已经登录过了
                                    System.out.println("######MyPreference.getInstance(context).getIsLogined():"+MyPreference.getInstance(context).getIsLogined());
                                    quryIndustryByUserRole(userType,userRoleSelected,userRoleIdSelected);

                                    break;
                                case ConstNumbers.ClientResult.result_ok_no_value:
                                    Toast.makeText(MainActivity.this,"账号或密码错误", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
//							Toast.makeText(MainActivity.this,resultJSON.getString("message"), Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                            System.out.println("#####返回数据:" + resultJSON);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MyPreference myPreference = MyPreference.getInstance(getApplicationContext());
                        myPreference.setUserAcount(userAcountEdit.getText().toString());
                        myPreference.setUserPassword(userPasswordEdit.getText().toString());
                        break;
                    case 1000:
                        System.out.println("###请求数据失败");
                        Toast.makeText(MainActivity.this, "网络请求失败",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, params, lDialog);
        httpthread.httpthread(url);
    }

    protected void quryIndustryByUserRole(final int userType,String userRoleSelected,String userRoleIdSelected) {
        //根据角色查询行业
        String url = ConstNumbers.Urls.ip_address + "/login/app/appClient2/login/queryIndustryByUserRole";
        RequestParams params = new RequestParams();
        params.addBodyParameter("userRoleSelected", userRoleSelected);//文字{name=种植业基地人员, id=13}
        params.addBodyParameter("userRoleIdSelected",userRoleIdSelected);//id
        params.addBodyParameter("userId",MyPreference.getUserId());//id
        Httpthread httpthread = new Httpthread(this, new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        String resp = msg.getData().get("result").toString();
                        System.out.println("###查询行业成功返回数据resp:" + resp.toString());
                        try {
                            JSONObject resultJSON = new JSONObject(resp);
                            int code = Integer.parseInt(resultJSON.getString("code"));
                            switch (code) {
                                case ConstNumbers.ClientResult.result_ok:
//							Toast.makeText(MainActivity.this,"查询行业成功", Toast.LENGTH_SHORT).show();
                                    int industry=resultJSON.getJSONObject("content").getInt("industries");
                                    String classify=resultJSON.getJSONObject("content").getString("classify");//1基地 2投入品生产单位 3投入品经营单位4定点屠宰企业
                                    choseToJump(industry,userType,classify);
                                    break;
                                case ConstNumbers.ClientResult.result_ok_no_value:
                                    Toast.makeText(MainActivity.this,"查询行业成功失败", Toast.LENGTH_SHORT).show();
                                    break;
                                case 3:
//									Toast.makeText(MainActivity.this,resultJSON.getString("message"), Toast.LENGTH_SHORT).show();
                                    break;
                                default:
                                    break;
                            }
                            System.out.println("#####返回数据:" + resultJSON);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MyPreference myPreference = MyPreference.getInstance(getApplicationContext());
                        myPreference.setUserAcount(userAcountEdit.getText().toString());
                        myPreference.setUserPassword(userPasswordEdit.getText().toString());
                        break;
                    case 1000:
                        System.out.println("###请求数据失败");
                        Toast.makeText(MainActivity.this, "网络请求失败",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, params, null);
        httpthread.httpthread(url);

    }
    protected void choseToJump(int industry, int userType, String classify) {
        System.out.println("####看看industry：" +industry+"  userType:"+ userType);
        switch (userType) {
            case 0:// 监管人员
                Toast.makeText(MainActivity.this, "你不是服务对象权限不够",Toast.LENGTH_SHORT).show();
                break;
            case 1:// 服务对象
                switch (Integer.parseInt(classify)) {
                    case 1://1基地
                        switch (industry) {// 行业 1-综合 2-种植 3-畜牧 4-渔产 5-管理员 6-超级管理员
                            case 2:
                                if (Integer.parseInt(android.os.Build.VERSION.SDK) <16) {
                                    Toast.makeText(getApplicationContext(), "手机版本太低，请换用高版本的手机进行登录！",
                                            Toast.LENGTH_LONG).show();
                                } else {
//                                    Intent intent2 = new Intent(getApplicationContext(),PlantingMainActivity.class);
//                                    startActivity(intent2);
//                                    MyPreference.getInstance(getApplicationContext()).setRememberWhich("1");
//                                    MyPreference.getInstance(context).setIndustry(String.valueOf(industry));//记住用哪个行业登陆的
//                                    finish();
                                    Toast.makeText(context,"请用投入品用户登录",Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 3:
                                if (Integer.parseInt(android.os.Build.VERSION.SDK) <16) {
                                    Toast.makeText(getApplicationContext(), "手机版本太低，请换用高版本的手机进行登录！",
                                            Toast.LENGTH_LONG).show();
                                } else {
//							Intent intent3 = new Intent(getApplicationContext(),AnimalMainActivity.class);
//                                    Intent intent3 = new Intent(getApplicationContext(),AnimalHusbandryMainActivity.class);
//                                    startActivity(intent3);
//                                    MyPreference.getInstance(getApplicationContext()).setRememberWhich("2");
//                                    MyPreference.getInstance(context).setIndustry(String.valueOf(industry));
//                                    finish();
                                    Toast.makeText(context,"请用投入品用户登录",Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 4:
                                if (Integer.parseInt(android.os.Build.VERSION.SDK) <16) {
                                    Toast.makeText(getApplicationContext(), "手机版本太低，请换用高版本的手机进行登录！",
                                            Toast.LENGTH_LONG).show();
                                } else {
//                                    Intent intent4 = new Intent(getApplicationContext(),AquacultureMainActivity.class);
//                                    startActivity(intent4);
//                                    MyPreference.getInstance(getApplicationContext()).setRememberWhich("3");
//                                    MyPreference.getInstance(context).setIndustry(String.valueOf(industry));
//                                    finish();
                                    Toast.makeText(context,"请用投入品用户登录",Toast.LENGTH_SHORT).show();
                                }
                                break;
                            default:
                                Toast.makeText(MainActivity.this, "你不是服务对象权限不够",Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                    case 2://2投入品生产单位
                        Toast.makeText(context, "2投入品生产单位还没开发", Toast.LENGTH_SHORT).show();
                        break;
                    case 3://3投入品经营单位
//                        String url = ConstNumbers.Urls.ip_address + "login/app/appClient2/login/appClient2Login";
//                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//                            CookieSyncManager.createInstance(context);
//                        }
//                        CookieManager cookieManager = CookieManager.getInstance();
//                        String CookieStr = cookieManager.getCookie(url);
//                        System.out.println("#####login CookieStr:"+CookieStr);
//                        MyPreference.getInstance(context).setCookie(CookieStr);//在这存储的cookie

                        Intent intent5=new Intent(context, InputBusinessUnitMainActivity.class);
                        startActivity(intent5);
                        MyPreference.getInstance(getApplicationContext()).setRememberWhich("5");
                        MyPreference.getInstance(context).setIndustries(String.valueOf(industry));//
                        finish();

                        break;
                    case 4://4定点屠宰企业

                        break;

                    default:
                        break;
                }


                break;

            default:
                break;
        }

    }
}
