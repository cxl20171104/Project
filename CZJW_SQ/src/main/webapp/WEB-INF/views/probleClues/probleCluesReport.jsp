<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<script type="text/javascript" src="${staticPath }/static/city.js"></script>
<script type="text/javascript">

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
//----------------------------------------------------------------------------------------------------------------------------
//年月日期加载方法
function rqs(id){
	id.datebox({
	       //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	       onShowPanel: function () {
	          //触发click事件弹出月份层
	          span.trigger('click'); 
	          if (!tds)
	            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	            setTimeout(function() { 
	                tds = p.find('div.calendar-menu-month-inner td');
	                tds.click(function(e) {
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

//----------------------------------------------------------------------------------------------------------------------------
//格式化日期方法
function myformatter(date) {
    //获取年份
    var y = date.getFullYear();
    //获取月份
    var m = date.getMonth() + 1;
    return y + '-' + m;
}
//----------------------------------------------------------------------------------------------------------------------------
//开始统计按钮
	function reportStart(){
		var obj = $('#reprotFrom').serializeObject();
		var url = '${path }/probleClues/OnImportWord';
		console.log(obj.ny);
		if(obj.ny=="2"){
			if(obj.attYearMonth=="" ||  obj.attYearMonth2==""){
				$.messager.show({
					title:'消息提示',
					msg:'日期不可不填',
					timeout:3000,
					showType:'slide'
				});
			}else if(obj.attYearMonth>=obj.attYearMonth2){
					$.messager.show({
						title:'消息提示',
						msg:'开始日期必须小于结束日期',
						timeout:3000,
						showType:'slide'
					});
		   }else if(obj.reportGs ==null||obj.reportGs==""){
					$.messager.show({
						title:'消息提示',
						msg:'未选择报表格式',
						timeout:3000,
						showType:'slide'
					});
			}else{
				if($('#reprotFrom').form('validate')){
			  		$('#reprotFrom').form('submit', {    
			  		    url:url,    
			  		    onSubmit: function(){    
			  		        
			  		    }
			  		}); 
			  	}
			}
		}else if(obj.ny=="1"){
			console.log(obj.attYear);
			if(obj.attYear==""){
			console.log('进来');
				$.messager.show({
					title:'消息提示',
					msg:'日期不可不填',
					timeout:3000,
					showType:'slide'
				});
			}else if(obj.reportGs==""){
				$.messager.show({
					title:'消息提示',
					msg:'未选择报表格式',
					timeout:3000,
					showType:'slide'
				});
			}else{
				if($('#reprotFrom').form('validate')){
			  		$('#reprotFrom').form('submit', {    
			  		    url:url,    
			  		    onSubmit: function(){    
			  		        
			  		    }
			  		}); 
			  	}
			}
		}
		
	
}
</script>
  <div class="easyui-layout" data-options="fit:true,border:false">
  	  <form id="reprotFrom" method="post">
	        <table class="formTable">
	        	 <tr>
	        	 	<th>统计分析类型：</th>
	    			<td>
	    				<select id="ny" name="ny" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'100%'">   
							    <option value="" selected="true" disabled="true">请选择</option>
							    <option value="1">按照年份统计</option>
							    <option value="2">按照月份统计</option>  
						 </select>
	    			</td>
		    	</tr>
		    		<tr id="month"style="display: none;">
				       <th>开始日期（年月）:</th>
				       <td>
				         <input id="attYearMonth"  name="attYearMonth" class="easyui-datebox"  data-options="width:'100%',editable:false" />
				      </td>
				    </tr>
				    <tr id="month2"style="display: none;">
				       <th>结束日期（年月）:</th>
				       <td>
				         <input id="attYearMonth2"  name="attYearMonth2" class="easyui-datebox"  data-options="width:'100%',editable:false" />
				      </td>
				    </tr>
		    	<tr id="year"style="display: none;">
		    		<th>日期（年份）:</th>
		    		<td>
		    			<input type="text" id="attYear" data-options="width:'100%',editable:false" id="receiveDate" name="attYear"/>
		    		</td> 
		    	</tr>
		    	<tr> 
	    			<th>报表格式：</th>
	    			<td>
	    				<select id="reportGs" name="reportGs" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'100%'">   
							    <option value="" selected="true" disabled="true">请选择</option>
							    <option value="1">统计情况通报</option>
							    <option value="2">职级违纪行为分类表</option>
							    <option value="3">纪律审查情况分析表</option>
							    <option value="4">案件处理情况表</option>
							    <option value="5">违纪行为分类情况表</option>
							    <option value="6">所属领域分类情况表</option>  
							    <option value="7">职级反映问题违纪行为分类表</option>
						 </select>
	    			</td>
	    		</tr>
	  		</table>
  		</form>
  </div>
