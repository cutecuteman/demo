package com.etc.service;

import java.util.List;

import com.etc.entity.Article;

public interface ArticleSerivce {

	public List<Article> getArticles();
	
	public boolean addArticle(Article article);
	
	public boolean delArticle(int articleId);
	
	public List<Article> getArticlesLike(String articleLike);
	
	public List<Article> getTopArticlesLike(String articleLike);

}
