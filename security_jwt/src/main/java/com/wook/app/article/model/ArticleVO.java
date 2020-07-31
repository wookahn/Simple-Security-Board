package com.wook.app.article.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVO {

	private int no, writer, views, articleGroup;
	private String title, content, id, use;
	private Timestamp regdate;
	
} // AritlceVO end