package com.itcv.chapter2.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

/**
 * ���Ϲ�����
 * @author zf
 *
 */
public class CollectionUtil {
 
	/**
	 * �ж�Collection�Ƿ�Ϊ��
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection){
		return CollectionUtils.isEmpty(collection);
	}
	
	/**
	 * �ж�Collection�Ƿ�Ϊ��
	 * @param collection
	 * @return
	 */
	public static boolean isNotEmpty(Collection<?> collection){
		return  !isEmpty(collection);
	}
	
	/**
	 * �ж�Map�Ƿ�Ϊ��
	 * @param map
	 */
	public static boolean isEmpty(Map<?,?> map){
		return MapUtils.isEmpty(map);
	}

	/**
	 * �ж�Map�Ƿ�Ϊ��
	 * @param map
	 */
	public static boolean isNotEmpty(Map<?,?> map){
		return !isEmpty(map);
	}
}
