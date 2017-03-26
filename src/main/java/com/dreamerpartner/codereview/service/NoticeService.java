package com.dreamerpartner.codereview.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;

import com.dreamerpartner.codereview.convert.NoticeModelConvert;
import com.dreamerpartner.codereview.lucene.IndexHelper;
import com.dreamerpartner.codereview.lucene.SearchHelper;
import com.dreamerpartner.codereview.model.NoticeModel;
import com.dreamerpartner.codereview.page.PageBean;
import com.dreamerpartner.codereview.util.DateUtil;
import com.dreamerpartner.codereview.util.PropertiesUtil;
import com.dreamerpartner.codereview.vo.IndexNoticeGroupVo;

/**
 * 公告 Service
 * @author xuhaowen
 * @date 2017年3月18日
 */
public class NoticeService {

	public static final String MODULE = "notice";
	
	/**
	 * 初始化索引
	 * @param model
	 * @throws ServletException 
	 * @throws IOException
	 */
	public static void initIndex() throws ServletException{
		NoticeModel model = new NoticeModel();
		model.setId("0");
		model.setGroupKey("");
		model.setContent("");
		model.setType("");
		model.setTitle("");
		model.setCreateBy("");
		model.setCreateTime(DateUtil.getDateStr());
		NoticeModelConvert convert = new NoticeModelConvert();
		try {
			IndexHelper.add(MODULE, convert.convert(model), true);
		} catch (Exception e) {
			throw new ServletException("初始化 Notice 索引失败.", e);
		}
	}
	
	/**
	 * 保存
	 * @param model
	 * @throws IOException
	 */
	public static void save(NoticeModel model) throws IOException{
		boolean isNew = model.getId()==null;
		if(isNew){
			model.setId(DateUtil.getTimeStr());
		}
		model.setCreateTime(DateUtil.getDateStr());
		NoticeModelConvert convert = new NoticeModelConvert();
		IndexHelper.add(MODULE, convert.convert(model), isNew);
	}
	
	/**
	 * 删除
	 * @param id
	 * @throws IOException
	 */
	public static void deleteById(String id) throws IOException{
		IndexHelper.delete(MODULE, id);
		CommentService.deleteByNoticeId(id);
	}
	
	/**
	 * 删除所有
	 * @throws IOException
	 */
	public static void deleteAll() throws IOException{
		IndexHelper.deleteAll(MODULE);
		CommentService.deleteAll();
	}
	
	
	/**
	 * 单条查询
	 * @param model
	 * @throws IOException
	 */
	public static NoticeModel get(String id) throws IOException{
		Document doc = SearchHelper.getById(MODULE, id);
		if(doc != null){
			NoticeModelConvert convert = new NoticeModelConvert();
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
		Type orderFieldType = Type.STRING;
		boolean desc = true;
		
		//search good type
		Map<String, List<Document>> goodGroupData = SearchHelper.group(MODULE, groupField, searchField, "good", 1, pageSize, orderField, orderFieldType, desc);
		Map<String ,List<NoticeModel>> goodGroupEntitys = convertGroupData(goodGroupData);
		//search good type
		Map<String, List<Document>> badGroupData = SearchHelper.group(MODULE, groupField, searchField, "bad", 1, pageSize, orderField, orderFieldType, desc);
		Map<String ,List<NoticeModel>> badGroupEntitys = convertGroupData(badGroupData);
		return new IndexNoticeGroupVo(goodGroupEntitys, badGroupEntitys);
	}
	
	
	/**
	 * 获得 分组数据
	 * @param groupKey
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static List<NoticeModel> getGroupList(String groupKey, String type, int pageNo, int pageSize){
		NoticeModelConvert convert = new NoticeModelConvert();
		String[] searchFields = new String[]{"groupKey", "type"};
		String[] searchStrs= new String[]{groupKey, type};
		Sort sort = new Sort(new SortField("id", Type.STRING));
		//search
		PageBean<Document> badDocs = SearchHelper.search(MODULE, searchFields, searchStrs, sort, pageNo, pageSize);
		return convert.converts(badDocs.getResult());
	}
	
	
	/**
	 * 转换 分组数据
	 * @param groupData
	 * @return
	 */
	private static Map<String ,List<NoticeModel>> convertGroupData(Map<String, List<Document>> groupData){
		Map<String ,List<NoticeModel>> result = new LinkedHashMap<String, List<NoticeModel>>(groupData.size());
		NoticeModelConvert convert = new NoticeModelConvert();
		for (String groupKey : groupData.keySet()) {
			List<Document> docs = groupData.get(groupKey);
			List<NoticeModel> models = convert.converts(docs);
			result.put(groupKey, models);
		}
		return result;
	}
}
