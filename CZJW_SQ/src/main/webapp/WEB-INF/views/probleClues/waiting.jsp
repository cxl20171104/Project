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
     <!-- 当需要导出数据的时候把这个参数传到导出编辑页面 -->
    <!-- 储存界面名称 -->
  	   <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
        <div id="tb" style="height:auto;width:100%;">
			 <form id="importForm" method="post" enctype="multipart/form-data" style="display: none;">
            	<input type="file" id="fileData" name="fileData" onchange="importExcel()" value="" />
              </form> 
        	  <%@ include file="/commons/baseSpecial.jsp" %>  
        	  <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun2();" >查询</a>
        	     <shiro:hasPermission name="/probleClues/mt"> 
			   <a  class="easyui-linkbutton" data-options="iconCls:'icon-list'" onclick="exportemp();" >制作模板</a> 
               <form id="reprotFrom" method="post"  >
               <input id="reportGs" name="reportGs" value="案件线索导入模板.xls_导入模板.xml_template" type="hidden" />   
               </form>	 
               	</shiro:hasPermission>
               <shiro:hasPermission name="/probleClues/import">
              </shiro:hasPermission>
	          <shiro:hasPermission name="/probleClues/jds_del">
	            <a  class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
	          </shiro:hasPermission>
			  <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
              <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="holeData();" >全部数据</a>
			  <input id="total" value=""  type="hidden"/>
		<a id="message" class="color" style="display:block;text-align:right; float:right;margin-right:20px" onclick="show(event)"><font color=red></font></a>
		<div id="content1" onmouseleave="init()" style="display:none;background-color:#E7EFFE;position:fixed;width:250px;box-shadow: #666 0px 0px 6px;">
		</div>
	</div>
	  </div> 
	<script type="text/javascript" src="${staticPath }/static/page/waiting.js"></script>
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