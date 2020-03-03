<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var  fileId;//储存地址文本框的id
var  type;//文件名称
function addStart(urll,name){
	var data= decodeURIComponent($('#accForm').serialize(),true);
	console.log(data);
	$.ajax({
		url:'${path}/acc/addAcc',
		data:data,
	    type:"post",
	    dataType:"json",
	    success:function(data){
	    	 if(name.indexOf("_")>=0){
           	      //给链接赋值
                 if(name.substring(name.indexOf("_")+1)=="初步核实处置情况报告"){
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_bg_B').val(urll);
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_bg_A').css('color','green');
                 }
           	      
                 if(name.substring(name.indexOf("_")+1)=="关于初步核实的处置意见"){
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_czyj_B').val(urll);
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_czyj_A').css('color','green');
                 }
                 
                 
                 if(name.substring(name.indexOf("_")+1)=="谈话函询处置情况报告"){
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_bg_B').val(urll);
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_bg_A').css('color','green');
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_bg_C').css('color','red');               	 
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_bg_D').val(data.obj);
                 }
                 
                 
                 if(name.substring(name.indexOf("_")+1)=="谈话函询处置方案"){
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_fa_B').val(urll);
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_fa_A').css('color','green');
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_fa_D').val(data.obj);
                	 $("#"+name.substring(0,name.indexOf("_"))+'thhx_fa_C').css('color','red');
                 }
                 
                 if(name.substring(name.indexOf("_")+1)=="关于暂存待查的处置意见"){
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_zcdc_B').val(urll);
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_zcdc_A').css('color','green');
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_zcdc_D').val(data.obj);
                	 $("#"+name.substring(0,name.indexOf("_"))+'cbhs_zcdc_C').css('color','red');
                 }
                 if(name.substring(name.indexOf("_")+1)=="暂存待查处置情况报告"){
                	 $("#"+name.substring(0,name.indexOf("_"))+'zcdc_bg_B').val(urll);
                	 $("#"+name.substring(0,name.indexOf("_"))+'zcdc_bg_A').css('color','green');
                	 $("#"+name.substring(0,name.indexOf("_"))+'zcdc_bg_D').val(data.obj);
                	 $("#"+name.substring(0,name.indexOf("_"))+'zcdc_bg_C').css('color','red');
                 }
           	 	 }else{
           	 	 if(name=="上传初核请示"){
           	 	   $("#chqs_s_B").val(urll);
           	 	   $("#chqs_s_A").css('color','green');
           	 	   $("#chqs_s_D").val(data.obj);
         	 	   $("#chqs_s_C").css('color','red');
           	 	 }
           	 	 if(name=="初核方案"){
           	 	   $("#chfa_s_B").val(urll);
         	 	   $("#chfa_s_A").css('color','green');
         	 	   $("#chfa_s_D").val(data.obj);
       	 	       $("#chfa_s_C").css('color','red');
          	 	 }
           	 	 if(name=="审查报告"){
           	 	   $("#scbg_s_B").val(urll);
         	 	   $("#scbg_s_A").css('color','green');
         	 	   $("#scbg_s_D").val(data.obj);
       	 	       $("#scbg_s_C").css('color','red');
          	 	 }
           	 	 if(name=="审查工作方案报告"){
           	 	   $("#scgzfa_s_B").val(urll);
         	 	   $("#scgzfa_s_A").css('color','green');
         	 	   $("#scgzfa_s_D").val(data.obj);
       	 	       $("#scgzfa_s_C").css('color','red');
          	 	 }
           	     if(name=="立案审查(调查)报告"){
            	 	 $("#lascdc_bg_B").val(urll);
          	 	     $("#lascdc_bg_A").css('color','green');
          	 	     $("#lascdc_bg_D").val(data.obj);
       	 	         $("#lascdc_bg_C").css('color','red');
           	 	 }
           	 	 
           	     if(name=="审查(调查)报告"){
         	 	 $("#scdc_bg_B").val(urll);
       	 	     $("#scdc_bg_A").css('color','green');
       	 	     $("#scdc_bg_D").val(data.obj);
   	 	         $("#scdc_bg_C").css('color','red');
        	 	 }
           	     
           	     if(name=="审理报告"){
           	    	 $("#slbg_l_B").val(urll);
          	 	     $("#slbg_l_A").css('color','green');
          	 	     $("#slbg_l_D").val(data.obj);
       	 	         $("#slbg_l_C").css('color','red');
           	     }
           	     if(name=="处分决定"){
           	    	 $("#cfjd_l_B").val(urll);
          	 	     $("#cfjd_l_A").css('color','green');
          	 	     $("#cfjd_l_D").val(data.obj);
       	 	         $("#cfjd_l_C").css('color','red');
        	     }
           	     if(name=="起诉意见书"){
           	    	 $("#qsyj_s_B").val(urll);
          	 	     $("#qsyj_s_A").css('color','green');
          	 	     $("#qsyj_s_D").val(data.obj);
       	 	         $("#qsyj_s_C").css('color','red');
       	          }
           	     
           	 	 if(name!="审查(调查)报告"&&name!="立案审查(调查)报告"&&$("#"+name+"other")){
           	     $("#"+name+"other_l_B").val(urll);
           	     $("#"+name+'other_l_A').css('color','green');
           	     $("#"+name+"other_l_D").val(data.obj);
        	     $("#"+name+'other_l_C').css('color','red');
           	 	 }
           	 	 }
	    	
	         $.messager.show({
				title:'我的消息',
				msg:data.msg,
				timeout:5000,
				showType:'slide'
				});
	    }
	})
	
}
function checkField(name){
	 console.log("上传附件");
	 console.log(name);
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
            	 if(name.indexOf("_")>=0){
            		 $("#types").val(name.substring(name.indexOf("_")+1));	 
               	 	 $("#name").val(name.substring(name.indexOf("_")+1));
               	 	 $("#title").val(name.substring(name.indexOf("_")+1));
            		 
            		 
            	 }else{
            		 $("#name").val(name);
               	 	 $("#title").val(name);
               	 	 $("#types").val(name);
            		 
            	 }
            	 
            	 
           	     //存储地址
           	 	 $("#filepath").val(urll);
                 $("input[name=asize]").attr("value",data.msg);
                 $("#errorMsg").css("display","none");
                 //存储数据
                 addStart(urll,name);
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