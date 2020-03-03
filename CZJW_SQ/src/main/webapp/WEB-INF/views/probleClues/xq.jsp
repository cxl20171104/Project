<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%-- <%@ include file="probleClues/probleClues.jsp" %> --%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
 <body class="easyui-layout">
    <%@ include file="/commons/basePage.jsp" %>
    <div data-options="region:'west',split:false,border:false" style="width:8%;border-right:1px solid #d3d3d3">
        <input id="oId" type="hidden" value="33"/>
    	<ul id="dictTree"></ul>
    </div>
    <div data-options="region:'center',border:false">
         <div id="tb" style="height:auto;width:80%;">
              <shiro:hasPermission name="/probleClues/check">
       		  <a  class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="cxxc();" >查询</a>
	      	  </shiro:hasPermission>
	      	   <shiro:hasPermission name="/probleClues/exprot">
			  <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="outExcelFun();" >导出</a>
			  <a  class="easyui-linkbutton" data-options="iconCls:'icon-tip'" onclick="holeData();" >全部数据</a>
			  <input id="total" value=""  type="hidden"/>
        	 </shiro:hasPermission>
        	 <span>查询本页数据使用的数据源---</span><span id="whichDateSource"></span>
         </div>
         <table id="dataGrid" style="width:400px;height:250px"></table>
    </div>
   
  </body>
	<script type="text/javascript">
	var path='${path }';
  </script>
  
  <script type="text/javascript">
        var dataGrid;
	    var organizationTree;
	    var special="";
	    var path =path;
	    var sqlStore="";
   $(function(){
	$('#dictTree').tree({    
	    url: '${path}/organization/findOrganizationByPid', 
	    queryParams:{pid:32},
	    onBeforeExpand:function(node){
	    	
	    },
	    onClick:function(node){
	    	//加载数据
	    	$("#oId").val(node.id);
	    	dataGrid.datagrid('load',{
	    		oId:node.id,
	    		pageName:"xq"
	    	});
	    	//获取 数据源信息
	    	
	    	
	    },
	    loadFilter: function(data){    
	        if (data.obj){    
	            return data.obj;    
	        } else {    
	            return data;    
	        }    
	    },onLoadSuccess : function(node,data) {
			 var node = $('#dictTree').tree('find', '33');
			 $("#dictTree").tree('select',node.target);
			
		}    
	}); 





 
	 dataGrid =	$('#dataGrid').datagrid({
    	 toolbar:'#tb',
    	  url :path + '/xq/getListData',
        fit : true,
        idField : 'id',
        fitColumns : true,
        striped : true,
        rownumbers : false,
        pagination : true,
        singleSelect : true,
        queryParams: { pageName: $("#pageName").val(),
        	zddb:$("#zddb").val(),
        	oId:'33',
        	fromId:$("#fromId").val(),
        	finalState:$("#finalState").val(),
        	control:$("#control").val()},    
        selectOnCheck : false,
        checkOnSelect : false,
        border : false,
        sortName : 'p.cluesNum,p.createTime', 
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
            width : '60',
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
        	width : '120',
        	formatter: function(value,row,index){  
				if(row.isImport=="1"){
					if(value.length>=3){
						return '<span title='+value+'>'+value.substring(0,3)+'(*)'+'</span>';						
					}else{
						return '<span title='+value+'>'+value+'(*)'+'</span>';
					}
    				
    			}else{
    				if(value.length>3){
    					return '<span title='+value+'>'+value.substring(0,3)+'</span>';
    				}else{
    					return '<span title='+value+'>'+value+'</span>';
    				}
    				
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
        	width : '80',
        	field:'problemLand'
        },{
        	title:'承办部门',
        	width : '80',
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
    		            return '<span style='+style+' onclick="tonganListLord('+index+')" title="主要人员 点击查看详情">██</span>';
        		}
    		    if(value.indexOf("tongan")>=0&&value.indexOf("lord")<0){
    		    	style="color:blue;font-size:14px;"
    		    		return '<span style='+style+' onclick="tonganList('+index+')" title="从属人员 点击查看详情">██</span>';
    		    }
    		    return '<span  title="无">██</span>';
    			
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
    				 return '<span style='+style+'  onclick="repeatAAA('+index+');" title="有  点击查看">██</span>';
	        	}else if(value=="1"&&row.whereFrom=="repeart"){
	        		 style="color:yellow;font-size:14px;";
	        		 return '<span style='+style+'   title="重复件...">██</span>';
	        	}else{
	        		return '<span title="无">██</span>';
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
        			} if(row.state.indexOf('JDSJBTJ')>=0){
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
        			if(row.finalState=="23"){
        				str= '县区受理'
        			}
        		    return '<span style="color:red" title='+str+' onclick="progress('+index+');">'+str+'</span>';
			} 
        },{//（新添加）
        	title:"操作",
        	field:"work",
        	width : '80',
        	formatter:function(value,row,index){   
        		if($("#pageName").val()=="having"||$("#pageName").val()=="newClues"||$("#pageName").val()=="initial"||$("#pageName").val()=="returnList"){
        			var str='<button style="color:white;" onclick="action(\''+row.reflectedName+'\',\''+row.id+'\');">办理</button>';
        			if(row.fromId!='1'&&row.fromId!='-1'&&row.fromId!='0'&&row.fromId!='xfs'&&row.fromId!='gbjds'&&row.fromId!='ta'){
        				str+='&nbsp;<button style="color:white;" onclick="returnClues(\''+row.id+'\',\''+row.reflectedName+'\');">退回</button>';
        			}
        			return str;
        		}else{
        			return '<button style="color:white;" onclick="fuction(\''+row.reflectedName+'\',\''+row.id+"_"+$("#oId").val()+'\');">县区详情</button>';
        		}
        	}
        }] ],
        loadFilter: function(data){
        	console.log(data);
        	$("#whichDateSource").text(data.whichDataSource);
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
   	  if(idStr==null){
   	     
   	     return       $.messager.alert('我的消息',"请选择一条数据！",'info');
   	  
   	  }
	  var finalURL = encodeURI(sqlStore);
	  parent.$.modalDialog({
          title : '导出',
          width : 880,
          height : 370,
          href : path+'/ags/OnExclEdit?ids='+idStr+'_'+$("#oId").val()+'&special='+special+'&sqlStore='+finalURL, 
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
	 //查询
function cxxc() {
   parent.$.modalDialog({
       title : '查询',
       width : 1180,
       height : 570,
       href : path+'/probleClues/searchInfo?special='+$("#special").val()+"&pageName="+$("#pageName").val()+"&zddb="+$("#zddb").val(),
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
function fuction(reflectedName,rowId){
        console.log("rowId:"+rowId);
		var tabsName2="县区信息["+reflectedName+"]";
		parent.addTab(name_xx(tabsName2),path+"/probleClues/fuction?id="+rowId+"&pageName="+$("#pageName").val());
	
}
function stringToDate(fDate){  
    var fullDate = fDate.split("-");  
     
    return new Date(fullDate[0], fullDate[1]-1, fullDate[2], 0, 0, 0);  
  }
//重复件操作列表
function repeatAAA(index){
	  var rows = $('#dataGrid').datagrid('getRows');
	  var row = rows[index];
	  var id = row.id;
	  var ref= row.reflectedName;
	parent.addTab("重复件处理["+ref+"]",path+"/repeat/repeatList?id="+id);	
}	
function progress(index){
	  var rows = $('#dataGrid').datagrid('getRows');
	  var row = rows[index];
	  var ref= row.reflectedName;
	  parent.addTab("案件进度["+ref+"]",path+"/progress/getProgressAll?id="+row.id);	
	
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
	parent.addTab("同案人员["+ref+"]",path+"/ta/getTonan?cluseId="+id);	
}
function holeData(){
	 dataGrid.datagrid({pageSize:$("#total").val(),pageList:[$("#total").val()]});	
}
</script>
</html>