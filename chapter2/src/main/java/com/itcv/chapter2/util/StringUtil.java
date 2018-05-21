package com.itcv.chapter2.util;

import org.apache.commons.lang3.StringUtils;


/**
 * ×Ö·û´®¹¤¾ßÀà
 * @author zf
 *
 */
public class StringUtil {
     
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str != null){
			str = str.trim();
		}
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
}
