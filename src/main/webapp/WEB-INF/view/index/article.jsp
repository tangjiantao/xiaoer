<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/sb-admin.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	//收藏
	function add(text) {
		//判断用户有没有登录
		//没有登录 去登录页面
		var user = '${sessionScope.user}';
		if (user == "") {
			location.href = "/user/login";

		} else {
			//如果已经登录  进行收藏
			//ajax做收藏   text(title) url
			//获得收藏夹地址  ==当前页面的访问地址
			var url = window.location.href;
			$.post("/favorite/addFavo", {
				text : text,
				url : url
			}, function(msg) {
				if (msg == "-1") {
					alert("url不合法")
				} else if (msg == "0") {
					alert("收藏失败,请重试");
				} else {
					alert("收藏成功");
				//	location.herf="/favorite/selects";
				}
			}, "json");

		}

	}
</script>
</head>
<body>
	<center>
		<div class="container">

			<h1>${article.title }</h1>
			${article.user.username }
			<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd" />
			${article.content }<br>
			<br>

			<button type="button" class="btn btn-info"
				onclick="add('${article.title }')">收藏</button>
		</div>

	</center>

</body>
</html>
