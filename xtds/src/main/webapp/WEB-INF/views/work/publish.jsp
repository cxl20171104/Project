<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>文章发布</title>
<script type="text/javascript">
	$(function() {
		chengeImg();
		var buttons = $.extend([], $.fn.datebox.defaults.buttons);
		buttons.splice(1, 0, {
			text : '永久有效',
			handler : function(target) {
				$("#expire").datetimebox("setValue", '9999-12-31:23:59:59')
				$("#expire").datetimebox('hidePanel');
			}
		});
		$("#expire").datetimebox({
			buttons : buttons
		});
		
		//===================================================================
		$('#catalogs')
				.combotree(
						{
							panelWidth : 500,
							panelHight : 500,
							multiple: true,
							url : "${path}/cata/cataTree?pid=100&type=0",
							onBeforeExpand : function(node) {
								$('#catalogs').combotree("tree")
										.tree('options').url = "${path}/cata/cataTree?pid="
										+ node.id+"&type=0";
							},
							onBeforeSelect : function(node) {
							
							
								$("#actName").combobox('setValue', ' ');
								$("#type").combobox('setValue', ' ');
							},
							onCheck:function(node){
								
								$("#actName").combobox('setValue', ' ');
								$("#type").combobox('setValue', ' ');
				
							},
							  onLoadSuccess:function(node,data){
								    
							    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
							    	var choice="${article.catalogs}";
							    	
							    		
							    
							    	var ids=new Array();
							    	if(choice.indexOf(",")>=0){
							    		ids=choice.split(",");
							    	}else{
							    		ids.push(choice);
							    	}
							    	
							    	$('#catalogs').combotree('tree').tree('expandAll');
							    	//每次加载的选项
							    	console.log(data);
							    	for(var i=0;i<data.length;i++){
							    	
							    		for(var j=0;j<ids.length;j++){
							    			
							    			if(data[i].id==ids[j]){
							    				var node = $('#catalogs').combotree('tree').tree('find', data[i]. id);
							    				$('#catalogs').combotree('tree').tree('check', node.target);
							    			}
							    		}
							    	}
							    	//我的选项
							    	console.log(ids);
							    },loadFilter : function(data) {
								if (data.obj) {
									return data.obj;
								} else {
									return data;
								}
							}
						});
						
        //-------------------------------------------------------------------------------
       
		
	});
	function chengeImg(){
		$("#headpicFile").change(
				function() {
					if ($("#headpicFile").val() != null
							&& $("#headpicFile").val() != ""
							&& $("#headpicFile").val() != undefined) {
						var formData = new FormData();
						formData.append("upload", document
								.getElementById("headpicFile").files[0]);
						$.ajax({
							url : "${path}/com/upload",
							type : "POST",
							dataType : "json",
							data : formData,
							/**
							 *必须false才会自动加上正确的Content-Type
							 */
							contentType : false,
							/**
							 * 必须false才会避开jQuery对 formdata 的默认处理
							 * XMLHttpRequest会对 formdata 进行正确的处理
							 */
							processData : false,
							success : function(data) {
								if (data.success) {
									$("#headpic").val(data.obj);
									$("#previewFile").attr("src",
											"${path}/files/" + data.obj);
									chengeImg();
								} else {
									alert("图片上传失败，请重试");
									$("#headpicFile").val("");
								}
							},
							error : function() {
								alert("图片上传失败，请重试");
								$("#headpicFile").val("");
							}
						});
					}
				});
	}
	function uploadFile() {
		$("#headpicFile").click();

	}
	function activ(node){
		$('#catalogs').textbox('setValue', node.value);
		$("#cataName").combotree('setValue', '  ');
		
	}
	
</script>
</head>
<body class="easyui-layout">
		  <div data-options="region:'west',split:false,border:false" style="width:32%;border-right:1px solid #d3d3d3">
		    <!-- 文章发布时提交的表单 -->
			<form method="post" class="easyui-form" id="cataForm" style="width:100%">
				<input id="id" name="id" type="hidden" value="${article.id }" /> 
				<input id="author" name="author" type="hidden" value="${article.author }" />
				<input id="inputer" name="inputer" type="hidden"value="${article.inputer }" /> 
				<input id="hits" name="hits" type="hidden" value="${article.hits }" /> 
				<input id="thumb"name="thumb" type="hidden" value="${article.thumb }" /> 
				<input id="comnum" name="comnum" type="hidden" value="${article.comnum }" />
				<input id="thumber" name="thumber" type="hidden"value="${article.thumber }" /> 
				<input id="area" name="area" type="hidden" value="${article.area }" /> 
				<input id="refusecontent" name="refusecontent" type="hidden" value="${article.refusecontent }" /> 
				<input id="audituser" name="audituser" type="hidden" value="${article.audituser}" /> 
				<input id="state" name="state" type="hidden" value="${article.state}" />
				<input  id="content" name="content"  type="hidden" />
				<table class="m-table m-table-rds" style="width: 100%;">
					<tbody>
					    <tr>
					      <td colspan="2"><hr></td>
					   
					   </tr>
					   <tr>
					   <th width="150px"><span style="color:red;">*</span>文章封面：</th>
					   <td ><c:choose>
									<c:when test="${article!=null and article['headpic']!='' and  article['headpic']!=null }">
										<img id="previewFile" style="max-height: 100px;"onclick="uploadFile();"src="${path }/files/${article.headpic }" />
										<input name="headpic" id="headpic" type="hidden" value="${article.headpic }" />
										<input name="headpicFile" id="headpicFile" type="file" style="display:none" />
									</c:when>
									<c:otherwise>
										<img id="previewFile" style="max-height: 100px;" onclick="uploadFile();" src="${path }/static/images/tpsc.jpg" />
										<input name="headpic" id="headpic" type="hidden" value="" />
										<input name="headpicFile" id="headpicFile" type="file" style="display:none" />
									</c:otherwise>
								</c:choose></td>
					   
					   </tr>
					
					
					
					
					   <tr>
					      <td colspan="2"><hr></td>
					   
					   </tr>
					
						<tr>
							<th width="140px"><span style="color:red;">*</span>栏目文章：</th>
							<td width="600px;">
							  <c:choose>							 
								   <c:when test="${content==null}">
									 <select id="catalogs" data-options="required:true,editable:false"class="easyui-combotree" style="width:200px;"name="catalogs"></select> 
									
								   </c:when>
								   <c:otherwise>
								             <select id="catalogs" data-options="required:true,editable:false" class="easyui-combotree" style="width:200px;" name="catalogs"></select>
								             
								   </c:otherwise>
							    </c:choose>
							
							
							</td>					     
						</tr>
						
						
					   <tr>
					      <td colspan="2"><hr></td>
					   
					   </tr>
						<shiro:hasPermission name="/artOnAct">
						<tr>
						    <th width="140px"><span style="color:red;">*</span>活动文章：</th>
							<td width="600px;">
							<c:choose>
							    <c:when test="${content==null}">
									<select id="actName" 
										class="easyui-combobox" style="width:200px;"
										data-options="onSelect:activ,required:true,editable:false"  name="actName">
										   <c:forEach var="act" items="${acts}">
										     <option value="${act.id }">${act.name}</option>
									      </c:forEach>
									</select>
							    </c:when>
							    <c:otherwise>
							           <select id="actName" 
										class="easyui-combobox" style="width:200px;"
										data-options="onSelect:activ,required:true,editable:false"  name="actName">
										   <c:forEach var="act" items="${acts}">
										     <option value="${act.id }">${act.name}</option>
									      </c:forEach>
									     </select>
							           <input class="easyui-textbox" value="${article.actname}"  readonly="readonly"/>
							    </c:otherwise>
							</c:choose>
							     
						    </td>
						      
						</tr>
						<tr>
						
						
						<th><span> 文章类型：</span></th>
							    <td> <input id="type" class="easyui-combobox" name="scores" data-options="
						                value:'${article.scores}',
										valueField: 'value',
										textField: 'label',
										data: [{
											label: '活动方案',
											value: '1'
										},{
											label: '活动图片',
											value: '2'
										},{
											label: '制度规定',
											value: '3'
										},{
											label: '学习资料',
											value: '4'
										}]" /></td>
						    
						
						
						</tr>
						</shiro:hasPermission>
					   <tr>
					      <td colspan="2"><hr></td>
					   
					   </tr>
						<tr>
						
						      <th> </span>发布时间：</span></th>
						     
								<td><input class="easyui-datetimebox" name="createtime" data-options="required:true,showSeconds:true" value="${article.ctime }" style="width:150px"></td>  
						
						
						</tr>
						
						 <tr>
					      <td colspan="2"><hr></td>
					   
					     </tr>
						
						
						<tr>
							<th width="140px"><span style="color:red;">*</span>文章标题：</th>
							<td><input class="easyui-validatebox"
								data-options="required:true" type="text" name="title" id="title"
								style="width:400px;" value="${article.title }" />
							</td>
						</tr>
						<tr>
							<th width="140px"><span style="color:red;"></span>短标题：</th>
							<td><input data-options="validType:['length[0,20]']"
								placeholder="短标题，最多20个字" class="easyui-validatebox" type="text"
								name="shorttitle" id="shorttitle" style="width:400px;"
								value="${article.shorttitle }" />
							</td>

						</tr>
						<tr>
							<th width="140px"><span style="color:red;"></span>副标题：</th>
							<td><input class="easyui-textbox" type="text"
								name="subtitle" id="subtitle" style="width:400px;"
								value="${article.subtitle }" />
							</td>
							
						</tr>
						<tr>
						
					   <tr>
					      <td colspan="2"><hr></td>
					   
					   </tr> 
						
						
						</tr>
						<tr>
						    <th width="140px"><span style="color:red;"></span>保存文章：</th>
				           <td> <a href="javascript:void(0)" onclick="saveArticle();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a></td>
						</tr>
						
						<tr>
						
						<th width="140px"><span style="color:red;"></span>发文须知：</th>
						<td> <textarea rows="5" cols="30" style="font-size:20px;">1.栏目文章、活动文章二选一发布2.发布活动文章需要选择文章类型3.栏目文章不需要选择文章类型4.发布视频要是MP4格式的</textarea> </td>
						
						</tr>
						<!-- <tr>
							<th width="120px"><span style="color:red;margin:3px;">*</span>是否允许评论：</th>
							<td><input name="comentable" type="radio" value="1"
								id="showOnTop1" /> <label for="showOnTop1">是</label> <input
								name="comentable" type="radio" value="0" id="showOnTop0"
								checked="checked" /> <label for="showOnTop0">否</label>
							</td>
						</tr> -->
						<%-- <tr>
							<th width="120px"><span style="color:red;margin:3px;">*</span>积分方式：</th>
							<td><input name="scoretype" type="radio" value="3"
								id="showOnTop21" /> <label for="showOnTop21">无积分</label> <input
								name="scoretype" type="radio" value="1" id="showOnTop11" /> <label
								for="showOnTop11">查看即得分</label> <input name="scoretype"
								type="radio" value="0" id="showOnTop01" checked="checked" /> <label
								for="showOnTop01">评论即得分</label> <span
								style="color:red;margin:3px;">*</span>
							</td>

						</tr>
						<tr>
							<th width="120px"><span style="color:red;margin:3px;">*</span>分值：</th>
							<td><input placeholder="无积分时填写无效" class="easyui-numberbox"
								type="text" name="scores" id="scores" value="${article.scores }" />
								&nbsp;积分有效期:<input id="expire" class="easyui-datetimebox"
								name="expire" value="${article.expire }" /></td>
						</tr>
 --%>
					</tbody>
				</table>
			    </form>
		</div>
		<div data-options="region:'center',fit:true,border:false" >
		
		<script type="text/plain" id="editor" style="width:82%;" name="editor"></script>
		
		</div>
		
	<script type="text/javascript">
		window.UEDITOR_HOME_URL = "${path}/static/ueditor/";
	</script>
	<script type="text/javascript" charset="utf-8"
		src="${path }/static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${path }/static/ueditor/ueditor.all.min.js">
		
	</script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8"
		src="${path }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
	<LINK rel=stylesheet
		href="${path }/static/ueditor/themes/default/css/ueditor.css">
	<script>
		function HTMLDecode(text) {
			var temp = document.createElement("div");
			temp.innerHTML = text;
			var output = temp.innerText || temp.textContent;
			temp = null;
			return output;
		}
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor', {
			toolbars : [ [ 'source', 'bold', 'italic', 'underline',
					'fontborder', 'strikethrough', 'superscript', 'subscript',
					'removeformat', 'formatmatch', 'autotypeset', '|',
					'forecolor', 'backcolor', 'insertorderedlist',
					'insertunorderedlist', '|', 'rowspacingtop',
					'rowspacingbottom', 'lineheight', '|', 'fontfamily',
					'fontsize', '|', 'directionalityltr', 'directionalityrtl',
					'indent', '|', 'justifyleft', 'justifycenter',
					'justifyright', 'justifyjustify', '|', 'imagenone',
					'imageleft', 'imageright', 'imagecenter', '|',
					'simpleupload', 'insertimage', '|', 'horizontal',
					'spechars', '|', 'inserttable', 'deletetable',
					'insertparagraphbeforetable', 'insertrow', 'deleterow',
					'insertcol', 'deletecol', 'mergecells', 'mergeright',
					'mergedown', 'splittocells', 'splittorows', 'splittocols',
					'charts', '|', 'preview', 'searchreplace', 'FullScreen','insertvideo' ] ]
		});
		ue.addListener("ready", function() {
			UE.getEditor('editor').setHeight(760);
				 insertHtml(HTMLDecode("${content}"));
			if ("${article.id != null}")
				;
		});
		function isFocus(e) {
			alert(UE.getEditor('editor').isFocus());
			UE.dom.domUtils.preventDefault(e)
		}
		function setblur(e) {
			UE.getEditor('editor').blur();
			UE.dom.domUtils.preventDefault(e)
		}
		function insertHtml(value) {
			UE.getEditor('editor').execCommand('insertHtml', value)
		}
		function createEditor() {
			enableBtn();

		}
		function getAllHtml() {
			alert(UE.getEditor('editor').getAllHtml())
		}
		function getContent() {
			var arr = [];
			arr.push("使用editor.getContent()方法可以获得编辑器的内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getContent());
		}
		function getPlainTxt() {
			var arr = [];
			arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
			arr.push("内容为：");
			arr.push(UE.getEditor('editor').getPlainTxt());
		}
		function setContent(isAppendTo) {
			UE.getEditor('editor').setContent('${article.content}', isAppendTo);
		}
		function setDisabled() {
			UE.getEditor('editor').setDisabled('fullscreen');
			disableBtn("enable");
		}

		function setEnabled() {
			UE.getEditor('editor').setEnabled();
			enableBtn();
		}

		function getText() {
			//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
			var range = UE.getEditor('editor').selection.getRange();
			range.select();
			var txt = UE.getEditor('editor').selection.getText();
		}

		function getContentTxt() {
			var arr = [];
			arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
			arr.push("编辑器的纯文本内容为：");
			arr.push(UE.getEditor('editor').getContentTxt());
		}
		function hasContent() {
			var arr = [];
			arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
			arr.push("判断结果为：");
			arr.push(UE.getEditor('editor').hasContents());
		}
		function setFocus() {
			UE.getEditor('editor').focus();
		}
		function deleteEditor() {
			disableBtn();
			UE.getEditor('editor').destroy();
		}
		function disableBtn(str) {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for ( var i = 0, btn; btn = btns[i++];) {
				if (btn.id == str) {
					UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
				} else {
					btn.setAttribute("disabled", "true");
				}
			}
		}
		function enableBtn() {
			var div = document.getElementById('btns');
			var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
			for ( var i = 0, btn; btn = btns[i++];) {
				UE.dom.domUtils.removeAttributes(btn, [ "disabled" ]);
			}
		}

		function getLocalData() {
			alert(UE.getEditor('editor').execCommand("getlocaldata"));
		}

		function clearLocalData() {
			UE.getEditor('editor').execCommand("clearlocaldata");
		}

		$('#cataForm').form({
			url : '${path}/article/save',
			onSubmit : function() {
				progressLoad();
				$("#content").val(UE.getEditor('editor').getContent());
				var isValid = $(this).form('validate');
				
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
                               
                               
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.messager.alert('消息', result.msg, 'info');
					$('#cataForm').form("clear");
					clearLocalData();

				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		function saveArticle() {
			
			$('#cataForm').form("submit");
		}
	</script>
</body>
</html>