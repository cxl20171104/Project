<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>二维码</title>
<style type="text/css">
</style>
<script type="text/javascript">
       var dataGrid;
       var organID="${oid}";
   $(function(){
         dataGrid = $('#qrcodelist').datagrid(
						{
							url : '${path}' + '/qrcode/qrcodelist',
							fit : true,
							fitColumns : true,
							striped : true,
							rownumbers : true,
							pagination : true,
							singleSelect : true,
							selectOnCheck : false,
							checkOnSelect : false,
							border : false,
							queryParams: {
								organid:organID
							},
							toolbar : '#toolbar',
							idField : 'id',
							columns : [ [
									{   width:  '100',
										title : '创建人',
										field : 'name',
										halign : 'center',
										sortable : false
									},
									{
										width : '280',
										title : '活动名称',
										field : 'info',
										halign : 'center',
										sortable : false
								
									},
									{
										width : '80',
										title :  '类型',
										field : 'type',
										halign : 'center',
										sortable : false,
										formatter:function(value,row,index){
										    return value;
										}
									},
									{
										width : '120',
										title : '创建时间',
										field : 'ctime',
										halign : 'center',
										sortable : false,
										formatter:function(value,row,index){
										return value;
										}
								
									},
									{
										width : '150',
										title : '举办部门',
										field : 'oname',
										halign : 'center',
										sortable : false,
										formatter:function(value,row,index){
										return value;
										}
								
									},
									{
										width : '150',
										title : '操作',
										field : 'id',
										halign : 'center',
										formatter:function(value,row,index){
										      
										
										   return '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="loadqr(\''+value+'\',\''+row.organid+'\');" >下载二维码</a>'
										   +'| |'+ '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="addputer(\''+value+'\',\''+row.info+'\',\''+row.dictid+'\');" >添加签到人</a>'
										   +'| |'+ '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="getslist(\''+value+'\');" >签到详情</a>'
										   +'| |'+ '<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:false,iconCls:\'icon-search\'" onclick="getOutlist(\''+value+'\',\''+row.info+'\');" >导出结果</a>';
										}
									} ] ],
							onLoadSuccess : function(data) {
								
							   
							},
							loadFilter : function(data) {
								console.log(data);
								if (data.obj) {
									return data.obj;
								} else {
									return data;
								}
							}
						});
      
   });
 
   function searchFun(){
	   var organid="${oid}";
	   if($("#organ")[0]){
		   organid=$("#organ").combotree('getValue');
		   if(organid){
			   
		   }else{
			   organid=organID; 
		   }
	   };
       dataGrid.datagrid("load",{info: $("#searchName").val(),
                                 type:$("#qtype").combobox('getValue'),
                                 organid:organid
       } ); 
   };
   function printImg(url){
         url=url.replace(/%5C/g,"/");
         var newstr = '<div style="margin-left:30%;margin-top:40%"><img style="width:300px;height:300px" src="${path}/files/'+url+'"></div>'
         printWindow = window.open();
      　       printWindow.document.write(newstr);
         printWindow.print();
         printWindow.close();    
      　　  return;
   }
   function loadqr(value,organid){
	   alert(organid);
     var form=$("<form></form>");
     form.attr("style","dispaly:none");
     form.attr("targer"," ");
     form.attr("action",'${path}' + '/qrcode/downfile');
     var input=$("<input>");
     input.attr("type","hidden");
     input.attr("name","id");
     input.attr("value",value);
     var organ=$("<input>");
     input.attr("type","hidden");
     input.attr("name","organid");
     input.attr("value",organid);
     $("body").append(form);
     form.append(input);
     form.append(organ);
     form.submit();
   }
   function getOutlist(value,info){
	   var form=$("<form></form>");
	     form.attr("style","dispaly:none");
	     form.attr("targer"," ");
	     form.attr("action",'${path}' + '/qrcode/outResult');
	     var input=$("<input>");
	     input.attr("type","hidden");
	     input.attr("name","id");
	     input.attr("value",value);
	     var input2=$("<input>");
	     input2.attr("type","hidden");
	     input2.attr("name","info");
	     input2.attr("value",info);
	     $("body").append(form);
	     form.append(input);
	     form.append(input2);
	     form.submit();	   
   }
   function getslist(qrcodeid){
        $("#qrcodeDo").datagrid({
              url : '${path}' + '/qrcode/qrusers',
                fit : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				selectOnCheck : false,
				checkOnSelect : false,
				border : false,
				sortName : 'ctime',
			    sortOrder : 'asc',
				idField : 'id',
				queryParams: {
		          qrcodeid:qrcodeid	              
	           }       
        })
   }
      function  addFun(){     
      $("#qrcodedialog").dialog("open");
   }
    function putFun(){
               var tb = $("#info");
               var content = $("#content");
                tb.textbox("enableValidation").textbox("validate");
                content.textbox("enableValidation").textbox("validate");
                
            if(!tb.textbox('isValid')){
               return;
            } 
            var info=tb.textbox('getValue');
            var content=content.textbox('getValue');
            var organid="${oid}";            
            if($("#organid")[0]){
         	   organid=$("#organid").combotree('getValue');
            };
            var type=$("#type").combobox('getValue');
            var etime=$("#etime").datetimebox('getValue');
            var qrcode={
                info:info,
                type:type,
                organid:organid,
                etime:etime,
                content:content
            }
            $.ajax({
               url:'${path}/qrcode/addQrcode',
                data:qrcode,
                dataType:'json',
                type:"post",
                success:function(data){
                   if(data.success){ 
                         $("#info").textbox('clear');
                        $("#qrcodedialog").dialog("close");
                         dataGrid.datagrid("load");
                   }else{
                	   $.messager.alert("提示","添加失败");
                   }
                }
            })                      
   }
   function outFun(){
        $("#info").textbox('clear');
        $("#content").textbox('clear');
       $("#qrcodedialog").dialog("close");  
   };
   function addputer(value,info,dictid){
	   $("#actid").val(value);
	   $("#ainfo").val(info);
	   $("#dictid").val(dictid);
	   $("#users").dialog("open");
   };
   function checkFun(node){ 
       var tt = $("#treeg"); 
           tt.tree('expand',node.target);    
      var childNode = tt.tree("getChildren",node.target);
                for(var i= 0;i<childNode.length;i++)
                {                          
                    tt.tree("check", childNode[i].target);
                }           
   };  
   function doublClick(node){
    var tt = $("#treeg"); 
    var childNode = tt.tree("getChildren",node.target);
              for(var i= 0;i<childNode.length;i++)
              {
                  tt.tree("uncheck", childNode[i].target);
              } 
   };
   function saveFun(){
       var tt = $("#treeg"); 
       var nodes = tt.tree('getChecked');
       var users="";
    if(nodes.length==0){
       alert("请选择签到人");
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
	var actid=$("#actid").val();
	var info=$("#ainfo").val();
	var dictid=$("#dictid").val();
    $.ajax({
        url:'${path}/qrcode/addusers',
         data:{ users:users,
        	 qrcodeid:actid,
        	 info:info,
        	 dictid:dictid
         },
         dataType:'json',
         type:"post",
         success:function(data){
            if(data.success){           
                 $("#actid").val('');
                 $("#ainfo").val('');
                 $("#dictid").val('');
                 tt.tree('reload');
                 $("#users").dialog("close");
                 alert(data.msg);
            }else{
            	 alert(data.msg);  
            }
         }
     }) 
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
 
   <div data-options="region:'center',title:'活动列表',split:true,collapsible:false"  style="width: 800px;height: 90%">
		     <div style="width: 100%"  id="toolbar">
		 
		         <input type="text" class="easyui-textbox" data-options="prompt:'请输入活动名称'" id="searchName" />
		         <label  for="qtype">类型选择</label>
		         <input id="qtype" class="easyui-combobox"    
                                   data-options="valueField:'id',textField:'name',editable:false,panelHeight:100,url:'${path}/qrcode/qrcodetype'" /> 
                 <shiro:hasPermission name="/qrcode/chooseOrgan">
                   <label for="organ">举办部门</label>   
		            <input id="organ" class="easyui-combotree" name="organ"  data-options="url:'${path}' + '/organization/organlist2',width:300,queryParams:{oid:${oid}},
		                    prompt:'未选择'
		                     " />   
		         </shiro:hasPermission> 
		          <a class="easyui-linkbutton" data-options="iconCls:'icon-search'"
					onclick="searchFun();">查询</a>
				    <a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
					onclick="addFun();">添加活动</a>
		     </div>
			  <table id="qrcodelist" class="easyui-datagrid" >
                     
             </table> 
                
   </div>
   <div data-options="region:'east',title:'扫码详情表',split:true,collapsible:false" style="width: 300px;height: 90%">
        <table id="qrcodeDo" class="easyui-datagrid">
             <thead>   
		        <tr>   
		            <th data-options="field:'name',width:120, halign:'center'">扫码人姓名</th>   
		            <th data-options="field:'ctime',width:120,halign:'center',formatter:function(value,row,index){
										return value;
										}">扫码时间</th>   
		        </tr>   
		    </thead>
        </table>   
   </div>
    <div  id="qrcodedialog" class="easyui-dialog"   data-options="closed:true,
						                                        title : '添加活动', 
						                                        width : 500,
					                                            height :320,
					                                             fitColumns : true,
					                                             resizable:true,
					                                             model:true,
															     buttons:'#dialogButton'" >
				<div id="dialogButton">
					<a href="#" class="easyui-linkbutton" onclick="putFun()">保存</a>
					<a href="#" class="easyui-linkbutton" onclick="outFun()">取消</a>
				</div>
				<div  class="easyui-layout" style="width:100%;height:100%;">    
                   <div id="centerdiv" data-options="region:'center',title:'二维码详情',split:true" style="width:400px;">
                             <span>选择类型</span>
							<input id="type" class="easyui-combobox" name="type"   
                                   data-options="valueField:'id',textField:'name',value:'16',editable:false,panelHeight:100,url:'${path}/qrcode/qrcodetype'" /> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>有效截止日期</span> 
							<input id="etime" name="etime" type="text" class="easyui-datetimebox" data-options="editable:false"></input></br></br>
							<shiro:hasPermission name="/qrcode/chooseOrgan">
								 <label for="organ">举办部门</label>   
			                     <input id="organid" class="easyui-combotree" name="organid" style="width:350px;"  data-options="url:'${path}' + '/organization/organlist2',
			                     queryParams:{oid:${oid}}
			                      " /> </br></br>
		                    </shiro:hasPermission>
							<span>活动标题</span> 
							<input id="info" name="info"  class="easyui-textbox" data-options="width:380,required:true,prompt:'请输入活动标题(限18字) 例如:第三届年党员交流会议',validType:'maxLength[18]'" /> </br> </br>          
							<input id="content" name="content"  class="easyui-textbox" data-options="multiline:true,fit:true,required:true,prompt:'请输入活动简介'" />            
                   </div>   
              </div> 
    </div>
     <div id="users" class="easyui-dialog"  data-options="closed:true,
                                                         title:'添加签到人',
                                                         width:300,height:450,
                                                         fitColumns : true,
					                                             resizable:true,
					                                             model:true,
					                                             buttons:'#addButtons'
                                                         ">
         <div id="addButtons">
					<a href="#" class="easyui-linkbutton" onclick="saveFun()">保存</a>
					<a href="#" class="easyui-linkbutton" onclick="outFun()">取消</a>
		 </div>
          <div  class="easyui-layout" style="width:100%;height:100%;"> 
                   <div data-options="region:'center',title:'',split:true">
                     <input id="actid" name="actid" type="hidden"/>
                     <input id="ainfo" name="info" type="hidden"/>
                     <input id="dictid" name="dictid" type="hidden"/>
                     <ul id ="treeg" class="easyui-tree" data-options="url:'${path }/organization/ptree',method:'get',animate:true,lines:true,
                             cascadeCheck:false, checkbox:true,formatter:function(node){ return node.name;},cascadeCheck:false,onlyLeafCheck:true,
                             onClick:checkFun,onDblClick:doublClick
                       "></ul> 
                   </div>                   
          </div>
     </div>
</body>
</html>