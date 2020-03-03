<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html style="height:100%">

	<head>
	<%@ include file="/commons/basejs.jsp" %>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${staticPath}/static/style/css/select.css"/>
		<style>
.wrap .top{
	width: 100%;
	height: 72px;
	box-shadow: 0 0 20px #333;
	background: url(${staticPath}/static/images/index_topBg.png);
	background-size: 100% 100%;
	position: relative;
}

    .iconTop{
	float: left;
	width: 48px;
	height: 48px;
	background: url(${staticPath}/static/images/index_IconBg.png);
	background-size: 100% 100%;
	z-index: 10;
	margin-left: 30px;
}

  .wrap .con{
	width: 100%;
	height: 915px;
	background: url(${staticPath}/static/images/index_conBg.png);
	background-size: 100% 100%;
	position: relative;
}

  .wrap .con .conLeft .conLeftTop .conLeftTopTab .tdImg .tImg{
	position: absolute;
	width: 100%;
	height: 100%;
	background: url(${staticPath}/static/images/index_conLeftTopCircleBg.png);
	top: 0;
	-webkit-animation: circle 5s infinite linear;
}

    .wrap .con .conRight .conRightTop_down .flyTextBox{
	width: 90%;
	height: 250px;
	background: url(${staticPath}/static/images/index_conRightTop_down_flyTextBoxBg.png);
	background-size: 100% 100%;
	margin: 100px auto 0;
	float: none;
	position: relative;
}

    .wrap .con .conRight .conRightDown .conRightDownBox{
	float: right;
	width: 320px;
	height: 289px;
	background: url(${staticPath}/static/images/index_conRightDownBoxBg.png);
	background-size: 100% 100%;
	margin-top: 45px;
	position: relative;
}

    .wrap .con .conRight .conRightTop .conRightTop_top .conRightTop_topRight .numChartsBox{
	width: 100%;
	height: 250px;
	background: url(${staticPath}/static/images/index_numChartsBg.png);
	background-size: 100% 100%;
	float: right;
	margin-top: 30px;
}
.cxl { width: 100%; min-height: 50px; line-height: 29.5px; text-align: center; border:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;color:white; width:10%;}
		</style>
	</head>

	<body style="height:100%">
	    <input type="hidden" id="src" value="${staticPath}">
	    <input type="hidden" id="total" value="">
		<div class="wrap" style="height:100%">
			<div class="top">
			    <table style="width:100%">
			            <tr>
			            <td style="width:30%;padding-left:5%">
			            <button id="1" class="iconImg"  data-name="线索管理" style="cursor:hand;font-size:20px;">线索管理</button>
			            <button class="iconImg" id="2"  data-name="线索统计" style="cursor:hand;font-size:20px;">线索统计</button>
			            <button class="iconImg" id="0"  data-name="系统管理" style="cursor:hand;font-size:20px;">系统管理</button>
			            </td>
			            <td style="width:30%"><img class="topCenter" src="${staticPath}/static/images/index_topCenter.png" /> </td>
			            <td style="width:30%">
			            <span class="iconTop"><img src="${staticPath}/static/images/index_tipIcon.png"/></span>
					    <span class="iconTop"><img src="${staticPath}/static/images/index_exitIcon.png" onclick="logout();"/></span>
			            </td>
			        
			        </tr>
			    </table>
			</div>
			<div class="con" id="conte">
				<div class="conLeft">
					<div class="conLeftTop">
						<img src="${staticPath}/static/images/index_conTitle1.png" />
						<table class="conLeftTopTab" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<span class="tdImg"><i class="tImg"></i><span  id="js" class="tdImgNum">0</span></span>
									<span class="tdTitle">接收</span>
								</td>
								<td>
									<span class="tdImg"><i class="tImg"></i><span  id="ff" class="tdImgNum">0</span></span>
									<span class="tdTitle">分发</span>
								</td>
								<td>
									<span class="tdImg"><i class="tImg"></i><span  id="la" class="tdImgNum">0</span></span>
									<span class="tdTitle">立案</span>
								</td>
							</tr>
							<tr>
								<td>
									<span class="tdImg"><i class="tImg"></i><span  id="cf" class="tdImgNum">0</span></span>
									<span class="tdTitle">处分</span>
								</td>
								<td>
									<span class="tdImg"><i class="tImg"></i><span  id="ys" class="tdImgNum">0</span></span>
									<span class="tdTitle">移送</span>
								</td>
								<td>
									<span class="tdImg"><i class="tImg"></i><span  id="ja" class="tdImgNum">0</span></span>
									<span class="tdTitle">办结</span>
								</td>
							</tr>
						</table>
					</div>
					<div class="conLeftDown">
						<img src="${staticPath}/static/images/index_conTitle6.png" />
							<div style="    width: 80%;height: 530px;margin-left:8%" class="conRightTop_topRight">
                            	<table class="cxl">
                            	<tr>
							     <td style="color:black">来源</td>
							     <td style="color:black">数量</td>
							   </tr>
							   <tr>
							     <td id="0b"></td>
							     <td id="0c"></td>
							   </tr>
							   <tr>
							    <td id="1b"></td>
							     <td id="1c"></td>
							   </tr>
							   <tr>
							     <td id="2b"></td>
							     <td id="2c"></td>
							   </tr>
							   <tr>
							     <td id="3b"></td>
							     <td id="3c"></td>
							   </tr>
							   <tr>
							     <td id="4b"></td>
							     <td id="4c"></td>
							   </tr>
							   <tr>
							     <td id="5b"></td>
							     <td id="5c"></td>
							   </tr>
							   <tr>
							     <td id="7b"></td>
							     <td id="7c"></td>
							   </tr>
							   <tr>
							     <td id="8b"></td>
							     <td id="8c"></td>
							   </tr>
							   <tr>
							     <td id="9b"></td>
							     <td id="9c"></td>
							   </tr>
							   <tr>
							     <td id="10b"></td>
							     <td id="10c"></td>
							   </tr>
							   <tr>
							     <td id="11b"></td>
							     <td id="11c"></td>
							   </tr>
							   <tr>
							     <td id="6b"></td>
							     <td id="6c"></td>
							   </tr>
							  <tr> 
							     <td >线索总计</td>
							     <td id="total_ags"></td>
							   </tr>
							     <tr>
							   
							</table>
							</div>
					</div>
				</div>
				<div class="conCon">
					<div class="conConTop">
					<div id="main" style="width:600px;height:500px"></div>
					</div>
					<div class="conConDown" >
						<img src="${staticPath}/static/images/index_conTitle3.png" />
						<div class="lineBox" style="width:100%;">
							<div id="line" style="width:100%;height:250px;">

							</div>
						</div>
					</div>
				</div>
				<div class="conRight">
					<div  class="conRightTop">
						 <div id="field_num" class="conRightTop_down">
							    <img src="${staticPath}/static/images/index_conTitle5.png" />
								<div id="field" style="width:100%;height:400px;"></div>
						</div> 
					</div>
					<div class="conRightDown">
						<img src="${staticPath}/static/images/index_conTitle4.png" />
                       <div style="width:100%;height: 350px;" id="container" class="numChartsBox" >
					   </div>
					</div>
				</div>
			</div>
			<div id="errorMessage" style="width:100%"></div>
		</div>
		<script type="text/javascript" src="${staticPath}/static/page/select.js"></script>
		<script type="text/javascript" src="${staticPath}/static/echart/echarts.js"></script>
		<script type="text/javascript">
		    $(function(){
		    	//获取当前屏幕的高度
		    	var height=window.innerHeight;
		    	$("#conte").css('height',height);
		    });
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
            function make_bar_chart(names,nums){
            	var bar_data=nums;
            	var bar_name=names;
            	
            		
        			var option_bar = {
        					color:['#da70d6', '#32cd32','#ff7f50', '#87cefa', '#6495ed', '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
        					        '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
        					        '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' ],
                            xAxis : [
                                {
                                    type : 'category',
                                    data : bar_name,
                                    axisLine:{
    					            	lineStyle:{
    					            		color:'#FFFFFF',
    					            		width:1,
    					            	}
    					            }
                                }
                            ],
                            yAxis : [
                                {
                                    type : 'value',
                                    axisLine:{
    					            	lineStyle:{
    					            		color:'#FFFFFF',
    					            		width:1,
    					            	}
    					            }
                                }
                            ],
                    		series: [{
            					name: '专项行动统计',
            					type: 'bar',
            					data: bar_data,
            					//设置柱子的宽度
            					barWidth: 20,
            					//配置样式
            					itemStyle: {
            						//通常情况下：
            						normal: { //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
            							color: function(params) {
            								var colorList = ['rgb(220,25,25)', 'rgb(34,25,25)', 'rgb(22,80,246)', 'rgb(13,243,198)', 'rgb(214,246,38)'];
            								return colorList[params.dataIndex];
            							}
            						},
            						//鼠标悬停时：
            						emphasis: {
            							shadowBlur: 10,
            							shadowOffsetX: 0,
            							shadowColor: 'rgba(0, 0, 0, 0.5)'
            						},
            						label: {
                                        show: true,
                                        position: 'top',
                                        formatter: function(p){ return p.value}
                                    }
            					},
            					//控制边距　
            					grid: {
            						left: '0',
            						right: '0',
            						bottom: '0',
            						containLabel: true,
            					}
            				}],
        		
        			 
        			};
    				myChart.setOption(option_bar, true);
    			
            }
			
		</script>
		<script>
				var chart = document.getElementById('line');
				var echart = echarts.init(chart);
				var line_data = [];
                function make_line_chart(data){
                	line_data=data;
                	console.log("折线数据");
                	console.log(data);
            		var option_line ={  
            		title:{
            	        text: '本年各月线索统计'
            	       
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['最多线索','最少线索']
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
            	            boundaryGap : false,
            	            data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value',
            	            axisLabel : {
            	                formatter: '{value} 件'
            	            }
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'最大线索量',
            	            type:'line',
            	            data:data,
            	            markPoint : {
            	                data : [
            	                    {type : 'max', name: '最大值'},
            	                    {type : 'min', name: '最小值'}
            	                ]
            	            },
            	            markLine : {
            	                data : [
            	                    {type : 'average', name: '平均值'}
            	                ]
            	            }
            	        }
            	    ]};
                	echart.setOption(option_line);
                }
		</script>
		<script type="text/javascript">  
        $.get('${staticPath}/static/130900.json', function (cJjson) {  
        echarts.registerMap('沧州', cJjson);  
        });  
        var chart = echarts.init(document.getElementById('main'));  
        function make_map_chart(data){
        	var map_data=data;
            var option_map = {  
                title: {  
                	text: '线索归属地统计',
                    subtext: '*',
                    x:'center'  
                },  
                tooltip : {
                    trigger: 'item'
                },
                dataRange:{  
                    min:0,  
                    max:500,  
                    text:['高','低'],  
                    realtime:true,  
                    calculable:true
                   
                },  
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    x: 'right',
                    y: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                roamController: {
                    show: true,
                    x: 'right',
                    mapTypeControl: {
                        'china': true
                    }
                },
                series:[  
                    {  
                        name:'线索数量',  
                        type:'map',  
                        map:'沧州',  
                        mapLocation:{  
                            y:60  
                        },  
                        itemSytle:{  
                        	 normal:{label:{show:true}},
                            emphasis:{label:{show:true}}  
                        
                        },  
                        data:map_data
                    }  
                ],  
                  
            };
        	chart.setOption(option_map);
        }      
        
        
        function fieldMaker(data,line_name){
        	 var chart = echarts.init(document.getElementById('field'));  
        	 option = {
        			    title : {
        			        text: '领域占比',
        			        x:'center'
        			    },
        			    legend: {
        			        orient : 'vertical',
        			        x : 'left',
        			        data:line_name
        			    },
        			    color:['#da70d6', '#32cd32','#ff7f50', '#87cefa', '#6495ed', '#ff69b4', '#ba55d3', '#cd5c5c', '#ffa500', '#40e0d0', 
        			        '#1e90ff', '#ff6347', '#7b68ee', '#00fa9a', '#ffd700', 
        			        '#6b8e23', '#ff00ff', '#3cb371', '#b8860b', '#30e0e0' ],
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
        			            name:'访问来源',
        			            type:'pie',
        			            radius : '55%',
        			            center: ['50%', '60%'],
        			            data:data
        			        }
        			    ]
        			};
        			                    
        		                    
        	 chart.setOption(option);
        	
        }
       
    </script>  
     <script type="text/javascript">
          var inter;
		  $(function(){   
			   $(".iconImg").click(function(){
					var id= $(this).attr("id");
					var name=$(this).attr("data-name");
					var path=basePath + '/index?version='+id+'&name='+name;
					window.location.href=path;
				});
			    getData();
         	});
		  
		  		  
		  function  getData(){
			  $.ajax({
					 url:"${path}/firstPage/getData",
					 type:"post",
					 dataType:"json",
					 success:function(result){
						     $("#errorMessage").text(result.obj.errorMessage);
						     if(result.obj.total){
						    	 //基本情况统计
								 $("#js").text(result.obj.jsClues);
								 $("#ff").text(result.obj.fbClues);
								 $("#la").text(result.obj.laClues);
				                 $("#cf").text(result.obj.cfClues);
				                 $("#ys").text(result.obj.ysClues);
				                 $("#ja").text(result.obj.bjClues);
				                 //专项行动统计
				                 make_bar_chart(result.obj.zx_name,result.obj.zx_num);
				                 //领域统计
	     		                   var line_name=new Array();
								   var data_list=new Array();
								   $.each( result.obj.fieldClues, function(index, content){
									   line_name[index]=content.field_name+content.field_num+"件";
									   var data=new Object();
									   data["name"]=content.field_name+content.field_num+"件";
									   data["value"]=content.field_num;
									   data_list[index]=data;
								   });  
								   fieldMaker(data_list,line_name);  
								 console.log(result.obj.cluesClues);
								 //线索来源统计						
								 $.each( result.obj.cluesClues, function(index, content){
									if(content.clues_total){
										$("#total_ags").text(content.clues_total);
									}else{
										$("#"+index+"b").text(content.clues_name);
										$("#"+index+"c").text(content.clues_num);	
									}
									
									
								 });
								 
								   //当年月线索统计
								 var data=result.obj.monthClues;
								
								   
								 make_line_chart(data);
								  //问题属地统计
								 var data=result.obj.problemLandClues;
								 make_map_chart(data);
				                 
						     }
					 }
					 
				   });
		  }
		  
		  
		  
		  		  
		  /**加载首页数据**/
		  function cxl(){
			  
			  logout();
		  }
		  function logout(){
			  $.messager.confirm('提示','确定要退出?',function(r){
		            if (r){
		                progressLoad();
		                $.post('${path }/logout', function(result) {
		                    if(result.success){
		                        progressClose();
		                        //关闭主页
		                       
		                        window.location.href='${path }';
		                    }
		                }, 'json');
		            }
		        }); 
		  }
		</script>
	</body>

</html>