package com.dreamerpartner.codereview.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

/**
 * OSS工具类 （https://help.aliyun.com/document_detail/32008.html?spm=5176.doc32010.6.648.RoAjFa）
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class OSSClientUtil {

	private static OSSClient client = null;
	private static String bucketName;
	private static String uploadDir;
	private static String endpoint;
	
	public static void init() throws ServletException{
		if(StringUtils.isBlank(bucketName)){
			endpoint = PropertiesUtil.getString("oss.endPoint");
			// accessKey请登录https://ak-console.aliyun.com/#/查看
			String accessKeyId = PropertiesUtil.getString("oss.accessKeyId");
			String accessKeySecret = PropertiesUtil.getString("oss.accessSecret");
			bucketName = PropertiesUtil.getString("oss.bucketName");
			uploadDir = PropertiesUtil.getString("oss.upload.dir");
			client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			boolean exists = client.doesBucketExist(bucketName);
			if(!exists){
				throw new ServletException("初始化 OSS失败. bucketName:"+bucketName+" 不存在.");
			}
			//创建目录
			client.putObject(bucketName, uploadDir, new ByteArrayInputStream(new byte[0]));
		}
	}
	
	/**
	 * 上传图片
	 * @param pathKey
	 * @param is
	 */
	public static PutObjectResult uploadImage(String pathKey, InputStream is){
		return client.putObject(bucketName, uploadDir+pathKey, is);
	}
	
	public static String getOssPath(){
		return "http://"+bucketName+"."+endpoint+"/"+uploadDir;
	}
	
	
	/**
	 * 关闭client
	 */
	public static void shutdown(){
		if(client != null) client.shutdown();
	}
}
