<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<div class="container-fulid">

	<div style="padding-left: 1000px">




		<!-- 右边登录注册 -->
		<ul class="nav">
			<!-- 如果没有登录  显示登录或者注册的超链接    -->
			<c:if test="${sessionScope.user==null }">
				<li class="nav-item"><a class="nav-link" href="/user/reg">注册</a></li>
				<li class="nav-item"><a class="nav-link" href="/user/login">登录</a></li>


			</c:if>
			<!-- 如果登录了  显示个人中心的超链接 -->
			<c:if test="${sessionScope.user!=null }">
				<li><a class="dropdown-item" href="/my">个人主页</a></li>
				<li><a class="dropdown-item" href="/user/logout">退出</a></li>
			</c:if>




		</ul>

	</div>
</div>