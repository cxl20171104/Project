<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function() {
        
        $('#pid').combotree({
        	width:'60%',
        	url : '${path }/organization/findOrganizationByPid',
            required: true,
            panelHeight : '600px',
            onBeforeExpand:function(node){
    	    	$('#pid').combotree('tree').tree('options').queryParams={pid:node.id};
    	    },
            onLoadSuccess:function(node,data){
            	var node = $('#pid').combotree('tree').tree('find', data[0].id);
            	$('#pid').combotree('tree').tree('expand', node.target);
            }
        });
        
        $('#organizationAddForm').form({
            url : '${path }/organization/add',
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
         
             var level=$("#le").val();
             $('#level').val(level);


            
    });
</script>
<div style="padding: 3px;">
    <input id="le"  value="${level}" />
    <form id="organizationAddForm" method="post">
        <table class="grid">
            <tr>
                <td>编号</td>
                <td><input name="code" type="text" placeholder="请输入部门编号" class="easyui-validatebox" data-options="required:true" ></td>
                <td>部门名称</td>
                <td><input name="name" type="text" placeholder="请输入部门名称" class="easyui-validatebox" data-options="required:true" ></td>
                
            </tr>
            <tr>
                <td>排序</td>
                <td><input name="seq"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false" value="0"></td>
                <td>菜单图标</td>
                <td><input  name="icon" value="icon-folder"/></td>
            </tr>
             <tr>
                <td>市局/区县</td>
                <td><select id="level" class="easyui-combobox" name="level" style="width:160px;">   
					    <option value="1">市局支部</option>   
					    <option value="2">区县支部</option>   
					</select></td>           
                <td>部门等级</td>
                <td><select id="grade" class="easyui-combobox" name="grade" style="width:160px;">   
					    <option value="1">党委</option>   
					    <option value="2">党总支</option>   
					    <option value="3">党支部</option>   
					    <option value="4">党小组</option>   
					</select></td>           
            </tr>
            <tr id="address">
                 <td>关联部门</td>
                 <td colspan="3"><input id="organ" class="easyui-combotree" name="address"  data-options="width:300,valueField:'id',textField:'',url:'${path}' + '/organization/organlist',prompt:'未选择'" />
                  <span style="color:red">**添加管理部门需要使用</span>
                  </td>
            </tr> 
            <tr>
                <td>上级部门</td>
                <td colspan="3"><select id="pid" name="pid" style="width:200px;height: 29px;"></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
                
            </tr>
        </table>
    </form>
</div>