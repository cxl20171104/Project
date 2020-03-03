<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>Insert title here</title>
<script  type="text/javascript">
var option0 = {
	    title : {
	        text: '某地区蒸发量和降水量',
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['线索量']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'线索量',
	            type:'bar',
	            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
	           
	        }
	    ]
	};




var option1 = {
	    title : {
	        text: '某站点用户访问来源',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient : 'vertical',
	        x : 'left',
	        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true, 
	                type: ['pie', 'funnel'],
	                option: {
	                    funnel: {
	                        x: '25%',
	                        width: '50%',
	                        funnelAlign: 'left',
	                        max: 1548
	                    }
	                }
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'线索数量',
	            type:'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
	                {value:335, name:'直接访问'},
	                {value:310, name:'邮件营销'},
	                {value:234, name:'联盟广告'},
	                {value:135, name:'视频广告'},
	                {value:1548, name:'搜索引擎'}
	            ]
	        }
	    ]
	};
	                    
$(function(){
	
	 var themecombo2 =[];  
	 for(var i=2016;i<2036;i++){
		   themecombo2.push({"text":i,"id":i});  
	 }

	

	$("#time").combobox("loadData", themecombo2);  
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var lastYear=year-1;
	$("#time").combobox('select', year);


	$('#start').datebox('setValue', lastYear+"-"+month+"-01");
	$('#end').datebox('setValue', year+"-"+month+"-01");
	getTj();
	
	

	

	   
});

function getTj(){
	var mychart=echarts.init(document.getElementById("chart"));
    var i=$("#lx").combobox('getValue');
    
    $.ajax({

        url:"${path}/zjk/tj",
        data:{
            start:$("#start").combobox('getValue'),
            end:$("#end").combobox('getValue'),
            js:$("#js").combobox('getValue'),
            lx:$("#lx").combobox('getValue')
            },
            type:"post",
            dataType:"json",
            success:function(data){
                console.log(data);
                if(i==0){
                    option0.title.text=data.obj.text;
                    option0.series[0].data=data.obj.seriesData;
                    option0.xAxis[0].data=data.obj.legendData;
            		mychart.setOption(option0);
            		
            		}
            	if(i==1){
            		option1.title.text=data.obj.text;
                    option1.series[0].data=data.obj.seriesData;
                    option1.legend.data=data.obj.legendData;
            		mychart.setOption(option1);

            		
            		}

                

                }

        });
}


function outExcel(){




	 var form=$("<form></form>");
     form.attr("style","dispaly:none");
     form.attr("targer"," ");
     form.attr("action",'${path}/print/tj');
     var js=$("<input>");
     js.attr("type","hidden");
     js.attr("name","js");
     js.attr("value",$("#js").combobox('getValue'));

     var start=$("<input>");
     start.attr("type","hidden");
     start.attr("name","start");
     start.attr("value",$("#start").datebox('getValue'));


     var end=$("<input>");
     end.attr("type","hidden");
     end.attr("name","end");
     end.attr("value",$("#end").datebox('getValue'));
     $("body").append(form);
     form.append(js);
     form.append(start);
     form.append(end);
     form.submit();
    



	
}

</script>
<style>
li{
padding-top:5%


}

</style>
</head>
<body class="easyui-layout">   
    <div data-options="region:'west',title:'统计',split:true" style="width:200px;margin-right:15px;">
    
     <form>
     <ul  style="list-style-type:none;margin:a auto">
    <!--  <li>
    <span>统计时间:</span>
    <input id="time" class="easyui-combobox" name="time" style="width:160px;" data-options="editable:false,valueField:'id', textField:'text'" />    
    </li> -->
    
    
    <li>
    <span>检索条件:</span>
    <select id="js" class="easyui-combobox" name="js" style="width:160px;">   
    <option value="0">日期</option>   
    <!-- <option value="1">部门</option>  -->
    <!-- <option value="2">职级</option>  -->
    <option value="3">线索来源</option>     
    </select>  
    </li>
    
    <li id="date">
    <span>从:</span>
    <input id="start" name="start" type="text" class="easyui-datebox" data-options="required:true" style="width:160px;" ></input>  
    <span>至:</span>
    <input id="end"   name="end" type="text" class="easyui-datebox" data-options="required:true"  style="width:160px;"></input>
    
    </li>
    <li>
    <span>统计类型:</span> 
    <select id="lx" class="easyui-combobox" name="lx" style="width:160px;">   
    <option value="0">标准统计</option>   
    <option value="1">分布统计</option>   
    </select>  
    </li>
    <li>
     <a  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="getTj();" >统计</a>
     <a  class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="outExcel();" >导出excel</a>
    </li>
    </ul>
    </form>
    
    
    
    
    </div>   
    <div data-options="region:'center',title:'显示'" style="padding:5px;background:#eee;">
    
    
    
     <div id="chart" style="width: 920px;height:550px;">
    
    
    
    </div>   
</body>
</html>