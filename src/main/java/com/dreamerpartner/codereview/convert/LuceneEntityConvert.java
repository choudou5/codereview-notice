package com.dreamerpartner.codereview.convert;

import java.io.Serializable;
import java.util.List;

import org.apache.lucene.document.Document;

/**
 * Lucene 实体转换器
 * @author xuhaowen
 * @date 2017年3月19日
 */
public interface LuceneEntityConvert <T extends Serializable>{

	/**
	 * 转换成 实体集合
	 * @param docs
	 * @return
	 */
	List<T> converts(List<Document> docs);
	
	/**
	 * 转换成 实体
	 * @param doc
	 * @param isList 是否列表
	 * @return
	 */
	T convert(Document doc, boolean isList);
	
	/**
	 * 转换成 Document
	 * @param t
	 * @return
	 */
	Document convert(T t);
}
