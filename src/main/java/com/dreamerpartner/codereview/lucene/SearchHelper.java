package com.dreamerpartner.codereview.lucene;


/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.GroupingSearch;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

import com.dreamerpartner.codereview.page.PageBean;
import com.dreamerpartner.codereview.util.JsonUtil;

/**
 * 查询助手
 * @author xuhaowen
 * @date 2017年3月19日
 */
public class SearchHelper {

  protected static Log logger = LogFactory.getLog(SearchHelper.class);
	
  private SearchHelper() {}
  
  @SuppressWarnings("deprecation")
  public static Document getById(String module, String id){
	  if(StringUtils.isEmpty(id))
		  return null;
	  IndexReader reader = null;
	  try {
		  reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath(module))));
		  IndexSearcher searcher = new IndexSearcher(reader);
		  
		  BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		  Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);
		  //单字段查询
		  QueryParser parser = new QueryParser(Version.LUCENE_4_10_0, "id", analyzer);
		  Query query = parser.parse(id);
		  return searchOne(in, searcher, query);
		} catch (Exception e) {
			logger.error(id+" getById fail.", e);
		}finally{
			try {
				if(reader !=null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	  return null;
  }
  
  /**
   * 搜索
   * @param module 模块
   * @param fields 搜索字段
   * @param queryStr 搜索值
   * @param sort
   * @param pageNo
   * @param pageSize
   * @return
   */
  @SuppressWarnings("deprecation")
  public static PageBean<Document> search(String module, String[] fields, String[] queryStr, Sort sort, int pageNo, int pageSize){
	  if(ArrayUtils.isEmpty(fields) || ArrayUtils.isEmpty(queryStr))
		  return null;
	  
	  IndexReader reader = null;
	  try {
		  reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath(module))));
		  IndexSearcher searcher = new IndexSearcher(reader);
		  
		  BooleanClause.Occur[] flags = new BooleanClause.Occur[fields.length];
		  for (int i = 0; i < fields.length; i++) {
			  flags[i] = BooleanClause.Occur.SHOULD;
		  }
		  Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);
		  Query query = MultiFieldQueryParser.parse(Version.LUCENE_4_10_0, queryStr, fields, flags, analyzer);
		  //分页查询
		  return doPagingSearch(searcher, query, sort, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader !=null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	  return null;
  }
  
  /**
   * 分组 查询
   * @param groupField 分组字段
   * @param searchField 查询字段
   * @param searchStr 查询字符串
   * @param pageNo
   * @param pageSize
   * @param orderField 排序字段
   * @param orderFieldType 排序字段类型
   * @param desc 是否 降序排
   * @return
   */
  @SuppressWarnings("deprecation")
  public static Map<String, List<Document>> group(String module, String groupField, String searchField, String searchStr, 
		  int pageNo, int pageSize, String orderField, Type orderFieldType, boolean desc) {
	    Map<String, List<Document>> result = new LinkedHashMap<String, List<Document>>(10);
	    IndexReader reader = null;
		try {
			  reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath(module))));
			  IndexSearcher indexSearcher = new IndexSearcher(reader);
			  GroupingSearch groupingSearch = new GroupingSearch(groupField);
			  Sort sort = new Sort(new SortField(orderField, orderFieldType, desc));
			  groupingSearch.setGroupSort(sort);
			  groupingSearch.setSortWithinGroup(sort);
			  groupingSearch.setFillSortFields(true);
			  groupingSearch.setCachingInMB(4.0, true);
			  groupingSearch.setAllGroups(true);
			  //groupingSearch.setAllGroupHeads(true);
			  groupingSearch.setGroupDocsLimit(pageSize);
	
			  QueryParser parser = new QueryParser(Version.LUCENE_4_10_0,  searchField, new StandardAnalyzer(Version.LUCENE_4_10_0));
			  Query query = parser.parse(searchStr);
	
			  TopGroups<BytesRef> groupResult = groupingSearch.search(indexSearcher, query, (pageNo-1)*pageSize, pageSize);
			  System.out.println("搜索命中数：" + groupResult.totalHitCount+", 搜索结果分组数：" + groupResult.groups.length);
			  
			  List<Document> groupData = null;
			  for (GroupDocs<BytesRef> groupDocs : groupResult.groups) {
				  groupData = new ArrayList<Document>(pageSize);
				  String groupName = groupDocs.groupValue.utf8ToString();
			      for (ScoreDoc scoreDoc : groupDocs.scoreDocs) {
			    	  groupData.add(indexSearcher.doc(scoreDoc.doc));
			      }
			      result.put(groupName, groupData);
			      groupData = null;
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader !=null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
      return result;
  }
  
  /** Simple command-line based search demo. */
  @SuppressWarnings("deprecation")
public static void main(String[] args) throws Exception {
	String module = "test";
    String field = "contents";
    String queryString = "produce";
    int pageNo = 1;
    int pageSize = 10;
    Sort sort = new Sort(new SortField("createTime", Type.LONG));
    
    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath(module))));
    IndexSearcher searcher = new IndexSearcher(reader);
    // :Post-Release-Update-Version.LUCENE_XY:
    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);

    // :Post-Release-Update-Version.LUCENE_XY:
    QueryParser parser = new QueryParser(Version.LUCENE_4_10_0, field, analyzer);
    Query query = parser.parse(queryString);
    System.out.println("Searching for: " + query.toString(field));
            
    //分页查询
    PageBean<Document> docs = doPagingSearch(searcher, query, sort, pageNo, pageSize);
    System.out.println(JsonUtil.toString(docs));
    reader.close();
  }

  /**
   * 单条记录
   * @param in
   * @param searcher
   * @param query
   * @return
   * @throws IOException
   */
  public static Document searchOne(BufferedReader in, IndexSearcher searcher, Query query) throws IOException {
	  TopDocs results = searcher.search(query, 1);
	  ScoreDoc[] hits = results.scoreDocs;
	  int numTotal = results.totalHits;
      if(numTotal > 0){
    	 return searcher.doc(hits[0].doc);
      }
      return null;
  }
  
  /**
   * 分页查询
   * @param searcher
   * @param query
   * @param sort 必填
   * @param pageNo
   * @param pageSize
   * @return
   * @throws IOException
   */
  public static PageBean<Document> doPagingSearch(IndexSearcher searcher, Query query, Sort sort,
                                     int pageNo, int pageSize) throws IOException {
	  if(sort == null)
		  sort = new Sort(new SortField("id", Type.STRING));
	  
	  int start = (pageNo-1)*pageSize;
	  int numHits = pageNo*pageSize;
	  TopFieldCollector collector = TopFieldCollector.create(sort, numHits, false, false, false, false);
	  searcher.search(query, collector);
	  ScoreDoc[] hits = collector.topDocs(start, pageSize).scoreDocs;
	  if (hits == null || hits.length < 1)
	  	return null;
	  
	  List<Document> docs = new ArrayList<Document>(pageSize);
	  for (int i = 0; i < hits.length; i++) {
		Document doc = searcher.doc(hits[i].doc);
		docs.add(doc);
	  }
	  return new PageBean<Document>(pageSize, pageNo, collector.getTotalHits(), docs);
  }
  
  static void print(Document doc){
	  String id = doc.get("id");
      if (id != null) {
        System.out.println(id+" : "+doc.get("title")+" : "+doc.get("contents")+" : "+doc.get("modified"));
      } else {
        System.out.println("No id for this document");
      }
  }
}

