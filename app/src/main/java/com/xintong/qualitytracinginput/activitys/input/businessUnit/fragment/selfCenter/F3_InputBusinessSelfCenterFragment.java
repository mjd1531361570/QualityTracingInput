package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.selfCenter;

import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.selfCenter.suggestionFeedBack.F3_SuggestionFeedBackActivity;
import com.xintong.qualitytracinginput.entity.MyPreference;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class F3_InputBusinessSelfCenterFragment extends Fragment implements OnClickListener{
	Context context;
	public F3_InputBusinessSelfCenterFragment() {
		super();
	}
	@SuppressLint("ValidFragment")
	public F3_InputBusinessSelfCenterFragment(Context context) {
		this();
		this.context = context;

	}
	View view;
	private TextView userName;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.planting_selfcenter_fragment, container, false);
		findView();
		initView();
		return view;
	}
	private void findView() {
		view.findViewById(R.id.exit_login_ll).setOnClickListener(this);
		view.findViewById(R.id.settings_ll).setOnClickListener(this);
		view.findViewById(R.id.other_matter_ll).setOnClickListener(this);
		view.findViewById(R.id.complain_ll).setOnClickListener(this);
		view.findViewById(R.id.version_update_ll).setOnClickListener(this);
		view.findViewById(R.id.proposal_ll).setOnClickListener(this);
		userName = (TextView) view.findViewById(R.id.username);
	}
	private void initView() {
		userName.setText(MyPreference.getInstance(context).getUserName());
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.exit_login_ll://退出登录
				MyPreference.getInstance(context).setRememberWhich("");
				System.exit(0);
				break;
			case R.id.settings_ll:
				break;
			case R.id.other_matter_ll:
				break;
			case R.id.complain_ll:
				break;
			case R.id.version_update_ll:
				break;
			case R.id.proposal_ll://意见反馈
				Intent intent=new Intent(context, F3_SuggestionFeedBackActivity.class);
				startActivity(intent);
				break;
			default:
				break;
		}
		
	}
}
