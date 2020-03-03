<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:10%;}
</style>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <select id="type"  style="width:300px;height:45px;" >
       <option value="problemLand">问题属地</option>
       <option value="clues">线索来源</option>
       <option value="rank">职级</option>
       <option value="special">专项行动</option>
       <option value="fields">领域</option>
    </select>
    <button onclick="dc();">导出</button>
    <button onclick="tj();">统计</button>
    
	<hr style="border:5px solid black;"/>
	<table class="cxl">
	 <tr>
	  <td>问题属地</td>
	  <td><select     id="problemLand_option"   style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${problemLand}">
										<option value="${act.value }">${act.name}</option>
								</c:forEach>
	  </select></td>
	   <td>线索来源</td>
	  <td><select     id="clues_option"   style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${clues}">
										<option value="${act.value }">${act.name}</option>
								</c:forEach>
	  </select></td>
	  <td>领域</td>
	  <td><select     id="field_option"   style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${fields}">
										<option value="${act.value }">${act.name}</option>
								</c:forEach>
	  </select></td>
	  <td>职级</td>
	  <td><select     id="rank_option"   style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${rank}">
										<option value="${act.value }">${act.name}</option>
								</c:forEach>
	  </select></td>
	 </tr>
	  <tr>
	  <td>专项行动</td>
	  <td><select     id="special_option"   style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${special}">
										<option value="${act.value }">${act.name}</option>
								</c:forEach>
	  </select></td>
	  <td>开始时间</td>
	  <td><input id="start_time" type="text" class="easyui-datebox" style="width:300px;height:45px;" ></input> </td>
	  <td>结束时间</td>
	  <td><input id="end_time" type="text" class="easyui-datebox" style="width:300px;height:45px;" ></input><td>   
	  </tr>
	</table>
    <table id="dataGrid"data-options="fit:true,border:false">
</table>
<script type="text/javascript">
var dataGrid;
$(function() {
    dataGrid = $('#dataGrid').datagrid({
        url : '${path }' + '/clues_tj/data',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        queryParams: { 
        	type: $("#type").val()
        	,options:$("#options").val()
        	,problemLand_option:$("#problemLand_option").val()
        	,field_option:$("#field_option").val()
        	,rank_option:$("#rank_option").val()
        	,start_time:$("#start_time").datebox('getValue')
        	,end_time:$("#end_time").datebox('getValue')
        	,organId_option:$("#organId_option").val()
        	,special_option:$("#special_option").val()},    
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
        frozenColumns : [ [  {
            width : '200',
            title : '类别',
            field : 'type',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.type){
            		return "";
            	}else{
            		return row.type;
            	}
            }
        } , {
            width : '100',
            title : '开始时间',
            field : 'start_time',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.start_time){
            		row.start_time="";
            		return "";
            	}else{
            		return row.start_time;
            	}
            }
        }, {
            width : '100',
            title : '结束时间',
            field : 'end_time',
            formatter : function(value, row, index) {
            	if(!row.end_time){
            		row.end_time="";
            		return "";
            	}else{
            		return row.end_time;
            	}
            }
        } , {
            width : '200',
            title : '线索来源',
            field : 'clues_option',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.clues_option){
            		row.clues_option="";
            		return "";
            	}else{
            		return row.clues_option;
            	}
            }
        }, {
            width : '200',
            title : '领域',
            field : 'field_option',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.field_option){
            		row.field_option="";
            		return "";
            	}else{
            		return row.field_option;
            	}
            }
        }, {
            width : '200',
            title : '问题属地',
            field : 'problemLand_option',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.problemLand_option){
            		row.problemLand_option="";
            		return "";
            	}else{
            		return row.problemLand_option;
            	}
            }
        }, {
            width : '200',
            title : '职级',
            field : 'rank_option',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.rank_option){
            		row.rank_option="";
            		return "";
            	}else{
            		return row.rank_option;
            	}
            }
        },{
            width : '200',
            title : '处置部门',
            field : 'organId_option',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.organId_option){
            		row.organId_option="";
            		return "";
            	}else{
            		return row.organId_option;
            	}
            }
        },{
            width : '200',
            title : '专项行动',
            field : 'special_option',
            sortable : true,
            formatter : function(value, row, index) {
            	if(!row.special_option){
            		row.special_option="";
            		return "";
            	}else{
            		return row.special_option;
            	}
            }
        }, {
            width : '80',
            title : '数量',
            field : 'num',
            sortable : true,
            formatter : function(value, row, index) {
            	return '<span  onclick="grantFun(\''+index+'\');" style="color:green">'+row.num+'</span>'
            }
        }] ]
    });
});
function option (id){
	$("#options").val($("#"+id).val());
}
function tj(){
	dataGrid.datagrid('reload',{ 
		type: $("#type").val()
    	,options:$("#options").val()
    	,problemLand_option:$("#problemLand_option").val()
    	,field_option:$("#field_option").val()
    	,rank_option:$("#rank_option").val()
    	,start_time:$("#start_time").datebox('getValue')
    	,end_time:$("#end_time").datebox('getValue')
    	,organId_option:$("#organId_option").val()
    	,special_option:$("#special_option").val()
    	,clues_option:$("#clues_option").val()
    	
	});
}


//统计反查使用
function grantFun(rowIndex){
	  var rows = $('#dataGrid').datagrid('getRows');
	  console.log(rows);
	  var row = rows[rowIndex];
	  console.log(row);
	  if(row.problemLand_option!=""){
		  a=row.problemLand_option;
	  }else if(row.clues_option){
		  a=row.clues_option;
	  }else{
		  var mydate=new Date();
		  a = "cms"+mydate.getDay()+ mydate.getHours()+ mydate.getMinutes()+mydate.getSeconds()+mydate.getMilliseconds()+ Math.round(Math.random() * 10000); 
	  }
	  
	  parent.addTab("统计详情||"+a,"${path}/clues_tj/detail?"+
			  "options="+$("#type").val()+
			  "&type="+row.type+
			  "&start_time="+row.start_time+
			  "&end_time="+row.end_time+
			  "&field_option="+row.field_option+
			  "&problemLand_option="+row.problemLand_option+
			  "&rank_option="+row.rank_option+
			  "&special_option="+row.special_option+
			  "&organId_option="+row.organId_option+
			  "&clues_option="+row.clues_option);
}

function dc(){
	var data = $('#dataGrid').datagrid('getData').rows; 
	var newData=new Array();
	for(var x in data){
		var obj=new Object();
		if(data[x].type){
			obj["类别"]=data[x].type;
		}
		
		if(data[x].field_option){
			obj["领域"]=data[x].field_option;
		}
		if(data[x].start_time){
			obj["开始时间"]=data[x].start_time;
		}
		if(data[x].end_time){
			obj["结束时间"]=data[x].end_time;
		}
		if(data[x].clues_option){
			obj["线索来源"]=data[x].clues_option;
		}
		if(data[x].field_option){
			obj["领域"]=data[x].field_option;
		}
		if(data[x].problemLand_option){
			obj["问题属地"]=data[x].problemLand_option;
		}
		if(data[x].rank_option){
			obj["职级"]=data[x].rank_option;
		}
		if(data[x].organId_option){
			obj["处置部门"]=data[x].organId_option;
		}
		if(data[x].special_option){
			obj["专项行动"]=data[x].special_option;
		}
		if(data[x].num){
			obj["数量"]=data[x].num;
		}
		newData[x]=obj;
	}
	
	JSONToCSVConvertor(JSON.stringify(newData), $('#type option:selected').text()+"统计结果", true);  
}


function JSONToCSVConvertor(JSONData, ReportTitle, ShowLabel) {  
    //If JSONData is not an object then JSON.parse will parse the JSON string in an Object  
    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData)  
            : JSONData;  
    var CSV = '';  
    //Set Report title in first row or line  
    CSV += ReportTitle + '\r\n\n';   
    //This condition will generate the Label/Header  
    if (ShowLabel) {  
        var row = "";  
        //This loop will extract the label from 1st index of on array  
        for ( var index in arrData[0]) {  
            //Now convert each value to string and comma-seprated  
            row += index + ',';  
        }  
        row = row.slice(0, -1);  
        //append Label row with line break  
        CSV += row + '\r\n';  
    }  
    //1st loop is to extract each row  
    for (var i = 0; i < arrData.length; i++) {  
        var row = "";  
        //2nd loop will extract each column and convert it in string comma-seprated  
        for ( var index in arrData[i]) {  
            row += '"' + arrData[i][index] + '",';  
        }  
        row.slice(0, row.length - 1);  
        //add a line break after each row  
        CSV += row + '\r\n';  
    }  
    if (CSV == '') {  
        alert("Invalid data");  
        return;  
    }  
    //Generate a file name  
    var fileName = "";  
    //this will remove the blank-spaces from the title and replace it with an underscore  
    fileName += ReportTitle.replace(/ /g, "_");  
    //Initialize file format you want csv or xls  
    var uri = 'data:text/csv;charset=utf-8,' + encodeURI(CSV);  
    // Now the little tricky part.  
    // you can use either>> window.open(uri);  
    // but this will not work in some browsers  
    // or you will not get the correct file extension      
    //this trick will generate a temp <a /> tag  
    var link = document.createElement("a");  
    link.href = uri;  
    //set the visibility hidden so it will not effect on your web-layout  
    link.style = "visibility:hidden";  
    link.download = fileName + ".csv";  
    //this part will append the anchor tag and remove it after automatic click  
    document.body.appendChild(link);  
    link.click();  
    document.body.removeChild(link);  
}  
</script>
</body>
</html>