<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%-- <%@ include file="probleClues/probleClues.jsp" %> --%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${name }</title>
    <style>
    .datagrid-btable .datagrid-cell{padding:6px 4px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;} 
     .tit{ overflow: hidden;text-overflow: ellipsis;-o-text-overflow: ellipsis;white-space:nowrap;;width:160px;display:block; } 
	  #ie6-warning {width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}   
        a{
		cursor:pointer;
		}    
		a:hover {text-decoration:underline;}  
		a:visited {color: #000000} /* 已访问的链接 */
		a:link {color: #000000} 
		a:active {color: #000000} /* 选定的链接 */
	
		.color{color:red;}/*链接设置*/
		.color:visited{color:red}/*访问过的链接设置*/
		.color:hover{color:red}/*鼠标放上的链接设置*/
		a {color:black;}
	</style>


</head>
<body>
    
    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;
    	background: #fff;z-index: 100;overflow: hidden;">
        	<img src="${staticPath }/static/style/images/ajax-loader.gif" 
        		style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    
    <div id="index_layout">
      <input id="version" type="hidden" value="${version}" />
      <input id="agsId" type="hidden" value="${agsId}" />
      <input id="scsIds" type="hidden" value="${scsIds}" />
      <input id="organId" type="hidden" value="${organId}" />
      <input id="userId" type="hidden" value="${userId}" />
      <input id="sjw_value" type="hidden" value="${sjw_value}" />
      <input id="dfsId" type="hidden" value="${dfsId}" />
      
        <div data-options="region:'north',border:false" style="overflow:hidden;height:85px;background:url('${staticPath }/static/images/jwtop.png') top center no-repeat; background-size:100% 100%;">
	        <img style="margin-left:5%;margin-top:6px;" src="${staticPath }/static/images/jwtop_dh.png" />
	        <span style="float: right; margin-right: 100px; margin-top: 35px; color: #f3f3f3;font-size:18px;">
	        	欢迎&nbsp; <b style="font-size:18px;"><shiro:principal /><span id="message">(${orgaName})</span></b>&nbsp;&nbsp; 
	        	
	        	<a id="mess" style="cursor:pointer;display:none" onclick="show(event)"><img src="./static/icon-message.png" style="height:30px;vertical-align:bottom; weight:40px;"/></a>
	        	<a style="margin-left:10px;" onmousedown="btn1Dowm()" onmouseup="btn1Up()" onclick="editUserPwd()" class="easyui-linkbutton btn1" plain="true" icon="icon-edit" >修改密码</a>
	        	<a style="margin-left:20px;"  href="javascript:void(0)" onclick="backToSelect()" class="easyui-linkbutton btn2" plain="true" icon="icon-clear">返回首页</a>
	        	<a style="margin-left:20px;" onmousedown="btn2Dowm()" onmouseup="btn2Up()" href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton btn2" plain="true" icon="icon-clear">安全退出</a>
	        </span>
        </div>
        <div data-options="region:'west',split:true" title="菜单" style="width: 300px; overflow:hidden; padding:0px">
            <div class="easyui-accordion" fit="true" border="false" style="overflow-y:scroll;">
				<ul id="menuTree"></ul>
            </div>

        </div>
        <div data-options="region:'center',border:false" style="overflow: hidden;">
            <div id="index_tabs" style="overflow: hidden;"><%--  background:url('${staticPath }/static/images/background.png--%>
                <div title="首页" data-options="border:false" style="overflow: hidden;height:30px;background:url('${staticPath }/static/images/bg.png')top center no-repeat; background-size:100% 100%;">
                   
                </div>
            </div>
        </div>
        <div id="messageDialog"></div>
       
    	<!-- <input type="button" id="Button1" onclick="refreshTable()"> -->
    	   
    	<!-- <iframe method="post" id ="myFrame" src="probleClues/probleClues.jsp" ></iframe> -->
    	  <iframe name="myFrame" id="myFrame" src=""></iframe> 
    	 <%--  <iframe name="myFrame" id="myFrame" src="${path }/probleClues/searchInfo"></iframe>  --%>
    </div>
    <script type="text/javascript">
    
    //全局参数
    var index_layout;
    
    var index_tabs;
    
    var layout_west_tree;
    //一级界面编号
    var version;
    //二级界面名称
    var version2Name;
    //当前用户部门Id
    var organId;
    //当前用户id
    var userId;
    //案管室id
    var agsId;
    //审查室ids
    var scsIds;
    //省纪委以上等
    var sjw_value;
    var dfsId;
    
    
    //
    var tree;
    var menuId;
    var conuntNum="${conuntNum}";
    var strs=conuntNum.split(",");
    var menu_num=new Object();
    for(var i=0;i<strs.length;i++){
    	var ss=strs[i].split("_");
    	menu_num[ss[0]]=ss[1];
    }
    
    $(function() {
    	version=document.getElementById("version").value;
    	agsId=document.getElementById("agsId").value;
    	scsIds=document.getElementById("scsIds").value;
    	organId=document.getElementById("organId").value;
    	userId=document.getElementById("userId").value;
    	sjw_value=document.getElementById("sjw_value").value;
    	dfsId=document.getElementById("dfsId").value;
    	var websocket = null;
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://${ip}:${port}/CZJW/websocket");
        }
        else {
            alert('当前浏览器 Not support websocket')
        }
     
        //连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("WebSocket连接发生错误");
        };
     
        //连接成功建立的回调方法
        websocket.onopen = function () {
           setMessageInnerHTML("open");
        }
     
        //接收到消息的回调方法
        websocket.onmessage = function (event) {
        	$.messager.show({
	 			title:'消息提示',
	 			msg:event.data,
	 			timeout:3000,
	 			showType:'slide'
	 		});
        }
     
        //连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("closed");
        }
     
        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        window.onbeforeunload = function () {
            closeWebSocket();
        }
     
        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML) {
           if(innerHTML=="closed"){
        	   document.getElementById("message").style.color="red";
           }
        }
     
        //关闭WebSocket连接
        function closeWebSocket() {
            websocket.close();
        }
     
        //发送消息
        function send() {
            var message = document.getElementById('text').value;
            websocket.send(message);
        }
        
        index_layout = $('#index_layout').layout({
            fit : true
        });
        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            tools : [{
                text:'首页',
                iconCls : 'icon-home',
                handler : function() {
                    index_tabs.tabs('select', 0);    //修改
                }
            }, {
                iconCls : 'icon-refresh',
                text:'刷新',
                handler : function() {
                 //   var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                  //  index_tabs.tabs('getTab', index).panel('open').panel('refresh');
                  //刷新当前TAB
                	$('.panel:visible > .panel-body > iframe').get(0).contentDocument.location.reload(true);
                
                }
            }, {
                iconCls : 'icon-close',
                text:'关闭',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ],
            onClose:function(title,index){
            	console.log(title);
            	if(title=="数据字典"||title=="部门管理"||title=="用户管理"){
            		//更新下拉选
            		$.ajax({
            			url:"${path}/option",
            			dataType:'json',
            			type:'post',
            			success:function(result){
            				if(result){
            					
            				$.messager.show({
            					title:'消息提示',
            					msg:result.text,
            					timeout:3000,
            					showType:'slide'
            				});
            				}
            			}
            		});
            	}
            }
        });
        loadMenuTree();

        
    });
    //关闭当前窗口并刷新列表
    function reloadTabGrid(gridtitle,index)
           { 
    	       console.log(index+"==============");
    	       var tab = index_tabs.tabs('getTab', index);
               if (tab.panel('options').closable) {
                 index_tabs.tabs('close', index);
               }
              if ($("#index_tabs" ).tabs('exists', gridtitle)) {
        	    index_tabs.tabs('select', gridtitle);
                window.top.reload_Abnormal_Monitor.call();
              }
             
              
            } 
          
    
    
    
           //加载菜单树
           //判断version如果为1直接加载300业务管理下的值
           //如果为2 直接加载331 下的
           //如果界面为0则为0
           var ver=document.getElementById("version").value;
           var  url= "${path }/getMenu?id=0&version="+ver;
           function loadMenuTree() {
		    tree=$('#menuTree').tree({
			url :url,
			 onBeforeExpand : function(node) {
				//展开前,异步访问添加节点
				console.log(node);
			    if(node.pid==null&&node.pid){
				$("#menuTree").tree('options').url = '${path }/getMenu?id='+node.id;
			    	
			    }else{
			    	$("#menuTree").tree('options').url = '${path }/getMenu';	
			    }
			    
			    //如果是下属单位那么加载1-13室
			    if(node.id=='375'){
			    	$("#menuTree").tree('options').url = '${path }/organization/forMenu?id=12';
			    }
			    if(node.id=='376'){
			    	$("#menuTree").tree('options').url = '${path }/organization/forMenu?id=32';
			    }
			},
			onExpand:function(node){
				         //默认打开
						//如果是重点督办
						if(node.children[0]){
							
						
					    var isImport='${name}';
					    var a="";
					    if(node.children[0].url.indexOf("?")!=-1){
					    	 if(isImport.indexOf("重点督办")!=-1){
							   	 a="&zddb=YES";
							    }else{
							     a="&zddb=NO";	
							    }
					    }else{
					    	
						    	 if(isImport.indexOf("重点督办")!=-1){
								   	 a="?zddb=YES";
								    }else{
								     a="?zddb=NO";	
								    }
					   
					    }
					 if(menuId){
						    var node = $('#menuTree').tree('find', menuId);
						    if(node&&node.name){
						    	addTab(node.name,"${path }"+node.url+a,style="border:0;width:100%;height:99.5%;","icon-search");
							    $("#menuTree").tree('select',node.target);
						    }
					 }else{
						 if(node.pid==null&&node.children[0]){
							addTab(node.children[0].name,"${path }"+node.children[0].url+a,style="border:0;width:100%;height:99.5%;","icon-search");
							$("#menuTree li:eq(1)").find("div").addClass("tree-node-selected");
						   } 
					 }
				    
				 }
			},
			loadFilter : function(data) {
				if (data.list) {
					return data.list;
				} else {
					return data;
				}
			},
			onClick : function(node) {
			 if (node.url != null && node.url != "") {
					//如果是重点督办
				    var isImport='${name}';
				    var a="";
				    if(isImport.indexOf("重点督办")!=-1){
				     if(node.url.indexOf("?")!=-1){
				    	 a="&zddb=YES";
				     }else{
				    	 a="?zddb=YES";
				     }
				   	
				   	 if(node.url=="/probleClues/mm"){
				   		 a+="&id="+node.id;
				   	 }
				    }else{
				    	if(node.url.indexOf("?")!=-1){
					    	 a="&zddb=NO";
					     }else{
					    	 a="?zddb=NO";
					     }
				     if(node.url=="/probleClues/mm"){
				    	 a+="&id="+node.id;
				     }
				    }
				    //点击的时候储存界面名称
					addTab(node.name, '${path }' + node.url+a, node.icon);
				} else {
					var node_ = $('#menuTree').tree('find', node.id);
					if (node_.state == 'open') {
						$('#menuTree').tree('collapse', node_.target);
					} else {
						$('#menuTree').tree('expand', node_.target);
					}
				}
			},
			onLoadSuccess : function(node,data) {
				//展开根节点
				 var rooNode = $("#menuTree").tree('getRoot'); //获取根节点
				 if(rooNode){
				 $("#menuTree").tree('expand',rooNode.target);     //调用expand方法,
				 }
			},formatter:function(node){
				console.log(menu_num);
				console.log(node.text);
				if(menu_num[node.text]){
					return '<span style="font-size:20px;">'+node.text+"("+menu_num[node.text]+')</span>';
				}else{
					return '<span style="font-size:20px;">'+node.text+'</span>';
				}
				
			}

		});
		
		$('#messageDialog').dialog({
        	width:900,
        	height:550,
        	title:'所有消息',/* 
        	resizable:true, */
        	closed:true,
        	buttons:[{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){
					$('#messageDialog').dialog('close');
				}
			}]
        });
	}
 
  //所有消息
	function allMessage(){  
		$('#messageDialog').dialog('refresh','${path }/remind/remindMessage');
    	$('#messageDialog').dialog('open');
    }
  
    // 添加标签页
    function addTab(title, href, icon) {
        var tt = $('#index_tabs');
        icon = icon || 'menu_icon_service';
        if (tt.tabs('exists', title)) {
            tt.tabs('select', title);
            var currTab = tt.tabs('getTab', title);
            tt.tabs('update', {tab: currTab, options: {content: content, closable: true}});
        } else {
            if (href) {
                var content = '<iframe frameborder="0" src="' + href + '" style="border:0;width:100%;height:99.5%;"></iframe>';
            } else {
                var content = '未实现';
            }
            tt.tabs('add', {
                title : title,
                content : content,
                closable : true,
                iconCls: icon
            });
        }
    }
    function updateTab(oldtitle,newtitle, href, icon){
    	 var tt = $('#index_tabs');
         icon = icon || 'menu_icon_service';
         tt.tabs('select', oldtitle);
         var currTab = tt.tabs('getTab', oldtitle);
         var content = '<iframe frameborder="0" src="' + href + '" style="border:0;width:100%;height:99.5%;"></iframe>';
         tt.tabs('update', {tab: currTab, options: {title:newtitle,content: content, closable: true}});
    }
    function logout(){
    	 $.messager.confirm('提示','确定要退出?',function(r){
             if (r){
                 progressLoad();
                 $.post('${path }/logout', function(result) {
                     if(result.success){
                         progressClose();
                         window.location.href='${path }';
                     }
                 }, 'json');
             }
         });
    }

    
    function editUserPwd() {
        parent.$.modalDialog({
            title : '修改密码',
            width : 300,
            height : 250,
            href : '${path }/user/editPwdPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                    f.submit();
                }
            } ]
        });
    }
	function btn1Dowm(){
		$(".btn1").css("background","rgba(255,255,255,0.5)");
		$(".btn1").css("border-left","2px solid #444");
		$(".btn1").css("border-top","2px solid #444");
		$(".btn1").css("border-right","1px solid #888");
		$(".btn1").css("border-bottom","1px solid #888");
	}
	function btn1Up(){
		$(".btn1").css("background","rgba(255,255,255,0.05)");
		$(".btn1").css("border-right","2px solid #444");
		$(".btn1").css("border-bottom","2px solid #444");
		$(".btn1").css("border-left","1px solid #888");
		$(".btn1").css("border-top","1px solid #888");
	}
	function btn2Dowm(){
		$(".btn2").css("background","rgba(255,255,255,0.5)");
		$(".btn2").css("border-left","2px solid #444");
		$(".btn2").css("border-top","2px solid #444");
		$(".btn2").css("border-right","1px solid #888");
		$(".btn2").css("border-bottom","1px solid #888");
	}
	function btn2Up(){
		$(".btn2").css("background","rgba(255,255,255,0.05)");
		$(".btn2").css("border-right","2px solid #444");
		$(".btn2").css("border-bottom","2px solid #444");
		$(".btn2").css("border-left","1px solid #888");
		$(".btn2").css("border-top","1px solid #888");
	}
	  function show(e) {
            e = e || event;
            if (e.stopPropogation) {
                e.stopPropogation();
            } else {
                e.cancelBubble = true;
            }
            document.getElementById("content1").style.display = "";
            /*  document.getElementById("content1").attr("z-index",999); */
        }
        function init() {
                document.getElementById("content1").style.display = "none";
        }
        function checkMessage(problemId,id){    
        if(problemId!=null&&problemId!=""){
    		$.ajax({
						url:'${path }/remind/updateState',
						data:{id:id},
						dataType:'json',
						async : false,
						type:'post',
						success:function(result2){
							if(result2.success){								
  	    						parent.addTab("线索:"+problemId,"${path}/probleClues/detail?id="+problemId);
															
							}							
						}
					});
    	} 
    	
    	}
        function backToSelect(){
        	 window.location.href =  '${path}/select';
        }
</script>
   <script type="text/javascript">
   
   </script>
</body>
</html>