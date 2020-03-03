<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="cluesForm" name="cluesForm" >
	                        <input type="hidden"  id="probleCluesId" name="id" value="${problemClues.id}" /> 
							<input type="hidden" id="caseId2"  name="cluesNum" value="${problemClues.cluesNum}" /> 
							<input type="hidden" id="reflectedId" name="reflectedPerson.id" value="${problemClues.reflectedPerson.id}" /> 
							<input type="hidden" name="reflectingPerson.id" value="${problemClues.reflectingPerson.id}" />
							<input type="hidden" name="isXf" value="${problemClues.isXf}" />
							<input type="hidden" id="flag" name="flag" value="${flag}" /> 
							<input   id="pageName" type="hidden"  value="${pageName}" /> 
							<input   id="organId"  type="hidden" value="${organId}" /> 
					        <!-- 是否添加的是同案人员 -->
					        <input type="hidden" id="whereForm" name="whereFrom" value="putong" />

<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="save2" type="button" class="btn btn-default" onclick="save_clues();" >保存(LA)</button>
</c:if>
<c:if test="${fn:contains(roleId,'18')!=false||(flag==null||flag=='')&&(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'}">
<button  onclick="addTonanRen();" title="以添加新案件的方式">增加同案人员</button>
<button  onclick="bangTonanRen();" title="绑定现有案件">绑定同案人员</button>
</c:if>
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'}">
<button id="fh" type="button" class="btn btn-default" onclick="addRepeat();" title="绑定现有案件">绑定重复件</button>
</c:if>
<c:if test="${pageName=='waiting'}">
<button id="fh" type="button" class="btn btn-default" onclick="getIt('${problemClues.id}');">签收</button>
</c:if>	
<c:if test="${pageName=='waiting'}">
<button id="fh" type="button" class="btn btn-default" onclick="backIt('${problemClues.id}');">退回</button>
</c:if>
<table class="cxl">

<tr><th>被反映人信息</th><th><c:if test="${problemClues.id!=null&&problemClues.id!=''}">(<span >${problemClues.cluesNum })</span></c:if> </th></tr>
<tr>
<td><label class="col-md-1 control-label"> <span style="color: red; font-size: 22px">*</span>姓名</label></td>
<td>

<input class="easyui-textbox" id="reflectedName"name="reflectedPerson.reflectedName" value="${problemClues.reflectedPerson.reflectedName}"   style="width:300px;height:45px;" required="required"> 
</td>
<td><label>性别</label></td>
<td >
<select id="sex" name="reflectedPerson.sex" class="easyui-combobox" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.reflectedPerson.sex}">
									<option selected="selected" value="1">男</option>
									<option value="2">女</option>
								</c:if>
								<c:if test="${'2' ==problemClues.reflectedPerson.sex}">
									<option value="1">男</option>
									<option selected="selected" value="2">女</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.reflectedPerson.sex && '1' !=problemClues.reflectedPerson.sex}">
									<option value="1">男</option>
									<option value="2">女</option>
								</c:if>
</select>

</td>



<td><label><span style="color: red; font-size: 22px">*</span>民族</label></td>
<td><input id="mzData" type="hidden"value="${problemClues.reflectedPerson.nation }" /> 
<select id="mz" class="easyui-combobox" name="reflectedPerson.nation" style="width:300px;height:45px;" validType="comboxValidate['mz','该项为必选项']">
							</select></td>
<td><label><span style="color: red; font-size: 22px">*</span>证件类型</label></td>
<td><select id="documentType"  name="reflectedPerson.documentType"  class="easyui-combobox" style="width:300px;height:45px;" validType="comboxValidate['documentType','该项为必选项']" >
								<option value="0">请选择</option>
								<c:forEach var="act" items="${documentType}">
									<c:if test="${act.value ==problemClues.reflectedPerson.documentType}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.documentType }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
</tr>
<tr>
<td><label><span style="color: red; font-size: 22px">*</span>证件号码</label></td>
<td><input type="text" class="easyui-textbox"  id="identificationNumber"
								name="reflectedPerson.identificationNumber" placeholder="请输入证件号码"
								value="${problemClues.reflectedPerson.identificationNumber}" style="width:300px;height:45px;"required="required"/></td>




<td><label ><span style="color: red; font-size: 22px">*</span>出生年月</label></td>
<td><input id='birthday' type="text" name="reflectedPerson.birthday" placeholder="格式:2018-06" value="${problemClues.reflectedPerson.birthday}" style="width:300px;height:45px;" required="required"></input>  
<td><label >学历</label></td>
<td><input id="xlData" type="hidden" value="${problemClues.reflectedPerson.xl }" /> 
<select id="xl" class="easyui-combobox" name="reflectedPerson.xl" style="width:300px;height:45px;">
</select></td>
							
<td><label class="col-md-2 control-label">工作单位</label></td>
<td><input type="text" class="easyui-textbox"
								name="reflectedPerson.workPosition" placeholder="请输入名称"
								value="${problemClues.reflectedPerson.workPosition}" style="width:300px;height:45px;"></td>
</tr>
<tr>
<td><label class="col-md-2 control-label">职务</label></td>
<td><input type="text" class="easyui-textbox" placeholder="请输入名称"
								name="reflectedPerson.duty"
								value="${problemClues.reflectedPerson.duty}" style="width:300px;height:45px;"></td>

<td><label class="col-md-1 control-label"> <span style="color: red; font-size: 22px">*</span>职级</label></td>
<td>
<select id="rank" class="easyui-combobox" name="reflectedPerson.rank" onChange="check('rank','choice');" style="width:300px;height:45px;" validType="comboxValidate['rank','该项为必选项']" >
								<option value="0">请选择</option>
					<c:forEach var="act" items="${rank}">
								<option <c:if test="${act.value ==problemClues.reflectedPerson.rank}">selected="selected"</c:if> value="${act.value }">${act.name}</option>
					</c:forEach>
							</select>
</td>
<td><label class="col-md-2 control-label"><span style="color: red; font-size: 22px">*</span>任现职时间</label></td>
<td><input id="worktime" type="text" name='reflectedPerson.worktime' placeholder="格式:2018-06" value="${problemClues.reflectedPerson.worktime}" style="width:300px;height:45px;" required="required"></input>  
<td><label class="col-md-2 control-label">是否中共党员</label></td>
<td><select id="partyMember" name="reflectedPerson.partyMember" class="easyui-combobox" style="width:300px;height:45px;" >
								<option <c:if test="${'0' ==problemClues.reflectedPerson.partyMember}">selected="selected"  </c:if>  value="0" >请选择</option>
            					<option <c:if test="${'1' ==problemClues.reflectedPerson.partyMember}">selected="selected"  </c:if>  value="1" >是</option>
            					<option <c:if test="${'2' ==problemClues.reflectedPerson.partyMember}">selected="selected"  </c:if>  value="2" >否</option>
							</select></td>
</tr>
<tr>
<td><label class="col-md-1 control-label"><span style="color: red; font-size: 22px">*</span>政治面貌</label></td>
<td>
<select class="easyui-combobox" id="political" name="reflectedPerson.political"  style="width:300px;height:45px;" validType="comboxValidate['political','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${political}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.political}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.political }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
</td>

<td><label class="col-md-2 control-label"><span style="color: red; font-size: 22px">*</span>入党时间</label></td>
<td><input id="intime" type="text" name='reflectedPerson.intime' placeholder="格式:2018-06" value="${problemClues.reflectedPerson.intime }" style="width:300px;height:45px;" required="required"></input>  
</td>
   <td>反映违纪行为</td>	
	   <td>
		<select  style="width:300px;height:45px"  class="easyui-combotree"  id="fyzvviolation" name="reflectedPerson.fyzvviolation" >
          
        </select>
    </td>    
    <td>涉法行为</td>
          <td><select id="fylegalAct" class="easyui-combotree" name="reflectedPerson.fylegalAct" style="width:300px;height:45px;"></select>  
    </td>
</tr>


<tr>
<td><label class="col-md-1 control-label"><span style="color: red; font-size: 22px">*</span>身份</label></td>
<td><select class="easyui-combobox" id="civilServant" name="reflectedPerson.civilServant" style="width:300px;height:45px;" validType="comboxValidate['civilServant','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${civilServant}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.civilServant}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.civilServant }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-2 control-label"><span style="color: red; font-size: 22px">*</span>部门分类</label></td>
<td><select class="easyui-combobox" id="departmenType" name="reflectedPerson.departmenType" style="width:300px;height:45px;" validType="comboxValidate['departmenType','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${departmenType}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.departmenType}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.departmenType }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-2 control-label">企业性质</label></td>
<td><select class="easyui-combobox"
								name="reflectedPerson.natureOfenterprise" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${natureOfenterprise}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.natureOfenterprise}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value !=problemClues.reflectedPerson.natureOfenterprise }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-1 control-label">企业级别</label></td>
<td><select class="easyui-combobox"
								name="reflectedPerson.classOfenterprise" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${classOfenterprise}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.classOfenterprise}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.classOfenterprise }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
</tr>
<tr>
<td><label class="col-md-2 control-label">岗位</label></td>
<td><select class="easyui-combobox" name="reflectedPerson.post" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${post}">
									<c:if test="${act.value ==problemClues.reflectedPerson.post}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.post }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-2 control-label ">企业人员类别</label></td>
<td><select class="easyui-combobox" name="reflectedPerson.posType" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${posType}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.posType}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.posType }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-1 control-label"><span style="color: red; font-size: 22px">*</span>一把手违纪违法</label></td>
<td><select class="easyui-combobox" id="topDiscipline" name="reflectedPerson.topDiscipline" style="width:300px;height:45px;" validType="comboxValidate['topDiscipline','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${topDiscipline}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.topDiscipline}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.topDiscipline }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-2 control-label"><span style="color: red; font-size: 22px">*</span>干部管理权限</label></td>
<td><select class="easyui-combobox" id="cadre" name="reflectedPerson.cadre" style="width:300px;height:45px;" validType="comboxValidate['cadre','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${cadre}">
									<c:if test="${act.value ==problemClues.reflectedPerson.cadre}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.cadre }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
</tr>
<tr>
<td><label class="col-md-2 control-label"><span style="color: red; font-size: 22px">*</span>中共党代表</label></td>
<td><select class="easyui-combobox" id="partyRepresent" name="reflectedPerson.partyRepresent" style="width:300px;height:45px;" validType="comboxValidate['partyRepresent','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${partyRepresent}">
									<c:if test="${act.value ==problemClues.reflectedPerson.partyRepresent}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.partyRepresent }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-1 control-label"><span style="color: red; font-size: 22px">*</span>人大代表</label></td>
<td><select class="easyui-combobox" id="np" name="reflectedPerson.np" style="width:300px;height:45px;" validType="comboxValidate['np','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${np}">
									<c:if test="${act.value ==problemClues.reflectedPerson.np}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.np }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-2 control-label">党委委员</label></td>
<td><select class="easyui-combobox" name="reflectedPerson.dwMember" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${dwMember}">
									<c:if test="${act.value ==problemClues.reflectedPerson.dwMember}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.dwMember }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-2 control-label">纪委委员</label></td>
<td><select class="easyui-combobox" name="reflectedPerson.jwMember" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${jwMember}">
									<c:if test="${act.value ==problemClues.reflectedPerson.jwMember}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.jwMember }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
</tr>
<tr>
<td><label class="col-md-1 control-label"><span style="color: red; font-size: 22px">*</span>政协委员</label></td>
<td><select class="easyui-combobox" id="zx" name="reflectedPerson.zx" style="width:300px;height:45px;" validType="comboxValidate['zx','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${zx}">
									<c:if test="${act.value ==problemClues.reflectedPerson.zx}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.zx }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
<td><label class="col-md-2 control-label">是否违反中央八项规定精神</label></td>
<td><select id="eightSpirit" name="reflectedPerson.eightSpirit" class="easyui-combobox" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.reflectedPerson.eightSpirit}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==problemClues.reflectedPerson.eightSpirit}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.reflectedPerson.eightSpirit && '1' !=problemClues.reflectedPerson.eightSpirit}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select></td>
<td><label class="col-md-2 control-label">是否国家监察对象</label></td>
<td><select id="iSupervision" name="reflectedPerson.iSupervision" style="width:300px;height:45px;" class="easyui-combobox"  data-options="onSelect:function(rec){
isup(rec.value)
}"  style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.reflectedPerson.iSupervision}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==problemClues.reflectedPerson.iSupervision}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.reflectedPerson.iSupervision && '1' !=problemClues.reflectedPerson.iSupervision}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select></td>
<td></td>
<td><select  id="supervision"  style="width:300px;height:45px;" class="easyui-combobox" name="reflectedPerson.supervision"  disabled="disabled" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${supervision}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.supervision}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.supervision }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
</tr>
<tr>
    <td><label class="col-md-2 control-label">是否非党员非监察对象</label></td>
<td><select id="isPMSupervisoryObject" name="reflectedPerson.isPMSupervisoryObject" style="width:300px;height:45px;" class="easyui-combobox"  data-options="onSelect: function(rec){    
            isP(rec.value)  
            }" />
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.reflectedPerson.isPMSupervisoryObject}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==problemClues.reflectedPerson.isPMSupervisoryObject}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.reflectedPerson.isPMSupervisoryObject && '1' !=problemClues.reflectedPerson.isPMSupervisoryObject}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select></td>
<td></td>
<td><select  id="pMSupervisoryObject"   name="reflectedPerson.pMSupervisoryObject" class="easyui-combobox" disabled="disabled" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${pMSupervisoryObject}">
									<c:if
										test="${act.value ==problemClues.reflectedPerson.pMSupervisoryObject}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != problemClues.reflectedPerson.pMSupervisoryObject }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
</tr>
<tr><th>线索信息</th></tr>
<tr>
<td><label class="col-md-1 control-label"><span style="color: red; font-size: 22px">*</span> 线索来源</label></td>
<td><select id="clues" name="clues" class="easyui-combobox"  style="width:300px;height:45px;" validType="comboxValidate['clues','该项为必选项']">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${clues}">
									<c:if test="${act.value ==problemClues.clues }">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value !=problemClues.clues }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select><span id="cluesspan" style="color:red;"></span></td>
<td><label class="col-md-2 control-label"> <span style="color: red; font-size: 22px">*</span>收件日期</label></td>
<td><input id="receiveDate" type="text" name="receiveDate" class="easyui-datebox" value="${problemClues.receiveDate }" style="width:300px;height:45px;"  required="required"></input>  
</td>
<td><label class="col-md-1 control-label" id="lwbh2">来文编号</label></td>
<td><input id="lwbh" type="text" class="form-control"  name="lwbh" placeholder="请输入名称" value="${problemClues.lwbh}" style="width:300px;height:45px;" disabled></td> 
</td>
<td><label class="col-md-1 control-label">问题属地</label></td>
<td><select id="problemLand" name="problemLand" class="easyui-combobox" style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${problemLand}">
									<c:if test="${act.value ==problemClues.problemLand}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.problemLand }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select></td>
</tr>
<tr>
<td><label class="col-md-2 control-label">所属领域</label></td>
<td><select id="fields" name="fields"  style="width:300px;height:45px;"  class="easyui-combobox"></select></td>
<c:if test="${organId!=26}">
<td><label class="col-md-2 control-label">专项行动</label></td>
<td><select id="special" name="special" class="easyui-combobox"  style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${special}">
										<option <c:if test="${act.value ==problemClues.special}">selected="selected" </c:if> value="${act.value }">${act.name}</option>
								</c:forEach>
							</select></td>
							</c:if>
<td><label class="col-md-1 control-label">是否单位或事件事故</label></td>
<td><select id="belongToId" name="belongToId" class="easyui-combobox"  style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.belongToId}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==problemClues.belongToId}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.belongToId && '1' !=problemClues.belongToId}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select></td>
<td><label class="col-md-2 control-label">是否重要问题</label></td>
<td><select id="isImport" name="isImport" class="easyui-combobox"  style="width:300px;height:45px;">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.isImport}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==problemClues.isImport}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.isImport && '1' !=problemClues.isImport}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select></td>
</tr>
<tr>
<td><label class="col-md-2 control-label">是否要结果</label></td>
<td><select id="isResult" name="isResult" class="easyui-combobox"  style="width:300px;height:45px;"
								onChange="return isResult2()">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.isResult}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==problemClues.isResult}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.isResult && '1' !=problemClues.isResult}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select></td>
<td><label class="col-md-1 control-label" id="res1" >办理期限</label></td>
<td><input id="resultTime" type="text" name='resultTime' class="easyui-datebox" value="${problemClues.resultTime}" style="width:300px;height:45px;" disabled></input>  
</td>
<td><label class="col-md-2 control-label" id="res3" >类型</label></td>
<td><select id="type" name="type" class="easyui-combobox"  style="width:300px;height:45px;" disabled>
								<option value="0">请选择</option>
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
								<c:if
									test="${'2' !=problemClues.type && '1' !=problemClues.type && '3' !=problemClues.type && '4' !=problemClues.type}">
									<option value="1">了解关注类</option>
									<option value="2">进一步了解情况</option>
									<option value="3">参考</option>
									<option value="4">其他</option>
								</c:if>
							</select></td>
							
							
							
							
<td><label class="col-md-2 control-label" id="res5" >监督检查活动时间</label></td>
<td><input id="superviseTestTime" type="text" name='superviseTestTime' class="easyui-datebox" value="${problemClues.superviseTestTime}" style="width:300px;height:45px;" ></input></td> 
							
</tr>
 <tr>
<td><label>问题描述</label></td>
<td colspan="7" class="box2">
<textarea id="problemDescribe" name="problemDescribe" cols="120"  rows="3" >${problemClues.problemDescribe}</textarea></td> 
</tr>
 <tr>
<td><label >备注</label></td>
<td colspan="7" class="box2">
<textarea id="remarks" name="remarks" class="form-control" rows="3" cols="120">${problemClues.remarks}</textarea>
</td>
</tr>    
<tr><th>反映人信息</th></tr>
<tr><td><label class="col-md-1 control-label">反映人姓名</label></td>
<td><input 
								name="reflectingPerson.reflectingName" placeholder="请输入名称"
								value="${problemClues.reflectingPerson.reflectingName}" class="easyui-textbox"  style="width:300px;height:45px;" ></td>
<td><label class="col-md-2 control-label">反映人单位</label></td>
<td><input type="text" class="form-control"
								name="reflectingPerson.reflectingDept" placeholder="请输入单位"
								value="${problemClues.reflectingPerson.reflectingDept}" class="easyui-textbox"  style="width:300px;height:45px;"></td>
<td><label class="col-md-2 control-label">联系方式</label></td>
<td><input type="text" class="form-control"
								name="reflectingPerson.tell" placeholder="请输入联系方式"
								value="${problemClues.reflectingPerson.tell}" class="easyui-textbox"  style="width:300px;height:45px;"></td>								
<td></td>
<td></td>
</tr>
</table>
</form>
<script>
function save_clues(state){
	 $('#cluesForm').form("submit",{
	        onSubmit: function(){
	            var isValid = $(this).form('validate');
	            //如果验证失败，返回false，form不提交
	            if(isValid){
	            	 var data= $('#cluesForm').serialize();
	            	 var url="";
	            	 if($("#flag").val()!=null&&$("#flag").val()!=""&&$("#probleCluesId").val()!=null){
	                	 url='${path}/ta/tonAdd'; 
	                 }else if($("#probleCluesId").val()!=""){
	                	 url='${path}/ags/edit';
	                 }else{
	                	 //添加线索时需要检查重复件问题
	                	 url='${path}/ags/clues_add';
	                 }
	            	 $.ajax({
	            	     url:url,
	            	     data:data,
	            	     cache:false,//false是不缓存，true为缓存
	            	     async:true,//true为异步，false为同步
	            	     type:'post',
	            	     dataType:'json',
	            	     beforeSend:function(){
	            	         //请求前
	            	     },
	            	     success:function(result){
	            	    	 console.log(result.success);
	            	         //请求成功时
	            	         if(result.success){
	            	        	    //清空表单
	            	        	    if(result.msg=="repeat"){
	            	        	    		//打开重复件列表
	                	        	    	parent.addTab("重复线索处理","${path}/ags/repeater_clues?reflectedName="+result.obj);
	            	        	    }else{
	            	        	    if(result.obj!=null&&result.obj!=""){
	            	        	    	$("#probleCluesId").val(result.obj);
	            	        	    }
	            	        	    $.messager.alert('我的消息',result.msg,'info');
	            	        	    }
	            				    
	            				    
	            	         }else{
	            	        	 $.messager.alert('我的消息',result.msg,'warning');
	            	         }
	            	         
	            	     },
	            	     complete:function(){
	            	         //请求结束时
	            	     },
	            	     error:function(){
	            	         //请求失败时
	            	     }
	            	 })
	            }
	        }
	 });
	
	 
	 
}
$(function(){
	
	 $("#xl").combobox('setValue',$("#xlData").val());  
	 $("#mz").combobox('setValue',$("#mzData").val()); 
	 //领域
	 $('#fields').combotree({
	    	width:'100%',
	        url: '${path }/dict/dictCombotree',
	        multiple: true,
	        panelHeight : 'auto',
	        queryParams:{dictPid:'0114'},
	        onBeforeExpand:function(node){
	        	 var childrenArr = $('#fields').combotree('tree');  
	        	 console.log(node.dictId);
	        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
		    },
		    onLoadSuccess:function(node,data){
		    	console.log(data);
		    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
		    	var idstr='${problemClues.fields}';
		    	console.log(idstr);
		    	var ids=idstr.split(",");
		    	console.log(ids);
		    	for(var d=0;d<data.length;d++){
		    	      //遍历后台传来的值
		    	 if (ids.length > 0) {
                 for ( var i = 0; i < ids.length; i++) {
                 	if(ids[i].indexOf(data[d].value)!=-1){
                 		//展开该节点
                 		//杀死它
                 		if(ids[i]==data[d].value){
                 			var node = $('#fields').combotree('tree').tree('find', data[d].id);
                 			$('#fields').combotree('tree').tree('check', node.target);
                 		}
                 		
                 	}
                 }
                }
		    	} 
         },loadFilter: function(data){  
		    	for(var x in data){
		    		data[x].id=data[x].value;
		    	}
		        if (data.d){   
		            return data.d;    
		        } else {    
		            return data;    
		        }    
		    }
	    });	
	 console.log("加载下拉选");
 	var str='${problemClues.reflectedPerson.fyzvviolation}';
 	var zyViolation1=str.split(",");
     console.log("加载下拉反映问题违纪性质下拉选");
	    $('#fyzvviolation').combotree({
	        url: '${path }/dict/dictCombotree',
	        multiple: true,
	        queryParams:{dictPid:'0105'},
	        onDblClick:function(node){
	        	if (node.state == 'open') {
	        		var node = $('#fyzvviolation').combotree('tree').tree('find', node.id);
		        	$('#fyzvviolation').combotree('tree').tree('collapse', node.target);
	        	}else{
	        		var node = $('#fyzvviolation').combotree('tree').tree('find', node.id);
		        	$('#fyzvviolation').combotree('tree').tree('expand', node.target);
	        	}
	        	
	        },
	        onBeforeExpand:function(node){
	        	 var childrenArr = $('#fyzvviolation').combotree('tree');  
	        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
		    },
		    onLoadSuccess:function(node,data){
		    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
		    	var ids=zyViolation1;
		    	if(ids){
		    	for(var d=0;d<data.length;d++){
		    	      //遍历后台传来的值
		    	    if (ids.length > 0) {
               for ( var i = 0; i < ids.length; i++) {
               	if(ids[i].indexOf(data[d].dictId)!=-1){
               		//展开该节点
               		var node = $('#fyzvviolation').combotree('tree').tree('find', data[d].id);
               		$('#fyzvviolation').combotree('tree').tree('expand', node.target);
               		//杀死它
               		if(ids[i]==data[d].dictId){
               			$('#fyzvviolation').combotree('tree').tree('check', node.target);
               		}
               		
               	}
               }
           }
		    	} 
		    	}
         },loadFilter: function(data){  
		    	for(var x in data){
		    		data[x].id=data[x].dictId;
		    	}
		        if (data.d){   
		            return data.d;    
		        } else {    
		            return data;    
		        }    
		    }
	    });
	    
	      var str='${problemClues.reflectedPerson.fylegalAct}';
 	  var fylegalAct=str.split(",");
		  //违纪情况
		  $('#fylegalAct').combotree({
		        url: '${path }/dict/dictCombotree',
		        multiple: true,
		        panelHeight : 'auto',
		        queryParams:{dictPid:'03'},
		        onDblClick:function(node){
		        	if (node.state == 'open') {
		        		var node = $('#legalAct').combotree('tree').tree('find', node.id);
			        	$('#fylegalAct').combotree('tree').tree('collapse', node.target);
		        	}else{
		        		var node = $('#legalAct').combotree('tree').tree('find', node.id);
			        	$('#fylegalAct').combotree('tree').tree('expand', node.target);
		        	}
		        	
		        },
		        onBeforeExpand:function(node){
		        	 var childrenArr = $('#fylegalAct').combotree('tree');  
		        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
			    },
			    onLoadSuccess:function(node,data){
			    	//因为每个节点是一个树//第一遍展开 //第二遍赋值
			    	var ids=fylegalAct;
			    	if(ids){
			    	for(var d=0;d<data.length;d++){
			    	      //遍历后台传来的值
			    	    if (ids.length > 0) {
                     for ( var i = 0; i < ids.length; i++) {
                     	if(ids[i].indexOf(data[d].dictId)!=-1){
                     		//展开该节点
                     		var node = $('#fylegalAct').combotree('tree').tree('find', data[d].id);
                     		$('#fylegalAct').combotree('tree').tree('expand', node.target);
                     		//杀死它
                     		if(ids[i]==data[d].dictId){
                     			$('#fylegalAct').combotree('tree').tree('check', node.target);
                     		}
                     		
                     	}
                     }
                 }
			    	} 
	            }
				} ,loadFilter: function(data){  
			    	for(var x in data){
			    		data[x].id=data[x].dictId;
			    	}
			        if (data.d){   
			            return data.d;    
			        } else {    
			            return data;    
			        }    
			    }
		    });
});
function isP(value){
	   console.log(value);
	   if(value=="1"){
		   $("#pMSupervisoryObject").removeAttr("disabled"); 
	   }else{
		   $('#pMSupervisoryObject').attr("disabled","disabled"); 
	   }
}
function isup(value){
	   if(value=="1"){
		   $("#supervision").removeAttr("disabled"); 
	   }else{
		   $('#supervision').attr("disabled","disabled"); 
	   }
}
function addTonanRen(){
	var id =$("#probleCluesId").val();
	if(id!=null&&id!=""){
		parent.addTab("添加同案人员","${path}/ta/tongan?id="+id);	
	}else{
		$.messager.alert('我的消息','请先保存线索！','warning');
	}
	
}
//将案件转发给案管室
function transfer(){
	   var cid=$("#probleCluesId").val();
	   $.ajax({
		   url:"${path}/jds/jdsToAgs",
		   data:{id:cid},
		   dataType:'json',
		   type:'post',
		   success:function(result){
			   if(result.success){
				   $.messager.alert('我的消息',result.msg,'info');
			   }else{
				   $.messager.alert('我的消息',result.msg,'warning');
			   }
		   }
	   })
}
function addRepeat(){
	var name='${problemClues.reflectedPerson.reflectedName}';
	var id='${problemClues.id}';
	if(name!=null&&name!=""){
		parent.addTab("添加重复件["+name+"]","${path}/repeat/repeatListAll?reflectedName="+name+"&id="+id);
	}else{
		$.messager.alert('我的消息','请添加被反映人姓名！','warning');
	}
	
}
//绑定同案人员
function bangTonanRen(){
	var id =$("#probleCluesId").val();
	if(id!=null&&id!=""){
		parent.addTab("绑定同案人员","${path}/ta/a?id="+id);	
	}else{
		 $.messager.alert('我的消息','请先保存线索！','warning');
	}
	
}
</script>
    <script type="text/javascript" src="${staticPath }/static/nation.js"
		charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/xueli.js"
		charset="utf-8"></script>