<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>积分办法</title>
<style type="text/css">
      #dd img{
     width:500px;
     height:450px;   
   }
   
   
</style>
<script type="text/javascript"
	src="${staticPath }/static/dmuploader.js" charset="utf-8"></script>
<script type="text/javascript">
   $(function(){
            $('#tt').tree({    
			    url:'${path}' + '/dict/treegridByDictPid',
			    idField:'id',    
			    treeField:'name',
			    queryParams:{id:'08'},
			    checkbox:true,
			    onlyLeafCheck:true,
			    onCheck: function (node,checked) {
			                var cknodes = $('#tt').tree("getChecked");
			                for (var i = 0; i < cknodes.length; i++) {
			                    if (cknodes[i].id != node.id) {
			                        $('#tt').tree("uncheck", cknodes[i].target);
			                    }
			                };			               
		                    $('#regionTree').tree('check', node.target);
		                    $('#scorevalue').textbox('setValue',node.value);
		                    $('#honorName').textbox('setValue',node.name);
		                    $('#descr').textbox('setValue',node.remark);
		                    $('#scoresource').val(node.id);
		                    $('#error').css("display","none");
                 }
			});
            hoorlist=$("#honorlist").datagrid({
            	url:'${path}'+'/honor/honorlist?type=2',
            	idField:'id',
            	toolbar:'#toobar',
            	fit:true,
            	singleSelect:true,
            	pagination:true,
            	fitColumns:true,
            	sortName:'ctime',
            	sortOrder:'desc',
            	columns:[[    
            	          {field:'userName',title:'获得荣誉部门',width:80,align:'center'},            	          
            	          {field:'honorname',title:'荣誉名称',width:80,align:'center'},    
            	          {field:'dictName',title:'荣誉级别',width:80,align:'center'},
            	          {field:'ctime',title:'时间',width:80,align:'center'},    
            	          {field:'lrrName',title:'录入人',width:80,align:'center'},
            	          {field:'imgurl',title:'荣誉图片',width:80,align:'center',formatter(value,row,index){
            	        	  return  $.formatString(
										'<a href="javascript:void(0)" class="role-easyui-linkbutton-search" data-options="plain:true,iconCls:\'icon-edit\'" onclick="seeImg(\'{0}\',\'{1}\');" >查看</a>',
										row.id,value);
            	          }}
            	          
            	 ]],onLoadSuccess : function(data) {
						$('.role-easyui-linkbutton-search').linkbutton({
							text : '查看',
							plain : true,
							iconCls : 'icon-search'
						});
					}, onSelect:function(rowIndex,rowData){
					 console.log(rowData);
					 	//tree none 返回树形对象。以下的例子显示了如何得到选择的树节点 
					//setText text 设置输入的文本。
					//setValue value 设置组件的值。 
					 $('#organ').combotree('setValue',rowData.organ_id);
			         $('#organ').combotree('setText',rowData.organName);
			         
					 $('#users').combobox('setValue',rowData.userid);
					 $('#users').combobox('setText',rowData.userName);
					 
					 $('#honorname').textbox('setText',rowData.honorname);
					 $('#yearChoose').combobox('setValue',rowData.yearly);
					 $('#isWhat').val(rowData.id);
					 if(rowData.gettime!=""&&rowData.gettime!=""){
					 
					 
					 console.log(rowData.gettime);
					 
					 $('#dd').datebox('setValue',rowData.gettime);
					 }
					 //getRoots none 获取所有根节点，返回节点数组。 
					 //reload target 重新载入树控件数据。 
					 var nodes = $('#tt').tree('getRoots');
					 $('#tt').tree('expand',nodes[0].target);
					//tree   select target 选择一个节点，'target'参数表示节点的DOM对象。 find id 查找指定节点并返回节点对象。
                    //expand target 展开一个节点，'target'参数表示节点的DOM对象。在节点关闭或没有子节点的时候，节点ID的值(名为'id'的参数)将会发送给服务器请求子节点的数据。  
					//getChildren target 获取所有子节点，'target'参数代表节点的DOM对象。
					setTimeout(function(){
					var selectnodes="";
					var nodeChilds=$('#tt').tree('getChildren',nodes[0].target);
					for(var x in  nodeChilds){
					 $('#tt').tree('uncheck',nodeChilds[x].target);
					if(nodeChilds[x].name==rowData.dictName){
					
					selectnodes=nodeChilds[x];
					break;
					}

					}
					
					$('#tt').tree('check',selectnodes.target);
					
					},200);
					
					
				
					
					
					}
            });
            yearFun();
   });
    function seeImg(id,urls){
	     var url=urls.split(",");
	     var len=url.length;
	     opendialog(url,len);
   };
   function opendialog(url,len){
       $('#img').html("");
	   for(var i=0;i<len;i++){
		   var img=$('<img style="width:500px;"></img>');
		   img.attr("src", url[i]==""?"${path}/static/images/nophoto.jpg":'${path}/files/'+url[i]); 
		   $('#img').append(img); 
	   };
       $('#img').dialog('open');
  }
   var hoorlist;
     function tijiao(){
     	console.log('提交');
        var url=""; 
        var isWhat=$('#isWhat').val();
        if(isWhat==""){
        url= '${path}'+'/honor/addHonor'
        }else{
        
        url= '${path}'+'/honor/editHonor'
        }         
        $('#ff').form('submit',{    
		    url: url,    
		    onSubmit: function(){    
		          $('#ff').form("enableValidation");
		         var re= $('#ff').form("validate");
		         var cknodes = $('#tt').tree("getChecked");
		         
		         if(cknodes.length==0){
		            $('#error').css("display","");
		            return false;
		         }
		         return re;
		    },    
		    success:function(data){
		           var data=eval("("+data+")");
		          var msg=data.msg;
                 $.messager.confirm('确认',msg,function(r){    
						    if (r){    
						          $('#tt').tree('reload');
						          $("#ff").form('reset');
						          hoorlist.datagrid("reload");
						    }    
						}); 
		    }    
     });
     }
     	
     function  qvXiao(){
              $('#tt').tree('reload');
			  $("#ff").form('reset'); 
     }
     $(function(){
        $("#upload").change(function () {
        	 var formData = new FormData();
    		 for(var i=0;i<document.getElementById("upload").files.length;i++){
    			 formData.append("upload"+i, document.getElementById("upload").files[i]);   
    		 }  
                $.ajax({
                    url: "${path}/com/uploadmany",
                    type: "POST",
                    dataType:"json",
                    data: formData,
                    /**
                    *必须false才会自动加上正确的Content-Type
                    */
                    contentType: false,
                    /**
                    * 必须false才会避开jQuery对 formdata 的默认处理
                    * XMLHttpRequest会对 formdata 进行正确的处理
                    */
                    processData: false,
                    success: function (data) {
                        if (data.success) {                       
                             $("#filepath").val(data.obj);
                             $("#asize").val(data.msg);
                        }
                       else{	
                           $("#errorMsg").css("display","inline");
                        }
                    },
                    error: function () {
                       $("#errorMsg").css("display","inline");
                    }
                });
            });
            
            
            
            $("#xtds").click(function(e){
            	 console.log(document.getElementById('xtds').classList.contains('cxl'));
            	if(document.getElementById('xtds').classList.contains('cxl')){
            		
            		document.getElementById('bu').style.type='block';
            		document.getElementById('zhi').style.display='block';
            		document.getElementById('xtds').classList.remove('cxl');
            	}else{
            		$('#organ').combotree('setValue',20000);
            		document.getElementById('bu').style.display='none';
            		document.getElementById('zhi').style.display='none';
            		document.getElementById('xtds').classList.add('cxl');
            	}
            	
            	
            });
       });   
     
     function research(){
    	 var organid=$("#organid").combotree("getValue");
    	 hoorlist.datagrid('load',{
    		 organid:organid
    	 });
     }
     function yearFun(){
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
     }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="region:'north',title:'荣誉列表',split:true,collapsible:false" style="width:100%;height:50%">
         <div id="toobar">
            <label for="organid">党组织:</label>   
		    <input id="organid" class="easyui-combotree" name="organid"  data-options="width:250,valueField:'id',textField:'',url:'${path}' + '/organization/organlist3',prompt:'选择支部'" />
                  <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="research();">查询</a>          
         </div>
        <table id="honorlist" ></table>
   </div>
   <div data-options="region:'center',title:'荣誉级别',split:true,collapsible:false"  style="width:25%;height: 50%">
       <table id="tt" class="easyui-tree" style="width:100%;height:100%">
       </table>
   </div>
   <div data-options="region:'east',title:'荣誉评比',split:true,collapsible:false" style="width: 70%;height: 50%">
       <form  id="ff" class="easyui-form" method="post" style="margin-top: 20px" data-options="novalidate:true">
        <input id="isWhat" name="id" type="hidden" value=""/>
                 
                 
                 <table>
                 	<tr>
                 	<td id="zhi"><span>支部选择:</span></td>   
		            <td id="bu"><input id="organ" class="easyui-combotree" name="organ_id"  data-options="width:250,valueField:'id',textField:'',url:'${path}' + '/organization/organlist3',prompt:'选择党组织',required: false,
		                     " />    	
                 		
                 	
                 	
                 	</tr>
                 	<tr>
                 	
                 	 
                 	 <td><span >荣誉年度:</span></td>
                     <td><input id="yearChoose" class="easyui-combobox" name="yearly"/></td>     
                     <td><span >荣誉名称:</span></td>   
		             <td><input id="honorname" class="easyui-textbox" name="honorname"  data-options="required: true,prompt:'输入荣誉名称'" /></td>
		             </td>	
                 	
                 		<td><span>地税荣誉:</span></td>
                 		<td><input type="checkbox" id="xtds"/></td>
                 	</tr>
                 	<tr>
                 	<td><span >嘉奖时间:</span></td>	
                 	<td><input id="dd" type="text" name="gettime" class="easyui-datebox" required="required"></input></td>
                 	<td><span> 积分分值:</span></td>
                 	<td><input class="easyui-textbox" id="scorevalue"  name="scorevalue" data-options="editable:false,prompt:'左侧所选荣誉级别积分值'">
                      <input type="text" id="scoresource" name="dictid" style="display: none;">
                       <input type="text" id="type" name="type" value="2" style="display: none;">
                 	</td>
                 	 <td><span> 荣誉级别:</span></td>
                     <td><input class="easyui-textbox" id="honorName"  name="hName" data-options="editable:false,prompt:'左侧所荣誉级别名称'"></td>
                 		
                 	
                 	</tr>	
                 </table>
                  <div style="margin-top: 20px">
                       <span style="margin-left:20px">荣誉级别描述:</span>
                       <input class="easyui-textbox" id="descr"  name="descr" data-options="editable:false,prompt:'此处显示左侧所选荣誉的描述',multiline:true,height:60,width:500">                 
                  </div>
                  <div style="margin-top: 20px">
				       <span style="margin-left:20px">荣誉图片:</span>
					   <input name="uoload" id="upload" type="file" multiple="multiple" /><input type="hidden" name="asize" id="asize"/><input type="hidden" name="imgurl" id="filepath"/><br>
					   <span id="errorMsg" style="display:none;color:red">请刷新重试</span>
                  </div>
                  <div style="margin-top: 20px" >                    
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-left: 50px;" onclick="tijiao()">提交</a> 
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="margin-left: 60px;" onclick="qvXiao()">取消</a> <br>
                         <span id="error" style="display: none; color: red ;margin-left: 50px; margin-top:20px; font-size: large;" >请勾选荣誉项</span><br>       
                  </div>
                  
       
       </form>
   
   </div>
    <div id="img" class="easyui-dialog" title="图片显示" style="width:500px;height:500px;"   
        data-options="modal:true,collapsible:false,minimizable:false,maximizable:true,border:false,closed:true"> 
       
      </div>  
</body>
</html>