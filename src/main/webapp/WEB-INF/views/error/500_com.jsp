<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="stc" value="${pageContext.request.contextPath}/assets" />
<!DOCTYPE html>
<html>
<head>
<title>错误提示</title>
<link href="${stc}/lib/error.css" media="screen" rel="stylesheet">
</head>
<body>
	<div id="error-ctn">
		<div>
			<c:if test="${exception!=null}">
<%-- 				<p>${exception.errorCode}</p> --%>
				<c:choose>
					<c:when test="${exception.message!=null}">
						<p>${exception}</p>
					</c:when>
					<c:otherwise>
						<p>${exception}</p>
					</c:otherwise>
				</c:choose>
			</c:if>
		</div>
	</div>
</body>
</html>