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
            url : '${path }/footprint/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            fitColumns : false,
            idField : 'id',
            sortName : 'createdate',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [{
                width : '150',
                title : '姓名',
                field : 'name',
                halign : 'center',
                sortable : true,
                align : 'center'
            },{
                width : '150',
                title : '点赞数',
                field : 'like',
                halign : 'center',
                align : 'center'
            },{
                width : '150',
                title : '评论数',
                field : 'review',
                halign : 'center',
                	 align : 'center'
            },{
                width : '150',
                title : '发布文章数',
                field : 'issue',
                halign : 'center',
                align : 'center'
            },{
                width : '150',
                title : '浏览文章数',
                field : 'track',
                halign : 'center',
                align : 'center'
            }] ]
        }); 
    });
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" >
    	<input type="hidden" id="currentUserId" value="<shiro:principal property="id" />" />
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',border:false,split:false" style="width:300px;border-right:1px solid #d3d3d3;padding:10px;">
        <ul id="organizationTree"></ul>
    </div>
</body>
</html>