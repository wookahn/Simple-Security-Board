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
<<<<<<< HEAD
	public void selectArticles(ArticleVO article, ResultRowDataHandler handler) {
		session.select("articleSqlMap.selectArticles", article, handler);
	} // selectArticles() end
	
	@Override
=======
>>>>>>> 960b185beb6916bf8b52e6787e2ecf75163ba641
	public int insertArticle(ArticleVO article) {
		return session.insert("articleSqlMap.insertArticle", article);
	} // insertArticle() end
	
	@Override
	public int deleteArticle(int no) {
		return session.update("articleSqlMap.deleteArticle", no);
	} // deleteArticle() end
	
<<<<<<< HEAD
=======
	@Override
	public void selectArticles(ArticleVO article, ResultRowDataHandler handler) {
		session.select("articleSqlMap.selectArticlesDownload", article, handler);
	} // selectArticles() end
>>>>>>> 960b185beb6916bf8b52e6787e2ecf75163ba641
	
} // ArticlesDAOImpl end