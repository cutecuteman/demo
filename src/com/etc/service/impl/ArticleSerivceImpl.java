package com.etc.service.impl;

import java.util.List;

import com.etc.dao.ArticleDao;
import com.etc.dao.impl.ArticleDaoImpl;
import com.etc.entity.Article;
import com.etc.service.ArticleSerivce;

/**
 * 文章的服务实现类
 * 
 * @author Administrator
 *
 */
public class ArticleSerivceImpl implements ArticleSerivce {

	ArticleDao ad = new ArticleDaoImpl();

	public List<Article> getArticles() {
		return ad.queryArticles();
	}

	public boolean addArticle(Article article) {
		return ad.addArticle(article);
	}

	@Override
	public boolean delArticle(int articleId) {
		// TODO Auto-generated method stub
		return ad.delArticleById(articleId);
	}

	public List<Article> getArticlesLike(String articleLike) {
		return ad.queryArticlesLike(articleLike);
	}

	@Override
	public List<Article> getTopArticlesLike(String articleLike) {
		// TODO Auto-generated method stub
		return ad.queryTopArticlesLike(articleLike);
		
	}

}
