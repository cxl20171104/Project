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

<body class="easyui-layout" data-options="fit:true,border:false">
     <!-- 储存界面名称 -->
   <%@ include file="/commons/basePage.jsp" %>
  	<div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
        	<a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="searchFun2();" >查询</a>
		<a id="message" class="color" style="display:block;text-align:right; float:right;margin-right:20px" onclick="show(event)"><font color=red></font></a>
		<div id="content1" onmouseleave="init()" style="display:none;background-color:#E7EFFE;position:fixed;width:250px;right:20px;box-shadow: #666 0px 0px 6px;">
		</div> 
    </div>
    <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
	</div> 
	<script type="text/javascript" src="${staticPath }/static/page/ADDTONGA.js"></script>
	<script type="text/javascript">
	var path='${path }';
</script>
</head> 
</body>
</html>