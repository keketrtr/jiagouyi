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
			var customerUuid = $("#customerUuid").val();
			var goodUuid = $("#goodUuid").val();
			var buyNum = $("#buyNum").val();
			var json = '{"customerUuid":"'+customerUuid+'","goodUuid":"'+goodUuid+'","buyNum":"'+buyNum+'"}';
			window.location.href = "${pageContext.request.contextPath}/cart/toList?queryJsonStr="+json;
		});
	});
</script>

	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="6" align="center">购物车查询</td>
		</tr>
		<tr>
			<td>客户编号</td>
			<td><input type="text" name="customerUuid" id="customerUuid" /></td>
			<td>商品编号</td>
			<td><input type="text" name="goodUuid" id="goodUuid" /></td>
			<td>购买数量</td>
			<td><input type="text" name="buyNum" id="buyNum" /></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><input id="btn_query" type="button" value="查询" /></td>
		</tr>
	</table>
</body>
</html>