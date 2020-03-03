<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
    $('#del_form').form({
        url: '${path }/ags/del',
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
        <form id="del_form" method="post">
            <input name="causeId" type="hidden" value="${ids}"/>
            <table class="grid">
                <tr>
                   <td><h2 >操作人</h2></td>
                   <td><input  name="executor" style="width:300px;height:45px;"  value=""/></td>
                </tr>
                <tr>
                    <td><h2 >删除理由</h2></td>
                    <td colspan="3"><textarea name="reason" rows="12" cols="100" ></textarea></td>
                </tr>
               
            </table>
        </form>
    </div>
</div>