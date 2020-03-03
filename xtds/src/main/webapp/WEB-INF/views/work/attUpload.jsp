<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>附件管理</title>
<script type="text/javascript"
	src="${staticPath }/static/dmuploader.js" charset="utf-8"></script>
<script>
$(function(){
 $("#upload").change(function () {
                var formData = new FormData();
                formData.append("upload", document.getElementById("upload").files[0]);   
                $.ajax({
                    url: "${path}/com/upload",
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
                    console.log(data.success);
                        if (data.success) {
                        
                             $("#filepath").val(data.obj);
                             $("#asize").val(data.msg);
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
            
            	$("#attForm").form({
 		url:"${path}/att/save",
 		onSubmit:function(){
 			var val = $(this).form("validate");
 			if(val)
 			return true;
 			else{
 			return false;
 			}
 			
 		},
 		success:function(data){
 		data = eval("("+data+")");
 			if(data==1){
 				$.messager.alert('提示', "保存成功", 'warning');
 			}else{
 			$.messager.alert('提示', "失败，请重试", 'warning');}
 		}
 	});
            });
 function saveFun(){
 
 	$("#attForm").form("submit");
 }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
		<shiro:hasPermission name="/company/add">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
				onclick="saveFun();">保存</a>
		</shiro:hasPermission>
	</div>
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="attForm" method="post">
			<table class="grid">
				<tr>
					<td>附件名称</td>
					<td colspan="3"><input name="name" type="text"
						placeholder="请输入附件名称" class="easyui-validatebox"
						data-options="required:true"></td>
				</tr>

				<tr>
					<td>请选择附件</td>
					<td><input name="uoload" id="upload" type="file" /><input type="hidden" name="asize" id="asize"/><input type="hidden" name="url" id="filepath"/></td>
					<td><span id="errorMsg" style="display:none;color:red">请刷新重试</span></td>
				</tr>
				<tr>
					<td>描述</td>
					<td colspan="3"><textarea style="width:80%" id="description"
							name="descn" rows="" cols=""></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>