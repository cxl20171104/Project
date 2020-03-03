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
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/user/dataGrid',
            fit : true,
            border:false,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
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
                width : '80',
                title : '所属部门',
                field : 'organizationName',
                halign : 'center'
            },{
                width : '130s',
                title : '创建时间',
                field : 'createdate',
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
                width : '120',
                title : '电话',
                field : 'phone',
                sortable : true
            }, 
            {
                width : '200',
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
            }] ],
            onDblClickRow:function(index, row){
            	$('#selectedTable').datagrid('appendRow',row);
            }
        });
        /* $('#selectedTable').datagrid({
        	fit : true,
        	border:false,
            striped : true,
            rownumbers : true,
            singleSelect : true,
            idField : 'id',
            columns : [ [ {
                width : '80',
                title : '姓名',
                field : 'name',
                halign : 'center',
            }]]
        }); */
    });
    
    function searchFun() {
        dataGrid.datagrid('load', {name:$('#name').textbox('getValue')});
    }
    function getSelectedUser(){
    	var row = dataGrid.datagrid('getSelected');
    	return row;
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
        <span style="float:right;">
        	<label>姓名:</label>
        	<input type="text" class="easyui-textbox" id="name" />
            <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a>
        </span>
    </div>
    <div data-options="region:'center',border:false" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <!-- <div data-options="region:'east',split:false,border:false" style="width:150px;border-left:1px solid #d3d3d3">
        <table id="selectedTable"></table>
    </div> -->
</body>
</html>