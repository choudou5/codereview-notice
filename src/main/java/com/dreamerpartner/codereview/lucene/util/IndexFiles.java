package com.dreamerpartner.codereview.lucene.util;

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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/** Index all text files under a directory.
 * <p>
 * This is a command-line application demonstrating simple Lucene indexing.
 * Run it with no command-line arguments for usage information.
 */
public class IndexFiles {
  
  private IndexFiles() {}
  
  static String INDEX_PATH = "D:\\data\\lucene\\index";
  
  /** Index all text files under a directory. */
  public static void main(String[] args) {
    boolean create = true;
    Date start = new Date();
    try {
      System.out.println("Indexing to directory '" + INDEX_PATH + "'...");

      Directory dir = FSDirectory.open(new File(INDEX_PATH));
      Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_0);
      IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_4_10_0, analyzer);

      if (create) {
        iwc.setOpenMode(OpenMode.CREATE);
      } else {
        iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
      }

//      iwc.setRAMBufferSizeMB(256.0);//设置内存 缓存区大小
      IndexWriter writer = new IndexWriter(dir, iwc);
      indexDocs(writer);

      //合并数据
      //writer.forceMerge(1);

      writer.close();
      Date end = new Date();
      System.out.println(end.getTime() - start.getTime() + " total milliseconds");

    } catch (IOException e) {
      System.out.println(" caught a " + e.getClass() +  "\n with message: " + e.getMessage());
    }
  }

  /**
   * 索引数据
   * @param writer
   * @throws IOException
   */
  static void indexDocs(IndexWriter writer)
    throws IOException {
	  List<Document> docs = new ArrayList<Document>();
      List<Field[]> list = getTestData();
      for (Field[] fields : list) {
    	  Document doc = new Document();
    	  for (Field field : fields) {
    		  doc.add(field);
    	  }
    	  docs.add(doc);
      }
      if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
        writer.addDocuments(docs);
      }
//      writer.updateDocument(new Term("id", 1001), doc);
  }
  
  static List<Field[]> getTestData(){
	  List<Field[]> list = new ArrayList<Field[]>(10);
	  int id = 1000;
	  list.add(getTestData(id++, "demo", "guide to using and running the Lucene demos. It walks you through some basic installation and configuration."));
	  list.add(getTestData(id++, "source", "application that demonstrates various functionalities of Lucene and how you can add Lucene to your applications."));
	  list.add(getTestData(id++, "files", "you should download the latest Lucene distribution and then extract it to a working directory"));
	  list.add(getTestData(id++, "good", "This will produce a subdirectory called index which will contain an index of all of the Lucene source code"));
	  list.add(getTestData(id++, "nice", "The results will page at every tenth result and ask you whether you want more results"));
	  list.add(getTestData(id++, "code", "their parts and their function. This section is intended for Java developers wishing to understand how to use Lucene in their applications"));
	  list.add(getTestData(id++, "bad", "is the location of the directory containing files to be indexed."));
	  list.add(getTestData(id++, "food", "Shows simple usage of dynamic range faceting, using the expressions module to calculate distance."));
	  list.add(getTestData(id++, "table", "because the earth is a bit wider at the equator than the poles"));
	  list.add(getTestData(id++, "detail", "Runs the search and drill-down examples and prints the results."));
	  return list;
  }
  
  
  static Field[] getTestData(int id, String title, String content){
	  Field[] fields = new Field[4];
	  fields[0] = new IntField("id", id, Field.Store.YES);
	  fields[1] = new TextField("title", title, Field.Store.YES);
	  fields[2] = new TextField("contents", content, Field.Store.YES);
	  fields[3] = new LongField("modified", System.currentTimeMillis(), Field.Store.YES);
	  return fields;
  }
  
  
  
}
