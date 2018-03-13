package com.my.util;

public class StringUtil {

	/**
	 * 字符串判空操作, 空返回TRUE, 非空返回false
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if(str==null){
			return true;
		}else{
			str=str.trim();
		}
		if(str.length()!= 0){
			return false;
		}else{
			return true;
		}
	}
}
