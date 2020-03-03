<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="jgs_jb_form" name="jds_jb_form" >
<input id="jds_jb_jds" name="problemClues.id" value="${problemClues.id}" type="hidden" />
<input id="lc_jds_jb" name="problemClues.state" value="${problemClues.state}" type="hidden" />
<input type="hidden" id="caseId2"  name="problemClues.cluesNum" value="${problemClues.cluesNum}" /> 
<input type="hidden" name="resultTime" value="${problemClues.resultTime}" />
<hr style="border:red;border:5px solid black;"/>
<h1>交办到县区</h1>
<table class="cxl">
<tr>
<td><label class="col-md-1 control-label" >承办人</label>   
</td>

<td>
<select id="cbr2"  class="cxls form-control"  name="user.id"   style="width:300px;height:45px;	"></select></td>
<td><label class="col-md-2 control-label">下发到基层单位</label></td>
<td><select id="jcdw" name='problemClues.organId' class="form-control" style="width:300px;height:45px;">
<option value="0">请选择</option>
<c:forEach var="act" items="${jc}">
           <option value="${act.id }">${act.name}纪委</option>
</c:forEach>
</select></td> 
<td><label class="col-md-2 control-label" >交办日期</label></td> 
<td><input  id="giveTime_jds" type="text"  name='problemClues.giveTime_jds' class="easyui-datebox" value="${problemClues.giveTime_jds}" style="width:300px;height:45px;" ></input>  
</td>  
     
</tr>
<tr>
<td><label class="col-md-1 control-label" >领办人</label></td>   
<td><input type='text' id="givePersion_jds"  name='problemClues.givePersion_jds' value="${problemClues.givePersion_jds}" style="width:300px;height:45px;"/></td>                                                                                   
<td><label class="col-md-2 control-label">是否要结果</label></td>
<td><select id="isResult_jds" name="problemClues.isResult_jds" class="form-control" onChange="openTime(this.options[this.options.selectedIndex].value);" style="width:300px;height:45px;">
<option value="0">请选择</option>
<option value="1" <c:if test="${1 ==problemClues.isResult_jds}">selected="selected"</c:if>>是</option>
<option value="2" <c:if test="${2 ==problemClues.isResult_jds}">selected="selected"</c:if>>否</option>
</select></td>
<td><label  id="res1333" >办理期限</label></td>
<td> <input  id="resultTime_jds_333" type="text" style="visibility:hidden;width:300px;height:45px;"  name='problemClues.resultTime_jds' class="easyui-datebox" value="${problemClues.resultTime_jds}"  ></input>  
</td>
</tr>
<tr>
</tr>
</table>
</form>

<form id="jgs_pz_form" name="jds_pz_form" >
<input id="jds_pz_jds" name="problemClues.id" value="${problemClues.id}" type="hidden" />
<input id="jds_pz_state" name="problemClues.state" value="${problemClues.state}" type="hidden" />
<input type="hidden" name="problemClues.isXf" value="${problemClues.isXf}" />
<input  type="hidden" name="resultTime" value="${problemClues.resultTime}" />
<input  type="hidden" name="problemClues.fromId" value="${problemClues.fromId}" />
<input  type="hidden" name="problemClues.organId" value="${problemClues.organId}" />
<input type="hidden" id="caseId2"  name="problemClues.cluesNum" value="${problemClues.cluesNum}" /> 
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="jds_jb_lc" type="button" class="btn btn-default" onclick="jds_jb_save('JDSJBLC');">保存</button>
</c:if>
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="jds_jb_tj" type="button" class="btn btn-default" onclick="jds_jb_save('JDSJBTJ');">提交</button>
</c:if>
<hr style="border:red;border:5px solid black;"/>
<h1>交办到派驻纪检组</h1>
<table class="cxl">
<tr>
    <td>派驻纪检组名称</td>
    <td><input  id="pz_name" type="text"  name='progress[0].remark'  value="" style="width:300px;height:45px;" ></input></td>
</tr>
<tr>
   <td>处置方式</td>
   <td>
             <input type="hidden"  name="progress[0].causeId" value="${problemClues.id}">
	         <input type="hidden"  name="progress[0].pointValue" value="3.51">
	         <input type="hidden"  name="progress[0].pointName" value="派驻纪检组处置">
	         <input type="hidden"  name="progress[0].type" value="派驻纪检组处置">
   <select   id="pz_czfs"        name="progress[0].detail" class=" cxls form-control"    style="width:300px;height:45px;">
	                        <option value="0">请选择</option>
	                        <option value="1">谈话函询</option>
	                        <option value="2">初步核实</option>
	                        <option value="3">暂存待查</option>
	                        <option value="4">予以了结</option>
   </select>
   </td>
   <td>处置时间</td>
   <td><input  id="pz_czsj" type="text"  name='progress[0].timeForday' class="easyui-datebox" value="" style="width:300px;height:45px;" ></input></td>
   <td>是否立案</td>
   <td> <select   id="pz_is_la"        name="progress[1].detail" class=" cxls form-control"    style="width:300px;height:45px;">
	                        <option value="0">请选择</option>
	                        <option value="1">是</option>
	                        <option value="2">否</option>
   </select>
   </td>
</tr>
<tr>


    <td>立案时间</td>
    <td>
             <input type="hidden"  name="progress[1].causeId" value="${problemClues.id}">
	         <input type="hidden"  name="progress[1].pointValue" value="3.52">
	         <input type="hidden"  name="progress[1].pointName" value="派驻纪检组立案">
	         <input type="hidden"  name="progress[1].type" value="派驻纪检组立案">
	         <input type="hidden" id="userId"  name="progress[0].user.id" value="">
   <input  id="pz_lasj" type="text"  name='progress[1].timeForday' class="easyui-datebox" value="" style="width:300px;height:45px;" ></input></td>
   <td>处理情况</td>
   <td>
             <input type="hidden"   name="progress[2].causeId" value="${problemClues.id}">
	         <input type="hidden"  name="progress[2].pointValue" value="-1">
	         <input type="hidden"  name="progress[2].pointName" value="派驻纪检组处理">
	         <input type="hidden"  name="progress[2].type" value="派驻纪检组处理">
	         <input type="hidden" id="userId"  name="progress[2].user.id" value="">
             <select id="pz_cl" class="easyui-combotree" name="progress[2].detail" style="width:300px;height:45px;" ></select></td>
   <td>结案时间</td>
   <td><input  id="pz_jasj" type="text"  name='progress[2].timeForday' class="easyui-datebox" value="" style="width:300px;height:45px;" ></input></td>
</tr>
<tr>
<td><label>备注</label></td>
<td colspan="5" class="box2">
    <textarea id="pz_cl_bz" name="progress[2].remark"></textarea></td> 
</tr>
</table>
<c:if test="${(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="jds_jb_lc" type="button" class="btn btn-default" onclick="jds_pz_save('JDSPZLC');">保存</button>
</c:if>
<c:if test="${(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="jds_jb_tj" type="button" class="btn btn-default" onclick="jds_pz_save('JDSPZTJ');">提交</button>
</c:if>
</form>
<script>
function jds_jb(){
	$("#jds_jb_jds").val($("#probleCluesId").val());
	$("#jds_pz_jds").val($("#probleCluesId").val());
	$.ajax({
		  url:"${path}/jds/jds_jb",
		  data:{id:$("#probleCluesId").val()},
		  type:"post",
		  dataType:"json",
		  success:function(result){
			     var jdsNext;
			     var userid="";
			     if(result.obj.progress2){
			    	 $.each( result.obj.progress2, function(index, content){
							 if(content.pointName=='监督室下发案件'){
								  console.log(content);
								  userid=content.user.id;
								  $("#jcdw").val(content.detail);
							  }
				
					 });
			    	
			    	  
			     }
			     $.ajax({
					 url:"${path}/user/dataGrid",
					 data:{organizationId:parent.organId,page:1,rows:10},
					 dataType:'json',
					 type:'post',
					 success:function(result){
						
						 
						 if(userid==0){
							 $("#cbr2").append('<option value="0" selected="selected">请选择</option>');
						 }else{
							 $("#cbr2").append('<option value="0">请选择</option>');
						 }
						 
						 $.each( result.rows, function(index, content){
					     if(userid==content.id){
					    	 $("#cbr2").append('<option value="'+content.id+'" selected="selected">'+content.name+'</option>');
					     }else{
					    	 $("#cbr2").append('<option value="'+content.id+'">'+content.name+'</option>');
					     }
			             
						 });
					 }
				 });
			     var pros=result.obj.progress;
			     if(pros){
			    	
			    	 $.each(pros, function(index, content){
						 if(content.pointName=='派驻纪检组处置'){
							 $("#pz_name").val(content.remark);
							 $("#pz_czfs").val(content.detail);
							 //处置时间
					    	  if(content.timeForday){
					    		  $("#pz_czsj").datebox('setValue',content.timeForday.substring(0,10));	 
					    	  }
							 
						  }
						 if(content.pointName=='派驻纪检组立案'){
							  //立案时间
					    	  if(content.timeForday){
						    	  $("#pz_lasj").datebox('setValue',content.timeForday.substring(0,10));
					    	  }
					    	  //是否立案
					    	  $("#pz_is_la").val(content.detail);
						 }
						 if(content.pointName=='派驻纪检组处理'){
							 //结案时间
					    	  if(content.timeForday){
					    		  $("#pz_jasj").datebox('setValue',content.timeForday.substring(0,10));
					    	  }
					    	  
					    	  //备注
					    	  $("#pz_cl_bz").text(content.remark);
						 }
			
				     });
			    	 
			     }
		    	 
			     
				 $('#pz_cl').combotree({
				    	width:'100%',
				        url: '${path }/dict/dictCombotree',
				        multiple: true,
				        panelHeight : 'auto',
				        queryParams:{dictPid:'02'},
				        onDblClick:function(node){
				        	if (node.state == 'open') {
				        		var node = $('#pz_cl').combotree('tree').tree('find', node.id);
					        	$('#pz_cl').combotree('tree').tree('collapse', node.target);
				        	}else{
				        		var node = $('#pz_cl').combotree('tree').tree('find', node.id);
					        	$('#pz_cl').combotree('tree').tree('expand', node.target);
				        	}
				        	
				        },
				        onBeforeExpand:function(node){
				        	 var childrenArr = $('#pz_cl').combotree('tree');  
				        	 console.log(node.dictId);
				        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
					    },
					    onLoadSuccess:function(node,data){
					    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
					    	var ids="";
					    	if(result.obj.progress){
					    	var pros=result.obj.progress;
					    	$.each(pros, function(index, content){
					    		 if(content.pointName=='派驻纪检组处理'){
					    			 if(content.detail!=null&&content.detail.indexOf(",")>=0){
					    				 ids=content.detail.split(",");
					    			 }
					    			 
					    		 }
					    		
					    	});
					    	if(ids){
					    		
					    	for(var d=0;d<data.length;d++){
					    	      //遍历后台传来的值
					    	 if (ids.length > 0) {
			                 for ( var i = 0; i < ids.length; i++) {
			                	console.log(ids[i]+"=============="+data[d].dictId);
			                 	if(ids[i].indexOf(data[d].dictId)!=-1){
			                 		
			                 		//展开该节点
			                 		var node = $('#pz_cl').combotree('tree').tree('find', data[d].id);
			                 		$('#pz_cl').combotree('tree').tree('expand', node.target);
			                 		//杀死它
			                 		if(ids[i]==data[d].dictId){
			                 			$('#pz_cl').combotree('tree').tree('check', node.target);
			                 		}
			                 	}
			                 }
			             }
					     } 
			            }
					    	
					    	
					    }	
					    } ,loadFilter: function(data){  
					    	for(var x in data){
					    		data[x].id=data[x].dictId;
					    	}
					        if (data.d){   
					            return data.d;    
					        } else {    
					            return data;    
					        }    
					    }
				    });	
			  
			  
		  }
	 });
	
	
	

	
	
	
}
function openTime(value){
	console.log(value=="1");
	  if(value=="1"){
		  $("#res1333").css("visibility","visible");
		  $("#resultTime_jds_333").css("visibility","visible");
	  }else{
		  $("#res1333").css("visibility","hidden");
		  $("#resultTime_jds_333").css("visibility","hidden");
	  }
}
function jds_jb_save(state){
	$("#lc_jds_jb").val(state+$("#organId").val());
	var data=decodeURIComponent($("#jgs_jb_form").serialize(),true);
	var isValid = $("#jgs_jb_form").form('validate');
	if(isValid){
		 //保存进度并且保存处置方式
		   $.ajax({
			  url:"${path}/jds/xfjcdw",
			  data:data,
			  dataType:"json",
			  type:"post",
			  success:function(data){
				  $.messager.alert('我的消息',data.msg,'info');
				  //刷新树
	      	      treeReload();
			     }
		     }); 
	}else{
		$.messager.alert('我的消息','有必填项没有填写','info');
	}
	  
}
function jds_pz_save(state){
	$("#jds_pz_state").val(state+$("#organId").val());
	var data=decodeURIComponent($("#jgs_pz_form").serialize(),true);
	var isValid = $("#jgs_pz_form").form('validate');
	if(isValid){
		 //保存进度并且保存处置方式
		   $.ajax({
			  url:"${path}/jds/pz",
			  data:data,
			  dataType:"json",
			  type:"post",
			  success:function(data){
				  $.messager.alert('我的消息',data.msg,'info');
				  //刷新树
	      	      treeReload();
			     }
		     }); 
	}else{
		$.messager.alert('我的消息','有必填项没有填写','info');
	}
	  
}
</script>