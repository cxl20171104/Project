<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>党费缴纳管理</title>
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript" src="${staticPath }/static/month.js"></script>
<style>
              

   #dialogTable th{background: #D7DBDD;      
   }  
  
</style>
    <script type="text/javascript">
    var oid="${oid}";
    var oname="${oname}";
    $(function() {
        $('#caseClueTable').datagrid({
            url : '${path }' + '/pay/findPayPageCondition',
            fit : true,
            idField : 'id',
            fitColumns : true,
            striped : true,
            rownumbers : false,
            pagination : true,
            singleSelect : true,
            selectOnCheck : false,
            checkOnSelect : false,
            border : false,
            queryParams: {
            	oid:oid
			},
            sortName : 'month',
            sortOrder : 'desc',
            toolbar:'#button',
            columns : [ [ {
                title : 'id',
                field : 'id',
                checkbox : true
            }, {
                width:15,
                title : '姓名',
                field : 'name',
                halign : 'center',
                sortable : true,
                formatter:function(value,row,index){                   
                	return row.user.name;
                }
            }
            , {
            	width:38,
                title : '部门名称',
                field : 'beReflectName',
                halign : 'center',
               formatter:function(value,row,index){
                    return row.organization.name;
                }
            } ,
            {
               width:25,
               title:'缴费基数',
               field:'paybase',
               halign:'center'
            }, 
            {
               width:25,
               title:'党费比例',
               field:'payscale',
               halign:'center'
            },            
            {
            	width:25,
                title : '支付金额',
                field : 'money',
                halign : 'center',
                sortable : true
            } ,
            {
            	width:25,
                title : '支付时间',
                field : 'payTime',
                halign : 'center',
                sortable : true
                
            },
            {
            	width:25,
                title : '年度',
                field : 'year',
                halign : 'center',
                sortable : true
                
            },
            {
            	width:25,
                title : '月度',
                field : 'month',
                halign : 'center',
                sortable : true
                
            },
            {
                width : 25,
                title : '操作',
                field : 'delete_status',
                halign : 'center',
                formatter:function(value,row,index){             
                return '<a class="easyui-edit-buttiom" href="javaScript:editFun();"></a>'            	
                }
            } 
            ] ],
            onLoadSuccess : function(data) {
				$('.easyui-edit-buttiom').linkbutton({
					text : '缴纳党费',
					plain : true,
					iconCls : 'icon-edit'
				});
			},
            loadFilter: function(data){
				if (data.obj){
					return data.obj;
				} else {
					return data;
				}
			}
        });
   
           $('#caseClueDialog').dialog({
        	width:480,
        	height:280,
        	title:'支付信息维护',
        	closed:true,
        	buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					save();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#caseClueDialog').dialog('close');
				}
			}]
        });  
      
   
    });
	function refreshTable(){
		$('#caseClueTable').datagrid('reload');
	}
	function closeDialog(){
		$('#caseClueDialog').dialog('close');
	}
	
	function searchFun(){
    var userid=$('#users').combobox('getValue');
    var organization_id=oid;
    if($('#organ')[0]){
    	   organization_id=$('#organ').combotree('getValue');
    };
    $('#caseClueTable').datagrid('load', {
				      userid:userid,
				      organization_id:organization_id,
					  start:$('#start').datebox('getText'),
					  end:$('#end').datebox('getText')
					  	  
		  });
	
	};	
	  function organChang(newValue, oldValue){
          $("#users").combobox('clear'); 
          if(newValue!=null){
            var url='${path}' + '/organization/organUsers?id='+newValue;
            $("#users").combobox('reload',url);
          }else{
             $("#users").combobox('reload'); 
          }
         
     };

     function addFun(){
    	$('#caseClueForm').form('clear');
        $("#payType2").combobox('setValue',3);
         var date = new Date();
         var year = date.getFullYear();
         var month = date.getMonth()+1;    //js从0开始取 
         var day = date.getDate();
        $("#payTime").datebox('setValue',year+'-'+month+'-'+day);  
		$('#caseClueDialog').dialog('open').dialog('refresh');
     };
     function delFun(){
        var rows = $('#caseClueTable').datagrid('getChecked');
		if(rows.length>0){
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r){
				if (r){
					var ids = new Array();
					for(var i=0;i<rows.length;i++){
						ids[ids.length] = rows[i].id;
					}
					$.ajax({
						url:'${path }/pay/del',
						data:{ids:ids.join()},
						dataType:'json',
						async : true,
						type:'post',
						success:function(result){
							if(result.success){
								$('#caseClueTable').datagrid('reload');
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

	 function editFun(){
	    var row = $('#caseClueTable').datagrid('getSelected');
        $("#organ2").combotree('setValue',row.organization_id);  
        $("#organ2").combotree('setText',row.organization.name); 
         $("#users2").combobox('setValue',row.userid);    
        $("#payTime").datebox('setValue',row.payTime);  
        $("#money").textbox('setValue',row.money); 
        $("#id").val(row.id); 
        $("#paybase").numberbox('setValue', row.paybase);
        $("#payscale").numberbox('setValue', row.payscale);
        $('#caseClueDialog').dialog('open');
	 }  
	function save(){
		$('#caseClueForm').form('enableValidation');
	if($('#caseClueForm').form('validate')){
		var url = '';
		if($('#id').val()==null||$('#id').val()==''){
			url = '${path }/pay/add';
		}else{
			url = '${path }/pay/edit';
		}
		var obj = $('#caseClueForm').serializeObject();
		$.ajax({
			url:url,
			data:obj,
			dataType:'json',
			type:'post',
			success:function(result){
				refreshTable();
				closeDialog();
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
function organChang2(newValue, oldValue){
          $("#users2").combobox('clear'); 
          if(newValue!=null){
             var url='${path}' + '/organization/organUsers?id='+newValue;
            $("#users2").combobox('reload',url);
          }else{
             $("#users2").combobox('reload'); 
          }
         
     }		 
		 
	function importFun(){
		$('#fileData').click();
	}
    function downTemplate(){
    	var year=$('#yearChoose').combobox('getValue');
    	var month=$('#monthChoose').combobox('getValue');
    	console.log(year);
    	console.log(month);
    	console.log(oid);
    	 var form=$("<form></form>");
         form.attr("style","dispaly:none");
         form.attr("targer"," ");
         form.attr("action",'${path}' + '/pay/downfile');
         var input=$("<input>");
	     input.attr("type","hidden");
	     input.attr("name","year");
	     input.attr("value",year);
         var input1=$("<input>");
	     input1.attr("type","hidden");
	     input1.attr("name","month");
	     input1.attr("value",month);
         var input2=$("<input>");
	     input2.attr("type","hidden");
	     input2.attr("name","oid");
	     input2.attr("value",oid);	     	     	     
         var input3=$("<input>");
	     input3.attr("type","hidden");
	     input3.attr("name","oname");
	     input3.attr("value",oname);	     	     	     
	     $("body").append(form);
	     form.append(input);
	     form.append(input1);
	     form.append(input2);
	     form.append(input3);
	     form.submit();
    };
    function downTable(){
    	var year=$('#year').combobox('getValue');
    	var quarter=$('#query').combobox('getValue');
    	console.log(year);
    	 var form=$("<form></form>");
         form.attr("style","dispaly:none");
         form.attr("targer"," ");
         form.attr("action",'${path}' + '/pay/outExcel');
         var input=$("<input>");
	     input.attr("type","hidden");
	     input.attr("name","year");
	     input.attr("value",year);
         var input1=$("<input>");
	     input1.attr("type","hidden");
	     input1.attr("name","quarter");
	     input1.attr("value",quarter);
         var input2=$("<input>");
	     input2.attr("type","hidden");
	     input2.attr("name","oid");
	     input2.attr("value",oid);	
	     var input3=$("<input>");
	     input3.attr("type","hidden");
	     input3.attr("name","oname");
	     input3.attr("value",oname);
	     $("body").append(form);
	     form.append(input);
	     form.append(input1);
	     form.append(input2);
	     form.append(input3);
	     form.submit();
    };
    function importExcel(){
		$.messager.alert('提示','正在导入中,请稍后...', 'info');   
		var formData = new FormData();
		if($("#fileData")[0] != undefined){
			formData.append("fileData",$("#fileData")[0].files[0]);
		}
		$.ajax({
			url:'${path }/pay/importExcel',
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
    $(function(){
        $("#yearChoose").combobox({   
     	valueField:'year',    
     	textField:'year',  
     	panelHeight:'auto'
     	});
     	var data = [];//创建年度数组
     	var startYear;//起始年份
     	var thisYear=new Date().getUTCFullYear();//今年
     	var endYear=thisYear+1;//结束年份
     	//数组添加值（2012-2016）//根据情况自己修改
     	for(startYear=endYear-4;startYear<=thisYear;startYear++){
     	data.push({"year":startYear});
     	     }
     	$("#yearChoose").combobox("loadData", data);//下拉框加载数据
     	$("#yearChoose").combobox("setValue",thisYear);//设置默认值为今年
     	//月度下拉框初始化
     	$("#monthChoose").combobox({
     	valueField:'month',    
     	textField:'month',  
     	panelHeight:'auto',
     	editable:false
     	});
     	var data1 = [];//创建月份数组
     	var startMonth=1;//起始月份
     	var thisMonth=new Date().getUTCMonth()+1;//本月
     	//数组添加值（1-12月）为固定值
     	for(startMonth;startMonth<13;startMonth++){
     		if(startMonth<10){
     		  	data1.push({"month":"0"+startMonth});
     		}else{
     		  	data1.push({"month":startMonth});
     		}
   
     	}
     	$("#monthChoose").combobox("loadData", data1);//下拉框加载数据
     	$("#monthChoose").combobox("setValue",thisMonth);//设置默认值为本月
    })
    $(function(){
        $("#year").combobox({   
     	valueField:'year',    
     	textField:'year',  
     	panelHeight:'auto'
     	});
     	var data = [];//创建年度数组
     	var startYear;//起始年份
     	var thisYear=new Date().getUTCFullYear();//今年
     	var endYear=thisYear+1;//结束年份
     	for(startYear=endYear-4;startYear<=thisYear;startYear++){
     	data.push({"year":startYear});
     	     }
     	$("#year").combobox("loadData", data);//下拉框加载数据
     	$("#year").combobox("setValue",thisYear);//设置默认值为今年
     	//季度下拉框初始化
     	$("#query").combobox({
     	valueField:'query',    
     	textField:'query',  
     	panelHeight:'auto',
     	editable:false
     	});
     	var data1 = [];//创建季度数组
     	var startQuery=1;//起始季度
     	var thisMonth=new Date().getUTCMonth()+1;//季度
     	var thisQuery=Math.floor( ( thisMonth % 3 == 0 ? ( thisMonth / 3 ) : ( thisMonth / 3 + 1 ) ) );
     	for(startQuery;startQuery<5;startQuery++){
     	data1.push({"query":startQuery});
     	}
     	$("#query").combobox("loadData", data1);//下拉框加载数据
     	$("#query").combobox("setValue",thisQuery);//设置默认值为本季度
    })

     
 	
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',split:false,border:false" style="height:0px;border-bottom:1px solid #d3d3d3">
             <div id="button">
	              <label for="organ">党组织&nbsp;&nbsp;&nbsp;&nbsp;:</label> 
	             <input id="organ" class="easyui-combotree" name="organ"  data-options="width:350,valueField:'id',textField:'',url:'${path}' + '/organization/organlist',
			                    onChange:organChang,prompt:'未选择'
			                     " /> 
              &nbsp;&nbsp;&nbsp;&nbsp;<label for="users">党员个人:</label>   
		     	

              &nbsp;&nbsp;&nbsp;&nbsp;<label for="organ">缴费时间&nbsp;&nbsp;&nbsp;&nbsp;从:</label> 
              <input id="start" type="text" class="easyui-datebox" style="width:100px">
              <label for="organ">&nbsp;&nbsp;&nbsp;&nbsp;至:</label> 
              <input id="end" type="text" class="easyui-datebox" style="width:100px">
             
             <a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();" >查询</a><br/>
           <!--   <a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFun();" >增加</a> -->
           <!--   <a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="delFun();" >删除</a> -->
         <!--  <input id="date" name="date" class="easyui-datebox" data-options="buttonText: '月份', 
　　                                                                    buttonAlign: 'left', panelAlign: 'right', editable: false"> -->
              <label>年度选择:</label>
                 <input id="yearChoose" class="easyui-combobox" name="yearChoose"/>  
             <label>月度选择:</label>
             <input id="monthChoose" class="easyui-combobox" name="monthChoose" /> 
             <a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="downTemplate();">下载模板</a>
              <form id="importForm" method="post" enctype="multipart/form-data" style="display: none;">
            	<input type="file" id="fileData" name="fileData" onchange="importExcel()" />
             </form>
             <a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="importFun();" >导入模板</a> </br>
              <label>年度选择:</label>
                 <input id="year" class="easyui-combobox" name="year"/>  
             <label>季度选择:</label>
                 <input id="query" class="easyui-combobox" name="query" /> 
             <a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="downTable();">下载报表</a>
             </div>
             
   </div>
    <div data-options="region:'center',border:false" id="div_table">
        <table id="caseClueTable"></table>
    </div>
     <div id="caseClueDialog">   
       <form id="caseClueForm" class="easyui-form" data-options="novalidate:true">
	    <input type="hidden" id="id" name="id" value="" />
	    
	    <table id="dialogTable">
	    	<tr>
	            <th>部门名称：</th>
	    		<td> <input id="organ2" width="50%" class="easyui-combotree" name="organization_id"  data-options="valueField:'id',textField:'name',url:'${path}' + '/organization/organlist',
		                    onChange:organChang2,readonly:true
		                     " /> </td>      	
	    		<th>党员姓名：</th>
	    		<td><input id="users2" width="50%" class="easyui-combobox" name="userid"  data-options="required:true,valueField:'id',textField:'name',url:'${path}' + '/user/getAll',readonly:true" /></td> 
	    	</tr>
	    	<tr>
	    	   <th> 缴费基数: </th>
	    	   <td>
	    	      <input id="paybase" name="paybase" type="text" class="easyui-numberbox" data-options="min:0,precision:2,required:true"></input> 
	    	   </td>
	    	   <th> 党费比例: </th>
	    	   <td>
	    	      <input id="payscale" name="payscale" type="text" class="easyui-numberbox" data-options="min:0,precision:2,suffix:'%',required:true"></input> 
	    	   </td>
	    	</tr>
	    	<tr> 
	    		 <th>支付时间：</th>
	    		 <td><input type="text" width="50%" class="easyui-datebox" data-options="required:true,width:'100%',editable:false" id="payTime" name="payTime" value="${pay.payTime}" /></td> 
	    	</tr>
	    	<tr>
	    	<th>支付金额：</th>
	    	   <td ><input type="text" width="50%" class="easyui-numberbox" data-options="min:1,precision:2,required:true"  id="money" name="money"/></td> 
	    	</tr>	    
	         </table>
	     </form>
     </div>
</body>
</html>