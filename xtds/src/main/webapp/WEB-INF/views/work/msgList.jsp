<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>信息管理</title>
<script type="text/javascript">
       var dataGrid;
       var oid="${oid}";
   $(function(){
         dataGrid = $('#msglist').datagrid(
						{
							url : '${path}' + '/msg/getMsgList',
							fit : true,
							fitColumns : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							selectOnCheck : false,
							checkOnSelect : false,
							queryParams: {
				            	oid:oid
							},
							border : false,
							toolbar : '#toolbar',
							idField : 'id',
							columns : [ [
									
									{
										width : '100',
										title : '发布人',
										field : 'puter',
										halign : 'center',
										sortable : false ,
										formatter:function(value,row,index){
										
										   return value.name;
										}
								
									},
									{
										width : '100',
										title : '职务',
										field : 'toUserId',
										halign : 'center',
										sortable : false,
										formatter:function(value,row,index){
										
										   return row.puter.post;
										}
								
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
										field : 'id',
										halign : 'center',
										formatter:function(value,row,index){
										   return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="getslist(\''+value+'\');" >查看发送结果</a>'
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
   function searchFun(){
       dataGrid.datagrid("load",{userName: $("#searchName").val(),
    	   oid:oid});  
   }
   function getslist(id){
        $("#reply").datagrid({
              url : '${path}' + '/msg/remindList',
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
		          id:id	              
	           },
	           columns:[[
	              {
					width : '100',
					title : '接收人',
					field : 'user',
					halign : 'center',
					sortable : false ,
					formatter:function(value,row,index){					
					   return value.name;
					}			
				},
	              {
					width : '100',
					title : '查看状态',
					field : 'hasRead',
					halign : 'center',
					sortable : false ,
					formatter:function(value,row,index){	
					  if(value=="0"){
					      return "未读";
					  };
					  if(value=="1"){
					      return "已读";
					  };					  
					}
				},
	              {
					width : '150',
					title : '查看时间',
					field : 'readTime',
					halign : 'center',
					sortable : false ,
					formatter:function(value,row,index){					
					   if(value==null){
					      return "待查看";
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
               alert("请选择发送人");
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
            var content;
            tb.textbox("enableValidation").textbox("validate");
            
            if(!tb.textbox('isValid')){
               return;
            }else{
               content=tb.textbox('getValue');
            }
            var msgPut={
                content:content,
                geterId:users            
            }
            $.ajax({
               url:'${path}/msg/sentMsg',
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

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="region:'center',title:'消息列表',split:true,collapsible:false" style="width: 800px;height: 90%">
		     <div style="width: 100%"  id="toolbar">
		 
		         <input type="text" class="easyui-textbox" data-options="prompt:'请输入人员名称'" id="searchName" />
		          <a class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="searchFun();">查询</a>
		          <a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
					onclick="addFun();">添加</a>
		     </div>
			  <table id="msglist" class="easyui-datagrid" >
                     
                 </table>  
   </div>
   <div data-options="region:'east',title:'详情表',collapsible:false" style="width: 360px;height: 90%">
         <table id="reply" class="easyui-datagrid">
            
        </table>          
    </div> 
    <div  id="msgdialog" class="easyui-dialog"   data-options="closed:true,
						                                        title : '添加消息', 
						                                        width : 650,
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
                   <div data-options="region:'west',title:'选择发送人',split:true,collapsible:false" style="width:300px;">
                         <div  class="easyui-panel" style="padding:5px">
                             <ul id ="treeg" class="easyui-tree" data-options="url:'${path }/organization/ptree',method:'get',animate:true,lines:true,
                             cascadeCheck:false, checkbox:true,formatter:function(node){ return node.name;},cascadeCheck:false,onlyLeafCheck:true,
                             onClick:checkFun,onDblClick:doublClick
                              "></ul> 
                        </div>                  
                   </div>   
                   <div data-options="region:'center',title:'填写消息'" style="padding:5px;">
                       <input id="msgcon" name="content" class="easyui-textbox" data-options="multiline:true,fit:true,prompt:'请输入消息内容(限50字)',required:true,validType:'maxLength[50]'" />
                   </div>   
              </div> 
    </div>
</body>
</html>