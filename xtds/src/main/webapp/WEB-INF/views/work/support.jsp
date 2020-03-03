<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>技术支持</title>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript" src="${staticPath }/static/month.js"></script>
<style>
   body{
     background-color:#ccb;
    
   }
   
   td{
    border:1px solid white;
    font-size:20px;
   }

</style>
</head>
<body>
   <div style="margin:0 auto;width:10%;">
   <img src="${path}/files/upload/cxl.png" style="margin:0 auto"/>
   </div>
    
     <table style="margin:0 auto;width:20%;margin-top:5%">
       <tr>
          <td>技术支持</td>
          <td style="color:red">Support</td>
       </tr>
       <tr>
         <td>开发者：</td> 
        <td>杨亚辉,孙进波,陈晓亮,任胜杰</td>
       </tr>
       <tr>
         <td>联系电话：</td> 
        <td>15614311202</td>
       </tr>
       <tr>
         <td>邮箱：</td> 
        <td>1290973212@qq.com</td>
       </tr>
    </table>
     <table style="margin:0 auto;width:20%;margin-top:5%">
    
       <tr>
         <td>developer:</td> 
        <td>cxl,yyh,sjb,rsj</td>
       </tr>
       <tr>
         <td>phone:</td> 
        <td>15614311202</td>
       </tr>
       <tr>
         <td>Email:</td> 
        <td>1290973212@qq.com</td>
       </tr>
    </table>


</body>
</html>