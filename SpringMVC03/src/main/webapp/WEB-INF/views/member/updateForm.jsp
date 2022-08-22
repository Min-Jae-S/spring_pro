<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC03</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script>
  	$(document).ready(function() {
  		if(${!empty msgType}) {
  			if(${msgType eq '성공 메세지'}) {
  				$('#panelType').attr('class', 'modal-content panel-success');
  			} else {
  				$('#panelType').attr('class', 'modal-content panel-danger');
  			}
	  		$('#updateMessageModal').modal('show');
  		}
  	});
  	
  	function checkPassword() {
  		var memPassword1 = $('#memPassword1').val();
  		var memPassword2 = $('#memPassword2').val();
  		
  		if(memPassword1 != memPassword2) {
  			$('span[name=passwordMessage]').html('비밀번호가 일치하지 않습니다.');
  			//$('#memPassword').val("");
  		} else {
  			$('span[name=passwordMessage]').html('');
  			$('#memPassword').val(memPassword1);
  		}
  	}
  	
  	function goUpdate() {
  		var memAge = $('#memAge').val();
  		
  		if(memAge == null || memAge == "" || memAge == 0) {
  			alert('나이를 입력하세요.');
  			return false; // type='submit'이면 return false라도 submit이 진행된다. 
  						  // 단, onclick="return goRegister()"는 제외
  		}
  		
  		//document.updateForm.submit();
  		//document.getElementById('updateForm').submit();
  		//$('#updateForm').submit();
  	}
  </script>
</head>
<body>

<div class="container">
  <c:import url="/WEB-INF/views/common/header.jsp"/>
  <h3>Spring MVC03</h3>
  <div class="panel panel-default">
    <div class="panel-heading"><strong>회원정보 수정 화면</strong></div>
    <div class="panel-body" style="vertical-align: middle">
    	<form action="${contextPath}/memUpdate.do" method="post" name="updateForm" id="updateForm">
    		<input type="hidden" id="memPassword" name="memPassword" value="${sessionScope.member.memPassword}">
    		<table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd; margin-top: 20px;">
    			<tr>
    				<td style="width: 110px; vertical-align: middle; font-weight: bold;">아이디</td>
    				<td><input id="memId" name="memId" class="form-control" type="test" maxlength="20" value="${sessionScope.member.memId}" readonly></td>
    			</tr>
    			<tr>
    				<td style="width: 110px; vertical-align: middle; font-weight: bold;">비밀번호</td>
    				<td colspan="2">
    					<input id="memPassword1" name="memPassword1" onkeyup="checkPassword()" class="form-control" type="password" maxlength="20" placeholder="비밀번호를 입력하세요.">
    					<span name="passwordMessage" class="pull-left" style="color: red; font-size: 12px;"></span>
    				</td>
    			</tr>
    			<tr>
    				<td style="width: 110px; vertical-align: middle; font-weight: bold;">비밀번호 확인</td>
    				<td colspan="2">
    					<input id="memPassword2" name="memPassword2" onkeyup="checkPassword()" class="form-control" type="password" maxlength="20" placeholder="비밀번호를 확인하세요.">
    					<span name="passwordMessage" class="pull-left" style="color: red; font-size: 12px;"></span>
    				</td>
    			</tr>
    			<tr>
    				<td style="width: 110px; vertical-align: middle; font-weight: bold;">이름</td>
    				<td colspan="2"><input id="memName" name="memName" class="form-control" type="text" maxlength="20" placeholder="이름을 입력하세요." value="${sessionScope.member.memName}"></td>
    			</tr>
    			<tr>
    				<td style="width: 110px; vertical-align: middle; font-weight: bold;">나이</td>
    				<td colspan="2"><input id="memAge" name="memAge" class="form-control" type="text" maxlength="20" placeholder="나이를 입력하세요." value="${sessionScope.member.memAge}"></td>
    			</tr>
    			<tr>
    				<td style="width: 110px; vertical-align: middle; font-weight: bold;">성별</td>
    				<td colspan="2">
    					<div class="form-group" style="text-align: center; margin: 0 auto;">
    						<div class="btn-group" data-toggle="buttons">
    							<label class="btn btn-default ${sessionScope.member.memGender eq '남자' ? 'active' : ''}">
    								<input type="radio" name="memGender" autocomplete="off" value="남자"
    									${sessionScope.member.memGender eq '남자' ? 'checked' : ''}>남자
    							</label>
    							<label class="btn btn-default ${sessionScope.member.memGender eq '여자' ? 'active' : ''}">
    								<input type="radio" name="memGender" autocomplete="off" value="여자" 
    									${sessionScope.member.memGender eq '여자' ? 'checked' : ''}>여자
    							</label>
    							<%-- 
    							<label class="btn btn-primary <c:if test="${sessionScope.member.memGender eq '남자'}">active</c:if>">
    								<input type="radio" name="memGender" autocomplete="off" value="남자" 
    									<c:if test="${sessionScope.member.memGender eq '남자'}">checked</c:if> />남자
    							</label>
    							<label class="btn btn-primary <c:if test="${sessionScope.member.memGender eq '여자'}">active</c:if>">
    								<input type="radio" name="memGender" autocomplete="off" value="여자" 
    									<c:if test="${sessionScope.member.memGender eq '여자'}">checked</c:if> />여자
    							</label>
    							 --%>
    						</div>
    					</div>
    				</td>
    			</tr>
    			<tr>
    				<td style="width: 110px; vertical-align: middle; font-weight: bold;">이메일</td>
    				<td colspan="2"><input id="memEmail" name="memEmail" class="form-control" type="text" maxlength="20" placeholder="이메일을 입력하세요." value="${sessionScope.member.memEmail}"></td>
    			</tr>
    			<tr>
    				<td colspan="3" style="text-align: left; vertical-align: middle;">
    					<input type="submit" class="btn btn-primary" value="수정" onclick="return goUpdate()">
    				</td>
    			</tr>
    		</table>
    	</form>
    </div>
    <div class="panel-footer">인프런_스프1탄_서민재</div>
  </div>
</div>

<!-- 회원정보 수정 메세지(Modal) -->
<div id="updateMessageModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div id="panelType" class="modal-content panel-info">
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