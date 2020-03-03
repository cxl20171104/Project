<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
	var url="";
	if($("#typpe").val()=="0"){
		$("#text").text("撤回类型");
	}else{
		$("#text").text("退回类型");
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
                   <td><input  name="executor" style="width:300px;height:45px;"  value="${back.executor}"/></td>
                </tr>
                <tr>
                    <td><h2 >时间</h2></td>
                    <td ><input  value="<fmt:formatDate value="${back.createTime}" dateStyle="full" />" style="width:200px;height:30px;"/></td>
                </tr>
                
                <tr>
                    <td><h2 >理由</h2></td>
                    <td colspan="3"><textarea name="remark" rows="12" cols="100" >${back.remark}</textarea></td>
                </tr>
               
            </table>
        </form>
    </div>
</div>