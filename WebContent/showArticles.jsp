<%@page import="com.etc.entity.Article"%>
<%@page import="java.util.List"%>
<%@page import="com.etc.service.impl.ArticleSerivceImpl"%>
<%@page import="com.etc.service.ArticleSerivce"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   //java代码
   ArticleSerivce as = new ArticleSerivceImpl();

   //调用其方法
   List<Article> list=as.getArticles();

   for(Article article : list)
   {
	   out.println(article+"<br/>");
   }
%>

</body>
</html>