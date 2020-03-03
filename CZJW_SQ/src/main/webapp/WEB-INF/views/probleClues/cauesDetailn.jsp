<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  
                         
                                       <table class="table  table-bordered table-hover">
                                            <tr>
                                            <th>收件日期</th>
                                            <td>
                                              <a class='input-group date' id='c1'">
                                                 <input name="receiveDate" type='text' class="form-control" id='receiveDate' style=" width: 170px; height: 34px;" value="${problemClues.receiveDate }" disabled/>
                                                 <span class="input-group-addon" style="width: 50px; height: 30px;">
                                                               <span class="glyphicon glyphicon-calendar"></span>
                                                 </span>
                                                 </a>   
                                             </td>
                                             <th>线索来源</th>
                                            <td>
                                            <select id="clues" name="clues" style=" width: 220px; height: 34px;" class="form-control" disabled>
                                              <option value="0">请选择</option>
						                          <c:forEach var="act" items="${clues}">
						                          <c:if test="${act.value ==problemClues.clues }">
						                                 <option selected="selected" value="${act.value }">${act.name}</option>
						                          </c:if>
						                           <c:if test="${act.value !=problemClues.clues }">
								                            <option value="${act.value }">${act.name}</option>
								                   </c:if>
		                                          </c:forEach>
                                            </select>
                                            </td>
                                              <th>问题属地</th>
                                            <td>
                                            <select id="problemLand"style=" width: 220px; height: 34px;" name="problemLand"  class="form-control"  disabled>
                                                  <option value="0">请选择</option>
						                          <c:forEach var="act" items="${problemLand}">
								                  <c:if test="${act.value ==problemClues.problemLand}">
						                                 <option selected="selected" value="${act.value }">${act.name}</option>
						                          </c:if>
						                           <c:if test="${act.value != problemClues.problemLand }">
								                             <option value="${act.value }">${act.name}</option>
								                   </c:if>
		                                          </c:forEach>
                                            </select>
                                            </td>
                                            </tr>
                                            <tr>
                                             <th>所属领域</th>
                                            <td>
                                            <select id="fields"  name="fields" style=" width: 200px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
                                                   
						                          <c:forEach var="act" items="${fields}">
								                     <option value="${act.value }">${act.name}</option>
		                                          </c:forEach>
                                            </select>
                                           </td>  
                                            <th>专项行动</th>
                                            <td>
                                            <select id="special" name="special" style=" width: 220px; height: 34px;" class="form-control"  disabled>
                                              <option value="0">请选择</option>
						                          <c:forEach var="act" items="${special}">
								                 <c:if test="${act.value ==problemClues.special}">
						                                 <option selected="selected" value="${act.value }">${act.name}</option>
						                          </c:if>
						                           <c:if test="${act.value != problemClues.special }">
								                             <option value="${act.value }">${act.name}</option>
								                   </c:if>
		                                          </c:forEach>
                                            </select>
                                            </td>
                                             <th>是否单位或事件事故</th>
                                            <td>
                                            <select id="belongToId" style=" width: 220px; height: 34px;"name="belongToId"  class="form-control"  disabled>
                                             <option value="0" >请选择</option>
                                             <c:if test="${'1' ==problemClues.belongToId}">
								              <option selected="selected" value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
								            <c:if test="${'2' ==problemClues.belongToId}"> 
								            <option  value="1">是</option>  
								            <option selected="selected"  value="2">否</option>
								            </c:if>
								            <c:if test="${'2' !=problemClues.belongToId && '1' !=problemClues.belongToId}"> 
								            <option  value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
                                            </select>
                                            </td>   
                                            </tr>
                                            <tr>                                             
                                             <th>是否重要问题</th>
                                             <td>
                                             <select id="isImport" name="isImport" style=" width: 220px; height: 34px;" class="form-control"  disabled>
                                             <option value="0" >请选择</option>
                                             <c:if test="${'1' ==problemClues.isImport}">
								              <option selected="selected" value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
								            <c:if test="${'2' ==problemClues.isImport}"> 
								            <option  value="1">是</option>  
								            <option selected="selected"  value="2">否</option>
								            </c:if>
								            <c:if test="${'2' !=problemClues.isImport && '1' !=problemClues.isImport}"> 
								            <option  value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
                                            </select>
                                            </td>
                                            <th>是否要结果</th>
                                              <td>
                                              	<select id="isResult" name="isResult" style=" width: 220px; height: 34px;" class="form-control"   onChange="return isResult2()" disabled>
                                              <option value="0" >请选择</option>
                                              <c:if test="${'1' ==problemClues.isResult}">
								              <option selected="selected" value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
								            <c:if test="${'2' ==problemClues.isResult}"> 
								            <option  value="1">是</option>  
								            <option selected="selected"  value="2">否</option>
								            </c:if>
								            <c:if test="${'2' !=problemClues.isResult && '1' !=problemClues.isResult}"> 
								            <option  value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
                                            </select>
                                            </td>
                                            
                                            <th>办理期限</th>
                                             <td> <a class='input-group date' id='c32'">
                                                 <input type='text' id="ress" class="form-control" name='resultTime' style="width: 170px; height: 34px;" value="${problemClues.resultTime}" disabled/>
                                                 <span class="input-group-addon" style="width: 50px; height: 30px;">
                                                               <span class="glyphicon glyphicon-calendar"></span>
                                                 </span>
                                                 </a>      
                                            </td>
                                            </tr>
                                            
                                            <tr>
                                            <th>类型</th>
                                            <td>
                                                <select id="type" style=" width: 220px; height: 34px;" name="type"  class="form-control" disabled>
                                                    <option value="0" >请选择</option>
		                                            <c:if test="${'1' ==problemClues.type}">
										            <option selected="selected" value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
										            <c:if test="${'2' ==problemClues.type}"> 
										            <option value="1">了解关注类</option>  
										            <option selected="selected" value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
										            <c:if test="${'3' ==problemClues.type}"> 
										            <option value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option selected="selected" value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
										            <c:if test="${'4' ==problemClues.type}"> 
										            <option value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option selected="selected" value="4">其他</option>
										            </c:if>
										            <c:if test="${'2' !=problemClues.type && '1' !=problemClues.type && '3' !=problemClues.type && '4' !=problemClues.type}"> 
										            <option value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
                                            </select>
                                           </td> 
                                                <c:if test="${problemClues.lwbh!=null}">
                                                 <th>来文编号</th>
                                                 <td>
                                                   
                                                    <input type="text" class="form-control"  placeholder="请输入名称" style="width: 220px; height: 34px;" value="${problemClues.lwbh}" disabled>   
                                                   
                                                 </td>
                                                  </c:if>
                                            <th>
                                            </th>
                                            <td>
                                            </td>
                                            </tr>
                                            <tr>                                            
                                           <th>问题描述</th>
                                            <td colspan="5">
                                            <textarea name="problemDescribe" class="form-control" rows="3" disabled>${problemClues.problemDescribe}</textarea>
                                            </td>
                                            
                                            </tr>
                                            <tr>
                                            <th>备注</th>
                                            <td colspan="5">
                                            <textarea name="remarks" class="form-control" rows="3" disabled>${problemClues.remarks}</textarea>
                                            </td>
                                          
                                            </tr>
                                            </table>
                             
                             
<script type="text/javascript">
	
	var str='${problemClues.fields}';
	if(str!=null&&str!=""){
		 var arr=str.split(',');
		    $('#fields').selectpicker('val', arr);
	}
	var ress = '${problemClues.reflectedPerson.worktime}';
	if(ress!=null&&ress!=""){
		
	}
	$(".selectpicker").selectpicker({  
        noneSelectedText : '请选择'//默认显示内容  
    });
</script>