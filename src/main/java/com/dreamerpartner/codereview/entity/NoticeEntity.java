package com.dreamerpartner.codereview.entity;

import java.io.Serializable;

/**
 * 公告 Entity
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class NoticeEntity implements Serializable{

	private static final long serialVersionUID = 9047384879926533459L;
	
	private String id;
	private String title;
	private String content;
	private String type;
	private String groupKey;
	private String createTime;
	
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
