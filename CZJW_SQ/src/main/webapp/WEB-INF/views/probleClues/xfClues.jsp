<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
  <style type="text/css">  
    .dropdown-submenu {
            position: relative;
        }
        .dropdown-submenu > .dropdown-menu {
            top: 0;
            left: 100%;
            margin-top: -6px;
            margin-left: -1px;
            -webkit-border-radius: 0 6px 6px 6px;
            -moz-border-radius: 0 6px 6px;
            border-radius: 0 6px 6px 6px;
        }
        .dropdown-submenu:hover > .dropdown-menu {
            display: block;
        }
        .dropdown-submenu > a:after {
            display: block;
            content: " ";
            float: right;
            width: 0;
            height: 0;
            border-color: transparent;
            border-style: solid;
            border-width: 5px 0 5px 5px;
            border-left-color: #ccc;
            margin-top: 5px;
            margin-right: -10px;
        }
        .dropdown-submenu:hover > a:after {
            border-left-color: #fff;
        }
        .dropdown-submenu.pull-left {
            float: none;
        }
        .dropdown-submenu.pull-left > .dropdown-menu {
            left: -100%;
            margin-left: 10px;
            -webkit-border-radius: 6px 0 6px 6px;
            -moz-border-radius: 6px 0 6px 6px;
            border-radius: 6px 0 6px 6px;
        }
 </style>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/problelist.css" />
<title>用户管理</title>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
     <%@ include file="/commons/basePage.jsp" %>
     <!-- 当需要导出数据的时候把这个参数传到导出编辑页面 -->
    <!-- 储存界面名称 -->
   <div class="dropdown-menu dropdown-anchor-top-left dropdown-has-anchor" id="dropdown-standard">
		    <ul aria-labelledby="dropdownMenu">
                <li><a href="#"  onclick="saveFun(1);">手动添加</a></li>
                <li class="divider"></li>
                <li><a href="#"  onclick="importFun(1);">Excel导入</a></li>
            </ul>
	</div>
	  
  	   <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
        <div id="tb" style="height:auto;width:100%;">
			 <form id="importForm" method="post" enctype="multipart/form-data" style="display: none;">
            	<input type="file" id="fileData" name="fileData" onchange="importExcel()" value="" />
              </form> 
        	   
        	     <shiro:hasPermission name="/probleClues/mt"> 
			   <a  class="easyui-linkbutton" data-options="iconCls:'icon-list'" onclick="exportemp();" >制作模板</a> 
               <form id="reprotFrom" method="post"  >
               <input id="reportGs" name="reportGs" value="案件线索导入模板.xls_导入模板.xml_template" type="hidden" />   
               </form>	 
               	</shiro:hasPermission>
               <shiro:hasPermission name="/probleClues/import">
              </shiro:hasPermission>
			     <%@ include file="/commons/baseSpecial.jsp" %>  
       		    <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun2();" >查询</a>
	            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" data-dropdown="#dropdown-standard" >添加</a>
	            <a  class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
			    <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
        	    <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="holeData();" >全部数据</a>
			    <input id="total" value=""  type="hidden"/>
             
		<a id="message" class="color" style="display:block;text-align:right; float:right;margin-right:20px" onclick="show(event)"><font color=red></font></a>
		<div id="content1" onmouseleave="init()" style="display:none;background-color:#E7EFFE;position:fixed;width:250px;box-shadow: #666 0px 0px 6px;">
		</div>
	</div>
	  </div> 
	<script type="text/javascript" src="${staticPath }/static/page/xfClues.js"></script>
	<script type="text/javascript">
	var path='${path }';
	</script>
</body>
</html>