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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.GroupingSearch;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

/**
 * 查询助手
 * @author xuhaowen
 * @date 2017年3月19日
 */
public class SearchHelper {

  protected static Log logger = LogFactory.getLog(SearchHelper.class);
	
  private SearchHelper() {}
  
  @SuppressWarnings("deprecation")
  public static Document getById(String id){
	  if(StringUtils.isEmpty(id))
		  return null;
	  IndexReader reader = null;
	  try {
		  reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath())));
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
  
  @SuppressWarnings("deprecation")
  public static List<Document> search(String[] fields, String[] queryStr, int pageSize){
	  if(ArrayUtils.isEmpty(fields) || ArrayUtils.isEmpty(queryStr))
		  return null;
	  
	  IndexReader reader = null;
	  try {
		  reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath())));
		  IndexSearcher searcher = new IndexSearcher(reader);
		  
		  BooleanClause.Occur[] flags = new BooleanClause.Occur[fields.length];
		  for (int i = 0; i < fields.length; i++) {
			  flags[i] = BooleanClause.Occur.SHOULD;
		  }
		  BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
		  Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);
		  
		  //单字段查询
//		  QueryParser parser = new QueryParser(Version.LUCENE_4_10_0, field, analyzer);
//		  Query query = parser.parse(queryStr);
		  
		  Query query = MultiFieldQueryParser.parse(Version.LUCENE_4_10_0, queryStr, fields, flags, analyzer);
		  //分页查询
		  doPagingSearch(in, searcher, query, pageSize, false);
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
  public static Map<String, List<Document>> group(String groupField, String searchField, String searchStr, 
		  int pageNo, int pageSize, String orderField, Type orderFieldType, boolean desc) {
	    Map<String, List<Document>> result = new LinkedHashMap<String, List<Document>>(10);
	    IndexReader reader = null;
		try {
			  reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath())));
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
			  System.out.println("搜索命中数：" + groupResult.totalHitCount);
			  System.out.println("搜索结果分组数：" + groupResult.groups.length);
			  
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
  public static void main(String[] args) throws Exception {
    String field = "contents";
    String queries = null;
    String queryString = "produce";
    int repeat = 0;
    boolean raw = false;
    int hitsPerPage = 10;
    
    IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(LuceneUtil.getIndexPath())));
    IndexSearcher searcher = new IndexSearcher(reader);
    // :Post-Release-Update-Version.LUCENE_XY:
    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);

    BufferedReader in = null;
    if (queries != null) {
      in = new BufferedReader(new InputStreamReader(new FileInputStream(queries), StandardCharsets.UTF_8));
    } else {
      in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    }
    // :Post-Release-Update-Version.LUCENE_XY:
    QueryParser parser = new QueryParser(Version.LUCENE_4_10_0, field, analyzer);
    while (true) {
      if (queries == null && queryString == null) {                        // prompt the user
        System.out.println("Enter query: ");
      }

      String line = queryString != null ? queryString : in.readLine();

      if (line == null || line.length() == -1) {
        break;
      }

      line = line.trim();
      if (line.length() == 0) {
        break;
      }
      
      Query query = parser.parse(line);
      System.out.println("Searching for: " + query.toString(field));
            
      if (repeat > 0) {                           // repeat & time as benchmark
        Date start = new Date();
        for (int i = 0; i < repeat; i++) {
          searcher.search(query, null, 100);
        }
        Date end = new Date();
        System.out.println("Time: "+(end.getTime()-start.getTime())+"ms");
      }

      //分页查询
      doPagingSearch(in, searcher, query, hitsPerPage, raw);

      if (queryString != null) {
        break;
      }
    }
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
   * @param in
   * @param searcher
   * @param query
   * @param pageSize
   * @param raw
   * @throws IOException
   */
  public static void doPagingSearch(BufferedReader in, IndexSearcher searcher, Query query, 
                                     int pageSize, boolean raw) throws IOException {
    // Collect enough docs to show 5 pages
    TopDocs results = searcher.search(query, 5 * pageSize);
    ScoreDoc[] hits = results.scoreDocs;
    
    int numTotal = results.totalHits;
    System.out.println(numTotal + " total matching documents");

    int start = 0;
    int end = Math.min(numTotal, pageSize);
        
    while (true) {
      if (end > hits.length) {
        System.out.println("Only results 1 - " + hits.length +" of " + numTotal + " total matching documents collected.");
        System.out.println("Collect more (y/n) ?");
        String line = in.readLine();
        if (line.length() == 0 || line.charAt(0) == 'n') {
          break;
        }
        hits = searcher.search(query, numTotal).scoreDocs;
      }
      end = Math.min(hits.length, start + pageSize);
      
      for (int i = start; i < end; i++) {
        if (raw) {                              // output raw format
          System.out.println("doc="+hits[i].doc+" score="+hits[i].score);
          continue;
        }
        Document doc = searcher.doc(hits[i].doc);
        print(doc);
      }

      if (end == 0) {
        break;
      }

      if (numTotal >= end) {
        boolean quit = false;
        while (true) {
          System.out.print("Press ");
          if (start - pageSize >= 0) {
            System.out.print("(p)revious page, ");  
          }
          if (start + pageSize < numTotal) {
            System.out.print("(n)ext page, ");
          }
          System.out.println("(q)uit or enter number to jump to a page.");
          
          String line = in.readLine();
          if (line.length() == 0 || line.charAt(0)=='q') {
            quit = true;
            break;
          }
          if (line.charAt(0) == 'p') {
            start = Math.max(0, start - pageSize);
            break;
          } else if (line.charAt(0) == 'n') {
            if (start + pageSize < numTotal) {
              start+=pageSize;
            }
            break;
          } else {
            int page = Integer.parseInt(line);
            if ((page - 1) * pageSize < numTotal) {
              start = (page - 1) * pageSize;
              break;
            } else {
              System.out.println("No such page");
            }
          }
        }
        if (quit) break;
        end = Math.min(numTotal, start + pageSize);
      }
    }
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

