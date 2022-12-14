<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring MVC01</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
 
<div class="container">
  <h2>Spring MVC01</h2>
  <div class="panel panel-default">
    <div class="panel-heading">BOARD</div>
    <div class="panel-body">
    	<table class="table table-bordered table-hover">
    		<tr>
    			<td>번호</td>
    			<td>제목</td>
    			<td>작성내용</td>
    			<td>작성자</td>
    			<td>작성일</td>
    			<td>조회수</td>
    		</tr>
    		<c:forEach var="board" items="${list}"> 
    		<tr>
    			<td>${board.idx}</td>
				<td><a href="boardContent.do?idx=${board.idx}">${board.title}</a></td>
    			<td>${board.content}</td>
    			<td>${board.writer}</td>
    			<td>${fn:split(board.indate, " ")[0]}</td>
    			<td>${board.count}</td>
    		</tr>
    		</c:forEach>
    	</table>
    	<a href="boardForm.do" class="btn btn-primary">글쓰기</a>
    </div>
    <div class="panel-footer">인프런_스프1탄_서민재</div>
  </div>
</div>

</body>
</html>