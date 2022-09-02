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
</head>
<body>
	<c:import url="/WEB-INF/views/common/header.jsp" />
	<div class="container pt-4">
		<div class="card">
			<div class="card-header font-weight-bold h5">프로필 편집</div>
			<div class="card-body">
				<form action="${contextPath}/member/update" method="POST" id="updateForm">
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">아이디</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-plaintext" name="memberId" value="${principal.memberVO.memberId}" readonly>
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">이름</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="memberEmail" value="${principal.memberVO.memberName}">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">이메일</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="memberEmail" value="${principal.memberVO.memberEmail}">
						</div>
					</div>
					<div class="form-group row">
						<label class="col-sm-2 col-form-label">권한</label>
						<div class="col-sm-10">
							<input type="text" class="form-control-plaintext" value="" readonly>
						</div>
					</div>
				</form>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary" onclick="document.getElementById('updateForm').submit()">수정하기</button>
			</div>
		</div>
	</div>
</body>
</html>