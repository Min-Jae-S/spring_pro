<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="principal" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>Spring Security</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
	td:first-child {
		width: 20%;
		font-weight: bold;
		text-align: center;
		background-color: #fafaaa;
	}
</style>
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp" />
	<div class="container pt-4">
		<div style="padding: 16px;">
			<h3><strong>프로필 관리</strong></h3>
			<br>
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>${principal.memberVO.memberId}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${principal.memberVO.memberName}</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>${principal.memberVO.memberEmail}</td>
				</tr>
				<tr>
					<td>권한</td>
					<td>
						<sec:authorize access="hasRole('USER')">일반</sec:authorize>
						<sec:authorize access="hasRole('MANAGER')">매니저</sec:authorize>
						<sec:authorize access="hasRole('ADMIN')">관리자</sec:authorize>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>