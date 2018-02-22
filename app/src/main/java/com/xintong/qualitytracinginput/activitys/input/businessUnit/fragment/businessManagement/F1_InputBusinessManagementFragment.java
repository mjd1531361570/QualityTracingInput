package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement;

import java.util.Timer;
import java.util.TimerTask;

import com.xintong.qualitytracinginput.R;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.A.aa.AA_SupplierBasicInformationActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.A.ab.AB_CommodityBasicInfoActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.ba.BA_PurchasingAndWarehousingActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.bb.BB_ArrearsToBePaidActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.bc.BC_AddPurchaseOfReturnFormActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.B.bd.BD_ArrearsToBeCollectedActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.C.C_InventoryInventoryActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.D.da.DA_SellingOutOfTheTreasuryActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.D.db.DB_ArrearsToBePaidActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.D.dc.DC_AddSalesReturnFormActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.D.dc.DC_SalesReturnActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.D.dd.DD_ArrearsToBeRefundedActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.E.ea.eaa.EAA_CommodityPurchaseQueryActivity;
import com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys.E.ea.eab.EAB_SupplierPurchaseQueryActivity;
import com.xintong.qualitytracinginput.utils.GPSUtil;
import com.xintong.qualitytracinginput.utils.netAsk.NetConnectUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//1.第一个选项卡内容，投入品经营单位
public class F1_InputBusinessManagementFragment extends Fragment implements OnClickListener{
	TextView label;
	private final static int scannin_grequest_code = 1;
	String url;//访问地址
	Context context;
	View view;
	AlertDialog globalAlertDialog;
	LinearLayout ll_select;
	public F1_InputBusinessManagementFragment() {
		super();
	}
	@SuppressLint("ValidFragment")
	public F1_InputBusinessManagementFragment(Context context) {
		this();
		this.context = context;
	}
	@Override
	public void onResume() {
		super.onResume();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.input_business_management_fragment, container, false);
		isHaveNetConnected();
//		initGetBase();
//		initDialogBlocks();
		findView();
		initView();
		return view;
	}
	private void isHaveNetConnected() {
		if(!NetConnectUtils.checkNetConnection(context)){
			Toast.makeText(context, "请打开网络,3s后自动退出！", Toast.LENGTH_SHORT).show();
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					((Activity)context).finish();
				}
			}, 3500);
		};
		return;
	}

	private void findView() {
		view.findViewById(R.id.pic1).setOnClickListener(this);
		view.findViewById(R.id.pic2).setOnClickListener(this);
		view.findViewById(R.id.pic3).setOnClickListener(this);
		view.findViewById(R.id.pic4).setOnClickListener(this);
		view.findViewById(R.id.pic5).setOnClickListener(this);
		view.findViewById(R.id.pic6).setOnClickListener(this);
		view.findViewById(R.id.pic7).setOnClickListener(this);
		view.findViewById(R.id.pic8).setOnClickListener(this);
		view.findViewById(R.id.pic9).setOnClickListener(this);
		view.findViewById(R.id.pic10).setOnClickListener(this);
		view.findViewById(R.id.pic11).setOnClickListener(this);
	}
	private void initView() {
		GPSUtil.isGotoOpenGPS(context);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pic1://供应商信息
			Intent intent=new Intent(context, AA_SupplierBasicInformationActivity.class);
			startActivity(intent);
			break;
		case R.id.pic2://商品基本信息
			Intent intent2=new Intent(context, AB_CommodityBasicInfoActivity.class);
			startActivity(intent2);
			break;
		case R.id.pic3://采购入库
			Intent intent3=new Intent(context, BA_PurchasingAndWarehousingActivity.class);
			startActivity(intent3);
			break;
		case R.id.pic4://欠款待付
			Intent intent4=new Intent(context, BB_ArrearsToBePaidActivity.class);
			startActivity(intent4);
			break;
		case R.id.pic5://采购退货
//			Intent intent5=new Intent(context, .class);
//			startActivity(intent5);
			break;
		case R.id.pic6://欠款待收
			Intent intent6=new Intent(context, BD_ArrearsToBeCollectedActivity.class);
			startActivity(intent6);
			break;
		case R.id.pic7://库存盘点
			Intent intent7=new Intent(context, C_InventoryInventoryActivity.class);
			startActivity(intent7);
			break;
		case R.id.pic8://销售出库
			Intent intent8=new Intent(context, DA_SellingOutOfTheTreasuryActivity.class);
			startActivity(intent8);
			break;
		case R.id.pic9://欠款待付
			Intent intent9=new Intent(context, DB_ArrearsToBePaidActivity.class);
			startActivity(intent9);
			break;
		case R.id.pic10://销售退货
			Intent intent10=new Intent(context, DC_SalesReturnActivity.class);
			startActivity(intent10);
			break;
		case R.id.pic11://
			Intent intent11=new Intent(context, DD_ArrearsToBeRefundedActivity.class);//入口
			startActivity(intent11);
			break;

		default:
			break;
		}
		
	}


}
