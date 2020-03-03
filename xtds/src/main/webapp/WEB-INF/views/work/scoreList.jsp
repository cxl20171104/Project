<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>积分管理</title>
<style type="text/css">
a{text-decoration:none}
</style>
<script type="text/javascript">
       var dataGrid;
       var level="${level}";
       var oid="${oid}";
       
   $(function(){
         dataGrid = $('#scorelist').datagrid(
						{
							url : '${path}' + '/score/list',
							fit : true,
							fitColumns : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							selectOnCheck : false,
							checkOnSelect : false,
							border : false,
							queryParams:{
								level:level,
								oid:oid
							},
							sortName : 'scores',
			                sortOrder : 'desc',
							toolbar : '#toolbar',
							idField : 'id',
							columns : [ [
									{
										title : 'id',
										field : 'id',
										hidden:true
									},
									{
										width : '150',
										title : '用户',
										field : 'name',
										halign : 'center',
										sortable : false
								
									},
									{
										width : '200',
										title : '身份证号',
										field : 'idcard',
										halign : 'center',
										sortable : false
								
									},
									{
										width : '100',
										title : '分值',
										field : 'scores',
										halign : 'center',
										sortable : true
								
									},
									{
										width : '100',
										title :  '积分类型',
										field : 'type',
										halign : 'center',
										sortable : false,
										formatter:function(value,row,index){
										    if(value==1){
										       return "年度积分";
										    };
										    if(value==2){
										       return "月度积分";
										    };
										    if(value==3){
										       return "活动积分";
										    };
										    return "总积分";
										
										}
									},
									{
										width : '100',
										title : '统计时间',
										field : 'ctime',
										halign : 'center',
										sortable : false,
										formatter:function(value,row,index){
										return value.substring(0,10);
										}
								
									},
									{
										width : '150',
										title : '操作',
										field : 'userId',
										halign : 'center',
										formatter:function(value,row,index){
											if(value){
												 return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="getslist('+value+');" >查看详情</a>'
											}else{
												 return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="getslist('+row.id+');" >查看详情</a>'
											}
										  
										}
									} ] ],
							onLoadSuccess : function(data) {
							   
							},
							loadFilter : function(data) {
								if (data.obj) {
									return data.obj;
								} else {
									return data;
								}
							}
						});
      
   })
   var nd;
   var yf;
   function searchFun(){
       dataGrid.datagrid("load",{ userName: $("#searchName").val(),
                                  nd:$("#nd").combobox("getValue"),
                                  yf:$("#yf").combobox("getValue"),
                                  level:level,
                                  oid:oid});  
   }
   function getslist(userId){
        $("#scoreDe").datagrid({
              url : '${path}' + '/score/scoreList',
                fit : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				selectOnCheck : false,
				checkOnSelect : false,
				border : false,
				idField : 'id',
				queryParams: {
		            userId:userId,
		           nd:$("#nd").combobox("getValue"),
                   yf:$("#yf").combobox("getValue")            
	           }       
        })
   }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="region:'center',title:'积分列表',split:true,collapsible:false"  style="width: 700px;height: 90%">
		     <div style="width: 100%"  id="toolbar">		 
		         <input type="text" class="easyui-textbox" data-options="prompt:'请输入人员名称'" id="searchName" />
		         <select id="nd" class="easyui-combobox" name="nd" style="width:100px;" data-options="prompt:'选择年度',value:'',editable:false">   
                        <option value="2017">2017年</option>   
					    <option value="2018">2018年</option>   
					    <option value="2019">2019年</option>   
					    <option value="2020">2020年</option>   
					    <option value="2021">2021年</option>  
				</select> 
		         <select id="yf" class="easyui-combobox" name="yf" style="width:100px;" data-options="prompt:'选择月份',value:'',editable:false">
                        <option value="1">一月</option>   
					    <option value="2">二月</option>     
					    <option value="3">三月</option>    
					    <option value="4">四月</option>     
					    <option value="5">五月</option>    
                        <option value="6">六月</option>   
					    <option value="7">七月</option>     
					    <option value="8">八月</option>   
					    <option value="9">九月</option>     
					    <option value="10">十月</option>    
					    <option value="11">十一月</option>     
					    <option value="12">十二月</option>    
				</select> 
				 
		          <a class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="searchFun();">查询</a>
		     </div>
			  <table id="scorelist" class="easyui-datagrid" >
                     
                 </table>  
   </div>
   <div data-options="region:'east',title:'积分详情表',split:true,collapsible:false" style="width: 600px;height: 90%">
        <table id="scoreDe" class="easyui-datagrid">
             <thead>   
		        <tr>   
		            <th data-options="field:'scorevalue',width:100, halign:'center'">积分值</th>   
		            <th data-options="field:'descr',width:250,halign:'center'">积分途径</th>   
		            <th data-options="field:'ctime',width:150,halign:'center',formatter:function(value,row,index){
										return value.substring(0,10);
										}">日期</th>   
		        </tr>   
		    </thead>
        </table>
   
   </div>
</body>
</html>