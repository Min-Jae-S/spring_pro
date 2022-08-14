<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC02</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script>
  	$(document).ready(function() {
  		loadList();
  	});
  	
  	function loadList() {
  		$.ajax({
  			url : 'boardList.do',
  			type : 'get',
  			dataType : 'json',
  			success : makeView,
  			error : function() {
  				alert('error');
  			}
  		});
  	}
  	
  	function makeView(data) { // data = [{index=0}, {index=1}, {index=2}, ...]
  		var listHtml = "<table class='table table-bordered'>";
  		listHtml += "<tr>";
  		listHtml += "<td>번호</td>";
  		listHtml += "<td>제목</td>";
  		listHtml += "<td>작성자</td>";
  		listHtml += "<td>작성일</td>";
  		listHtml += "<td>조회수</td>";
  		listHtml += "</tr>";
  		$.each(data, function(index, obj) { // obj = {"idx":5, "title":제목, "writer":작성자, ...}
	  		listHtml += "<tr>";
	  		listHtml += "<td>" + obj.idx  + "</td>";
	  		listHtml += "<td>" + obj.title + "</td>";
	  		listHtml += "<td>" + obj.writer + "</td>";
	  		listHtml += "<td>" + obj.indate + "</td>";
	  		listHtml += "<td>" + obj.count + "</td>";
	  		listHtml += "</tr>";
  		});
  		listHtml += "<tr>";
  		listHtml += "<td colspan='5'>";
  		listHtml += "<button class='btn btn-primary' onclick='goWriteForm()'>글쓰기</button>";
  		listHtml += "</td>";
  		listHtml += "</tr>";
  		listHtml += "</table>";
  		
  		$('#boardList').html(listHtml);
  		
  		$('#boardList').css('display', 'block');
  		$('#writeForm').css('display', 'none');
  	}
  	
  	function goWriteForm() {
  		$('#boardList').css("display", "none");
  		$('#writeForm').css("display", "block");
  	}
  	
  	function goList() {
  		$('#boardList').css("display", "block");
  		$('#writeForm').css("display", "none");	
  	}
  	
  	function goInsert() {
  		//var title = $("title").val();
  		//var content = $("content").val();
  		//var wrtier = $("writer").val();
  		
  		var formData = $("#insertForm").serialize();
  		
  		$.ajax({
  			url : 'boardInsert.do',
  			type : 'post',
  			data : formData,
  			success : loadList,
  			error : function() {
  				alert('error');
  			}
  		});
  	}
  </script>
</head>
<body>
 
<div class="container">
  <h2>Spring MVC02</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body" id="boardList"></div>
    <div class="panel-body" id="writeForm" style="display: none;">
    	<form id="insertForm" method="post">
	    	<table class="table">
	    		<tr>
	    			<td>제목</td>
	    			<td><input type="text" id="title" name="title" class="form-control"></td>
	    		</tr>
	    		<tr>
	    			<td>내용</td>
	    			<td><textarea id="content" name="content" rows="7" class="form-control" style="resize: none;"></textarea></td>
	    		</tr>
	    		<tr>
	    			<td>작성자</td>
	    			<td><input type="text" it="writer" name="writer" class="form-control"></td>
	    		</tr>
	    		<tr>
	    			<td colspan="2" align="center">
	    				<button type="submit" class="btn btn-success" onclick="goInsert()">등록</button>
	    				<button type="reset" class="btn btn-warning">취소</button>
	    				<button type="button" class="btn btn-info" onclick="goList()">목록</button>
	    			</td>
	    		</tr>
	    	</table>
	    </form>
    </div>
    <div class="panel-footer">인프런_스프1탄_서민재</div>
  </div>
</div>

</body>
</html>