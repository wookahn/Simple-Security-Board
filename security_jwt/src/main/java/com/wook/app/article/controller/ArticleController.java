package com.wook.app.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wook.app.article.excel.ExcelArticleService;
import com.wook.app.article.model.ArticleVO;
import com.wook.app.article.service.ArticlesService;
import com.wook.app.common.util.FileUtil;
import com.wook.app.user.model.UserVO;

@RestController
public class ArticleController {
	@Autowired private ArticlesService articlesService;
	@Autowired private ExcelArticleService excelArticleService;
	
	@RequestMapping(value = "/articles", method = RequestMethod.GET)
	public List<ArticleVO> articles(@RequestBody(required = false) ArticleVO article) {
		
		List<ArticleVO> articles = articlesService.getArticles(article);
		System.err.println("Articles num: " + articles.size());
		
		return articles;
		
	} // articles() end
	
	@RequestMapping(value = "/article/register", method = RequestMethod.POST)
	public void register(@RequestBody ArticleVO article, Authentication auth) {
		
		UserVO user = (UserVO) auth.getPrincipal();
		article.setWriter(user.getNo());
		
		articlesService.articleRegister(article);
		
	} // register() end
	
	@RequestMapping(value = "/article/remove", method = RequestMethod.POST)
	public void removeArticle(@RequestBody ArticleVO article) {
		articlesService.removeArticle(article.getNo());
	} // removeArticle() end
	
	@RequestMapping(value = "/article/download", method = RequestMethod.POST)
	public void download(@RequestBody(required = false) ArticleVO article) {
		FileUtil.downloadFile(excelArticleService.downloadArticles(article));
	} // download() end
	
} // ArticleController end