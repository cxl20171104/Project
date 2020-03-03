<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>用户使用情况</title>
<style>
</style>
</head>
<body>

<h2>用户总使用量:<span>    ${count}</span></h2>
<hr>
<h2>各部使用情况</h2>

<table>
<c:forEach items="${lmap}" var="obj">
   <tr>
   <td>${obj.organName}</td>
   <td>${obj.count}</td>
   </tr>
</c:forEach>
</table>
</body>
</html>