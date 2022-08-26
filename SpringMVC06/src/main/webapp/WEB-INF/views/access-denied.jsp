<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC06</title>
</head>
<body>
	<h2>Access Denied - You are not authorized to access this resource.</h2>
	<hr>
	<a href="${contextPath}/">Back to Home Page</a>
</body>
</html>