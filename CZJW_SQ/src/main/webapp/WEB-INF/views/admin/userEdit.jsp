<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	 var roleIds = ${roleIds};
    	$('#organizationId').combotree({
        	width:'100%',
        	url : '${path }/organization/findOrganizationByPid',
            required: true,
            panelHeight : 'auto',
            onBeforeExpand:function(node){
    	    	$('#organizationId').combotree('tree').tree('options').queryParams={pid:node.id};
    	    },
            onLoadSuccess:function(node,data){
            	var node = $('#organizationId').combotree('tree').tree('find', data[0].id);
            	$('#organizationId').combotree('tree').tree('expand', node.target);
            },
            value : '${user.organizationId}'
        });

    	  $('#roleIds').combotree({
        	width:'100%',
            url: '${path }/role/tree',
            multiple: true,
            required: true,
            panelHeight : 'auto',
            value : roleIds
        });
   
        $('#positionId').combobox({
        	width:'100%',
            url: '${path }/position/findPosition',
            required: true,
            panelHeight : 'auto',
            valueField:'id',
            textField:'name',
            editable: false,
            value:'${user.positionId }' ,
            loadFilter:function(data){
            	if(data.obj){
            		return data.obj;
            	}else{
            		return obj;
            	}
            }
        });

        $('#userEditForm').form({
            url : '${path }/user/edit',
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
        $("#status").val('${user.status}');
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <form id="userEditForm" method="post">
        	<input type="hidden" id="id" name="id" value="${user.id }" />
        	<input type="hidden" id="usertype" name="usertype" value="${user.usertype }" />
        	<input type="hidden" id="password" name="password" value="${user.password }" />
            <table class="formTable">
                <tr>
                    <th>登录名：</th>
                    <td><input name="loginname" type="text" placeholder="请输入登录名称" class="easyui-textbox" data-options="width:'100%',required:true" value="${user.loginname }" /></td>
                    <th>姓名：</th>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-textbox" data-options="width:'100%',required:true" value="${user.name }" /></td>
                </tr>
                <tr>
                    <th>性别：</th>
                    <td>
                        <select name="sex" class="easyui-combobox" data-options="width:'100%',editable:false,panelHeight:'auto',value:${user.sex }">
                            <option value="0" selected="selected">男</option>
                            <option value="1" >女</option>
                        </select>
                    </td>
                    <th>年龄：</th>
                    <td><input type="text" name="age" class="easyui-numberbox" data-options="width:'100%'" value="${user.age }" /></td>
                </tr>
                <tr>
                    <th>用户状态：</th>
                    <td>
                        <select id="status" name="status" class="easyui-combobox" data-options="width:'100%',height:29,editable:false,panelHeight:'auto'">
                                <option value="0">正常</option>
                                <option value="1">停用</option>
                        </select>
                    </td>
                    <th>职位：</th>
                    <td>
                        <input type="text" id="positionId" name="positionId" />
                    </td>
                </tr>
                <tr>
                    <th>部门：</th>
                    <td><select id="organizationId" name="organizationId"></select></td>
                    <th>角色：</th>
                    <td><select id="roleIds" name="roleIds" ></select></td>
                </tr>
                <tr>
                    <th>手机：</th>
                    <td>
                        <input type="text" name="phone" class="easyui-numberbox" data-options="width:'100%'" value="${user.phone }" />
                    </td>
                    <th>固定电话：</th>
                    <td>
                        <input type="text" name="phone1" class="easyui-numberbox" data-options="width:'100%'" value="${user.phone1 }" />
                    </td>
                </tr>
                <tr>
                    <th>分机号：</th>
                    <td>
                        <input type="text" name="phone2" class="easyui-numberbox" data-options="width:'100%'" value="${user.phone2 }" />
                    </td>
                    <th>邮箱：</th>
                    <td>
                        <input type="text" name="email" class="easyui-textbox" data-options="width:'100%'" value="${user.email }" />
                    </td>
                  </tr>
                  <tr>
                     <!-- <th>分管部门：</th>
                      <td >
                        <select id="organIds" name="organId" ></select>
                    </td> -->
                </tr>
            </table>
        </form>
    </div>
</div>