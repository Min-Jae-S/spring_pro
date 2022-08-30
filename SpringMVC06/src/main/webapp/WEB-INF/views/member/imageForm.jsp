<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="memberUser" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}"/>
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
  		if(${!empty msgType}) {
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
    <div class="panel-heading"><strong>회원사진 등록</strong></div>
    <div class="panel-body">
	  <form action="${contextPath}/memImageUpload.do?${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
    	<table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd; margin-top: 20px;">
    		<tr>
    			<td style="width: 110px; vertical-align: middle; font-weight: bold;">아이디</td>
   				<td><input type="text" class="form-control" id="memId" name="memId" value="${memberUser.member.memId}" readonly></td>
    		</tr>
    		<tr>
    			<td style="width: 110px; vertical-align: middle; font-weight: bold;">사진 업로드</td>
   				<td colspan="2">
   					<span class="btn btn-default">
   						이미지를 업로드하세요.
   						<input type="file" name="memProfile">
   					</span>
   				</td>
    		</tr>
    		<tr>
    			<td colspan="2" style="text-align: left;">
    				<input type="submit" class="btn btn-primary" value="등록">
    			</td>
    		</tr>
    	</table>
	  </form>
    </div>
    <div class="panel-footer">인프런_스프1탄_서민재</div>
  </div>
</div>

<!-- 업로드 실패 메세지(Modal) -->
<div id="messageModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content panel-danger">
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