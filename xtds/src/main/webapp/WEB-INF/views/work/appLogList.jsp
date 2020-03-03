<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>app日志管理</title>
<script type="text/javascript">
	$(function() {
		$('#companyTable').datagrid({
			url : '${path }' + '/applog/list',
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
			sortName : 'createTime',
			sortOrder : 'asc',
			columns : [ [ {
				title : 'id',
				field : 'id',
				checkbox : true
			}, {
				width : '200',
				title : '操作人',
				field : 'operaterName',
				halign : 'center',
				sortable : false
			}, {
				width : '200',
				title : '操作类型',
				field : 'type',
				halign : 'center',
				sortable : false,
				formatter : function(data, row, index) {
					if (data == 1)
						return "删除";
					else if (data == 0) {
						return "增加";
					} else if (data == 2)
						return "修改";
					else
						return "查询";

				}
			}, {
				width : '200',
				title : '操作时间',
				field : 'otime',
				halign : 'center',
				sortable : false

			}, {
				width : '200',
				title : '操作内容',
				field : 'detail',
				halign : 'left',
				sortable : false
			} ] ],
			loadFilter : function(data) {
				if (data.obj) {
					return data.obj;
				} else {
					return data;
				}
			}
		});
		$('#companyDialog').dialog({
			width : 400,
			height : 300,
			title : '企业信息维护',
			closed : true,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					save();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#companyDialog').dialog('close');
				}
			} ]
		});
	});
	function downloadFile(id) {
		var form = $("<form>");//定义一个form表单
		form.attr("style", "display:none");
		form.attr("target", "");
		form.attr("method", "post");
		form.attr("action", "${path}/att/download");
		var input1 = $("<input>");
		input1.attr("type", "hidden");
		input1.attr("name", "url");
		input1.attr("value", id);
		$("body").append(form);//将表单放置在web中
		form.append(input1);
		form.submit();//表单提交 
	}
	function addFun() {
		parent.addTab("上传", "${path}/att/toMain", "icon-company");
	}
	function deleteFun() {
		var rows = $('#companyTable').datagrid('getChecked');
		if (rows.length > 0) {
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r) {
				if (r) {
					var ids = new Array();
					for ( var i = 0; i < rows.length; i++) {
						ids[ids.length] = rows[i].id;
					}
					$.ajax({
						url : '${path }/att/delete',
						data : {
							ids : ids.join()
						},
						dataType : 'json',
						async : true,
						type : 'post',
						success : function(result) {
							if (result.success) {
								$('#companyTable').datagrid('load');
							}
							$.messager.show({
								title : '消息提示',
								msg : result.msg,
								timeout : 3000,
								showType : 'slide'
							});
						}
					});
				}
			});

		} else {
			$.messager.show({
				title : '消息提示',
				msg : '请勾选要删除的企业信息',
				timeout : 3000,
				showType : 'slide'
			});
		}
	}
	function refreshTable() {
		$('#companyTable').datagrid('load');
	}
	function closeDialog() {
		$('#companyDialog').dialog('close');
	}
	function searchFun() {
		$('#companyTable').datagrid('load', {
			operaterName : $('#searchName').textbox('getValue')
		});
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">

		<span style="float: left;">
		
		<%-- <select style="width:100px;" class="webui-combobox">
		<c:forEach items="${dict }" var="d">
			<option value="${d.value }">${d.name }</option>
		</c:forEach>
		</select> --%>
		 <input type="text" class="easyui-textbox"
			data-options="prompt:'请输入附件名称'" id="searchName" /> <a
			class="easyui-linkbutton" data-options="iconCls:'icon-search'"
			onclick="searchFun();">查询</a> </span>
	</div>
	<div data-options="region:'center',border:false">
		<table id="companyTable"></table>
	</div>
	<div id="companyDialog"></div>
</body>
</html>