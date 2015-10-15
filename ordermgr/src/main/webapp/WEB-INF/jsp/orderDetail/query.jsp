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
			var orderUuid = $("#orderUuid").val();
			var goodsUuid = $("#goodsUuid").val();
			var orderNum = $("#orderNum").val();
			var price = $("#price").val();
			var money = $("#money").val();
			var saveMoney = $("#saveMoney").val();
			var json = '{"orderUuid":"'+orderUuid+'","goodsUuid":"'+goodsUuid+'","orderNum":"'+orderNum+'",'+
				'"price":"'+price+'","money":"'+money+'","saveMoney":"'+saveMoney+'"}';
			window.location.href = "${pageContext.request.contextPath}/orderDetail/toList?queryJsonStr="+json;
		});
	});
</script>

	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="6" align="center">子订单查询</td>
		</tr>
		<tr>
			<td>订单编号</td>
			<td><input type="text" name="orderUuid" id="orderUuid" /></td>
			<td>商品编号</td>
			<td><input type="text" name="goodsUuid" id="goodsUuid" /></td>
			<td>订购数量</td>
			<td><input type="text" name="orderNum" id="orderNum" /></td>
		</tr>
		<tr>
			<td>单价</td>
			<td><input type="text" name="price" id="price" /></td>
			<td>总金额</td>
			<td><input type="text" name="money" id="money" /></td>
			<td>节省金额</td>
			<td><input type="text" name="saveMoney" id="saveMoney" /></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><input id="btn_query" type="button" value="查询" /></td>
		</tr>
	</table>
</body>
</html>