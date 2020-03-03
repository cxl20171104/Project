<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
     <meta http-equiv="pragma" content="no-cache"> 

     <meta http-equiv="cache-control" content="no-cache"> 

     <meta http-equiv="expires" content="0">   
<title>文章发布</title>
<style type="text/css">
.article_main{
width:1200px;
border:#d2d2d2 1px solid;
float:left;
}
.article_main .main_tl {
background:#F7F7F7;
padding: 10px;
position: relative;
text-align: center;
}
.article_main .main_tl h5 {
color: #115fc3;
font-size: 26px;
font-family:"Microsoft Yahei";
font-weight:normal;
line-height: 40px;
text-align: center;
}
.article_main .main_tl li {
color: #999;
display: inline;
margin: 0 10px;
}
.article_main p{
font-size: 14px;
line-height: 30px;
margin: 10px 30px;
text-align: left;
}
#comlist{
float:left;
font-size: 26px;
width:1200px;
margin:10px 30px;
}
</style>
<script type="text/javascript">
   $(function(){
	   <c:if test="${fn:contains(content, '&')}"> 
    var content = "${content}";
    content = content.replace(/&amp;/g,"&");
    content = content.replace(/&quot;/g,"'");
    content = content.replace(/&lt;/g,"<");
    content = content.replace(/&gt;/g,">");
    //content = content.replace(/&nbsp;/g," ");
    document.getElementById("editor").innerHTML=content;
    </c:if>
   });
</script>
</head>
<body>
       <div class="article_main">
			<div class="main_tl">
				<h5 style="padding-bottom:10px; margin-top:30px;" id="print_title">${article.title}</h5>
				<ul>
					<li>作者:${article.inputerName}</li>
				</ul>
			</div>
			<div style="margin:15px 0px">
			     <div align="center">
			     <c:forEach items="${urls}"  var="url" >
			                <img src="${url}" style="height:400px;width:50%"/>
			     </c:forEach>
			     </div>
				 <c:choose>
					   <c:when test="${fn:contains(content, '&')}">
					      <div id="editor"> 
				          </div>
					   </c:when>
					   <c:otherwise>
					      <div id="editor">${content}
				            </div>
					   </c:otherwise>
					</c:choose>
			</div>
		</div>
</body>
</html>