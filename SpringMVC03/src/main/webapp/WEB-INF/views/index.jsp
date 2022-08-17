<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script>
  	$(document).ready(function() {
  		if(${!empty msgType}) {
  			$('#registerSuccessMessageModal').modal('show');
  		}
  	});
  </script>
</head>
<body>
<%-- <jsp:include page="common/header.jsp"/> --%>

<div class="container">
  <c:import url="/WEB-INF/views/common/header.jsp"/>
  <div style="margin-bottom: 15px;">
	  <c:if test="${empty sessionScope.member}">
		  <h3>Spring MVC03</h3>
	  </c:if>
	  <c:if test="${!empty sessionScope.member}">
	  	<c:if test="${empty sessionScope.member.memProfile}">
	  		<img src="${contextPath}/resources/images/person.png" style="width : 50px; height: 50px; margin-right: 10px">
	  	</c:if>
	  	<c:if test="${!empty sessionScope.member.memProfile}">
	  		<img src="${contextPath}/resources/upload/${sessionScope.member.memProfile}" style="width : 50px; height: 50px; margin-right: 10px">
	  	</c:if>
		<label>${sessionScope.member.memId}님 방문을 환영합니다.</label>
	  </c:if>
  </div>
  <div class="panel panel-default">
    <img src="${contextPath}/resources/images/main_mokoko_skyblue.png" style="width: 100%;">
    <div class="panel-body">
	    <ul class="nav nav-pills">
		  <li class="active"><a data-toggle="pill" href="#home">Home</a></li>
		  <li><a data-toggle="pill" href="#menu1">게시판</a></li>
		  <li><a data-toggle="pill" href="#menu2">공지사항</a></li>
		</ul>
	
		<div class="tab-content">
		  <div id="home" class="tab-pane fade in active">
		    <h3>HOME</h3>
		    <p>Some content.</p>
		  </div>
		  <div id="menu1" class="tab-pane fade">
		    <h3>게시판</h3>
		    <p>Some content in menu 1.</p>
		  </div>
		  <div id="menu2" class="tab-pane fade">
		    <h3>공지사항</h3>
		    <p>Some content in menu 2.</p>
		  </div>
		</div>
	</div>
    <div class="panel-footer">인프런_스프1탄_서민재</div>
  </div>
</div>

<!-- 회원가입 성공 메세지(Modal) -->
<div id="registerSuccessMessageModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content panel-success">
      <div class="modal-header panel-heading">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" style="font-weight: bold;">${msgType}</h4>
      </div>
      <div class="modal-body">
        <p style="font-weight: bold;">${msg}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
