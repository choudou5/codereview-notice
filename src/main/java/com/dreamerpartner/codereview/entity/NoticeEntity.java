package com.dreamerpartner.codereview.entity;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 公告 Entity
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class NoticeEntity implements Serializable{

	private static final long serialVersionUID = 9047384879926533459L;
	
	private Long id;
	private String title;
	private String content;
	private String type;
	private String groupKey;
	private String createTime;
	
	public static boolean validate(NoticeEntity entity){
		if(entity == null){
			return false;
		}
		return (StringUtils.isBlank(entity.getGroupKey()) || StringUtils.isBlank(entity.getTitle()) 
				|| StringUtils.isBlank(entity.getContent()) || StringUtils.isBlank(entity.getType()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	@Override
	public String toString() {
		return "id:"+id+",title:"+title+",type:"+type+",groupKey:"+groupKey+", createTime:"+createTime;
	}
}
