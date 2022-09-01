<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp" />
	<div class="container pt-4">
		<div style="padding: 16px;">
			<h3><strong>로그인</strong></h3>
			<br>
			<form action="${contextPath}/member/login" method="POST">
				<div class="form-group">
					<label for="memberId">아이디</label>
					<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력하세요.">
				</div>
				<div class="form-group">
					<label for="memberPassword">비밀번호</label>
					<input type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="비밀번호를 입력하세요.">
				</div>
				<hr>
				<button type="submit" class="btn btn-primary">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>
