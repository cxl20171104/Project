<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>签名管理</title>
    <script type="text/javascript">
    $(function() {
        $('#signatureTable').datagrid({
            url : '${path }' + '/signature/findSignature',
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
                checkbox : true
            }, {
                width : '150',
                title : '部门',
                field : 'orgId',
                halign : 'center',
                formatter:function(value,row,index){
                	return row.organ.name;
                }
            }, {
                width : '100',
                title : '姓名',
                field : 'userId',
                halign : 'center',
				formatter:function(value,row,index){
					return row.user.name;
                }
            }, {
                width : '300',
                title : '签名',
                field : 'filePath',
                halign : 'center',
				formatter:function(value,row,index){
					if(value != null && value != ''){
						return '<img style="width:300px;height:100px" src="${path}/signature/getImageData?filePath='+value+'" />';
					}else{
						return '暂无签名';
					}
                }
            }] ],
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
        
        $('#searchOrgId').combotree({
        	width:200,
        	panelHeight:'auto',
        	url : '${path }/organization/findOrganizationByPid',
            onBeforeExpand:function(node){
    	    	$('#searchOrgId').combotree('tree').tree('options').queryParams={pid:node.id};
    	    },
            onLoadSuccess:function(node,data){
            	var node = $('#searchOrgId').combotree('tree').tree('find', data[0].id);
            	$('#searchOrgId').combotree('tree').tree('expand', node.target);
            }
        });
    });
	function saveFun(index){
		var row = $('#signatureTable').datagrid('getSelected');
		var formData = new FormData();
		if(row.id != null){
		  formData.append('id',row.id);
		}
		formData.append('userId',row.userId);
		formData.append("fileData",$("#upload")[0].files[0]);
		$.ajax({
			url:'${path}/signature/save',
			data:formData,
			dataType:'json',
			type:'post',
			processData : false, 
			contentType : false,
			success:function(result){
				if(result.success){
					$('#signatureTable').datagrid('load');
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
	function uploadFun(){
		var row = $('#signatureTable').datagrid('getSelected');
		if(row != null){
			$('#upload').click();
		}else{
			$.messager.show({
				title:'消息提示',
				msg:'请选择一个人员上传图片',
				timeout:3000,
				showType:'slide'
			});
		}
	}
	function searchFun(){
		$('#signatureTable').datagrid('load',{orgId:$('#searchOrgId').combotree('getValue')});
	}
	function deleteFun(){
		var rows = $('#signatureTable').datagrid('getChecked');
		if(rows.length>0){
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r){
				if (r){
					var ids = new Array();
					for(var i=0;i<rows.length;i++){
						if(rows[i].id!=null&&rows[i].id!=''){
							ids[ids.length] = rows[i].id;
						}
					}
					$.ajax({
						url:'${path }/signature/delete',
						data:{ids:ids.join()},
						dataType:'json',
						async : true,
						type:'post',
						success:function(result){
							if(result.success){
								$('#signatureTable').datagrid('load');
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
				msg:'请勾选要删除的签名信息',
				timeout:3000,
				showType:'slide'
			});
		}
	}
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
        <shiro:hasPermission name="/signature/save">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="uploadFun();" >上传</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/signature/delete">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
        </shiro:hasPermission>
        <span style="float:right;">
	        <label for="searchPlanNumber">部门：</label>
	        <input type="text" class="easyui-textbox" id="searchOrgId" />
	        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();" >查询</a>
	    </span>
    </div>
    <div data-options="region:'center',border:false">
    	<input type="file" id="upload" hidden="true" accept="image/png,image/gif,image/jpg" onchange="saveFun();" />
        <table id="signatureTable"></table>
    </div>
</body>
</html>