<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id="back_form" method="post">
            <table class="grid">
                <tr>
                   <td><h2 >操作人</h2></td>
                   <td><input  name="executor" style="width:300px;height:45px;"  value="${delete_Clues.executor}"/></td>
                </tr>
                <tr>
                    <td><h2 >时间</h2></td>
                    <td ><input  value="<fmt:formatDate value="${delete_Clues.createTime}" dateStyle="full" />" style="width:200px;height:30px;"/></td>
                </tr>
                
                <tr>
                    <td><h2 >理由</h2></td>
                    <td colspan="3"><textarea name="remark" rows="12" cols="100" >${delete_Clues.reason}</textarea></td>
                </tr>
               
            </table>
        </form>
    </div>
</div>