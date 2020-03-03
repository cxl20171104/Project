<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
//excel导入
function importExcel(){
	$.messager.alert('提示','正在导入中,请稍后...', 'info');   
	var formData = new FormData();
	if($("#fileData")[0] != undefined){
		formData.append("fileData",$("#fileData")[0].files[0]);
	}
	var url;
	var rsj ='0';
	url='${path}/probleClues/importExcel';
	if(rsj!='1'){
		$.ajax({
			url:url,
			data:formData,
			dataType:'json',
			type:'post',
			processData : false, 
			contentType : false,
			success:function(result){
			      console.log(result);
			      $(".messager-body").window('close');
				  $("#msg0").text(result.msg0);
				
			}
		});
	}
}

function fileSelect(){
		$("#fileData").val("");
		$('#fileData').click();
}

</script>
<style>
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:300px;}
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
       <table class="cxl">
           <tr>
              <td>1.填写序号</td>
              <td>Excel模板中序号必须填写,并从1开始</td>
           </tr>
           <tr>
              <td><span>2.所属领域填写格式：</span></td>
              <td>财政,水利,国土,扶贫,建设,党群</td>
           </tr>
            <tr>
              <td>3.<span>反映违纪性质填写格式：</span></td>
              <td>违反组织纪律，违反政治纪律</td>
           </tr>
            <tr>
              <td><span style="color:red">4.所有时间格式格式：</span></td>
              <td>2018年6月5日__2018/6/5__2018\6\5__2018.6.5__2018-6-5</td>
           </tr>
           <tr>
              <td>5.导入</td>
              <td> <a  class="easyui-linkbutton" data-options="iconCls:'icon-list'" onclick="fileSelect();" >选择文件</a> </td>
           </tr>
           <tr>
              <td>导入条数</td>
              <td id="msg0"></td>
           </tr>
       </table>
       <form id="importForm" method="post" enctype="multipart/form-data" style="display: none;">
            	<input type="file" id="fileData" name="fileData" onchange="importExcel()" value="" />
       </form>
</div>