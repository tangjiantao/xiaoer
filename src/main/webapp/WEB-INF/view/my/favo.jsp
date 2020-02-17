<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        	    	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<div style="padding-top: 10px">
<h2>我的收藏夹:</h2>
<hr style="border-color: red">
		<ul class="list-unstyled">
			<c:forEach items="${info.list }" var="a">
			<li class="media">
				<div class="media-body text-center">
					<h5 class="mt-0 mb-1 "><a href="${a.url }" style="font-size: 15px;">${a.text }</a></h5><br>
					时间:${a.created }&nbsp;&nbsp;&nbsp;&nbsp;<a>删除</a>
				</div></li>
				<hr>
				</c:forEach>
				<jsp:include page="/WEB-INF/view/public/page.jsp"></jsp:include>
			
		</ul>
	</div>



</body>
</html>