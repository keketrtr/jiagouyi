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
</head>
<body>

<table width="70%" align="center">
	<tr>
		<td colspan="4" align="center"><a href="${pageContext.request.contextPath}/toCart">查看购物车</a></td>
	</tr>
<c:forEach items="${page.result}" var="m" varStatus="s">
	<c:if test="${s.index % 4 == 0 }">
	<tr>
	</c:if>
		<td>
			<a href="${pageContext.request.contextPath}/toGoodsDesc/${m.uuid }">
				<table>
					<tr>
						<td><img width="120" height="100" alt="暂无图片" src="${pageContext.request.contextPath}/${m.imgPath }"></td>
						<td>${m.description }</td>
					</tr>
					<tr>
						<td colspan="2" align="center">${m.name }</td>
					</tr>
				</table>
			</a>
		</td>
	<c:if test="${s.index % 4 == 3 }">
	</tr>
	</c:if>
</c:forEach>
</table>

</body>
</html>