<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript">
function save(){
	if($('#companyForm').form('validate')){
		var url = '';
		if($('#id').val()==null||$('#id').val()==''){
			url = '${path }/company/add';
		}else{
			url = '${path }/company/edit';
		}
		$.ajax({
			url:url,
			data:$('#companyForm').serialize(),
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
	<form id="companyForm" method="post">
		<input type="hidden" id="id" name="id" value="${company.id }" />
	    <table class="formTable">
	    	<tr>
	    		<th>企业名称：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="name" name="name" value="${company.name }" /></td> 
	    	</tr>
	    	<tr> 
	    		<th>负责人：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="leader" name="leader" value="${company.leader }" /></td>  
	    	</tr>
	    	<tr> 
	    		<th>地址：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="address" name="address" value="${company.address }" /></td>  
	    	</tr>
	    	<tr> 
	    		<th>传真：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%'" id="fax" name="fax" value="${company.fax }" /></td>  
	    	</tr>
	    	<tr> 
	    		<th>电话：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%'" id="tel" name="tel" value="${company.tel }" /></td>  
	    	</tr>
	    </table>
	</form>
</div>
</html>