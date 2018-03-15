package com.xintong.qualitytracinginput.activitys.input.businessUnit;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.F1_InputBusinessManagementFragment;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.F2_InputBusinessFragment;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.selfCenter.F3_InputBusinessSelfCenterFragment;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.service.KeepSessionService;
import com.xintong.qualitytracinginput.utils.webView.WebViewUtil;

//投入品经营单位三个选项卡
public class InputBusinessUnitMainActivity extends Activity implements OnCheckedChangeListener{
	Context context;
	LinearLayout saveFragmentAll;
	RadioGroup radioGroup;
	F1_InputBusinessManagementFragment fragment1;
	F2_InputBusinessFragment fragment2;
	F3_InputBusinessSelfCenterFragment fragment3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.input_business_unit_main_activity);
		context=InputBusinessUnitMainActivity.this;
		findView();
		initView();
		keepSession();
	}
	private void keepSession() {//保持session
		ConstNumbers.isCanceledAlarmManager=false;//开启
		Intent intent = new Intent(this, KeepSessionService.class);
		startService(intent);
	}
	private void findView() {
		radioGroup=(RadioGroup) findViewById(R.id.radio_group);
		radioGroup.setOnCheckedChangeListener(this);
	}

	private void initView() {
		fragment1=new F1_InputBusinessManagementFragment(this);
		fragment2=new F2_InputBusinessFragment(this);
		fragment3=new F3_InputBusinessSelfCenterFragment(this);
		getFragmentManager().beginTransaction().add(R.id.save_fragment_all,fragment1)
				.add(R.id.save_fragment_all,fragment2)
				.add(R.id.save_fragment_all,fragment3).commit();
		((RadioButton)findViewById(R.id.radio1)).setChecked(true);
		((RadioButton)findViewById(R.id.radio1)).setTextColor(Color.parseColor("#0770da"));
		openGPS(getApplicationContext());
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.radio1:
			getFragmentManager().beginTransaction().show(fragment1).hide(fragment2).hide(fragment3).commit();
			((RadioButton)findViewById(R.id.radio1)).setTextColor(Color.parseColor("#0770da"));
			((RadioButton)findViewById(R.id.radio2)).setTextColor(Color.parseColor("#b0b2b5"));
			((RadioButton)findViewById(R.id.radio3)).setTextColor(Color.parseColor("#b0b2b5"));
			break;
		case R.id.radio2:
			getFragmentManager().beginTransaction().show(fragment2).hide(fragment1).hide(fragment3).commit();
			((RadioButton)findViewById(R.id.radio2)).setTextColor(Color.parseColor("#0770da"));
			((RadioButton)findViewById(R.id.radio1)).setTextColor(Color.parseColor("#b0b2b5"));
			((RadioButton)findViewById(R.id.radio3)).setTextColor(Color.parseColor("#b0b2b5"));
			break;
		case R.id.radio3:
			getFragmentManager().beginTransaction().show(fragment3).hide(fragment1).hide(fragment2).commit();
			((RadioButton)findViewById(R.id.radio3)).setTextColor(Color.parseColor("#0770da"));
			((RadioButton)findViewById(R.id.radio1)).setTextColor(Color.parseColor("#b0b2b5"));
			((RadioButton)findViewById(R.id.radio2)).setTextColor(Color.parseColor("#b0b2b5"));
			break;
		default:
			break;
		}
		
	}
	
	/** 
     * 强制帮用户打开GPS 
     * @param context 
     */  
    public static final void openGPS(Context context) {  
        Intent GPSIntent = new Intent();  
        GPSIntent.setClassName("com.android.settings",  
                "com.android.settings.widget.SettingsAppWidgetProvider");  
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");  
        GPSIntent.setData(Uri.parse("custom:3"));  
        try {  
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();  
        } catch (CanceledException e) {  
            e.printStackTrace();  
        }  
    }  
	
}
