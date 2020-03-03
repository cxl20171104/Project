<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var cxl_cpq;
$(function(){
        cxl_cpq =	$('#228').datagrid({
    	toolbar:'#_cpq',
    	url : '',
        fit : true,
        idField : 'id',
        fitColumns : true,
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        selectOnCheck : false,
        checkOnSelect : false,
        queryParams: {type:'安管呈批签摸版'},
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
        	width : '400',
          	formatter: function(value,row,index){    
				return '<a id="btn" href="#"   onclick="downLoad('+index+')">'+value+'</a>';            			
        }
        },{
        	title:'删除',
        	field:'aaa',
        	width : '100',
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
});

function load(){
	cxl_cpq.datagrid('load','${path}/ags_tools/reportList_cpq');
}
function addFuck(){
	console.log("添加附件");
	   parent.$.modalDialog({
           title : '添加文件',
           width : 500,
           height : 200,
           href : "${path}/acc/addAccDialog_cpq",
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
</script>

<table id="228" data-options="fit:true,border:false"></table>   
 <div id="_cpq" style="height:auto;width:100%;">
                    <a  class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addFuck();" >添加</a>
</div>
  
    
    
    
