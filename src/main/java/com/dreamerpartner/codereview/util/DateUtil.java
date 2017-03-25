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
	 * 转换成 日期
	 * @param dateStr
	 */
	public static Date toDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return sdf.parse(dateStr);
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
	 * @return yyyyMMddHHmmssSSS
	 */
	public static String getTimeFullStr(){
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
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
	 * 获得日期 字符串
	 * @return MM月dd HH:mm
	 */
	@SuppressWarnings("deprecation")
	public static String parseZHDateStr(String dateStr){
		SimpleDateFormat sdf = null;
		try {
			Date date = toDate(dateStr);
			if(date.getYear() == new Date().getYear()){
				sdf = new SimpleDateFormat("MM月dd HH:mm");
			}else{
				sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm");
			}
			return sdf.format(date);
		} catch (Exception e) {
			return dateStr;
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
