<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>首页理念</title>
</head>
<body>
<script type="text/javascript">
   $(function(){
		  $('#dg').datagrid({    
		    url:'${path}/cept/getall', 
		    fit : true,
			fitColumns : true,
		    rownumbers : true,
			pagination : true,
			striped : true,
		    toolbar: [{
		        iconCls: 'icon-add',
		        text:'添加',
		        handler: function(){add()}
	          }],		       
		    columns:[[    
		        {field:'content',title:'内容',width:500},    
		        {field:'useing',title:'状态',width:100,formatter:function(value,row,index){
		            if(value=="0"){
		                return "未使用";
		            };
		            if(value=="1"){
		                return "使用中";
		            };
		        }},    
		        {field:'id',title:'操作',width:100,formatter:function(value,row,index){
		            return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-edit\'" onclick="editFun(\''+value+'\',\''+row.content+'\',\''+row.useing+'\');" >编辑</a>';
		        }}    
		    ]]    
		  });
   });
   function editFun(id,content,useing){
       $("#id").val(id);
       $("#content").textbox("setValue",content);
       $("#useing").combobox("setValue",useing);
       $("#msgdialog").dialog("open");
  }; 
  function outFun(){  
    $('#ff').form("clear");
    $("#msgdialog").dialog("close");  
  };
  function add(){
     $('#ff').form("clear");
     $("#msgdialog").dialog("open");  
  };
  function putFun(){
      $('#ff').form("enableValidation");
      if( $('#ff').form("validate")){
           $('#ff').form('submit', {    
			    url:"${path}/cept/addOrUpdate",    
			    onSubmit: function(){ 
			          return true;      
			    },    
			    success:function(data){   
			          $('#dg').datagrid("load",{"name":"00"});
			          outFun();
			    }   
           });
      
      }else{
          alert("0000");
      }
  
  };
</script>
  
<div id="dg">
      
    
</div>
<div  id="msgdialog" class="easyui-dialog"   data-options="closed:true,
						                                        title : '理念编辑/添加', 
						                                        width : 400,
					                                            height : 300,
					                                            fitColumns : true,
					                                             resizable:true,
					                                             model:true,
															     buttons:'#dialogButton'" >
				<div id="dialogButton">
					<a href="#" class="easyui-linkbutton" onclick="putFun()">保存</a>
					<a href="#" class="easyui-linkbutton" onclick="outFun()">取消</a>
				</div>
				<div id="addmsg" class="easyui-layout" style="width:100%;height:100%;">  
                   <div data-options="region:'center',title:''" style="padding:5px;">
                        <form id="ff" method="post">  
                             <input id="id" name="id" style="display: none"/>
                        <div> 
                         <label>状态：</label>
                        <select id="useing" class="easyui-combobox" name="useing" style="width:200px;required:true">   
						    <option selected="selected"  value="1">使用</option>   
						    <option value="0">未使用</option>   
						</select>  
						</div> 
						<div><label>理念内容：</label>
						     <input id="content" name="content" class="easyui-textbox" data-options="multiline:true,fit:true,prompt:'请输入理念',required:true" />
						</div>
                      
                       </form>
                   </div>   
              </div> 
    </div>
</body>
</html>