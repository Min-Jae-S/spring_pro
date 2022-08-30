<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="memberUser" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
<c:set var="auth" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities}"/>
<script>
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
	
	function logout() {
		$.ajax({
			url : '${contextPath}/logout',
			type : 'POST',
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			success : function() {
				location.href = '${contextPath}/';
			},
			error : function() {
				alert('error');
			}
		});
	}
</script>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${contextPath}">스프1탄</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${contextPath}">Home</a></li>
        <li><a href="${contextPath}/boardMain.do">게시판</a></li>
        <li><a href="#">Page 2</a></li>
      </ul>

      <sec:authorize access="isAnonymous()"> <!-- 로그인 X -->
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="${contextPath}/memLoginForm.do"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;로그인</a></li>
      	<li><a href="${contextPath}/memRegisterForm.do"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;회원가입</a></li>
      </ul>
      </sec:authorize>
      
      <sec:authorize access="isAuthenticated()"> <!-- 로그인 O -->
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${contextPath}/memUpdateForm.do"><span class="glyphicon glyphicon-wrench"></span>&nbsp;&nbsp;회원정보 수정</a></li>
        <li><a href="${contextPath}/memImageForm.do"><span class="glyphicon glyphicon-picture"></span>&nbsp;&nbsp;사진 등록</a></li>
        <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
        <li>
        	<c:if test="${empty memberUser.member.memProfile}">
        		<img src="${contextPath}/resources/images/person.png" class="img-circle" style="width : 30px; height: 30px; margin: 10px">
        	</c:if>
        	<c:if test="${!empty memberUser.member.memProfile}">
        		<img src="${contextPath}/resources/upload/${memberUser.member.memProfile}" class="img-circle" style="width : 30px; height: 30px; margin: 10px">
        	</c:if>
        	<span style="color: white;">${memberUser.member.memName} 님
	  			(<sec:authorize access="hasRole('ROLE_USER')">U</sec:authorize>
	  			<sec:authorize access="hasRole('ROLE_MANAGER')">M</sec:authorize>
	  			<sec:authorize access="hasRole('ROLE_ADMIN')">A</sec:authorize>)
	  		</span>
        </li>
      </ul>
      </sec:authorize>
    </div>
  </div>
</nav>