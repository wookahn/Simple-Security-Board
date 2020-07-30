package com.wook.app.article.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wook.app.article.model.ArticleVO;
import com.wook.app.common.excel.ResultRowDataHandler;

@Repository
public class ArticlesDAOImpl implements ArticlesDAO {

	@Autowired private SqlSession session;
	
	@Override
	public List<ArticleVO> selectArticles(ArticleVO article) {
		return session.selectList("articleSqlMap.selectArticles", article);
	} // selectArticles() end
	
	@Override
	public int insertArticle(ArticleVO article) {
		return session.insert("articleSqlMap.insertArticle", article);
	} // insertArticle() end
	
	@Override
	public int deleteArticle(int no) {
		return session.update("articleSqlMap.deleteArticle", no);
	} // deleteArticle() end
	
	@Override
	public void selectArticles(ArticleVO article, ResultRowDataHandler handler) {
		session.select("articleSqlMap.selectArticlesDownload", article, handler);
	} // selectArticles() end
	
} // ArticlesDAOImpl end