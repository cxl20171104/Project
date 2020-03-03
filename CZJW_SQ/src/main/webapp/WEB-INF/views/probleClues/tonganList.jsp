<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/problelist.css" />
<title>用户管理</title>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
     <%@ include file="/commons/basePage.jsp" %>
  	 <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
  	        <%@ include file="/commons/baseSpecial.jsp" %>
       	    <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun2();" >查询</a>
       	    <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
    </div> 
    
    <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
	</div>  
	<script type="text/javascript">
		var path='${path }';
		     $(function(){
	        	//[待接收案件]
	        	    var dataGrid;
	        	    var special="";
	        	    var path =path;
	        	    var sqlStore="";
	        	    var f = $("#finalState").val();
	        	     //  alert(f);
	        	     $(function() {
	        	    	//将pageName赋给全局变量 version2Name  以后  在任何界面可以使用 parent.version2Name获取
	        		    var datag = "#dataGrid";
	        	        dataGrid =	$('#dataGrid').datagrid({
	        	        	 toolbar:'#tb',
	        	        	  url : '${path}/page/getListData',
	        	            fit : true,
	        	            idField : 'id',
	        	            fitColumns : true,
	        	            striped : true,
	        	            rownumbers : false,
	        	            pagination : true,
	        	            singleSelect : true,
	        	            queryParams: { pageName: $("#pageName").val(),zddb:$("#zddb").val(),whereFrom:"tongan"+$("#cluesId").val()},    
	        	            selectOnCheck : false,
	        	            checkOnSelect : false,
	        	            border : false,
	        	            sortName : 'p.createTime', 
	        	            sortOrder : 'desc',
	        	            columns : [ [ {
	        	                title : 'id',
	        	                field : 'id',
	        	                checkbox : true
	        	            },{
	        	            	title:'收件时间',
	        	            	field:'receiveDate',
	        	            	width : '100'
	        	            },{
	        	            	 title:'到期类别',
	        	                 field:'expireTime1',
	        	                 formatter: function(value,row,index){  
	        	 					if(row.expireTime==""||row.expireTime==null){
	        	     				return '暂无';
	        	     			    }else{
	        	     			    	return row.expireTime.split("_")[0];
	        	     			    }
	        	 		     } 
	        	            },{
	        	                title:'到期时间',
	        	                field:'expireTime2',
	        	                formatter: function(value,row,index){  
	        	                	if(row.expireTime==""||row.expireTime==null){
	        	    				return '暂无';
	        	    			    }else{
	        	    			    	return row.expireTime.split("_")[1];
	        	    			    }
	        			     } 
	        	             },{
	        	            	title:'线索来源',
	        	            	field:'clues',
	        	            	width : '110'
	        	   
	        	            },{
	        	            	title:'被反映人姓名',
	        	            	field:'reflectedName'
	        	            },{
	        	            	title:'被反映人职级',
	        	            	field:'rank'
	        	            },{
	        	            	title:'问题属地',
	        	            	field:'problemLand'
	        	            },{
	        	            	title:'承办部门',
	        	            	field:'organId'
	        	            },{
	        	            	title:'专项行动',
	        	            	field:'special',
	        	            	width:150, 
	        	            	formatter:function(value,row,index){            		
	        	        			if(value!=""||value!=null){
	        			        		return value;
	        			        	}
	        			        	return value;
	        	            	},
	        	             	styler : function(value){
	        	            		if(value!=""||value!=null){
	        	            			return 'color:#FF8C00;';
	        	            		};
	        	            		return value;
	        	            	}
	        	            },{
	        	            	title:'是否是同案人员',
	        	            	field:'whereFrom',
	        	            	width : '100',
	        	            	formatter: function(value,row,index){  
	        	            		if(value.indexOf("tongan")>=0&&value.indexOf("lord")>=0){
	        	        		    var style="color:red;font-size:14px;";
	        	        		            return '<span style='+style+'  title="主要人员 点击查看详情">██</span>';
	        	            		}
	        	        		    if(value.indexOf("tongan")>=0&&value.indexOf("lord")<0){
	        	        		    	style="color:blue;font-size:14px;"
	        	        		    		return '<span style='+style+'  title="从属人员 点击查看详情">██</span>';
	        	        		    }
	        	        		    return '<span  title="无">██</span>';
	        	        			
	        				}
	        	            },{
	        	            	title:'是否有重复案件',
	        	            	field:'duplicate',
	        	            	width:150, 
	        	            	formatter:function(value,row,index){            		
	        	        			if(value=="1"){
	        			        		return "有重复案件";
	        			        	}else{
	        			        		return "无";
	        			        	}
	        			        	return value;
	        	            	},
	        	             	styler : function(value){
	        	            		if(value == "1"){
	        	            			return 'color:#FF8C00;';
	        	            		}else{
	        	            			return 'color:green;';
	        	            		}
	        	            		return value;
	        	            	}
	        	            },{
	        	            	title:'办理进度',
	        	            	field:'progress',
	        	            	width : '80',
	        	            	formatter: function(value,row,index){  
	        	            		    if(row.finalState=="31"){
	        	        		    	return '<span style="color:red" onclick="back_ch_detail(\''+row.reflectedName+'\',\''+row.id+'\');">撤回<span>';
	        	        		        }else if(row.finalState=="29"){
	        	        		    	return '<span style="color:red" onclick="back_th_detail(\''+row.reflectedName+'\',\''+row.id+'\');">退回</span>';  
	        	        		    	
	        	        	            } 
	        	            		    var str="";
	        	            		    if(row.finalState=="3"||row.finalState=="3.6"){
	        	            		    	if(row.organId!="干部监督室"){
	        	            		    		str= '监督室';
	        	            		    		if(row.state.indexOf("LC")>=0){
	        	            		    			str+="(留存)"
	        	            		    		}
	        	            		    	}else{
	        	            		    		str= '干部监督室新增';
	        	            		    	}
	        	            				
	        	            			} if(row.finalState=="-1"&&row.state.indexOf("TJ")>=0){
	        	            		    	str= '结案(提交)';
	        	            		    } if(row.finalState=="-1"&&row.state.indexOf("LC")>=0){
	        	            		    	str= '结案(留存)';
	        	            		    } if(row.finalState=="0"&&row.state.indexOf("TJ")>=0){
	        	            		    	str= '新增(提交)';  
	        	            		    	
	        	            	        } if(row.finalState=="0"&&row.state.indexOf("LC")>=0){
	        	            		    	str= '新增(留存)';  
	        	            		    	
	        	            	        } if(row.finalState=="4"&&row.state.indexOf("TJ")>=0){
	        	            	        	str= '初核(提交)';
	        	            			} if(row.finalState=="9"){
	        	            				str='审查室受理'
	        	            			}
	        	            		    return '<span style="color:red" onclick="progress('+index+');">'+str+'</span>';
	        					} 
	        	            },{//（新添加）
	        	            	title:"操作",
	        	            	field:"work",
	        	            	formatter:function(value,row,index){   
	        	            		if($("#pageName").val()=="having"||$("#pageName").val()=="newClues"||$("#pageName").val()=="initial"||$("#pageName").val()=="returnList"){
	        	            			return '<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+'\');">办理</button>';
	        	            		}else{
	        	            			return '<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+'\');">详情</button>';
	        	            		}
	        			        	
	        	            	}
	        	            },{//（新添加）
	        	            	title:"操作",
	        	            	field:"3",
	        	            	formatter:function(value,row,index){   
	        	            		
	        	            			return '<button style="color:white;" onclick="refbang(\''+row.id+'\',\''+row.whereFrom+'\');">解绑</button>';
	        			        	
	        	            	}
	        	            }] ],
	        	            loadFilter: function(data){
	        	            	sqlStore =data.sqlStore;
	        	            	if (data.rows) {
	        		            	for (var i = 0; i < data.rows.length; i++) {
	        			            	if(data.rows[i].reflectedPerson == null) {
	        				            	data.rows[i].reflectedName = '';
	        				            	data.rows[i].rank = "";
	        			            	} else {
	        				            	data.rows[i].reflectedName = data.rows[i].reflectedPerson.reflectedName;
	        				            	data.rows[i].rank = data.rows[i].reflectedPerson.rank;
	        			            	}
	        		            	}
	        		            	return data;
	        	            	}
	        	            	$('#searchDialog').dialog('close');
	        	            	$('#problemCluesClueOnExclDialog').dialog('close');
	        					if (data.obj){
	        						return data.obj;
	        					} else {
	        						return data;
	        					}
	        					
	        				}
	        	        });  
	        	      //领域专项行动
	        	        $('#special').combobox({
	        	        	onSelect:function(param){
	        	        		special =$("#special").combobox('getValue');
	        	        		$('#dataGrid').datagrid('load', {
	        	        			pageName : $("#pageName").val(),
	        	        			zddb :$("#zddb").val(),
	        	        			special :special
	        	        		});
	        	        	}
	        	        	
	        	        });    
	        	    });

		     });
	        	 
	        		 //查询
	        	 function searchFun2() {
	        	    parent.$.modalDialog({
	        	        title : '查询',
	        	        width : 1180,
	        	        height : 570,
	        	        href : path+'/probleClues/searchInfo?special='+special+"&pageName="+$("#pageName").val()+"&zddb="+$("#zddb").val(),
	        	        buttons : [ {
	        	            text : '查询',
	        	            iconCls:'icon-search',
	        	            handler : function() {
	        	                parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
	        	                var f = parent.$.modalDialog.handler.find('#searForm');
	        	                f.submit();
	        	            }
	        	        },{
	        				text:'取消',
	        				iconCls:'icon-cancel',
	        				handler:function(){
	        					parent.$.modalDialog.handler.dialog('close');
	        				}
	        			} ]
	        	    });
	        	}

	        	//添加
	        	 function saveFun(param) {	
	        	 	parent.addTab("添加新线索",path+"/probleClues/info?flag="+param);	
	        	 }

	        	 //添加
	        	 function saveFunUnit(param) {	
	        	 	if(param){
	        	     var row = $('#dataGrid').datagrid('getSelected');
	        	     if(row != null){
	        	     	
	        	     	parent.addTab("编辑线索",path+"/probleClues/info?id="+row.id);
	        	  	   
	        	     }else{
	        	     	$.messager.show({
	        	 			title:'消息提示',
	        	 			msg:'请选择一条人员信息',
	        	 			timeout:3000,
	        	 			showType:'slide'
	        	 		});
	        	     }
	        	 		
	        	 	}else{
	        	 		parent.addTab("添加新线索",path+"/probleClues/info");	
	        	 	}
	        	 }

	        	//删除   
	        	function deleteFun(){
	        		var rows = $('#dataGrid').datagrid('getChecked');
	        		if(rows.length>0){
	        			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r){
	        				if(r){
	        					var ids = new Array();
	        					for(var i=0;i<rows.length;i++){
	        						ids[ids.length] = rows[i].id;
	        						
	        					}
	        					$.ajax({
	        						url:path+'/ags/del',
	        						data:{ids:ids.join()},
	        						dataType:'json',
	        						async : true,
	        						type:'post',
	        						success:function(result){
	        							if(result.success){
	        								$('#dataGrid').datagrid('load');
	        							}
	        							$.messager.show({
	        								title:'消息提示',
	        								msg:result.msg,
	        								timeout:3000,
	        								showType:'slide'
	        							});
	        						}
	        					});
	        				}
	        				
	        			});
	        			
	        		}else{
	        			$.messager.show({
	        				title:'消息提示',
	        				msg:'请勾选要删除的案件线索信息',
	        				timeout:3000,
	        				showType:'slide'
	        			});
	        		}
	        	}

	        	//查询重复件
	        	function deptCheck(){
	        	   	var duplicate = "1";
	        	   	$('#dataGrid').datagrid('load', {
	        	   	duplicate : duplicate,
	        	   	special:special
	        	   	});
	        	}

	        	//excel导入弹出框
	        	function importFun(value){
	        		if(value=='-1'){
	        			impValue = '-1';
	        			$("#fileData").val("");
	        			$('#fileData').click();
	        		}else{
	        			$("#fileData").val("");
	        			$('#fileData').click();
	        			impValue = '';
	        		}
	        	}

	        	//excel导入
	        	function importExcel(value){
	        		$.messager.alert('提示','正在导入中,请稍后...', 'info');   
	        		var formData = new FormData();
	        		
	        		if($("#fileData")[0] != undefined){
	        			formData.append("fileData",$("#fileData")[0].files[0]);
	        		}
	        		var url;
	        		var rsj ='0';
	        		if(impValue =='-1'){
	        			//alert("走压缩包");
	        			url=path+'/probleClues/importExcel?value='+impValue;
	        			 var file = $("#fileData").val();
	        	         var len = file.length;
	        	         var ext = file.substring(len-3,len).toLowerCase();
	        	         if(ext!='zip'){
	        	             alert("文件类型不匹配，请使用正确的压缩包");
	        	             rsj='1';
	        	         }
	        		}else{
	        			url=path+'/probleClues/importExcel';
	        		}
	        		if(rsj!='1'){
	        			$.ajax({
	        				url:url,
	        				data:formData,
	        				dataType:'json',
	        				type:'post',
	        				processData : false, 
	        				contentType : false,
	        				success:function(result){
	        					 $(".messager-body").window('close');
	        					if(result.success){
	        						refreshTable();
	        					}
	        					$.messager.show({
	        						title:'消息提示',
	        						msg:result.msg,
	        						timeout:3000,
	        						showType:'slide'
	        					});
	        				}
	        			});
	        		}
	        	}

	        	
	        	//excel导出
	        	 function outExcelFun(value){                                                                
	        	      var rows = $('#dataGrid').datagrid('getChecked');
	        	      var ids = new Array();
	        	      var idStr;
	        		  if(value =='-1'){
	        		  	ids[0] = '-1';
	        		  	idStr = ids.join();
	        		  }else{
	        		  	if(rows.length>0){
	        		  		for(var i=0;i<rows.length;i++){
	        		  			ids[i] =rows[i].id;
	        		  		};
	        		  		idStr=ids.join();
	        		  	}
	        		  }
	        		  var finalURL = encodeURI(sqlStore);
	        		  parent.$.modalDialog({
	        	          title : '导出',
	        	          width : 880,
	        	          height : 370,
	        	          href : path+'/ags/OnExclEdit?ids='+idStr+'&special='+special+'&sqlStore='+finalURL, 
	        	    	buttons:[{
	        				text:'全选',
	        				iconCls:'icon-ok',
	        				id:'qxll',
	        				handler:function(){
	        					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
	        	                parent.$.modalDialog.handler.find("[name='fxk']").prop("checked","checked");
	        				}
	        			},{
	        				text:'全不选',
	        				iconCls:'icon-ok',
	        				id:'qxll',
	        				handler:function(){
	        					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
	        	                parent.$.modalDialog.handler.find("[name='fxk']").removeAttr("checked");
	        				}
	        			},{
	        				text:'导出',
	        				iconCls:'icon-save',
	        				handler:function(){
	        					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
	        					var str =window.parent.onExcl();
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



	        	 //下载导入模板
	        	function exportemp(){
	        			var url = path+'/statistics/OnImportWord';
	        			 $('#reprotFrom').form('submit',{
	        				 url:url,
	        				 onSubmit:function(){} 
	        			 });
	        	 }


	        	//监督室处置建议
	        	function JAdvise(param) {
	        	   var row = $('#dataGrid').datagrid('getSelected');
	        	     if(row != null){
	        	         parent.$.modalDialog({
	        	             title : '处置建议',
	        	             width : 880,
	        	             height : 370,
	        	             href : path+'/probleClues/'+param+'?id='+row.id,
	        	             buttons : [ {
	        	                 text : '确定',
	        	                 iconCls:'icon-ok',
	        	                 handler : function() {
	        	                     parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
	        	                     var f = parent.$.modalDialog.handler.find('#fbForm');
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
	        	     }else{
	        	     	$.messager.show({
	        	 			title:'消息提示',
	        	 			msg:'请选择一条人员信息',
	        	 			timeout:3000,
	        	 			showType:'slide'
	        	 		});
	        	     }
	        	}


	        	function change(pageName){
	        		$("#pageName").val(pageName);
	        		 /*refreshTable();*/
	        		$('#dataGrid').datagrid('reload',{pageName: $("#pageName").val(),zddb:$("#zddb").val() }); 
	        	}


	        	window.top["reload_Abnormal_Monitor"]=function(){
	        		$('#dataGrid').datagrid( "load");
	        		
	        	};
	        	function get(id,fromId){
	        		$.messager.confirm('提醒', '确认签收？', function(r){
	        			if(r){
	        				console.log(fromId);
	        				$.ajax({
	        					url:path+"/probleClues/get",
	        					data:{id:id,fromId:fromId},
	        					dataType:'json',
	        					type:'post',
	        					success:function(result){
	        						if(result.success){
	        							refreshTable();
	        							$.messager.show({
	        					 			title:'消息提示',
	        					 			msg:'线索已经签收',
	        					 			timeout:3000,
	        					 			showType:'slide'
	        					 		});
	        						}
	        					}
	        				})
	        			}
	        		});
	        		
	        	}
	        	function checkDetail(id,reflectedName){
	        		 parent.addTab("详情："+reflectedName,path+"/probleClues/action?id="+id);
	        	}
	        	//（新添加）
	        	function returnClues(id,reflectedName){
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
	        	function back_th_detail(reflectedName,id){
	        		parent.$.modalDialog({
	        	        title : '案件状态',
	        	        width : 880,
	        	        height : 370,
	        	        href : path+'/probleClues/call_back_detail?cluesId='+id+"&type=TH"
	        	        
	        	    });
	        		
	        		
	        	}
	        	//开始办理
	        	function action(reflectedName,rowId,url){
	        		var poi = $("#poi").val()
	        		var pageName=$("#pageName").val();
	        		if($("#pageName").val()=="having"||$("#pageName").val()=="newClues"||$("#pageName").val()=="initial"||$("#pageName").val()=="returnList"){
	        			parent.addTab("案件办理("+reflectedName+")",path+"/probleClues/action?id="+rowId+"&pageName="+pageName);
	        		}else{
	        			parent.addTab("案件信息("+reflectedName+")",path+"/probleClues/action?id="+rowId+"&pageName="+pageName);
	        			
	        		}
	        		
	        	}

	       
		     
		     function refbang(id,whereFrom){
		    	 $.ajax({
 					url:path+"/ta/refbang",
 					data:{cluesId:id,whereFrom:whereFrom},
 					dataType:'json',
 					type:'post',
 					success:function(result){
 						if(result.success){
 							 $.messager.alert('我的消息',result.msg,'info',function(){
        	        	    	 //刷新树
 								 refreshTable();
        	        	    });
 						}else{
 							 $.messager.alert('我的消息',result.msg,'info');
 						}
 					}
 				})
		     }
		        //刷新当前页
	        	function refreshTable(){
	        		$('#dataGrid').datagrid('reload');
	        	}

	</script>
</body>
</html>