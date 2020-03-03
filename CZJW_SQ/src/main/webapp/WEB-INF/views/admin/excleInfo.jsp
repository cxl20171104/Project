<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath }/static/city.js">
</script>
<script type="text/javascript">
	function check(){
		// var fileName = form.filePath.value;
		 var filename = document.getElementById("filePath").value;
		 var reg= /^.*\.(?:xls)$/i;
		 if(filename==''){
		    alert("请选择文件名");
		    form.filePath.focus();
		    return false;
		 }else if(!reg.test(filename)){
		     alert("文件格式必须是：.xls");
		     return ;
		 }
		 document.getElementById("excleForm").submit();
	 }
	 
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="excleForm" name ="excleForm" action="${path }/excle/read" method="post">
	    <table class="formTable" >
	    	<tr>
	    		<th>请选择文件：</th>
	    		<td><input type="file" name="filePath" id="filePath" onclick="checksuff();"/>
	    		<input type=button onclick="check();" value="导入" />
	    		</td> 
	    	</tr>
	    </table>
	</form>
</div>
</html>