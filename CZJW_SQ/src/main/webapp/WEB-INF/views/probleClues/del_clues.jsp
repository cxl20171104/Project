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


 <%@ include file="/commons/basePage.jsp" %>
  	<div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
            <%@ include file="/commons/baseSpecial.jsp" %>
       		<a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun2();">查询</a>
       		<a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
			<a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="holeData();" >全部数据</a>
			<input id="total" value=""  type="hidden"/>
        	
    </div>
    <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>  
	<script type="text/javascript" src="${staticPath }/static/page/del_clues.js"></script>
	<script type="text/javascript">
	var path='${path }';
  
  	
</script>
</body>
</html>