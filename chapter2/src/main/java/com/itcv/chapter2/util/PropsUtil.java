package com.itcv.chapter2.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsUtil {

	private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);
	
	/**
	 * ���������ļ�
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName){
		Properties props = null;
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if(is  == null){
				throw new FileNotFoundException(fileName + "file is not found");
			}
			props = new Properties();
			props.load(is);
		} catch (IOException e) {
			logger.error("load properties file failure",e);
		} finally{
			if (is !=null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error("close input stream failure",e);
				}
			}
		}
		
		return props;
	} 
	
	/**
	 * ��ȡ�ַ�������(Ĭ��ֵΪ���ַ���)
	 * @param props
	 * @param key
	 * @return
	 */
	public static String getString(Properties props, String key){
		return getString(props,key,"");
	}
	
	/**
	 * ��ȡ�ַ������ԣ���ָ��Ĭ��ֵ��
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(Properties props,String key,String defaultValue){
		String value = defaultValue;
		if(props.containsKey(key)){
			value = props.getProperty(key);
		}
		return value;
	}
	
	/**
	 * ��ȡ��ֵ�����ԣ�Ĭ��ֵΪ0��
	 * @param props
	 * @param key
	 */
	public static int getInt(Properties props,String key){
		return getInt(props,key,0);
	}
	
	/**
	 * ��ȡ��ֵ������(��ָ��Ĭ��ֵ)
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(Properties props,String key,int defaultValue){
		int value = defaultValue;
		if(props.containsKey(key)){
			value = CastUtil.castInt(props.getProperty(key));
		}
		return value;
	}
	
	/**
	 * ��ȡ���������ԣ�Ĭ��ֵfalse��
	 * @param props
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties props,String key){
		return getBoolean(props,key,false);
	}
	
	/**
	 * ��ȡ���������ԣ���ָ��Ĭ��ֵ��
	 * @param props
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBoolean(Properties props,String key,boolean defaultValue){
		boolean value = defaultValue;
		if(props.containsKey(key)){
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}
	
}
