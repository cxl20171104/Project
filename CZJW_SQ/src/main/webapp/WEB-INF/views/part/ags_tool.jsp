<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<script type="text/javascript">
            function file(id){
            $("#fileName").val(id);
	        $('#formx').form('submit', {    
		    url:"${path}/ags_tools/tool0",    
		    onSubmit: function(){    
		    	var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.alert('我的消息','线索不存在！','info');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
    
		    }   
		     
		});  
  }
  function load(){
	 $("#cluesId").textbox('setValue',$("#probleCluesId").val());
 }
</script>
    <form id="formx">
        <input type="hidden" id="cluesId" class="easyui-textbox" name="cluesId" value=""  required="required"/> 
        <input type="hidden" id="fileName"  name="fileName" value="" />
    <c:if test='${pageName=="newClues"}'>
    <table class="cxl" style="width:67%">
          <tr >
          <td><h2>分办时间</h2></td>
          <td><input id="fileTime" type="text"  name="fileTime" class="easyui-datebox" value="<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd"/>"  style="width:300px;height:45px;"></input> 
          </td>
          </tr>
    </table>
    </c:if>
    </form>
     <table class="cxl">
          <tr>
           <th>名称</th>
           <th>备注</th>
           <th>下载</th>
          </tr>
          <tr>
          <td><h2>沧州市纪委监委问题线索分办呈批签(处级)</h2></td>
          <td>初版</td>
          <td><button onclick="file('0');">下载</button></td>
          </tr>
          <tr>
          <td><h2>沧州市纪委监委问题线索分办呈批签(处级)(2)</h2></td>
          <td>去掉了“分管领导批示这一项”</td>
          <td><button onclick="file('01');">下载</button></td>
          </tr>
          <tr>
          <td><h2>沧州市纪委监委问题线索分办呈批签(科级及以下)</h2></td>
           <td>初版</td>
          <td><button onclick="file('1');">下载</button></td>
         </tr>
          <tr>
          <td><h2>沧州市纪委监委问题线索分办呈批签(科级及以下)(2)</h2></td>
          <td>去掉了“分管领导批示这一项”</td>
          <td><button onclick="file('11');">下载</button></td>
         </tr>
          <tr>
          <td><h2>沧州市纪委监委问题线索分办呈批签(科级及以下)(3)</h2></td>
          <td>把“分管领导批示这一项” 改为“主要领导批示”</td>
          <td><button onclick="file('12');">下载</button></td>
         </tr>
          <tr>
          <td><h2>沧州市纪委监委问题线索分办呈批签(科级及以下)(4)</h2></td>
          <td>增加“主要领导批示”</td>
          <td><button onclick="file('13');">下载</button></td>
         </tr>
   </table>
  
    
    
    
