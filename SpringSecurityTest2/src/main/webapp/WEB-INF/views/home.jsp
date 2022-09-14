<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="principal" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<!DOCTYPE html>
<html>
<head>
<title>HOME</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp" />
	<div class="container text-center mt-5 mb-5">
    	<h1>Spring Security2</h1>
    </div>
    <div class="container text-center col-2">
    	<sec:authorize access="isAuthenticated()">
	    	<p>
	            User : ${principal.memberVO.memberId}
	            <br>
	            Role(s) : ${principal.memberVO.memberRole}
	        </p><br>
    	</sec:authorize>
        <a href="${contextPath}/all" role="button" class="btn btn-outline-secondary btn-block">ALL</a>
        <a href="${contextPath}/memberOnly" role="button" class="btn btn-outline-secondary btn-block">MEMBER</a>
        <a href="${contextPath}/managerOnly" role="button" class="btn btn-outline-secondary btn-block">MANAGER</a>
        <a href="${contextPath}/adminOnly" role="button" class="btn btn-outline-secondary btn-block">ADMIN</a>
    </div>
</body>
</html>