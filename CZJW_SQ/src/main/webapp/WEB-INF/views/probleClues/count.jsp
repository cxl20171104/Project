<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>Insert title here</title>
<script  type="text/javascript">
$(function(){
	//上来加载
	var start = 'http://156.4.177.28:18080/smartbi/vision/openresource.jsp?resid=I402896470163380d380de36e0163387b89ee0414';
document.getElementById('tj').setAttribute('src', start);
});

function getTj(){
		var url = 'http://156.4.177.28:18080/smartbi/vision/openresource.jsp?resid=';
		var resid;
		var name = $("#js").combobox('getValue');
		var xs=document.getElementById('chart');
		var tj=document.getElementById('tj');
			if(name==1){
				resid=url+'I402896470163380d380de36e0163387b89ee0414';
				tj.setAttribute('src', resid);
			}
			if(name==2){
				resid=url+'I402896470163380d380de36e01633857e30b02c0';
				tj.setAttribute('src', resid);
			}
			if(name==3){
				resid=url+'I402896470163380d380de36e01633879436f0403';
				tj.setAttribute('src', resid);
			}
			if(name==4){
				resid=url+'I402896470163380d380de36e0163387cf83c0425';
				tj.setAttribute('src', resid);
			}
			if(name==5){
				resid=url+'I402896470163380d380de36e016338623a04038a';
				tj.setAttribute('src', resid);
			}
			if(name==6){
				resid=url+'I402896470163380d380de36e0163385edb0c0377';
				tj.setAttribute('src', resid);
			}
			/* if(name==7){
				resid=url+'I4028fdf7015f8fd08fd078c6015f90bdb0f1017b';
				tj.setAttribute('src', resid);
			}
			if(name==8){
				resid=url+'I4028fdf7015f8fd08fd078c6015f912ba86804c2';
				tj.setAttribute('src', resid);
			}
			if(name==9){
				resid=url+'I4028fdf7015f8fd08fd078c6015f918651060858';
				tj.setAttribute('src', resid);
			}
			if(name==10){
				resid=url+'I4028fdf7015f8fd08fd078c6015f918e8b1608dd';
				tj.setAttribute('src', resid);
			}
			if(name==11){
				resid=url+'I4028fdf7015f8fd08fd078c6015f918a5846088b';
				tj.setAttribute('src', resid);
			}
			if(name==12){
				resid=url+'I4028fdf7015f8fd08fd078c6015f91947e6f0962';
				tj.setAttribute('src', resid);
			}
			if(name==13){
				resid=url+'I4028fdf7015f8fd08fd078c6015f91972f3f099f';
				tj.setAttribute('src', resid);
			}
			if(name==14){
				resid=url+'I4028fdf7015f8fd08fd078c6015f9191f9330912';
				tj.setAttribute('src', resid);
			} */
			
}


</script>
<style>
li{
padding-top:5%
}
.demo_line_01{
	margin-top:20px;
	margin-left:20px;
    letter-spacing: -1px;
    color: #ddd;
}
.demo_line_01 span{
    letter-spacing: 0;
    color: #222;
    margin:0 20px;
}
.demo_line_02{
	margin-top:40px;
	margin-left:20px;
    letter-spacing: -1px;
    color: #ddd;
}
.demo_line_02 span{
    letter-spacing: 0;
    color: #222;
    margin:0 20px;
}
</style>
</head>
<body class="easyui-layout">   
    <div data-options="region:'north',title:'统计类别',split:true" style="width:300px;height:70px;margin-right:15px;border-right:1px solid #ccc;">
	     <form style="margin-top: 10px;">
			    <select style="margin:10px 0 0" id="js" class="easyui-combobox" name="js" data-options="editable:false,width:'15%'">   
			    <c:if test="${tb=='counts' }">
			    <option value="1">线索来源</option>
			    <option value="2">职级</option>
			    <option value="3">问题属地</option>
			    <option value="4">专项行动</option>
			    <option value="5">处置方式</option>
			    <option value="6">处置单位</option>
			    </c:if>
			   <%--  <c:if test="${tb=='counts2' }">
			    <option value="2">党纪处分</option> 
			    <option value="3">政纪处分</option>   
			    <option value="4">组织处理</option> 
			    <option value="5">移交司法</option>
			    <option value="6">其他处理</option>
				</c:if>
				<c:if test="${tb=='counts3' }">
				<option value="8">线索状态</option>
			    <option value="9">处置单位</option>
			    <option value="10">督办单位</option>
			    <option value="11">处置方式</option>				
				</c:if>   --%>
			    </select>  
		    	 <a style=""  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="getTj();" >图表统计</a>
		    
		   
	     </form>
    </div>   
    <div data-options="region:'center',title:'统计结果'" style="padding:5px;background:#e7effe;">
      <div id="chart" style="width: 100%;height:100%;" >
      		<iframe id="tj" frameborder="0" src="" style="width: 100%;height:100%;"></iframe>
      </div>
    </div>   
</body>
</html>