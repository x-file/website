package com.thinkgem.jeesite.modules.cms.service;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDao;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;

@Service
@Transactional(readOnly = true)
public class ArticleServiceSeach {
	@Autowired
	private ArticleDao articleDAO;

	private final String INDEXPATH = "d:\\index";
	private Analyzer analyzer = new StandardAnalyzer();

	//将检索到的数据进行分页
	public Page<Article> find(Page<Article> page, Article article,List<Article> qlist) {
		article.setPage(page);
		page.setList(qlist);
		page.setCount(qlist.size());
		page.setPageSize(15);
		return page;
	}
	
	@SuppressWarnings("unused")
	public Page<Article> getArticles(Page<Article> page,String query) {

		try {
			List<Article> qlist = new ArrayList<Article>();
			String fieldName = "title";
			IndexSearcher indexSearcher = new IndexSearcher(INDEXPATH);
			// QueryParser parser = new QueryParser(fieldName, analyzer); //单
			// key 搜索
			// Query queryOBJ = parser.parse(query);
			System.out.println(">>> 2.开始读取索引... ... 通过关键字：【 " + query + " 】");
			long begin = new Date().getTime();
			// 下面的是进行title,content 两个范围内进行收索.
			BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };
			Query queryOBJ = MultiFieldQueryParser.parse(query, new String[] { "title", "content" }, clauses,
					new StandardAnalyzer());// parser.parse(query);
			Filter filter = null;
			// ################# 搜索相似度最高的记录 ###################
			TopDocs topDocs = indexSearcher.search(queryOBJ, filter, 1000);
			// TopDocs topDocs = indexSearcher.search(queryOBJ , 10000);
			System.out.println("*** 共匹配：" + topDocs.totalHits + "个 ***");
			Article article = null;

			// 输出结果
			for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
				Document targetDoc = indexSearcher.doc(scoreDoc.doc);
				article = new Article();
				// 设置高亮显示格式
				SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font color='red'><strong>",
						"</strong></font>");
				/* 语法高亮显示设置 */
				Highlighter highlighter = new Highlighter(simpleHTMLFormatter, new QueryScorer(queryOBJ));
				highlighter.setTextFragmenter(new SimpleFragmenter(100));
				// 设置高亮 设置 title,content 字段
				String id = targetDoc.get("id");
				String title = targetDoc.get("title");
				String content = targetDoc.get("content");
				String category_id = targetDoc.get("category_id");
				TokenStream titleTokenStream = analyzer.tokenStream(fieldName, new StringReader(title));
				TokenStream contentTokenStream = analyzer.tokenStream("content", new StringReader(content));
				String highLightTitle = highlighter.getBestFragment(titleTokenStream, title);
				String highLightContent = highlighter.getBestFragment(contentTokenStream, content);

				if (highLightTitle == null)
					highLightTitle = title;

				if (highLightContent == null)
					highLightContent = content;

				article.setTitle(highLightTitle);
				article.setDescription(highLightContent);
				article.setId(id);
				article.setCategory(new Category(category_id));
				qlist.add(article);
			}

			long end = new Date().getTime();
			System.out.println(">>> 3.搜索完毕... ... 共花费：" + (end - begin) + "毫秒...");
			indexSearcher.close();
			
			//return qlist;
			if(article==null)
				return null;
			else
				return find(page, article, qlist);
				
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	

	@SuppressWarnings("unused")
	public boolean createIndex() {
		// 检查索引是否存在
		if (this.isIndexExisted())
			return this.isIndexExisted();

		List<Article> list = articleDAO.findAllList(new Article());
		try {
			Directory directory = FSDirectory.getDirectory(INDEXPATH);
			IndexWriter indexWriter = new IndexWriter(directory, analyzer, true, IndexWriter.MaxFieldLength.LIMITED);

			long begin = new Date().getTime();
			for (Article art : list) {
				Document doc = new Document();//创建索引库的文档
				String id = art.getId() ==null ? "" : art.getId().trim();
				String title = art.getTitle() == null ? "" : art.getTitle().trim();
				String content = art.getDescription() == null ? "" : art.getDescription();
				String category_id = art.getCategory().getId() == null ? "" : art.getCategory().getId();
				doc.add(new Field("title", title, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
				doc.add(new Field("content", content, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.YES));
				doc.add(new Field("category_id", category_id, Field.Store.YES, Field.Index.ANALYZED,Field.TermVector.YES));
				doc.add(new Field("id", id, Field.Store.YES, Field.Index.ANALYZED,Field.TermVector.YES));
				indexWriter.addDocument(doc);
			}
			long end = new Date().getTime();
			System.out.println(">>> 1.存入索引完毕.. 共花费：" + (end - begin) + "毫秒...");

			indexWriter.optimize();
			indexWriter.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * check Index is Existed
	 * 
	 * @return true or false
	 */
	private boolean isIndexExisted() {
		try {
			File dir = new File(INDEXPATH);
			//if (dir.listFiles().length > 0)
			if (dir.exists())
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
// package com.thinkgem.jeesite.modules.cms.service;
//
// import java.io.File;
// import java.io.StringReader;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
//
// import org.apache.lucene.analysis.Analyzer;
// import org.apache.lucene.analysis.TokenStream;
// import org.apache.lucene.analysis.standard.StandardAnalyzer;
// import org.apache.lucene.document.Document;
// import org.apache.lucene.document.Field;
// import org.apache.lucene.index.IndexWriter;
// import org.apache.lucene.queryParser.MultiFieldQueryParser;
// import org.apache.lucene.search.BooleanClause;
// import org.apache.lucene.search.Filter;
// import org.apache.lucene.search.IndexSearcher;
// import org.apache.lucene.search.Query;
// import org.apache.lucene.search.ScoreDoc;
// import org.apache.lucene.search.TopDocs;
// import org.apache.lucene.search.highlight.Highlighter;
// import org.apache.lucene.search.highlight.QueryScorer;
// import org.apache.lucene.search.highlight.SimpleFragmenter;
// import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
// import org.apache.lucene.store.Directory;
// import org.apache.lucene.store.FSDirectory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import com.thinkgem.jeesite.modules.cms.dao.ArticleDao;
// import com.thinkgem.jeesite.modules.cms.entity.Article;
// import com.thinkgem.jeesite.modules.cms.entity.Category;
//
// @Service
// @Transactional(readOnly = true)
// public class ArticleServiceSeach{
// @Autowired
// private ArticleDao articleDAO;
//
// private final String INDEXPATH = "d:\\index";
// private Analyzer analyzer = new StandardAnalyzer();
//
// @SuppressWarnings("unused")
// public List<Article> getArticles(String query) {
//
// try{
// List<Article> qlist = new ArrayList<Article>();
// String fieldName = "title";
// IndexSearcher indexSearcher = new IndexSearcher(INDEXPATH);
// //QueryParser parser = new QueryParser(fieldName, analyzer); //单 key 搜索
// //Query queryOBJ = parser.parse(query);
// System.out.println(">>> 2.开始读取索引... ... 通过关键字：【 "+ query +" 】");
// long begin = new Date().getTime();
// //下面的是进行title,content 两个范围内进行收索.
// BooleanClause.Occur[] clauses = {
// BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD };
// Query queryOBJ = MultiFieldQueryParser.parse(query, new
// String[]{"title","content"}, clauses, new
// StandardAnalyzer());//parser.parse(query);
// Filter filter = null;
// //################# 搜索相似度最高的记录 ###################
// TopDocs topDocs = indexSearcher.search(queryOBJ, filter, 1000);
// //TopDocs topDocs = indexSearcher.search(queryOBJ , 10000);
// System.out.println("*** 共匹配：" + topDocs.totalHits + "个 ***");
// Article article = null;
//
// //输出结果
// for (ScoreDoc scoreDoc : topDocs.scoreDocs){
// Document targetDoc = indexSearcher.doc(scoreDoc.doc);
// article = new Article();
// //设置高亮显示格式
// SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<font
// color='red'><strong>", "</strong></font>");
// /* 语法高亮显示设置 */
// Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new
// QueryScorer(queryOBJ));
// highlighter.setTextFragmenter(new SimpleFragmenter(100));
// // 设置高亮 设置 title,content 字段
// String title = targetDoc.get("title");
// String content = targetDoc.get("content");
// String category_id = targetDoc.get("category_id");
// TokenStream titleTokenStream = analyzer.tokenStream(fieldName,new
// StringReader(title));
// TokenStream contentTokenStream = analyzer.tokenStream("content",new
// StringReader(content));
// String highLightTitle = highlighter.getBestFragment(titleTokenStream, title);
// String highLightContent = highlighter.getBestFragment(contentTokenStream,
// content);
//
// if(highLightTitle == null)
// highLightTitle = title;
//
// if(highLightContent == null)
// highLightContent = content;
//
//
// article.setTitle(highLightTitle);
// article.setDescription(highLightContent);
// //article.setCategory_id(category_id);
// article.setCategory(new Category(category_id));
// qlist.add(article);
// }
//
// long end = new Date().getTime();
// System.out.println(">>> 3.搜索完毕... ... 共花费：" + (end - begin) +"毫秒...");
// indexSearcher.close();
// return qlist;
// }catch(Exception e){
// e.printStackTrace();
// return null;
// }
// }
//
// @SuppressWarnings("unused")
// public boolean createIndex()
// {
// //检查索引是否存在
// if(this.isIndexExisted())
// return this.isIndexExisted();
//
// List<Article> list = articleDAO.findAllList(new Article());
// try
// {
// Directory directory = FSDirectory.getDirectory(INDEXPATH);
// IndexWriter indexWriter = new IndexWriter(directory, analyzer ,true,
// IndexWriter.MaxFieldLength.LIMITED);
//
// long begin = new Date().getTime();
// for(Article art: list)
// {
// Document doc = new Document();
// String title = art.getTitle() == null ? "" : art.getTitle().trim();
// String content = art.getDescription() == null ? "" : art.getDescription();
// String category_id = art.getCategory().getId() == null ? "" :
// art.getCategory().getId();
// doc.add(new Field("title", title, Field.Store.YES, Field.Index.ANALYZED,
// Field.TermVector.YES));
// doc.add(new Field("content", content, Field.Store.YES, Field.Index.ANALYZED,
// Field.TermVector.YES));
// doc.add(new Field("category_id", category_id, Field.Store.YES,
// Field.Index.ANALYZED, Field.TermVector.YES));
// indexWriter.addDocument(doc);
// }
// long end = new Date().getTime();
// System.out.println(">>> 1.存入索引完毕.. 共花费：" + (end - begin) +"毫秒...");
//
// indexWriter.optimize();
// indexWriter.close();
// return true;
//
// }catch(Exception e){
// e.printStackTrace();
// return false;
// }
// }
//
// /**
// * check Index is Existed
// * @return true or false
// */
// private boolean isIndexExisted()
// {
// try
// {
// File dir = new File(INDEXPATH);
// if(dir.listFiles().length>0)
// return true;
// else
// return false;
// }catch(Exception e){
// e.printStackTrace();
// return false;
// }
// }
// }