<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>KindEditor JSP</title>
<link rel="stylesheet"
	href="/resource/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resource/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
 
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
<script src="/resource/js/jquery-3.2.1.js"></script>

<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
	function query() {

		//alert( $("[name='content1']").attr("src"))
	}

	$(function() {
		//加载所有的栏目追加到下拉框
		$.post("/article/selectsChannel", {}, function(json) {
			for ( var i in json) {
				$("[name='channelId']").append(
						"<option value='"+json[i].id+"'>" + json[i].name
								+ "</option>");
			}
		}, "json");

		//只要选中一个栏目自动触发加载此栏目下的所有的分类
		$("[name='channelId']").change(
				function() {
					$.post("/article/selectsCategory", {
						id : $(this).val()
					}, function(json) {
						//追加之前情清空
						$("[name='categoryId'] option:gt(0)").remove();
						for ( var i in json) {
							$("[name='categoryId']").append(
									"<option value='"+json[i].id+"'>"
											+ json[i].name + "</option>");
						}
					}, "json");
				})

	})

	//发布
	function add() {
		//ajax提交    上传   副文本编辑器的内容
		//序列化表单数据 带文件
		var formData = new FormData($("#f1")[0]);
		//封装富文本中的html内容
		formData.append("content", editor1.html());

		
		//ajax提交
		$.ajax({
			// 告诉jQuery不要去处理发送的数据
			processData : false,
			// 告诉jQuery不要去设置Content-Type请求头
			contentType : false,
			url : "/article/add",
			data : formData,
			type : "post",
			success : function(msg) {
				if (msg) {
					alert("发布完成");
					$("#center").load("article/selectArticle");
				} else {
					alert("发布失败");
				}
			}

		})
	}
</script>
</head>
<body>
	<%=htmlData%>
	<form name="example" id="f1">

		文章标题: <input type="text" name="title" class="form-control" /><br>


		<textarea name="content1" cols="100" rows="8"
			style="width: 920px; height: 200px; visibility: hidden;">
		<%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<div class="form-inline">
			栏目:<select name="channelId" class="form-control">
				<option>---请选择栏目---</option>
			</select> 分类:<select name="categoryId" class="form-control">
				<option>---请选择分类---</option>
			</select>
		</div>
		<br> 标题图片: <input type="file" name="file" /> <br> <br>
		<button type="button" class="btn btn-primary" onclick="add()">发布</button>
	</form>
</body>
</html>
<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%>