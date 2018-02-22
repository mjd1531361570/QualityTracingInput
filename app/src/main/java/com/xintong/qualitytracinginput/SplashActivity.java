package com.xintong.qualitytracinginput;

import com.xintong.qualitytracinginput.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.xintong.qualitytracinginput.activitys.input.businessUnit.InputBusinessUnitMainActivity;
import com.xintong.qualitytracinginput.entity.MyPreference;

public class SplashActivity extends Activity{
		private Handler handler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	        	//首次需要上下文
	        	System.out.println("########MyPreference.getInstance(getApplicationContext()).getIsLogined():"+MyPreference.getInstance(getApplicationContext()).getIsLogined());
	        	if(MyPreference.getInstance(getApplicationContext()).getIsLogined().equals("Yes")){
	        		if(MyPreference.getRememberPassword().equals("Yes")){
	        			System.out.println("#######MyPreference.getRememberWhich():"+MyPreference.getRememberWhich());
//	        			if(MyPreference.getRememberWhich().equals("1")){
//	            			Intent intent=new Intent(getApplicationContext(), PlantingMainActivity.class);
//	    	    			startActivity(intent);
//	    	    			finish();
//	    	    			overridePendingTransition(R.anim.fade, R.anim.hold);
////	    	    			handler = null;
//	            		}else if(MyPreference.getRememberWhich().equals("2")){
////	            			Intent intent=new Intent(getApplicationContext(), AnimalMainActivity.class);
//	            			Intent intent=new Intent(getApplicationContext(), AnimalHusbandryMainActivity.class);
//	    	    			startActivity(intent);
//	    	    			finish();
//	    	    			overridePendingTransition(R.anim.fade, R.anim.hold);
////	    	    			handler = null;
//	    	        	}else if(MyPreference.getRememberWhich().equals("3")){
//	    	        		Intent intent=new Intent(getApplicationContext(), AquacultureMainActivity.class);
//	    	    			startActivity(intent);
//	    	    			finish();
//	    	    			overridePendingTransition(R.anim.fade, R.anim.hold);
//	    	        	}else
	    	        		if(MyPreference.getRememberWhich().equals("5")){
	    	        		Intent intent=new Intent(getApplicationContext(), InputBusinessUnitMainActivity.class);
	    	    			startActivity(intent);
	    	    			finish();
	    	    			overridePendingTransition(R.anim.fade, R.anim.hold);
	    	        	}else if(MyPreference.getRememberWhich().equals("")){//开始安装的，或点记住密码，但是没点击登录按钮
	    	        		Intent intent=new Intent(getApplicationContext(), MainActivity.class);		
	    	    			startActivity(intent);
	    	    			finish();
	    	    			overridePendingTransition(R.anim.fade, R.anim.hold);
//	    	    			handler = null;
	    	        	}
	        		}else{
	        			Intent intent=new Intent(getApplicationContext(), MainActivity.class);		
		    			startActivity(intent);
		    			finish();
		    			overridePendingTransition(R.anim.fade, R.anim.hold);
//		    			handler = null;
	        		}
	        		
	        	}else{
	        		Intent intent=new Intent(getApplicationContext(), MainActivity.class);		
	    			startActivity(intent);
	    			finish();
	    			overridePendingTransition(R.anim.fade, R.anim.hold);
//	    			handler = null;
	        	}
	        }
	    };
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		    // 1.避免从桌面启动程序后，会重新实例化入口类的activity
			//2.如果启动页为SplashActivity，之后finish掉启动MainActivity，解决办法：将MainActivity的launchMode设置为“singleTask”
		    if (!this.isTaskRoot()) { // 判断当前activity是不是所在任务栈的根
		        Intent intent = getIntent();
		        if (intent != null) {
		            String action = intent.getAction();
		            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
		                finish();
		                return;
		            }
		        }
		    }
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.splash_activty);
			handler.sendEmptyMessageDelayed(1, 100);
		}
}
