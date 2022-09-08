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
		<div class="card">
			<div class="card-header font-weight-bold h5">로그인</div>
			<div class="card-body">
				<form action="${contextPath}/member/login" method="POST" id="loginForm">
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="memberId">아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력하세요.">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label" for="memberPassword">비밀번호</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="memberPassword" name="memberPassword" placeholder="비밀번호를 입력하세요.">
						</div>
					</div>
					<c:if test="${not empty requestScope.loginFailMsg}">
						<p class="text-danger">${requestScope.loginFailMsg}</p>
					</c:if>
					
					<p class="text-danger">
						SPRING_SECURITY_LAST_EXCEPTION : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					</p>
				</form>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary" onclick="document.getElementById('loginForm').submit()">로그인</button>
				<button type="button" class="btn btn-secondary" onclick="document.getElementById('loginForm').reset()">취소</button>
			</div>
		</div>

	</div>
</body>
</html>
