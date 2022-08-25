<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
      <c:if test="${empty sessionScope.member}">
      <ul class="nav navbar-nav navbar-right">
      	<li><a href="${contextPath}/memLoginForm.do"><span class="glyphicon glyphicon-log-in"></span>&nbsp;&nbsp;로그인</a></li>
      	<li><a href="${contextPath}/memRegisterForm.do"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;회원가입</a></li>
      </ul>
      </c:if>
      <c:if test="${!empty sessionScope.member}">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${contextPath}/memUpdateForm.do"><span class="glyphicon glyphicon-wrench"></span>&nbsp;&nbsp;회원정보 수정</a></li>
        <li><a href="${contextPath}/memImageForm.do"><span class="glyphicon glyphicon-picture"></span>&nbsp;&nbsp;사진 등록</a></li>
        <li><a href="${contextPath}/memLogout.do"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;로그아웃</a></li>
        <li>
        	<c:if test="${empty sessionScope.member.memProfile}">
        	<img src="${contextPath}/resources/images/person.png" class="img-circle" style="width : 30px; height: 30px; margin: 10px">
        	</c:if>
        	<c:if test="${!empty sessionScope.member.memProfile}">
        	<img src="${contextPath}/resources/images/person.png" class="img-circle" style="width : 30px; height: 30px; margin: 10px">
        	</c:if>
        	<span style="color: white;">${sessionScope.member.memId} 님
	  			(
  				<c:forEach items="${sessionScope.member.authList}" var="obj">
  					<c:if test="${obj.auth eq 'ROLE_USER'}">U</c:if>
  					<c:if test="${obj.auth eq 'ROLE_MANAGER'}">M</c:if>
  					<c:if test="${obj.auth eq 'ROLE_ADMIN'}">A</c:if>
  				</c:forEach>
	  			)
	  		</span>
        </li>
      </ul>
      </c:if>
    </div>
  </div>
</nav>