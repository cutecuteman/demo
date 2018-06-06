<%@page import="com.etc.entity.Users"%>
<%@page import="com.etc.entity.Article"%>
<%@page import="java.util.List"%>
<%@page import="com.etc.service.impl.ArticleSerivceImpl"%>
<%@page import="com.etc.service.ArticleSerivce"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>CMS后台管理-MVC</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/dist/css/bootstrap.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="../../assets/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/mvc/dashboard.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${pageContext.request.contextPath}/dist/js/ie-emulation-modes-warning.js"></script>

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
				<a class="navbar-brand" href="#">CMS系统后台管理-MVC</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">仪表盘</a></li>
					<li><a href="#">设置</a></li>
					<li><a href="#">个人信息</a></li>
					<li><a href="#">帮助</a></li>
				</ul>
				<form class="navbar-form navbar-right" method="get">
					<input type="text" id="searchUserName" class="form-control" placeholder="Search..." value="${userNameLike==null?'':userNameLike}">
					<input type="button" id="btnSearch" class="form-control" value="搜索">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a
						href="${pageContext.request.contextPath}/ArticleController?op=query">文章管理 <span
							class="sr-only">(current)</span></a></li>
					<li><a href="mvc/addArticle.jsp">新增文章</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">用户管理</a></li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/UsersController?op=query">用户列表</a></li>
					<li><a href="">One more nav</a></li>
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

				<h2 class="sub-header">用户列表</h2>
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>编号</th>
								<th>用户名</th>
								<th>用户状态</th>
								<th>用户等级</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>

							<c:if test="${pd.data!=null}">
								<c:forEach items="${pd.data}" var="user">
									<tr>
										<td>${user.userId}</td>
										<td>${user.userName}</td>
										<td>${user.userState == 1 ? "正常" : "禁用"}</td>
										<td>${user.userLevel== 1 ? "普通用户" : "管理员"}</td>
										<td><a href="#">修改</a><a href="#">删除</a></td>
									</tr>
								</c:forEach>
							</c:if>
							


						</tbody>
					</table>

					<!--
            	作者：offline
            	时间：2018-05-10
            	描述：分页的导航
            -->

					<div class="col-md-12 column text-center">
						<ul class="pagination">
							<li><a href="javascript:void(0)" id="prePage">Prev</a></li>
							<c:forEach  begin="1" end="${pd.totalPage}" var="index">
								<c:if test="${index==pd.page}">
									<li class="active"> <a href="javascript:void(0)" class="pageNo">${index}</a></li>
								</c:if>
								<c:if test="${pd.page!=index}">
									<li > <a href="javascript:void(0)" class="pageNo" >${index}</a></li>
								</c:if>
							</c:forEach>
							<li><a href="javascript:void(0)" id="nextPage">Next</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	
		<!--  < script>src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->
	<script>
		window.jQuery
				|| document
						.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="${pageContext.request.contextPath}/dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="${pageContext.request.contextPath}/dist/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="${pageContext.request.contextPath}/dist/js/ie10-viewport-bug-workaround.js"></script>
	<script type="text/javascript">
		/* function jumpPage(page){
			location.href="UsersController?op=queryByPage&page="+page;
		}
		function jumpPageNextPage(page){
			if(page>${pd.totalPage}){
				page=${pd.totalPage};
			}
			location.href="UsersController?op=queryByPage&page="+page;
		}
		function jumpPagePrevPage(page){
			if(page<1){
				page=1;
			}
			location.href="UsersController?op=queryByPage&page="+page;
		} */
	</script>
	<script type="text/javascript" src="https://cdn.bootcss.com/jquery/2.1.1/jquery.js"></script>
	<script>
		$(function(){
			if(${pd.page >= pd.totalPage}){
				$("#nextPage").css("color","gray");
				$("#nextPage").css("pointer-events","none");
			}
			if(${pd.page <= 1}){
				$("#prePage").css("color","gray");
				$("#prePage").css("pointer-events","none");
			}
			$(".pageNo").click(function() {
				location.href="UsersController?op=queryByPage&page="+$(this).text()+"&userNameLike="+$("#searchUserName").val();
			});				
			$("#nextPage").click(function() {
				location.href="UsersController?op=queryByPage&page="+${pd.page+1}+"&userNameLike="+$("#searchUserName").val();
			});
			$("#prePage").click(function() {
				location.href="UsersController?op=queryByPage&page="+${pd.page-1}+"&userNameLike="+$("#searchUserName").val();
			});
		
		$("#btnSearch").click(function(){
			var userNameLike=$("#searchUserName").val();
			location.href="UsersController?op=queryByPage&userNameLike="+userNameLike;

			});
		});
	</script>
	
</body>

</html>