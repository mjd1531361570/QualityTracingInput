package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.entity.MyPreference;

public class F2_InputBusinessFragment extends Fragment implements OnClickListener{
	Context context;
	View view;
	public F2_InputBusinessFragment() {
		super();
	}
	@SuppressLint("ValidFragment")
	public F2_InputBusinessFragment(Context context) {
		this();
		this.context = context; 
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//三个共享了
		view=inflater.inflate(R.layout.planting_integrated_query_fragment, container, false);
		findView();
		initView();
		return view;
	}

	private void findView() {

	}

	private void initView() {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pic1://
//			Intent intent=new Intent(context,);
			break;
//		case R.id.pic2://
//			Intent intent=new Intent(context,);
//			break;
//		case R.id.pic3://
//			Intent intent=new Intent(context,);
//			break;
//		case R.id.pic4://
//			Intent intent=new Intent(context,);
//			break;
//		case R.id.pic5://
//			Intent intent=new Intent(context,);
//			break;
//		case R.id.pic6://
//			Intent intent=new Intent(context,);
//			break;
//		case R.id.pic7://
//			Intent intent=new Intent(context,);
//			break;
//		case R.id.pic8://
//			Intent intent=new Intent(context,);
//			break;
//		case R.id.pic9://
//			Intent intent=new Intent(context,);
//			break;
		default:
			break;
		}
		
	}

}
