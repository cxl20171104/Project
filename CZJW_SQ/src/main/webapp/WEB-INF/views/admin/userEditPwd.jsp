<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#editUserPwdForm').form({
            url : '${path }/user/editUserPwd',
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
                    parent.$.messager.alert('提示', result.msg, 'info');
                    parent.$.modalDialog.handler.dialog('close');
                    $.post('${path }/logout', function(result) {
                        if(result.success){
                            progressClose();
                            window.location.href='${path }';
                        }
                    }, 'json');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
  //扩展easyui表单的验证  
    $.extend($.fn.validatebox.defaults.rules, {  
        //密码验证(只能包括 _ 数字 字母)   
        account: {//param的值为[]中值  
            validator: function (value, param) {  
                if (value.length < param[0] || value.length > param[1]) {  
                    $.fn.validatebox.defaults.rules.account.message = '密码长度必须在' + param[0] + '至' + param[1] + '范围';  
                    return false;  
                } else if(!/^\S*$/.test(value)){
                	 $.fn.validatebox.defaults.rules.account.message = '密码不可包含空格'; 
                	 return false;
                }else {  
                    if (!/^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)[0-9A-Za-z]{8,20}$/.test(value)) {  
                        $.fn.validatebox.defaults.rules.account.message = '密码必须需包含数字和大小写字母中至少两种字符的8-20位字符';  
                        return false;  
                    } else {  
                        return true;  
                    }  
                }  
            }, message: ''  
        }  
    })
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;">
            <form id="editUserPwdForm" method="post">
                <table>
                    <tr>
                        <th>登录名：</th>
                        <td><shiro:principal></shiro:principal></td>
                    </tr>
                    <tr>
                        <th>原密码：</th>
                        <td style="style="background:#e7effe;"><input style="background:#e7effe;border-radius:5px;" id="oldPwd" name="oldPwd" type="password" placeholder="请输入原密码" class="easyui-validatebox" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <th>新密码：</th>
                        <td style="style="background:#e7effe;"><input style="background:#e7effe;border-radius:5px;" id="pwd" name="pwd" type="password" placeholder="请输入新密码" class="easyui-validatebox" data-options="required:true" validtype="account[8,20]"></td>
                    </tr>
                    <tr>
                        <th>再次输入新密码：</th>
                        <td style="style="background:#e7effe;"><input style="background:#e7effe;border-radius:5px;" name="rePwd" type="password" placeholder="请再次输入新密码" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#editUserPwdForm input[name=pwd]\']'"></td>
                    </tr>
                </table>
            </form>
    </div>
</div>