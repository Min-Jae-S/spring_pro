<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC06</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script>
  	$(document).ready(function() {
  		// http://localhost:8888/mvc06/memLoginForm.do?error
  		if(${param.error != null}) {
  			$('#messageType').attr('class', 'modal-content panel-danger');
  			$('.modal-title').text('실패 메세지');
  			$('.modal-body p').text('아이디와 비밀번호를 확인해주세요.');
  			$('#messageModal').modal('show');
  		}
  		
  		if(${!empty msgType}) {
  			$('#messageType').attr('class', 'modal-content panel-success');
  			$('#messageModal').modal('show');
  		}
  	});
  </script>
</head>
<body>
<div class="container">
  <c:import url="/WEB-INF/views/common/header.jsp"/>
  <h3>Spring MVC06</h3><br/>
  <div class="panel panel-default">
    <div class="panel-heading"><strong>로그인 화면</strong></div>
    <div class="panel-body">
	  <form class="form-horizontal" action="${contextPath}/memLogin.do" method="post" style="margin-top: 15px;">
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="memId">아이디</label>
	      <div class="col-sm-10">
	        <input type="text" class="form-control" id="memId" name="username" placeholder="아이디를 입력하세요.">
	      </div>
	    </div>
	    <div class="form-group">
	      <label class="control-label col-sm-2" for="memPassword">비밀번호</label>
	      <div class="col-sm-10">          
	        <input type="password" class="form-control" id="memPassword" name="password" placeholder="비밀번호를 입력하세요.">
	      </div>
	    </div>
	    <div class="form-group">        
	      <div class="col-sm-offset-2 col-sm-10">
	        <button type="submit" class="btn btn-primary">로그인</button>
	      </div>
	    </div>
	  </form>
    </div>
    <div class="panel-footer">인프런_스프1탄_서민재</div>
  </div>
</div>

<!-- 로그인 실패 메세지, 회원가입 성공 메세지 -->
<div id="messageModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div id="messageType" class="modal-content">
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