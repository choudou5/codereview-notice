package com.dreamerpartner.codereview.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import com.dreamerpartner.codereview.model.CommentModel;

public class CommentModelConvert implements LuceneModelConvert<CommentModel>{

	@Override
	public List<CommentModel> converts(List<Document> docs) {
		if(CollectionUtils.isEmpty(docs))
			return null;
		List<CommentModel> result = new ArrayList<CommentModel>(docs.size());
		for (Document doc : docs) {
			result.add(convert(doc, true));
		}
		return result;
	}

	@Override
	public CommentModel convert(Document doc, boolean isList) {
		CommentModel entity = new CommentModel();
		entity.setId(doc.get("id"));
		entity.setNoticeId(doc.get("noticeId"));
		entity.setContent(doc.get("content"));
		entity.setCreateTime(doc.get("createTime"));
		entity.setThumbsUpCount(Integer.parseInt(doc.get("thumbsUpCount")));
		return entity;
	}
	
	@Override
	public Document convert(CommentModel entity) {
		Document doc = new Document();
		doc.add(new StringField("id", entity.getId(), Field.Store.YES));
		doc.add(new StringField("noticeId", entity.getNoticeId(), Field.Store.YES));
		doc.add(new TextField("content", entity.getContent(), Field.Store.YES));
		doc.add(new StringField("createTime", entity.getCreateTime(), Field.Store.YES));
		doc.add(new IntField("thumbsUpCount", entity.getThumbsUpCount(), Field.Store.YES));
		return doc;
	}
	
}
