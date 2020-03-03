<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="slForm" name="slForm" >
<input id="sls_cluesId" name="problemClues.id" value="${problemClues.id}" type="hidden" />
<input id="sls_reflectedId" name="reflectedPerson.id" value="${problemClues.reflectedPerson.id}" type="hidden" />
<input id="lc_sl" name="problemClues.state" value="${problemClues.state}" type="hidden" />
<input type="hidden" name="problemClues.isXf" value="${problemClues.isXf}" />
<input  type="hidden" name="resultTime" value="${problemClues.resultTime}" />
<input  type="hidden" name="problemClues.organId" value="${problemClues.organId}" />
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="sl_lc" type="button" class="btn btn-default" onclick="sl_save('SLSLC');">保存</button>
</c:if>
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="sl_tj" type="button" class="btn btn-default" onclick="sl_save('SLSTJ');">提交</button>
</c:if>
<table class="cxl">



<tr>
<td>受理时间</td>
<td>
	 <input name="progress[0].pointValue" type="hidden" value="22"/>
	 <input name="progress[0].pointName" type="hidden" value="审理室受理时间"/>
	 <input id="slsSLTime"  type="text"  name='progress[0].timeForday' class="easyui-datebox"  style="width:300px;height:45px;" ></input>
</td>
<td>审结时间</td>
<td>
		<input name="progress[1].pointValue" type="hidden" value="22"/>
		<input name="progress[1].pointName" type="hidden" value="审理室审结时间"/>
		<input id="sjTime"  type="text"  name='progress[1].timeForday' class="easyui-datebox"  style="width:300px;height:45px;"></input>
</td>
<td>审理建议</td>
<td><select id="progress1" class="easyui-combotree" name="progress[1].detail" style="width:300px;height:45px;" ></select></td>
</tr>
<tr>
<td>市委常委会议时间</td>
<td>
<input name="progress[2].pointValue" type="hidden" value="27"/>
<input name="progress[2].pointName" type="hidden" value="审理室市委常委会"/>
<input id="swcwh"  type="text"  name='progress[2].timeForday' class="easyui-datebox"  style="width:300px;height:45px;" ></input></td>
<td>纪委监委会议时间</td>
<td>
<input name="progress[3].pointValue" type="hidden" value="28"/>
<input name="progress[3].pointName" type="hidden" value="审理室纪委监委会"/>
<input id="jwjwh"   type="text"  name='progress[3].timeForday'  class="easyui-datebox"  style="width:300px;height:45px;" ></input>
</td>
<td>收缴涉案金额</td>
<td><input type="text" style="width: 260px; height: 45px;"class="easyui-textbox" name="reflectedPerson.collectionMoney" placeholder="请输入金额" oninput="money(this.value.length);"  value="${problemClues.reflectedPerson.collectionMoney}">万元</td>
</tr>
<tr>
<td>挽回经济损失</td>
<td><input type="text" style="width: 260px; height: 45px;" class="easyui-textbox" name="reflectedPerson.toSaveMoney" placeholder="请输入金额"  oninput="money(this.value.length);" value="${problemClues.reflectedPerson.toSaveMoney}">  

万元</td>
<td>结案时间</td>
<td>
									    	 <input name="progress[4].pointValue" type="hidden" value="14"/>
									    	 <input name="progress[4].pointName" type="hidden" value="审理室处理结果"/>
<input  id="jaTime"  type="text"  name='progress[4].timeForday'  class="easyui-datebox"  style="width:300px;height:45px;" ></input>
</td>
<td>是否属于问责</td>
<td><select id="isAccountability" name="reflectedPerson.isAccountability" class="easyui-combobox" style="width: 300px; height: 45px;">
													<option <c:if test="${'0' ==problemClues.reflectedPerson.isAccountability}">selected="selected"  </c:if>  value="0" >请选择</option>
													<option <c:if test="${'1' ==problemClues.reflectedPerson.isAccountability}">selected="selected"  </c:if>  value="1" >是</option>
													<option <c:if test="${'2' ==problemClues.reflectedPerson.isAccountability}">selected="selected"  </c:if>  value="2" >否</option>
												</select></td>
</tr>
<tr>
<td>处理结果</td>
<td><select id="progress4" class="easyui-combotree" name="progress[4].detail" style="width:300px;height:45px;" ></select></td>
<td>组织措施</td>
<td><select id="meaResult" class="easyui-combotree" name="measures.meaResult" style="width:300px;height:45px;"></select></td>
</tr>
<tr>
<td colspan="7">--留党察看--</td>
</tr>
<tr>
<!-- <td colspan="2">留党察看:
</td> -->
<td>处分决定生效时间</td>
<td>
<input  id="cfsxTime"  type="text"  name='reflectedPerson.takeEffectTime'  class="easyui-datebox"  style="width:300px;height:45px;" value="${problemClues.reflectedPerson.takeEffectTime}" disabled></input>
 </td>
 <td>留党察看年限</td>
<td>
<select id="stayTerm" name="reflectedPerson.stayTerm" class="easyui-combobox" style="width: 300px; height: 45px;" disabled>
													<option <c:if test="${'0' ==problemClues.reflectedPerson.stayTerm}">selected="selected"  </c:if>  value="0" >请选择</option>
													<option <c:if test="${'1' ==problemClues.reflectedPerson.stayTerm}">selected="selected"  </c:if>  value="1" >一年</option>
													<option <c:if test="${'2' ==problemClues.reflectedPerson.stayTerm}">selected="selected"  </c:if>  value="2" >两年</option>
												</select>
</td>
 <td>留党察看结束时间</td>
<td>
<input  id="stayTermEndTime"  type="text"  name='reflectedPerson.stayTermEndTime'  class="easyui-datebox"  style="width:300px;height:45px;" value="${problemClues.reflectedPerson.stayTermEndTime}" readonly></input>
</td>
</tr>
<tr>
  <td>处分决定执行完毕时间</td>
	  <td><input type="text" id="takeEffectEndTime" style="width: 300px; height: 45px;"class="easyui-datebox" name="slsResult.takeEffectEndTime" disabled></td>

<td>恢复党员权力</td>
<td>
<select id="hfql1" name="reflectedPerson.recoveryPower" class="easyui-combobox" style="width:300px;height:45px;" disabled>
													<option <c:if test="${'0' ==problemClues.reflectedPerson.recoveryPower}">selected="selected"  </c:if>  value="0" >请选择</option>
													<option <c:if test="${'1' ==problemClues.reflectedPerson.recoveryPower}">selected="selected"  </c:if>  value="1" >是</option>
													<option <c:if test="${'2' ==problemClues.reflectedPerson.recoveryPower}">selected="selected"  </c:if>  value="2" >否</option>
	
</select>
 
 </td>
</tr>
<tr>
<td colspan="7">--政务处分--</td>
</tr>
<tr>
  <td>处分决定生效时间</td>
	<td><input  id="cfsxTime2"  type="text"  name='slsResult.takeEffectTimeZW'  class="easyui-datebox"  style="width:300px;height:45px;" disabled></input>
 </td>
<td>影响期</td>
<td>
<input type="text" id="influencePeriod" style="width: 260px; height: 45px;" class="easyui-textbox" name="slsResult.influencePeriod" disabled>  

 
 </td>
</tr>
<tr>
<td colspan="7">--移送审查起诉--</td>
</tr>
<tr>
<!-- <td colspan="2">留党察看:
</td> -->
<td>移送罪名</td>
<td><input type="text" id="ysCharge" style="width: 300px; height: 45px;"class="form-control" name="slsResult.ysCharge" placeholder="请输入移送罪名"  value="" disabled></td>

 <td>移送时间</td>
<td>
<input  id="ysChargeTime"  type="text"  name='slsResult.ysChargeTime'  class="easyui-datebox"  style="width:300px;height:45px;" value="" disabled></input>

</td>
 <td>移送金额</td>
<td><input type="text" id="ysChargeMoney" style="width: 260px; height: 45px;"class="form-control" name="slsResult.ysChargeMoney" placeholder="请输入金额" oninput="money(this.value.length);"  value="" disabled>万元</td>
</tr>
<tr>
<td colspan="7">--公检法等处理情况--</td>
</tr>
<tr>
 <td>公检法等机关处理时间</td>
<td>
<input  id="handlingTime"  type="text"  name='slsResult.handlingTime'  class="easyui-datebox"  style="width:300px;height:45px;" value=""></input>
</td>
 <td>公检法等机关处理内容</td>
    <td><select id="handlingContent" name="slsResult.handlingContent" style="width: 300px; height: 45px;" class="easyui-combobox" onChange="return handlingContentChange()">
	   <option value="0">请选择</option>
		<c:forEach var="act" items="${handlingContent}">
				<option value="${act.value }">${act.name}</option>
		</c:forEach>
        </select>
    </td> 
    <td> </td>
    <td>
    <select class="easyui-combobox" id="handlingContentJT"   style="width: 300px; height: 45px;" name="slsResult.handlingContentJT" >
		<option value="0">请选择</option>		
	</select> 
   	<select class="easyui-combobox" id="handlingContentJT1"   style="width: 300px; height: 45px;display:none;" name="" >
		<option value="0">请选择</option>
		<c:forEach var="act" items="${handlingContentJT1}">
				<option value="${act.value }">${act.name}</option>
		</c:forEach>
	</select> 
		<select class="easyui-combobox" id="handlingContentJT2"   style="width: 300px; height: 45px;display:none;" name="" >
		<option value="0">请选择</option>
		<c:forEach var="act" items="${handlingContentJT2}">
				<option value="${act.value }">${act.name}</option>
		</c:forEach>
	</select> 
     </td>  
</tr>
<tr>
   <td>有期徒刑</td>
<td>
<!-- <input  id="imprisonmentYear"  type="text"  name='slsResult.imprisonmentYear'  class="easyui-datebox"  style="width:300px;height:45px;" value=""></input>
 -->
 <input type="text" id="imprisonmentYear" style="width: 120px; height: 45px;"class="form-control" name="slsResult.imprisonmentYear"  value="" >&nbsp;年
 <input type="text" id="imprisonmentMonth" style="width: 120px; height: 45px;"class="form-control" name="slsResult.imprisonmentMonth"  value="" >&nbsp;月
 </td>
	  <td>缓刑</td>
	<td>
	<!-- <input  id="slowDownYear"  type="text"  name='slsResult.slowDownYear'  class="easyui-datebox"  style="width:300px;height:45px;" value=""></input>
	 -->
	 <input type="text" id="slowDownYear" style="width: 120px; height: 45px;"class="form-control" name="slsResult.slowDownYear"  value="" >&nbsp;年
     <input type="text" id="slowDownMonth" style="width: 120px; height: 45px;"class="form-control" name="slsResult.slowDownMonth"  value="" >&nbsp;月
    </td>
	  <td>司法判决金额</td>
	  <td><input type="text" id="verdictAmount" style="width: 260px; height: 45px;"class="form-control" name="slsResult.verdictAmount" placeholder="请输入金额" oninput="money(this.value.length);"  value="">万元</td>
</tr>
<tr>
 <td>审理报告</td>
 <td colspan="7">
    <label    for="审理报告">上传</label>
    <input   class="btn btn-info" id="审理报告" type="file" style="visibility:hidden" multiple="multiple"  onchange="checkField('审理报告')"/>
    <label   id="slbg_l_A"  for="slbg_l_B">文件下载</label>
	<input   id="slbg_l_B"     value="" data-title="审理报告"  style="visibility:hidden"  onclick="download(this)">
	 <label   id="slbg_l_C"  for="slbg_l_D">文件删除</label>
	<input   id="slbg_l_D"     value="" data-title="审理报告"  style="visibility:hidden"  onclick="deleteFile(this)">
 </td>
 </tr>
  <tr>
  <td>处分决定</td>
 <td colspan="7">
    <label    for="处分决定">上传</label>
	<input class="btn btn-info" id="处分决定" type="file" style="visibility:hidden" multiple="multiple"  onchange="checkField('处分决定')"/>
	<label id="cfjd_l_A" for="cfjd_l_B">文件下载</label>
	<input id="cfjd_l_B" data-title="处分决定" style="visibility:hidden"  value="" onclick="download(this)">
	<label id="cfjd_l_C" for="cfjd_l_D">文件删除</label>
	<input id="cfjd_l_D" data-title="处分决定" style="visibility:hidden"  value="" onclick="deleteFile(this)">		
</td>
</tr>
 <tr>
 <td>起诉意见书</td>
 <td colspan="7">
   <label  for="起诉意见书">上传</label> 
   <input class="btn btn-info" id="起诉意见书" type="file" style="visibility:hidden" multiple="multiple"  onchange="checkField('起诉意见书')"/>
   <label id="qsyj_s_A" for="qsyj_B">文件下载</label>
   <input id="qsyj_s_B" data-title="起诉意见书" style="visibility:hidden"  value=""  onclick="download(this)">
    <label id="qsyj_s_C" for="qsyj_s_D">文件删除</label>
   <input id="qsyj_s_D" data-title="起诉意见书" style="visibility:hidden"  value=""  onclick="deleteFile(this)">
 </td>
</tr>
</table>
</form>
<script>
$(function(){
	$('#handlingContentJT1').next(".combo").hide();
	$('#handlingContentJT2').next(".combo").hide();
})
function sl_data(){
	$("#sls_cluesId").val($("#probleCluesId").val());
	$("#sls_reflectedId").val($("#reflectedId").val());
	
	if($("#state").val()){
		if($("#state").val().indexOf("LC")!=-1){
			$("#sl_lc").css('color','blue');
		}
		if($("#state").val().indexOf("TJ")!=-1){
			$("#sl_tj").css('color','blue');
		}
		
	}
	$.ajax({
		url:"${path}/sls/sls_data",
		data:{id:$("#probleCluesId").val(),ip:$("#dataSourceIp").val()},
		type:"post",
		dataType:"json",
		success:function(result){
			console.log(result);
			if(result.obj.cfjd){
                $("#cfjd_l").css("color","green");	
				  $("#cfjd").val(result.obj.cfjd[0].url);
				  }
			      if(result.obj.slbg){
                $("#slbg_l").css("color","green");									  
				  $("#slbg").val(result.obj.slbg[0].url);
				  }
			      if(result.obj.yjs){
                  $("#qsyj_s_l").css("color","green");	
				  $("#qsyj_s").val(result.obj.yjs[0].url);
				  }
			 if(result.obj.slsResult){
			      //影响期
				  $("#influencePeriod").textbox('setValue',result.obj.slsResult.influencePeriod);
				  //政务处分的处分生效时间
				  $("#cfsxTime2").datebox('setValue',result.obj.slsResult.takeEffectTimeZW);
				  $("#ysCharge").val(result.obj.slsResult.ysCharge);
					  $("#ysChargeTime").datebox('setValue',result.obj.slsResult.ysChargeTime);			  
				  $("#ysChargeMoney").val(result.obj.slsResult.ysChargeMoney);
				  if(result.obj.slsResult.handlingTime){
					  $("#handlingTime").datebox('setValue',result.obj.slsResult.handlingTime);
				  }				 
				  $("#handlingContent").combobox('setValue',result.obj.slsResult.handlingContent);
				  if(result.obj.slsResult.handlingContent=="1"){
					  $('#handlingContentJT').next(".combo").hide();
					  $('#handlingContentJT1').next(".combo").show();
	     	    	  $('#handlingContentJT2').next(".combo").hide();
					  $("#handlingContentJT1").combobox('setValue',result.obj.slsResult.handlingContentJT);
				  }else if(result.obj.slsResult.handlingContent=="2"){
					  $('#handlingContentJT').next(".combo").hide();
					  $('#handlingContentJT1').next(".combo").hide();
	     	    	  $('#handlingContentJT2').next(".combo").show();
					  $("#handlingContentJT2").combobox('setValue',result.obj.slsResult.handlingContentJT);
				  }else{
					  $('#handlingContentJT').next(".combo").show();
				  }
				  $("#imprisonmentYear").val(result.obj.slsResult.imprisonmentYear);	
				  $("#imprisonmentMonth").val(result.obj.slsResult.imprisonmentMonth);			  
				  $("#slowDownYear").val(result.obj.slsResult.slowDownYear);
				  $("#slowDownMonth").val(result.obj.slsResult.slowDownMonth);
				  $("#verdictAmount").val(result.obj.slsResult.verdictAmount);
			 }     
			 if(result.obj.progress){
			 $.each( result.obj.progress, function(index, content){
				  if(content.pointName=="审理室受理时间"){
						if(content.timeForday){
						$("#slsSLTime").datebox('setValue',content.timeForday.substring(0,10));
						}
				  }
				  if(content.pointName=="审理室审结时间"){
					  if(content.timeForday){
						  	$("#sjTime").datebox('setValue',content.timeForday.substring(0,10));
						  }
				  }
				  if(content.pointName=="审理室处理结果"){
					  if(content.timeForday){
					  $("#jaTime").datebox('setValue',content.timeForday.substring(0,10));
					  }
				  }
				  //市委常委会时间、纪委监委会时间
			      if(content.pointName=="审理室市委常委会"){
			    	  if(content.timeForday){
			    	  $("#swcwh").datebox('setValue',content.timeForday.substring(0,10));
			    	  }
				  }
			      if(content.pointName=="审理室纪委监委会"){
			    	  if(content.timeForday){
			    		  $("#jwjwh").datebox('setValue',content.timeForday.substring(0,10));
			    	  }
			    	 
				  }
			 });
			 }  
			 //审理室处置建议
			 $('#progress1').combotree({
			    	width:'100%',
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        valueField: 'dictId',
			        textField: 'name',
			        panelHeight : 'auto',
			        queryParams:{dictPid:'02'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#progress1').combotree('tree').tree('find', node.id);
				        	$('#progress1').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#progress1').combotree('tree').tree('find', node.id);
				        	$('#progress1').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#progress1').combotree('tree');  
			        	 console.log(node.dictId);
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	console.log(data);
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=result.obj.sls_czjy;
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	if(ids[i].indexOf(data[d].dictId)!=-1){
	                        		//展开该节点
	                        		var node = $('#progress1').combotree('tree').tree('find', data[d]. id);
	                        		$('#progress1').combotree('tree').tree('expand', node.target);
	                        		//杀死它
	                        		if(ids[i]==data[d].dictId){
	                        			$('#progress1').combotree('tree').tree('check', node.target);
	                        		}
	                        		
	                        	}
	                        }
	                    }
				    	} 
		            }
				    } ,loadFilter: function(data){  
				    	console.log(data+"=====");
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
			//审理室处置结果
			 $('#progress4').combotree({
			    	width:'100%',
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        panelHeight : 'auto',
			        queryParams:{dictPid:'02'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#progress4').combotree('tree').tree('find', node.id);
				        	$('#progress4').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#progress4').combotree('tree').tree('find', node.id);
				        	$('#progress4').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#progress4').combotree('tree');  
			        	 console.log(node.dictId);
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	console.log(data);
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=result.obj.sls_czjg;
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	if(ids[i].indexOf(data[d].dictId)!=-1){
	                        		//展开该节点
	                        		var node = $('#progress4').combotree('tree').tree('find', data[d].id);
	                        		$('#progress4').combotree('tree').tree('expand', node.target);
	                        		//杀死它
	                        		if(ids[i]==data[d].dictId){
	                        			$('#progress4').combotree('tree').tree('check', node.target);
	                        		}
	                        		
	                        	}
	                        }
	                    }
				    	} 
		            }
				    } ,loadFilter: function(data){  
				    	console.log(data+"=====");
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
			  //违纪情况
			  $('#meaResult').combotree({
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        panelHeight : 'auto',
			        queryParams:{dictPid:'0148'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#meaResult').combotree('tree').tree('find', node.id);
				        	$('#meaResult').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#meaResult').combotree('tree').tree('find', node.id);
				        	$('#meaResult').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#meaResult').combotree('tree');  
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=result.obj.sls_zzcs;
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	if(ids[i].indexOf(data[d].dictId)!=-1){
	                        		//展开该节点
	                        		var node = $('#meaResult').combotree('tree').tree('find', data[d].id);
	                        		$('#meaResult').combotree('tree').tree('expand', node.target);
	                        		//杀死它
	                        		if(ids[i]==data[d].dictId){
	                        			$('#meaResult').combotree('tree').tree('check', node.target);
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

function sl_save(state){
	$("#lc_sl").val(state+$("#organId").val());
	var data=decodeURIComponent($("#slForm").serialize(),true);
	  var isValid = $("#slForm").form('validate');
	  if(isValid){
	   //保存进度并且保存处置方式
	  $.ajax({
		  url:"${path}/sls/ajsl",
		  data:data,
		  dataType:"json",
		  type:"post",
		  success:function(data){
			  $.messager.alert('我的消息',data.msg,'info',function(){
				  //刷新树
	      	      treeReload();
			  });
		  }
	     });
	  }else{
		  $.messager.alert('我的消息','有必填项没有填写','info');
	  }
}


</script>