<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
function save(){
	if($('#companyForm').form('validate')){
		var url = '';
			url = '${path }/opt/save';
		$.ajax({
			url:url,
			data:$('#companyForm').serialize(),
			dataType:'json',
			type:'post',
			success:function(result){
				var msg = "添加成功";
				if(result.msg==1){
					msg = "修改成功";
					refreshTable();
					closeDialog();
				}else{
					appendChild(result.obj);
					closeDialog();
				}
				$.messager.show({
					title:'消息提示',
					msg:msg,
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
					<td>是否为正确答案</td>
				</tr>
				<tr>
					<td><input style="width:30px" type="text"
						class="easyui-textbox" name="num" value="${xh==null?option.num:xh}"
						readonly="readonly" /><input type="hidden" name="question"
						value="${questionid==null?option.question:questionid }" /><input type="hidden" name="id"
						value="${option.id }" />
					</td>
					<td><input name="content" style="width:100%" placeholder="请输入选项"
						class="easyui-validatebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${option.content}">
					</td>
					<td>
						<input name="isanswer" type="radio" value="1"
								id="showOnTop21" /> <label for="showOnTop21">是</label> <input checked="checked"
								name="isanswer" type="radio" value="2" id="showOnTop11" /> <label
								for="showOnTop11">否</label>
					</td>
				</tr>

			</table>
		</form>
	</div>
</div>