<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>版本升级</title>
<script type="text/javascript"
	src="${staticPath }/static/dmuploader.js" charset="utf-8"></script>
<script>
$(function(){
 $("#upload").change(function () {
                var bath=$("#upload").val();               
                if(bath.length==0||!bath.endWith("wgt")){
                      $("#typeErrorMsg").css("display","inline");
                     return;
                }else{
                     $("#typeErrorMsg").css("display","none");   
                }
                var formData = new FormData();
                formData.append("upload", document.getElementById("upload").files[0]);   
                $.ajax({
                    url: "${path}/com/uploadApp",
                    type: "POST",
                    dataType:"json",
                    data: formData,
                    /**
                    *必须false才会自动加上正确的Content-Type
                    */
                    contentType: false,
                    /**
                    * 必须false才会避开jQuery对 formdata 的默认处理
                    * XMLHttpRequest会对 formdata 进行正确的处理
                    */
                    processData: false,
                    success: function (data) {
                        if (data.success) {                       
                             $("#filepath").val(data.obj);
                             $("#asize").val(data.msg);
                        }
                       else{	
                           $("#errorMsg").css("display","inline");
                        }
                    },
                    error: function () {
                       $("#errorMsg").css("display","inline");
                    }
                });
            });
            
        $("#attForm").form({
 		url:"${path}/att/addVersion",
 		onSubmit:function(){
 			var val = $(this).form("validate");
 			var url = document.getElementById("filepath").value;
 			alert(url);
 			if(!url){
 				 $.messager.alert('提示', "请上传wgt文件", 'warning');  
 			     return false;
 			}else  if(!val){
 				 $.messager.alert('提示', "请填写版本号", 'warning');  
 			    return false;
 	        }else{
 			    return true;
 			}
 		},
 		success:function(data){
 		   data = eval("("+data+")");
 			if(data.success){
 				$.messager.alert('提示', "保存成功", 'warning');
 			}else{
 			$.messager.alert('提示', "失败，请重试", 'warning');}
 		}
 	});
 	  $.ajax({
 	      url:"${path}/att/getNewestTime",
 	      dataType : 'json',
			async : true,
			type : 'post',
			success : function(data) {
				
				
			    if(data.success){
			       var inputs= $("#mytable").find("input");	   
			       for(var i=0;i<inputs.length;i++){
			    	   
			    	  if(inputs[i].id!=null&&data.obj[inputs[i].id]!=null){
			    		
			    		  $("#"+inputs[i].id).val(data.obj[inputs[i].id]);
			    	  }
			    	   
			    	   
			       }
			    }
			}
 	  })
 	
 	
            });
 function saveFun(){
 
 	$("#attForm").form("submit");
 }
 String.prototype.endWith=function(str){     
  var reg=new RegExp(str+"$");     
  return reg.test(this);        
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
			

	</div>
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="attForm" method="post">
			<table class="grid"  id="mytable">
				
				<tr>
				
				<td><hr></td>
				<td><hr></td>
				</tr>
				<tr>
					<td>请选择最新资源上传</td>
					<td><input name="uoload" id="upload" type="file" /><input type="hidden" name="asize" id="asize"/><input type="hidden" name="url" id="filepath"/><span id="typeErrorMsg" style="display:none;color:red">请上传.wgt文件</span></td>
					<td><span id="errorMsg" style="display:none;color:red">请刷新重试</span></td>
				</tr>
				<tr>
				     <td>版本号</td>
				     <td><input id="verNum" name="verNum" class="easyui-textbox" data-options="required:true"/><span style="color:red">*发布ios更新包请添加.1后缀</span></td>
				</tr>
				<tr>
				   <td>点击保存</td>
				   <td><a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="saveFun();">保存</a></td>
				</tr>
				<tr>
				
				<td><hr></td>
				<td><hr></td>
				</tr>
				<tr>
				    <td>安卓最新版本号</td>
				     <td><input id="avernum" name="avernum" readonly="readonly"/></td>
				</tr>
				<tr>
				    <td>安卓更新版本时间</td>
				    <td><input id="atime" name="atime" readonly="readonly"/></td>   
				</tr>
				<tr>
				    <td>文件存放位置</td>
				    <td><input id="aurl" name="aurl" readonly="readonly"/></td>   
				</tr>
				
				<tr>
				  
				<td><hr></td>
				<td><hr></td>
				</tr>
				
				<tr>
				    <td>苹果最新版本号</td>
				     <td><input id="ivernum" name="ivernum" readonly="readonly"/></td>
				</tr>
				<tr>
				    <td>上次更新版本时间</td>
				    <td><input id="itime" name="itime" readonly="readonly"/></td>   
				</tr>
				<tr>
				    <td>文件存放位置</td>
				    <td><input id="iurl" name="iurl" readonly="readonly"/></td>   
				</tr>
			</table>
		</form>
	</div>
</body>
</html>