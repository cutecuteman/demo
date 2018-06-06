package com.etc.dao;

import java.util.List;

import com.etc.entity.Article;

public interface ArticleDao {

	// ��ѯ����article
	public List<Article> queryArticles();

	public boolean addArticle(Article article);

	public boolean delArticleById(int articleId);

	// ģ����ѯ����article
	public List<Article> queryArticlesLike(String articleLike);

	// ģ����ѯ����article
	public List<Article> queryTopArticlesLike(String articleLike);

}
