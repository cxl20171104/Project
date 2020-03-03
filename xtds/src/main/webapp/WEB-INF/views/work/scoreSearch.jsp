<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>积分查询</title>
<script type="text/javascript">
     function organChang(newValue, oldValue){
          $("#users").combobox('clear'); 
          if(newValue!=null){
             var url='${path}' + '/organization/organUsers?id='+newValue;
            $("#users").combobox('reload',url);
          }else{
             $("#users").combobox('reload'); 
          }
         
     }
       var level="${level}";
       var oid="${oid}";
       var organ=null;
       var type=null;
       var users=null;
       var year="";
     function  searchFun(){
        var selctedORgan=$("#organ").combotree('tree').tree('getSelected');
        if(selctedORgan){
           organ=selctedORgan.id;
        }
        type=$("#type").combobox("getValue");
        users=$("#users").combobox("getValue");
        year=$("#year").combobox("getValue");
         var data= {
            type:$("#type").combobox("getValue"),
            users:$("#users").combobox("getValue"),
            year:$("#year").combobox("getValue"),
            organ:organ,
            level:level,
            oid:oid
         }
         dataGrid.datagrid("load",data);
     }
     function outFun(){
          var selctedORgan=$("#organ").combotree('tree').tree('getSelected');
          var organ;
        if(selctedORgan){
           organ=selctedORgan.id;
        } 
          var  type=$("#type").combobox("getValue");
           var users=$("#users").combobox("getValue");
           var sort=dataGrid.datagrid("options").sortName;
           var order=dataGrid.datagrid("options").sortOrder;
          var form=$("<form></form>");
             form.attr("style","dispaly:none");
             form.attr("targer"," ");
             form.attr("action",'${path}' + '/score/downfile');
             var input=$("<input>");
		     input.attr("type","hidden");
		     input.attr("name","type");
		     input.attr("value",type);
             var input1=$("<input>");
		     input1.attr("type","hidden");
		     input1.attr("name","organ");
		     input1.attr("value",organ);
             var input2=$("<input>");
		     input2.attr("type","hidden");
		     input2.attr("name","users");
		     input2.attr("value",users);		     
             var input3=$("<input>");
		     input3.attr("type","hidden");
		     input3.attr("name","sort");
		     input3.attr("value",sort);		     
             var input4=$("<input>");
		     input4.attr("type","hidden");
		     input4.attr("name","order");
		     input4.attr("value",order);		     
             var input5=$("<input>");
		     input5.attr("type","hidden");
		     input5.attr("name","year");
		     input5.attr("value",year);		     
		     $("body").append(form);
		     form.append(input);
		     form.append(input1);
		     form.append(input2);
		     form.append(input3);
		     form.append(input4);
		     form.append(input5);
		     form.submit();
     }
       var dataGrid;
   $(function(){
         dataGrid = $('#scorelist').datagrid(
						{
							url : '${path}' + '/score/scoreshow',
							fit : true,
							fitColumns : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							selectOnCheck : false,
							checkOnSelect : false,
							border : false,
							toolbar : '#toolbar',
							idField : 'id',
							sortName : 'scores',
							queryParams:{
								level:level,
								oid:oid
							},
			                sortOrder : 'desc',
							columns : [ [
									{
										title : 'id',
										field : 'id',
										hidden:true
									},
									{
										width : '100',
										title : '名称',
										field : 'name',
										halign : 'center',
										align:  'center',
										sortable : false
								
									},
									{
										width : '50',
										title : '分值',
										field : 'scores',
										halign : 'center',
										align:  'center',
										sortable : true
								
									},
									{
										width : '70',
										title :  '积分类型',
										field : 'type',
										halign : 'center',
										align:  'center',
										sortable : false,
										formatter:function(value,row,index){
										    if(value==1){
										       return "年度积分";
										    };
										    if(value==2){
										       return "月度积分";
										    };
										
										}
									},
									{
										width : '120',
										title : '日期',
										field : 'ctime',
										halign : 'center',
										align:  'center',
										sortable : false,
										formatter:function(value,row,index){
										 if(row.type==1){
										 return value.substring(0,4);
										 };
										 if(row.type==2){
										 return value.substring(0,7);
										 };
										 return value;
										}
								
									}] ],
							onClickRow : showSecondPage
						
						});
      
   })
   var rowD;
   var rowI;
   function showSecondPage(rowIndex, rowData){
     rowI=rowIndex; //记下选择的行数
     rowD=rowData;    //记下选择的数据   
       $("#secordPage").datagrid(
						{
							url : '${path}' + '/score/secordPage',
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
							sortName : 'scores',
			                sortOrder : 'desc',
							columns : [ [
									{
										title : 'id',
										field : 'id',
										hidden:true
									},
									{
										width : '100',
										title : '名称',
										field : 'name',
										halign : 'center',
										align:  'center',
										sortable : false
								
									},
									{
										width : '70',
										title : '分值',
										field : 'scores',
										halign : 'center',
										align:  'center',
										sortable : true
								
									},
									{
										width : '70',
										title :  '积分类型',
										field : 'type',
										halign : 'center',
										align:  'center',
										sortable : false,
										formatter:function(value,row,index){
										    if(value==1){
										       return "年度积分";
										    };
										    if(value==2){
										       return "月度积分";
										    }else{
										      return"";
										    }
										
										}
									},
									{
										width : '120',
										title : '日期',
										field : 'ctime',
										halign : 'center',
										align:  'center',
										sortable : true,
										formatter:function(value,row,index){
										 if(row.type==1){
										 return value.substring(0,4);
										 };
										 if(row.type==2){
										 return value.substring(0,7);
										 };
										 return value;
										}
								
									},{
									   width:'400',
									   title:'描述',
									   field:'descr',
									   sortable:false
									},{
									   width:'100',
									   title:'实时图片',
									   field:'url',
									   sortable:false,
									   formatter:function(value,row,index){
									     if(value==null){
									       return"";
									     };
									     if(value==""){
									       return"";
									     };
									     
									     return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="opendialog(\''+value+'\');" >查看图片</a>'
									   
									   
									   }
									
									}
									] ],
									queryParams: {
											type: rowData.type,
											id: rowData.id,
											organ:organ,
											users:users,
											year:year,
											ctime:rowData.ctime,
											classType:rowData.classType
									},
									onClickRow : showThirdPage
									
						});
   }
   var rowIn;
   var rowDa;
   function showThirdPage(rowIndex, rowData){
          
          if(!rowData.type){
             return;
          } 
          rowIn=rowIndex;
          rowDa=rowData;
          $("#secordPage").datagrid(
						{
							url : '${path}' + '/score/secordPage',
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
							sortName : 'scores',
			                sortOrder : 'desc',
			                toolbar: [{
								iconCls: 'icon-back',
								text:'返回',
								handler: function(){showSecondPage(rowI, rowD)}
							}],
							columns : [ [
									{
										title : 'id',
										field : 'id',
										hidden:true
									},
									{
										width : '100',
										title : '名称',
										field : 'name',
										halign : 'center',
										align:  'center',
										sortable : false
								
									},
									{
										width : '70',
										title : '分值',
										field : 'scores',
										halign : 'center',
										align:  'center',
										sortable : true
								
									},
									{
										width : '70',
										title :  '积分类型',
										field : 'type',
										halign : 'center',
										align:  'center',
										sortable : false,
										formatter:function(value,row,index){
										    if(value==1){
										       return "年度积分";
										    };
										    if(value==2){
										       return "月度积分";
										    }else{
										      return"";
										    }
										
										}
									},
									{
										width : '120',
										title : '日期',
										field : 'ctime',
										halign : 'center',
										align:  'center',
										sortable : true,
										formatter:function(value,row,index){
										 if(row.type==1){
										 return value.substring(0,4);
										 };
										 if(row.type==2){
										 return value.substring(0,7);
										 };
										 return value;
										}
								
									},{
									   width:'200',
									   title:'描述',
									   field:'descr',
									   sortable:false
									},{
									   width:'100',
									   title:'实时图片',
									   field:'url',
									   sortable:false,
									   formatter:function(value,row,index){
									     if(value==null){
									       return"";
									     };
									     if(value==""){
									       return"";
									     };
									     return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="opendialog(\''+value+'\');" >查看图片</a>'
									   
									   
									   }
									
									}
									] ],
									queryParams: {
											type: rowData.type,
											id: rowData.id,
											organ:organ,
											users:users,
											year:year,
											ctime:rowData.ctime,
											classType:rowData.classType
									},
									onClickRow : showForthPage									
						});
   
   
   
   }
   var rowInd;
   var rowDat;
   function showForthPage(rowIndex, rowData){
          
          if(!rowData.type){
             return;
          }           
          rowInd=rowIndex;
          rowDat=rowData;
          $("#secordPage").datagrid(
						{
							url : '${path}' + '/score/secordPage',
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
							sortName : 'scores',
			                sortOrder : 'desc',
			                toolbar: [{
								iconCls: 'icon-back',
								text:'返回',
								handler: function(){showThirdPage(rowIn, rowDa)}
							}],
							columns : [ [
									{
										title : 'id',
										field : 'id',
										hidden:true
									},
									{
										width : '100',
										title : '名称',
										field : 'name',
										halign : 'center',
										align:  'center',
										sortable : false
								
									},
									{
										width : '70',
										title : '分值',
										field : 'scores',
										align:  'center',
										halign : 'center',
										sortable : true
								
									},
									{
										width : '70',
										title :  '积分类型',
										field : 'type',
										halign : 'center',
										align:  'center',
										sortable : false,
										formatter:function(value,row,index){
										    if(value==1){
										       return "年度积分";
										    };
										    if(value==2){
										       return "月度积分";
										    }else{
										      return"";
										    }
										
										}
									},
									{
										width : '120',
										title : '日期',
										field : 'ctime',
										halign : 'center',
										align:  'center',
										sortable : false,
										formatter:function(value,row,index){
										 if(row.type==1){
										 return value.substring(0,4);
										 };
										 if(row.type==2){
										 return value.substring(0,7);
										 };
										 return value;
										}
								
									},{
									   width:'400',
									   title:'描述',
									   field:'descr',
									   sortable:false
									},{
									   width:'100',
									   title:'实时图片',
									   field:'url',
									   halign : 'center',
									   align:  'center',
									   sortable:false,
									   formatter:function(value,row,index){
									     if(value==null){
									       return"";
									     };
									     if(value==""){
									       return"";
									     };
									     return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="opendialog(\''+value+'\');" >查看图片</a>'
									   
									   
									   }
									
									}
									] ],
									queryParams: {
											type: rowData.type,
											id: rowData.id,
											organ:"",
											users:users,
											year:year,
											ctime:rowData.ctime
									}									
						});
   
   
   
   }
   function opendialog(value){
        $('#img').attr("src", '${path}/files/'+value); 
        $('#dd').dialog('open');
   }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
      <div data-options="region:'west',iconCls:'icon-reload',title:'积分查询页',split:false,collapsible:false" style="width:500px;">
        <form id="toolbar" method="post">
                 <div>
                    <label for="organ">党组织&nbsp;&nbsp;&nbsp;&nbsp;:</label>   
		            <input id="organ" class="easyui-combotree" name="organ"  data-options="valueField:'id',textField:'name',url:'${path}' + '/organization/organlist',prompt:'选择党组织',width:300,
		                    onChange:organChang,prompt:'未选择'
		                     " /> 
		         </div><div>   
		           <label for="users">党员个人:</label>   
		            <input id="users" class="easyui-combobox" name="users"  data-options="valueField:'id',textField:'name',url:'${path}' + '/user/getAll',prompt:'未选择',queryParams:{level:'${level}',oid:'${oid}'}" /> 
                 
                 </div>
		          <div>
		              <label for="type">积分周期:</label>   
			           <select id="type" class="easyui-combobox" name="type" style="width:160px;">   
						    <option value="1">年度积分</option>   
						    <option value="2" selected="selected">月度积分</option>
						</select> 
				         &nbsp;&nbsp;&nbsp;&nbsp;<label for="users">年度选择:</label>
				          <select id="year" class="easyui-combobox" name="year" style="width:160px;"> 
				               <option value="" selected="selected">未选择</option>     
						       <option value="2017">2017年</option>   
						       <option value="2018">2018年</option> 
						       <option value="2019">2019年</option>  
						       <option value="2020">2020年</option>  
						       <option value="2021">2021年</option>    
						 </select>    
		          </div>
		          <div>
		               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="searchFun();">查询</a>
				  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="outFun();">导出</a>
		          
		          </div>
		</form> 
		         <table id="scorelist" class="easyui-datagrid" >
                     
                 </table>
		
      
      </div>   
      <div data-options="region:'center',title:'积分详情页'">
                 <table id="secordPage" class="easyui-datagrid">
                 
                 
                 </table>
      
      </div>
      <div id="dd" class="easyui-dialog" title="图片显示" style="width:500px;height:400px;"   
        data-options="modal:true,collapsible:false,minimizable:false,maximizable:false,border:false,closed:true"> 
        <img alt="" src="" id="img" style="width:100%;height: 100%">
      </div>     
 
</body>
</html>