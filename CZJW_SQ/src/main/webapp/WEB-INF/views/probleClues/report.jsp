<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>

<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/problelist.css" />
<title>监督室报备案件列表页</title>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
     <%@ include file="/commons/basePage.jsp" %>
     <!-- 当需要导出数据的时候把这个参数传到导出编辑页面 -->
    <!-- 储存界面名称 -->
   <div class="dropdown-menu dropdown-anchor-top-left dropdown-has-anchor" id="dropdown-standard">
		<ul>
			<li><a href="#"  onclick="saveFun();">手动添加</a></li>
			<li class="divider"></li>		
			<li><a href="#"  onclick="importFun();">Excel导入</a></li> 
			<li class="divider"></li>
		</ul>
		<input  type="hidden" id="pp"/>
	</div>
	  
  	   <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
        <div id="tb" style="height:auto;width:100%;">
			 <form id="importForm" method="post" enctype="multipart/form-data" style="display: none;">
            	<input type="file" id="fileData" name="fileData" onchange="importExcel()" value="" />
              </form> 
              <%@ include file="/commons/baseSpecial.jsp" %>  
			<shiro:hasPermission name="/probleClues/check">
       		<a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun2();" >查询</a>
       		<a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
       		<a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="holeData();" >全部数据</a>
			<input id="total" value=""  type="hidden"/>
	      	</shiro:hasPermission>
              
		<a id="message" class="color" style="display:block;text-align:right; float:right;margin-right:20px" onclick="show(event)"><font color=red></font></a>
		<div id="content1" onmouseleave="init()" style="display:none;background-color:#E7EFFE;position:fixed;width:250px;box-shadow: #666 0px 0px 6px;">
		</div>
	</div>
	  </div> 
	<script type="text/javascript" src="${staticPath }/static/page/report.js"></script>
	<script type="text/javascript">
	var path='${path }';
	
	function dlo(){
		downloadFile("${path}/probleClues/accDownload","upload/案件线索导入模板.xls","案件线索导入模板");
	}
	
	
    function downloadFile(actoinURL,filePath,fileName){  
    	
    	
	     var form = $("<form>");     
	        $('body').append(form);    
	        form.attr('style','display:none');     
	        form.attr('target','');  
	        form.attr('method','post');  
	        form.attr('action',actoinURL);//下载文件的请求路径  
	          
	          
	        var input1 = $('<input>');   
	        input1.attr('type','hidden');   
	        input1.attr('name','filePath');   
	        input1.attr('value',filePath);  
	        form.append(input1);    
	        var input2 = $('<input>');   
	        input2.attr('type','hidden');   
	        input2.attr('name','fileName');   
	        input2.attr('value',fileName);  
	        form.append(input2);  
	          
	        form.submit();      
	      
	    };
	    

</script>
</body>
</html>