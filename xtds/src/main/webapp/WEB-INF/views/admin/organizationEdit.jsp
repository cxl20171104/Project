<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function() {
        
    	 $('#pid').combotree({
         	width:'60%',
         	url : '${path }/organization/findOrganizationByPid',
             panelHeight : '600px',
             onBeforeExpand:function(node){
     	    	$('#pid').combotree('tree').tree('options').queryParams={pid:node.id};
     	    },
             onLoadSuccess:function(node,data){
             	var node = $('#pid').combotree('tree').tree('find', data[0].id);
             	$('#pid').combotree('tree').tree('expand', node.target);
             }
         });
        
        $('#organizationEditForm').form({
            url : '${path }/organization/edit',
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
                    parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为organization.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
            }
        });
        
    });
</script>
<div style="padding: 3px;">
    <form id="organizationEditForm" method="post">
        <table class="grid">
            <tr>
                <td>编号</td>
                <td><input name="id" type="hidden"  value="${organization.id}"><input name="code" type="text" value="${organization.code}" /></td>
                <td>资源名称</td>
                <td><input name="name" type="text" value="${organization.name}" placeholder="请输入部门名称" class="easyui-validatebox" data-options="required:true" ></td>
            </tr>
            <tr>
                <td>排序</td>
                <td><input name="seq"  class="easyui-numberspinner" value="${organization.seq}" style="widtd: 140px; height: 29px;" required="required" data-options="editable:false"></td>
                <td>菜单图标</td>
                <td ><input  name="icon" value="${organization.icon}"/></td>
            </tr>
            <tr>
                <td>市局/区县</td>
                <td><select id="level" class="easyui-combobox" name="level" style="width:160px;"  value="${organization.level}">   
					    <option value="1" <c:if test="${organization.level=='1'}">selected</c:if>>市局支部</option>   
					    <option value="2" <c:if test="${organization.level=='2'}">selected</c:if>>区县支部</option>   
					</select></td>
			     <td>部门等级</td>
                <td><select id="grade" class="easyui-combobox" name="grade" style="width:160px;" value="${organization.grade}">   
					    <option value="1" <c:if test="${organization.grade=='1'}">selected</c:if>>党委</option>   
					    <option value="2" <c:if test="${organization.grade=='2'}">selected</c:if>>党总支</option>   
					    <option value="3" <c:if test="${organization.grade=='3'}">selected</c:if>>党支部</option>   
					    <option value="4" <c:if test="${organization.grade=='4'}">selected</c:if>>党小组</option>
					</select></td>  
            </tr>
             <tr id="address">
                 <td>关联部门</td>
                 <td colspan="3"><input id="organ" class="easyui-combotree" name="address"  data-options="width:300,valueField:'id',textField:'',url:'${path}' + '/organization/organlist',prompt:'未选择'" />
                  <span style="color:red">**编辑管理部门需要使用</span>
                  </td>
            </tr> 
            <tr>
                <td>上级资源</td>
                <td colspan="3"><select id="pid" name="pid" style="width: 200px; height: 29px;"></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
            </tr>
            <tr>
                <td>部门概述</td>
                <td colspan="3">
                <input id="summarize" name="summarize" class="easyui-textbox" data-options="multiline:true,fit:true" value="${organization.summarize}" />
                </td>
            </tr>
        </table>
    </form>
</div>
