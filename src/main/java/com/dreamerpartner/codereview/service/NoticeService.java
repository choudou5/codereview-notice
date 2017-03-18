package com.dreamerpartner.codereview.service;

import java.util.List;

import com.dreamerpartner.codereview.IndexNoticeVo;
import com.dreamerpartner.codereview.servlet.CodeReviewNoticeServlet;
import com.dreamerpartner.codereview.util.PropertiesUtil;

/**
 * 公告 Service
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class NoticeService {

	
	public static List<IndexNoticeVo> getIndexNoticeVos(){
		int pageSize = PropertiesUtil.getInteger("index.group.pagesize", 10);
		List<String> groudList = CodeReviewNoticeServlet.GROUP_LIST;
		for (String groupKey : groudList) {
			
		}
		return null;
	}
}
