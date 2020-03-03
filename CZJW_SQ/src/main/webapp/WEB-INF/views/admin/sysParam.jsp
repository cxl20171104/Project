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
        $('#sysParamTable').datagrid({
            url : '${path }' + '/sysParam/findSysParam',
            fit : true,
            fitColumns : true,
            striped : true,
            rownumbers : true,
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
                hidden : true
            }, {
                width : '150',
                title : '参数名称',
                field : 'name',
                halign : 'center'
            }, {
                width : '100',
                title : '参数键',
                field : 'key',
                halign : 'center'
            }, {
                width : '100',
                title : '参数值',
                field : 'value',
                halign : 'center',
                editor:'textbox'
            } , {
                width : '200',
                title : '备份',
                field : 'remark',
                halign : 'center'
            } , {
            	width : '100',
                title : '操作',
                field : 'sz',
                halign : 'center',
                align : 'center',
                formatter:function(value,row,index){
               	 var str = '';
                    <shiro:hasPermission name="/sysParam/edit">
                        str += $.formatString('<a href="javascript:saveFun(\'{0}\');" >保存</a>', index);
                    </shiro:hasPermission>
                 return str;
                }
            }] ],
            onClickRow : function(index,row){
            	$('#sysParamTable').datagrid('beginEdit',index);
            },
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
    });
	function saveFun(index){
		$('#sysParamTable').datagrid('endEdit',index);
		var row = $('#sysParamTable').datagrid('getSelected');
		$.ajax({
			url:'${path}/sysParam/edit',
			data:row,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.success){
					$('#sysParamTable').datagrid('load');
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
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="sysParamTable"></table>
    </div>
</body>
</html>