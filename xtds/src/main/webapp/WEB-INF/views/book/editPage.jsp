<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	

       
    	chengeImg();
       

        $('#userEditForm').form({
            url : '${path }/book/edit',
            onSubmit : function() {
                progressLoad();
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
        $("#status").val('${user.status}');
    });
    
    function uploadFile() {

        $("#file").click();

    }
    function chengeImg(){
		$("#file").change(
				function() {
					if ($("#file").val() != null
							&& $("#file").val() != ""
							&& $("#file").val() != undefined) {
						var formData = new FormData();
						formData.append("upload", document
								.getElementById("file").files[0]);
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
									$("#headPic").val(data.obj);
									$("#previewFile").attr("src",
											"${path}/files/" + data.obj);
									chengeImg();
								} else {
									alert("图片上传失败，请重试");
									$("#file").val("");
								}
							},
							error : function() {
								alert("图片上传失败，请重试");
								$("#file").val("");
							}
						});
					}
				});
		$("#filePath").change(
				function() {
					if ($("#filePath").val() != null
							&& $("#filePath").val() != ""
							&& $("#filePath").val() != undefined) {
						var formData = new FormData();
						formData.append("upload", document
								.getElementById("filePath").files[0]);
						$("#fileState").text(".....文件上传中......");
						$.ajax({
							url : "${path}/com/uploadLarge",
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
									$("#filePic").val(data.obj);
									$("#fileState").text("......文件上传完毕！");
									chengeImg();
								} else {
									alert("文件上传失败，请重试");
									$("#filePath").val("");
								}
							},
							error : function() {
								alert("文件上传失败，请重试");
								$("#filePath").val("");
							}
						});
					}
				});
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <form id="userEditForm" method="post">
        	<input type="hidden" id="id" name="id" value="${book.id }" />
        	
        	 <table class="formTable">
                <tr>
                    <th>图书名称：</th>
                    <td><input name="name" type="text" placeholder="请输入礼品名称" class="easyui-textbox" data-options="width:'100%',required:true" value="${book.name }"></td>
                    </tr>
                <tr>
                    <th>兑换开始时间：</th>
                    <td>
                        <input name="startTime" type="text" class="easyui-datebox" data-options="width:'100%',required:false,editable:false" value="${book.startTime}"></input>                          
                    </td> 
                    </tr>
                    <tr>
                    <th>兑换结束时间：</th> 
                    <td>
                        <input name="endTime" type="text" class="easyui-datebox" data-options="width:'100%',required:false,editable:false" value="${book.endTime}"></input>                          
                    </td>               
                </tr>
                <tr>
                    <th>详情：</th>
                    <td>
                        <textarea name="detail"  id="detail" rows="" cols="">${book.detail}</textarea>
                    </td>
                    
                </tr>
                 <tr>
                   <th>图书封面：</th>
                   <td>
                      <c:choose>
                      <c:when test="${book!=null and book['headPic']!='' and  book['headPic']!=null }">
                      <img id="previewFile" style="max-height: 100px;" onclick="uploadFile();" src="${path }/files/${book.headPic }"  />
                      <input name="headPic" id="headPic"  type="hidden" value="${book.headPic }" />
                      <input name="file" id="file" type="file" style="display:none" />
                      </c:when>
                      <c:otherwise>
                      <img id="previewFile" style="max-height: 100px;" onclick="uploadFile();" src="${path }/static/images/tpsc.jpg" />
                      <input name="headPic" id="headPic" type="hidden" value="" />
                      <input name="file" id="file" type="file" style="display:none" />
                      </c:otherwise>
                      </c:choose>
                   </td>
                </tr>
                <tr>
                 <th>选择图书：</th>
                   <td>
                      <input name="filePic" id="filePic" type="hidden" value="${book.filePic }" />
                      <input name="filePath" id="filePath" type="file"  />
                      <span id="fileState"></span>
                   </td>
                 </tr>                  
            </table>            
        </form>
    </div>
</div>