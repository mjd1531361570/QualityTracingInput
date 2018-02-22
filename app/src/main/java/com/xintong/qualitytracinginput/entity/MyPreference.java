package com.xintong.qualitytracinginput.entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
public class MyPreference {
	private static MyPreference preference = null;
	private static SharedPreferences sharedPreference;
	private String packageName = "";
	//系统字段
	private static final String user_id = "userId";//服务对象id
	private static final String user_name = "userName";//服务对象名字
	private static final String user_acount = "userAcount";//用户账号
	private static final String user_password = "userPassword";//用户密码
	private static final String cookie_ = "cookie";
	private static final String remember_password = "rememberPassword";//checkbox 记住密码 Yes，No  密码登陆后记住的，这里只是一种显示标标记
	private static final String remember_which = "rememberWhich";//记住密码后还要记住是 1种植业 2畜牧业 3渔业  4.投入品生产单位 5.投入品经营单位
	private static final String is_logined = "isLogined";//判断是否已经登录过了 Yes为已经登录过了    No用于退出登录等
	private static final String last_login_role = "lastLoginRole";//用戶最后后登陆的角色id
	private static final String industries_ = "industries";//用戶的行业 1综合 admin 2.种植业 3.畜牧业 4.渔业 用英文逗号分割了如：2,3
	private static final String industry_ = "industry";//用戶的以什么行业登录 1综合 admin 2.种植业 3.畜牧业 4.渔业  只有一个值
	private static final String unit_ = "unit";//用戶工作单位id
	private static final String unit_name = "unitName";//用戶工作单位名称
	private static final String user_type = "userType";//用户类型 0-监管人员 1-服务对象
	private static final String region_ = "region";//行政区划代码 如：130110107209
	
	//种植业**
	private static final String is_first_chose_planting = "isFirstChosePlanting";//是否首次选择对话框,种植业的
	private static final String planting_base_id = "plantingBaseId";//种植业基地id
	private static final String planting_base_name = "plantingBaseName";//种植业基地
	private static final String planting_block_id = "plantingBlockId";//种植业地块id
	private static final String planting_block_name = "plantingBlockName";//种植业地块
	private static final String planting_block_latitude = "plantingBlockLatitude";//种植业地块
	private static final String planting_block_longitude = "plantingBlockLongitude";//种植业地块
	private static final String planting_products_id = "plantingProductsId";//农产品id
	private static final String planting_products_name = "plantingProductsName";//农产品
	private static final String planting_product_id = "plantingProductId";//品种id 就是产品id
	private static final String planting_product_name = "plantingProductName";//产品名称
	private static final String planting_product_region = "plantingProductRegion";//

	//种植业end

	//畜牧业**
		/////////////////////////
	
	//畜牧业end
	
	//水产业**
	private static final String baseid = "baseid";//基地id
	private static final String ponlid = "ponlid";//池塘id
	private static final String productid = "productid";//产品id
	private static final String productids = "productids";//产品id
	private static final String productNames = "productNames";//产品id
	private static final String isFirstException = "isFirstExceptions";//0:继续执行；1：不执行
	private static final String fishBaseName = "fishBaseName";
	private static final String fishPoolName = "fishPoolName";

	
	//种植业end
	
	public static synchronized MyPreference getInstance(Context context) {
		if (preference == null)
			preference = new MyPreference(context);
		return preference;
	}
	public MyPreference(Context context) {
		packageName = context.getPackageName() + "_preferences";
		sharedPreference = context.getSharedPreferences(packageName,
				context.MODE_PRIVATE);
	}
	//系统的
	public void setUserId(String userId){
		Editor editor=sharedPreference.edit();
		editor.putString(user_id,userId);
		editor.commit();
	}
	public static String getUserId() {
		return sharedPreference.getString(user_id, "");
	}
	public void setUserName(String userName){
		Editor editor=sharedPreference.edit();
		editor.putString(user_name,userName);
		editor.commit();
	}
	public static String getUserName() {
		return sharedPreference.getString(user_name, "");
	}
	public void setUserAcount(String userAcount){
		Editor editor=sharedPreference.edit();
		editor.putString(user_acount,userAcount);
		editor.commit();
	}
	public static String getUserAcount() {
		return sharedPreference.getString(user_acount, "");
	}
	public void setUserPassword(String userPassword){
		Editor editor=sharedPreference.edit();
		editor.putString(user_password,userPassword);
		editor.commit();
	}
	public static String getUserPassword() {
		return sharedPreference.getString(user_password, "");
	}
	public void setCookie(String cookie){
		Editor editor=sharedPreference.edit();
		editor.putString(cookie_,cookie);
		editor.commit();
	}
	public static String getCookie() {
		return sharedPreference.getString(cookie_, "");
	}
	public void setRememberPassword(String rememberPassword){
		Editor editor=sharedPreference.edit();
		editor.putString(remember_password,rememberPassword);
		editor.commit();
	}
	public static String getRememberPassword() {
		return sharedPreference.getString(remember_password, "");
	}
	public void setRememberWhich(String rememberWhich){
		Editor editor=sharedPreference.edit();
		editor.putString(remember_which,rememberWhich);
		editor.commit();
	}
	public static String getRememberWhich() {
		return sharedPreference.getString(remember_which, "");
	}
	public void setIsLogined(String isLogined){
		Editor editor=sharedPreference.edit();
		editor.putString(is_logined,isLogined);
		editor.commit();
	}
	public static String getIsLogined() {
		return sharedPreference.getString(is_logined, "");
	}
	public void setLastLoginRole(String lastLoginRole){
		Editor editor=sharedPreference.edit();
		editor.putString(last_login_role,lastLoginRole);
		editor.commit();
	}
	public static String getLastLoginRole() {
		return sharedPreference.getString(last_login_role, "");
	}
	public void setIndustries(String industries){
		Editor editor=sharedPreference.edit();
		editor.putString(industries_,industries);
		editor.commit();
	}
	public static String getIndustries() {
		return sharedPreference.getString(industries_, "");
	}
	public void setIndustry(String industry){
		Editor editor=sharedPreference.edit();
		editor.putString(industry_,industry);
		editor.commit();
	}
	public static String getIndustry() {
		return sharedPreference.getString(industry_, "");
	}
	public void setUnit(String unit){
		Editor editor=sharedPreference.edit();
		editor.putString(unit_,unit);
		editor.commit();
	}
	public static String getUnit() {
		return sharedPreference.getString(unit_, "");
	}
	public void setUnitName(String unitName){
		Editor editor=sharedPreference.edit();
		editor.putString(unit_name,unitName);
		editor.commit();
	}
	public static String getUnitName() {
		return sharedPreference.getString(unit_name, "");
	}
	public void setUserType(String userType){
		Editor editor=sharedPreference.edit();
		editor.putString(user_type,userType);
		editor.commit();
	}
	public static String getUserType() {
		return sharedPreference.getString(user_type, "");
	}
	public void setRegion(String region){
		Editor editor=sharedPreference.edit();
		editor.putString(region_,region);
		editor.commit();
	}
	public static String getRegion() {
		return sharedPreference.getString(region_, "");
	}
	
	
	//种植业
	public void setIsFirstChosePlanting(boolean isFirstChosePlanting){
		Editor editor=sharedPreference.edit();
		editor.putBoolean(is_first_chose_planting,isFirstChosePlanting);
		editor.commit();
	}
	public static boolean getIsFirstChosePlanting() {
		return sharedPreference.getBoolean(is_first_chose_planting, true);
	}
	public void setPlantingBaseId(String palntingBaseId){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_base_id,palntingBaseId);
		editor.commit();
	}
	public static String getPlantingBaseId() {
		return sharedPreference.getString(planting_base_id, "");
	}
	public void setPlantingBaseName(String palntingBaseName){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_base_name,palntingBaseName);
		editor.commit();
	}
	public static String getPlantingBaseName() {
		return sharedPreference.getString(planting_base_name, "");
	}
	
	public void setPlantingBlockId(String palntingBaseName){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_block_id,palntingBaseName);
		editor.commit();
	}
	public static String getPlantingBlockId() {
		return sharedPreference.getString(planting_block_id, "");
	}
	public void setPlantingBlockName(String palntingBaseName){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_block_name,palntingBaseName);
		editor.commit();
	}
	public static String getPlantingBlockName() {
		return sharedPreference.getString(planting_block_name, "");
	}

	public void setPlantingBlockLatitude(String plantingBlockLatitude){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_block_latitude,plantingBlockLatitude);
		editor.commit();
	}
	public static String getPlantingBlockLatitude() {
		return sharedPreference.getString(planting_block_latitude, "");
	}
	
	public void setPlantingBlockLongitude(String plantingBlockLongitude){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_block_longitude,plantingBlockLongitude);
		editor.commit();
	}
	public static String getPlantingBlockLongitude() {
		return sharedPreference.getString(planting_block_longitude, "");
	}
	
	public void setPlantingProductsId(String palntingProductId){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_products_id,palntingProductId);
		editor.commit();
	}
	public static String getPlantingProductsId() {
		return sharedPreference.getString(planting_products_id, "");
	}
	public void setPlantingProductsName(String palntingProductsName){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_products_name,palntingProductsName);
		editor.commit();
	}
	public static String getPlantingProductsName() {
		return sharedPreference.getString(planting_products_name, "");
	}
	
	public void setPlantingProductId(String palntingProductId){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_product_id,palntingProductId);
		editor.commit();
	}
	public static String getPlantingProductId() {
		return sharedPreference.getString(planting_product_id, "");
	}
	public void setPlantingProductName(String palntingProductName){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_product_name,palntingProductName);
		editor.commit();
	}
	public static String getPlantingProductName() {
		return sharedPreference.getString(planting_product_name, "");
	}
	public void setPlantingProductRegion(String palntingProductRegion){
		Editor editor=sharedPreference.edit();
		editor.putString(planting_product_region,palntingProductRegion);
		editor.commit();
	}
	public static String getPlantingProductRegion() {
		return sharedPreference.getString(planting_product_region, "");
	}
	//种植业end
	
	
	//畜牧业
	
	//畜牧业end
	
	//水产业
	public void setBaseid(String baseids){
		Editor editor=sharedPreference.edit();
		editor.putString(baseid,baseids);
		editor.commit();
	}
	public static String getBaseid() {
		return sharedPreference.getString(baseid, "");
	}
	public void setPonlid(String ponlids){
		Editor editor=sharedPreference.edit();
		editor.putString(ponlid,ponlids);
		editor.commit();
	}
	public static String getPonlid() {
		return sharedPreference.getString(ponlid, "");
	}
	public void setProductid(String productids){
		Editor editor=sharedPreference.edit();
		editor.putString(productid,productids);
		editor.commit();
	}
	public static String getProductid() {
		return sharedPreference.getString(productid, "");
	}
	public void setProductids(String productid){
		Editor editor=sharedPreference.edit();
		editor.putString(productids,productid);
		editor.commit();
	}
	public static String getProductids() {
		return sharedPreference.getString(productids, "");
	}
	public void setProductNames(String productNamess){
		Editor editor=sharedPreference.edit();
		editor.putString(productNames,productNamess);
		editor.commit();
	}
	public static String getProductNames() {
		return sharedPreference.getString(productNames, "");
	}
	public void setIsFirstException(String isFirstExceptions){
		Editor editor=sharedPreference.edit();
		editor.putString(isFirstException,isFirstExceptions);
		editor.commit();
	}
	public static String getIsFirstException() {
		return sharedPreference.getString(isFirstException, "");
	}
	public void setFishBaseName(String baseName){
		Editor editor=sharedPreference.edit();
		editor.putString(fishBaseName,baseName);
		editor.commit();
	}
	public static String getFishBaseName() {
		return sharedPreference.getString(fishBaseName, "");
	}
	public void setFishPoolName(String poolName){
		Editor editor=sharedPreference.edit();
		editor.putString(fishPoolName,poolName);
		editor.commit();
	}
	public static String getFishPoolName() {
		return sharedPreference.getString(fishPoolName, "");
	}

	//水产业end
		
}
