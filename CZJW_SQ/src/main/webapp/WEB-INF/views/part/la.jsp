<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="laForm" name="laForm" >
<input id="la_cluesId" name="problemClues.id" value="${problemClues.id}"  type="hidden"/>
<input id="reflectedId_la" name="reflectedPerson.id" value="${problemClues.reflectedPerson.id}"  type="hidden"/>
<input id="lc_la" name="problemClues.state" value="${problemClues.state}" type="hidden" />
<input type="hidden" name="problemClues.isXf" value="${problemClues.isXf}" />
<input  type="hidden" name="resultTime" value="${problemClues.resultTime}" />
<input  type="hidden" name="problemClues.fromId" value="${problemClues.fromId}" />
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="la_lc" type="button" class="btn btn-default" onclick="la_save('LALC');">保存</button>
</c:if>
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="la_tj" type="button" class="btn btn-default" onclick="la_save('LATJ');">提交</button>
</c:if>
<table class="cxl">    
     <tr>
	 <td>纪委立案时间</td>
	 <td>
				<input name="progress[0].pointValue" type="hidden" value="21"/>
				<input name="progress[0].pointName" type="hidden" value="立案审查（调查）"/>
				<input name="progress[0].detail" type="hidden" value="纪委立案时间"/>
				<input id="jiwla"  type="text"  name="progress[0].timeForday" class="easyui-datebox" value="" style="width:300px;height:45px;" ></input>  
	</td>	
	<td>监委立案时间</td>
	<td>
				<input name="progress[1].pointValue" type="hidden" value="21"/>
				<input name="progress[1].pointName" type="hidden" value="立案审查（调查）"/>
				<input name="progress[1].detail" type="hidden" value="监委立案时间"/>
				<input id="jianwla"  type="text"   name="progress[1].timeForday" class="easyui-datebox" value="" style="width:300px;height:45px;" ></input>  
	</td>													
	   <td>违纪行为</td>	
	   <td>
		<select  style="width:300px;height:45px"  class="easyui-combotree"  id="zyViolation" name="zyViolation.zyResult" >
          
        </select>
    </td>               							    			
   </tr>
   <tr>
   <td colspan="3">是否其他纪检监察机关立案后移送</td>
    <td><select id="isOtherTransfer" name="reflectedPerson.isOtherTransfer" style="width: 300px; height: 45px;" class="easyui-combobox" onChange="return isOther()">
	    <option <c:if test="${'0' ==problemClues.reflectedPerson.isOtherTransfer}">selected="selected"</c:if> value="0">请选择</option>
		<option <c:if test="${'1' ==problemClues.reflectedPerson.isOtherTransfer}">selected="selected"</c:if> value="1">是</option>
		<option <c:if test="${'2' ==problemClues.reflectedPerson.isOtherTransfer}">selected="selected"</c:if> value="2">否</option>
        </select>
    </td> 
    <td> </td>
    <td>
   	<select class="easyui-combobox" id="otherTransfer"   style="width: 300px; height: 45px;" name="reflectedPerson.otherTransfer" disabled="disabled">
		<option value="0">请选择</option>
		<c:forEach var="act" items="${otherTransfer}">
				<option  <c:if test="${act.value ==problemClues.reflectedPerson.otherTransfer}"> selected="selected" </c:if> value="${act.value }">${act.name}</option>
		</c:forEach>
	</select> 
     </td>  
   </tr>
   <tr>
  
    <td>审查调查组组长</td>
    <td><input name="group[0].state" type="hidden" value="2"/>
 	    <input name="group[0].groupIdentity" type="hidden" value="1"/>
 	    <input id="zu_zhang_la" type="text" style="width: 300px; height: 45px;" class="form-control" name="group[0].groupName" placeholder="请输入调查组组长"></td>
    <td>审查调查组成员</td>
       
	<td>
	     <input name="group[1].state" type="hidden" value="2"/>
		 <input name="group[1].groupIdentity" type="hidden" value="2"/>
	<input id="zu_yuan_la" type="text" style="width:300px;height:45px;" class="form-control" name="group[1].groupName" placeholder="请输入调查组组员"></td> 
    <td>是否采取留置措施</td>									    			
                           <td>
                           <select id="measures" name="reflectedPerson.measures"  style="width: 300px; height: 45px;" >
                              <option  value="2">否</option>
                              <option <c:if test="${'1' ==problemClues.reflectedPerson.measures}">selected="selected" </c:if> value="1">是</option>  
                              </select>
                             </td> 
   </tr>
    <tr>
 
    <td>留置依据</td>
    <td><input id="lienBasis" type="text" class="form-control" name="lien[0].lienBasis" style="width: 300px; height: 34px;" placeholder="请输入留置依据" value="" disabled></td>
    <td>留置起始时间</td>
    <td><input id="lienTime"  type="text" name="lien[0].lienTime" class="easyui-datebox" value="" style="width:300px;height:45px;"  disabled></input>  
    </td>     
    <td>解除留置时间</td>
   <td><input id="lienRelieveTime"  type="text" name="lien[0].lienRelieveTime" class="easyui-datebox" value="" style="width:300px;height:45px;"  disabled></input>  
  </td>
   </tr>
   <tr>
  
    <td>留置天数</td>
      <td><input type="text" id="lienDays" style="width: 270px; height: 45px;" class="form-control" name="lien[0].lienDays" placeholder="留置天数"  value="" readonly>天</td>
       
   <td>是否延期</td>
   <td><select id="delayLien" name="lien[0].delayLien" style="width: 300px; height: 45px;"  disabled>
                                         <option selected="selected" value="0" >请选择</option>
				                         <option   value="1">是</option>  
				                         <option   value="2">否</option>
                                         </select></td>
     <td>延期天数</td>
   <td><input type="text" id="delayLienDays" style="width: 270px; height: 45px;" class="form-control" name="lien[0].delayLienDays" placeholder="请输入延期天数"  value="" disabled onchange="compute()" >天</td>
       
   </tr>
   <tr>
  
   <td>延期结束时间</td>
   <td><input id="delayLienTime"  type="text" name="lien[0].delayLienTime" class="easyui-datebox" value="" style="width:300px;height:45px;"  readonly></input>  
   </td>
   <td>延期留置理由</td>
    <td><input id="lienReason" type="text" class="form-control" name="lien[0].lienReason" style="width: 300px; height: 34px;" placeholder="请输入延期理由" value="" disabled></td>
    
                   <td>是否涉嫌犯罪</td>									    			
          <td>
                  <select id="crime333" name="reflectedPerson.crime"   style="width: 300px; height: 45px;" onchange="crime();" >
                  <option   value="2">否</option>
                  <option   <c:if test="${'1' ==problemClues.reflectedPerson.crime}"> selected="selected" </c:if> value="1">是</option>  
         </select>
         </td>
   </tr>
   <tr>
   <td>涉嫌罪名</td>
   <td><select id="crimeCharge" class="easyui-combotree" name="reflectedPerson.crimeCharge" style="width:300px;height:45px;display:none;" disabled></select>  
  </td>
   
     <td>是否在逃</td>								    			
    <td>
    <select id="escape" name="reflectedPerson.escape"  class="easyui-combobox" style="width: 300px; height: 45px;" >
                              <option  value="2">否</option>
                             <option  <c:if test="${'1' ==problemClues.reflectedPerson.escape}">selected="selected"  </c:if> value="1">是</option>  
                            
                           
                           
    </select>
    </td>
     <td>是否通缉</td>									    			
  <td>
  <select id="wanted" name="reflectedPerson.wanted"  class="easyui-combobox" style="width: 300px; height: 45px; " >
                             <option   value="2">否</option>
                             <option <c:if test="${'1' ==problemClues.reflectedPerson.wanted}">selected="selected"  </c:if> value="1">是</option> 
                             </select>
                           </td> 
   </tr> 
  <tr>
  
   <td>通缉时间</td>
   
    <td> 
    <input id="wantedTime"  type="text" name="reflectedPerson.wantedTime" class="easyui-datebox" value="${problemClues.reflectedPerson.wantedTime }" style="width:300px;height:45px;" ></input>  </td>
     <td>涉案金额</td>
      <td><input type="text" style="width: 270px; height: 45px;" class="form-control" name="reflectedPerson.involvedMoney" placeholder="请输入金额" oninput="money(this.value.length);" value="${problemClues.reflectedPerson.involvedMoney}">万元</td>
       
         
  
   <td>首次违纪违法时间</td>
   <td><input id="firstViolationTime"  type="text" name="reflectedPerson.firstViolationTime"  value="${problemClues.reflectedPerson.firstViolationTime}" style="width:280px;height:45px;" ></input>年</td>
   </tr>
   <tr>
   <td>末次违纪违法时间</td>
    <td><input id="finalViolationTime"  type="text" name="reflectedPerson.finalViolationTime"  value="${problemClues.reflectedPerson.finalViolationTime}" style="width:280px;height:45px;" ></input>年</td>
     <%-- <td>其他违纪违法时间</td>
      <td><input id="otherViolationTime"  type="text" name="reflectedPerson.otherViolationTime" class="easyui-datebox" value="${problemClues.reflectedPerson.otherViolationTime}" style="width:300px;height:45px;" ></input></td> --%>
 
     <td>搜查时间</td>
        <td><input id="searchTime"  type="text" name="reflectedPerson.searchTime" class="easyui-datebox" value="${problemClues.reflectedPerson.searchTime}" style="width:300px;height:45px;" ></input></td>
     <td>调查中止时间</td>
        <td><input id="researchEndTime"  type="text" name="reflectedPerson.researchEndTime" class="easyui-datebox" value="${problemClues.reflectedPerson.researchEndTime}" style="width:300px;height:45px;" ></input></td>
       </tr>
   
   
   <tr>
    <td>恢复调查时间</td>
        <td><input id="researchRegainTime"  type="text" name="reflectedPerson.researchRegainTime" class="easyui-datebox" value="${problemClues.reflectedPerson.researchRegainTime}" style="width:300px;height:45px;" ></input></td>
      
       <td>涉法行为</td>
          <td><select id="legalAct" class="easyui-combotree" name="legalAct[0].lawResult" style="width:300px;height:45px;"></select>  
       </td>
    <td>处置建议</td>
    <td>
    <input name="progress[2].causeId"    type="hidden" value="${problemClues.id}"/>
	<input name="progress[2].pointValue" type="hidden" value="13"/>
	<input name="progress[2].pointName" type="hidden" value="审理室受理"/>
	<input name="progress[2].detail" type="hidden" value="30"/>	
	<select id="cfResult" class="easyui-combotree" name="punishment.cfResult" style="width:300px;height:45px;"></select>  
    </td>  
   </tr>
   <tr>
      <td>是否限制出境</td>
      <td>
       <select id="isChu" name="reflectedPerson.isChu"  class="easyui-combobox" style="width: 300px; height: 45px; " >
                             <option   value="2">否</option>
                             <option <c:if test="${'1' ==problemClues.reflectedPerson.isChu}">selected="selected"  </c:if> value="1">是</option> 
       </select>
      </td>
      <td>限制出境起始时间</td>
      <td>
      <input id="chuStartime"  type="text" name="reflectedPerson.chuStartime" class="easyui-datebox" value="${problemClues.reflectedPerson.chuStartime}" style="width:300px;height:45px;" ></input>
      </td>
      <td>解除限制出境时间</td>
      <td>
      <input id="chuendtime"  type="text" name="reflectedPerson.chuendtime" class="easyui-datebox" value="${problemClues.reflectedPerson.chuendtime}" style="width:300px;height:45px;" ></input>
      </td>
   </tr>
   <tr>
   <td>立案审查(调查)报告</td>
   <td colspan="6">
    <label  for="立案审查(调查)报告">上传</label>
   <input class="btn btn-info" id="立案审查(调查)报告" type="file" style="visibility:hidden" multiple="multiple"  onchange="checkField('立案审查(调查)报告')"/>
   <label id="lascdc_bg_A" for="lascdc_bg_B">文件下载</label>
   <input id="lascdc_bg_B" data-title="立案审查(调查)报告" style="visibility:hidden"  value=""  onclick="download(this)">	
   <label id="lascdc_bg_C" for="lascdc_bg_D">文件删除</label>
   <input id="lascdc_bg_D" data-title="立案审查(调查)报告" style="visibility:hidden"  value=""  onclick="deleteFile(this)">		
   </td>
   </tr>
   
   <tr>
   <td>审查(调查)报告</td>
  	<td colspan="6">
  	     <label  for="审查(调查)报告">上传</label>
         <input class="btn btn-info" id="审查(调查)报告" type="file" style="visibility:hidden" multiple="multiple"  onchange="checkField('审查(调查)报告')"/>
         <label id="scdc_bg_A" for="scdc_bg_B">文件下载</label>
		 <input id="scdc_bg_B" data-title="审查(调查)报告" style="visibility:hidden"  value=""  onclick="download(this)">		
		 <label id="scdc_bg_C" for="scdc_bg_D">文件删除</label>
		 <input id="scdc_bg_D" data-title="审查(调查)报告" style="visibility:hidden"  value=""  onclick="deleteFile(this)">
    </td>
	</tr>
</table>
</form>
<script>

function isOther(){
	   var isOtherTransfer = $('#isOtherTransfer').val();
	   if(isOtherTransfer=="1"){
		   $("#otherTransfer").removeAttr("disabled"); 
	   }else{
		   $('#otherTransfer').attr("disabled","disabled"); 
	   }
  }
function la_data(){
	//如果是党员则 纪委立案时间为必填项
	var is_d='${problemClues.reflectedPerson.partyMember}';
	//保留线索id
	$("#la_cluesId").val($("#probleCluesId").val());
	$("#reflectedId_la").val($("#reflectedId").val());
	
	
	if($("#state").val()){
		if($("#state").val().indexOf("LC")!=-1){
			$("#la_lc").css('color','blue');
		}
		if($("#state").val().indexOf("TJ")!=-1){
			$("#la_tj").css('color','blue');
		}
		
	}
	$.ajax({
		url:"${path}/scs/la_data",
		data:{id:$("#probleCluesId").val(),ip:$("#dataSourceIp").val()},
		type:"post",
		dataType:"json",
		success:function(result){
			  if(result.obj.progress){
			  $.each( result.obj.progress, function(index, content){
				      if(content.detail=="纪委立案时间"){
				    	  if(content.timeForday){
					  $("#jiwla").datebox('setValue',content.timeForday.substring(0,10));
				    	  }
				      }
				       if(content.detail=="监委立案时间"){
				       if(content.timeForday){
				    	   $("#jianwla").datebox('setValue',content.timeForday.substring(0,10));
				       }
					 
				      }
			      isOther();
			      if(result.obj.dcbg){
                  $("#scdc_bg_l").css("color","green");									  
				  $("#scdc_bg").val(result.obj.dcbg[0].url);
				  }
			      if(result.obj.lascbg){
                  $("#lascdc_bg_l").css("color","green");									  
				  $("#lascdc_bg").val(result.obj.lascbg[0].url);
				  }
			      $("#sc_czjy").val(content.detail);
			      //审查室小组
				  if(result.obj.zu_yuan2){
		         	      $('#zu_yuan_la').val(result.obj.zu_yuan2.groupName);
				  }
				  if(result.obj.zu_zhang2){
						  $("#zu_zhang_la").val(result.obj.zu_zhang2.groupName);
				  }
			  });
			  }
			//留置
		       if(result.obj.lien1){
		    	      lien1=result.obj.lien1;
			    	  $("#lienTime").datebox('setValue',lien1.lienTime);
			    	  $("#lienBasis").val(lien1.lienBasis);
			    	  $("#lienRelieveTime").datebox('setValue',lien1.lienRelieveTime);
			    	  $("#delayLien").combobox('setValue',lien1.delayLien);
		    	      $("#delayLienTime").datebox('setValue',lien1.delayLienTime);
		    	      $("#lienDays").val(lien1.lienDays);
		    	      $("#delayLienDays").val(lien1.delayLienDays);
		    	      $("#lienReason").val(lien1.lienReason);
		    	     

		      } 
			  //处分情况
			  $('#cfResult').combotree({
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        panelHeight : 'auto',
			        queryParams:{dictPid:'02'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#cfResult').combotree('tree').tree('find', node.id);
				        	$('#cfResult').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#cfResult').combotree('tree').tree('find', node.id);
				        	$('#cfResult').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#cfResult').combotree('tree');  
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=result.obj.cfResult;
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	if(ids[i].indexOf(data[d].dictId)!=-1){
	                        		//展开该节点
	                        		var node = $('#cfResult').combotree('tree').tree('find', data[d].id);
	                        		$('#cfResult').combotree('tree').tree('expand', node.target);
	                        		//杀死它
	                        		if(ids[i]==data[d].dictId){
	                        			$('#cfResult').combotree('tree').tree('check', node.target);
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
			  
			  //违纪情况
			  $('#legalAct').combotree({
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        panelHeight : 'auto',
			        queryParams:{dictPid:'03'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#legalAct').combotree('tree').tree('find', node.id);
				        	$('#legalAct').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#legalAct').combotree('tree').tree('find', node.id);
				        	$('#legalAct').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#legalAct').combotree('tree');  
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=result.obj.legalAct;
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	if(ids[i].indexOf(data[d].dictId)!=-1){
	                        		//展开该节点
	                        		var node = $('#legalAct').combotree('tree').tree('find', data[d].id);
	                        		$('#legalAct').combotree('tree').tree('expand', node.target);
	                        		//杀死它
	                        		if(ids[i]==data[d].dictId){
	                        			$('#legalAct').combotree('tree').tree('check', node.target);
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
			    //违纪行为
			  $('#zyViolation').combotree({
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        queryParams:{dictPid:'0105'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#zyViolation').combotree('tree').tree('find', node.id);
				        	$('#zyViolation').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#zyViolation').combotree('tree').tree('find', node.id);
				        	$('#zyViolation').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#zyViolation').combotree('tree');  
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=result.obj.zyViolation1;
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	if(ids[i].indexOf(data[d].dictId)!=-1){
	                        		//展开该节点
	                        		var node = $('#zyViolation').combotree('tree').tree('find', data[d].id);
	                        		$('#zyViolation').combotree('tree').tree('expand', node.target);
	                        		//杀死它
	                        		if(ids[i]==data[d].dictId){
	                        			$('#zyViolation').combotree('tree').tree('check', node.target);
	                        		}
	                        		
	                        	}
	                        }
	                    }
				    	} 
				    	}
		            },loadFilter: function(data){  
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
			    //罪名
			  $('#crimeCharge').combotree({
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        panelHeight : 'auto',
			        queryParams:{dictPid:'07'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#crimeCharge').combotree('tree').tree('find', node.id);
				        	$('#crimeCharge').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#crimeCharge').combotree('tree').tree('find', node.id);
				        	$('#crimeCharge').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#crimeCharge').combotree('tree');  
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=result.obj.crimes;
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
	                        for ( var i = 0; i < ids.length; i++) {
	                        	if(ids[i].indexOf(data[d].dictId)!=-1){
	                        		//展开该节点
	                        		var node = $('#crimeCharge').combotree('tree').tree('find', data[d].id);
	                        		$('#crimeCharge').combotree('tree').tree('expand', node.target);
	                        		//杀死它
	                        		if(ids[i]==data[d].dictId){
	                        			$('#crimeCharge').combotree('tree').tree('check', node.target);
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

function  la_save(state){
	$("#lc_la").val(state+$("#organId").val());
	var data=decodeURIComponent($("#laForm").serialize(),true);
	   var isValid = $("#laForm").form('validate');
	   if(isValid){
		   //保存进度并且保存处置方式
		   $.ajax({
			  url:"${path}/scs/lasc",
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
        function lz2(){
		var zm = $('#measures2').val();
		if(zm=="1"){
   		//显示
	    $("#crimeCharge").css("display","block");
   	    }else{
   		//隐藏
   		$("#crimeCharge").css("display","none");
   		
   	}
	}
        function crime(){
        	 $("#crimeCharge").css("visibility","visible");
        }       
</script>