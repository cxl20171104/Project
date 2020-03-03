<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>滴水先锋</title>
    <meta name="viewport" content="width=device-width">
    <%@ include file="/commons/basejs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/login.css" />
    <script type="text/javascript" src="${staticPath }/static/login.js" charset="utf-8"></script>
    <script type="text/javascript">
      function support(){
    	  var content="开发人员：扬亚辉，孙进波，陈晓亮，任圣杰\n联系电话：15614311202";
    	  alert(content);
    	  
    	  
      }

    
    </script>
</head>
<body onkeydown="enterlogin(event);" style="text-align: center;background:url('${staticPath }/static/images/login.jpg') top center no-repeat; background-size:cover;">
<div style="text-align: center;margin:10% auto;padding:40px 10px;height:350px;width:656px;background:url('${staticPath}/static/images/loginForm.png');background-size:100% 100%;
                     ">
	<div style="width:100%;text-align: center;margin-bottom:20px;">
		<img src="${staticPath}/static/images/loginTitle.png" />
	</div>
    <form method="post" id="loginform">
    	<table id="loginTable" style="width:75%;">
			<tr>
				<th style="height:70px;width:230px;text-align:right;font-size:18px;color:black;">登录名：</th>
				<td><input name="username" class="easyui-validatebox textbox" data-options="required:true" value="" style="width:100%;height:32px;" /></td>
			</tr>
			<tr>
				<th style="height:70px;width:230px;text-align:right;font-size:18px;color:black;">密&nbsp;&nbsp;&nbsp;&nbsp;码：</th>
				<td><input name="password" type="password" class="easyui-validatebox textbox" data-options="required:true" value="" style="width:100%;height:32px;" /></td>
			</tr>
		</table>
		<div style="padding-top:25px;">
			<input type="button" style="width:169px;height:47px;background:url('${staticPath }/static/images/dl.png') top center no-repeat;background-size:cover;border:none;" onclick="submitForm();" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" style="width:169px;height:47px;background:url('${staticPath }/static/images/chz.png') top center no-repeat;background-size:cover;border:none;" onclick="clearForm();" />
		</div>
    </form>
    
   
</div>

     <div>
      
      <input type="button" value="技术支持" onclick="support();" />
      
    </div>
</body>
</html>
