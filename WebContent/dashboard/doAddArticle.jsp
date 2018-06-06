<%@page import="com.etc.entity.Article"%>
<%@page import="com.etc.service.impl.ArticleSerivceImpl"%>
<%@page import="com.etc.service.ArticleSerivce"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 接收表单提交过来的信息 -->
<%
	//设置请求的编码 ,位置应该在request.getParameter之前
	request.setCharacterEncoding("utf-8");

	//jsp中的对象(9个)

	//request.getParameter("articleTitle") 括号内的参数为表单元素名字;
	//返回值是一个字符串类型
	//文章的标题
	String articleTitle = request.getParameter("articleTitle");
	//文章的内容
	String articleContent = request.getParameter("articleContent");
	//文章的作者
	String articleAuthor = request.getParameter("articleAuthor");

	Article article = new Article(articleTitle, articleContent, articleAuthor);

	//将得到信息 提交给数据库.... ,但是这里不是直接访问数据库,我们是有套路的;
	//
	ArticleSerivce as = new ArticleSerivceImpl();

	//调用方法
	boolean flag = as.addArticle(article);

	//简单处理 提示
	//用之前js来提示
	if (flag) {
		out.println("<script>alert('增加成功');location.href='showArticles.jsp'</script>");
	}
	else
	{
		out.println("<script>alert('增加失败');location.href='addArticle.jsp'</script>");
	}
%>

