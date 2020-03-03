<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>企业管理</title>
    <script type="text/javascript">
    $(function() {
        $('#companyTable').datagrid({
            url : '${path }' + '/company/findCompanyPageCondition',
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
                title : '企业名称',
                field : 'name',
                halign : 'center',
                sortable : true
            } , {
                width : '100',
                title : '负责人',
                field : 'leader',
                halign : 'center'
            } , {
                width : '200',
                title : '地址',
                field : 'address',
                halign : 'center'
            } , {
                width : '100',
                title : '传真',
                field : 'fax',
                halign : 'center'
            } , {
                width : '100',
                title : '电话',
                field : 'tel',
                halign : 'center'
            } , {
                width : '100',
                title : '创建人',
                field : 'createrId',
                halign : 'center',
                formatter:function(value,row,index){
                	return row.createUser.name;
                }
            } , {
                width : '150',
                title : '创建时间',
                field : 'createTime',
                halign : 'center',
                sortable : true
            } ] ],
            loadFilter: function(data){
				if (data.obj){
					return data.obj;
				} else {
					return data;
				}
			}
        });
        $('#companyDialog').dialog({
        	width:400,
        	height:300,
        	title:'企业信息维护',
        	closed:true,
        	buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					save();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#companyDialog').dialog('close');
				}
			}]
        });
    });
	function addFun(){
		$('#companyDialog').dialog('refresh','${path }/company/info');
		$('#companyDialog').dialog('open');
	}
	function editFun(){
		var row = $('#companyTable').datagrid('getSelected');
		if(row != null){
			$('#companyDialog').dialog('refresh','${path }/company/info?id='+row.id);
			$('#companyDialog').dialog('open');
		}else{
			$.messager.show({
				title:'消息提示',
				msg:'请选择一条企业信息',
				timeout:3000,
				showType:'slide'
			});
		}
	}
	function deleteFun(){
		var rows = $('#companyTable').datagrid('getChecked');
		if(rows.length>0){
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r){
				if (r){
					var ids = new Array();
					for(var i=0;i<rows.length;i++){
						ids[ids.length] = rows[i].id;
					}
					$.ajax({
						url:'${path }/company/delete',
						data:{ids:ids.join()},
						dataType:'json',
						async : true,
						type:'post',
						success:function(result){
							if(result.success){
								$('#companyTable').datagrid('load');
							}
							$.messager.show({
								title:'消息提示',
								msg:result.msg,
								timeout:3000,
								showType:'slide'
							});
						}
					});
				}
			});
			
		}else{
			$.messager.show({
				title:'消息提示',
				msg:'请勾选要删除的企业信息',
				timeout:3000,
				showType:'slide'
			});
		}
	}
	function refreshTable(){
		$('#companyTable').datagrid('load');
	}
	function closeDialog(){
		$('#companyDialog').dialog('close');
	}
	function searchFun(){
		$('#companyTable').datagrid('load',{name:$('#searchName').textbox('getValue')});
	}
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
        <shiro:hasPermission name="/company/add">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFun();" >添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/company/edit">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editFun();" >编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/company/delete">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
        </shiro:hasPermission>
        <span style="float: right;">
        	<input type="text" class="easyui-textbox" data-options="prompt:'请输入企业名称'" id="searchName" />
	        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();" >查询</a>
        </span>
    </div>
    <div data-options="region:'center',border:false">
        <table id="companyTable"></table>
    </div>
    <div id="companyDialog"></div>
</body>
</html>