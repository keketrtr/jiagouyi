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
			<td colspan="4">
				<a href="${pageContext.request.contextPath}/goods/toQuery">转到查询</a>
				<a href="${pageContext.request.contextPath}/goods/toAdd">转到新增</a>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center">商品列表</td>
		</tr>
		<tr>
			<td>商品名称</td>
			<td>图片路径</td>
			<td>描述</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${page.result}" var="m">
			<tr>
				<td>${m.name}</td>
				<td>${m.imgPath}</td>
				<td>${m.description}</td>
				<td>
					<a href="${pageContext.request.contextPath}/goods/toUpdate/${m.uuid}">修改</a>
					<a href="${pageContext.request.contextPath}/goods/toDelete/${m.uuid}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4" align="center">
				<input type="hidden" id="queryJsonStr" value='${wm.queryJsonStr}' />
				<myTag:page page="${page}"></myTag:page>
			</td>
		</tr>
	</table>
</body>
</html>