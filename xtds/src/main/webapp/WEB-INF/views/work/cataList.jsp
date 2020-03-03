<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>文章管理</title>
<script type="text/javascript">
	$(function() {
		change();
		$('#dictTree').tree({
			url : '${path}/cata/tree',
			queryParams : {
				pid : "100"
			},
			onBeforeExpand : function(node) {
				$('#dictTree').tree('options').queryParams = {
					pid : node.id
				};
			},
			onClick : function(node) {
				$("#activities").combobox({ disabled: false }); 
				$('#dictForm').form('load', node);
				console.log(node);
				$("#previewFile").attr("src","${path}/files/"+node.pic);
				change();
				var pNode = $('#dictTree').tree('getParent', node.target);
				if (pNode == null) {
					$('#parentcataName').textbox('setValue', '无');
				} else {
					$('#parentcataName').textbox('setValue', pNode.name);
				}
			},
			loadFilter : function(data) {
				if (data.obj) {
					return data.obj;
				} else {
					return data;
				}
			}
		});
	});
	function  change(){
		console.log("加载");
		$("#picFile").on("change",function(){
			console.log("上传图片");
			if($("#picFile").val() != null && $("#picFile").val() != "" && $("#picFile").val() != undefined){
			 var formData = new FormData();
	         formData.append("upload", document.getElementById("picFile").files[0]);   
	         $.ajax({
	             url: "${path}/com/upload",
	             type: "POST",
	             dataType:"json",
	             data: formData,
	             /**
	             *必须false才会自动加上正确的Content-Type
	             */
	             contentType: false,
	             /**
	             * 必须false才会避开jQuery对 formdata 的默认处理
	             * XMLHttpRequest会对 formdata 进行正确的处理
	             */
	             processData: false,
	             success: function (data) {
	                 if (data.success) {
	                      $("#pic").val(data.obj);
	                      $("#previewFile").attr("src","${path}/files/"+data.obj);
	                     // $("#picFile").val('');
	                      //$('#picFile').replaceWith('<input  type="file" id="picFile" style="display:none;" />');
	                 }
	                else{	
	                    alert("图片上传失败，请重试");
	                    $("#headpicFile").val("");
	                 }
	             },
	             error: function () {
	            	   alert("图片上传失败，请重试");
	                   $("#headpicFile").val("");
	             }
	         });}
			
		});	
	}
	function saveFun() {
		if ($('#dictForm').form('validate')) {
			parentNname = $("#parentcataName").val();
			if ($('#parentcata').val() == null || $('#parentcata').val() == ''||parentNname == null || parentNname == "") {
				$('#parentcata').val("100");
			}
			$.ajax({
				url : '${path}/cata/save',
				data : $('#dictForm').serialize(),
				dataType : 'json',
				type : 'post',
				success : function(result) {
					$('#dictForm').form('clear');
					$("#previewFile").attr("src", "${path}/static/images/tpsc.jpg");
					$('#dictTree').tree('options').queryParams = {
						pid : "100"
					};
					$('#dictTree').tree('reload');
					$.messager.show({
						title : '消息提示',
						msg : result.msg,
						timeout : 3000,
						showType : 'slide'
					});
				}
			});
			$("#activities").combobox({ disabled: false }); 
		}
	}
	function addFun() {
		$('#dictForm').form('clear');
		var node = $('#dictTree').tree('getSelected');
		if (node != null) {
			$("#activities").combobox({ readonly: true }); 
				$('#parentcata').val(node.id);
				$('#parentcataName').textbox('setValue', node.name);
				$('#activities').combobox('setValue', node.activities);
				
		} else {
			$('#parentcata').val(100);
			$('#dictPname').textbox('setValue', '无');
		
		}
			$("#previewFile").attr("src", "${path}/static/images/tpsc.jpg");
			$("#pic").val("");
	}
	function deleteFun() {
		var node = $('#dictTree').tree('getSelected');
		if (node != null) {
			if ($('#dictTree').tree('isLeaf', node.target)) {
				$.messager.confirm('确认对话框', '您确定要删除吗？', function(r) {
					if (r) {
						$.ajax({
							url : '${path}/cata/delete',
							data : {
								id : node.id
							},
							dataType : 'json',
							type : 'post',
							success : function(result) {
								$('#dictForm').form('clear');
								$('#dictTree').tree('options').queryParams = {
									pid : "100"
								};
								$('#dictTree').tree('reload');
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
			} else {
				$.messager.show({
					title : '消息提示',
					msg : '请先删除子节点',
					timeout : 3000,
					showType : 'slide'
				});
			}
		} else {
			$.messager.show({
				title : '消息提示',
				msg : '请选择要删除的节点',
				timeout : 3000,
				showType : 'slide'
			});
		}
	}
	function uploadFile(){
		$("#picFile").click();
		
	}
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:false,border:false"
		style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
		<shiro:hasPermission name="/cata/save">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-add'"
				onclick="addFun();">添加</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-save'"
				onclick="saveFun();">保存</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="/cata/delete">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-del'"
				onclick="deleteFun();">删除</a>
		</shiro:hasPermission>
	</div>
	<div data-options="region:'west',split:false,border:false"
		style="width:350px;border-right:1px solid #d3d3d3">
		<ul id="dictTree"></ul>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<form id="dictForm">
			<input type="hidden" id="id" name="id" /> <input type="hidden"
				id="root" name="root" /> <input type="hidden" id="creator"
				name="creator" /> <input type="hidden" id="picWidth"
				name="picWidth" /> <input type="hidden" id="picHeight"
				name="picHeight" /> <input type="hidden" id="template"
				name="template" /> <input type="hidden" id="arttemplate"
				name="arttemplate" /> <input type="hidden" id="showonindex"
				name="showonindex" /> <input type="hidden" id="showNextColumn"
				name="showNextColumn" /> <input type="hidden" id="parentcata"
				name="parentcata" />
			<table class="formTable" style="width:50%">
				<tr>
					<th>父栏目：</th>
					<td><input type="text" class="easyui-textbox"
						data-options="width:'100%'" id="parentcataName"
						name="parentcataName" /></td>
				</tr>
				<tr>
					<th>栏目名称：</th>
					<td><input type="text" class="easyui-textbox"
						data-options="width:'100%',required:true" id="name" name="name" />
					</td>
				</tr>
					<tr>
					<td>是否为公共栏目</td>
					<td><select id="iscommon" name="iscommon"
						class="easyui-combobox"
						data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0">否</option>
							<option value="1" selected="selected">是</option>
					</select>
					</td>
				</tr>
				<%--
				<tr>
					<th>活动：</th>
					<td><select style="width:100px;border:1px solid gray;" class="easyui-combobox"
						id="activities" name="activities">
							<c:forEach var="act" items="${acts }">
								<option value="${act.id }">${act.name }</option>
							</c:forEach>
					</select></td>
				</tr>
				--%>
				<tr>
					<th>栏目图标：</th>
					<td><img onclick="uploadFile()" src="${path }/static/images/tpsc.jpg" id="previewFile"
						style="width:100px;height:100px;" />
						<input type="file" id="picFile" style="display:none;"/>
						<input type="hidden" name="pic" id="pic" value="pic" /></td>
				</tr>
					<tr>
					<th>所属部门：</th>
					<td><select style="width:400px;border:1px solid gray;" class="easyui-combobox" 
					 data-options="editable:false,panelHeight:'500'"
						id="organ" name="organ">
						        <option value="" selected="selected"></option>
							<c:forEach var="org" items="${organs}">
								<option value="${org.id}">${org.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>是否启用</td>
					<td><select id="isenable" name="isenable"
						class="easyui-combobox"
						data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0">是</option>
							<option value="1" selected="selected">否</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>描述：</th>
					<td><textarea id="descn" name="descn"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>