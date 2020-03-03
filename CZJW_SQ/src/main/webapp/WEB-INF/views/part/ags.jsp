<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="agsForm" name="agsForm" >
<input id="cluesId_ags" name="problemClues.id" value="${problemClues.id}" type="hidden" />
<input id="lc_ags" name="problemClues.state"   value="${problemClues.state}" type="hidden" />
<input type="hidden" name="problemClues.isXf" value="${problemClues.isXf}" />
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="ags_lc" type="button" class="btn btn-default" onclick="ags_save('AGSLC');">保存</button>
</c:if>
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="ags_tj" type="button" class="btn btn-default" onclick="ags_save('AGSTJ');">提交</button>
</c:if>
 <div style="visibility:hidden"> 
<input   id="zjt_ags"  type="text" name="progress[0].timeForday" class="easyui-datebox" value="" style="width:300px;height:45px;"></input>  
<input   id="fbt_ags" type="text" name="progress[1].timeForday" class="easyui-datebox" value="" style="width:300px;height:45px;" ></input> 
<input   id="org_ags"  name="progress[1].detail"  value=""/><!--承办部门-->
<textarea  id="remark_ags"  name="progress[1].remark" class="form-control" rows="4" cols="50"></textarea>
 </div> 
<div id="history">


</div>
<hr style="border:red;border:5px solid black;"/>
<table class="cxl">
<tr>
<th>一般案件分办</th>
</tr>
 <tr>
<td><label class="col-md-1 control-label">分办时间</label></td>
<td><input id="fbTime_yb" type="text"  class="easyui-datebox" value="" data-options="onSelect:function(date){fbTime('fbTime_yb',date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate())}" style="width:300px;height:45px;" ></input>  
<td>承办部门</td>
<td><input id="organId_yb"        style="width:300px;height:45px;" ></input></td>
</tr>
<tr>
<td><label class="col-md-1 control-label">备注</label></td>
<td colspan="3">
<input id="remark_yb"  class="form-control" style="width:100%;height:45px;"  oninput="remark('remark_yb');"></input>
</td>
 </tr>
 <!-- 党风室分办案件只能分办到一到五室和下发县区 -->

 
 
 <!-- 县区不能有指定 管辖  -->

 <tr>
 <th>指定管辖</th>
 </tr>
 <tr>
 <td><label class="col-md-1 control-label">分办时间</label></td>
<td><input id="fbTime_gx" type="text"  class="easyui-datebox" value="" data-options="onSelect:function(date){fbTime('fbTime_yb',date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate())}" style="width:300px;height:45px;" ></input>  
<td>承办部门</td>
<td>
<select    id="organId_gx" class="easyui-combobox"  style="width:300px;height:45px;"  >
</select>	 
</td>
 </tr>
 <tr>
<td><label class="col-md-1 control-label">备注</label></td>
<td colspan="3">
<input id="remark_gx"  class="form-control" style="width:100%;height:45px;"  oninput="remark('remark_gx');"></input>
</td>
</tr>

 <c:if test='${organId!="27" }'>
 <tr>
    <th>执纪审查专题会议决定</th>
 </tr>
<tr>
<td><label class="col-md-2 control-label">执纪审查专题会议时间</label></td>
<td><input id='zjscTime' type="text"   class="easyui-datebox" value="" data-options="onSelect:function(date){fbTime('zjscTime',date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate())}" style="width:300px;height:45px;"></input>  
</td>
<td><label class="col-md-1 control-label">分办时间</label></td>
<td><input id="fbTime_zj" type="text"  class="easyui-datebox" value="" data-options="onSelect:function(date){fbTime('fbTime_zj',date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate())}" style="width:300px;height:45px;"></input>  
</tr> 
<tr>
<td>承办部门</td>
<td><select id="organId_zj"    class="easyui-combobox"   style="width:300px;height:45px;"    >
</select></td>
<td><label class="col-md-1 control-label">备注</label></td>
<td colspan="2">
<input id="remark_zj"  class="form-control" style="width:100%;height:45px;" oninput="remark('remark_zj');"></input>
</td>
</tr>
</c:if>
</table>
</form>
<script>
function organ(value){
	$("#org_ags").val(value);
}
function remark(id){
	$("#remark_ags").html($("#"+id).val());
}
function fbTime(id,value){
	if(id=="zjscTime"){
		
		$("#zjt_ags").datebox('setValue',value);
	}else{
		
		$("#fbt_ags").datebox('setValue',value);
	}
}
//增加同案人员
function addTonan(){
	//获取存储的案件编号和线索id
	 var cluesNum=$("#caseId2").val();
	 var cid=$("#probleCluesId").val();
	 parent.addTab("添加同案人员"+cluesNum,"${path}/probleClues/tongan?id="+cid);
}
           //表单提交
           function save(){  
        	   var data= $('#myForm').serialize();
        	   var submitData=decodeURIComponent(data,true);
        	   console.log("submitDataLLLLLL："+submitData);
        	 //submitData是解码后的表单数据，结果同上
        	 var url="";
             if($("#probleCluesId").val()!=""){
            	 url='${path}/probleClues/edit';
             }else{
            	 //添加线索时需要检查重复件问题
            	 url='${path}/probleClues/add?proTime='+proTime;
             }
        	 $.ajax({
        	     url:url,
        	     data:submitData,
        	     cache:false,//false是不缓存，true为缓存
        	     async:true,//true为异步，false为同步
        	     type:'post',
        	     dataType:'json',
        	     beforeSend:function(){
        	         //请求前
        	     },
        	     success:function(result){
        	    	 console.log(result.success);
        	         //请求成功时
        	         if(result.success){
        	        	    //清空表单
        	        	    if(result.msg=="repeat"){
        	        	    		//打开重复件列表
            	        	    	parent.addTab("重复线索处理","${path}/probleClues/repeater_clues?reflectedName="+result.obj);
        	        	    }else{
        	        	    console.log(result.obj);
        	        	    if(result.obj!=null&&result.obj!=""){
        	        	    	tz="添加新线索";
        	        	    	$("#probleCluesId").val(result.obj);
        	        	    	$.messager.alert('我的消息',result.msg,'info');
        	        	    	//如果点击保存按钮则关闭当前界面并刷新列表页
        	        	    	if(choice==1){
        	        	    		//刷新datagrid
        	        	    	   var index_tabs=parent.index_tabs;
        	  					   var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
        	  			    	   parent.reloadTabGrid("待办件", index);
        	        	    	}
        	        	    	
        	        	    }else{
        	        	    	
        	        	    	 $.messager.alert('我的消息',result.msg,'info');
        	        	    }
        	        	    }
        				    
        				    
        	         }else{
        	        	  $.messager.alert('我的消息',result.msg,'warning');
        	         }
        	         
        	     },
        	     complete:function(){
        	         //请求结束时
        	     },
        	     error:function(){
        	         //请求失败时
        	     }
        	 })
           }
    //如果该标签被点击则加载 案管室
    function ags_data(){
    	//给本页附上线索id提交表单时使用
    	//给本页附上线索id提交表单时使用
    	$("#cluesId_ags").val($("#probleCluesId").val());
    	if($("#lc_ags").val()){
    		if($("#lc_ags").val().indexOf("LC")!=-1){
    			$("#ags_lc").css('color','blue');
    		}
    		if($("#lc_ags").val().indexOf("TJ")!=-1){
    			$("#ags_tj").css('color','blue');
    		}
    		
    	}
    	console.log("加载案管室分办或交办数据");
    	  $("#organId_yb").combobox({
	    		url:'${path}/organization/findOrganizationByPid?pid=12',
	    		valueField:'id',    
			    textField:'text',
	    		onSelect:function(data){
	    			organ(data.id)
	    		},
	    		 loadFilter:function(data){
	    			console.log(data);
	    			var arr=new Array();
	    			for(x in data){
	    				
	    				if(parseInt(data[x].id)<18){
	    					arr[x]=data[x];
	    				}
	    			}
	    			var option=new Object();
	    			option["id"]='26';
	    			option["text"]="干部监督室";
	    			arr[arr.length]=option;
	    			var qc_dfs=new Object();
	    			qc_dfs["id"]='27';
	    			qc_dfs["text"]="党风室";
	    			arr[arr.length]=qc_dfs;
	    			var qc_qc=new Object();
	    			qc_qc["id"]='29';
	    			qc_qc["text"]="其他";
	    			arr[arr.length]=qc_qc;
	    			return arr;
	    		} 
	    	}); 
	    	$("#organId_zj").combobox({
	    		valueField:'id',    
			    textField:'text',
	    		data: [{
	    			id: '18',
	    			text: '第六纪检监察室'
	    		},{
	    			id: '19',
	    			text: '第七纪检监察室'
	    		},{
	    			id: '20',
	    			text: '第八纪检监察室'
	    		},{
	    			id: '21',
	    			text: '第九纪检监察室'
	    		},{
	    			id: '22',
	    			text: '第十纪检监察室'
	    		},{
	    			id: '23',
	    			text: '第十一纪检监察室'
	    		},{
	    			id: '24',
	    			text: '第十二纪检监察室'
	    		},{
	    			id: '25',
	    			text: '第十三纪检监察室'
	    		}],
	    		onSelect:function(data){
	    			organ(data.id)
	    		}
	    	}); 
  	$("#organId_gx").combobox({
  		url:'${path}/organization/findOrganizationByPid?pid=32',
  		valueField:'id',    
		    textField:'text',		
  		onSelect:function(data){
  			organ(data.id);
  		}
  	});
    	$.ajax({
    		url:"${path}/ags/ags_fb",
    		data:{id:$("#probleCluesId").val(),ip:$("#dataSourceIp").val()},
    		type:"post",
    		dataType:"json",
    		success:function(result){
    			console.log(result);
    			//加载最新的分办情况
    			if(result.obj.progress){
    			 var firstValue=true;
    			 $("#history").html("");
    			 $.each( result.obj.progress, function(index, content){
    				         if(content.pointName=="案管室分办"&&content.detail.length<4){
    					     //part1:一般案件
    					     if(content.detail<18||content.detail==26||content.detail==29){
    						  var str="一般案件分办";
    						  if(result.obj.progress[index+1]&&result.obj.progress[index+1].pointName=="退回"){
    							  if(result.obj.progress[index+1].time){
        							  str+="(退回时间:"+result.obj.progress[index+1].time.substring(0,10)+")"    								  
    							  }
    						  }
    						  var html=makeFB_YB(index,"一般案件分办");
    						  $("#history").append(html);
    						  $("#"+index+"fbt").datebox({disabled:true});
    						  if(result.obj.progress[index].timeForday){
    							  $("#"+index+"fbt").datebox('setValue',result.obj.progress[index].timeForday.substring(0,10));
    						  }    						  
    						  $("#"+index+"cb").val("承办部门");
    						  $("#"+index+"bm").combobox({
    							    url:'${path}/organization/findOrganizationByPid?pid=12',    
    							    valueField:'id',    
    							    textField:'text'   

    						  });
    						  $("#"+index+"bm").combobox({disabled:true});
    						  $("#"+index+"bm").combobox('setValue',content.detail);
    						  $("#"+index+"remark").val(content.remark);
    						  
    					      }
    					     
    					     
    					     
    					     
    					      //part2:指定管辖
    					      if(content.detail>32){
    						  var html=makeFB_GX(index,"指定管辖");
    						  $("#history").append(html);
    						  $("#"+index+"fbt").datebox({disabled:true});
    						  if(result.obj.progress[index].timeForday){
    							  $("#"+index+"fbt").datebox('setValue',result.obj.progress[index].timeForday.substring(0,10));
    						  }    						
    						  $("#"+index+"cb").val("基层单位");  
    						  $("#"+index+"bm").combobox({
    							    url:'${path}/organization/findOrganizationByPid?pid=32',    
    							    valueField:'id',    
    							    textField:'text'   

    						  });
    						  $("#"+index+"bm").combobox({disabled:true});
    						  $("#"+index+"bm").combobox('setValue',content.detail);
    						  $("#"+index+"remark").val(content.remark);
    					      }
    					      
    					      
    					      //part3:执纪审查专题会议决定
    					      if(content.detail>=18&&content.detail<26){
    					    	  var str="执纪审查专题会议决定";
    							  if(result.obj.progress[index+1]&&result.obj.progress[index+1].pointName=="退回"){
    								  if(result.obj.progress[index+1].time){
    									  str+="(退回时间:"+result.obj.progress[index+1].time.substring(0,10)+")"
    								  }							  
    							  }
    					    	  var html=makeFB(index,str);
    	    					  $("#history").append(html);
    	    					  $("#"+index+"zjt").datebox({disabled:true});
    	    					  if(result.obj.progress[index-1].timeForday){
    	    						  $("#"+index+"zjt").datebox('setValue',result.obj.progress[index-1].timeForday.substring(0,10));  
    	    					  }						  
    							  $("#"+index+"fbt").datebox({disabled:true});
    							  if(result.obj.progress[index].timeForday){
    								  $("#"+index+"fbt").datebox('setValue',result.obj.progress[index].timeForday.substring(0,10));
    							  }						  
    							  $("#"+index+"cb").val("承办部门");
    							  $("#"+index+"bm").combobox({
    								    url:'${path}/organization/findOrganizationByPid?pid=12',    
    								    valueField:'id',    
    								    textField:'text'   

    							  });
    							  $("#"+index+"bm").combobox({disabled:true});
    							  $("#"+index+"bm").combobox('setValue',content.detail);
    							  $("#"+index+"remark").val(content.remark);
    					      }
    					      
    					      
    					      
    					      
					      }
    				  
    				  
    				  
    				  
    				  //part4:案件再分办
    				  if(content.pointName=="案管室受理再分办"&&content.detail.length<4){
						  var str="执纪审查专题会议决定";
						  if(result.obj.progress[index+1]&&result.obj.progress[index+1].pointName=="退回"){
							  if(result.obj.progress[index+1].time){
								  str+="(退回时间:"+result.obj.progress[index+1].time.substring(0,10)+")"
							  }							  
						  }
    					  var html=makeFB(index,str);
    					  $("#history").append(html);
    					  $("#"+index+"zjt").datebox({disabled:true});
    					  if(result.obj.progress[index-1].timeForday){
    						  $("#"+index+"zjt").datebox('setValue',result.obj.progress[index-1].timeForday.substring(0,10));  
    					  }						  
						  $("#"+index+"fbt").datebox({disabled:true});
						  if(result.obj.progress[index].timeForday){
							  $("#"+index+"fbt").datebox('setValue',result.obj.progress[index].timeForday.substring(0,10));
						  }						  
						  $("#"+index+"cb").val("承办部门");
						  $("#"+index+"bm").combobox({
							    url:'${path}/organization/findOrganizationByPid?pid=12',    
							    valueField:'id',    
							    textField:'text'   

						  });
						  $("#"+index+"bm").combobox({disabled:true});
						  $("#"+index+"bm").combobox('setValue',content.detail);
						  $("#"+index+"remark").val(content.remark);
    					 
						  
    				  }
    			 });
    		}
    		}
    	});
		
    	
    }
    
    function makeFB(index,text){
    	var html= '<table class="cxl" style="margin-top:2%;"><tr><th>'+text+'</th></tr><tr><td><label>执纪审查专题会议时间</label></td>'+
    	          '<td><input id="'+index+'zjt" class="easyui-datebox" style="width:300px;height:45px;"/>'+
    	          '<td><label>分办时间</label></td>'+
    	          '<td><input id="'+index+'fbt" class="easyui-datebox" style="width:300px;height:45px;"/>'+
    	          '<tr>'+
    	          '<td><label>承办部门</label></td>'+
    	          '<td><input id="'+index+'bm"  style="width:300px;height:45px;"/>'+
    	          '<td><label>备注</label></td>'+
    	          '<td>'+
    	          '<input id="'+index+'remark"  class="form-control" style="width:100%;height:45px;" disabled></input>'+
    	          '</td></tr></table>';
    	          return html;
    }
    function makeFB_YB(index,text){
    	var html= '<table class="cxl" style="margin-top:2%;"><tr><th>'+text+'</th></tr><tr>'+
    	          '<td><label>分办时间</label></td>'+
    	          '<td><input id="'+index+'fbt" class="easyui-datebox" style="width:300px;height:45px;"/>'+
    	          
    	          '<td><label>承办部门</label></td>'+
    	          '<td><input id="'+index+'bm"  style="width:300px;height:45px;"/></tr>'+
    	          '<tr>'+
    	          '<td ><label>备注</label></td>'+
    	          
    	          '<td colspan="3">'+
    	          '<input id="'+index+'remark"  class="form-control" style="width:100%;height:45px;" disabled></input>'+
    	          '</td></tr></table>';
    	          return html;
    }
    function makeFB_GX(index,text){
    	var html= '<table class="cxl" style="margin-top:2%;"><tr><th>'+text+'</th></tr><tr>'+
    	          '<td><label>分办时间</label></td>'+
    	          '<td><input id="'+index+'fbt" class="easyui-datebox" style="width:300px;height:45px;"/>'+
    	          
    	          '<td><label>承办部门</label></td>'+
    	          '<td><input id="'+index+'bm"  style="width:300px;height:45px;"/></tr>'+
    	          '<tr>'+
    	          '<td><label>备注</label></td>'+
    	          
    	          '<td colspan="3">'+
    	          '<input id="'+index+'remark"  class="form-control" style="width:100%;height:45px;" disabled></input>'+
    	          '</td></tr></table>';
    	          return html;
    }
    function ags_save(state){
  	  if(!$("#probleCluesId").val()){
  		  $.messager.alert('我的消息','请先保存线索','warning');
  		  return false;
  	  }
  	  $("#lc_ags").val(state+$("#organId").val());
	       var data=decodeURIComponent($("#agsForm").serialize(),true);
	       var isValid = $("#agsForm").form('validate');
	       if(isValid){
		   //保存进度并且保存处置方式
		   $.ajax({
			  url:"${path}/ags/agsfb",
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