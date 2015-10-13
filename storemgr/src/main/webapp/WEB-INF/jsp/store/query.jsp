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
			var goodsUuid = $("#goodsUuid").val();
			var storeNum = $("#storeNum").val();
			var json = '{"goodsUuid":"'+goodsUuid+'","storeNum":"'+storeNum+'"}';
			window.location.href = "${pageContext.request.contextPath}/store/toList?queryJsonStr="+json;
		});
	});
</script>

	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="4" align="center">库存查询</td>
		</tr>
		<tr>
			<td>商品编号</td>
			<td><input type="text" name="goodsUuid" id="goodsUuid" /></td>
			<td>库存量</td>
			<td><input type="text" name="storeNum" id="storeNum" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input id="btn_query" type="button" value="查询" /></td>
		</tr>
	</table>
</body>
</html>