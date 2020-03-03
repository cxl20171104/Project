<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript">
  function searchStart(){
	  var obj = $('#searForm').serializeObject();
	    /* if(obj.startDate!=""){
	    	 obj.startDate = DateUtil.dateToStr('yyyy-MM-dd HH:mm:ss',new Date(obj.startDate));
	    }
	    if(obj.endDate!=""){
	    	obj.endDate = DateUtil.dateToStr('yyyy-MM-dd HH:mm:ss',new Date(obj.endDate));
	    } */
	     console.log(obj.name);
			  $('#zjkTable').datagrid('load', {    
				      name: obj.name,
				      sex:obj.sex,
					  nation:obj.nation,
					  birthday_s:obj.birthday_s,
					  birthday_e:obj.birthday_e,
					  place:obj.place,
					  bir_place:obj.bir_place,
					  education:obj.education,
					  graduated:obj.graduated,
					  major:obj.major,
					  zai_zhi:obj.zai_zhi,
					  is_health:obj.is_health,
					  working_time_s:obj.working_time_s,
					  working_time_e:obj.working_time_e,
					  over_unit:obj.over_unit,
					  post:obj.post,
					  rank:obj.rank,
					  party_member:obj.party_member,
					  party_time_s:obj.party_time_s,
					  party_time_e:obj.party_time_e,
					  tell:obj.tell,
					  quan_ri_zhi:obj.quan_ri_zhi	  
		  });
  }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
   <form id="searForm" method="post">
        <input type="hidden" id="id" name="id" value="${zjk.id }" />
	    <table class="formTable" width="400" height="580">
	    	<tr>
	    		<th>姓名：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="name" name="name" value="${zjk.name }" /></td> 
	    		<th>性别：</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'男'},{value:2,text:'女'}]"  id="sex" name="sex" value="${zjk.sex }" /></td>  
	    		<th>民族：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="nation" name="nation" value="${zjk.nation }" /></td> 
	    	</tr>
	    	
	    	<tr>
	    	    <th>籍贯:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="place" name="place" value="${zjk.place }" /></td>  
	    	    <th>出生地:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="bir_place" name="bir_place" value="${zjk.bir_place }" /></td>  
	    		<th>联系方式:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="tell" name="tell" value="${zjk.tell }" /></td>         
	    	
	    	</tr>    
	    	</tr>
	    	<tr>
	    	    <th>学历:</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'博士'},{value:2,text:'硕士'},{value:3,text:'本科'},{value:4,text:'大专'},{value:5,text:'其他'}]"  id="education" name="education" value="${zjk.education }" /></td>  
	    	    <th>毕业院校:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="graduated" name="graduated" value="${zjk.graduated }" /></td>  
	    	     <th>专业:</th>
	    		<td><input type="text"  class="easyui-textbox" data-options="width:'100%',required:true"  id="major" name="major" value="${zjk.major }" /></td>  
	    	    
	    	
	    	
	    	</tr>
	    	
	    	<tr>
	    	 <th>在职教育:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="zai_zhi" name="zai_zhi" value="${zjk.zai_zhi }" /></td>  
	    	 <th>全日制教育:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="quan_ri_zhi" name="quan_ri_zhi" value="${zjk.quan_ri_zhi }" /></td>  
	    	   <th>工作单位:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="over_unit" name="over_unit" value="${zjk.over_unit }" /></td>  
	    	</tr>
	    	<tr>    
	    	    
	    	    <th>职务:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true" id="post" name="post" value="${zjk.post }" /></td>  
	    		<th>职级:</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'100%',required:true"  id="rank" name="rank" value="${zjk.rank }" /></td>  
	    		<th>是否党员:</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'是'},{value:2,text:'否'}]" id="party_member" name="party_member" value="${zjk.party_member }" /></td>  
	    	</tr>
	    	<tr>
	    	    
	    	    <th>是否健康:</th>
	    		<td><input type="text" class="easyui-combobox" data-options="width:'100%',panelHeight:'auto',panelMaxHeight:200,editable:false,data:[{value:0,text:'请选择'},{value:1,text:'是'},{value:2,text:'否'}]"  id="is_health" name="is_health" value="${zjk.is_health }" /></td>  
	    		 <th>从业时间 从:</th>
	    		<td><input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="working_time" name="working_time_s" value="${zjk.working_time }" /></td>  
	    		<th>到:</th>
	    		<td><input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="working_time" name="working_time_e" value="${zjk.working_time }" /></td>  
	    	</tr>
	    	
	    	
	    	<tr>	
	    		
	    		<th>入党时间 从:</th>
	    		<td><input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="party_time" name="party_time_s" value="" /></td>
	    		<th>到:</th>
	    		<td><input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="party_time" name="party_time_e" value="" /></td>    
	           
	       
	       </tr>
	       <tr>
	    	    
	    	    
	    	    <th>出生年月 从:</th>
	    		<td><input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'101%'" readonly="readonly" id="birthday" name="birthday_s" value="" /></td>  
	    		<th>到:</th>
	    		<td><input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="birthday" name="birthday_e" value="" /></td>  
	    	    
	    	</tr>
	    	<tr>
	    	 
	    </table>
   </form>
</div>