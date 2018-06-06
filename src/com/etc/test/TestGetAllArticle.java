package com.etc.test;

import java.util.List;

import com.etc.entity.Article;
import com.etc.service.ArticleSerivce;
import com.etc.service.impl.ArticleSerivceImpl;

public class TestGetAllArticle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArticleSerivce as = new ArticleSerivceImpl();
		
		List<Article> list=as.getArticles();

		for (Article article : list) {
			System.out.println(article);
		}
		
	}

}
