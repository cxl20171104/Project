<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function(){
	    $('#my_form').form({
            url : '${path}/acc/addAcc',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
	 $("#upload").change(function () {  
		 console.log("上传附件");
		 var formData = new FormData();
		 for(var i=0;i<document.getElementById("upload").files.length;i++){
			 formData.append("upload"+i, document.getElementById("upload").files[i]);   
		 }       
         $.ajax({
             url: "${path}/com/uploadmany",
             type: "POST",
             dataType:"json",
             data: formData,
             /**
             *必须false才会自动加上正确的Content-Type
             */
             contentType: false,
             /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
             processData: false,
             success: function (data) {
                 if (data.success) {                       
                      $("#filepath").val(data.obj);
                      console.log($("#filepath").val(data.obj));
                      $("#asize").val(data.msg);
                      $("#errorMsg").css("display","none");
                 }
                else{	
                    $("#errorMsg").css("display","inline");
                 }
             },
             error: function () {
                $("#errorMsg").css("display","inline");
             }
         });
      });
	  
      });
		
	

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
   <form id="my_form" method="post">
           	<input type="hidden"  name="caseid" value="${caseid}" />
           	<input type="hidden"  name="type" value="报告模板" />
        <table class="formTable">
              <tr>
	    		<th>标题：</th>
	    		<td colspan="2"><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="title" name="title" /></td>  
	    	  </tr>
	    	  <tr> 
	    		<th>备注：</th>
	    		<td colspan="2"><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="name" name="name" /></td>  
	    	 </tr>
	    	  <tr> 
	    		<th >分类：</th>
	    		<td colspan="2"><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="content" name="content" value="${acc.content }" /></td>  
	    	  </tr>
	    	 
	    	    <tr>
					<th>附件： </th>
					<td>
						<input style="background: #d3e2fd;border: 1px solid #c8cef2;outline: none;" name="uoload" id="upload" type="file" multiple="multiple" />
						<input type="hidden" name="asize" id="asize"/>
						<input type="hidden" required="required"  name="url" id="filepath"/>
					</td>
					<td><span id="errorMsg" style="display:none;color:red">文件内容不能为空,请刷新重试</span></td>
				</tr>
        </table>
   </form>
</div>