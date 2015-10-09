<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/application.js"></script>
</head>
<body>
	<table width="100%" border="1" cellpadding="0" cellspacing="1">
		<tr>
			<td colspan="6" align="center">客户列表</td>
		</tr>
		<tr>
			<td>客户编号</td>
			<td>显示名称</td>
			<td>真实姓名</td>
			<td>客户密码</td>
			<td>注册时间</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.result}" var="m">
			<tr>
				<td>${m.customerId}</td>
				<td>${m.showName}</td>
				<td>${m.trueName}</td>
				<td>${m.pwd}</td>
				<td>${m.registerTime}</td>
				<td>
					<a href="${pageContext.request.contextPath}/customer/toUpdate/${m.uuid}">修改</a>
					<a href="${pageContext.request.contextPath}/customer/toDelete/${m.uuid}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="center">
				<myTag:page page="${page}"></myTag:page>
			</td>
		</tr>
	</table>
</body>
</html>