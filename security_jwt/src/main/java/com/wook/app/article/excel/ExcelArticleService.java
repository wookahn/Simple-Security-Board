package com.wook.app.article.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wook.app.article.dao.ArticlesDAO;
import com.wook.app.article.model.ArticleVO;
import com.wook.app.common.excel.ResultRowDataHandler;

@Service
public class ExcelArticleService {
	
	@Autowired private ArticlesDAO articlesDAO;
	@Value("${excel.path}") private String DOWNLOAD_PATH;
	
<<<<<<< HEAD
	public String downloadArticles(ArticleVO article) {
=======
	public String downloadArticles() {
>>>>>>> 960b185beb6916bf8b52e6787e2ecf75163ba641
		File directory = new File(DOWNLOAD_PATH);
		File file = null;
		
		if (!directory.isDirectory()) {
			directory.mkdir();
		} // if end
		
		try {
			file = File.createTempFile("article_", ".xlsx", directory);
			
			SXSSFWorkbook workbook = new SXSSFWorkbook(100);
			ResultRowDataHandler handler = new ResultRowDataHandler(workbook);
			
<<<<<<< HEAD
			ArticleVO articleVO = new ArticleVO();
			articleVO.setId(article.getId());
			
			articlesDAO.selectArticles(articleVO, handler);
=======
			articlesDAO.selectArticles(new ArticleVO(), handler);
>>>>>>> 960b185beb6916bf8b52e6787e2ecf75163ba641
			
			FileOutputStream fos = new FileOutputStream(file.getPath());
			workbook.write(fos);
			fos.close();
			workbook.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		} // try~catch end
		
		return file.getPath();
		
	} // downloadArticles() end
	
} // ExcelArticleService end