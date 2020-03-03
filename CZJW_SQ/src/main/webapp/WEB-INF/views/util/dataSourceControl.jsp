<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs2.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:300px;}
</style>
<script type="text/javascript">


  function save(){
	    var obj = $('#probleCluesOnexclEditFrom').serializeObject();
	    var str = new Array();  
	    str = obj.a;
		if ($('#probleCluesOnexclEditFrom').form('validate')) {
			$('#probleCluesOnexclEditFrom').form(
					'submit',
					{
						url :'${path}/dataSource/save',
						onSubmit: function(param){    
					        param.choice = str;    
					    }, 
						success:function(data){    
							$.messager.alert('我的消息',data.msg,'info');
						} 
					});
		}
  }
  $(function(){
	  var choice=$("#dataSourceControl").val();
	  $("input[name='a']").each(function(){
		  console.log($(this).val());
		  if(choice.indexOf(","+$(this).val()+",")>=0){
			  $(this).attr("checked","checked"); 
		  }
	  });
  });
</script>


</head>
<body>
<input id="dataSourceControl" value="${dataSourceControl}">
<form id="probleCluesOnexclEditFrom" method="post" style="width:'100%';height: '100%'">
   <table  class="cxl">
				     <tr>
					  <td colspan="2">各县数据库</td>
					</tr>
					<tr>
	    				<td><input  type="checkbox" id="rq" name="a" value="1" style="margin-right: 5px;"/>任丘</td>
						<td><input  type="checkbox" id="hh" name="a" value="2" style="margin-right: 5px;"/>黄骅市</td>
	    			</tr>
					<tr>
	    				<td><input  type="checkbox" id="hj" name="a" value="3" style="margin-right: 5px;"/>河间市</td>
		    			<td><input  type="checkbox" id="bt" name="a" value="4" style="margin-right: 5px;"/>泊头市</td>
		    		</tr>
					<tr>
		    			 <td><input type="checkbox" id="cx" name="a" value="5" style="margin-right: 5px;"/>沧县</td>		    		
					     <td><input type="checkbox" id="qx" name="a" value="6" style="margin-right: 5px;"/>青县</td>
					</tr>
		    		<tr>
					<!-- 新增所属领域 -->
					   <td><input type="checkbox" id="xx" name="a" value="7" style="margin-right: 5px;"/>献县</td>
	    		     
	    			   <td><input type="checkbox" id="sn" name="a" value="8" style="margin-right: 5px;"/>肃宁县</td>
	    		   </tr>
	    		   
	    		   <tr>
					<!-- 线索状态 -->
					   <td><input type="checkbox" id="ys" name="a" value="9" style="margin-right: 5px;"/>盐山县</td>
	    			   <td><input type="checkbox" id="hx" name="a" value="10" style="margin-right: 5px;"/>海兴县</td>
	    		   </tr>
					<tr>
		    			<td><input  type="checkbox" id="np" name="a" value="12" style="margin-right: 5px;"/>南皮县</td>
		    			<td><input  type="checkbox" id="dg" name="a" value="13" style="margin-right: 5px;"/>东光县</td>
		    		</tr>
		    		<tr>
		    		     <td><input  type="checkbox" id="wq" name="a" value="14" style="margin-right: 5px;"/>吴桥县</td>
		    		     <td><input  type="checkbox" id="yhq" name="a" value="15" style="margin-right: 5px;"/>运河区</td>
		    		</tr>
                    <tr>
		    		     <td><input  type="checkbox" id="xhq" name="a" value="16" style="margin-right: 5px;"/>新华区</td>
		    		     <td><input  type="checkbox" id="hhg" name="a" value="17" style="margin-right: 5px;"/>渤海新区</td>
		    		</tr>
		    		 <tr>
		    		     <td><input  type="checkbox" id="kfq" name="a" value="18" style="margin-right: 5px;"/>沧州开发区</td>
		    		     <td><input  type="checkbox" id="gxq" name="a" value="19" style="margin-right: 5px;"/>高新区</td>
		    		</tr>
		    		 <tr>
					   <td><input type="checkbox" id="mc" name="a" value="11" style="margin-right: 5px;"/>孟村县</td>
					   <td><button onclick="save();">保存配置信息</button></td>
	    		     </tr>
      </table>


</form>

</body>
</html>

