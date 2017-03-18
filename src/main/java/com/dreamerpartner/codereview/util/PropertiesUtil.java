package com.dreamerpartner.codereview.util;

import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 属性资源文件 工具类
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class PropertiesUtil {

	protected static Log logger = LogFactory.getLog(PropertiesUtil.class);
	
	private static Properties props;
	
	private PropertiesUtil() {}
	
	public static void init(String filePath){
		try {
            props = new Properties();
            InputStreamReader inputStream = new InputStreamReader(
            		PropertiesUtil.class.getResourceAsStream(filePath), "UTF-8");
            props.load(inputStream);
        } catch (Exception e) {
            logger.error("init Properties file: "+filePath+" fail.", e);
        }
	}

    /** 
     * 通过key值获取文件的String类型数据 
     * @param key 
     * @return 
     */  
    public static String getString(String key){  
        return props.getProperty(key);  
    }
    
    /** 
     * 通过key值获取文件的String类型数据 
     * @param key 
     * @return 
     */  
    public static String[] getStringArray(String key){  
        String val = props.getProperty(key);
        if(StringUtils.isBlank(val))
        	return null;
        return val.split(",");
    }
    
    /** 
     * 通过key值获取文件的int类型数据 
     * @param key 
     * @return 
     */  
    public static Integer getInteger(String key){  
    	String val = props.getProperty(key);
        return val==null?null:Integer.parseInt(val);  
    }
    
    /** 
     * 通过key值获取文件的int类型数据 
     * @param key 
     * @param def 默认值
     * @return 
     */  
    public static Integer getInteger(String key, int def){  
    	Integer val = getInteger(key);
    	if(val == null)
    		return def;
    	return val;
    }
    
    /** 
     * 通过key值获取文件的double类型数据 
     * @param key 
     * @return 
     */  
    public static Double getDouble(String key){  
        return Double.parseDouble(props.getProperty(key));  
    }
    
    /** 
     * 通过key值获取文件的boolean类型数据 
     * @param key 
     * @return 
     */  
    public static Boolean getBoolean(String key){  
        return Boolean.parseBoolean(props.getProperty(key));  
    }  

    /**
     * 得到所有的配置信息
     * 
     * @return
     *//*
    public Map<?, ?> getAll() {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<?> enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }*/
    
}
