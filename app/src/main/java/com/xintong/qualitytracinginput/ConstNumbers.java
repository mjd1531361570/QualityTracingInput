
package com.xintong.qualitytracinginput;


import android.os.Environment;
public class ConstNumbers {
	public static class Urls{
		
//		public static final String ip_address="http://jg.hebny.cn:80/";//域名
//		public static final String ip_address="http://218.12.43.40:8080/";
		public static final String ip_address="http://192.168.1.213:8080/agripro/";
//		public static final String scan_code_login_ip_address="http://jg.hebny.cn:80/";		
//		public static final String scan_code_login_ip_address="http://218.12.43.40:8080/";		
		public static final String scan_code_login_ip_address="http://192.168.1.213:8080/agripro/";
		
	}
	public static class ClientResult{
		public static final int result_ok=0;//请求成功有数据
		public static final int result_ok_no_value=101;//请求成功没有数据
		public static final int result_wrong=201;//请求错误
	}
	public static class Dir {
	}

}