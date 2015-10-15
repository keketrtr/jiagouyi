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
			var totalMoney = $("#totalMoney").val();
			var saveMoney = $("#saveMoney").val();
			var state = $("#state").val();
			var json = '{"totalMoney":"'+totalMoney+'","saveMoney":"'+saveMoney+'","state":"'+state+'"}';
			window.location.href = "${pageContext.request.contextPath}/order/toList?queryJsonStr="+json;
		});
	});
</script>

	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="6" align="center">订单查询</td>
		</tr>
		<tr>
			<td>总金额</td>
			<td><input type="text" name="totalMoney" id="totalMoney" /></td>
			<td>节省金额</td>
			<td><input type="text" name="saveMoney" id="saveMoney" /></td>
			<td>状态</td>
			<td><input type="text" name="state" id="state" /></td>
		</tr>
		<tr>
			<td colspan="6" align="center"><input id="btn_query" type="button" value="查询" /></td>
		</tr>
	</table>
</body>
</html>