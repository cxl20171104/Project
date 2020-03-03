<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs2.jsp" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:300px;}
.box2 { padding: 10px 0 10px 12px;}
.box2 textarea {
    width: 100%;
    height: 80px;
    padding: 2px 3px;
    margin-left: -12px;
    border: 3px solid #ccc;
    overflow: auto;
}
</style>
</head>
<body>
<input  type="hidden"  id="caseIdHidden"   value="0" />
<input  type="hidden"  id="pageName"  value="${pageName}" />
<input  type="hidden"  id="xzqh"      value="${xzqh}" />
<input  type="hidden"  id="roleId"    value="${roleId}" />  
<input  type="hidden"   id="dataSourceIp"           value="${dataSourceIp}"/>
<div id="tt" class="easyui-tabs" style="width:98%;">   
       <!-- 基本情况 -->
       <div title="线索基本信息" style="padding:20px;display:none;">   
       <c:choose>
       <c:when test="${fn:contains(roleId,9)!=false||fn:contains(roleId,17)!=false}">
        <%@ include file="../part/clues.jsp" %>   
       </c:when> 
        <c:when test="${fn:contains(roleId,10)!=false}">
        <%@ include file="../part/cluesJDS.jsp" %>   
        </c:when>
        <c:when test="${fn:contains('AGSTJ11',problemClues.state)!=false&&fn:contains(roleId,11)!=false||fn:contains(problemClues.state,'SCLC')!=false}">
        <%@ include file="../part/cluesSCS1.jsp" %>   
        </c:when>
        <c:when test="${fn:contains(roleId,11)!=false&&(fn:contains(problemClues.state,'SCTJ')!=false||fn:contains(problemClues.state,'LATJ')!=false||fn:contains(problemClues.state,'LALC')!=false)}">
        <%@ include file="../part/cluesSCS2.jsp" %>   
        </c:when> 
        <c:when test="${fn:contains(roleId,12)!=false}">
        <%@ include file="../part/cluesSLS.jsp" %>   
        </c:when>
        <c:otherwise>
        <%@ include file="../part/clues.jsp" %>   
        </c:otherwise>
        </c:choose>
        </div> 
        <shiro:hasPermission name="/part/ags">
        
        <div title="线索分办"  style="overflow:auto;padding:20px;display:none;">   
        <%@ include file="../part/ags.jsp" %>   
        
        </div> 
        </shiro:hasPermission>
	    <shiro:hasPermission name="/part/jds">
	    <c:if test='${ ((flag==null||flag=="") && xzqh=="00")}'>
	    <div title="线索直查"  style="padding:20px;display:none;">   
	       <%@ include file="../part/jds_new.jsp" %>  
	    </div> 
	     </c:if>
	    </shiro:hasPermission>  
	    <shiro:hasPermission name="/part/jds_jb">
	    <c:if test='${ (flag==null||flag=="")&& pageName!="workingDown"}'>
	    <div title="线索交办"  style="padding:20px;display:none;">   
	       <%@ include file="../part/jds_jb.jsp" %>   
	    </div>
	    </c:if>
	    </shiro:hasPermission>
	    <shiro:hasPermission name="/part/sc">
	     <c:if test='${ (flag==null||flag=="")}'>
	     <div title="审查处理" style="padding:20px;display:none;">  
	       <%@ include file="../part/sc.jsp" %>   
	    </div>
	    </c:if>
	    </shiro:hasPermission>
	    <shiro:hasPermission name="/part/la">
	     <c:if test='${ (flag==null||flag=="")}'>
         <div title="审查立案"  style="padding:20px;display:none;"> 
	           <%@ include file="../part/la.jsp" %>   
	     </div>  
	     </c:if>
	    </shiro:hasPermission>
	    <shiro:hasPermission name="/part/sl">
	     <c:if test='${ (flag==null||flag=="" )}'>
	    <div title="审理处理"  style="padding:20px;display:none;">   
	       <%@ include file="../part/sl.jsp" %>   
	    </div> 
	    </c:if>
	    </shiro:hasPermission>
	    <!-- 案管室补充 -->
	    <c:if test='${pageName=="library"
	    ||(pageName=="overing"&&organId=="11")
	    ||pageName=="AGSZAIBAN"
	    ||pageName=="add_from_jds"
	    ||pageName=="add_from_dfs"
	    ||pageName=="add_from_gbjds"
	    ||pageName=="xq"
	    ||pageName=="workingDown"
	    ||pageName=="holeWeb"}'>
	    <div title="线索直查"  style="padding:20px;display:none;">   
	       <%@ include file="../part/jds_new.jsp" %>  
	    </div> 
	    <div title="线索交办"  style="padding:20px;display:none;">   
	       <%@ include file="../part/jds_jb.jsp" %>   			
	    </div>
	     <div title="审查处理" style="padding:20px;display:none;">   
	       <%@ include file="../part/sc.jsp" %>   
	    </div>
         <div title="审查立案"  style="padding:20px;display:none;"> 
	           <%@ include file="../part/la.jsp" %>   
	     </div>  
	    <div title="审理处理"  style="padding:20px;display:none;">   
	       <%@ include file="../part/sl.jsp" %>   
	    </div> 
	    </c:if>
	     <!-- 监督室县区案件查看补充 -->
	     <c:if test='${pageName=="workingDown"&&organId!="11"}'>
	    <div title="线索分办"  style="overflow:auto;padding:20px;display:none;">   
             <%@ include file="../part/ags.jsp" %>   
        </div>
	    <div title="审查处理" style="padding:20px;display:none;">   
	       <%@ include file="../part/sc.jsp" %>   
	    </div>
         <div title="审查立案"  style="padding:20px;display:none;"> 
	           <%@ include file="../part/la.jsp" %>   
	     </div>  
	    <div title="审理处理"  style="padding:20px;display:none;">   
	       <%@ include file="../part/sl.jsp" %>   
	    </div> 
	    </c:if>
	     
        <div title="其他"  style="padding:20px;"> 
            <div style="visibility:hidden;">
             <%@ include file="../part/addAccDialog.jsp" %>  
            </div>   
            <c:if test="${organId=='11'&&(pageName=='newClues'||pageName=='working'||pageName=='AGSZAIBAN')}">
            <div>
               <%@ include file="../part/ags_tool.jsp" %>  
            </div>
            </c:if>
       </div>   
</div>  
</body>
<script type="text/javascript" src="${staticPath }/static/form_id.js"></script>
<script type="text/javascript">
$(function(){
	//获取当前屏幕的高度
	var height=window.innerHeight;
	$("#tt").css('height',height);
	var pageName = $("#pageName").val();
	var state = $("#state").val();
	//判读点击的哪个选项卡
	$("#tt").tabs({
		onSelect:function(title,index){
			
			if(title=="线索分办"){
				ags_data();
			}
			if(title=="线索直查"){
				jds_zc_data();
			}
			if(title=="线索交办"){
				jds_jb();
			}
			if(title=="审查处理"){
				sc_data();
			}
            if(title=="审查立案"){
				la_data();
			}
            if(title=="审理处理"){
				sl_data();
			}
            if(title=="其他"){
				load();
			}
		}
	    });
	        
});
//下载文件的方法（多个界面公用）
function download(obj){
	   //先判断文件是否存在
	   $.ajax({
		  url:"${path}/probleClues/isHaveFile",
		  data:{filePath:obj.value},
		  dataType:'json',
		  type:'post',
		  success:function(result){
			  if(result.success){
				  if(obj.value==""){
					  $.messager.alert('我的消息',"您还没有上传文件",'warning');
			    	   }else{
			    	   downloadFile("${path}/probleClues/accDownload",obj.value,obj.getAttribute("data-title"));
			    	   }
			  }else{
				  $.messager.alert('我的消息','文件不存在或已删除！','warning');
			  }
		  }
	   });
}
//删除已经上传的文件
function deleteFile(obj){
	   
	   //删除文件数据库中的数据
	   $.ajax({
			  url:"${path}/acc/remove",
			  data:{id:obj.value},
			  dataType:'json',
			  type:'post',
			  success:function(result){
				  if(result.success){
					  $.messager.alert('我的消息','文件删除成功！','info');
					  //修改界面的样式
					   $("#"+obj.id.substring(0,obj.id.lastIndexOf("_"))+"_A").css("color","black");
					   $("#"+obj.id.substring(0,obj.id.lastIndexOf("_"))+"_C").css("color","black");
					   $("#"+obj.id.substring(0,obj.id.lastIndexOf("_"))+"_B").val("");
					   $("#"+obj.id).val("");
				  }else{
					  $.messager.alert('我的消息','文件删除失败！','warning');
				  }
			  }
		   });
	
	
	
}
function downloadFile(actoinURL,filePath,fileName){  
	     var form = $("<form>");     
	    $('body').append(form);    
	        form.attr('style','display:none');     
	        form.attr('target','');  
	        form.attr('method','post');  
	        form.attr('action',actoinURL);//下载文件的请求路径  
	          
	          
	        var input1 = $('<input>');   
	        input1.attr('type','hidden');   
	        input1.attr('name','filePath');   
	        input1.attr('value',filePath);  
	        form.append(input1);    
	        var input2 = $('<input>');   
	        input2.attr('type','hidden');   
	        input2.attr('name','fileName');   
	        input2.attr('value',fileName);  
	        form.append(input2);  
	          
	        form.submit();      
	      
	    };
	    window.onload = function(){  
        	$("#measures").combobox({
        		onChange: function () {
        			   var measures = $('#measures').combobox('getValue');
        				if(measures=="1"){
        					$("#lienTime").datebox({disabled:false}); 
        					$("#lienBasis").attr("disabled",false);
        					$("#lienRelieveTime").datebox({disabled:false}); 
        					$("#delayLien").combobox({disabled:false}); 
        					$("#delayLienTime").datebox({disabled:false});
        				}else{
        					$("#lienTime").datebox({disabled:true});
        					$("#lienBasis").attr("disabled",true);
        					$("#lienRelieveTime").datebox({disabled:true}); 
        					$("#delayLien").combobox({disabled:true}); 
        					$("#delayLienTime").datebox({disabled:true});
        				}
        				

        			}
        	});
        	$("#delayLien").combobox({
        		onChange: function () {
     			   var delayLien = $('#delayLien').combobox('getValue');
     				if(delayLien=="1"){
     					//$("#delayLienDays").textbox({disabled:false});
     					$("#delayLienDays").attr("disabled",false);
     					$("#lienReason").attr("disabled",false);
     				}else{
     					//$("#delayLienDays").textbox({disabled:true});
     					$("#delayLienDays").attr("disabled",true);
     					$("#lienReason").attr("disabled",true);
     				}
     			}
        	});
        	$("#crime333").combobox({
        		onChange: function () {
     			   var crime333 = $('#crime333').combobox('getValue');
     				if(crime333=="1"){
     					$("#crimeCharge").combotree({disabled:false});
     				}else{
     					$("#crimeCharge").combotree({disabled:true});
     				}
     			}
        	});
        	$("#hyjd").combobox({
    	  		onChange: function () {
    	  			 var hyjd = $('#hyjd').combobox('getValue');
    	  			 if(hyjd=="5"){
    	  				$("#ysPartyOrgan").attr("disabled",false)
    	  			 }else{
    	  				$("#ysPartyOrgan").attr("disabled",true)
    	  			 }
    	  		}
    	  	});
        	 /* $("#progress4").combotree({
                 onChange:function(){
                	    var progress4 = $('#progress4').combotree('getValues');
        				if(progress4.indexOf('011904')!=-1||(progress4.indexOf('011801')!=-1&&progress4.indexOf('011802')!=-1&&progress4.indexOf('011803')!=-1&&progress4.indexOf('011804')!=-1&&progress4.indexOf('011805')!=-1&&progress4.indexOf('011806')!=-1)){        					
        					$("#jccf").combobox({disabled:false}); 
        					$("#hfql1").combobox({disabled:false}); 
        				}else{
        					$("#jccf").combobox({disabled:true}); 
        					$("#hfql1").combobox({disabled:true}); 
        				}
        				if(progress4.indexOf('011904')!=-1){
        					$("#cfsxTime").datebox({disabled:false}); 
        					$("#ldckTime").datebox({disabled:false});
        					 
        				}else{
        					$("#cfsxTime").datebox({disabled:true}); 
        					$("#ldckTime").datebox({disabled:true});
        					
        				}
        			}
        	});	  */
        	 $("#clues").combobox({
                 onChange:function(){
                	    var clues = $('#clues').combotree('getValues');
                	    if(clues=="15"||clues=="16"||clues=="23"){
                	    	$("#superviseTestTime").datebox({disabled:true}); 
                	    	$("#type").combobox({disabled:false});
                	    }else if(clues=="24"){
                	    	$("#superviseTestTime").datebox({disabled:false}); 
                	    	$("#type").combobox({disabled:true}); 
                  	
                		}else{
                			$("#superviseTestTime").datebox({disabled:true});
                			$("#type").combobox({disabled:true});                 			
                		}
                	    if(parent.sjw_value.indexOf(","+clues+",")!=-1){
                	    	$("#lwbh").attr("disabled",false) 
	              		}else{
	              			$("#lwbh").attr("disabled",true) 
	              	      
	              		} 
        			}
        	});	
        	 $("#isResult").combobox({
                 onChange:function(){
             	    var isResult = $('#isResult').combotree('getValues');
             	    if(isResult=="1"){
             	    	$("#resultTime").datebox({disabled:false}); 
             	    }else{
             	    	$("#resultTime").datebox({disabled:true});
             	    	$("#resultTime").datebox({required:true});
             	    }
     			}
     		});	
        	
        	 $("#isPMSupervisoryObject").combobox({
                 onChange:function(){
             	    var isPMSupervisoryObject = $('#isPMSupervisoryObject').combotree('getValues');
             	    if(isPMSupervisoryObject=="1"){
             	    	$("#pMSupervisoryObject").combobox({disabled:false}); 
             	    }else{
             	    	$("#pMSupervisoryObject").combobox({disabled:true}); 
             	    }
     			}
     		});	
        	 $("#iSupervision").combobox({
                 onChange:function(){
             	    var iSupervision = $('#iSupervision').combotree('getValues');
             	    if(iSupervision=="1"){
             	    	$("#supervision").combobox({disabled:false}); 
             	    }else{
             	    	$("#supervision").combobox({disabled:true}); 
             	    }
     			}
     		});	
        	 $("#isOtherTransfer").combobox({
                 onChange:function(){
             	    var isOtherTransfer = $('#isOtherTransfer').combotree('getValues');
             	    if(isOtherTransfer=="1"){
             	    	$("#otherTransfer").combobox({disabled:false}); 
             	    }else{
             	    	$("#otherTransfer").combobox({disabled:true}); 
             	    }
     			}
     		});	
        	 $("#lienTime").datebox({
                 onChange:function(){
             	    var lienTime = $('#lienTime').datebox('getValues');
             	   var lienRelieveTime = $('#lienRelieveTime').datebox('getValues');
             	   if(lienTime!=""&&lienTime!=null&&lienRelieveTime!=""&&lienRelieveTime!=null){
             		  var lienTime = new Date(lienTime); 
             		  var lienRelieveTime = new Date(lienRelieveTime); 
             		   var time = (lienRelieveTime.getTime() - lienTime.getTime())/(24 * 60 * 60 * 1000);
             		  $('#lienDays').val(time);	
             	   }
     			}
     		});	
        	 $("#lienRelieveTime").datebox({
                 onChange:function(){
             	    var lienTime = $('#lienTime').datebox('getValues');
             	   var lienRelieveTime = $('#lienRelieveTime').datebox('getValues');
             	  // var delayLienDays = $('#delayLienDays').val();
             	var delayLienDays = $('#delayLienDays').val();
             	   if(lienTime!=""&&lienTime!=null&&lienRelieveTime!=""&&lienRelieveTime!=null){
             		  var lienTime = new Date(lienTime); 
             		  var lienRelieveTime = new Date(lienRelieveTime); 
             		   var time = (lienRelieveTime.getTime() - lienTime.getTime())/(24 * 60 * 60 * 1000);
             		  $('#lienDays').val(time);	
             	   }
             	  if(lienRelieveTime!=""&&lienRelieveTime!=null&&delayLienDays!=null&&delayLienDays!=""){
             		 var date='"'+lienRelieveTime+'"';
                 	  var daysInt=parseInt(delayLienDays);
                 	  var vall=addDate(date,daysInt);
                 	 $('#delayLienTime').datebox('setValue', vall);
             	   } 
     			}
     		});	

        	 $("#handlingContent").combobox({
                 onChange:function(){
     	    	var hc = $("#handlingContent").combobox('getValue');
     	    	if(hc=="1"){
     	    		$('#handlingContentJT').next(".combo").hide();
     	    		$('#handlingContentJT1').next(".combo").show();
     	    		$('#handlingContentJT2').next(".combo").hide();
     	    	}else{
     	    		$('#handlingContentJT').next(".combo").hide();
     	    		$('#handlingContentJT1').next(".combo").hide();
     	    		$('#handlingContentJT2').next(".combo").show();
     	    		
     	    	}
     	    	
                 }
      		});	
        	 $("#handlingContentJT1").combobox({
                 onChange:function(){
     	    	var hc = $("#handlingContentJT1").combobox('getValue');
     	    	$("#handlingContentJT").combobox('setValue',hc);
     	    	
                 }
      		});	
        	 $("#handlingContentJT2").combobox({
                 onChange:function(){
     	    	var hc = $("#handlingContentJT2").combobox('getValue');
     	    	$("#handlingContentJT").combobox('setValue',hc);
     	    	
                 }
      		});	
      		 $("#stayTerm").combobox({
                 onChange:function(){
     	    		var hc = $("#stayTerm").combobox('getValue');
	     	    	if(hc=='1'){
	     	    		var cfsxTime = $('#cfsxTime').datebox('getValues');
	     	    		var d=new Date(cfsxTime); 
	     		        d.setFullYear(d.getFullYear()+1); 
	     		       d.setMonth(d.getMonth()+1); 
	     		      var r = d.getFullYear()+'-'+d.getMonth()+'-'+d.getDate(); 
	     		      $('#stayTermEndTime').datebox('setValue', r);
	     	    	}else if(hc=='2'){
	     	    		var cfsxTime = $('#cfsxTime').datebox('getValues');
		     	    		var d=new Date(cfsxTime); 
		     		        d.setFullYear(d.getFullYear()+2); 
		     		       d.setMonth(d.getMonth()+1); 
		     		       var r = d.getFullYear()+'-'+d.getMonth()+'-'+d.getDate(); 
		     		      $('#stayTermEndTime').datebox('setValue', r);
	     	    	}
     	    	
                 }
      		});
      		 $("#cfsxTime").combobox({
                 onChange:function(){
     	    		var hc = $("#stayTerm").combobox('getValue');
	     	    	if(hc=='1'){
	     	    		var cfsxTime = $('#cfsxTime').datebox('getValues');
	     	    		var d=new Date(cfsxTime); 
	     		        d.setFullYear(d.getFullYear()+1); 
	     		       d.setMonth(d.getMonth()+1); 
	     		      var r = d.getFullYear()+'-'+d.getMonth()+'-'+d.getDate(); 
	     		      $('#stayTermEndTime').datebox('setValue', r);
	     	    	}else if(hc=='2'){
	     	    		var cfsxTime = $('#cfsxTime').datebox('getValues');
		     	    		var d=new Date(cfsxTime); 
		     		        d.setFullYear(d.getFullYear()+2); 
		     		       d.setMonth(d.getMonth()+1); 
		     		       var r = d.getFullYear()+'-'+d.getMonth()+'-'+d.getDate(); 
		     		      $('#stayTermEndTime').datebox('setValue', r);
	     	    	}
     	    	
                 }
      		});
      		
      		 $("#progress4").combotree({
                 onChange:function(){
                		var ll = $('#progress4').combotree('getValues');
                		var a=ll.join(",");
                		var l = RegExp(/0118/);
			    		if(a.indexOf('0119')!=-1){
			    			//处分决定生效时间
			    			$("#cfsxTime").datebox({disabled:false}); 
			    			//处分决定执行完毕时间
			    			$("#takeEffectEndTime").datebox({disabled:false});
			    			
			    			
			    			if(a.indexOf('011904')!=-1){
			    				//恢复党员权力
				    			$("#hfql1").combobox({disabled:false});	
				    			//留党察看年限
				    			$("#stayTerm").combobox({disabled:false});
			    			}
			    			
			    			
			    			
			    			
			    		/* 	$('#ysCharge').attr("disabled","disabled");
			    			$("#ysChargeTime").datebox({disabled:true});
			    			$('#ysChargeMoney').attr("disabled","disabled"); */
			    		}else{
			    			$("#cfsxTime").datebox({disabled:true}); 
			    			$("#stayTerm").combobox({disabled:true}); 
			    			$("#takeEffectEndTime").datebox({disabled:true}); 
			    			$("#hfql1").combobox({disabled:true});	
			    		}
			    			if(l.exec(ll)){
			    				$("#cfsxTime2").datebox({disabled:false}); 
			    				$('#influencePeriod').textbox({disabled:false});
				    			//$("#jccf").combobox({disabled:false});
				    			//$("#hfql1").combobox({disabled:false});	
				    			/* $('#ysCharge').attr("disabled","disabled");
				    			$("#ysChargeTime").datebox({disabled:true}); 
				    			$('#ysChargeMoney').attr("disabled","disabled"); */
				    		}else{
				    			$("#cfsxTime2").datebox({disabled:true}); 
			    				$('#influencePeriod').textbox({disabled:true});
				    		}
			    			if(a.indexOf('0121')!=-1){
				    			//$('#ysCharge').attr("disabled","disabled"); 
				    			$("#ysCharge").removeAttr("disabled"); 
				    			$("#ysChargeTime").datebox({disabled:false}); 	
				    			 //$('#ysChargeTime').removeAttr("disabled");
				    			$("#ysChargeMoney").removeAttr("disabled"); 						    			
				    		}else{
				    			$('#ysCharge').attr("disabled","disabled");
				    			$("#ysChargeTime").datebox({disabled:true});
				    			$('#ysChargeMoney').attr("disabled","disabled");
				    		}
			    		
     	    	
                 }
      		});
        	
        } 
	    function money(length){
            if(length>3){
            	$.messager.alert('消息','此处单位为万元！','info');
            }

	    } 
	    function addDate(date,days){ 
	        var d=new Date(date); 
	        d.setDate(d.getDate()+days); 
	        var m=d.getMonth()+1; 
	        return d.getFullYear()+'-'+m+'-'+d.getDate(); 
	    }
	    function compute(){
	    	 var delayLienDays = $('#delayLienDays').val();
       	   var lienRelieveTime = $('#lienRelieveTime').datebox('getValues');
       	var daysInt=parseInt(delayLienDays);
       	 if(daysInt>90){
         	$.messager.alert('消息','最多延期90天！','info');
         	 var delayLienDays = $('#delayLienDays').val("");
         	$('#delayLienTime').datebox('setValue',''); 
         }else if(lienRelieveTime!=""&&lienRelieveTime!=null&&delayLienDays!=null&&delayLienDays!=""){
       		  var date='"'+lienRelieveTime+'"';            	  
            	  var vall=addDate(date,daysInt);
            	  $('#delayLienTime').datebox('setValue',vall); 
       	   }
	    }
	    $.extend($.fn.validatebox.defaults.rules, {  
		    comboxValidate : {  
		        validator : function(value, param,missingMessage) {  
		            if($('#'+param).combobox('getValue')!='0' &&$('#'+param).combobox('getValue')!='' && $('#'+param).combobox('getValue')!=null){  
		                return true;  
		            }  
		            return false;  
		        },  
		        message : "{1}"  
		    },idcard: {// 验证身份证
	            validator: function (value) {
	                return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
	            },
	            message: '身份证号码格式不正确'
	        },phone:{
	        	validator: function (value) {
	        		var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
	                return myreg.test(value);
	            },
	            message: '手机号码格式不正确'
	        },number:{
	        	validator: function (value) {
	        		var myreg=/^[0-9]$/;
	                return myreg.test(value);
	            },
	            message: '请填入数字'
	        } 
		}); 
	    //关闭当前界面
        function cancel(){
 		   var index_tabs=parent.index_tabs;
 		   var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
            var tab = index_tabs.tabs('getTab', index);
            if (tab.panel('options').closable) {
                index_tabs.tabs('close', index);
            }
 	    }
	    //当前界面的索引
	    function currentIndex(){
	    	   var index_tabs=parent.index_tabs;
	 		   var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
	 		   return index;
	    }
	    //刷新树
	    function treeReload(id){
	    	
	    	var tree= parent.tree;
	    	var node;
	    	if(id){
	    		 node = tree.tree('find', id);
	    	}else{
	    		 node= tree.tree('getSelected');
	    	}
	    	var nodeName=node.name;
	    	
	    	parent.menuId=node.id;
	    	$.getJSON("${path}/index/indexNum?nodeName="+nodeName,function(req){
	    		    if(req.success){
	    			parent.menu_num[req.obj.nodeName]=req.obj.createNum+'';
	    	    	parent.loadMenuTree();
	    	    	cancel();
	    		    }
	    		});
	    	
	    	
         }
	       //除了待办件 在办件  都设置成禁用表单 但是现在要添加一个用户不用禁用表单 当用户拥有  id为18时可以实现这种情况
	       console.log($("#pageName").val());
	       var roleId=$("#roleId").val();
	     
	       if(roleId.indexOf("18")<0){
           if(     $("#pageName").val()!="newClues"
        		   &&$("#pageName").val()!="having"
        		   &&$("#pageName").val()!="returnList"
        		   &&$("#pageName").val()!="tongan"){
        	  
            var tags = document.getElementsByTagName('*');
            console.log(form_ids);
            for (var i = 0; i < tags.length; i++) {
            if(tags[i].id){
                console.log(tags[i].id);
               if(form_ids.indexOf(tags[i].id)!=-1){
                 console.log(tags[i].id);
                 tags[i].disabled=true;
               }
               
            }
            } 
           }
	       }
           function getIt(id){
        		$.messager.confirm('提醒', '确认签收？', function(r){
        			if(r){
        				$.ajax({
        					url:"${path}/probleClues/get",
        					data:{id:id},
        					dataType:'json',
        					type:'post',
        					success:function(result){
        						if(result.success){
        							$.messager.show({
        					 			title:'消息提示',
        					 			msg:'线索已经签收',
        					 			timeout:3000,
        					 			showType:'slide'
        					 		});
        							//刷新树
        							treeReload();
        						}
        						
        					}
        				})
        			}
        		});
        		
        	}
        	//（新添加）
        	function backIt(id){
        		parent.$.modalDialog({
        	        title : '退回案件',
        	        width : 880,
        	        height : 370,
        	        href : path+'/probleClues/call_back?id='+id+"&type=TH",
        	        buttons : [ {
        	            text : '确定',
        	            iconCls:'icon-ok',
        	            handler : function() {
        	                parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
        	                var f = parent.$.modalDialog.handler.find('#back_form');
        	                f.submit();
        	            }
        	        },{
        				text:'取消',
        				iconCls:'icon-cancel',
        				handler:function(){
        					parent.$.modalDialog.handler.dialog('close');
        				}
        			}]
        	    });
        	}
</script>
</html>

