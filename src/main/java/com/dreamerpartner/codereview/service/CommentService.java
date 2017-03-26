package com.dreamerpartner.codereview.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;

import com.dreamerpartner.codereview.convert.CommentModelConvert;
import com.dreamerpartner.codereview.lucene.IndexHelper;
import com.dreamerpartner.codereview.lucene.SearchHelper;
import com.dreamerpartner.codereview.model.CommentModel;
import com.dreamerpartner.codereview.page.PageBean;
import com.dreamerpartner.codereview.util.DateUtil;

/**
 * 评论 Service
 * @author xuhaowen
 * @date 2017年3月23日
 */
public class CommentService {

	public static final String MODULE = "comment";
	
	/**
	 * 初始化索引
	 * @param model
	 * @throws ServletException 
	 * @throws IOException
	 */
	public static void initIndex() throws ServletException{
		CommentModel model = new CommentModel();
		model.setId("0");
		model.setNoticeId("0");
		model.setContent("");
		model.setCreateTime(DateUtil.getDateStr());
		model.setThumbsUpCount(0);
		CommentModelConvert convert = new CommentModelConvert();
		try {
			IndexHelper.add(MODULE, convert.convert(model), true);
		} catch (Exception e) {
			throw new ServletException("初始化 Comment 索引失败.", e);
		}
	}
	
	/**
	 * 添加 (返回id)
	 * @param model
	 * @return id
	 * @throws IOException
	 */
	public static String add(String noticeId, String content) throws IOException{
		CommentModel model = new CommentModel();
		String id = DateUtil.getTimeFullStr();
		model.setId(id);
		model.setNoticeId(noticeId);
		model.setContent(content);
		model.setCreateTime(DateUtil.getDateStr());
		model.setThumbsUpCount(0);
		CommentModelConvert convert = new CommentModelConvert();
		IndexHelper.add(MODULE, convert.convert(model), true);
		return id;
	}
	
	/**
	 * 点赞
	 * @param model
	 * @return id
	 * @throws IOException
	 */
	public static void thumbsUp(String commentId) throws IOException{
		Document doc = SearchHelper.getById(MODULE, commentId);
		if(doc != null){
			int oldThumbsUpCount = Integer.parseInt(doc.get("thumbsUpCount"));
			doc.removeField("thumbsUpCount");
			doc.add(new IntField("thumbsUpCount", oldThumbsUpCount+1, Field.Store.YES));
		}
		IndexHelper.add(MODULE, doc, false);
	}
	
	/**
	 * 删除评论
	 * @param model
	 * @throws IOException
	 */
	public static void deleteById(String id) throws IOException{
		IndexHelper.delete(MODULE, id);
	}
	
	/**
	 * 根据 公告ID 删除评论
	 * @param model
	 * @param noticeId
	 * @throws IOException
	 */
	public static void deleteByNoticeId(String noticeId) throws IOException{
		IndexHelper.delete(MODULE, new Term("noticeId", noticeId));
	}
	
	/**
	 * 删除所有评论
	 * @throws IOException
	 */
	public static void deleteAll() throws IOException{
		IndexHelper.deleteAll(MODULE);
	}
	
	/**
	 * 列表数据
	 * @param noticeId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static PageBean<CommentModel> list(String noticeId, int pageNo, int pageSize){
		String[] searchFields = new String[]{"noticeId"};
		String[] searchStrs= new String[]{noticeId};
		Sort sort = new Sort(new SortField("thumbsUpCount", Type.INT, true), new SortField("id", Type.STRING, true));
		PageBean<Document> pageBeanDocs = SearchHelper.search(MODULE, searchFields, searchStrs, sort, pageNo, pageSize);
		return convertData(pageBeanDocs);
	}
	
	
	/**
	 * 转换 数据
	 * @param pageBeanDocs
	 * @return
	 */
	private static PageBean<CommentModel> convertData(PageBean<Document> pageBeanDocs){
		PageBean<CommentModel> pageBean = new PageBean<CommentModel>();
		if(pageBeanDocs != null){
			if(pageBeanDocs.getTotalCount() > 0){
				List<Document> docs = pageBeanDocs.getResult();
				CommentModelConvert convert = new CommentModelConvert();
				List<CommentModel> result = convert.converts(docs);
				pageBean.setPageNo(pageBeanDocs.getPageNo());
				pageBean.setPageSize(pageBeanDocs.getPageSize());
				pageBean.setTotalCount(pageBeanDocs.getTotalCount());
				pageBean.setResult(result);
			}
		}
		return pageBean;
	}
}
