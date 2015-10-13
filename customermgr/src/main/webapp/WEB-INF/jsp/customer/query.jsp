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
			var customerId = $("#customerId").val();
			var pwd = $("#pwd").val();
			var showName = $("#showName").val();
			var trueName = $("#trueName").val();
			var json = '{"customerId":"'+customerId+'","pwd":"'+pwd+'","showName":"'+showName+'","trueName":"'+trueName+'"}';
			window.location.href = "${pageContext.request.contextPath}/customer/toList?queryJsonStr="+json;
		});
	});
</script>

	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="4" align="center">客户查询</td>
		</tr>
		<tr>
			<td>客户编号</td>
			<td><input type="text" name="customerId" id="customerId" /></td>
			<td>客户密码</td>
			<td><input type="password" name="pwd" id="pwd" /></td>
		</tr>
		<tr>
			<td>显示名称</td>
			<td><input type="text" name="showName" id="showName" /></td>
			<td>真实名称</td>
			<td><input type="text" name="trueName" id="trueName" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input id="btn_query" type="button" value="查询" /></td>
		</tr>
	</table>
</body>
</html>