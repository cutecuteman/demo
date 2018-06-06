<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    System.out.println(contextPath);
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

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet">



<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/dist/css/signin.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
 -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">

		<form class="form-signin"
			action="${pageContext.request.contextPath}/UsersController?op=login"
			method="post">
			<h2 class="form-signin-heading">请登录</h2>
			<label for="userName" class="sr-only">用户名</label> 
			<input type="text" id="userName" class="form-control" placeholder="用户名" name="userName" required autofocus> 
			<span id="userNameMsg" style="display:inline-block ;color:#C80000"></span>
			
			<label for="userPwd" class="sr-only">密码</label>
			<input type="password" id="userPwd" name="userPwd"
				class="form-control" placeholder="密码" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我(预留功能)
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

	</div>
	<!-- /container -->

 
	
	 <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
     <script type="text/javascript">
    	
			$(function () {
				$("#userName").blur(function () {
					
				/* });
				$("#btn").click(function () { */
					 $.post("${pageContext.request.contextPath}/UsersController?op=queryUser","userName="+$("#userName").val(),function (data,status) {
						console.log(data+","+status);
						//alert(data);
						 //$("#result").html(data);
						$("#userNameMsg").html(data);
					}); 
					/* $.post("UsersController",function (data,status) {
						
					}); */
				});
			});

    </script>
</body>
</html>
