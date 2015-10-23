<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/application.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
</head>
<body>

	<table width="70%" align="center" border="1">
		<tr>
			<th>客户编号</th>
			<th>商品编号</th>
			<th>购买数量</th>
		</tr>
		<c:forEach items="${page.result}" var="m">
			<tr>
				<td>${m.customerUuid }</td>
				<td>${m.goodUuid }</td>
				<td>${m.buyNum }</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3">
				<a id="btn_order" href="${pageContext.request.contextPath}/order">提交订单</a>
			</td>
		</tr>
	</table>

</body>
</html>