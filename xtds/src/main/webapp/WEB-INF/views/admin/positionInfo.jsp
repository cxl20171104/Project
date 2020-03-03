<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript">
function save(){
	if($('#positionForm').form('validate')){
		var url = '';
		if($('#id').val()==null||$('#id').val()==''){
			url = '${path }/position/add';
		}else{
			url = '${path }/position/edit';
		}
		$.ajax({
			url:url,
			data:$('#positionForm').serialize(),
			dataType:'json',
			type:'post',
			success:function(result){
				refreshTable();
				closeDialog();
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="positionForm" method="post">
		<input type="hidden" id="id" name="id" value="${position.id }" />
	    <table class="formTable">
	    	<tr>
	    		<th>职位名称：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="name" name="name" value="${position.name }" /></td> 
	    	</tr>
	    	<tr> 
	    		<th>是否可以查看：</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:1,text:'是'},{value:0,text:'否'}],value:0" id="isView" name="isView" value="${position.isView }" /></td>  
	    	</tr>
	    	<tr>
	    		<th>备注：</th>
	    		<td><textarea id="remark" name="remark">${position.remark }</textarea></td> 
	    	</tr>
	    </table>
	</form>
</div>
</html>