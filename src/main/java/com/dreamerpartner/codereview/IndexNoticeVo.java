package com.dreamerpartner.codereview;

import java.io.Serializable;
import java.util.List;

import com.dreamerpartner.codereview.entity.NoticeEntity;

/**
 * 首页 公告 Vo
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class IndexNoticeVo implements Serializable{

	private static final long serialVersionUID = 8570843787403267913L;
	
	private List<NoticeEntity> goods;
	private List<NoticeEntity> bads;
	
	public List<NoticeEntity> getGoods() {
		return goods;
	}
	public void setGoods(List<NoticeEntity> goods) {
		this.goods = goods;
	}
	public List<NoticeEntity> getBads() {
		return bads;
	}
	public void setBads(List<NoticeEntity> bads) {
		this.bads = bads;
	}
	
}
