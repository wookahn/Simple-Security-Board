package com.wook.app.common.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.wook.app.article.model.ArticleVO;

public class ResultRowDataHandler implements ResultHandler<ArticleVO> {

	private SXSSFWorkbook workbook;
	private SXSSFSheet sheet;
	private SXSSFRow row;
	private SXSSFCell cell;
	
	private int rownum, idx;
	
	public ResultRowDataHandler(SXSSFWorkbook workbook) {
		this.workbook = workbook;
		
		sheet = this.workbook.createSheet("Articles");
		createTitle();
	} // constructor end
	
	private void createTitle() {
		row = sheet.createRow(rownum);

		List<String> cellTitles = new ArrayList<String>(Arrays.asList(new String[] {
				"번호", "제목", "본문", "작성자", "조회수", "작성일"
		}));
		
		for (String cellTitle : cellTitles) {
			cell = row.createCell(idx++);
			cell.setCellValue(cellTitle);
		} // for end
		
	} // createTitle() end
	
	@Override
	public void handleResult(ResultContext<? extends ArticleVO> resultContext) {

		idx = 0;
		
		ArticleVO article = resultContext.getResultObject();
		row = sheet.createRow(++rownum);

		List<Object> cellValues = new ArrayList<Object>(Arrays.asList(new Object[] {
				article.getNo(), article.getTitle(), article.getContent(), article.getId(),
				article.getViews(), article.getRegdate()
		}));
		
		for (Object cellValue : cellValues) {
			
			cell = row.createCell(idx++);
			
			if (idx == 1) {
				cell.setCellValue(article.getNo() == article.getArticleGroup() ? cellValue.toString() : "└");
			} else {
				cell.setCellValue(cellValue.toString());
			} // if~else end
			
		} // for end
		
	} // handleResult() end
	
} // ResultRowDateHandler end