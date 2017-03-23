package com.dreamerpartner.codereview.model;

import java.io.Serializable;

/**
 * 评论 模型
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class CommentModel implements Serializable{

	private static final long serialVersionUID = 4351870839260282289L;
	
	private String id;
	private String noticeId;
	private String content;
	private String createTime;
	private int thumbsUpCount; //点赞次数
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getThumbsUpCount() {
		return thumbsUpCount;
	}
	public void setThumbsUpCount(int thumbsUpCount) {
		this.thumbsUpCount = thumbsUpCount;
	}
	
}
