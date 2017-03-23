package com.dreamerpartner.codereview.lucene;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.dreamerpartner.codereview.util.PropertiesUtil;

/**
 * Lucene 工具类
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class LuceneUtil {

	public static String DATA_PATH;
	private static String INDEX_PATH;
	
	/**
	 * 初始化  索引目录
	 * @throws ServletException
	 * @throws IOException 
	 */
	public static void initIndexDir() throws ServletException{
		DATA_PATH = PropertiesUtil.getString("data.dir");
		if(StringUtils.isBlank(DATA_PATH))
			throw new ServletException("请初始化 system.properties 里的 data.dir 参数。");
		
		INDEX_PATH = DATA_PATH+"/index";
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
	public static String getIndexPath(String module){
		return INDEX_PATH+"/"+module;
	}
	
	/**
	 * 获得 数据地址
	 * @return
	 */
	public static String getDataPath(){
		return DATA_PATH;
	}
}
