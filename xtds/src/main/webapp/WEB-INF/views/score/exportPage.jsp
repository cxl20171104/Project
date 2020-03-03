<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>积分查询</title>
<style>
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:300px;}
</style>
<script type="text/javascript">
     function organChang(newValue, oldValue){
          $("#users").combobox('clear'); 
          if(newValue!=null){
             var url='${path}' + '/organization/organUsers?id='+newValue;
            $("#users").combobox('reload',url);
          }else{
             $("#users").combobox('reload'); 
          }
         
     }
     
         
     function exp(){
    	 $("#content").table2excel({
		        exclude: ".noExl",
		        name: "Excel Document Name",
		        filename: "积分统计"+new Date().toISOString().replace(/[\-\:\.]/g, ""),
		        fileext: ".xls",
		        exclude_img: true,
		        exclude_links: true,
		        exclude_inputs: true
		    });
     } 
         
     var form;
     $(function(){
    	 form=$('#toolbar').form({
             url : '${path }/score/tj',
             dataType:'json',
             onSubmit : function() {
                 progressLoad();
                 var isValid = $(this).form('validate');
                 if (!isValid) {
                     progressClose();
                 }
                 return isValid;
             },
             success : function(result) {
            	 progressClose();
               
                 
            	 $("#tbody").html("");
                 result=JSON.parse(result);
                 var data=result.obj;
                 console.log(data);
                 var html="";
                 for(var i in data) {

                     console.log(i,":",data[i]);
                     var name=i;
                     var total;
                     html+='<tr><td>'+name+'</td>';
                     for(var y=0;y<=5;y++){
                     var scoreValue="";
                     for(var x=0;x<data[i].length;x++ ){
                    	 var type=data[i][x]['type'];
                    	 if(y==type){
                    		 scoreValue=data[i][x]['scores']; 
                    	 }
                    	 total=data[i][x]['scorevalue'];
                     } 
                     
                     
                     if(scoreValue){
                    	 html+='<td>'+scoreValue+'</td>';
                    	 
                     }else{
                    	 html+='<td>0</td>';
                     }
                     
                     }
                     
                     html+= '<td>'+total+'</td>';
                  
                         
                 
                     
                 }
                 $("#tbody").append(html);
             }
         }); 
     });
     function  searchFun(){
        form.form('submit');
         
     }
     function outFun(){
          var selctedORgan=$("#organ").combotree('tree').tree('getSelected');
          var organ;
        if(selctedORgan){
           organ=selctedORgan.id;
        } 
          var  type=$("#type").combobox("getValue");
           var users=$("#users").combobox("getValue");
           var sort=dataGrid.datagrid("options").sortName;
           var order=dataGrid.datagrid("options").sortOrder;
          var form=$("<form></form>");
             form.attr("style","dispaly:none");
             form.attr("targer"," ");
             form.attr("action",'${path}' + '/score/downfile');
             var input=$("<input>");
		     input.attr("type","hidden");
		     input.attr("name","type");
		     input.attr("value",type);
             var input1=$("<input>");
		     input1.attr("type","hidden");
		     input1.attr("name","organ");
		     input1.attr("value",organ);
             var input2=$("<input>");
		     input2.attr("type","hidden");
		     input2.attr("name","users");
		     input2.attr("value",users);		     
             var input3=$("<input>");
		     input3.attr("type","hidden");
		     input3.attr("name","sort");
		     input3.attr("value",sort);		     
             var input4=$("<input>");
		     input4.attr("type","hidden");
		     input4.attr("name","order");
		     input4.attr("value",order);		     
             var input5=$("<input>");
		     input5.attr("type","hidden");
		     input5.attr("name","year");
		     input5.attr("value",year);		     
		     $("body").append(form);
		     form.append(input);
		     form.append(input1);
		     form.append(input2);
		     form.append(input3);
		     form.append(input4);
		     form.append(input5);
		     form.submit();
     }
       var dataGrid;
 
  
   
  
   function opendialog(value){
        $('#img').attr("src", '${path}/files/'+value); 
        $('#dd').dialog('open');
   }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
      <div data-options="region:'north',iconCls:'icon-reload',title:'积分查询页',split:false,collapsible:false" style="width:500px;">
        <form id="toolbar" method="post">
                    <table>
                    <tr>
                    <td>
                    <label for="organ">党组织&nbsp;&nbsp;&nbsp;&nbsp;:</label>
                    <input id="organ" class="easyui-combotree"   data-options="valueField:'id',textField:'name',url:'${path}' + '/organization/organlist',prompt:'选择党组织',width:300,
		                    onChange:organChang,prompt:'未选择'" />   
                    </td>
                    <td>
                    <label for="users">党员个人:</label>
                    <input id="users" class="easyui-combobox" name="users"  data-options="valueField:'id',textField:'name',url:'${path}' + '/user/getAll',prompt:'未选择',queryParams:{level:'${level}',oid:'${oid}'},width:200,multiple:true" />  
                    </td>
                    <td>
                     <label>开始时间：</label>
                     <input name="startTime" type="text" class="easyui-datebox" data-options="width:'40%',required:false,editable:false" value=""></input>
                     <label>结束时间：</label>
                     <input name="endTime" type="text" class="easyui-datebox" data-options="width:'40%',required:false,editable:false" value=""></input>
                     </td>
                     <td>
		             <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();">查询</a>
					 <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="exp('content');">导出</a>	
                    </td>
                    </tr>
                    </table>
		</form> 
		        
		
      
      </div>   
      <div data-options="region:'center',title:'统计详情页'">
             <table id="content"  class="cxl">
                    <thead><tr><td>姓名</td><td>查看文章</td><td>文章点赞</td><td>文章评论</td><td>评论留言</td><td>发布文章</td><td>用户签到</td><td>总分</td></tr></thead>
                    <tbody id="tbody"></tbody>
                   
             </table>
          
      
      </div>
      <div id="dd" class="easyui-dialog" title="图片显示" style="width:500px;height:400px;"   
        data-options="modal:true,collapsible:false,minimizable:false,maximizable:false,border:false,closed:true"> 
        <img alt="" src="" id="img" style="width:100%;height: 100%">
      </div>     
 
</body>
</html>