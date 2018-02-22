package com.xintong.qualitytracinginput.activitys.input.businessUnit.fragment.businessManagement.activitys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.xintong.qualitytracinginput.ConstNumbers;
import com.xintong.qualitytracinginput.entity.MyPreference;
import com.xintong.qualitytracinginput.utils.IsKeyNull;
import com.xintong.qualitytracinginput.utils.netAsk.Httpthread;
import com.xintong.qualitytracinginput.utils.netAsk.LoadDialogDao;
import com.xintong.qualitytracinginput.utils.view.ListViewForScrollView;

public class InputAjax {
	 public static interface PlantingAjaxInterface{
		 void getData(String[] ssName, String[] ssId);
		 void noData();
	 }
	 public static interface PlantingAjaxInterface4{
		 void getData(String[] ssName, String[] ssId, String[] ssName2, String[] ssName3);
		 void noData();
	 }
	 public static interface PlantingAjaxInterface5{
		 void getData(String[] ss, String[] ss1, String[] ss2, String[] ss3, String[] ss4, String[] ss5);
		 void noData();
	 }
	 public static interface PlantingAjaxInterfaceSearch{
		 void getData(String resp);
		 void noData();
	 }
	//获取基地
	public static void getBaseInfoList(final Context context,final PlantingAjaxInterface plantingAjaxInterface) {
		String url= ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/productionRecord/getPlantingBaseInfoList";
		LoadDialogDao lDialog=new LoadDialogDao(context, "请稍等..");
		RequestParams params=new RequestParams();
		params.addBodyParameter("userId", MyPreference.getUserId());
		System.out.println("####获取基地列表region:"+ MyPreference.getRegion());
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
					System.out.println("####获取基地列表resp："+resp);
					JSONObject jsonObject;
					JSONArray jsonArray;
					try {
						jsonObject = new JSONObject(resp);
						jsonArray=jsonObject.getJSONObject("a").getJSONArray("content");
						switch (jsonObject.getJSONObject("a").getInt("code")) {
							case ConstNumbers.ClientResult.result_ok:
								String[] ssName = new String[jsonArray.length()];
								String[] ssId = new String[jsonArray.length()];
								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject jsonObject2=jsonArray.getJSONObject(i);
									ssName[i]=jsonObject2.getString("basename");//文字
									ssId[i]=jsonObject2.getString("id");//字典值,注意是id 而不是baseid
								}
								plantingAjaxInterface.getData(ssName, ssId);
								break;
								
							default:
								break;
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 1000:
					Toast.makeText(context, "网络访问失败", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		}, params, lDialog);
		httpthread.httpthread(url);
	}
	//获取地块
	public static void getBlockInfoList(final Context context,String baseId,final PlantingAjaxInterface4 plantingAjaxInterface4) {
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/productionRecord/getPlantingBlocksInfoList";
		LoadDialogDao lDialog=new LoadDialogDao(context, "请稍等..");
		RequestParams params=new RequestParams();
		params.addBodyParameter("baseId", baseId);
		params.addBodyParameter("userId", MyPreference.getUserId());
		System.out.println("####获取地块列表传值baseId:"+baseId);
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
					System.out.println("####获取地块列表resp："+resp);
					JSONObject jsonObject;
					JSONArray jsonArray;
					try {
						jsonObject = new JSONObject(resp);
						jsonArray=jsonObject.getJSONArray("content");
						switch (jsonObject.getInt("code")) {
							case ConstNumbers.ClientResult.result_ok:
								String[] ssName = new String[jsonArray.length()];
								String[] ssId = new String[jsonArray.length()];
								String[] ssBlockLatitude = new String[jsonArray.length()];
								String[] ssBlockLongitude = new String[jsonArray.length()];
								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject jsonObject2=jsonArray.getJSONObject(i);
									ssName[i]=jsonObject2.getString("blockname");//文字
									ssId[i]=jsonObject2.getString("id");//字典值 注意是id 不是blockid
									
									ssBlockLatitude[i]=jsonObject2.getString("latitude");//字典值
									ssBlockLongitude[i]=jsonObject2.getString("longitude");//字典值
								}
								plantingAjaxInterface4.getData(ssName, ssId,ssBlockLatitude,ssBlockLongitude);
								break;
								
							default:
								break;
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 1000:
					Toast.makeText(context, "网络访问失败", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		}, params, lDialog);
		httpthread.httpthread(url);
	}	 
	//获取品种
	public static void getVarietyInfoList(final Context context,String blockId,final PlantingAjaxInterface5 plantingAjaxInterface5) {
		
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/productionRecord/getPlantingVarietiesInfoList";
		LoadDialogDao lDialog=new LoadDialogDao(context, "请稍等..");
		RequestParams params=new RequestParams();
		params.addBodyParameter("blockId", blockId);
		params.addBodyParameter("year", "");
		System.out.println("####获取品种信息列表传值blockId:"+blockId);
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
					System.out.println("####获取品种信息列表resp："+resp);
					JSONObject jsonObject;
					JSONArray jsonArray;
					try {
						jsonObject = new JSONObject(resp);
						jsonArray=jsonObject.getJSONArray("content");
						switch (jsonObject.getInt("code")) {
							case ConstNumbers.ClientResult.result_ok:
								String[] ssName = new String[jsonArray.length()];
								String[] ssProductsAndProductName = new String[jsonArray.length()];
								String[] ssId = new String[jsonArray.length()];
								String[] ssProductName = new String[jsonArray.length()];
								String[] ssProductId = new String[jsonArray.length()];
								String[] ssRegion = new String[jsonArray.length()];
								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject jsonObject2=jsonArray.getJSONObject(i);
									//将jsonObject2.getString("productcat")截断只取第一个 名
									String productsOnlyOneName=jsonObject2.getString("productsname").split("，")[0];
									System.out.println("#####productsOnlyOneName："+productsOnlyOneName);
									ssProductsAndProductName[i]=productsOnlyOneName+"/"+jsonObject2.getString("productname");//文字
									ssName[i]=jsonObject2.getString("productsname");//文字
									ssId[i]=jsonObject2.getString("productsid");//字典值
									ssProductId[i]=jsonObject2.getString("id");//品种id：
									ssProductName[i]=jsonObject2.getString("productname");//产品名称
									ssRegion[i]=jsonObject2.getString("region");//字典值
								}
								plantingAjaxInterface5.getData(ssProductsAndProductName,ssName, ssId,ssProductName,ssProductId,ssRegion);
								break;
								
							default:
								break;
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 1000:
					Toast.makeText(context, "网络访问失败", Toast.LENGTH_SHORT).show();
					break;

				default:
					break;
				}
			}
		}, params, lDialog);
		httpthread.httpthread(url);
		
	}	 
	
	//通用的字典管理
	//生产标准，
	public static void getGeneralDictionary(final String dicType,final Context context,final PlantingAjaxInterface plantingAjaxInterface) {
		//通用的地址
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/plantingAjax/productionRecord/getGeneralDictionary";
		RequestParams params=new RequestParams();
		params.addBodyParameter("dicType", dicType);
		System.out.println("####准备请求获取  通用字典："+dicType);
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
//							Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
					System.out.println("###获取 通用字典："+dicType+"返回resp："+resp);
					try {
						JSONObject respJson=new JSONObject(resp);
						int code=respJson.getInt("code");
						if(code==0){
							JSONArray jsonArray=respJson.getJSONArray("content");
							String[] ssName = new String[jsonArray.length()];
							String[] ssId = new String[jsonArray.length()];
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject=jsonArray.getJSONObject(i);
								ssName[i]=jsonObject.getString("name");//文字
								ssId[i]=jsonObject.getString("id");//字典值
							}
							plantingAjaxInterface.getData(ssName, ssId);
						}else{
							
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 1000:
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	//生产方式
	public static void getProductionMode(String productsId,final String select0AreaDicId,final String select1SpinnerDicId,final Context context,final PlantingAjaxInterface plantingAjaxInterface) {
		//通用的地址
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/plantingAjax/productionRecord/getProductionMode";
		RequestParams params=new RequestParams();
		params.addBodyParameter("industry", "2");//行业类型2.种植业
		params.addBodyParameter("productsId", productsId);
		params.addBodyParameter("select0AreaDicId", select0AreaDicId);//区划代码   产地
		params.addBodyParameter("select1SpinnerDicId", select1SpinnerDicId);//行业标准
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
//							Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
					System.out.println("###获取 生产方式返回resp："+resp);
					try {
						JSONObject respJson=new JSONObject(resp);
						int code=respJson.getInt("code");
						if(code==0){
							JSONArray jsonArray=respJson.getJSONArray("content");
							String[] ssName = new String[jsonArray.length()];
							String[] ssId = new String[jsonArray.length()];
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject=jsonArray.getJSONObject(i);
								ssName[i]=jsonObject.getString("name");//文字
								ssId[i]=jsonObject.getString("id");//字典值
							}
							plantingAjaxInterface.getData(ssName, ssId);
						}else{
							
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 1000:
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	//查询按钮点击
	public static void getSearch(String productsId,String select0AreaDicId,String select1SpinnerDicId,String select2SpinnerString,final Context context,final PlantingAjaxInterfaceSearch plantingAjaxInterfaceSearch) {
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/plantingAjax/productionRecord/getSearch";
		RequestParams params=new RequestParams();
		System.out.println("####准备请求获取  select1SpinnerDicId"+select1SpinnerDicId);
		System.out.println("####准备请求获取  select2SpinnerString"+select2SpinnerString);
		params.addBodyParameter("industry", "2");//行业，种植业
		params.addBodyParameter("productsId", productsId);
		params.addBodyParameter("select0AreaDicId", select0AreaDicId);//产地区划代码
		params.addBodyParameter("select1SpinnerDicId", select1SpinnerDicId);//生产标准
		params.addBodyParameter("select2SpinnerString", select2SpinnerString);//生产方式
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
//								Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
					System.out.println("###获取 查询返回resp："+resp);
					plantingAjaxInterfaceSearch.getData(resp);
					break;
				case 1000:
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	
	//查询记忆的生产规程-渔业
	public static void getFishMemoryRules(String productId,final Context context,final PlantingAjaxInterfaceSearch plantingAjaxInterfaceSearch) {
		String url=ConstNumbers.Urls.ip_address+"/login/app/app2/planting/productionArchives/plantingAjax/productionRecord/getFishsMemoryRules";
		RequestParams params=new RequestParams();
		params.addBodyParameter("productId", productId);
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
					System.out.println("###获取 查询返回resp："+resp);
					plantingAjaxInterfaceSearch.getData(resp);
					break;
				case 1000:
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	
	//查询记忆的生产规程
	public static void getMemoryRules(String productId,final Context context,final PlantingAjaxInterfaceSearch plantingAjaxInterfaceSearch) {
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/plantingAjax/productionRecord/getMemoryRules";
		RequestParams params=new RequestParams();
		params.addBodyParameter("productId", productId);
		params.addBodyParameter("userId", MyPreference.getUserId());
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
//									Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
					System.out.println("###获取 查询返回resp："+resp);
					plantingAjaxInterfaceSearch.getData(resp);
					break;
				case 1000:
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	
	
//生产档案的-----------------------------------------------------------	 
	 //A.
	//二级联动
	 //获取第一级  管理环节
	public static void getManagelinkSpinner(String standardized_productionId,final Context context,final PlantingAjaxInterface plantingAjaxInterface) {
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/plantingAjax/productionRecord/getManagelinkSpinner";
		RequestParams params=new RequestParams();
		params.addBodyParameter("standardized_productionId", standardized_productionId);
		System.out.println("####准备请求获取第一级  管理环节");
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
					case 1:
						String resp=msg.getData().getString("result");
//							Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
						System.out.println("###获取管理环节返回resp："+resp);
						try {
							JSONObject respJson=new JSONObject(resp);
							int code=respJson.getInt("code");
							if(code==0){
								JSONArray jsonArray=respJson.getJSONArray("content");
								String[] ssName = new String[jsonArray.length()];
								String[] ssId = new String[jsonArray.length()];
								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject jsonObject=jsonArray.getJSONObject(i);
									ssName[i]=jsonObject.getString("name");//文字
									ssId[i]=new IsKeyNull().get(jsonObject, "id");//字典值,这个字段没有返回
								}
								plantingAjaxInterface.getData(ssName, ssId);
							}else{
								
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						break;
					case 1000:
						break;
					default:
						break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	//获取第二级  管理措施
	public static void getManagemeasureSpinner(String standardized_productionId,final Context context,String managelinkString,final PlantingAjaxInterface plantingAjaxInterface) {
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/plantingAjax/productionRecord/getManagemeasureSpinner";
		RequestParams params=new RequestParams();
		params.addBodyParameter("managelinkString", managelinkString);
		params.addBodyParameter("standardized_productionId", standardized_productionId);
		System.out.println("####准备请求获取第二级  管理措施");
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
//							Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
					System.out.println("###获取管理措施返回resp："+resp);
					try {
						JSONObject respJson=new JSONObject(resp);
						int code=respJson.getInt("code");
						if(code==0){
							JSONArray jsonArray=respJson.getJSONArray("content");
							String[] ssName = new String[jsonArray.length()];
							String[] ssId = new String[jsonArray.length()];
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject=jsonArray.getJSONObject(i);
								ssName[i]=jsonObject.getString("name");//文字
								ssId[i]=jsonObject.getString("id");//字典值
							}
							plantingAjaxInterface.getData(ssName, ssId);
						}else{
							
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 1000:
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	//获取  管理活动
	public static void getJobitemSpinner(final Context context,final PlantingAjaxInterface plantingAjaxInterface) {
		String url=ConstNumbers.Urls.ip_address+"login/app/appClient2/planting/productionArchives/plantingAjax/productionRecord/getJobitemSpinner";
		RequestParams params=new RequestParams();
		System.out.println("####准备请求获取  管理活动");
		Httpthread httpthread=new Httpthread(context, new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				case 1:
					String resp=msg.getData().getString("result");
//							Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
					System.out.println("###获取管理活动返回resp："+resp);
					try {
						JSONObject respJson=new JSONObject(resp);
						int code=respJson.getInt("code");
						if(code==0){
							JSONArray jsonArray=respJson.getJSONArray("content");
							String[] ssName = new String[jsonArray.length()];
							String[] ssId = new String[jsonArray.length()];
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject jsonObject=jsonArray.getJSONObject(i);
								ssName[i]=jsonObject.getString("name");//文字
								ssId[i]=jsonObject.getString("id");//字典值
							}
							plantingAjaxInterface.getData(ssName, ssId);
						}else{
							
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
					break;
				case 1000:
					break;
				default:
					break;
				}
			}
		}, params, null);
		httpthread.httpthread(url);
	}
	//B.   C.
	//获取 农药通用品种id   只是请求出全部数据
	public static void getVarietyAutoComplete(final Context context, EditText varietyname, final ListViewForScrollView autoListView, final PlantingAjaxInterface plantingAjaxInterface) {
		
		varietyname.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				autoListView.setVisibility(View.VISIBLE);
				System.out.println("####@1  s:"+s+"  start:"+start+"  count:"+count);
				String url=ConstNumbers.Urls.ip_address+"/login/app/app2/planting/productionArchives/plantingAjax/pesticidesBuyRecord/getVarietyAutoComplete";//B C共用了
				RequestParams params=new RequestParams();
				params.addBodyParameter("productname", s+"");
				System.out.println("####准备请求获取  管理活动");
				Httpthread httpthread=new Httpthread(context, new Handler(){
					public void handleMessage(Message msg){
						switch (msg.what) {
						case 1:
							String resp=msg.getData().getString("result");
//									Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
							System.out.println("###获取农药通用品种返回resp："+resp);
							try {
								JSONObject respJson=new JSONObject(resp);
								int code=respJson.getInt("code");
								if(code==0){
									JSONArray jsonArray=respJson.getJSONArray("content");
									String[] ssName = new String[jsonArray.length()];
									String[] ssId = new String[jsonArray.length()];
									for (int i = 0; i < jsonArray.length(); i++) {
										JSONObject jsonObject=jsonArray.getJSONObject(i);
										ssName[i]=jsonObject.getString("name");//文字
										ssId[i]=jsonObject.getString("id");//字典值
									}
									plantingAjaxInterface.getData(ssName, ssId);
								}else{
									
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
							break;
						case 1000:
							break;
						default:
							break;
						}
					}
				}, params, null);
				httpthread.httpthread(url);
				
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				System.out.println("####@2  s:"+s+"  start:"+start+"  count:"+count+" after");
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				System.out.println("####@3  s:"+s);
			}
		});
		
		
	}
	//生产方式
		public static void getProductionModeFish(String productId,final String select0DicId,final String select1SpinnerDicId,final Context context,final PlantingAjaxInterface plantingAjaxInterface) {
			//通用的地址
			String url=ConstNumbers.Urls.ip_address+"/login/app/app2/planting/productionArchives/plantingAjax/productionRecord/getProductionMode";
			RequestParams params=new RequestParams();
			params.addBodyParameter("industry", "4");//行业类型2.种植业
			params.addBodyParameter("productId", productId);//行业类型2.种植业
			params.addBodyParameter("select0DicId", select0DicId);//区划代码
			params.addBodyParameter("select1SpinnerDicId", select1SpinnerDicId);//行业标准
			Httpthread httpthread=new Httpthread(context, new Handler(){
				public void handleMessage(Message msg){
					switch (msg.what) {
					case 1:
						String resp=msg.getData().getString("result");
//								Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
						System.out.println("###获取 生产方式返回resp："+resp);
						try {
							JSONObject respJson=new JSONObject(resp);
							int code=respJson.getInt("code");
							if(code==0){
								JSONArray jsonArray=respJson.getJSONArray("content");
								String[] ssName = new String[jsonArray.length()];
								String[] ssId = new String[jsonArray.length()];
								for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject jsonObject=jsonArray.getJSONObject(i);
									ssName[i]=jsonObject.getString("name");//文字
									ssId[i]=jsonObject.getString("id");//字典值
								}
								plantingAjaxInterface.getData(ssName, ssId);
							}else{
								
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
						break;
					case 1000:
						break;
					default:
						break;
					}
				}
			}, params, null);
			httpthread.httpthread(url);
		}
		//查询按钮点击
		public static void getSearchFish(String productId,String select0DicId,String select1SpinnerDicId,String select2SpinnerString,final Context context,final PlantingAjaxInterfaceSearch plantingAjaxInterfaceSearch) {
			String url=ConstNumbers.Urls.ip_address+"/login/app/app2/planting/productionArchives/plantingAjax/productionRecord/getSearch";
			RequestParams params=new RequestParams();
			System.out.println("####准备请求获取  select0DicId"+select0DicId);
			System.out.println("####准备请求获取  select1SpinnerDicId"+select1SpinnerDicId);
			System.out.println("####准备请求获取  select2SpinnerString"+select2SpinnerString);
			params.addBodyParameter("industry", "4");//行业，种植业
			params.addBodyParameter("productId", productId);
			params.addBodyParameter("select0DicId", select0DicId);//产地区划代码
			params.addBodyParameter("select1SpinnerDicId", select1SpinnerDicId);//生产标准
			params.addBodyParameter("select2SpinnerString", select2SpinnerString);//生产方式
			Httpthread httpthread=new Httpthread(context, new Handler(){
				public void handleMessage(Message msg){
					switch (msg.what) {
					case 1:
						String resp=msg.getData().getString("result");
//									Toast.makeText(context, "###获取分类返回resp："+resp, Toast.LENGTH_SHORT).show();
						System.out.println("###获取 查询返回resp："+resp);
						plantingAjaxInterfaceSearch.getData(resp);
						break;
					case 1000:
						break;
					default:
						break;
					}
				}
			}, params, null);
			httpthread.httpthread(url);
		}
}
