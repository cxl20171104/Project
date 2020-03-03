<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {

var buttons = $.extend([], $.fn.datebox.defaults.buttons);
		buttons.splice(1, 0, {
			text: '永久有效',
			handler: function(target){
				$("#qgp").datetimebox("setValue",'9999-12-31:23:59:59')
				$("#qgp").datetimebox('hidePanel');
			}
		});
		$("#qgp").datetimebox({
			buttons:buttons
		});
		$('#roleEditForm').form({
			url : '${path }/qnaire/save',
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="roleEditForm" method="post">
			<table class="grid">
				<tr>
					<td>问卷名称 <input name="pubshlisher" type="hidden"
						value="${qnaire.pubshlisher}" /> <%--<input name="nums" type="hidden"
						value="${qnaire.nums}" /> <input name="amount" type="hidden"
						value="${qnaire.amount}" />--%>
					</td>
					<td><input name="id" type="hidden" value="${qnaire.id}">
						<input name="name" type="text" placeholder="请输入问卷名称"
						class="easyui-validatebox" data-options="required:true"
						value="${qnaire.name}"></td>
				</tr>
				<tr>
					<td>问卷主题</td>
					<td><input name="activities" placeholder="请输入问卷主题"
						class="easyui-validatebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${qnaire.activities}"></td>
				</tr>
				<tr>
					<td>开始时间</td>
					<td><input name="btime" placeholder="允许开始答题的时间"
						class="easyui-datetimebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false" value="${btime}">
					</td>
				</tr>
				<tr>
					<td>结束时间</td>
					<td><input name="etime" placeholder="停止答题时间"
						class="easyui-datetimebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false" value="${etime}">
					</td>
				</tr>
				<%--				
				<tr>
					<td>积分分值</td>
					<td><input name="scores" placeholder="请输入积分分值(无为0)"
						class="easyui-validatebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${qnaire.scores}"></td>
				</tr>
				--%><%--
				<tr>
					<td>奖励积分</td>
					<td><input name="award" placeholder="额外的积分"
						class="easyui-validatebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${qnaire.award}"></td>
				</tr>
				--%>
				<tr>
					<td>卷面总分数</td>
					<td><input name="total" placeholder="问卷的总分值"
						class="easyui-validatebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${qnaire.total}"></td>
				</tr>
				<tr>
					<td>题目数量</td>
					<td><input name="amount" placeholder="问卷题目的数量"
					class="easyui-validatebox" style="width: 140px; height: 29px;"
					required="required" data-options="editable:false"
					value = "${qnaire.amount}"></td>
				</tr>
				<tr>
					<td>积分方式</td>
					<td><select value="${qnaire.scorestype }" id="scoretype"
						name="scorestype" class="easyui-combobox"
						data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="1">参加积分</option>
							<option value="2">等比积分</option>
					</select>
					</td>
				</tr>
				<%--<tr>
					<td>积分有效期</td>
					<td><input name="qgp"  id="qgp" placeholder="永久有限可选择9999-12-32"
						class="easyui-datetimebox" style="width: 140px; height: 29px;"
						required="required" data-options="editable:false"
						value="${qnaire.qgp}"></td>
				</tr>
			--%></table>
		</form>
	</div>
</div>