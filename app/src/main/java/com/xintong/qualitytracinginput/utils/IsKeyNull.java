package com.xintong.qualitytracinginput.utils;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.bool;

//mybatis空值映射
public class IsKeyNull {
	public  String  get(JSONObject jsonObject,String key){
		String s;
		boolean b=true;
		try {
			s=jsonObject.getString(key);
		} catch (Exception e) {
			b=false;
			System.out.println("######"+key+" 抛出异常！");
			e.printStackTrace();
		}
		if(b==false){
			 s="";
			 System.out.println("#####返回结果s："+s);
			return s;
		}else{
			 s = "已有数据";
			try {
				s =jsonObject.getString(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			System.out.println("#####返回结果s："+s);
			return s;
		}
	}
}
