package com.dreamerpartner.codereview.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author xuhaowen
 * @date 2017年3月19日
 */
public class DateUtil {

	/**
	 * 转换成 字符串
	 * @param date
	 * @return yyyy MM-dd
	 */
	public static String toString(Date date){
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy MM-dd");
		try {
			return dateTimeFormatter.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得日期 字符串
	 * @return yyyyMMdd
	 */
	public static String getDateShortStr(){
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyyMMdd");
		try {
			return dateTimeFormatter.format(new Date());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得日期 字符串
	 * @return yyyyMMddHHmmss
	 */
	public static String getTimeStr(){
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return dateTimeFormatter.format(new Date());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得日期 字符串
	 * @return yyyy-MM-dd HH:mm
	 */
	public static String getDateStr(){
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return dateTimeFormatter.format(new Date());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获得日期
	 * @return yyyyMMddHHmmss
	 */
	public static long getTimeLong(){
		return Long.parseLong(getTimeStr());
	}
}
