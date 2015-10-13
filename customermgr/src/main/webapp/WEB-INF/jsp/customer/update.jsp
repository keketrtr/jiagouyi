<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/customer/update" method="post">
	<input type="hidden" name="uuid" value="${m.uuid}" />
	<input type="hidden" name="registerTime" value="${m.registerTime}" />
	
	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="4" align="center">客户修改</td>
		</tr>
		<tr>
			<td>客户编号</td>
			<td><input type="text" name="customerId" value="${m.customerId}" /></td>
			<td>客户密码</td>
			<td><input type="text" name="pwd" value="${m.pwd}" /></td>
		</tr>
		<tr>
			<td>显示名称</td>
			<td><input type="text" name="showName" value="${m.showName}" /></td>
			<td>真实名称</td>
			<td><input type="text" name="trueName" value="${m.trueName}" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="submit" value="修改" /></td>
		</tr>
	</table>
</form>
</body>
</html>