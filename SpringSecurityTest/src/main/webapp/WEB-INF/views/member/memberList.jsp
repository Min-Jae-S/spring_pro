<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Spring Security</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container pt-5">
  <h3><strong>회원관리</strong></h3>
  <br>
  <table class="table table-bordered">
    <thead class="thead-dark">
      <tr>
        <th>번호</th>
        <th>아이디</th>
        <th>이메일</th>
        <th>권한</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${list}" var="memberVO">
      <tr>
        <td><c:out value="${memberVO.memberIdx}"/></td>
        <td><c:out value="${memberVO.memberId}"/></td>
        <td><c:out value="${memberVO.memberEmail}"/></td>
        <td><c:out value="${memberVO.memberRole}"/></td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <br>
  <a href="${contextPath}/" class="btn btn-info">HOME</a>
</div>
</body>
</html>