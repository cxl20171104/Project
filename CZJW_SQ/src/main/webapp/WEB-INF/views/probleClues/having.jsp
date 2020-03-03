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
	 <div class="dropdown-menu dropdown-anchor-top-left dropdown-has-anchor" id="dropdown-standard">
		<ul aria-labelledby="dropdownMenu">
                <li><a href="#"  onclick="saveFun(1);">手动添加</a></li>
                <li class="divider"></li>
                <li><a href="#"  onclick="importFun();">Excel导入</a></li>
            </ul>
		<input  type="hidden" id="pp"/>
	 </div>
  	 <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
  	        <%@ include file="/commons/baseSpecial.jsp" %>
       		<a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun2();" >查询</a>
       		<a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
			<a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="holeData();" >全部数据</a>
			<input id="total" value=""  type="hidden"/>
       		<shiro:hasPermission name="/probleClues/jds_add">
	            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" data-dropdown="#dropdown-standard" >添加</a>
	            <a  class="easyui-linkbutton" data-options="iconCls:'icon-list'" onclick="dlo();" >下载模板</a> 
	          </shiro:hasPermission>
    </div> 
    
    <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>  
	<script type="text/javascript" src="${staticPath }/static/page/having.js"></script>
	<script type="text/javascript">
		var path='${path }';
		     $(function(){
	        	  $('#state').combobox({
	              	onSelect:function(param){
	              		state =$("#state").combobox('getValue');
	              		$('#dataGrid').datagrid('load', {
	              			pageName : $("#pageName").val(),
	              			zddb :$("#zddb").val(),
	              			state :state,
	              			organId :$("#organId").val(),
	              			isGet :'1'
	              		});
	              	}
	              	
	              }); 
	        });
		     function dlo(){
			       downloadFile("${path}/probleClues/accDownload","upload/案件线索导入模板.xls","案件线索导入模板");
			}
	</script>
</body>
</html>