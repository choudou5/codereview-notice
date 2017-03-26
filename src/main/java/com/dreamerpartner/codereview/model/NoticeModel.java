package com.dreamerpartner.codereview.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 公告 模型
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class NoticeModel implements Serializable{

	private static final long serialVersionUID = 9047384879926533459L;
	
	private String id;
	private String title;
	private String content;
	private String type;
	private String groupKey;
	private String createBy;
	private String createTime;
	
	public static boolean validate(NoticeModel entity){
		if(entity == null){
			return false;
		}
		return (StringUtils.isBlank(entity.getGroupKey()) || StringUtils.isBlank(entity.getTitle()) 
				|| StringUtils.isBlank(entity.getContent()) || StringUtils.isBlank(entity.getType()) 
				|| StringUtils.isBlank(entity.getCreateBy()));
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGroupKey() {
		return groupKey;
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Override
	public String toString() {
		return "id:"+id+",title:"+title+",type:"+type+",groupKey:"+groupKey+", createBy:"+createBy+", createTime:"+createTime;
	}
}
