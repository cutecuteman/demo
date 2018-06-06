package com.etc.dao;

import java.util.List;

import com.etc.entity.Article;

public interface ArticleDao {

	// 查询所有article
	public List<Article> queryArticles();

	public boolean addArticle(Article article);

	public boolean delArticleById(int articleId);

	// 模糊查询所有article
	public List<Article> queryArticlesLike(String articleLike);

	// 模糊查询所有article
	public List<Article> queryTopArticlesLike(String articleLike);

}
