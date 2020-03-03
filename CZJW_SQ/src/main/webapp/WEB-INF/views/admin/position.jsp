<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>职位管理</title>
    <script type="text/javascript">
    $(function() {
        $('#positionTable').datagrid({
            url : '${path }' + '/position/findPosition',
            fit : true,
            fitColumns : true,
            striped : true,
            rownumbers : false,
            pagination : false,
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
                width : '250',
                title : '职位名称',
                field : 'name',
                halign : 'center',
                sortable : true
            } , {
                width : '100',
                title : '是否可以查看',
                field : 'isView',
                halign : 'center',
                align : 'center',
                formatter:function(value,row,index){
                	if(value==0){
                		return '否';
                	}else if(value==1){
                		return '是';
                	}else{
                		return value;
                	}
                }
            } , {
                width : '200',
                title : '备份',
                field : 'remark',
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
					var obj = new Object();
					obj.rows = data.obj;
					return obj;
				} else {
					return data;
				}
			}
        });
        $('#positionDialog').dialog({
        	width:400,
        	height:300,
        	title:'职位信息维护',
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
					$('#positionDialog').dialog('close');
				}
			}]
        });
    });
	function addFun(){
		$('#positionDialog').dialog('refresh','${path }/position/info');
		$('#positionDialog').dialog('open');
	}
	function editFun(){
		var row = $('#positionTable').datagrid('getSelected');
		if(row != null){
			$('#positionDialog').dialog('refresh','${path }/position/info?id='+row.id);
			$('#positionDialog').dialog('open');
		}else{
			$.messager.show({
				title:'消息提示',
				msg:'请选择一条职位信息',
				timeout:3000,
				showType:'slide'
			});
		}
	}
	function deleteFun(){
		var rows = $('#positionTable').datagrid('getChecked');
		if(rows.length>0){
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r){
				if (r){
					var ids = new Array();
					for(var i=0;i<rows.length;i++){
						ids[ids.length] = rows[i].id;
					}
					$.ajax({
						url:'${path }/position/delete',
						data:{ids:ids.join()},
						dataType:'json',
						async : true,
						type:'post',
						success:function(result){
							if(result.success){
								$('#positionTable').datagrid('load');
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
				msg:'请勾选要删除的职位信息',
				timeout:3000,
				showType:'slide'
			});
		}
	}
	function refreshTable(){
		$('#positionTable').datagrid('load');
	}
	function closeDialog(){
		$('#positionDialog').dialog('close');
	}
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
        <shiro:hasPermission name="/position/add">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFun();" >添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/position/edit">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editFun();" >编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/position/delete">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
        </shiro:hasPermission>
    </div>
    <div data-options="region:'center',border:false">
        <table id="positionTable"></table>
    </div>
    <div id="positionDialog"></div>
</body>
</html>