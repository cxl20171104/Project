<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>通知公告</title>
<script type="text/javascript">
       var dataGrid;
   $(function(){
         dataGrid = $('#tasklist').datagrid(
						{
							url : '${path}' + '/task/taskList',
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
							columns : [ [
									
									{
										width : '100',
										title : '发布人',
										field : 'creator',
										halign : 'center',
										sortable : false ,
										formatter:function(value,row,index){
										
										   return value;
										}
								
									},
								
									{
										width : '200',
										title : '标题',
										field : 'title',
										halign : 'center',
										sortable : false
								
									},
									{
										width : '400',
										title : '内容',
										field : 'content',
										halign : 'center',
										sortable : false
								
									},
									{
										width : '100',
										title : '发布时间',
										field : 'creatTime',
										halign : 'center',
										sortable : false,
										formatter:function(value,row,index){
										   return value.substring(0,10);
										}
								
									},
									{
										width : '150',
										title : '查看结果',
										field : 'id',
										halign : 'center',
										formatter:function(value,row,index){
										   return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="getslist(\''+value+'\');" >查看签收结果</a>'
									}
									},
									{
										width : '150',
										title : '删除',
										field : 'code',
										halign : 'center',
										formatter:function(value,row,index){
										   return '<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\''+row.id+'\');" >删除</a>'
										}
									}] ],
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
   
   
   
   function searchFun(){
       dataGrid.datagrid("load",{userName: $("#searchName").val()});  
   }
   function getslist(id){
        $("#reply").datagrid({
              url : '${path}' + '/task/taskStatus',
                fit : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				selectOnCheck : false,
				checkOnSelect : false,
				pageSize:20,
				pageList:[20,40],
				border : false,
				idField : 'id',
				queryParams: {		         
		          taskid:id	              
	           },
	           columns:[[
	              {
					width : '100',
					title : '部门',
					field : 'deptName',
					halign : 'center',
					sortable : false ,
					formatter:function(value,row,index){					
					   return value;
					}			
				},
	              {
					width : '100',
					title : '状态',
					field : 'status',
					halign : 'center',
					sortable : false ,
					formatter:function(value,row,index){	
					  if(value=="0"){
					      return "未签收";
					  };
					  if(value=="1"){
					      return "已签收";
					  };					  
					}
				},
	              {
					width : '150',
					title : '时间',
					field : 'rtime',
					halign : 'center',
					sortable : false ,
					formatter:function(value,row,index){					
					   if(value==null){
					      return "待签收";
					  }else{
					      return value;
					  }
					 
					}
				}
	           ]]
	                 
        })
   }
   function  addFun(){     
      $("#msgdialog").dialog("open");
   }
    
   function checkFun(node){ 
           var tt = $("#treeg"); 
               tt.tree('expand',node.target);    
          var childNode = tt.tree("getChildren",node.target);
                    for(var i= 0;i<childNode.length;i++)
                    {                          
                        tt.tree("check", childNode[i].target);
                    }           
   }
   function doublClick(node){
          var tt = $("#treeg"); 
          var childNode = tt.tree("getChildren",node.target);
                    for(var i= 0;i<childNode.length;i++)
                    {
                        tt.tree("uncheck", childNode[i].target);
                    } 
   }
   function putFun(){
                var tt = $("#treeg"); 
               var nodes = tt.tree('getChecked');
               var users="";
            if(nodes.length==0){
               alert("请选择接受公告部门");
               return;
            }else{
               for(var i=0;i<nodes.length;i++){
                  if(i==nodes.length-1){
                    users+=nodes[i].id;
                  }else{
                   users+=nodes[i].id+",";
                  }
               }
            } 
            var tb=$("#msgcon");
            var ti=$("#title");
            var content;
            var title;
            tb.textbox("enableValidation").textbox("validate");
            ti.textbox("enableValidation").textbox("validate");
            
            if(!tb.textbox('isValid')){
               return;
            }else if(!ti.textbox('isValid')){
               return;
            }else{
               content=tb.textbox('getValue');
               title=ti.textbox('getValue');
            }
            var msgPut={
                content:content,
                title:title,
                depts:users            
            }
            $.ajax({
               url:'${path}/task/sentTask',
                data:msgPut,
                dataType:'json',
                type:"post",
                success:function(data){
                   if(data.success){
                         tb.textbox('clear');
                        tt.tree('reload');
                        $("#msgdialog").dialog("close");
                         dataGrid.datagrid("load");
                   }else{
                         
                   }
                }
            })                      
   }
   function outFun(){
        var tt = $("#treeg"); 
        var tb=$("#msgcon");
        tb.textbox('clear');
        tt.tree('reload');
       $("#msgdialog").dialog("close");  
   }
   $.extend($.fn.validatebox.defaults.rules, {    
    maxLength: {    
        validator: function(value, param){    
            return value.length <= param[0];    
        },    
        message: '输入文字长度超限'   
    }    
});
function deleteFun(id){
	
	alert(id);
	 $.ajax({
         url:'${path}/task/delTask',
          data:{id:id},
          dataType:'json',
          type:"post",
          success:function(data){
             if(data.success){
                   dataGrid.datagrid("load");
             }else{
                   
             }
          }
      })   
	
	
	
	
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="region:'center',title:'公告列表',split:true,collapsible:false" style="width: 600px;height: 90%">
		     <div style="width: 100%"  id="toolbar"><%--
		 
		         <input type="text" class="easyui-textbox" data-options="prompt:'请输入人员名称'" id="searchName" />
		          <a class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="searchFun();">查询</a>
		          --%><a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
					onclick="addFun();">添加</a>
		     </div>
			  <table id="tasklist" class="easyui-datagrid" >
                     
                 </table>  
   </div>
   <div data-options="region:'east',title:'详情表',collapsible:false" style="width: 350px;height: 90%">
         <table id="reply" class="easyui-datagrid">
            
        </table>          
    </div> 
    <div  id="msgdialog" class="easyui-dialog"   data-options="closed:true,
						                                        title : '添加消息', 
						                                        width : 900,
					                                            height : 500,
					                                            fitColumns : true,
					                                             resizable:true,
					                                             model:true,
															     buttons:'#dialogButton'" >
				<div id="dialogButton">
					<a href="#" class="easyui-linkbutton" onclick="putFun()">发送</a>
					<a href="#" class="easyui-linkbutton" onclick="outFun()">取消</a>
				</div>
				<div id="addmsg" class="easyui-layout" style="width:100%;height:100%;">    
                   <div data-options="region:'west',title:'选择发送人',split:true,collapsible:false" style="width:400px;">
                         <div  class="easyui-panel" style="padding:5px">
                             <ul id ="treeg" class="easyui-tree" data-options="url:'${path }/organization/organlist',method:'get',animate:true,lines:true,
                             cascadeCheck:false, checkbox:true,formatter:function(node){ return node.name;},cascadeCheck:false,onlyLeafCheck:true,
                             onClick:checkFun,onDblClick:doublClick
                              "></ul> 
                        </div>                  
                   </div>   
                   <div data-options="region:'center',title:'填写公告'" style="padding:5px;">
                       <label>公告标题：</label><input id="title" name="title" class="easyui-textbox" data-options="required:true,prompt:'输入通知公告标题',width:300"></br></br>
                       
                       <input id="msgcon" name="content" class="easyui-textbox" data-options="multiline:true,fit:true,prompt:'请输入通知公告内容(限1000字)',required:true,validType:'maxLength[1000]'" />
                   </div>   
              </div> 
    </div>
</body>
</html>