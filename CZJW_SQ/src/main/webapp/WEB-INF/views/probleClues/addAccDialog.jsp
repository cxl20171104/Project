<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript">
var  fileId;//储存地址文本框的id
var  type;//文件名称
function addStart(){
	var data= decodeURIComponent($('#accForm').serialize(),true);
	console.log(data);
	$.ajax({
		url:'${path}/acc/addAcc',
		data:data,
	    type:"post",
	    dataType:"json",
	    success:function(data){
	    	 //给在哪上传的赋值
	         success("上传成功");
	    	 //清空表单
	    	 ClearForm("accForm");
	    }
	})
	
}
function checkField(name){
	console.log("上传附件");
	 var formData = new FormData();
	 for(var i=0;i<document.getElementById(name).files.length;i++){
		 formData.append(name+i, document.getElementById(name).files[i]);   
	 }
    $.ajax({
        url: "${path}/com/uploadmany",
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
            	 var urll = data.obj;
           	     //存储地址
           	 	 $("#filepath").val(urll);
           	 	 if(name.indexOf("_")!=-1){
           	 	 $("#types").val(name.substring(name.indexOf("_")+1));	 
           	 	 $("#name").val(name.substring(name.indexOf("_")+1));
           	 	 $("#title").val(name.substring(name.indexOf("_")+1));
           	 	 }else{
           	 	 $("#name").val(name);
           	 	 $("#title").val(name);
           	 	 $("#types").val(name);
           	 	 }
           	 	
                 $("input[name=asize]").attr("value",data.msg);
                 $("#errorMsg").css("display","none");
                 addStart();
            }
           else{	
               $("#errorMsg").css("display","inline");
            }
        },
        error: function () {
           $("#errorMsg").css("display","inline");
        }
    });
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
   <form id="accForm" method="post">
            <!-- 线索id -->
           	<input   type="hidden" id="cluesIdAcc"  name="caseId" value="${problemClues.id}" />
           	<input   type="hidden"  name="organId" id="organId" value="${problemClues.organId}" />
			<input   type="hidden"  name="type" id="types" value=""/>
            <input   type="hidden"  class="form-control" id="title" placeholder="请输入标题" name="title" />
            <input   type="hidden"  class="form-control"   id="name" placeholder="请输入名称" name="name"  />
            <input   type="hidden"  name="asize" id="asize"/>
			<input   type="hidden" name="url" id="filepath"/>
        <table class="table  table-bordered table-hover">
			
	    	 <!--  <tr> 
	    		<th >说明：</th>
	    		<td colspan="2"><input type="text" class="form-control"  id="content" name="content" /></td>  
	    	 </tr> -->
				  <tr>
					<th>附件： </th>
					<td>
						<input class="btn btn-info" name="upload" id="upload" type="file" multiple="multiple"  onchange="return checkField()"/>
						
					</td>
					<td><span id="errorMsg" style="display:none;color:red">文件内容不能为空,请刷新重试</span></td>
				</tr>
        </table>
   </form>
</div>