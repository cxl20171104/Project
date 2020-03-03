<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:300px;}
</style>
<script type="text/javascript">

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
     <hr style="border:10px solid blue;"/>
     <table class="cxl">
     <tr>
     <td><strong>进度名称</strong></td>
     <td><strong>详情</strong></td>
     <td><strong>时间</strong></td>
     </tr>
     <c:forEach var="act" items="${progress}">
          <tr>
          <c:if test="${act.timeForday!=null&&act.timeForday!=''}">
             <td>${act.pointName}</td> <td>${act.detail}</td><td>${act.timeForday.substring(0,10)}</td>
          </c:if>
		  <c:if test="${act.timeForday==null||act.timeForday==''}">
		     <td>${act.pointName}</td> <td>${act.detail}</td><td><span style="color:blue">${act.time.substring(0,10)}</span></td>
		  </c:if>
		 </tr>
	</c:forEach>
	</table>
	<hr style="border:10px solid blue;"/>
</body>
</html>