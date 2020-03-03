<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript">
function save(){
	if($('#caseClueForm').form('validate')){
		var url = '';
		if($('#id').val()==null||$('#id').val()==''){
			url = '${path }/pay/add';
		}else{
			url = '${path }/pay/edit';
		}
		var obj = $('#caseClueForm').serializeObject();
		console.log(obj);
		//不为上级交办案件时，清空这些上级交办值
		$.ajax({
			url:url,
			data:obj,
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

  
  $(function(){
  
     
    
  
  });
  
     function organChang(newValue, oldValue){
          console.log(newValue);
          $("#users2").combobox('clear'); 
          if(newValue!=null){
             var url='${path}' + '/organization/organUsers?id='+newValue;
            $("#users2").combobox('reload',url);
          }else{
             $("#users2").combobox('reload'); 
          }
         
     }




</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	    <form id="caseClueForm">
	    <input type="hidden" id="id" name="id" value="${pay.id }" />
	    <table class="formTable">
	    	<tr>
	    	
	            <th>部门名称：</th>
	    		<td><input id="organ2"   /></td>      	
	    		<th>党员姓名：</th>
	    		<td><input id="users2" class="easyui-combobox" name="userid"  data-options="valueField:'id',textField:'name',url:'${path}' + '/user/getAll'" /></td> 
	    		
	    		
	    		
	    	</tr>
	    	<tr> 
	    		
	    		 <th>支付时间：</th>
	    		 <td><input type="text" class="easyui-datebox" data-options="width:'100%'" id="payTime" name="payTime" value="${pay.payTime }" /></td> 
	    		 <th>支付方式：</th>
	    		 <td><input id="payType" name="payType" class="easyui-combobox" data-options="valueField: 'value',textField: 'text',
		data: [{
			value: 0,
			text: '请选择'
		},{
			value: 1,
			text: '微信支付'
		},{
			value: 2,
			text: '支付宝支付'
		}] "  value="${pay.payType} " /></td> 
	    		 
	    	</tr>
	    	
	    	
	    	
	    	<tr>
	    	
	    	<th>支付金额：</th>
	    	<td ><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="money" name="money" value="${pay.money }" /></td> 
	    	</tr>
	    
	    </table>
	     </form>
	   </div>
     
