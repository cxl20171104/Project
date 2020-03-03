<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>Insert title here</title>
<!--  <script  type="text/javascript">
 $(function() {
 	 //$("#a1").attr("href","http://156.4.177.28:18080/smartbi/vision/openresource.jsp?resid=I2c9d6581015f51165116c50c015f530c1c6b0d88&user=admin&password=admin"); 
 	 $("#subBtn").trigger("click");
 });

</script>-->
<style>
li{
padding-top:5%
}
</style>
</head>
<body class="easyui-layout">  

    <select id="type"  style="width:300px;height:45px;" >
       <option value="1">纪检监察机关处置反映问题线索情况统计表（上）</option>
       <option value="2">纪检监察机关处置反映问题线索情况统计表（下）</option>
       <option value="3">纪检监察机关处分人员情况统计表</option>
       <option value="4">纪检监察机关立案审查调查情况统计表</option>
    </select>

<button id="subBtn" type="button" onclick="tj();">统计</button>
<br><br>
<div id="aa" style="display: none;width:100%"> <iframe id="iframe1" src="${path}/probleClues/cxl/tjb1" width="1700" height="700"></iframe></div>
<div id="bb" style="display: none"> <iframe id="iframe1" src="${path}/probleClues/cxl/tjb2" width="1700" height="700"></iframe></div>
<div id="cc" style="display: none"> <iframe id="iframe1" src="${path}/probleClues/cxl/tjb3" width="1700" height="700"></iframe></div>
<div id="dd" style="display: none"> <iframe id="iframe1" src="${path}/probleClues/cxl/tjb4" width="1700" height="700"></iframe></div>

<script type="text/javascript">
function tj(){
    var path =path;
	var t=$("#type").val();
    if (t==1){
    	$("#aa").show();
    	$("#bb").hide();
    	$("#cc").hide();
    	$("#dd").hide();
    	//parent.addTab("处置反映问题线索情况统计表（上）","${path}/probleClues/cxl/tjb1");
    }else if(t==2){
    	$("#aa").hide();
    	$("#bb").show();
    	$("#cc").hide();
    	$("#dd").hide();
    	//parent.addTab("处置反映问题线索情况统计表（上）","${path}/probleClues/cxl/tjb2");
    }else if(t==3){
    	$("#aa").hide();
    	$("#cc").show();
    	$("#bb").hide();
    	$("#dd").hide();
    	//parent.addTab("纪检监察机关处分人员情况统计表","${path}/probleClues/cxl/tjb2");
    }else if(t==4){
    	$("#aa").hide();
    	$("#dd").show();
    	$("#bb").hide();
    	$("#cc").hide();
    	//parent.addTab("纪检监察机关立案审查调查情况统计表","${path}/probleClues/cxl/tjb2");
    }
}
</script>
</body>
</html>