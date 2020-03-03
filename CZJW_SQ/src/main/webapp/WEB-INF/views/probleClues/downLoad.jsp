<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>报告下载</title>


</head>
<body>    
          <div atdgn="center" width="300px;border:1px solid red">
        <!--   <a style=""  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="tj_now();">开始统计</a> -->
	      <form id="reprotFrom" method="post" style="margin-top: 20px;" width="300px">
	        <table class="formTable" width="300px">
	          <tr class="charttdst"  style="tdst-style-type:none;margin:0 auto;">
		        	 <td><span style="margin:10px 0 10px">日期类型：</span></td>
		    				<td><select id="ny" name="ny" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px',required:true">   
								    <option value="" selected="true" disabled="true">请选择</option>
								    <option value="1">按照年份统计</option>
								    <option value="2">按照月份统计</option>  
							 </select>
			    	</td>
			    	
			  </tr>
			   <tr id="month" class="charttdst"  style="tdst-style-type:none;margin:0 auto;display: none;">
			    	<td  ><span style="margin:10px 0 10px">开始日期（年月）:</span></td>
					<td><input id="attYearMonth"  name="attYearMonth" class="easyui-datebox"  data-options="width:'200px',editable:false,required:true" />
					    <span  style="color:red" class="cxl"></span>
					</td>
					
		       </tr>
		        <tr id="month2" class="charttdst"  style="tdst-style-type:none;margin:0 auto;display: none;">	
				    <td >
				       <span style="margin:10px 0 10px">结束日期（年月）:</span></td>
				         <td><input id="attYearMonth2"  name="attYearMonth2" class="easyui-datebox"  data-options="width:'200px',editable:false,required:true" />
				             <span  style="color:red" class="cxl"></span>
				    </td>
			    <tr id="year" class="charttdst"  style="tdst-style-type:none;margin:0 auto;display: none;">	
				
			    	        <td ><span style="margin:10px 0 10px">日期（年份）:</span></td>
			    			<td><input type="text" id="attYear" data-options="width:'200px',editable:false,required:true" id="receiveDate" name="attYear"/>
			    			    <span  style="color:red" class="cxl"></span>
			    	       </td>
			    	
			   	
			  </tr>
			    <tr class="charttdst"  style="tdst-style-type:none;margin:0 auto;">  
			      <td> <span style="margin:10px 0 10px">专项行动：</span></td>
			           <td><select id="special" name="special" class="easyui-combobox"  panelHeight="500" data-options="editable:false,width:'200px',">   
								    <option value="">请选择</option>
								    <c:forEach var="act" items="${special}">
								    	<c:if test="${act.name!=null&&act.name!=''}">
								             <option value="${act.value }">${act.name}</option>
								        </c:if>
		                            </c:forEach>
							 </select>
							 <span  style="color:red" class="cxl"></span>
		    		    </td>
			    </tr>
			   <tr class="charttdst"  style="tdst-style-type:none;margin:0 auto;">  	
			    	
			    	<td> <span style="margin:10px 0 10px">报表格式：</span></td>
		    				<td><select id="reportGs" name="reportGs" class="easyui-combobox"  panelHeight="200" data-options="editable:false,width:'200px',">   
								    <option value="" selected="true" disabled="true">请选择</option>
								    <c:forEach var="act" items="${reportGs}">
								             <option value="${act.value }">${act.name}</option>
		                            </c:forEach>
							 </select>
							 <span  style="color:red" class="cxl"></span>
		    		</td>
		    		 	
			  </tr> 
			  <tr>
			      <td></td>
  				  <td>
  				   <a style=""  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="reportStart();">报表统计</a>
	             </td>
			  
			  </tr>
	  		</table>
  		</form>
          </div>
    <script  type="text/javascript">
$(function(){
	//监听统计类型下拉选
	$('#ny').combobox({
		  onSelect : function(param){
	          	if(param.value=="1"){
	          		//加载年份的
	          		$("#attYear").combobox({   
	          				valueField:'year',    
	          				textField:'year',  
	          				panelHeight:'auto'
	          		});
	          		var data = [];//创建年度数组
	          		var startYear;//起始年份
	          		var thisYear=new Date().getUTCFullYear();//今年
	          		var endYear=thisYear+1;//结束年份
	          		startYear=endYear-4;
	          		//数组添加值（2014-2017）//根据情况自己修改
	          		for(startYear;startYear<endYear;startYear++){
	          			data.push({"year":startYear+"年"});
	          		}
	          		$("#attYear").combobox("loadData", data);//下拉框加载数据
	          		//显示
	          		document.getElementById("year").style.display="";
	          		document.getElementById("month").style.display="none";
	          		document.getElementById("month2").style.display="none";
	          	}else if(param.value=="2"){
	          		document.getElementById("year").style.display="none";
	          		document.getElementById("month").style.display="";
	          		document.getElementById("month2").style.display="";
	          	//加载月份数据
	          		rqs($('#attYearMonth'));
	          		rqs($('#attYearMonth2'));
	          	}
     		} 
	});
	
	
});

//开始统计按钮
function reportStart(){
	var url = '${path }/statistics/OnImportWord';
	 $('#reprotFrom').form('submit',{
		 url:url,
		 onSubmit:function(){
	         var type=$("#ny").combobox('getValue');
	         if(type==1){
	        	if(!$("#attYear").combobox('getValue')){
	        		$("#attYear").parent().children(".cxl").text('请选择开始月份');
	        		 return false;
	        	} 	       
	         }
	         if(type==2){
	        	if(!$("#attYearMonth").datebox('getValue')){
	        		$("#attYearMonth").parent().children(".cxl").text('请选择开始月份');
	        		return false;
	        	}
	        	if(!$("#attYearMonth2").datebox('getValue')){
	        		$("#attYearMonth2").parent().children(".cxl").text('请选择结束月份');
	        		return false;
	        	}
	         }
	         if(!$("#reportGs").combobox('getValue')){
	        	 $("#reportGs").parent().children(".cxl").text('请选择结报表格式');
	        	 return  false;
	         }
			 
		     } 
		 
	 });

	

}
//年月日期加载方法
function rqs(id){
	id.datebox({
	       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	       onShowPanel: function () {
	          //触发ctdck事件弹出月份层
	          span.trigger('ctdck'); 
	          if (!tds)
	            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            setTimeout(function() { 
	                tds = p.find('div.calendar-menu-month-inner td');
	                tds.ctdck(function(e) {
	                   //禁止冒泡执行easyui给月份绑定的事件
	                   e.stopPropagation(); 
	                   //得到年份
	                   var year = /\d{4}/.exec(span.html())[0] ,
	                   //月份
	                   //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1; 
	                   month = parseInt($(this).attr('abbr'), 10);  

	         //隐藏日期对象                     
	         id.datebox('hidePanel') 
	           //设置日期的值
	           .datebox('setValue', year + '-' + month); 
	                        });
	                    }, 0);
	            },
	            //配置parser，返回选择的日期
	            parser: function (s) {
	                if (!s) return new Date();
	                var arr = s.split('-');
	                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	            },
	            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
	            formatter: function (d) { 
	                var currentMonth = (d.getMonth()+1);
	                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
	                return d.getFullYear() + '-' + currentMonthStr; 
	            }
	        });
	        //日期选择对象
	        var p = id.datebox('panel'), 
	        //日期选择对象中月份
	        tds = false, 
	        //显示月份层的触发控件
	        span = p.find('span.calendar-text'); 
	        var curr_time = new Date();
}

//格式化日期方法
function myformatter(date) {
  //获取年份
  var y = date.getFullYear();
  //获取月份
  var m = date.getMonth() + 1;
  return y + '-' + m;
}
function tj_now(){
	$.ajax({
		url:"${path}/statistics/tj_now",
		type:"get",
		success:function(){
			alert("统计完成");
		}
	})
}
</script>
</body>
</html>