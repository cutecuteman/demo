package com.etc.entity;

/**
 * 自定义实体类
 * @author Administrator
 *
 */
public class Article {
	private int articleId;
	private String articleTitle;
	private String articleContent;
	private String articleAuthor;
	private String articleDate;
	
	
	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	public Article(String articleTitle, String articleContent, String articleAuthor) {
		super();
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleAuthor = articleAuthor;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", articleTitle=" + articleTitle + ", articleContent="
				+ articleContent + ", articleAuthor=" + articleAuthor + ", articleDate=" + articleDate + "]";
	}
	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the articleTitle
	 */
	public String getArticleTitle() {
		return articleTitle;
	}
	/**
	 * @param articleTitle the articleTitle to set
	 */
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	/**
	 * @return the articleContent
	 */
	public String getArticleContent() {
		return articleContent;
	}
	/**
	 * @param articleContent the articleContent to set
	 */
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	/**
	 * @return the articleAuthor
	 */
	public String getArticleAuthor() {
		return articleAuthor;
	}
	/**
	 * @param articleAuthor the articleAuthor to set
	 */
	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}
	/**
	 * @return the articleDate
	 */
	public String getArticleDate() {
		return articleDate;
	}
	/**
	 * @param articleDate the articleDate to set
	 */
	public void setArticleDate(String articleDate) {
		this.articleDate = articleDate;
	}

}
