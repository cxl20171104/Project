<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html style="height:100%">
	<head>
		<meta charset="UTF-8">
		 <%@ include file="/commons/basejs.jsp" %>
		<title></title>
		<script type="text/javascript" src="${staticPath }/static/page/login.js" charset="utf-8"></script>
	</head>
	<body style="height:100%">
			<form class="loginForm" method="post" id="loginform" style="height:100%">
				  <table style="margin:0 auto;width:100%;height:100%;background: url('${staticPath}/static/images/login_Bg.png');background-size: 100% 100%;">
				        <tr>
				          <td ></td>
				        </tr>
				        <tr>
				          <td ></td>
				        </tr>
				        <tr>
				          <td ></td>
				        </tr>
				        <tr>
				          <td ></td>
				        </tr>
				         <tr>
				          <td ></td>
				        </tr>
				         <tr>
				          <td style="padding-left:42%;"><span><strong>用户名</strong></span>&nbsp;&nbsp;<input   placeholder="请输入用户名" style="color:black;width:20%;height:35%" name="username" /></td>
				        </tr>
				        <tr>
				          <td style="padding-left:42%;"><strong><span>密&nbsp;&nbsp;&nbsp;码</span></strong>&nbsp;&nbsp;<input id="password" type="password"  placeholder="请输入密码"    style="color:black;width:20%;height:35%" name="password" /></td>
				       </tr>
				       <tr>
				          <td style="padding-left:44.5%;"><input class="subBtn" type="submit" value="登&nbsp;录"   onclick="submitForm();" style="width:20%;height:35%"/></td>
				       </tr>
				        <tr>
				          <td style="padding-left:44.5%;">版权所有 © 河北万方中天科技有限公司<br/>全国免费服务热线：400-089-0199<br/><span onclick=version(); style="color:green">市区版</span></td>
				       </tr>
				        <tr>
				          <td></td>
				       </tr>
				        <tr>
				          <td></td>
				       </tr>
				  </table>
			</form>
		<script type="text/javascript">
			
			function resetForm(){
				document.getElementsByClassName("loginForm")[0].reset();
			}
			function version(){
				var path=basePath + '/static/version/version.jsp';
				window.location.href=path;
			}
		</script>
	</body>
</html>
