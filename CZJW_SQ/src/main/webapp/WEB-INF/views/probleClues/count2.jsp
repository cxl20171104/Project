<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>Insert title here</title>
<script  type="text/javascript">
 $(function() {
 	 //$("#a1").attr("href","http://156.4.177.28:18080/smartbi/vision/openresource.jsp?resid=I2c9d6581015f51165116c50c015f530c1c6b0d88&user=admin&password=admin"); 
 	 $("#subBtn").trigger("click");
 });

</script>
<style>
li{
padding-top:5%
}
</style>
</head>
<body class="easyui-layout">   
   
 <a id="a1" href = "http://156.4.177.28:18080/smartbi/vision/openresource.jsp?resid=I402896470163380d380de36e01633891485104f6&user=admin&password=admin"><button class="new-btn-login" id="subBtn" style="display: none" type="button"></button></a>
  
</body>
</html>