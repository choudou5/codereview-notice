package com.dreamerpartner.codereview.service;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.SortField.Type;

import com.dreamerpartner.codereview.convert.NoticeEntityConvert;
import com.dreamerpartner.codereview.entity.NoticeEntity;
import com.dreamerpartner.codereview.lucene.IndexHelper;
import com.dreamerpartner.codereview.lucene.SearchHelper;
import com.dreamerpartner.codereview.util.DateUtil;
import com.dreamerpartner.codereview.util.PropertiesUtil;
import com.dreamerpartner.codereview.vo.IndexNoticeGroupVo;

/**
 * 公告 Service
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class NoticeService {

	/**
	 * 初始化索引
	 * @param entity
	 * @throws ServletException 
	 * @throws IOException
	 */
	public static void initIndex() throws ServletException{
		NoticeEntity entity = new NoticeEntity();
		entity.setId(-1L);
		entity.setCreateTime(DateUtil.toString(new Date()));
		entity.setGroupKey("");
		entity.setContent("");
		entity.setType("");
		entity.setTitle("");
		NoticeEntityConvert convert = new NoticeEntityConvert();
		try {
			IndexHelper.add(convert.convert(entity), true);
		} catch (Exception e) {
			throw new ServletException("初始化 Notice 索引失败.", e);
		}
	}
	
	/**
	 * 保存
	 * @param entity
	 * @throws IOException
	 */
	public static void save(NoticeEntity entity) throws IOException{
		boolean isNew = entity.getId()==null;
		if(isNew){
			entity.setId(Long.parseLong(DateUtil.getTimeStr()));
		}
		entity.setCreateTime(DateUtil.toString(new Date()));
		NoticeEntityConvert convert = new NoticeEntityConvert();
		IndexHelper.add(convert.convert(entity), isNew);
	}
	
	/**
	 * 单条查询
	 * @param entity
	 * @throws IOException
	 */
	public static NoticeEntity get(String id) throws IOException{
		Document doc = SearchHelper.getById(id);
		if(doc != null){
			NoticeEntityConvert convert = new NoticeEntityConvert();
			return convert.convert(doc, false);
		}
		return null;
	}
	
	/**
	 * 获得 首页公告信息
	 * @return
	 */
	public static IndexNoticeGroupVo getIndexNoticeGroupVo(){
		int pageSize = PropertiesUtil.getInteger("index.group.pagesize", 10);
		String groupField = "groupKey", searchField = "type", orderField = "id";
		Type orderFieldType = Type.LONG;
		boolean asc = true;
		
		//search good type
		Map<String, List<Document>> goodGroupData = SearchHelper.group(groupField, searchField, "good", 1, pageSize, orderField, orderFieldType, asc);
		Map<String ,List<NoticeEntity>> goodGroupEntitys = convertGroupData(goodGroupData);
		//search good type
		Map<String, List<Document>> badGroupData = SearchHelper.group(groupField, searchField, "bad", 1, pageSize, orderField, orderFieldType, asc);
		Map<String ,List<NoticeEntity>> badGroupEntitys = convertGroupData(badGroupData);
		return new IndexNoticeGroupVo(goodGroupEntitys, badGroupEntitys);
	}
	
	
	/**
	 * 获得 分组数据
	 * @param groupKey 分组key
	 * @param type 类型
	 * @return
	 */
	public static List<NoticeEntity> getGroupList(String groupKey, String type){
		int pageSize = PropertiesUtil.getInteger("index.group.pagesize", 10);
		NoticeEntityConvert convert = new NoticeEntityConvert();
		String[] searchFields = new String[]{"groupKey", "type"};
		String[] searchStrs= new String[]{groupKey, type};
		//search
		List<Document> badDocs = SearchHelper.search(searchFields, searchStrs, pageSize);
		return convert.converts(badDocs);
	}
	
	
	/**
	 * 转换 分组数据
	 * @param groupData
	 * @return
	 */
	private static Map<String ,List<NoticeEntity>> convertGroupData(Map<String, List<Document>> groupData){
		Map<String ,List<NoticeEntity>> result = new LinkedHashMap<String, List<NoticeEntity>>(groupData.size());
		NoticeEntityConvert convert = new NoticeEntityConvert();
		for (String groupKey : groupData.keySet()) {
			List<Document> docs = groupData.get(groupKey);
			List<NoticeEntity> entitys = convert.converts(docs);
			result.put(groupKey, entitys);
		}
		return result;
	}
}
