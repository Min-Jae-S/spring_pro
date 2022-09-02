<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="principal" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" />
<script>

</script>
<div class="container mb-4">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="${contextPath}/">
			<img class="d-inline-block align-top mr-2" src="${contextPath}/resources/image/mokoko.png" width="30" height="30">HOME
		</a>
		<ul class="navbar-nav ml-auto">
			<sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
				<li class="nav-item"><a class="nav-link" href="${contextPath}/member/memberList">회원목록</a></li>
			</sec:authorize>

			<sec:authorize access="isAnonymous()">
				<li class="nav-item"><a class="nav-link" href="${contextPath}/member/joinForm">회원가입</a></li>
				<li class="nav-item"><a class="nav-link" href="${contextPath}/member/loginForm">로그인</a></li>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item"><a class="nav-link" href="${contextPath}/member/memberInfo">프로필 관리</a></li>
				<li class="nav-item"><a class="nav-link" href="${contextPath}/member/logout">로그아웃</a></li>
				<li class="nav-item">
					<span class="nav-link text-white">${principal.memberVO.memberId}(<!--  
						--><sec:authorize access="hasRole('USER')">일반</sec:authorize><!-- 
						--><sec:authorize access="hasRole('MANAGER')">매니저</sec:authorize><!-- 
						--><sec:authorize access="hasRole('ADMIN')">관리자</sec:authorize>)<!-- 
					--></span>
				</li>
			</sec:authorize>
		</ul>
	</nav>
</div>