<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
     <meta http-equiv="pragma" content="no-cache"> 
     <meta http-equiv="cache-control" content="no-cache"> 
     <meta http-equiv="expires" content="0">   
<title>文章评论</title>
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
        $('#tt').treegrid({    
	    url:'${path}/article/grideTree?articleId='+'${article.id}'+'&random='+Date.parse(new Date()),
	    fitColumns:true,
	    rownumbers:true,
	    height:300,
	    width:1000, 
	    method:'get',   
	    idField:'id',
	    treeField:'cusername',
	     parentField:'articleid',  
	    columns:[[    
	        {title:'评论人',field:'cusername',halign:'center',width:100},    
	        {field:'content',title:'评论内容',width:300,halign:'center'},    
	        {field:'onusername',title:'被评论人',halign:'center',align:'center',width:80},
	         {field:'ctime',title:'评论时间',halign:'center',align:'center',width:120},
	        {field:'showable',title:'是否已删除',width:80,halign:'center',align:'center',formatter:function(value,row,index){
	            if(value=="0"){
	               return "已删除";
	            };
	            if(value=="1"){
	               return "未删除";
	            };
	        
	        }},	            
	        {field:'id',title:'操作',width:80,halign:'center',align:'center',formatter:function(value,row,index){
	             if(row.showable=="1"){
	                 if(row.ctype=="2"){
	                      return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="com(\''+row.articleid+'\',\''+row.cuser+'\',\''+row.cusername+'\',\''+value+'\');" >回复评论</a>'
	                 };
	               return '<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="com(\''+row.replyartid+'\',\''+row.cuser+'\',\''+row.cusername+'\',\''+value+'\');" >回复评论</a>'
	             };
	             return"";
	        
	        }}, 
	        {field:'ctype',title:'类型',width:80,hidden:true}, 
	        {title:'评论人ID',field:'cuser',hidden:true},     
	        {field:'onuser',title:'被评论人ID',width:80,hidden:true},    
	        {field:'replyartid',title:'评论的文章',width:80,hidden:true}
	           
	    ]]    
    });
        <c:if test="${fn:contains(content, '&')}"> 
            var content = "${content}";
            content = content.replace(/&amp;/g,"&");
            content = content.replace(/&quot;/g,"'");
            content = content.replace(/&lt;/g,"<");
            content = content.replace(/&gt;/g,">");
            content = content.replace(/&nbsp;/g," ");
            document.getElementById("editor").innerHTML=content;
        </c:if>
   });
   function com(artid,cuser,cusername,id){
          $("#articleId").val(artid);
          $("#onuser").val(cuser);
          $("#onusername").val(cusername);
          $("#id").val(id);     
          //$("#comdialog").dialog("open");
          var top = $("#struBtn").offset().top + 30;
          var left = $("#struBtn").offset().left;
          $('#comdialog').window('open').window('resize',{width:'500',height:'180',top: top,left:left});
   };
   function putFun(){
        $('#form').form('submit', {    
		    url:'${path}/article/addOne', 
		    onSubmit: function(){
		        $('#form').form('enableValidation');
		       return $('#form').form('validate');
		    },    
		    success:function(data){
		           var data=eval("("+data+")"); 
		        if(data.success){
		             $('#form').form('reset');
		              $('#tt').treegrid('reload');	
		             $("#comdialog").dialog("close");
		              
		        } else{
		            alert(data.msg);		        
		        }  
		    }    
   }); 
   };
   function outFun(){
        $('#form').form("reset");
         $("#comdialog").dialog("close");
       
   };
    $.extend($.fn.validatebox.defaults.rules, {    
    maxLength: {    
        validator: function(value, param){    
            return value.length <= param[0];    
        },    
        message: '输入文字长度超限'   
    }    
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
			<div>
			    <span>点赞量:${article.thumb}</span>&nbsp;&nbsp;&nbsp;&nbsp;
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    <span>点击量:${article.hits}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   	<a id="struBtn" href="javascript:void(0);" onclick="com('${article.id}','','','');" >点击评论</a>		
			</div>
		</div>
	    	<div id="comlist">
	    	   <table id="tt" style="width:600px;height:400px"></table>
            </div>
            <div  id="comdialog" class="easyui-dialog"   data-options="closed:true,
						                                        title : '填写评论信息', 
						                                        width : 500,
					                                            height :180,					                                            
					                                             fitColumns : true,
					                                             resizable:true,
					                                             model:true,
															     buttons:'#dialogButton'" >
				<div id="dialogButton">
					<a href="#" class="easyui-linkbutton" onclick="putFun()">保存</a>
					<a href="#" class="easyui-linkbutton" onclick="outFun()">取消</a>
				</div>
				<form id="form" class="easyui-form" data-options="novalidate:true"> 
				      <input id="articleId" name="articleId" type="hidden" value="">
				      <input id= "onuser" name="onuser" type="hidden" value="">
				      <input id="onusername" name="onusername" type="hidden" value="">
				      <input  id="id" name="id" type="hidden" value="">
				      <input class="easyui-textbox" name ="content" data-options="multiline:true,required:true,prompt:'请输入评论信息'" style="width:600px; height:400px "> 
				</form>
        </div>
</body>
</html>