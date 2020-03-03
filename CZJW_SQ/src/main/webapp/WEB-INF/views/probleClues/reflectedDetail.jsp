                        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
                       <table class="table  table-bordered table-hover">
                                          
                       <tr>
						<th>姓名</th>
						 <td><input type="text" class="form-control" id="reflectedName"
								name="reflectedPerson.reflectedName" placeholder="请输入名称"
								value="${problemClues.reflectedPerson.reflectedName}"  onblur="check('reflectedName','noEmpty')"/><span id="reflectedNamespan" style="color:red"></span>
						</td>
						<th>性别</th>
						<td><select id="sex" name="reflectedPerson.sex" class="form-control">
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
                       
						<th>民族</th>
						<td><input id="mzData" type="hidden"
								value="${problemClues.reflectedPerson.nation }" /> <select
								id="mz" class="form-control" name="reflectedPerson.nation">


							</select>
						</td>
					</tr>
					<tr>
						 <th>证件类型</th>
						<td><select id="documentType" class="form-control" name="reflectedPerson.documentType">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${documentType}">
									<c:if test="${act.value ==problemClues.reflectedPerson.documentType}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.documentType }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						</tr> 
						
						<tr>
					    <th>证件号码</th>
						<td><input type="text" class="form-control" id="identificationNumber"
								name="reflectedPerson.identificationNumber" placeholder="请输入证件号码"
								value="${problemClues.reflectedPerson.identificationNumber}"/>
						</td>
						<th>出生年月</th>
						<td><a class='input-group date' id='c2'> <input
								name="reflectedPerson.birthday" type='text' class="form-control"
								id='birthday' value="${problemClues.reflectedPerson.birthday}" />
								<span class="input-group-addon"
								style="width: 50px; height: 34px;"> <span
									class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</td>
					</tr>
					<tr>

						<th>学历</th>
						<td>
							<input id="xlData" type="hidden"
								value="${problemClues.reflectedPerson.xl }" /> <select id="xl"
								class="form-control" name="reflectedPerson.xl">


							</select>
						</td>
						<th>工作单位</th>
						<td><input type="text" class="form-control"
								name="reflectedPerson.workPosition" placeholder="请输入名称"
								value="${problemClues.reflectedPerson.workPosition}">
						</td>
						<th>职务</th>
						<td><input type="text" class="form-control" placeholder="请输入名称"
								name="reflectedPerson.duty"
								value="${problemClues.reflectedPerson.duty}">
						</td>
					</tr>

					<tr>
						

						<th>职级</th>
						<td>
							<select id="rank" class="form-control" name="reflectedPerson.rank" onChange="check('rank','choice');">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${rank}">
									<c:if test="${act.name ==problemClues.reflectedPerson.rank}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.rank }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select><span id="rankspan" style="color:red"></span>
						</td>
						<th>任现职时间</th>
						<td><a class='input-group date' id='c3'> <input type='text'
								id="worktime" class="form-control"
								name='reflectedPerson.worktime'
								value="${problemClues.reflectedPerson.worktime}" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</td>
						<th>是否中共党员</th>
						<td><select id="partyMember" name="reflectedPerson.partyMember" class="form-control">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.reflectedPerson.partyMember}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==problemClues.reflectedPerson.partyMember}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=problemClues.reflectedPerson.partyMember && '1' !=problemClues.reflectedPerson.partyMember}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select>
						</td>
					
					</tr>
					<tr>

						<th>政治面貌</th>
						<td><select class="form-control" name="reflectedPerson.political">
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
						<th>入党时间</th>
						<td><a class='input-group date' id='c4'> <input id="intime"
								type='text' class="form-control" name='reflectedPerson.intime'
								value="${problemClues.reflectedPerson.intime }" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</td>
						<th>是否非党员非监察对象</th>
						<td style="width: 250px; height: 34px;" >
						<div style="display:inline-block;float:left" ><select id="isPMSupervisoryObject" name="reflectedPerson.isPMSupervisoryObject" style="width: 90px; height: 34px;" class="form-control" onChange="return isP()">
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
							</select>
							</div>
							<c:forEach var="act" items="${pMSupervisoryObject}">
									<c:if test="${act.value ==problemClues.reflectedPerson.pMSupervisoryObject}">
										<div style="display:inline-block;float:right" title="${act.name}">
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.pMSupervisoryObject }">
										<div style="display:inline-block;float:right">
									</c:if>
								</c:forEach>
							<select class="form-control" id="pMSupervisoryObject"  style="width: 120px; height: 34px;" name="reflectedPerson.pMSupervisoryObject" disabled="disabled">
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
							</select>
							
						</td>
					</tr>
					<tr>						
						<th>身份</th>
						<td><select class="form-control" name="reflectedPerson.civilServant">
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
							</select>
						</td>
						<th>部门分类</th>
						<td><select class="form-control" name="reflectedPerson.departmenType">
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
							</select>
						</td>
						<th>企业性质</th>
						<td><select class="form-control"
								name="reflectedPerson.natureOfenterprise">
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
							</select>
						</td>
					</tr>
					<tr>
						<th>企业级别</th>
						<td><select class="form-control"
								name="reflectedPerson.classOfenterprise">
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
							</select>
						</td>
						<th>岗位</th>
						<td><select class="form-control" name="reflectedPerson.post">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${post}">
									<c:if test="${act.value ==problemClues.reflectedPerson.post}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.post }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>企业人员类别</th>
						<td><select class="form-control" name="reflectedPerson.posType">
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
							</select>
						</td>
					</tr>
					<tr>
						<th>一把手违纪违法</th>
						<td>
							<select class="form-control" name="reflectedPerson.topDiscipline">
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
							</select>
						</td>
						<th>干部管理权限</th>
						<td>
							<select class="form-control" name="reflectedPerson.cadre">
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
							</select>
						</td>
						<th>中共代表</th>
						<td>
							<select class="form-control" name="reflectedPerson.partyRepresent">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${partyRepresent}">
									<c:if test="${act.value ==problemClues.reflectedPerson.partyRepresent}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.partyRepresent }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						
						<th>人大代表</th>
						<td>
							<select class="form-control" name="reflectedPerson.np">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${np}">
									<c:if test="${act.value ==problemClues.reflectedPerson.np}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.np }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>党委委员</th>
						<td>
							<select class="form-control" name="reflectedPerson.dwMember">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${dwMember}">
									<c:if test="${act.value ==problemClues.reflectedPerson.dwMember}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.dwMember }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>纪委委员</th>
						<td>
							<select class="form-control" name="reflectedPerson.jwMember">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${jwMember}">
									<c:if test="${act.value ==problemClues.reflectedPerson.jwMember}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.jwMember }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<th>政协委员</th>
						<td>
							<select class="form-control" name="reflectedPerson.zx">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${zx}">
									<c:if test="${act.value ==problemClues.reflectedPerson.zx}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.zx }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>是否违反中央八项规定精神</th>
						<td>
							<select id="eightSpirit" name="reflectedPerson.eightSpirit" class="form-control">
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
							</select>
							</td>
						<th>是否国家监察对象</th>
						<td style="width: 250px; height: 34px;" >
						<div style="display:inline-block;float:left" ><select id="iSupervision" name="reflectedPerson.iSupervision" style="width: 90px; height: 34px;" class="form-control" onChange="return isup()">
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
							</select>
							</div>
							<c:forEach var="act" items="${supervision}">
									<c:if test="${act.value ==problemClues.reflectedPerson.supervision}">
										<div style="display:inline-block;float:right" title="${act.name}">
									</c:if>
									<c:if test="${act.value != problemClues.reflectedPerson.supervision }">
										<div style="display:inline-block;float:right">
									</c:if>
								</c:forEach>
							<select class="form-control" id="supervision"  style="width: 120px; height: 34px;" name="reflectedPerson.supervision" disabled="disabled">
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
							           </select>
						            </td>         
                                      </tr> 
                                   </tbody>
                        </table> 