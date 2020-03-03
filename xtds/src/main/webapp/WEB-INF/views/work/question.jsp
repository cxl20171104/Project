<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>题目管理</title>
<script type="text/javascript">
	var treeGrid;
	$(function() {
 
		treeGrid = $('#treeGrid').treegrid({
			url : '${path }/quest/tree?naireid=' + "${naireid}",
			idField : 'id',
			treeField : 'id',
			parentField : 'pid',
			fit : true,
			fitColumns : false,
			border : false,
			columns : [ [ {
				field : 'id',
				title : '序号',
				width : 150,
				formatter : function(value, row, index) {
					if (row.attributes)
						return row.attributes.attr1;
				}
			}, {
				field : 'text',
				title : '内容',
				width : 300,
			}, {
				field : '_c',
				title : '分值 ',
				width : 300,
				formatter : function(value, row, index) {
					if (row.attributes) {
						if (row.attributes.attr2 != "")
							return row.attributes.attr2 + "分";
					}
				}

			}, {
				field : '_d',
				title : '是否是正确答案 ',
				width : 300,
				formatter : function(value, row, index) {
					if (row.attributes) {
						if(row.attributes.attr3==1){
							return "<input type=\'button\' class=\'icon-ok\'/>";
						}
					}
				}

			} /* ,
																		{
																			field : '_o',
																			title : '操作',
																			width : 300,
																			formatter : function(value, row, index) {
																				if (row.attributes) {
																					var str = '';
																					if (row.attributes.type == 0) {
																						str += $
																								.formatString(
																										'<a href="javascript:void(0)" class="resource-easyui-linkbutton-add" data-options="plain:true,iconCls:\'icon-add\'" onclick="addFun(\'{0}\');" >添加</a>',
																										row.id);
																							<shiro:hasPermission name="/resource/delete">
																						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
																						str += $
																								.formatString(
																										'<a href="javascript:void(0)" class="resource-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>',
																										row.id);
																						</shiro:hasPermission>
																					} else {
																						<shiro:hasPermission name="/resource/edit">
																						str += $
																								.formatString(
																										'<a href="javascript:void(0)" class="resource-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>',
																										row.id);
																						</shiro:hasPermission>
																						<shiro:hasPermission name="/resource/delete">
																						str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
																						str += $
																								.formatString(
																										'<a href="javascript:void(0)" class="resource-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>',
																										row.id);
																						</shiro:hasPermission>
																					}

																					return str;

																				}
																			}
																		} */] ],
			toolbar : '#toolbar'
		});

		$('#companyDialog').dialog({
			width : 400,
			height : 300,
			title : '问题',
			closed : true,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					save();
				}
			}, {
				text : '取消',
				iconCls : 'icon-cancel',
				handler : function() {
					$('#companyDialog').dialog('close');
				}
			} ]
		});
	});

	function editFun() {
		var selnode = $('#treeGrid').treegrid('getSelected');
		if (selnode) {
			//修改题目
			if (selnode.attributes.type == 0) {
				$('#companyDialog').dialog(
						'refresh',
						'${path }/quest/toEdit?id=' + selnode.attributes.attrid
								+ "&&qid=" + "${naireid}&&dt=" + new Date());
				$('#companyDialog').dialog('open');
			}
			//修改选项
			else {
				$('#companyDialog').dialog(
						'refresh',
						'${path }/opt/toEdit?id=' + selnode.attributes.attrid
								+ "&&dt=" + new Date());
				$('#companyDialog').dialog('open');

			}
		} else {
			alert("请选择要编辑的行");
		}

	}

	function deleteFun() {
		var selnode = $('#treeGrid').treegrid('getSelected');
		if (selnode) {
			if (selnode.attributes.type == 0) {
				$.ajax({
					url : "${path}/quest/delete",
					data : {
						id : selnode.attributes.attrid
					},
					dataType : "json",
					success : function(data) {
						$.messager.show({
							title : '消息提示',
							msg : "删除成功",
							timeout : 3000,
							showType : 'slide'
						});
						$('#treeGrid').treegrid('remove',selnode.id);
					},
					error : function() {
						$.messager.show({
							title : '消息提示',
							msg : "失败，请重试",
							timeout : 3000,
							showType : 'slide'
						});
					}

				});
			} else {
				$.ajax({
				url:"${path}/opt/delete",
				data:{id:selnode.attributes.attrid},
				dataType:"json",
				success:function(data){
				$.messager.show({
					title:'消息提示',
					msg:"删除成功",
					timeout:3000,
					showType:'slide'
				});
				$('#treeGrid').treegrid('remove',selnode.id);
				},error:function(){
					$.messager.show({
					title:'消息提示',
					msg:"失败，请重试",
					timeout:3000,
					showType:'slide'
				});
				}
				
			});
			}
		} else {

			alert("请选择要删除的行");
		}
	}

	//添加问题
	function addQues() {
		var childs = $('#treeGrid').treegrid('getChildren');
		var qid = 0;
		for ( var i = 0; i < childs.length; i++) {
			var attr1 = childs[i].attributes.attr1;
			if (childs[i].attributes.type == 0) {
				if (attr1 - qid > 0)
					qid = attr1;
			}
		}
		$('#companyDialog').dialog(
				'refresh',
				'${path }/quest/toEdit?xh=' + qid + "&&qid="
						+ "${naireid}&&dt=" + new Date());
		$('#companyDialog').dialog('open');
	}
	//添加选项
	var id;
	function addFun() {
		var seleNode = $('#treeGrid').treegrid('getSelected');
		if (seleNode) {
			var parent;
			id = seleNode.id;
			parent = $('#treeGrid').treegrid('find', id);
			if (seleNode.attributes.type == 1) {
				parent = $('#treeGrid').treegrid('getParent', id);
				id = parent.id;

			}

			var childs = $('#treeGrid').treegrid('getChildren', id);
			var length = $('#treeGrid').treegrid('getChildren').length + 1;

			var xh = '';
			if (childs.length == 0)
				xh = "A";
			else {
				xh = childs[childs.length - 1].attributes.attr1;
				xh = xh.charCodeAt() + 1;
				xh = String.fromCharCode(xh);
			}
			$('#companyDialog').dialog(
					'refresh',
					'${path }/opt/toEdit?xh=' + xh + "&&questionid="
							+ parent.attributes.attrid + "&&dt=" + new Date());
			$('#companyDialog').dialog('open');
		} else {
			alert("请选择要添加选项的题目");
		}
	}

	function refreshTable() {
		$('#treeGrid').treegrid('load');
	}
	function closeDialog() {
		$('#companyDialog').dialog('close');
	}

	function appendChild(child) {
		child.id = $('#treeGrid').treegrid('getChildren').length + 1
		child.pid = id;
		var param = {
			parent : id,
			data : [ child ]
		};
		$('#treeGrid').treegrid('append', param);

	}
	
	function importFun(){
		$("#excelFile").click();
	}
	$(function(){
		$("#excelFile").change(function(){
		progressLoad();
			 var formData = new FormData();
                formData.append("file", document.getElementById("excelFile").files[0]);   
                formData.append("questionnaire", "${naireid}");   
                $.ajax({
                    url: "${path}/quest/import",
                    type: "POST",
                    dataType:"json",
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        if (data.success) {
                        	progressClose();
                        	parent.$.messager.alert('提示', data.msg);
                        	refreshTable()
                        }else{
                        	progressClose();
                        	parent.$.messager.alert('提示', data.msg)
                        	
                        }
                    },
                    error: function (data) {
                    	progressClose();
                    	parent.$.messager.alert('提示', data.msg)
                    }
                });
		});
	});
	function templatDown(){
		 var form=$("<form></form>");
         form.attr("style","dispaly:none");
         form.attr("targer"," ");
         form.attr("action",'${path}' + '/quest/downfile');
         $("body").append(form);
         form.submit();
	}
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>

	<div id="toolbar" style="display: none;">
		<shiro:hasPermission name="/resource/add">
			<a onclick="addFun();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">添加选项</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="/resource/add">
			<a onclick="addQues();" href="javascript:void(0);"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">添加题目</a>
		</shiro:hasPermission>
		<a onclick="editFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-edit'">编辑</a> <a
			onclick="deleteFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-del'">删除</a>
			<a
			onclick="importFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-page_excel'">导入</a>
			<a  onclick="templatDown();" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">下载模板</a>
			
	</div>
	<input id="excelFile" type="file"  style="display:none"/>
	<div id="companyDialog"></div>
</body>
</html>