<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/cart/update" method="post">
	<input type="hidden" name="uuid" value="${m.uuid}" />
	
	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="6" align="center">购物车修改</td>
		</tr>
		<tr>
			<td>客户编号</td>
			<td><input type="text" name="customerUuid" value="${m.customerUuid}" /></td>
			<td>商品编号</td>
			<td><input type="text" name="goodUuid" value="${m.goodUuid}" /></td>
			<td>购买数量</td>
			<td><input type="text" name="buyNum" value="${m.buyNum}" /></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><input type="submit" value="修改" /></td>
		</tr>
	</table>
</form>
</body>
</html>