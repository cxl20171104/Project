<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
<script type="text/javascript">
    var index_layout;
    var index_tabs;
    var layout_west_tree;

    $(function() {
        index_layout = $('#index_layout').layout({
            fit : true
        });
        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            tools : [{
                iconCls : 'icon-home',
                handler : function() {
                    index_tabs.tabs('select', 0);
                }
            }, {
                iconCls : 'icon-refresh',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    index_tabs.tabs('getTab', index).panel('open').panel('refresh');
                }
            }, {
                iconCls : 'icon-del',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ]
        });
        
        loadMenuTree();
    });
    
 // 加载菜单树
    function loadMenuTree() {
		$('#menuTree').tree({
			url : "${path }/getMenu?id=0",
			onBeforeExpand : function(node) {
				//展开前,异步访问添加节点
				$("#menuTree").tree('options').url = '${path }/getMenu';
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
					addTab(node.name, '${path }' + node.url, node.icon);
				} else {
					var node_ = $('#menuTree').tree('find', node.id);
					if (node_.state == 'open') {
						$('#menuTree').tree('collapse', node_.target);
					} else {
						$('#menuTree').tree('expand', node_.target);
					}
				}
			},
			onLoadSuccess : function() {
				var node = $('#menuTree').tree('find', 0);
				if (node) {
					$('#menuTree').tree('expand', node.target);
				}
			}
		});
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

</script>
</head>
<body>
    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;
    	background: #fff;z-index: 100;overflow: hidden;">
        	<img src="${staticPath }/static/style/images/ajax-loader.gif" 
        		style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    <div id="index_layout">
        <div data-options="region:'north',border:false" style="height:70px;background:url('${staticPath }/static/images/top.jpg') top center no-repeat; background-size:100% 100%;">
	        <span style="float: right; padding-right: 20px; margin-top: 15px; color: #333">
	        	欢迎 <b><shiro:principal /></b>&nbsp;&nbsp; 
	        	<a onclick="editUserPwd()" class="easyui-linkbutton" plain="true" icon="icon-edit" >修改密码</a>
	        	<a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton" plain="true" icon="icon-clear">安全退出</a>
	        </span>
        </div>
        <div data-options="region:'west',split:true" title="菜单" style="width: 160px; overflow: hidden;overflow-y:auto; padding:0px">
            <div class="easyui-accordion" fit="true" border="false">
				<ul id="menuTree"></ul>
            </div>

        </div>
        <div data-options="region:'center',border:false" style="overflow: hidden;">
            <div id="index_tabs" style="overflow: hidden;">
                <div title="首页" data-options="border:false" style="overflow: hidden;background:url('${staticPath }/static/images/online.jpg')top center no-repeat; background-size:100% 100%;">
                </div>
            </div>
        </div>
    </div>

    <style>
        #ie6-warning {width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}
    </style>
</body>
</html>