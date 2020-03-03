<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="scForm" name="scForm" >
<input id="scs_cluesId" name="problemClues.id" value="${problemClues.id}" type="hidden" />
<input id="lc_scs" name="problemClues.state"  value="${problemClues.state}"  type="hidden" />
<input id="reflectedId_la" name="problemClues.reflectedPerson.id" value="${problemClues.reflectedPerson.id}"  type="hidden"/>
<input type="hidden" name="problemClues.isXf" value="${problemClues.isXf}" />
<input  type="hidden" name="resultTime" value="${problemClues.resultTime}" />
<input  type="hidden" name="problemClues.fromId" value="${problemClues.fromId}" />
<input  type="hidden" name="problemClues.organId" value="${problemClues.organId}" />
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="scs_lc" type="button" class="btn btn-default" onclick="scs_save('SCLC');">保存</button>
</c:if>

<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="scs_tj" type="button" class="btn btn-default" onclick="scs_save('SCTJ');">提交</button>
</c:if>
<table class="cxl">
<tr>
<td>初核时间</td>
<td><input id="chtime"  type="text" name='progress[0].timeForday' class="easyui-datebox" value="" style="width:300px;height:45px;" ></input>  
</td>
<td>处置建议</td>
<td><input name="progress[0].causeId"    type="hidden" value="${problemClues.id}"/>
<input name="progress[0].pointValue" type="hidden" value="10"/>
<input name="progress[0].pointName" type="hidden" value="审查室处置建议"/>
<select   id="scJY1" name="progress[0].detail" class="easyui-combobox" style="width: 300px; height: 45px;">
<option value="0" selected="selected" >请选择</option>
<c:forEach var="act" items="${scsMethod}">
<option value="${act.value }">${act.name}</option>									                      
</c:forEach>
</select>
</td>
</tr>
<tr>
<td>审查室领导审批意见</td>
<td><input name="progress[1].causeId"    type="hidden" value="${problemClues.id}" />
<input name="progress[1].pointValue" type="hidden" value="20"/>
<input name="progress[1].pointName" type="hidden" value="审查室领导审批意见"/>
<select        id="spyjWhere"      class="easyui-combobox"     name="progress[1].detail" style="width: 300px; height: 45px;">
             <option value="0" selected="selected" >请选择</option>
             <c:forEach var="act" items="${scsMethod}">
                 <option value="${act.value }">${act.name}</option>
             </c:forEach>
</select></td>
<td>审批时间</td>
<td><input  id="sptime" type="text" name='progress[1].timeForday' class="easyui-datebox" value="" style="width:300px;height:45px;" ></input>  
</td>
</tr>
<tr>
<td>市委主要领导签批时间</td>
<td>
<input name="progress[2].causeId"    type="hidden" value="${problemClues.id}"/>
<input name="progress[2].pointValue" type="hidden" value="24"/>
<input name="progress[2].pointName" type="hidden" value="市委主要领导签批意见"/>
<input   id="qpTime"  type="text" name='progress[2].timeForday' class="easyui-datebox"  style="width:300px;height:45px;" ></input>  
</td>
<td>执纪审查专题会议时间</td>
<td><input id=zjscTime_333  type="text" name='progress[3].timeForday' class="easyui-datebox"  style="width:300px;height:45px;" ></input>  
</td>
</tr>
<tr>
<td>会议决定</td>
<td><input name="progress[3].causeId"    type="hidden" value="${problemClues.id}"/>
<input name="progress[3].pointValue" type="hidden" value="25"/>
<input name="progress[3].pointName" type="hidden" value="审查室会议决定" />
<select  id="hyjd"    name="progress[3].detail" style="width:300px;height:45px;" class="easyui-combobox" validType="comboxValidate['hyjd','该项为必选项']">
             <option value="0" selected="selected" >请选择</option>
              <c:forEach var="act" items="${scsMethod}">
                    <option value="${act.value }">${act.name}</option>									                      
             </c:forEach>
</select>	
</td>
<td>移送何党组织</td>
<td><input id="ysPartyOrgan" type="text" 	style="width: 300px; height: 45px;"	  name="problemClues.reflectedPerson.ysPartyOrgan" placeholder="请输入名称" value="${problemClues.reflectedPerson.ysPartyOrgan}" /></td>
</tr>
<tr>
<td>调查组组长</td>
<td>
<input name="group[0].state" type="hidden" value="1"/>
<input name="group[0].groupIdentity" type="hidden" value="1"/>
<input id="zu_zhang" type="text" style="width:300px;height:45px;" class="form-control" name="group[0].groupName" placeholder="请输入调查组组长">
</td>
<td>调查组成员</td>
<td>
<input name="group[1].state" type="hidden" value="1"/>
<input name="group[1].groupIdentity" type="hidden" value="2"/>
<input id="zu_yuan" type="text" style="width:300px;height:45px;" class="form-control" name="group[1].groupName" placeholder="请输入调查组组员"></td>
</tr>
<tr>
<td>适当处理</td>
<td>
    <input name="progress[5].causeId"    type="hidden" value="${problemClues.id}"/>
	<input name="progress[5].pointValue" type="hidden" value="26"/>
	<input name="progress[5].pointName" type="hidden" value="审查室适当处理"/>
	<select id="cfResult2" class="easyui-combotree" name="progress[5].detail" style="width:300px;height:45px;"></select>  
 </td> 
<td>结案时间</td>
<td id="ja">
<input  name="progress[4].pointValue" type="hidden" value="26"  disabled/>
<input name="progress[4].pointName" type="hidden" value="审查室结案" disabled />
<input id="jaType" name="progress[4].detail" type="hidden" value="" disabled />
<input  id="jaTime"  type="text"  name='progress[4].timeForday'  class="easyui-datebox"  style="width:300px;height:45px;"  disabled></input>
</td>
</tr>
<tr>
<td>初核请示</td>
<td colspan="7">
 <label  for="上传初核请示">上传</label>
 <input class="btn btn-info" id="上传初核请示" style="visibility:hidden" type="file" multiple="multiple"  onchange="checkField('上传初核请示')"/>
 <label id="chqs_s_A" for="chqs_s_B">文件下载</label>
 <input id="chqs_s_B" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">
 <label id="chqs_s_C" for="chqs_s_D">文件删除</label>
 <input id="chqs_s_D" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="deleteFile(this)">		
</td>
</tr>
<tr>
<td>初核方案</td>
<td colspan="7">
 <label  for="初核方案">上传</label>
<input class="btn btn-info" id="初核方案" style="visibility:hidden" type="file" multiple="multiple"  onchange="checkField('初核方案')"/>
<label id="chfa_s_A" for="chfa_s_B">文件下载</label>
<input id="chfa_s_B" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">
<label id="chfa_s_C" for="chfa_s_D">文件删除</label>
<input id="chfa_s_D" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="deleteFile(this)">			
</td>
</tr>
<tr>
<td>审查报告</td>
<td colspan="7">
 <label  for="审查报告">上传</label>
<input class="btn btn-info" id="审查报告" style="visibility:hidden" type="file" multiple="multiple"  onchange="checkField('审查报告')"/>
<label id="scbg_s_A" for="scbg_s_B">文件下载</label>
<input id="scbg_s_B" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">
<label id="scbg_s_C" for="scbg_s_D">文件删除</label>
<input id="scbg_s_D" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="deleteFile(this)">		
</td>
</tr>
<tr>
<td>审查工作方案报告</td>
<td colspan="7">
 <label  for="审查工作方案报告">上传</label>
<input class="btn btn-info" id="审查工作方案报告" style="visibility:hidden" type="file" multiple="multiple"  onchange="checkField('审查工作方案报告')"/>
<label id="scgzfa_s_A" for="scgzfa_s_B">文件下载</label>
<input id="scgzfa_s_B" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">	
<label id="scgzfa_s_C" for="scgzfa_s_D">文件删除</label>
<input id="scgzfa_s_D" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="deleteFile(this)">		
</td>
</tr>
</table>
</form>
<script>

function sc_data(){
	$("#scs_cluesId").val($("#probleCluesId").val());
	
	if($("#state").val()){
		if($("#state").val().indexOf("LC")!=-1){
			$("#scs_lc").css('color','blue');
		}
		if($("#state").val().indexOf("TJ")!=-1){
			$("#scs_tj").css('color','blue');
		}
		
	}
	 $("#hyjd").combobox({
		 onChange: function(value){ 
			    var text=$("#hyjd").combobox('getText');
		        spyj_aaaa(value,text);
		   }
		});
	//加载最新审查室的进度
	$.ajax({
		url:"${path}/scs/sc_data",
		data:{id:$("#probleCluesId").val(),ip:$("#dataSourceIp").val()},
		type:"post",
		dataType:"json",
		success:function(result){
			 if(result.obj.progress){
			 var sdclValue="";
			  $.each( result.obj.progress, function(index, content){
			  if(content.pointName=="审查室处置建议"){
				  $("#scJY1").combobox('setValue',content.detail);
				  if(content.timeForday){
				  $("#chtime").datebox('setValue',content.timeForday.substring(0,10));
				  }
			  }
			  if(content.pointName=="审查室领导审批意见"){
				  $("#spyjWhere").combobox('setValue',content.detail);
				  if(content.timeForday){
				  $("#sptime").datebox('setValue',content.timeForday);
				  }
			  }
			  
			  if(content.pointName=="审查室受理"){
				 if(content.time){
					 $("#scstime").val(content.time.substring(0,10));
				 }
			  }
			  if(content.pointName=="审查室结案"){
					 if(content.time){
						 $("#jaTime").datebox('setValue',content.timeForday.substring(0,10));
					 }				 
				  }
			  
			  if(content.pointName=="审查室适当处理"){
				   sdclValue=content.detail;
			   } 
			  if(content.pointName=="市委主要领导签批意见"){
				  if(content.timeForday){
				  $("#qpTime").datebox('setValue',content.timeForday.substring(0,10));
				  }
			  }
			  if(content.pointName=="审查室会议决定"){
				  if(content.timeForday){
				  $("#zjscTime_333").datebox('setValue',content.timeForday.substring(0,10));
				  }
				  $("#hyjd").combobox('setValue',content.detail);
			  }
			  //审查室小组
			  if(result.obj.zu_yuan){
	         	    $('#zu_yuan').val(result.obj.zu_yuan.groupName);
			  }
			  if(result.obj.zu_zhang){
					  $("#zu_zhang").val(result.obj.zu_zhang.groupName);
			  }
			  if(result.obj.chbg){
				  if(result.obj.chbg){
                  $("#chbg_s_l").css("color","green");									  
				  $("#chbg_s").val(result.obj.chbg.url);
				  }
				  
			  }
			  if(result.obj.chfa){
				  if(result.obj.chfa){
                  $("#chfa_s_l").css("color","green");									  
				  $("#chfa_s").val(result.obj.chfa.url);
				  }
				  
			  }
			  if(result.obj.chqs){
				  if(result.obj.chqs){
                  $("#chqs_s_l").css("color","green");									  
				  $("#chqs_s").val(result.obj.chqs.url);
				  }
				  
			  }
			  if(result.obj.scbg){
				  if(result.obj.scbg){
                  $("#scbg_s_l").css("color","green");									  
				  $("#scbg_s").val(result.obj.scbg.url);
				  }
				  
			  }
			  if(result.obj.scgzfa){
				  if(result.obj.scgzfa){
                  $("#scgzfa_s_l").css("color","green");									  
				  $("#scgzfa_s").val(result.obj.scgzfa.url);
				  }
				  
			  }
			  if(result.obj.ysPartyOrgan){
				  $('#ysPartyOrgan').val(result.obj.ysPartyOrgan);
			  }
			 });
			  
			  
			 //适当处理
			  $('#cfResult2').combotree({
			        url: '${path }/dict/dictCombotree',
			        multiple: true,
			        panelHeight : 'auto',
			        queryParams:{dictPid:'02'},
			        onDblClick:function(node){
			        	if (node.state == 'open') {
			        		var node = $('#cfResult2').combotree('tree').tree('find', node.id);
				        	$('#cfResult2').combotree('tree').tree('collapse', node.target);
			        	}else{
			        		var node = $('#cfResult2').combotree('tree').tree('find', node.id);
				        	$('#cfResult2').combotree('tree').tree('expand', node.target);
			        	}
			        	
			        },
			        onBeforeExpand:function(node){
			        	 var childrenArr = $('#cfResult2').combotree('tree');  
			        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
				    },
				    onLoadSuccess:function(node,data){
				    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
				    	var ids=sdclValue.split(",");
				    	if(ids){
				    	for(var d=0;d<data.length;d++){
				    	      //遍历后台传来的值
				    	    if (ids.length > 0) {
			                for ( var i = 0; i < ids.length; i++) {
			                	if(ids[i].indexOf(data[d].dictId)!=-1){
			                		//展开该节点
			                		var node = $('#cfResult2').combotree('tree').tree('find', data[d].id);
			                		$('#cfResult2').combotree('tree').tree('expand', node.target);
			                		//杀死它
			                		if(ids[i]==data[d].dictId){
			                			$('#cfResult2').combotree('tree').tree('check', node.target);
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
		
			 						 			 
		}
		
	});
	
}

//初核阶段的诫勉谈话不分到审理室
function scs_save(state){
	$("#lc_scs").val(state)
	 var url="";
	/*  var hyjd = $('#hyjd').combobox('getValue');
	 if(hyjd=="6"){
		 url="${path}/scs/lasc";
	 }else{ */
		 url="${path}/scs/scs";
	/*  } */
	
	$("#lc_scs").val(state+$("#organId").val());
	var data=decodeURIComponent($("#scForm").serialize(),true);
	var isValid = $("#scForm").form('validate');
	if(isValid){
	   //保存进度并且保存处置方式
	    $.ajax({
		  url:url,
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

  function spyj_aaaa(spyjWhere,text){
  	
  	if(spyjWhere=="1"){
		   $("#ysPartyOrgan").val("");
  	}else if(spyjWhere=="0"){
		   $("#ysPartyOrgan").val("");
  	}else if(spyjWhere=="5"){
  	}else{
		   $("#ysPartyOrgan").val("");
  	}  	
  	if(spyjWhere!="1"){
  		//如果不是立案审查 则结案时间可填
  		$("#ja").children().each(function(){
  			if($(this).attr('id')=='jaTime'){
  				console.log('修改日期1');
  				$(this).datebox({disabled:false,required:true});
  				$("#jaType").val(text+"时间");
  			}else{
  				$(this).attr('disabled',false);
  			}
  			
  		});
  	}else{
  		$("#ja").children().each(function(){
  			if($(this).attr('id')=='jaTime'){
  				console.log('修改日期2');
  				$(this).datebox({disabled:true});
  			}else{
  				$(this).attr('disabled',true);
  			}
  		});
  	}
  }
</script>