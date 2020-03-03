<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
var dataGrid;
var total=0;
$(function() {
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
          queryParams: { clues: $("#clues").val(),
        	  zddb:$("#zddb").val(),
        	  problemLand:$("#problemLand").val(),
        	  rank:$("#rank").val(),
        	  startDate:$("#start_time").val(),
        	  endDate:$("#end_time").val(),
        	  pageName:"tj_detail",
        	  clues:$("#clues").val(),
        	  special:$("#special").val(),
        	  organId:$("#organId").val(),
        	  fields:$("#fields").val()},    
          selectOnCheck : false,
          checkOnSelect : false,
          border : false,
          sortName : 'p.createTime', 
          sortOrder : 'desc',
          pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
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
              width : '200',
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
          	width : '200'
 
          },{
          	title:'被反映人姓名',
          	field:'reflectedName',
          	width : '200',
          	formatter: function(value,row,index){  
					if(row.isImport=="1"){
      				return '<span title='+value+'>'+value+'(*)'+'</span>';
      			}else{
      				return value;
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
          	width : '100'
          },{
          	title:'问题属地',
          	field:'problemLand',
          	width : '200'
          },{
          	title:'承办部门',
          	field:'organId',
          	width : '200'
          	
          },{
          	title:'专项行动',
          	field:'special',
          	width:200, 
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
    		            return '<span style='+style+' onclick="tonganListLord('+index+')" title="主要人员 点击查看详情">██</span>';
        		}
    		    if(value.indexOf("tongan")>=0&&value.indexOf("lord")<0){
    		    	style="color:blue;font-size:14px;"
    		    		return '<span style='+style+' onclick="tonganList('+index+')" title="从属人员 点击查看详情">██</span>';
    		    }
    		    return '<span  title="无">██</span>';
    			
		}
        },{
          	title:'重复案件',
          	field:'duplicate',
          	width:'100', 
          	formatter:function(value,row,index){            		
      			if(value=="1"){
		        		return "有";
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
          	width : '100',
          	formatter: function(value,row,index){  
          		    console.log(row.fromId);
          		    if(row.finalState=="-1"){
          		    	return '结案';
          		    }else if(row.finalState=="0"){
          		    	return '新增';  
          		    	
          	        }else if(row.finalState=="4"){
          	        	return '初核';
          			}   
				} 
          },{
          	title:'线索状态',
          	field:'finalState',
          	width : '100',
          	formatter: function(value,row,index){  
          		    if(row.finalState=="31"){
          		    	return '<span style="color:red" onclick="back_ch_detail(\''+row.reflectedName+'\',\''+row.id+'\');">撤回<span>';
          		    }else if(row.finalState=="29"){
          		    	return '<span style="color:red" onclick="back_th_detail(\''+row.reflectedName+'\',\''+row.id+'\');">退回</span>';  
          		    	
          	        }else if(row.state.indexOf("TJ")!=-1){
          	        	return '<span style="color:green">已提交<span>';
          				
          			}else if(row.state.indexOf("LC")!=-1){
          				return '<span style="color:blue">暂存中<span>';          			
          			}else{
          				return '<span style="color:green">正常案件</span>';
          			}    
				} 
          },{//（新添加）
          	title:"操作",
          	field:"work",
          	width : '100',
          	formatter:function(value,row,index){   
          		if($("#pageName").val()=="having"||$("#pageName").val()=="newClues"||$("#pageName").val()=="initial"||$("#pageName").val()=="returnList"){
          			return '<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+'\');">办理</button>';
          		}else{
          			return '<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+'\');">详情</button>';
          		}
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
		            	} else {
			            	data.rows[i].reflectedName = data.rows[i].reflectedPerson.reflectedName;
			            	data.rows[i].rank = data.rows[i].reflectedPerson.rank;
		            	}
	            	}
	            	total=data.total;
		            $("#total").val(total);
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
});

function action(reflectedName,rowId,url){
	var poi = $("#poi").val()
	var pageName=$("#pageName").val();
	if($("#pageName").val()=="having"||$("#pageName").val()=="newClues"||$("#pageName").val()=="initial"||$("#pageName").val()=="returnList"){
		parent.addTab("案件办理("+reflectedName+")","${path}/probleClues/action?id="+rowId+"&pageName="+pageName);
	}else{
		parent.addTab("案件信息("+reflectedName+")","${path}/probleClues/action?id="+rowId+"&pageName="+pageName);
		
	}
	
}
function holeData(){
	 dataGrid.datagrid({pageSize:$("#total").val(),pageList:[$("#total").val()]});	
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
         href : '${path}/ags/OnExclEdit?ids='+idStr+'&special='+special+'&sqlStore='+finalURL, 
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
			}
		}]
     });

}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
     <input type="hidden" id="rank" value="${tjModel.rank_option}"/>
     <input type="hidden" id="problemLand" value="${tjModel.problemLand_option}"/>
     <input type="hidden" id="clues" value="${tjModel.clues_option}"/>
     <input type="hidden" id="start_time" value="${tjModel.start_time}"/>
     <input type="hidden" id="end_time" value="${tjModel.end_time}"/>
     <input type="hidden" id="special" value="${tjModel.special_option}"/>
     <input type="hidden" id="organId" value="${tjModel.organId_option}"/>
     <input type="hidden" id="fields" value="${tjModel.field_option}"/>
     <table type="hidden" id="dataGrid"data-options="fit:true,border:false" style="height:600px;"></table>
     
             <div id="tb" style="height:auto;width:100%;">
             <shiro:hasPermission name="/probleClues/exprot">
			 <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
        	 </shiro:hasPermission>
             <shiro:hasPermission name="/probleClues/exprot">
			 <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="holeData();" >全部数据</a>
			 <input id="total" value=""  type="hidden"/>
        	 </shiro:hasPermission>
        	 </div>
</body>
</html>