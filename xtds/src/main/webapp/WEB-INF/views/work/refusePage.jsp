<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
function save(){
	if($('#companyForm').form('validate')){
		var url = '';
			url = '${path }/article/audit';
			var data = {"type":"0","articleId":$("#articleId").val(),"refuse":$("#refuse").val()}
		$.ajax({
			url:url,
			data:data,
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
			},error:function(){
				refreshTable();
				closeDialog();
				$.messager.alert({
					title:'消息提示',
					msg:"服务器故障，请稍后再试",
					timeout:3000,
					showType:'slide'
				});
			}
		});
	}
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="companyForm" method="post">
			<table class="grid" style="width:100">
				<tr>
					<td><input type="hidden" id="articleId" value="${id }" />
					<c:if test="${type=='1' }">
						<textarea style="width:100%;height:800px;" id="refuse">${refusecontent }</textarea>
					</c:if>
					<c:if test="${type!='1' }">
						<div style="width:100%;height:800px;" id="refuse">${refusecontent }</div>
					</c:if>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>