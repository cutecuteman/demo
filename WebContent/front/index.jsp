<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("basePath", basePath);
%>
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

<title>CMS系统前台</title>

<!-- Bootstrap core CSS ${pageContext.request.contextPath}/ -->
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/front/jumbotron.css"
	rel="stylesheet">

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
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">JJA1801~CMS网站前台 </a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<c:choose>

					<c:when test="${sessionScope.frontusers==null}">

						<form class="navbar-form navbar-right"
							action="${pageContext.request.contextPath}/UsersController"
							method="post">
							<div class="form-group">
								<input type="text" placeholder="用户名" name="userName"
									class="form-control">

							</div>
							<div class="form-group">
								<input type="password" placeholder="密码" name="userPwd"
									class="form-control">
							</div>
							<input type="hidden" name="op" value="login">
							<button type="submit" class="btn btn-success">登录</button>

						</form>
					</c:when>
					<c:when test="${sessionScope.frontusers!=null}">
						<div id="navbar" class="navbar-collapse collapse">
							<ul class="nav navbar-nav navbar-right">
								<li><a href="#">${sessionScope.frontusers.userName}</a></li>
								<li><a href="#">个人中心</a></li>
								<li><a href="UsersController?op=frontExit">退出</a></li>
							</ul>
						</div>
					</c:when>
				</c:choose>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Hello, world!</h1>
			<p>This is a template for a simple marketing or informational
				website. It includes a large callout called a jumbotron and three
				supporting pieces of content. Use it as a starting point to create
				something more unique.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="#" role="button">Learn
					more &raquo;</a>
			</p>
		</div>
	</div>
	<c:if test="${requestScope.list==null}">
		<!-- 稍后 再改改 -->
		<%-- <c:redirect
			url="http://localhost:7777/cms1.4/ArticleController?op=frontQueryLike"></c:redirect> --%>
		<!-- 如下跳转方式 -->
			<c:redirect url="${basePath}ArticleController?op=frontQueryLike"></c:redirect>
	</c:if>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<c:if test="${requestScope.list!=null}">

				<c:forEach items="${requestScope.list}" var="article">
					<div class="col-md-4">
						<h2>${article.articleTitle}</h2>
						<p>${article.articleContent}</p>
						<p>
							<a class="btn btn-default" href="#" role="button">查看详细
								&raquo;</a>
						</p>
					</div>

				</c:forEach>
			</c:if>
		</div>

		<hr>

		<footer>
			<p>&copy; 2018 JJA1801, Inc.</p>
		</footer>
	</div>
	<!-- /container -->


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
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
