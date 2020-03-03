<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
    <script type="text/javascript">

    var dataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/score/exchangeData',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            fitColumns : true,
            idField : 'id',
            sortName : 'creaTime',
            sortOrder : 'desc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '100',
                title : '礼品名称',
                field : 'giftName',
                halign : 'center',
                sortable : true
            }, {
                width : '200',
                title : '图片',
                field : 'giftPic',
                halign : 'center',
                formatter: function(value,row,index){  
                	var path='${path}/files/'+value;
					return '<img src="'+path+'" style="width:100%;"/>';
		     } 
            },{
                width : '200',
                title : '兑换时间',
                field : 'creaTime',
                halign : 'center',
                sortable : true
            },{
                width : '200',
                title : '兑换人',
                field : 'userName',
                halign : 'center',
                sortable : true
               
            },{
                width : '200',
                title : '兑换人部门',
                field : 'orgaName',
                halign : 'center',
                sortable : true
            }] ]
        });
        $('#organ').combotree(
    			{
    				panelWidth : 500,
    				panelHight : 500,
    				url :'${path}' + '/organization/organlist',
    				onChange:function(node){
    					
    					
    					 if(node!=null){
    				           var url='${path}' + '/organization/organUsers?id='+node;
    				          $("#users").combobox('reload',url);
    				        }else{
    				           $("#users").combobox('reload'); 
    				        }
    	
    				  },
    				  onLoadSuccess:function(node,data){
    				   $('#organ').combotree('tree').tree('expandAll');
    				    },loadFilter : function(data) {
    					if (data.obj) {
    						return data.obj;
    					} else {
    						return data;
    					}
    				}
    			});
    });
    

    
    function deleteFun() {
    	var row = dataGrid.datagrid('getSelected');
        if(row != null){
	        parent.$.messager.confirm('确认对话框', '您是否要删除当前凭证？', function(b) {
	            if (b) {
	                if ($('#currentUserId').val() != row.id) {
	                    progressLoad();
	                    $.post('${path }/score/exchangeDelete', {
	                    	exchangeId : row.id
	                    }, function(result) {
	                        if (result.success) {
	                        	$.messager.show({
	    	        				title:'消息提示',
	    	        				msg:result.msg,
	    	        				timeout:3000,
	    	        				showType:'slide'
	    	        			});
	                            dataGrid.datagrid('reload');
	                        }
	                        progressClose();
	                    }, 'JSON');
	                } else {
	                	$.messager.show({
	        				title:'消息提示',
	        				msg:'不可以删除自己！',
	        				timeout:3000,
	        				showType:'slide'
	        			});
	                }
	            }
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
    
   

    
    function searchFun() {
        dataGrid.datagrid('load', {
        	userId:$('#users').combobox('getValue'),
        	startTime:$('#startTime').combobox('getValue'),
        	endTime:$('#endTime').combobox('getValue')
        });
    }
    function organChang(newValue, oldValue){
    	$('#organ').combotree('tree').tree('expandAll');
        $("#users").combobox('clear'); 
        if(newValue!=null){
           var url='${path}' + '/organization/organUsers?id='+newValue;
          $("#users").combobox('reload',url);
        }else{
           $("#users").combobox('reload'); 
        }
       
    }

    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
                     <table>
                     <tr>
                     <td><label for="organ">党组织&nbsp;&nbsp;&nbsp;&nbsp;:</label>   
		              <input id="organ" class="easyui-combotree" name="organ"  data-options="valueField:'id',textField:'name',prompt:'选择党组织',width:300"/> </td>
                     <td><label for="users">党员个人:</label>   
		             <input id="users" class="easyui-combobox" name="users"  data-options="valueField:'id',textField:'name',url:'${path}' + '/user/getAll',prompt:'未选择'" /> </td>
                     <td><label>开始时间：</label>
                     <input id="startTime" name="startTime" type="text" class="easyui-datebox" data-options="width:'80%',required:false,editable:false" value=""></input></td>
                     <td> <label>结束时间：</label>
                     <input id="endTime" name="endTime" type="text" class="easyui-datebox" data-options="width:'80%',required:false,editable:false" value=""></input></td>
                     <td><shiro:hasPermission name="/user/delete">
                     <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();" >查询</a>
                     </shiro:hasPermission></td>
                     <td><shiro:hasPermission name="/user/delete">
                     <a class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
                     </shiro:hasPermission> </td>
                     </tr>
                     </table>
                      
                     
                     
                    
                      
                      
    </div>
    <div data-options="region:'center',border:false" >
    	<input type="hidden" id="currentUserId" value="<shiro:principal property="id" />" />
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
</body>
</html>