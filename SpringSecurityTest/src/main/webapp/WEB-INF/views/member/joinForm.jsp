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
			<div class="card-header font-weight-bold h5">회원가입</div>
			<div class="card-body">
				<form action="${contextPath}/member/join" method="POST" id="joinForm">
					<div class="form-group row mb-4">
						<label class="col-sm-2 col-form-label" for="memberId">아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="memberId" name="memberId" placeholder="아이디를 입력하세요.">
						</div>
					</div>
					<div class="form-group row mb-4">
						<label class="col-sm-2 col-form-label" for="memberPassword">비밀번호</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="memberPassword" name=memberPassword placeholder="비밀번호를 입력하세요.">
						</div>
					</div>
					<div class="form-group row mb-4">
						<label class="col-sm-2 col-form-label" for="memberName">이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="memberName" name="memberName" placeholder="이름을 입력하세요.">
						</div>
					</div>
					<div class="form-group row mb-4">
						<label class="col-sm-2 col-form-label" for="memberEmail">이메일</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="memberEmail" name="memberEmail" placeholder="이메일을 입력하세요.">
						</div>
					</div>
					<div class="form-group row align-items-center">
						<label class="col-sm-2 col-form-label">권한</label>
						<div class="col-sm-10">
							<div class="form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="form-check-input" name="memberRole" value="ROLE_MEMBER" checked>일반회원
								</label>
							</div>
							<div class="form-check-inline">
								<label class="form-check-label"> 
									<input type="radio" class="form-check-input" name="memberRole" value="ROLE_MANAGER">매니저
								</label>
							</div>
							<div class="form-check-inline">
								<label class="form-check-label">
									<input type="radio" class="form-check-input" name="memberRole" value="ROLE_ADMIN">관리자
								</label>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary" onclick="document.getElementById('joinForm').submit()">회원가입</button>
			</div>
		</div>
	</div>
</body>
</html>
