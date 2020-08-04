package com.wook.app.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wook.app.article.dao.ArticlesDAO;
import com.wook.app.article.model.ArticleVO;

@Service
public class ArticlesServiceImpl implements ArticlesService {

	@Autowired private ArticlesDAO articlesDAO;

	@Override
	public List<ArticleVO> getArticles(ArticleVO article) {
		return articlesDAO.selectArticles(article);
	} // getArticles() end
	
	@Override
	public void articleRegister(ArticleVO article) {
		articlesDAO.insertArticle(article);
	} // articleRegister() end
	
	@Override
	public void removeArticle(int no) {
		articlesDAO.deleteArticle(no);
	} // removeArticle() end
	
} // ArticlesServiceImpl end