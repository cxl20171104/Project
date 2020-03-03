
    var dataGrid;
    var organizationTree;
    var special="";
    var path =path;
    var sqlStore="";
$(function() {
    	//将pageName赋给全局变量 version2Name  以后  在任何界面可以使用 parent.version2Name获取
	    parent.version2Name= $("#pageName").val();
	    var datag = "#dataGrid";
        dataGrid =	$('#dataGrid').datagrid({
        	 toolbar:'#tb',
        	  url :path + '/page/getListData',
            fit : true,
            idField : 'id',
            fitColumns : true,
            striped : true,
            rownumbers : false,
            pagination : true,
            singleSelect : true,
            queryParams: { pageName: $("#pageName").val(),
            	zddb:$("#zddb").val(),
            	organId:$("#organId").val(),
            	fromId:$("#fromId").val(),
            	finalState:$("#finalState").val(),
            	control:$("#control").val()},    
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
                title:'到期时间',
                field:'expireTime',
                formatter: function(value,row,index){  
						if(value==""||value==null){
							return '<span  style="color:black" title="无" >██</span>';
        			    }else{
        			    	var str=value.split("_");
        			    	var date=stringToDate(str[1]);
        			    	if(date.getTime()-new Date().getTime()<5*24*60*60){
        			    		return '<span title='+value+' style="color:red">██</span>';
        			    	}else{
        			    		return '<span title='+value+' style="color:green">██</span>';
        			    	}
        			    	
        			    }
			 } 
             	
             	
             },{
            	title:'线索来源',
            	field:'clues',
            	width : '110'
   
            },{
            	title:'被反映人姓名',
            	field:'reflectedName',
            	width : '80',
            	formatter: function(value,row,index){  
					if(row.isImport=="1"){
        				return '<span title='+value+'>'+value+'(*)'+'</span>';
        			}else{
        				return '<span title='+value+'>'+value+'</span>';
        			} 
            	} ,
            	styler : function(value,row){
            		if(row.isImport=="1"){
            			return 'color:red;';
            		}
            		return value;
            	}
            },{
            	title:'被反映人职级',
            	field:'rank',
            	width : '80'
            },{
            	title:'被反映人职务',
            	field:'duty',
            	width : '100',
            	formatter:function(value,row,index){
            		    if(value){
            		    	return '<span title='+value+'>'+value+'</span>';
            		    }
                		
            	}
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
            		if(value!=""&&value!=null){
            		if(value.indexOf("tongan")>=0&&value.indexOf("lord")>=0){
        		    var style="color:red;font-size:14px;";
        		            return '<span style='+style+' onclick="tonganListLord('+index+')" title="主要人员 点击查看详情">是</span>';
            		}
        		    if(value.indexOf("tongan")>=0&&value.indexOf("lord")<0){
        		    	style="color:blue;font-size:14px;"
        		    		return '<span style='+style+' onclick="tonganList('+index+')" title="从属人员 点击查看详情">是</span>';
        		    }
            		}
        		    return '<span  title="无">否</span>';
        			
			}
            },{
            	title:'是否有重复案件',
            	field:'duplicate',
            	width:100, 
            	formatter:function(value,row,index){ 
            		var style="";
            		var fun="";
        			if(value=="1"&&row.whereFrom!="repeart"){
        				 style="color:red;font-size:14px;";
        				 return '<span style='+style+'  onclick="repeatAAA('+index+');" title="有  点击查看">是</span>';
		        	}else if(value=="1"&&row.whereFrom=="repeart"){
		        		 style="color:yellow;font-size:14px;";
		        		 return '<span style='+style+'   title="重复件...">是</span>';
		        	}else{
		        		return '<span title="无">否</span>';
		        	}
        			
            	}
             
            },{
            	title:'办理进度',
            	field:'progress',
            	width : '100',
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
            				str= '审查室受理'
            			} if(row.finalState=="23"){
            				str= '县区受理'
            			}if(row.state.indexOf('JDSJBTJ')>=0){
            				str= '监督室(下放县区)';
            			} if(row.state.indexOf('JDSPZLC')>=0){
            				str= '派驻纪检组(留存)';
            			} if(row.state.indexOf('SCLC')>=0){
            				str= '审查室(留存)';
            			} if(row.state.indexOf('LATJ')>=0){
            				str= '交审理室';
            			} if(row.state.indexOf('JDSPZTJ')>=0){
            				str= '派驻纪检组(了结)';
            			}
            		    return '<span style="color:red" title='+str+' onclick="progress('+index+');">'+str+'</span>';
				} 
            },{//（新添加）
            	title:"操作",
            	field:"work",
            	formatter:function(value,row,index){   
            		var str="";
            		if($("#pageName").val()=="having"||$("#pageName").val()=="newClues"||$("#pageName").val()=="initial"||$("#pageName").val()=="returnList"){
            			    str='<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+'\');">办理</button>';
            			 if(row.fromId!='1'&&row.fromId!='-1'&&row.fromId!='0'&&row.fromId!='xfs'&&row.fromId!='gbjds'&&row.fromId!='ta'){
            				str+='&nbsp;<button style="color:white;" onclick="returnClues(\''+row.id+'\',\''+row.reflectedName+'\');">退回</button>';
            			 }
            		}else{
            			
            			str+= '<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+'\');">详情</button>';
            		}
            		console.log(row.reflectedName+"000000"+row.isXf);
            		if(row.isXf!=""&&row.isXf!=null){
            			//县区线索查看需要 在案件id后面加上 分到哪个县的id
            			str+= '<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+"_"+row.isXf+'\');">县区详情</button>';
            			str+= '<button style="color:white;" onclick="recallClues(\''+row.id+'\',\''+row.reflectedName+'\');">撤回</button>';
            		}
            		
            		return str;
            	}
            }] ],
            loadFilter: function(data){
            	console.log(data);
            	sqlStore =data.sqlStore;
            	if (data.rows) {
	            	for (var i = 0; i < data.rows.length; i++) {
		            	if(data.rows[i].reflectedPerson == null) {
			            	data.rows[i].reflectedName = '';
			            	data.rows[i].rank = "";
			            	data.rows[i].duty = "";
		            	} else {
			            	data.rows[i].reflectedName = data.rows[i].reflectedPerson.reflectedName;
			            	data.rows[i].rank = data.rows[i].reflectedPerson.rank;
			            	data.rows[i].duty = data.rows[i].reflectedPerson.duty;
		            	}
	            	}
	            	   
		            $("#total").val(data.total);
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
        			special :special,
        			organId :$("#organId").val()
        		});
        	}
        	
        });    
        
    });
     

function holeData(){
	 dataGrid.datagrid({pageSize:$("#total").val(),pageList:[$("#total").val()]});	
}
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
var rows="";
//删除   
function deleteFun(){
   rows = $('#dataGrid').datagrid('getChecked');
   if(rows.length>0){
   var ids = new Array();
	for(var i=0;i<rows.length;i++){
		ids[ids.length] = rows[i].id;
		
	}
   parent.$.modalDialog({
       title : '删除案件',
       width : 880,
       height : 370,
       href : path+'/probleClues/del_dialog?ids='+ids.join(),
       buttons : [ {
           text : '确定',
           iconCls:'icon-ok',
           handler : function() {
               parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
               var f = parent.$.modalDialog.handler.find('#del_form');
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
				//刷新列表
				 refreshTable();
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

//刷新当前页
function refreshTable(){
	$('#dataGrid').datagrid('reload');
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
				window.parent.onExcl();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				parent.$.modalDialog.handler.dialog('close');
				dataGrid.datagrid('uncheckAll');
			}
		}]
      });

}
function name_xx(name){
	var equalName=name;
	for(var i=1;i<20;i++){
		 if (parent.index_tabs.tabs('exists', equalName)) {
			 equalName=name+"("+i+")";
		 }else{
			 break;
		 }
	}
	 return equalName;
}
//开始办理
function action(reflectedName,rowId,url){
	var pageName=$("#pageName").val();
	if($("#pageName").val()=="having"||$("#pageName").val()=="newClues"||$("#pageName").val()=="initial"||$("#pageName").val()=="returnList"){
		var tabsName="案件办理["+reflectedName+"]";
		parent.addTab(name_xx(tabsName),path+"/probleClues/action?id="+rowId+"&pageName="+pageName);
	}else{
		var tabsName2="案件信息["+reflectedName+"]";
		parent.addTab(name_xx(tabsName2),path+"/probleClues/action?id="+rowId+"&pageName="+pageName);
		
	}
	
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
window.top["reload_taskTab"] = function () {
	$('#dataGrid').datagrid('reload');
};

function returnAJ(){
	$("#pageName").val("returnList");
	$('#dataGrid').datagrid('reload',{pageName: $("#pageName").val(),zddb:$("#zddb").val() }); 
}
function jdInitial(){
	$("#pageName").val("initial");
	$('#dataGrid').datagrid('reload',{pageName: $("#pageName").val(),zddb:$("#zddb").val() }); 
}
function jdTemporary(){
	$("#pageName").val("temporary");
	$('#dataGrid').datagrid('reload',{pageName: $("#pageName").val(),zddb:$("#zddb").val() }); 
}
function back_ch_detail(reflectedName,id){
	parent.$.modalDialog({
        title : '案件状态',
        width : 880,
        height : 370,
        href : path+'/probleClues/call_back_detail?cluesId='+id+"&type=CH"
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
//重复件操作列表
function repeatAAA(index){
	  var rows = $('#dataGrid').datagrid('getRows');
	  var row = rows[index];
	  var id = row.id;
	  var ref= row.reflectedName;
	parent.addTab("重复件处理["+ref+"]",path+"/repeat/repeatList?id="+id);	
}	

function tonganListLord(index){
	  var rows = $('#dataGrid').datagrid('getRows');
	  var row = rows[index];
	  var id = row.id;
	  var ref= row.reflectedName;
	parent.addTab("同案人员["+ref+"]",path+"/ta/getTonan?cluesId="+id);	
}
function tonganList(index){
	  var rows = $('#dataGrid').datagrid('getRows');
	  var row = rows[index];
	  var id = row.whereFrom.replace("tongan","");
	  var ref= row.reflectedName;
	parent.addTab("同案人员["+ref+"]",path+"/ta/getTonan?cluesId="+id);	
}
function stringToDate(fDate){  
    var fullDate = fDate.split("-");  
     
    return new Date(fullDate[0], fullDate[1]-1, fullDate[2], 0, 0, 0);  
  }


//（新添加）
function returnClues(id,reflectedName){
	
	
	$.messager.confirm('确认','您确认要退回吗？',function(r){    
    if (r){ 
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
	});
	
}

function progress(index){
	  var rows = $('#dataGrid').datagrid('getRows');
	  var row = rows[index];
	  var ref= row.reflectedName;
	  parent.addTab("案件进度["+ref+"]",path+"/progress/getProgressAll?id="+row.id);	
	
}

function recallClues(id,reflectedName){
	parent.$.modalDialog({
        title : '撤回案件',
        width : 880,
        height : 370,
        href : path+'/probleClues/call_back?id='+id+"&type=CH",
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