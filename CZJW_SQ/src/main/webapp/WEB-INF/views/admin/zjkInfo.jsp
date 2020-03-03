<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript">
$(function(){
	$("#headpicFile").change(
			function() {
				if ($("#headpicFile").val() != null
						&& $("#headpicFile").val() != ""
						&& $("#headpicFile").val() != undefined) {
					var formData = new FormData();
					formData.append("upload", document.getElementById("headpicFile").files[0]);
					$.ajax({
						url : "${path}/com2/upload2",
						type : "POST",
						dataType : "json",
						data : formData,
						/**
						 *必须false才会自动加上正确的Content-Type
						 */
						contentType : false,
						/**
						 * 必须false才会避开jQuery对 formdata 的默认处理
						 * XMLHttpRequest会对 formdata 进行正确的处理
						 */
						processData : false,
						success : function(data) {
							
							if (data.success) {
								$("#headpic").val(data.obj);
								$("#previewFile").attr("src","${path}/static/" +data.obj );
							} else {
								alert("图片上传失败，请重试");
								$("#headpicFile").val("");
							}
						},
						error : function() {
							alert("图片上传失败，请重试");
							$("#headpicFile").val("");
						}
					});
				}
				
				
			

			});


	
});
function save(){
	if($('#zjkForm').form('validate')){
		var url = '';
		if($('#id').val()==null||$('#id').val()==''){
			url = '${path }/zjk/add';
		}else{
			url = '${path }/zjk/edit';
		}
		$.ajax({
			url:url,
			data:$('#zjkForm').serialize(),
			dataType:'json',
			type:'post',
			success:function(result){
				refreshTable();
				closeDialog();
				$.messager.show({
					title:'消息提示',
					msg:result.msg,
					timeout:300,
					showType:'slide'
				});
			}
		});
	}
	
}

function uploadFile() {
	$("#headpicFile").click();

}	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
  <div data-options="region:'center',border:false">
	<form id="zjkForm" method="post">
		<input type="hidden" id="id" name="id" value="${zjk.id }" />
	    <table class="formTable" width="536" height="280">
	    	<tr>
	    		<th>姓名：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="name" name="name" value="${zjk.name }" /></td> 
	    		<th>性别：</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'男'},{value:2,text:'女'}]"  id="sex" name="sex" value="${zjk.sex }" /></td>  
	    		<td colspan="3" rowspan="5">
	    		<c:choose>
									<c:when test="${zjk!=null and zjk['filePath']!='' and  zjk['filePath']!=null }">
										<img id="previewFile" style="display:block; width: 204px;margin: 0 auto;height: 120px;" onclick="uploadFile();" src="${path }/static/${zjk.filePath }" />
										<input name="filePath" id="headpic" type="hidden" value="${zjk.filePath }" />
										<input name="headpicFile" id="headpicFile" type="file" style="display:none" />
									</c:when>
									<c:otherwise>
										<img id="previewFile" style="display:block; width: 204px;margin: 0 auto;height: 120px;" onclick="uploadFile();" src="${path }/static/images/tpsc.jpg" />
										<input name="filePath" id="headpic" type="hidden" value="" />
										<input name="headpicFile" id="headpicFile" type="file" style="display:none" />
									</c:otherwise>
								</c:choose>
	    		
	    		
	    		</td> 
	    	</tr>
	    	<tr>
	    	    <th>民族：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="nation" name="nation" value="${zjk.nation }" /></td> 
	    		<th>出生年月：</th>
	    		<td><input type="text" class="laydate-icon"    readonly="readonly" onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="birthday" name="birthday" value="${zjk.birthday }" /></td>  
	    		
	    		
	    		
	    	</tr>
	    	
	    	<tr> 
	    	    <th>籍贯:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="place" name="place" value="${zjk.place }" /></td>  
	    	    <th>出生地:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="bir_place" name="bir_place" value="${zjk.bir_place }" /></td>  
	    		
	    	    
	    	</tr>
	    	<tr>
	    	    <th>学历:</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'博士'},{value:2,text:'硕士'},{value:3,text:'本科'},{value:4,text:'大专'},{value:5,text:'其他'}]"  id="education" name="education" value="${zjk.education }" /></td>  
	    	    <th>毕业院校:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="graduated" name="graduated" value="${zjk.graduated }" /></td>  
	    	
	    	    
	    	
	    	
	    	</tr>
	    	<tr>
	    	    <th>专业:</th>
	    		<td><input type="text"  class="easyui-textbox" data-options="width:'100%',required:true"  id="major" name="major" value="${zjk.major }" /></td>  
	    	     <th>全日制教育:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="quan_ri_zhi" name="quan_ri_zhi" value="${zjk.quan_ri_zhi }" /></td>  
	    	    
	    	    
	    	</tr>
	    	 <tr>
	    	    <th>在职教育:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="zai_zhi" name="zai_zhi" value="${zjk.zai_zhi }" /></td>  
	    	    <th>是否健康:</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'否'},{value:2,text:'是'}]"  id="is_health" name="is_health" value="${zjk.is_health }" /></td>  
	    	    <th>从业时间:</th>
	    		<td><input type="text" class="laydate-icon"  readonly="readonly" onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="working_time" name="working_time" value="${zjk.working_time }" /></td>  
	    	</tr>
	    	<tr>    
	    	    <th>工作单位:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="over_unit" name="over_unit" value="${zjk.over_unit }" /></td>  
	    	    <th>职务:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="post" name="post" value="${zjk.post }" /></td>  
	    		<th>职级:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="rank" name="rank" value="${zjk.rank }" /></td>  
	    	</tr>
	    	<tr>	
	    		<th>是否党员:</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'否'},{value:2,text:'是'}]" id="party_member" name="party_member" value="${zjk.party_member }" /></td>  
	    		<th>入党时间:</th>
	    		<td><input type="text" class="laydate-icon" readonly="readonly"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="party_time" name="party_time" value="${zjk.party_time }" /></td>  
	            <th>联系方式:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="tell" name="tell" value="${zjk.tell }" /></td>         
	       
	       </tr>
	    	<%-- <tr> 
	    		<th>父亲姓名:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="father" name="father" value="${zjk.father }" /></td>  
	    		
	    		<th>母亲姓名:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="mother" name="mother" value="${zjk.mother }" /></td>  
	    	</tr>
	    	<tr> 
	    		<th>父亲工作单位:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="father_unit" name="father_unit" value="${zjk.father_unit }" /></td>  
	    		
	    		<th>母亲工作单位:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="mother_unit" name="mother_unit" value="${zjk.mother_unit }" /></td>  
	    	</tr>
	    	<tr> 
	    		<th>配偶姓名:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="spouse" name="spouse" value="${zjk.spouse }" /></td>  
	    		
	    		<th>配偶工作单位:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="spouse_unit" name="spouse_unit" value="${zjk.spouse_unit }" /></td>  
	    	</tr>
	    	<tr> 
	    		<th>子女信息:</th>
	    		<td  colspan="3"><textarea  id="children" name="children" >${zjk.children }</textarea></td>  
	    		</tr>
	    		<tr> 
	    		<th>近亲属信息:</th>
	    		<td  colspan="3"><textarea  id="relative" name="relative" >${zjk.relative }</textarea></td>  
	    		</tr>
	    	<tr>  --%>
	    	<tr> 
	    		<th>目前情况:</th>
	    		<td  colspan="5"><textarea   id="nowstate" name="nowstate"  >${zjk.nowstate }</textarea></td>  
	    	</tr>
	    	<tr> 
	    		<th>工作简历:</th>
	    		<td  colspan="5"><textarea   id="experience" name="experience"  >${zjk.experience }</textarea></td>  
	    	</tr>
	    	
	    	<tr> 
	    		<th>办案专长:</th>
	    		<td  colspan="5"><textarea   id="specialty" name="specialty"  >${zjk.specialty }</textarea></td>  
	    	</tr>
	    	<tr> 
	    		<th>纪律审查方面的工作经历:</th>
	    		<td  colspan="5"><textarea   id="ji_lv_shen_cha" name="ji_lv_shen_cha"  >${zjk.ji_lv_shen_cha }</textarea></td>  
	    	</tr>
	    	
	    	<tr> 
	    		<th>奖惩情况:</th>
	    		<td  colspan="5"><textarea   id="jiang_cheng" name="jiang_cheng"  >${zjk.jiang_cheng }</textarea></td>  
	    	</tr>
	    	<tr> 
	    		<th>备注:</th>
	    		<td  colspan="5"><textarea   id="remark" name="remark"  >${zjk.remark }</textarea></td>  
	    	</tr>
	    </table>
	</form>
	</div>
</div>
</html>