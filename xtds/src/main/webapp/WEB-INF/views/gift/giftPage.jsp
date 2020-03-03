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
            url : '${path }/gift/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            fitColumns : true,
            idField : 'id',
            sortName : 'startTime',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '100',
                title : '礼品名称',
                field : 'name',
                halign : 'center',
                sortable : true
            }, {
                width : '200',
                title : '图片',
                field : 'pic',
                halign : 'center',
                formatter: function(value,row,index){  
                	var path='${path}/files/'+value;
					return '<img src="'+path+'" style="width:100%;"/>';
		     } 
            },{
                width : '200',
                title : '兑换开始时间',
                field : 'startTime',
                halign : 'center',
                sortable : true
            },{
                width : '200',
                title : '兑换结束时间',
                field : 'endTime',
                halign : 'center',
                sortable : true
               
            },{
                width : '200',
                title : '需要积分',
                field : 'score',
                halign : 'center',
                sortable : true
            },{
                width : '100',
                title : '活动详情',
                field : 'detail',
                halign : 'center',
                align : 'center'
                
            }] ]
        });
    });
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 800,
            height : 450,
            href : '${path }/gift/addPage',
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
	        parent.$.messager.confirm('确认对话框', '您是否要删除当前礼品？', function(b) {
	            if (b) {
	                if ($('#currentUserId').val() != row.id) {
	                    progressLoad();
	                    $.post('${path }/gift/delete', {
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
	            width : 800,
	            height : 450,
	            href : '${path }/gift/editPage?id=' + row.id,
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
    </div>
    <div data-options="region:'center',border:false" >
    	<input type="hidden" id="currentUserId" value="<shiro:principal property="id" />" />
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
</body>
</html>