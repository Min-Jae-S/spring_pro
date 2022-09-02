<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
			<div class="card-header font-weight-bold">회원 목록</div>
			<div class="card-body">
				<table class="table table-bordered">
					<thead class="thead-light text-center">
						<tr>
							<th>No</th>
							<th>아이디</th>
							<th>이름</th>
							<th>이메일</th>
							<th>권한</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="memberVO">
							<tr>
								<td>${memberVO.memberIdx}</td>
								<td>${memberVO.memberId}</td>
								<td>${memberVO.memberName}</td>
								<td>${memberVO.memberEmail}</td>
								<td>
									<c:if test="${memberVO.memberRole eq 'ROLE_USER'}">일반</c:if>
									<c:if test="${memberVO.memberRole eq 'ROLE_MANAGE'}">매니저</c:if>
									<c:if test="${memberVO.memberRole eq 'ROLE_ADMIN'}">관리자</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>