<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${path }/static/QQtalk/css/chat.css" />
<!--[if lt IE 7]>
<script src="${path }/static/QQtalk/js/IE7.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
<script src="${path }/static/QQtalk/js/iepng.js" type="text/javascript"></script>
<script type="text/javascript">
EvPNG.fix('body, div, ul, img, li, input, a, span ,label'); 
</script>
<![endif]-->
<script type="text/javascript">
	function cancleDialog() {
		closeDialog();
	}

	function message() {
	}

	function feedBack() {
		var e = new Date, f = "";
		f += e.getFullYear() + "-", f += e.getMonth() + 1 + "-", f += e
				.getDate()
				+ "  ", f += e.getHours() + ":", f += e.getMinutes() + ":",
				f += e.getSeconds();
		var g = $("#textarea").val();
		if(g !=null && g != undefined && g.replace(/^\s+|\s+$/g, "").length !=0){
		
	
	$.ajax({
			url:"${path}/feedBack/reply",
			data:{"content":g,"comid":$("#comid").val()},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.success){
				
				}else{
					alert(data.msg);
				}
			},
			error:function(data){
				alert("服务器故障，请稍后再试");
			}
		}); 
		var i = "<div class='message clearfix'>"
				+ "<div class='user-logo'>"
				+ "<img style='width:50px;height:50px;' src='" +  "${path}/static/images/dl.png" + "'/>"
				+ "</div>" + "<div class='wrap-text'>"
				+ "<h5 class='clearfix'>" + "${cuser.name}" + "</h5>" + "<div>" + g
				+ "</div>" + "</div>" + "<div class='wrap-ri'>"
				+ "<div clsss='clearfix'><span>" + f + "</span></div>"
				+ "</div>" + "<div style='clear:both;'></div></div>";
		$("#textarea").val("");
		$(".chat01_content").append(i);
			}else{
				alert("请输入回复内容");
			}
	}
</script>
<!--效果html开始-->
<div class="content">
	<div class="chatLeft">
		<div class="chat01">
			<div class="chat01_title">
				<ul class="talkTo">
					<li><a href="javascript:;">${user.name }</a></li>
				</ul>
			</div>
			<div class="chat01_content">
		
				<c:forEach items="${comment }" var="com" varStatus="status">
					<div class='message clearfix'>
						<div class='user-logo'>
							<img style='width:50px;height:50px;'
								src='http://localhost:8080/shiro/static/images/dl.png' />
						</div>
						<div class='wrap-text'>
							<h5 class='clearfix'>${com.cusername }</h5>
							<div>${com.content }</div>
						</div>
						<div class='wrap-ri'>
							<div clsss='clearfix'>
								<span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${com.ctime }"/></span>
							</div>
						</div>
						<div style='clear:both;'></div>
					</div>
					<c:if test="${status.last }"><input id="comid" type="hidden" value="${com.id }"/></c:if>
				</c:forEach>
			</div>
		</div>
		
		<div class="chat02">
			<div class="chat02_title"></div>
			<div class="chat02_content">
				<textarea id="textarea"></textarea>
			</div>
			<div class="chat02_bar">
				<ul>

					<li style=""><a href="javascript:;" onclick="feedBack();">
							<img src="${path }/static/QQtalk/img/send_btn.jpg"> </a> <a
						onclick="cancleDialog();" href="javascript:;"> <img
							src="${path }/static/images/dl.png"> </a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<!--效果html结束-->
