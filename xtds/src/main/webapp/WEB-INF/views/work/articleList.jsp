<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>活动管理</title>
<style type="text/css">
a{text-decoration:none}
</style>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#companyTable')
				.datagrid(
						{
							url : '${path }' + '/article/list',
							fit : true,
							fitColumns : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							selectOnCheck : false,
							checkOnSelect : false,
							border : false,
							idField : 'id',
							sortName : 'createTime',
							sortOrder : 'asc',
							columns : [ [
									{
										title : 'id',
										field : 'id',
										checkbox : false,
										hidden:true
									},
									{
										width : '200',
										title : '标题',
										field : 'title',
										halign : 'center',
										sortable : false,
										formatter:function(data,row,index){
										  return '<a href="javascript:void(0)"onclick="seeFun(\''+row.id+'\',\''+data+'\');" >'+data+'</a>';
										}
									},
									{
										width : '100',
										title : '所属栏目',
										field : 'catalogName',
										halign : 'center',
										sortable : false
									},
									{
										width : '100',
										title : '作者',
										field : 'inputerName',
										halign : 'center',
										sortable : false
									},
									{
										width : '60',
										title : '允许评论',
										field : 'comentable',
										halign : 'center',
										sortable : false,
										formatter : function(data, row, index) {
											if (data == 1)
												return "是";
											else
												return "否";

										}
									},
									{
										width : '100',
										title : '点击量/赞/评论',
										field : 'hits',
										halign : 'center',
										sortable : false,
										formatter : function(data, row, index) {
											return row.hits + "|" + row.thumb
													+ "|" + row.comnum;
										}
									},
									{
										width : '150',
										title : '创建时间',
										field : 'createtime',
										halign : 'center',
										sortable : false
									},
									/* {
										width : '50',
										title : '积分',
										field : 'scores',
										halign : 'center',
										sortable : false
									}, */
									{
										width : '80',
										title : '状态',
										field : 'state',
										halign : 'center',
										sortable : false,
										formatter : function(data, row, index) {
											if (data == 1)
												return "通过";
											else
												return "驳回";

										}
									},
									{
										width : '100',
										title : '审核人',
										field : 'audituserName',
										halign : 'center',
										sortable : false
									},
								 /* 	{
										width : '80',
										title : '得分方式',
										field : 'scoretype',
										halign : 'center',
										sortable : false,
										formatter : function(data, row, index) {
											if (data == 1)
												return "查看";
											else
												return "回复";

										}

									}, */ 
									/* {
										width : '150',
										title : '积分有效期',
										field : 'expire',
										halign : 'center',
										sortable : false
									}, */
									{
										field : '_b',
										title : '操作',
										width : 200,
										formatter : function(value, row, index) {
											var str = '';
											<shiro:hasPermission name="actiedit">
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="addFun(\'{0}\',\'{1}\');" >编辑</a>',
															row.id,"编辑_"+row.title);
											</shiro:hasPermission>
											<shiro:hasPermission name="actidelete">
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>',
															row.id);
											</shiro:hasPermission>
											return str;
										}
									} ] ],
							onLoadSuccess : function(data) {
								$('.role-easyui-linkbutton-edit').linkbutton({
									text : '编辑',
									plain : true,
									iconCls : 'icon-edit'
								});
								$('.role-easyui-linkbutton-del').linkbutton({
									text : '删除',
									plain : true,
									iconCls : 'icon-del'
								});
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
	function addFun(id,title){
		
		parent.addTab(title,"${path}/article/toEdit?aid="+id,"icon-company");
		
	}
	function deleteFun(id) {
		$.messager.confirm('确认对话框', '您确认要删除吗？', function(r) {
			if (r) {
				$.ajax({
					url : '${path }/article/delete',
					data : {
						aid : id
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
	}
	function refreshTable() {
		$('#companyTable').datagrid('load');
	}
	function closeDialog() {
		$('#companyDialog').dialog('close');
	}
	function searchFun() {
		$('#companyTable').datagrid('load', {
			title : $('#searchName').textbox('getValue')
		});
	}
	function seeFun(id,title){
		/* addTab(title, href, icon); */
		parent.addTab(title,"${path}/article/SeeArt?aid="+id,"icon-company");
		
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
		<span style="float: left;"> <%-- <select style="width:100px;" class="webui-combobox">
		<c:forEach items="${dict }" var="d">
			<option value="${d.value }">${d.name }</option>
		</c:forEach>
		</select> --%> <input type="text" class="easyui-textbox"
			data-options="prompt:'请输入文章名称'" id="searchName" /> <a
			class="easyui-linkbutton" data-options="iconCls:'icon-search'"
			onclick="searchFun();">查询</a> </span>
		<div id="toolbar" style="display: none;"></div>

	</div>
	<div data-options="region:'center',border:false">
		<table id="companyTable"></table>
	</div>
	<div id="companyDialog"></div>
</body>
</html>