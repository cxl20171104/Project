<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>附件管理</title>
<script type="text/javascript">
	$(function() {
		$('#companyTable')
				.datagrid(
						{
							url : '${path }' + '/att/page',
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
							columns : [ [
									{
										title : 'id',
										field : 'id',
										checkbox : true
									},
									{
										width : '200',
										title : '名称',
										field : 'name',
										halign : 'center',
										sortable : false
									},
									{
										width : '200',
										title : '大小',
										field : 'asize',
										halign : 'center',
										sortable : false
									},
									{
										width : '200',
										title : '上传时间',
										field : 'utime',
										halign : 'center',
										sortable : false

									},
									{
										width : '200',
										title : '操作',
										field : '_b',
										halign : 'left',
										sortable : false,
										formatter : function(data, row, index) {
											return '<a href=\'javascript:void(0)\' class=\"easyui-linkbutton1\"  data-options=\"iconCls:\'icon-download\'\" onclick=downloadFile(\''
													+ row.url + '\')>下载</a>';
										}
									} ] ],
									 onLoadSuccess:function(data){
							                $('.easyui-linkbutton1').linkbutton({text:'下载',plain:true,iconCls:'icon-download'});
							            },
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
		id="/"+id;
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
		parent.addTab("上传","${path}/att/toMain","icon-company");
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
			name : $('#searchName').textbox('getValue')
		});
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
		<shiro:hasPermission name="/company/add">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
				onclick="addFun();">添加</a>
		</shiro:hasPermission>
	
		<shiro:hasPermission name="/company/delete">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-del'"
				onclick="deleteFun();">删除</a>
		</shiro:hasPermission>
		<span style="float: right;"> <input type="text"
			class="easyui-textbox" data-options="prompt:'请输入附件名称'"
			id="searchName" /> <a class="easyui-linkbutton"
			data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a> </span>
	</div>
	<div data-options="region:'center',border:false">
		<table id="companyTable"></table>
	</div>
	<div id="companyDialog"></div>
</body>
</html>