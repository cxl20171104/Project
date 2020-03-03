<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>活动管理</title>
<script type="text/javascript">
 var dataGrid;
	$(function() {
	
	
		 dataGrid = $('#companyTable').datagrid({
			url : '${path }' + '/qnaire/list',
			fit : true,
			fitColumns : true,
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			selectOnCheck : false,
			checkOnSelect : false,
			border : false,
			idField : 'id',
			sortName : 'ctime',
			sortOrder : 'asc',
			columns : [ [ {
				title : 'id',
				field : 'id',
				checkbox : false,
				hidden:true
			}, {
				width : '180',
				title : '名称',
				field : 'name',
				halign : 'center',
				sortable : false
			}, {
				width : '200',
				title : '主题',
				field : 'activities',
				halign : 'center',
				sortable : false
			}, {
				width : '160',
				title : '开始日期',
				field : 'btime',
				halign : 'center',
				sortable : false
			}, {
				width : '160',
				title : '截止日期',
				field : 'etime',
				halign : 'center',
				sortable : false
			}, {
				width : '60',
				title : '答题人数',
				field : 'nums',
				halign : 'center',
				sortable : false
			}, {
				width : '60',
				title : '题目数量',
				field : 'amount',
				halign : 'center',
				sortable : false
			}, {
				width : '80',
				title : '总分',
				field : 'total',
				halign : 'center',
				sortable : false
			}, {
				width : '80',
				title : '积分分值',
				field : 'scores',
				halign : 'center',
				sortable : false
			}, {
				width : '80',
				title : '奖励积分',
				field : 'award',
				halign : 'center',
				sortable : false
			/*}, {
				width : '80',
				title : '积分有限期',
				field : 'qgp',
				halign : 'center',
				sortable : false,
				formatter : function(data, row, index) {
					if (data == 1)
						return "永久有效";
					else 
						return data;

				}*/
			}, {
				width : '80',
				title : '积分方式',
				field : 'scorestype',
				halign : 'center',
				sortable : false,
				formatter : function(data, row, index) {
					if (data == 1)
						return "参与得分";
					else 
						return "等比积分";

				}

			}, {
                field : '_b',
                title : '操作',
                width : 200,
                formatter : function(value, row, index) {
                    var str = '';
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="addFun(\'{0}\');" >编辑</a>', row.id);
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                         str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                         str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-list" data-options="plain:true,iconCls:\'icon-list\'" onclick="questions(\'{0}\',\'{1}\');" >题目</a>', row.id,row.name);
                    return str;
                }
            }] ],
                onLoadSuccess:function(data){
                $('.role-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.role-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
                $('.role-easyui-linkbutton-list').linkbutton({text:'题目',plain:true,iconCls:'icon-list'});
            },
            toolbar : '#toolbar',
			loadFilter : function(data) {
				if (data.obj) {
					return data.obj;
				} else {
					return data;
				}
			}
		});
		});
		
		function questions(id,title){
		parent.addTab(title,"${path}/quest/main?naireid="+id,"icon-company");
		}
    function addFun(id) {
    var url = "${path }/qnaire/toEdit";
    var title = "添加";
    if(id !=null && id!= "undefined"){
    	title = "修改";
    	url='${path }/qnaire/toEdit?id='+id;
    }
        parent.$.modalDialog({
            title : title,
            width : 400,
            height : 500,
            href : url,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;
                    var f = parent.$.modalDialog.handler.find('#roleEditForm');
                    f.submit();
                }
            } ]
        });
    }
	function deleteFun(id) {
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r) {
				if (r) {
					$.ajax({
						url : '${path }/qnaire/delete',
						data : {
							id : id
						},
						dataType : 'json',
						async : true,
						type : 'post',
						success : function(result) {
							if (result.success) {
								$('#companyTable').datagrid('load');
							}
							$.messager.show({
								title : '消息提示',
								msg : result.msg,
								timeout : 3000,
								showType : 'slide'
							});
						}
					});
				}
			});

	}
	function refreshTable() {
		$('#companyTable').datagrid('load');
	}
	function closeDialog() {
		$('#companyDialog').dialog('close');
	}
	function searchFun() {
		$('#companyTable').datagrid('load', {
			name : $('#searchName').textbox('getValue')
		});
	}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
		<span style="float: right;">
		
		<%-- <select style="width:100px;" class="webui-combobox">
		<c:forEach items="${dict }" var="d">
			<option value="${d.value }">${d.name }</option>
		</c:forEach>
		</select> --%>
		
		 <input type="text" class="easyui-textbox"
			data-options="prompt:'请输入试卷名称'" id="searchName" /> <a
			class="easyui-linkbutton" data-options="iconCls:'icon-search'"
			onclick="searchFun();">查询</a> </span>
 <div id="toolbar" style="display: none;">
        <shiro:hasPermission name="/acti/add">
            <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        </shiro:hasPermission>
        
    </div>
		
	</div>
	<div data-options="region:'center',border:false">
		<table id="companyTable"></table>
	</div>
	<div id="companyDialog"></div>
</body>
</html>