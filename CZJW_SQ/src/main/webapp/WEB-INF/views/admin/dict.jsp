<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>数据字典管理</title>
<script type="text/javascript">
$(function(){
	$('#dictTree').tree({    
	    url: '${path}/dict/findDictByDictPid', 
	    queryParams:{dictPid:0},
	    onBeforeExpand:function(node){
	    	$('#dictTree').tree('options').queryParams={dictPid:node.dictId};
	    },
	    onClick:function(node){
	    	$('#dictForm').form('load',node);
	    	var pNode = $('#dictTree').tree('getParent',node.target);
	    	if(pNode==null){
		    	$('#dictPname').textbox('setValue','无');
	    	}else{
		    	$('#dictPname').textbox('setValue',pNode.name);
	    	}
	    },
	    loadFilter: function(data){    
	        if (data.obj){    
	            return data.obj;    
	        } else {    
	            return data;    
	        }    
	    }    
	}); 
});
function saveFun(){
	if($('#dictForm').form('validate')){
		if($('#dictPid').val()==null||$('#dictPid').val()==''){
			$('#dictPid').val(0);
		}
		$.ajax({
			url:'${path}/dict/save',
			data:$('#dictForm').serialize(),
			dataType:'json',
			type:'post',
			success:function(result){
				$('#dictForm').form('clear');
				$('#dictTree').tree('options').queryParams={dictPid:0};
				$('#dictTree').tree('reload');
				$.messager.show({
					title:'消息提示',
					msg:result.msg,
					timeout:3000,
					showType:'slide'
				});
			}
		});
	}
}
function addFun(){
	$('#dictForm').form('clear');
	var node = $('#dictTree').tree('getSelected');
	if(node != null){
		$('#dictPid').val(node.dictId);
		$('#dictPname').textbox('setValue',node.name);
	}else{
		$('#dictPid').val(0);
		$('#dictPname').textbox('setValue','无');
	}
}
function deleteFun(){
	var node = $('#dictTree').tree('getSelected');
	if(node != null){
		if($('#dictTree').tree('isLeaf',node.target)){
			$.messager.confirm('确认对话框', '您确定要删除吗？', function(r){
				if (r){
					$.ajax({
						url:'${path}/dict/delete',
						data:{ids:node.id},
						dataType:'json',
						type:'post',
						success:function(result){
							$('#dictForm').form('clear');
							$('#dictTree').tree('options').queryParams={dictPid:0};
							$('#dictTree').tree('reload');
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
				msg:'请先删除子节点',
				timeout:3000,
				showType:'slide'
			});
		}
	}else{
		$.messager.show({
			title:'消息提示',
			msg:'请选择要删除的节点',
			timeout:3000,
			showType:'slide'
		});
	}
}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
        <shiro:hasPermission name="/dict/save">
        	<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFun();" >添加</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="saveFun();" >保存</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/dict/delete">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
        </shiro:hasPermission>
    </div>
    <div data-options="region:'west',split:false,border:false" style="width:200px;border-right:1px solid #d3d3d3">
    	<ul id="dictTree"></ul>
    </div>
	<div data-options="region:'center',fit:true,border:false">
		<form id="dictForm">
			<input type="hidden" id="id" name="id" />
			<input type="hidden" id="dictId" name="dictId" />
			<input type="hidden" id="dictPid" name="dictPid" />
			<table class="formTable">
				<tr>
					<th>上级字典：</th>
					<td><input type="text" class="easyui-textbox" data-options="width:'100%'" id="dictPname" name="dictPname" /></td>
				</tr>
				<tr>
					<th>字典名称：</th>
					<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="name" name="name" /></td>
				</tr>
				<tr>
					<th>字典值：</th>
					<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="value" name="value" /></td>
				</tr>
				<tr>
					<th>备注：</th>
					<td><textarea id="remark" name="remark" ></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>