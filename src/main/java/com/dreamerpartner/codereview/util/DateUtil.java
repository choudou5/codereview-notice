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
}
