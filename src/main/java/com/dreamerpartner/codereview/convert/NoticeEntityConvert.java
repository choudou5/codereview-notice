package com.dreamerpartner.codereview.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import com.dreamerpartner.codereview.entity.NoticeEntity;

public class NoticeEntityConvert implements LuceneEntityConvert<NoticeEntity>{

	@Override
	public List<NoticeEntity> converts(List<Document> docs) {
		if(CollectionUtils.isEmpty(docs))
			return null;
		List<NoticeEntity> result = new ArrayList<NoticeEntity>(docs.size());
		for (Document doc : docs) {
			result.add(convert(doc, true));
		}
		return result;
	}

	@Override
	public NoticeEntity convert(Document doc, boolean isList) {
		NoticeEntity entity = new NoticeEntity();
		entity.setId(Long.parseLong(doc.get("id")));
		entity.setTitle(doc.get("title"));
		if(!isList)
			entity.setContent(doc.get("content"));
		entity.setType(doc.get("type"));
		entity.setGroupKey(doc.get("groupKey"));
		entity.setCreateTime(doc.get("createTime"));
		return entity;
	}
	
	@Override
	public Document convert(NoticeEntity entity) {
		Document doc = new Document();
		doc.add(new LongField("id", entity.getId(), Field.Store.YES));
		doc.add(new StringField("title", entity.getTitle(), Field.Store.YES));
		doc.add(new TextField("content", entity.getContent(), Field.Store.YES));
		doc.add(new StringField("type", entity.getType(), Field.Store.YES));
		doc.add(new StringField("groupKey", entity.getGroupKey(), Field.Store.YES));
		doc.add(new StringField("createTime", entity.getCreateTime(), Field.Store.YES));
		return doc;
	}
	
}
