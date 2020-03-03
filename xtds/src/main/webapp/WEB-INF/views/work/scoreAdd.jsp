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

</style>
<script type="text/javascript"
	src="${staticPath }/static/dmuploader.js" charset="utf-8"></script>
<script type="text/javascript">
    var level="${level}";
    var oid="${oid}";
   $(function(){
            $('#tt').tree({    
			    url:'${path}' + '/dict/treegridByDictPid',
			    idField:'id',    
			    treeField:'name',
			     checkbox:true,
			    onlyLeafCheck:true,
			    onCheck: function (node,checked) {
			                var cknodes = $('#tt').tree("getChecked");
			                for (var i = 0; i < cknodes.length; i++) {
			                    if (cknodes[i].id != node.id) {
			                        $('#tt').tree("uncheck", cknodes[i].target);
			                    }
			                }			               
		                    $('#regionTree').tree('check', node.target);
		                    $('#scorevalue').textbox('setValue',node.value);
		                    $('#jfbf').textbox('setValue',node.remark);
		                    $('#scoresource').val(node.id);
		                    $('#error').css("display","none");
                 }
			}); 
   })
	     function organChang(newValue, oldValue){
          $("#users").combobox('clear'); 
          if(newValue!=null){
             var url='${path}' + '/organization/organUsers?id='+newValue;
            $("#users").combobox('reload',url);
          }else{
             $("#users").combobox('reload'); 
          }
         
     }
     function tijiao(){
        $('#ff').form('submit',{    
		    url: '${path}'+'/score/addScoreByDict',    
		    onSubmit: function(){    
		          $('#ff').form("enableValidation");
		         var re= $('#ff').form("validate");
		         var cknodes = $('#tt').tree("getChecked");
		         
		         if(cknodes.length==0){
		            console.log(cknodes.length);
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
                formData.append("upload", document.getElementById("upload").files[0]);   
                $.ajax({
                    url: "${path}/com/upload",
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
                    console.log(data.success);
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
       });     
     
 
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <div data-options="region:'center',title:'积分方式',split:true,collapsible:false"  style="width:25%;height: 90%">
       <table id="tt" class="easyui-tree" style="width:600px;height:400px">
       </table>
   </div>
   <div data-options="region:'east',title:'奖励积分',split:true,collapsible:false" style="width: 70%;height: 90%">
       <form  id="ff" class="easyui-form" method="post" style="margin-top: 20px" data-options="novalidate:true">
                 
                 
                 <shiro:hasPermission name="/score/choseorgan">
                 <div style="margin-top: 20px" >                  
                    <label for="organ" style="margin-left: 20px">党组织&nbsp;&nbsp;&nbsp;&nbsp;:</label>   
		            <input id="organ" class="easyui-combotree" name="organ"  data-options="valueField:'id',textField:'',url:'${path}' + '/organization/organlist',prompt:'选择党组织',width:300,
		                    onChange:organChang
		                     " />  
                 </div>
                 </shiro:hasPermission>
                 <div style="margin-top: 20px" >
                    <label for="users" style="margin-left: 20px">党员个人:</label>   
		            <input id="users" class="easyui-combobox" name="userid"  data-options="valueField:'id',textField:'name',url:'${path}' + '/user/getAll?oid=${oid}&level=${level}',prompt:'选择加分人',required: true,
		            
		            " />                  
                 </div>
                 
                  <div style="margin-top: 20px">
                     <span style="margin-left: 20px"> 积分分值:</span>
                     <input class="easyui-textbox" id="scorevalue"  name="scorevalue" data-options="editable:false,prompt:'左侧所选积分方式分值'">
                     <input type="text" id="scoresource" name="scoresource" style="display: none;">
                    
                  </div>
                  <div style="margin-top: 20px">
                       <span style="margin-left:20px">积分办法:</span>
                       <input class="easyui-textbox" id="jfbf"  name="jfbf" data-options="editable:false,multiline:true,height:60,width:500">                 
                  </div>
                  <div style="margin-top: 20px">
                       <span style="margin-left:20px">描述信息:</span>
                       <input class="easyui-textbox" id="descr"  name="descr" data-options="editable:true,prompt:'填写获得积分的详情',multiline:true,height:60,width:500">                 
                  </div>
                  <div style="margin-top: 20px">
                    
				       
				       <span style="margin-left:20px">实时图片:</span>
					   <input name="uoload" id="upload" type="file" /><input type="hidden" name="asize" id="asize"/><input type="hidden" name="url" id="filepath"/><br>
					   <span id="errorMsg" style="display:none;color:red">请刷新重试</span>
                  </div>
                  <div style="margin-top: 40px" >
                         
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-left: 50px;" onclick="tijiao()">提交</a> 
                        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" style="margin-left: 60px;" onclick="qvXiao()">取消</a> <br>
                         <span id="error" style="display: none; color: red ;margin-left: 50px; margin-top:20px; font-size: large;" >请勾选积分办法项</span><br>       
                  </div>       
       </form>
   
   </div>
</body>
</html>