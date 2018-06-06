package com.etc.dao.impl;

import java.util.List;

import com.etc.dao.ArticleDao;
import com.etc.entity.Article;
import com.etc.util.DBUtil;

public class ArticleDaoImpl implements ArticleDao {

	// ²éÑ¯ËùÓÐarticle
	@SuppressWarnings("unchecked")
	public List<Article> queryArticles() {
		return (List<Article>) DBUtil.select("select * from article", Article.class);

	}

	@Override
	public boolean addArticle(Article article) {
		// TODO Auto-generated method stub
		return DBUtil.execute("insert into article values(null,?,?,?,now())", article.getArticleTitle(),
				article.getArticleContent(), article.getArticleAuthor()) > 0;
	}

	@Override
	public boolean delArticleById(int articleId) {
		// TODO Auto-generated method stub
		return DBUtil.execute("delete from article where articleId=?", articleId) > 0;
	}

	@Override
	public List<Article> queryArticlesLike(String articleLike) {
		// TODO Auto-generated method stub
		return (List<Article>) DBUtil.select("select * from article where articleTitle like ? or articleContent like ?",
				Article.class, "%" + articleLike + "%", "%" + articleLike + "%");
	}
	
	@Override
	public List<Article> queryTopArticlesLike(String articleLike) {
		// TODO Auto-generated method stub
		return (List<Article>) DBUtil.select("select * from article where articleTitle like ? or articleContent like ? order by articleDate desc limit 3",
				Article.class, "%" + articleLike + "%", "%" + articleLike + "%");
	}

}
