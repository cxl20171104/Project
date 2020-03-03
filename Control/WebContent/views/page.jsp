<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:10%;}
.font{
  color:#FFFF;
  font-size:20px;
}
</style>
<script type="text/javascript">
     function order(index){
    	 $("#order").val(index);
    	 $("#orderForm").submit();
     }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false" style="background:url(${path}/images/timg.png) no-repeat;background-size:100%;">
<h1 class="font">项目部署控制器</h1>
<form id="orderForm" action="order">
   <table class="cxl">
     <tr>
       <td><span class="font">项目名称</span></td>
       <td><input name="programName" value="CZJW" style="width:400px;height:50px;"/></td>
     </tr>
     <tr>
       <td><span class="font">部署地址</span></td>
       <td><input name="programPath" value="D://tomcat/webapps/" style="width:400px;height:50px;"/></td>
     </tr>
      <tr>
       <td><span class="font">项目位置</span></td>
       <td><input name="filePath" value="D://tomcat/webapps/CZJW" style="width:400px;height:50px;"/></td>
     </tr>
      <tr>
       <td> <span class="font">命令</span></td>
       <td><input id="order" name="order" value="" style="width:400px;height:50px;"/></td>
     </tr>
       <tr>
       <td><span class="font">项目备份位置</span></td>
       <td><input name="tempPath" value="D://temp" style="width:400px;height:50px;"/></td>
     </tr>
      <tr>
       <td><span class="font">移动文件的起始位置</span></td>
       <td><input name="startPath" value="D://temp/CZJW/files" style="width:400px;height:50px;"/></td>
     </tr>
      <tr>
       <td><span class="font">移动文件的终点位置</span></td>
       <td><input name="endPath"   value="D://tomcat/webapps/CZJW/files" style="width:400px;height:50px;"/></td>
     </tr>
   </table>
</form>
   <table class="cxl">
   <tr>
    <td> <button onclick="order(0);">停止服务器</button></td>
    <td> <button onclick="order(3);">备份项目</button></td>
    <td> <button onclick="order(4);">移动文件</button></td>
    <td> <button onclick="order(1);">删除项目</button></td>
    <td> <button onclick="order(2);">启动服务器</button></td>
    
    
   </tr>
   </table>
<form action="upload" enctype="multipart/form-data" method="post" > 
<table class="cxl"> 
<tr>
    <td><span class="font">上传war包</span></td>
    <td><input type="file" name="file1"></td>

    <td><input type="submit" value="提交"/></td>
</tr>
 </table> 
</form>
</body>
</html>