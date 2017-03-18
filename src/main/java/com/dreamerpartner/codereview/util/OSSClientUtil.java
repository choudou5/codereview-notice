package com.dreamerpartner.codereview.util;

import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

/**
 * OSS工具类
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class OSSClientUtil {

	private static OSSClient client = null;
	private static String bucketName;
	
	public static void init(){
		String endpoint = "http://choudoufu-hd2.oss-cn-shanghai.aliyuncs.com";
		// accessKey请登录https://ak-console.aliyun.com/#/查看
		String accessKeyId = PropertiesUtil.getString("oss.accessKeyId");
		String accessKeySecret = PropertiesUtil.getString("oss.accessSecret");
		bucketName = PropertiesUtil.getString("oss.bucketName");
		client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}
	
	/**
	 * 上传图片
	 * @param is
	 */
	public static void uploadImage(InputStream is){
		PutObjectResult result = client.putObject(bucketName, "image", is);
		System.out.println(JsonUtil.toString(result));
	}
	
	
	/**
	 * 关闭client
	 */
	public static void shutdown(){
		if(client != null) client.shutdown();
	}
}
