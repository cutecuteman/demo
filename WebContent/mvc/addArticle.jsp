<%@page import="com.etc.entity.Article"%>
<%@page import="java.util.List"%>
<%@page import="com.etc.service.impl.ArticleSerivceImpl"%>
<%@page import="com.etc.service.ArticleSerivce"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	   String contextPath = request.getContextPath();
	   System.out.println(contextPath);
	%>
<%-- 	<%
		Object u=session.getAttribute("u");
	%>
	<c:if test="${u==null }">
		跳转到登录页面
	</c:if> --%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>CMS后台管理</title>

<!-- Bootstrap core CSS -->
<link href="../dist/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">CMS系统后台管理</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">仪表盘</a></li>
					<li><a href="#">设置</a></li>
					<li><a href="#">个人信息</a></li>
					<li><a href="#">帮助</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a href="<%=contextPath%>/ArticleController?op=query">文章管理 <span class="sr-only">(current)</span></a>
					</li>
					<li class="active"><a href="<%=contextPath%>/mvc/addArticle.jsp">增加文章</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li class="active"><a href="">用户管理</a></li>
					<li><a href="<%=contextPath%>/UsersController?op=query">用户列表</a></li>
					<li><a href="">新增用户</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
				</ul>
				<ul class="nav nav-sidebar hidden">
					<li class="active"><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
				</ul>
			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">增加文章</h2>

				<!-- 
				   action="doAddArticle.jsp"   这个提交给谁处理
				    method="post" 以什么样的方式提交
				 -->
				<form class="form-horizontal" role="form" action="<%=contextPath%>/ArticleController?op=add" method="post">
					<div class="form-group">
						<label for="articleTitle" class="col-sm-2 control-label">文章标题</label>
						<div class="col-sm-8">
							<input type="text" required="required" class="form-control" name="articleTitle" id="articleTitle" />
						</div>
					</div>
					<div class="form-group">
						<label for="articleContent" class="col-sm-2 control-label">文章内容</label>
						<div class="col-sm-8">
							<textarea rows="5" class="form-control" name="articleContent" id="articleContent"></textarea>
						</div>
					</div>

					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">文章类型(扩展)</label>
						<div class="col-sm-2">
							<select class="form-control">
								<option>娱乐</option>
								<option>体育</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="articleAuthor" class="col-sm-2 control-label">文章作者(数据假的)</label>
						<div class="col-sm-2">
							<select class="form-control" name="articleAuthor" id="articleAuthor">
								<option value="小白">小白</option>
								<option value="小红">小红</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-danger">提交</button>
							<button type="button" class="btn btn-success">预览(扩展)</button>
						</div>
					</div>
				</form>



			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="../../assets/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>

</html>