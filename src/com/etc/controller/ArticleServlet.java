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
		// ������Ӧ response setContentType ���ݵ����� charset=UTF-8 ��Ӧ�ı�������
		response.setContentType("text/html;charset=UTF-8");

		// �õ�һ��PrintWriter������Խ�������ʾ�������һ��
		PrintWriter out = response.getWriter();

		out.write("<!DOCTYPE html>\r\n");
		out.write("<html lang=\"en\">\r\n");
		out.write("<head>\r\n");
		out.write("<meta charset=\"utf-8\">\r\n");

		out.write("<title>CMS��̨</title>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		// ����service
		ArticleSerivce as = new ArticleSerivceImpl();

		List<Article> list = as.getArticles();

		// ������ ҳ��������list

		out.write("<table border='1' width='70%'>");

		out.write("<tr><th>���</th><th>����</th><th>����</th><th>����</th><th>����ʱ��</th></tr>");

		for (Article article : list) {

			// ֱ��ʹ�����涨���out
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
