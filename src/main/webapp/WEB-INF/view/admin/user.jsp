<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript">
	//分页
	/*  $(function() {
		$(".page-link").click(function() {
			var url=$(this).attr("data");
			alert(url);
			$("#content-wrapper").load(url);
		})
	})  */
	
	function check() {
		var username=$("[name='username']").val();
		var url="/user/selects?username="+username;
		//跳转到url  查询的结果得在index页面中id="content-wrapper"的div中显示
		$("#content-wrapper").load(url);
	}
	
	//改变用户状态
	function locted(id,obj) {
		//获得当前用户的状态
		var locked=$(obj).text()=="改为正常"?0:1;
		//调用方法区修改
		$.post("/user/update",{id:id,locked:locked},function(msg){
			$(obj).text(locked==0?"改为禁用":"改为正常");
			$(obj).attr("class",locked==0?"btn btn-success":"btn btn-secondary")
		
		},"json")
	}
	
	//分页
	function goPage(pageNum){
		var url="/user/selects?pageNum="+pageNum+"&"+$("#f1").serialize();
		$("#content-wrapper").load(url);
		
	}
</script>
</head>
<body>
<div style="padding: 0 10px 0 10px">

	<div>
		<form id="f1">
		姓名:
		<input type="text" name="username" value="${username}"/>
		<button type="button" class="btn btn-success" onclick="check()">搜索</button>
		</form>
	</div>
	<br>

	<table class="table table-bordered">
		<tr>
				<td>序号</td>
				<td>用户名</td>
				<td>昵称</td>
				<td>性别</td>
				<td>生日</td>
				<td>注册日期</td>
				<td>修改日期</td>
				<td>状态</td>
			</tr>
			<c:forEach items="${list }" var="u" varStatus="i">
				<tr>
					<td>${i.index+1 }</td>
					<td>${u.username }</td>
					<td>${u.nickname }</td>
					<td>${u.gender}</td>
					<td>${u.birthday }</td>
					<td>${u.created}</td>
					<td>${u.updated}</td>
					<td>
						<c:if test="${u.locked==0}">
						<button type="button" class="btn btn-success" onclick="locted(${u.id},this)">改为禁用</button>
							
						</c:if>
						
						<c:if test="${u.locked==1}">
						<button type="button" class="btn btn-secondary" onclick="locted(${u.id},this)">改为正常</button>
							
						</c:if>
					</td>
				</tr>


			</c:forEach>
			
	</table>
	<!-- 引入分页页面 -->
	<jsp:include page="/WEB-INF/view/public/page.jsp"></jsp:include>
	
</div>
</body>
</html>