<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<!DOCTYPE html>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/problelist.css" />
<title>用户管理</title>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
         <div data-options="region:'center',border:false" id="div_table">
              <table id="dataGrid" data-options="fit:true,border:false"></table>
              <div id="tb" style="height:auto;width:100%;">
                    <a  class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFuck();" >添加</a>
                    <span>分类：</span>
                    <select id="tt"     style="width:300px;height:45px;" onChange="fielter(this.options[this.options.selectedIndex].value);">
								<option value="">全部</option>
								<c:forEach var="act" items="${type}">
								        <c:if test='${act.content!="" }'>
								            <option value="${act.content }">${act.content}</option>
								        </c:if>
										
								</c:forEach>
							</select>
							<input class="easyui-searchbox" style="width:300px" id="ss" />
              </div>
        </div>
        <script type="text/javascript">
        function fielter(content){
        	$('#dataGrid').datagrid('reload',{"content":content,type:'报告模板'});
        }
        var cxl;
        $(function() {
        	//将pageName赋给全局变量 version2Name  以后  在任何界面可以使用 parent.version2Name获取
    	    
            cxl =	$('#dataGrid').datagrid({
            	 toolbar:'#tb',
            	  url : '${path}/acc/reportList',
                fit : true,
                idField : 'id',
                fitColumns : true,
                striped : true,
                rownumbers : true,
                pagination : true,
                singleSelect : true,
                selectOnCheck : false,
                checkOnSelect : false,
                queryParams: {type:'报告模板'},
                border : false,
                sortOrder : 'desc',
                columns : [ [ {
                    title : 'id',
                    field : 'id',
                    hidden :true 
                },{
                	title:'上传时间',
                	field:'uploadate',
                	width : '100',
                  	formatter: function(value,row,index){    
						return value.substring(0,10);         			
		        }
                },{
                	title:'标题',
                	field:'title',
                	width : '200',
                  	formatter: function(value,row,index){    
						return '<a id="btn" href="#"   onclick="downLoad('+index+')">'+value+'</a>';            			
		        }
                },
                {
                	title:'备注',
                	field:'name',
                	width : '100'
                },{
                	title:'编辑',
                	field:'abc',
                	width : '80',
                  	formatter: function(value,row,index){    
						return '<button id="btn" href="#"   onclick="edit('+index+')">编辑</button>';            			
		        }
                },{
                	title:'删除',
                	field:'aaa',
                	width : '80',
                  	formatter: function(value,row,index){    
						return '<button  href="#"   onclick="remove('+row.id+')">删除</button>';            			
		        }
                }] ],
                loadFilter: function(data){
                	console.log(data);
    				if (data.obj){
    					return data.obj;
    				} else {
    					return data;
    				}
    				
    			}
            });
            $('#ss').searchbox({ 
            	searcher:function(value){ 
            		cxl.datagrid('reload',{
            			type:'报告模板',title:value
            		});
            	}
            }); 
        });
        function edit(index){
        	  var rows = $('#dataGrid').datagrid('getRows');
        	  var row = rows[index];
        	  var id = row.id;
        	  parent.$.modalDialog({
                  title : '编辑文件',
                  width : 500,
                  height : 200,
                  href : "${path}/acc/editAccDialog?id="+id,
                  buttons : [ {
                      text : '确定',
                      iconCls:'icon-ok',
                      handler : function() {
                   	   parent.$.modalDialog.openner_dataGrid = cxl;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                          var f = parent.$.modalDialog.handler.find('#my_form');
                          f.submit();
                         
                         
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
        function addFuck(){
        	console.log("添加附件");
        	   parent.$.modalDialog({
                   title : '添加文件',
                   width : 500,
                   height : 200,
                   href : "${path}/acc/addAccDialog",
                   buttons : [ {
                       text : '确定',
                       iconCls:'icon-ok',
                       handler : function() {
                    	   parent.$.modalDialog.openner_dataGrid = cxl;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                           var f = parent.$.modalDialog.handler.find('#my_form');
                           f.submit();
                          
                          
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
        
        function remove(id){
        	if(id){
        		$.messager.confirm('确认对话框', '您确认要删除吗？', function(r){
        			if(r){
        				$.ajax({
        					url:"${path}/acc/remove",
        					data:{id:id},
        					dataType:'json',
        					async : true,
        					type:'post',
        					success:function(result){
        						if(result.success){
        							$('#dataGrid').datagrid('reload');
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
        			msg:'请勾选要删除的模板',
        			timeout:3000,
        			showType:'slide'
        		});
        	}
        }
        function downLoad(index){
        	 var row = $('#dataGrid').datagrid('getData').rows[index];
        	downloadFile("${path}/probleClues/accDownload",row.url,row.title);
        	
        }
        function downloadFile(actoinURL,filePath,fileName){  
        	
        	
   	     var form = $("<form>");     
   	    $('body').append(form);    
   	        form.attr('style','display:none');     
   	        form.attr('target','');  
   	        form.attr('method','post');  
   	        form.attr('action',actoinURL);//下载文件的请求路径  
   	          
   	          
   	        var input1 = $('<input>');   
   	        input1.attr('type','hidden');   
   	        input1.attr('name','filePath');   
   	        input1.attr('value',filePath);  
   	        form.append(input1);    
   	        var input2 = $('<input>');   
   	        input2.attr('type','hidden');   
   	        input2.attr('name','fileName');   
   	        input2.attr('value',fileName);  
   	        form.append(input2);  
   	          
   	        form.submit();      
   	      
   	    };
        </script>
</body>