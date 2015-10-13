<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.0.3.min.js"></script>
</head>
<body>
<script type="text/javascript">
	$().ready(function(){
		$("#btn_query").click(function(){
			var name = $("#name").val();
			var imgPath = $("#imgPath").val();
			var description = $("#description").val();
			var json = '{"name":"'+name+'","imgPath":"'+imgPath+'","description":"'+description+'"}';
			window.location.href = "${pageContext.request.contextPath}/goods/toList?queryJsonStr="+json;
		});
	});
</script>

	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="6" align="center">商品查询</td>
		</tr>
		<tr>
			<td>商品名称</td>
			<td><input type="text" name="name" id="name" /></td>
			<td>图片路径</td>
			<td><input type="text" name="imgPath" id="imgPath" /></td>
			<td>描述</td>
			<td><input type="text" name="description" id="description" /></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><input id="btn_query" type="button" value="查询" /></td>
		</tr>
	</table>
</body>
</html>