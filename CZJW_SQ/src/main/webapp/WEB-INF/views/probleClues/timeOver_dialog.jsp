<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<title>到期案件</title>
</head>
    <input id="which" value="${which}"/>
    <div data-options="region:'center',border:false" id="div_table">
        <table id="dataGrid" data-options="fit:true,border:false"></table>
	</div> 
	
	<script type="text/javascript">
	$(function() {
    	//将pageName赋给全局变量 version2Name  以后  在任何界面可以使用 parent.version2Name获取
	    parent.version2Name= $("#pageName").val();
	    var datag = "#dataGrid";
            dataGrid =$('#dataGrid').datagrid({
        	url :'${path}/timeOver/dq_list',
            fit : true,
            idField : 'id',
            fitColumns : true,
            striped : true,
            rownumbers : false,
            pagination : true,
            singleSelect : true,
            queryParams: { which: $("#which").val()},    
            selectOnCheck : false,
            checkOnSelect : false,
            border : false,
            sortName : 'p.createTime', 
            sortOrder : 'desc',
            columns : [ [ {
                title : 'id',
                field : 'id',
                hidden :true 
            },{
                title:'到期时间',
                field:'expireTime',
                width : '80',
                formatter: function(value,row,index){  
						if(value==""||value==null){
							return '<span  style="color:black">██</span>';
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
    });
</script> 
