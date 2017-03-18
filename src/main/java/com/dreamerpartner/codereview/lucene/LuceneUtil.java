package com.dreamerpartner.codereview.lucene;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.dreamerpartner.codereview.util.PropertiesUtil;

/**
 * Lucene 工具类
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class LuceneUtil {

	public static String PATH;
	private static String INDEX_PATH;
	
	/**
	 * 初始化  索引目录
	 * @throws ServletException
	 */
	public static void initIndexDir() throws ServletException{
		PATH = PropertiesUtil.getString("data.dir");
		if(StringUtils.isBlank(PATH))
			throw new ServletException("请初始化 system.properties 里的 data.dir 参数。");
		
		INDEX_PATH = PATH+"/index";
		File file = new File(INDEX_PATH);
		try {
			if(!file.exists()) file.mkdir();
		} catch (Exception e) {
			throw new ServletException("创建 "+INDEX_PATH+" 目录失败.", e);
		}
	}
	
	/**
	 * 获得 索引地址
	 * @return
	 */
	public static String getIndexPath(){
		return INDEX_PATH;
	}
	
}
