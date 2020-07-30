package com.wook.app.article.service;

import java.util.List;

import com.wook.app.article.model.ArticleVO;

public interface ArticlesService {
	public List<ArticleVO> getArticles(ArticleVO article);
	public void articleRegister(ArticleVO article);
	public void removeArticle(int no);
} // ArticlesService end