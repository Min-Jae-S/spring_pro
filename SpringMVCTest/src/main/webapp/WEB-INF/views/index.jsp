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
  	$(document).ready(function(){
		
  		// Object
  		$('#btnObj').on("click", function() {
  			var obj = {
				name : "John",
				age : 30
  			};
  			console.log("obj");
  			console.log(obj);
  		});
  		
  		// JSON
  		$('#btnJSON').on("click", function() {
  			var obj = {
				name : "John",
  				age : 30
  	  		};
  			
  			var json = JSON.stringify(obj);
  			console.log("json");
  			console.log(json);
  		});
  		
  		// Obj --> JSON
  		$('#btnToJson').on("click", function() {
  			var formData = {
  				userId : $('input[name=userId]').val(),
  				userPassword : $('input[name=userPassword]').val(),
  				userAge : $('input[name=userAge]').val(),
  				userAgree : $('input[name=userAgree]:checked').val()
  			};
  			
  			var json = JSON.stringify(formData);
  			console.log("json");
  			console.log(json);
  		});
  		
  		// JSON --> Obj
  		$('#btnToObj').on("click", function() {
  			
  		});
  		
  		// Send Data
  		$('#btnSend').on("click", function() {
  			var formData = {
  				userId : $('input[name=userId]').val(),
  				userPassword : $('input[name=userPassword]').val(),
  				userAge : $('input[name=userAge]').val(),
  				userAgree : $('input[name=userAgree]:checked').val()
  			};
  			console.log(formData);
  			
  			var json = JSON.stringify(formData);
  			console.log(json);
  			
  			$.ajax({
  				url : '${contextPath}/getData',
  				type : 'POST',
  				contentType: 'application/json; charset=utf-8',
  				data : json,
  				//data: formData,
  				success : function(data) { 
  					console.log(data);
  				},
  				error : function() { alert('error'); }
  			});
  		});
  		
  		// Get Data
  		$('#btnGet').on("click", function() {
  			$.ajax({
  				url : '${contextPath}/sendData',
  				type : 'POST',
  				dataType : 'json',
  				success : function(data) { 
  					console.log(data);
  				},
  				error : function() { alert('error'); }
  			});
  		});
  		
  		
  	});
  </script>
  <style>
  	table {
  		text-align: center;
  	}
  	
  	td {
  		vertical-align: middle !important;
  	}
  </style>
</head>
<body>

<div class="container">
	<div style="margin-top: 20px;">
	    <button class="btn btn-primary" type="button" id="btnObj">Object</button>
	    <button class="btn btn-primary" type="button" id="btnJSON">JSON</button>
	</div>
	<div style="margin-top: 20px;">
		<form id="frm">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td><input class="form-control" type="text" name="userId"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input class="form-control" type="password" name="userPassword"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input class="form-control" type="text" name="userAge"></td>
				</tr>
				<tr>
					<td>동의여부</td>
					<td>
						<label class="radio-inline"><input type="radio" name="userAgree" value="true" checked>동의</label>
						<label class="radio-inline"><input type="radio" name="userAgree" value="false">비동의</label>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div style="margin-top: 20px;">
	   	<button class="btn btn-primary" type="button" id="btnToJson">Obejct --> JSON</button>
		<button class="btn btn-primary" type="button" id="btnToObj">JSON --> Object</button>
	</div>
	<div style="margin-top: 20px;">
	   	<button class="btn btn-primary" type="button" id="btnSend">Send Data</button>
	   	<button class="btn btn-primary" type="button" id="btnGet">Get Data</button>
	</div>
</div>

</body>
</html>