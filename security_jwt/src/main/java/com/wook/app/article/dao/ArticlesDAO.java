package com.wook.app.article.dao;

import java.util.List;

import com.wook.app.article.model.ArticleVO;
import com.wook.app.common.excel.ResultRowDataHandler;

public interface ArticlesDAO {
	public List<ArticleVO> selectArticles(ArticleVO article);
	public int insertArticle(ArticleVO article);
	public int deleteArticle(int no);
	public void selectArticles(ArticleVO article, ResultRowDataHandler handler);
} // ArticlesDAO end