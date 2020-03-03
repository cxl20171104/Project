<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
	var url="";
	if($("#typpe").val()=="0"){
		$("#text").text("申请撤回");
		$("#text2").text("撤回理由");
	}else{
		$("#text").text("申请退回");
		$("#text2").text("申请理由");
	}
    $('#back_form').form({
        url: '${path }/probleClues/back_please',
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
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id="back_form" method="post">
            <input name="cluesId" type="hidden" value="${id}"/>
            <input id="type" name="type" type="hidden" value="${type}"/>
            <table class="grid">
                <tr>
                   <td><h2 >审批人</h2></td>
                   <td><input class="easyui-textbox" id="executor"name="executor" value=""   style="width:300px;height:45px;" required="required"> </td>
                </tr>
                <tr>
                  <td><h2 id="text"></h2></td>
                  <td><select name="reason" style="width:300px;height:45px;">
                  <option value="HB">回避</option>
                  <option value="FP">分派</option>
                  <option value="OTHER">其他</option>
                  </select></td>
                </tr>
                
                <tr>
                    <td><h2 id="text2"></h2></td>
                    <td colspan="3"><input class="easyui-textbox" id="remark"name="remark" value=""   style="width:300px;height:45px;" required="required"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>