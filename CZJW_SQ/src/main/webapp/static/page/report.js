//{案管室和监督室案件报备}
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
            	reportOrganId:$("#organId").val(),
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
    				return '暂无';
    			    }else{
    			    	return value;
    			    }
		     } 
             	
             	
             },{
            	title:'线索来源',
            	field:'clues',
            	width : '110'
   
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
             
            }] ],
            onSelect:function(rowIndex, rowData){
            	console.log(rowData);
            	console.log(rowIndex);
            	action( rowData.reflectedName,rowData.id );
					
            },
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
        			special :special
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
				var str =window.parent.onExcl();
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

//开始办理
function action(reflectedName,rowId,url){
	var pageName=$("#pageName").val();
	if(pageName=='working'||pageName=='overing'||pageName=='library'){
		parent.addTab("案件信息("+reflectedName+")",path+"/probleClues/action?id="+rowId+"&pageName="+pageName);
	}else{
		parent.addTab("案件办理("+reflectedName+")",path+"/probleClues/action?id="+rowId+"&pageName="+pageName);
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
