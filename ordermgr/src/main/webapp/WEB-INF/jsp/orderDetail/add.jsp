<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/orderDetail/add" method="post">
	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="6" align="center">子订单新增</td>
		</tr>
		<tr>
			<td>订单编号</td>
			<td><input type="text" name="orderUuid" /></td>
			<td>商品编号</td>
			<td><input type="text" name="goodsUuid" /></td>
			<td>订购数量</td>
			<td><input type="text" name="orderNum" /></td>
		</tr>
		<tr>
			<td>单价</td>
			<td><input type="text" name="price" /></td>
			<td>总金额</td>
			<td><input type="text" name="money" /></td>
			<td>节省金额</td>
			<td><input type="text" name="saveMoney" /></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><input type="submit" value="新增" /></td>
		</tr>
	</table>
</form>
</body>
</html>