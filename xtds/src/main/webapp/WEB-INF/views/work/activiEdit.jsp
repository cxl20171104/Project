<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<<style>
.img-responsive {
  display: inline-block;
  height: auto;
  max-width: 100%;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#headpicFile").change(function(){
			if($("#headpicFile").val() != null && $("#headpicFile").val() != "" && $("#headpicFile").val() != undefined){
			 var formData = new FormData();
	         formData.append("upload", document.getElementById("headpicFile").files[0]);   
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
	                      $("#picture").val(data.obj);
	                      $("#previewFile").attr("src","${path}/files/"+data.obj);
	                 }
	                else{	
	                    alert("图片上传失败，请重试");
	                    $("#headpicFile").val("");
	                 }
	             },
	             error: function () {
	            	   alert("图片上传失败，请重试");
	                   $("#headpicFile").val("");
	             }
	         });}
			
		});
		$('#roleEditForm').form({
			url : '${path }/activi/save',
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

	});
	
	function uploadFile(){
		$("#headpicFile").click();
		
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="roleEditForm" method="post">
			<table class="grid">
				<tr>
					<td>活动名称</td>
					<td><input name="id" type="hidden" value="${act.id}"><input
						name="dept" type="hidden" value="${act.dept}"> <input
						name="name" type="text" placeholder="请输入活动名称"
						class="easyui-validatebox" data-options="required:true"
						value="${act.name}">
					</td>
					<td rowspan="5" style="width:150px">
					<c:choose>
									<c:when test="${act!=null }">	
										<img id="previewFile" onclick="uploadFile();" src="${path }/files/${act.picture }" class="img-responsive" />
										<input name="picture" id="picture" type="hidden" value="${act.picture }"/>
										<input name="headpicFile" id="headpicFile" type="file" style="display:none" />
									</c:when>
									<c:otherwise>
										<img id="previewFile" onclick="uploadFile();" src="${path }/static/images/tpsc.jpg" class="img-responsive"/>
										<input name="picture" id="picture" type="hidden" value=""/>
										<input name="headpicFile" id="headpicFile" type="file" style="display:none" />
									</c:otherwise>
								</c:choose>
					</td>
				</tr>
				<tr>
					<td>活动主题</td>
					<td><input name="topic" placeholder="请输入活动主题"
						class="easyui-validatebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${act.topic}">
					</td>
				</tr>
				<tr>
					<td>活动类型</td>
					<td><select id="type" name="type" class="easyui-combobox"
						data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1">持续性活动</option>
							<option value="2">临时性活动</option>
					</select></td>
				</tr>
				<tr>
					<td>结束时间</td>
					<td><input name="etime" id="etime" placeholder="临时性活动时有效"
						class="easyui-datebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${act.etime}">
					</td>
				</tr>
				<tr>
					<td>是否启用</td>
					<td><select id="useable" name="useable"
						class="easyui-combobox"
						data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0">否</option>
							<option value="1" selected="selected">是</option>

					</select></td>
				</tr>
			</table>
		</form>
	</div>
</div>