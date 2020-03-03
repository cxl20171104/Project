<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/problelist.css" />
<title>用户管理</title>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
  	           <a  class="easyui-linkbutton" data-options="iconCls:'icon-list'" onclick="exportemp();" >制作模板</a> 
               <form id="reprotFrom" method="post"  >
                     <input id="reportGs" name="reportGs" value="案件线索导入模板.xls_导入模板.xml_template" type="hidden" />   
               </form>	
<script type="text/javascript">
 //下载导入模板
function exportemp(){
		var url ='${path}/statistics/OnImportWord';
		 $('#reprotFrom').form('submit',{
			 url:url,
			 onSubmit:function(){} 
		 });
 }
</script>
</body>
</html>