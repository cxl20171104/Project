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
		 dataGrid = $('#companyTable').datagrid({
			url : '${path }' + '/activi/list',
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
			columns : [ [ {
				title : 'id',
				field : 'id',
				checkbox : false,
				hidden:true
			}, {
				width : '200',
				title : '名称',
				field : 'name',
				halign : 'center',
				sortable : false
			}, {
				width : '200',
				title : '主题',
				field : 'topic',
				halign : 'center',
				sortable : false
			}, {
				width : '200',
				title : '截止日期',
				field : 'etime',
				halign : 'center',
				sortable : false
			}, {
				width : '200',
				title : '活动性质',
				field : 'type',
				halign : 'center',
				sortable : false,
				formatter : function(data, row, index) {
					if (data == 1)
						return "持续性活动";
					else if(data == 2)
						return "临时性活动";
					else 
						return "其他";

				}

			}, {
                field : '_b',
                title : '操作',
                width : 200,
                formatter : function(value, row, index) {
                    var str = '';
                        <shiro:hasPermission name="actiedit">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="addFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="actidelete">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                    return str;
                }
            }] ],
                onLoadSuccess:function(data){
                $('.role-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.role-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
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
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/activi/edit?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;
                    var f = parent.$.modalDialog.handler.find('#roleEditForm');
                    f.submit();
                }
            } ]
        });
    }
	function deleteFun(id) {
		if (id) {
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r) {
				if (r) {
					$.ajax({
						url : '${path }/activi/delete',
						data : {
							ids : id
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
				msg : '请勾选要删除的活动信息',
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
		<span style="float: right;">
		
		<%-- <select style="width:100px;" class="webui-combobox">
		<c:forEach items="${dict }" var="d">
			<option value="${d.value }">${d.name }</option>
		</c:forEach>
		</select> --%>
		
		 <input type="text" class="easyui-textbox"
			data-options="prompt:'请输入活动名称'" id="searchName" /> <a
			class="easyui-linkbutton" data-options="iconCls:'icon-search'"
			onclick="searchFun();">查询</a> </span>
 <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/acti/add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        
    </div>
		
	</div>
	<div data-options="region:'center',border:false">
		<table id="companyTable"></table>
	</div>
	<div id="companyDialog"></div>
</body>
</html>