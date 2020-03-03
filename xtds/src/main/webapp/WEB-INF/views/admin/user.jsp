<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
    <script type="text/javascript">

    var dataGrid;
    var organizationTree;

    $(function() {
        organizationTree = $('#organizationTree').tree({
        	width:'100%',
            url : '${path }/organization/findOrganizationByPid',
            onBeforeExpand:function(node){
    	    	$('#organizationTree').tree('options').queryParams={pid:node.id};
    	    },
            onClick : function(node) {
                dataGrid.datagrid('load', {organizationId: node.id});
            },
            onLoadSuccess:function(node,data){
            	var node = $('#organizationTree').tree('find', data[0].id);
            	$('#organizationTree').tree('expand', node.target);
            }
        });

        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/user/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            fitColumns : true,
            idField : 'id',
            sortName : 'createdate',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '80',
                title : '登录名',
                field : 'loginname',
                halign : 'center',
                sortable : true
            }, {
                width : '80',
                title : '姓名',
                field : 'name',
                halign : 'center',
                sortable : true
            },{
                width : '80',
                title : '部门ID',
                field : 'organizationId',
                hidden : true
            },{
                width : '300',
                title : '所属部门',
                field : 'organizationName',
                halign : 'center'
            },{
                width : '130s',
                title : '入党时间',
                field : 'intime',
                halign : 'center',
                align : 'center',
                sortable : true
            },  {
                width : '40',
                title : '性别',
                field : 'sex',
                halign : 'center',
                align : 'center',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '男';
                    case 1:
                        return '女';
                    }
                }
            }, {
                width : '40',
                title : '年龄',
                field : 'age',
                halign : 'center',
                align : 'center',
                sortable : true
            },{
                width : '80',
                title : '学历',
                field : 'xue_li'
        	},{
	            width : '100',
	            title : '党委职位',
	            field : 'positionTwoName'
        	},{
	            width : '100',
	            title : '支部职位',
	            field : 'positionName'
        	},{
                width : '100',
                title : '角色',
                field : 'rolesList',
                halign : 'center',
                align : 'center',
                sortable : true,
                formatter : function(value, row, index) {
                    var roles = [];
                    for(var i = 0; i< value.length; i++) {
                        roles.push(value[i].name);  
                    }
                    return(roles.join('\n'));
                }
            },{
            	width:'80',
            	title:'特长',
            	field:'love',
            	halign:'center',
            	align:'center'
            },
            {
                width : '60',
                title : '状态',
                field : 'status',
                halign : 'center',
                align : 'center',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '正常';
                    case 1:
                        return '停用';
                    }
                }
            },{
                width : '100',
                title : '组织架构排名',
                field : 'seq',
                halign : 'center',
                align : 'center',
                sortable : true
            }] ]
        });
    });
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 400,
            href : '${path }/user/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function deleteFun() {
    	var row = dataGrid.datagrid('getSelected');
        if(row != null){
	        parent.$.messager.confirm('确认对话框', '您是否要删除当前用户？', function(b) {
	            if (b) {
	                if ($('#currentUserId').val() != row.id) {
	                    progressLoad();
	                    $.post('${path }/user/delete', {
	                        id : row.id
	                    }, function(result) {
	                        if (result.success) {
	                        	$.messager.show({
	    	        				title:'消息提示',
	    	        				msg:result.msg,
	    	        				timeout:3000,
	    	        				showType:'slide'
	    	        			});
	                            dataGrid.datagrid('reload');
	                        }
	                        progressClose();
	                    }, 'JSON');
	                } else {
	                	$.messager.show({
	        				title:'消息提示',
	        				msg:'不可以删除自己！',
	        				timeout:3000,
	        				showType:'slide'
	        			});
	                }
	            }
	        });
        }else{
        	$.messager.show({
				title:'消息提示',
				msg:'请选择一条人员信息',
				timeout:3000,
				showType:'slide'
			});
        }
    }
    
    function editFun() {
        var row = dataGrid.datagrid('getSelected');
        if(row != null){
	        parent.$.modalDialog({
	            title : '编辑',
	            width : 500,
	            height : 300,
	            href : '${path }/user/editPage?id=' + row.id,
	            buttons : [ {
	                text : '确定',
	                handler : function() {
	                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
	                    var f = parent.$.modalDialog.handler.find('#userEditForm');
	                    f.submit();
	                }
	            } ]
	        });
        }else{
        	$.messager.show({
				title:'消息提示',
				msg:'请选择一条人员信息',
				timeout:3000,
				showType:'slide'
			});
        }
    }
    function resetPwdFun(){
    	 var row = dataGrid.datagrid('getSelected');
         if(row != null){
        	 $.ajax({
					url:'${path }/user/resetUserPwd',
					data:{id:row.id},
					dataType:'json',
					async : true,
					type:'post',
					success:function(result){
						if(result.success){
							dataGrid.datagrid('reload');
						}
						$.messager.show({
							title:'消息提示',
							msg:result.msg,
							timeout:3000,
							showType:'slide'
						});
					}
				});
         }else{
         	$.messager.show({
 				title:'消息提示',
 				msg:'请选择一条人员信息',
 				timeout:3000,
 				showType:'slide'
 			});
         }
    }
    
    function searchFun() {
        dataGrid.datagrid('load', {
        	name:$('#name').textbox('getValue'),
        	xue_li:$('#xue_li').combobox('getValue'),
        	love:$('#love').combobox('getValue')
        });
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
        <shiro:hasPermission name="/user/add">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFun();">添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/edit">
        	<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editFun();" >编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/delete">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/user/resetPwd">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-refresh'" onclick="resetPwdFun();" >重置密码</a>
        </shiro:hasPermission>
        <span style="float:right;">
        	<label>姓名:</label>
        	<input type="text" class="easyui-textbox" id="name" />
        	<label>学历:</label>
        	<input id="xue_li" class="easyui-combobox"  
                     data-options="valueField:'id',textField:'name',url:'${path}/dict/findDictByDictPid?dictPid=09',
                     loadFilter:function(data){
                        if (data.obj) {
									return data.obj;
								} else {
									return data;
								}
                     }
                     " />  
        	<label>特长:</label>
        	<input id="love" class="easyui-combobox"  
                     data-options="valueField:'id',textField:'name',url:'${path}/dict/findDictByDictPid?dictPid=010',
                     loadFilter:function(data){
                        if (data.obj) {
									return data.obj;
								} else {
									return data;
								}
                     }
                     " />  
            <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a>
        </span>
    </div>
    <div data-options="region:'center',border:false" >
    	<input type="hidden" id="currentUserId" value="<shiro:principal property="id" />" />
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',border:false,split:false" style="width:300px;border-right:1px solid #d3d3d3;padding:10px;">
        <ul id="organizationTree"></ul>
    </div>
</body>
</html>