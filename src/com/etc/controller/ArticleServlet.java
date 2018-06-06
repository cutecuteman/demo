package com.etc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etc.entity.Article;
import com.etc.service.ArticleSerivce;
import com.etc.service.impl.ArticleSerivceImpl;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet({ "/ArticleServlet", "/as" })
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置响应 response setContentType 内容的类型 charset=UTF-8 响应的编码内容
		response.setContentType("text/html;charset=UTF-8");

		// 得到一个PrintWriter对象可以将内容显示在浏览器一侧
		PrintWriter out = response.getWriter();

		out.write("<!DOCTYPE html>\r\n");
		out.write("<html lang=\"en\">\r\n");
		out.write("<head>\r\n");
		out.write("<meta charset=\"utf-8\">\r\n");

		out.write("<title>CMS后台</title>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		// 调用service
		ArticleSerivce as = new ArticleSerivceImpl();

		List<Article> list = as.getArticles();

		// 遍历并 页面输出输出list

		out.write("<table border='1' width='70%'>");

		out.write("<tr><th>编号</th><th>标题</th><th>内容</th><th>作者</th><th>发布时间</th></tr>");

		for (Article article : list) {

			// 直接使用上面定义的out
			//out.print(article + "<br/>");

			out.write("<tr><td>" + article.getArticleId() + "</td><td>" + article.getArticleTitle() + "</td><td>"
					+ article.getArticleContent() + "</td><td>" + article.getArticleAuthor() + "</td><td>"
					+ article.getArticleDate() + "</td></tr>");

		}
		out.write("</table>");
		out.write("</body>\r\n");
		out.write("</html>\r\n");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
