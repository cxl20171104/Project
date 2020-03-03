<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>活动管理</title>
<script type="text/javascript">
 var dataGrid;
	$(function() {
	$('#companyDialog').dialog({
			width : 545,
			height : 500,
			title : '回复',
			closed : true
		});
		 dataGrid = $('#companyTable').datagrid({
			url : '${path }' + '/feedBack/list',
			fit : true,
			fitColumns : true,
			striped : true,
			rownumbers : false,
			pagination : true,
			singleSelect : true,
			selectOnCheck : false,
			checkOnSelect : false,
			border : false,
			idField : 'id',
			sortName : 'ctime',
			sortOrder : 'desc',
			columns : [ [ {
				title : 'id',
				field : 'id',
				checkbox : true
			}, {
				width : '200',
				title : '反馈用户',
				field : 'cusername',
				halign : 'center',
				sortable : false
			},  {
				width : '200',
				title : '日期',
				field : 'ctime',
				halign : 'center',
				sortable : false
			}, {
				width : '200',
				title : '内容',
				field : 'type',
				halign : 'center',
				sortable : false,
				formatter : function(data, row, index) {
					if(data){
						if(data.length>20){
							return data.substring(0,20);
						}
						return data
					}else{
						return "";
					}

				}

			}, {
                field : '_b',
                title : '操作',
                width : 200,
                formatter : function(value, row, index) {
                    var str = '';
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="addFun(\'{0}\');" >回复</a>', row.id);
                    return str;
                }
            }] ],
                onLoadSuccess:function(data){
                $('.role-easyui-linkbutton-edit').linkbutton({text:'回复',plain:true,iconCls:'icon-edit'});
            },
            toolbar : '#toolbar',
			loadFilter : function(data) {
				if (data.obj) {
					return data.obj;
				} else {
					return data;
				}
			}
		});
		});
		
    function addFun(id) {
    	$('#companyDialog').dialog('refresh','${path }/feedBack/detail?commentId='+id+ "&&dt=" + new Date());
		$('#companyDialog').dialog('open');
    }
	
	function refreshTable() {
		$('#companyTable').datagrid('load');
	}
	function closeDialog() {
		$('#companyDialog').dialog('close');
	}
	function searchFun() {
		$('#companyTable').datagrid('load', {
			cusername : $('#searchName').textbox('getValue')
		});
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
		<span style="float: right;">
		
		<%-- <select style="width:100px;" class="webui-combobox">
		<c:forEach items="${dict }" var="d">
			<option value="${d.value }">${d.name }</option>
		</c:forEach>
		</select> --%>
		
		 <input type="text" class="easyui-textbox"
			data-options="prompt:'请用户名称'" id="searchName" /> <a
			class="easyui-linkbutton" data-options="iconCls:'icon-search'"
			onclick="searchFun();">查询</a> </span>
 <div id="toolbar" style="display: none;">
        
    </div>
		
	</div>
	<div data-options="region:'center',border:false">
		<table id="companyTable"></table>
	</div>
	<div style="overflow: hidden;" id="companyDialog"></div>
</body>
</html>