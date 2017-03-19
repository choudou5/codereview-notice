package com.dreamerpartner.codereview.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dreamerpartner.codereview.entity.NoticeEntity;

/**
 * 首页 公告组 Vo
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class IndexNoticeGroupVo implements Serializable{

	private static final long serialVersionUID = 8570843787403267913L;
	
	private Map<String, List<NoticeEntity>> goods;
	private Map<String, List<NoticeEntity>> bads;
	
	public IndexNoticeGroupVo(){}
	
	public IndexNoticeGroupVo(Map<String, List<NoticeEntity>> goods, Map<String, List<NoticeEntity>> bads){
		this.goods = goods;
		this.bads = bads;
	}

	public Map<String, List<NoticeEntity>> getGoods() {
		return goods;
	}

	public void setGoods(Map<String, List<NoticeEntity>> goods) {
		this.goods = goods;
	}

	public Map<String, List<NoticeEntity>> getBads() {
		return bads;
	}

	public void setBads(Map<String, List<NoticeEntity>> bads) {
		this.bads = bads;
	}
	
}
