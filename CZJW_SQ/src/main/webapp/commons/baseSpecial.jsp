<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<a  class="easyui-linkbutton"  style="width:80px;height:25px;text-align:center;">专项行动</a>
<select id="special" class="easyui-combobox" name="problemClues.special" style="width:150px;height:25px;"> 
			 				<option value="">请选择</option>
						    <c:forEach var="act" items="${special}">
						    	 <c:if test="${act.value !=''&&act.value!=null }">
						    	     <c:if test="${organId=='27'&&act.id!='288' }">
								  	 <option value="${act.value }">${act.name}</option>
								  	</c:if>
								  	 <c:if test="${organId!='27'}">
								  	 <option value="${act.value }">${act.name}</option>
								  	 </c:if>
								 </c:if>
		                    </c:forEach>
			 </select>

