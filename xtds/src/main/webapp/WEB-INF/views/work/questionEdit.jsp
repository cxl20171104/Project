<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
function save(){
	if($('#companyForm').form('validate')){
		var url = '';
			url = '${path }/quest/save';
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
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="companyForm" method="post">
			<table class="grid">
				<tr>
					<td>题目序号</td>
					<td>问题</td>
					<td>分值</td>
				</tr>
				<tr>
					<td><input style="width:30px" type="text"
						class="easyui-textbox" name="answer" value="${xh==null?quest.answer:xh}"
						readonly="readonly" /><input type="hidden" name="questionnaire"
						value="${questionnaire==null?quest.questionnaire:questionnaire }" /><input type="hidden" name="id"
						value="${quest.id }" />
					</td>
					<td><input name="topic" style="width:100%" placeholder="请输入问题"
						class="easyui-validatebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${quest.topic}">
					</td>
					<td><input style="width:30px" placeholder="分数"
						class="easyui-numberbox" type="text" name="score"
						value="${quest.score==null?0:quest.score }" />
					</td>
				</tr>

			</table>
		</form>
	</div>
</div>