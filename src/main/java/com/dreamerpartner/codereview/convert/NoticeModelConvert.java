package com.dreamerpartner.codereview.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import com.dreamerpartner.codereview.model.NoticeModel;
import com.dreamerpartner.codereview.util.DateUtil;

public class NoticeModelConvert implements LuceneModelConvert<NoticeModel>{

	@Override
	public List<NoticeModel> converts(List<Document> docs) {
		if(CollectionUtils.isEmpty(docs))
			return null;
		List<NoticeModel> result = new ArrayList<NoticeModel>(docs.size());
		for (Document doc : docs) {
			result.add(convert(doc, true));
		}
		return result;
	}

	@Override
	public NoticeModel convert(Document doc, boolean isList) {
		NoticeModel model = new NoticeModel();
		model.setId(doc.get("id"));
		model.setTitle(doc.get("title"));
		if(!isList)
			model.setContent(doc.get("content"));
		model.setType(doc.get("type"));
		model.setGroupKey(doc.get("groupKey"));
		model.setCreateBy(doc.get("createBy"));
		model.setCreateTime(DateUtil.parseZHDateStr(doc.get("createTime")));
		return model;
	}
	
	@Override
	public Document convert(NoticeModel model) {
		Document doc = new Document();
		doc.add(new StringField("id", model.getId(), Field.Store.YES));
		doc.add(new StringField("title", model.getTitle(), Field.Store.YES));
		doc.add(new TextField("content", model.getContent(), Field.Store.YES));
		doc.add(new StringField("type", model.getType(), Field.Store.YES));
		doc.add(new StringField("groupKey", model.getGroupKey(), Field.Store.YES));
		doc.add(new StringField("createBy", model.getCreateBy(), Field.Store.YES));
		doc.add(new StringField("createTime", model.getCreateTime(), Field.Store.YES));
		return doc;
	}
	
}
