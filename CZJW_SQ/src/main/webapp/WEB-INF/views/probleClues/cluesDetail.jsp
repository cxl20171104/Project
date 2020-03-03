<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/commons/bootstrap.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>案管室分办</title>

</head>
<body >
       <div class="container">   
                    <form action="" id="fbForm"  class="form-horizontal">
                           <div class="row">
                    				  <input  type="hidden" name="cluesId" id="cluesId" value="${problemClues.id}" />
                                      <input type="hidden" name="reflectedPerson.id" id="" value="${problemClues.reflectedPerson.id}" />
                                     
                                      <input type="hidden" id="pageName"  value="${pageName }">
                                       <h4 class="page-header">被反映人信息 <span>&nbsp;&nbsp;&nbsp;&nbsp;(${problemClues.cluesNum})</span></h4>
                                       <table class="table  table-bordered table-hover">
                                           <tbody>
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
						<th>证件号码</th>
						<td><input type="text" class="form-control" id="identificationNumber"
								name="reflectedPerson.identificationNumber" placeholder="请输入证件号码"
								value="${problemClues.reflectedPerson.identificationNumber}"/>
						</td>
						<th>出生年月</th>
						<td><a class='input-group date' id='c2'"> <input
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
						<td><a class='input-group date' id='c3'"> <input type='text'
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
						<td><a class='input-group date' id='c4'"> <input id="intime"
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
							</div>
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
						</div>
						</td>         
                                      </tr> 
                                            </tbody>
                                            </table>
                                           <div  class="row">
                                           
                                            <ul class="nav nav-tabs" id="myTab">
                                            
								              <li><a href="#fyr" data-toggle="tab" >反映人情况</a></li>
								              <li><a href="#profile" data-toggle="tab">线索情况</a></li>
								              <shiro:hasPermission name="/probleClues/agsfb"> 
								               <li><a href="#agsjb" data-toggle="tab" onclick="thisPage('agsjb');">案管室交办</a></li>
								               <li><a href="#ags" data-toggle="tab" onclick="thisPage('ags');">案管室分办</a></li>
								              
								               </shiro:hasPermission> 
								               <shiro:hasPermission name="/probleClues/jdscz">
								               <li><a href="#jds" data-toggle="tab" onclick="thisPage('jds');">监督室直查/直办</a></li>
								               </shiro:hasPermission>
								                <li><a href="#jdss" data-toggle="tab" onclick="thisPage('jb');">监督室交办</a></li>
								               <shiro:hasPermission name="/probleClues/scscz">
								               <li><a href="#scs" data-toggle="tab" onclick="thisPage('scs');">审查室处置情况</a></li>
								               </shiro:hasPermission>
								               <shiro:hasPermission name="/probleClues/scsla">
								               <li><a href="#scs2" data-toggle="tab" onclick="thisPage('scs2');">审查室立案情况</a></li>
								               </shiro:hasPermission>
								               <shiro:hasPermission name="/probleClues/slssl">
								               <li><a href="#sls" data-toggle="tab"  onclick="thisPage('sls');">审理室审理情况</a></li>
								               </shiro:hasPermission>
							                  </ul>
							                  <div class="tab-content">
							                  
							                  <!-- 
							                  
							                                                   案管室分办情况
							                  
							                   -->
							                  
							                  
								              <div class="tab-pane active" id="ags" >
								                  <table class="table  table-bordered table-hover">
								                      <tr>
								                      <th>受理时间</th>
								    		           <td>
								    		                           <a class='input-group date' id='c5' >
								    					<input id="agslTime" type='text' class="form-control" name="progress[2].timeForday" style="width: 170px; height: 30px;" value="" disabled />
							                            <span class="input-group-addon" style="width: 50px; height: 30px;">
							                            	<span class="glyphicon glyphicon-calendar"></span>
							                            </span>
							                         </a>
							                         </td>
								    		     
							                         
								    		               <th>分办时间</th>
								    		               <td>
								    		                <a class='input-group date' id='c6' >
								    				 	    <input id="fbTime" type='text' class="form-control" name="progress[4].timeForday" style="width: 170px; height: 30px;" value=""  disabled/>
							                                <span class="input-group-addon" style="width: 50px; height: 30px;">
							                            	 <span class="glyphicon glyphicon-calendar"></span>
							                                </span>
							                                </a>  
							                                </td>
							                         
													        <th>执纪审查专题会议时间</th>
								    		                <td>
								    		                <a class='input-group date' id='c5' >
								    					    <input id="zjscTime" type='text' class="form-control" name="progress[2].timeForday" style="width: 170px; height: 30px;"  disabled />
							                                <span class="input-group-addon" style="width: 50px; height: 30px;">
							                            	<span class="glyphicon glyphicon-calendar"></span>
							                                </span>
							                                </a>
							                                 </td>
													
													    </tr>					
																		
													     <tr> 
							                             <th>承办单位</th>          
							                             <td>
								                         <select  class="form-control" id="organId1" name="progress[4].detail" style="width: 220px; height: 30px;" data-options="editable:false" disabled> 
												              <option value="0">暂无</option>
                                                                      <c:forEach var="act" items="${oId}">
                                                                                      <option value="${act.id }">${act.name}</option>
                                                                       </c:forEach>
													
													     </select>               
													     </td>
													     <th></th>
														 <td>
														 </td>
														 <th></th>
														 <td>
														 </td>
														 </tr>
													
													<!-- 
													   
													   案管室再分办
													
													 -->
												  <tr>
												  <th>案管室再分办</th>
												  </tr>
												  	 
												  <tr>
								                      <th>受理时间</th>
								    		          <td>
								    		           <a class='input-group date' id='c5' >
								    					<input id="agslTime_again" type='text' class="form-control" name="progress[2].timeForday" style="width: 170px; height: 30px;" value="" disabled />
							                            <span class="input-group-addon" style="width: 50px; height: 30px;">
							                            	<span class="glyphicon glyphicon-calendar"></span>
							                            </span>
							                            </a>
							                         </td>
								    		     
							                         
								    		         <th>分办时间</th>
								    		         <td>
								    		           <a class='input-group date' id='c6' >
								    				   <input id="fbTime_again" type='text' class="form-control" name="progress[4].timeForday" style="width: 170px; height: 30px;" value=""  disabled/>
							                            <span class="input-group-addon" style="width: 50px; height: 30px;">
							                            <span class="glyphicon glyphicon-calendar"></span>
							                            </span>
							                         </a>  
							                         </td>
							                         
													   <th>执纪审查专题会议时间</th>
								    		           <td>
								    		            <a class='input-group date' id='c5' >
								    					<input id="zjscTime_again" type='text' class="form-control" name="progress[2].timeForday" style="width: 170px; height: 30px;"  disabled />
							                            <span class="input-group-addon" style="width: 50px; height: 30px;">
							                            	<span class="glyphicon glyphicon-calendar"></span>
							                            </span>
							                          </a>
							                          </td>
													
													</tr>	
													
													
													<tr> 
							                        <th>承办单位</th>          
							                        <td>
								                    <select  class="form-control" id="organId1_again" name="progress[4].detail" style="width: 220px; height: 30px;" data-options="editable:false" disabled> 
												    <option value="0">暂无</option>
                                                    <c:forEach var="act" items="${oId}">
                                                    <option value="${act.id }">${act.name}</option>
                                                    </c:forEach>
													</select>               
													</td>
													<th></th>
													<td>
													</td>
													<th></th>
													<td>
													</td>
													</tr>
													</table>
																	     
									              	
							                </div>
							                <!-- 
							                
							                                               案管室交办情况
							                
							                 -->
							                 <div class="tab-pane active" id="agsjb" >
							                 <table class="table  table-bordered table-hover">
							                 <tr>
							                 <th>承办县区</th>          
							                 <td>
								             <select  class="form-control" id="organId2" name="progress[4].detail"  style="width: 220px; height: 30px;" data-options="editable:false" disabled> 
										     <option value="0">暂无</option>
                                             <c:forEach var="act" items="${jc}">
                                             <option value="${act.id }">${act.name}</option>
                                             </c:forEach>
										     </select>               
										     </td>
										     <th>承办时间</th>
								    		 <td>
								    		 <a class='input-group date' id='c6' >
								    		 <input id="xqcbTime" type='text' class="form-control" name="progress[4].timeForday" style="width: 170px; height: 30px;" value=""  disabled/>
							                 <span class="input-group-addon" style="width: 50px; height: 30px;">
							                 <span class="glyphicon glyphicon-calendar"></span>
							                 </span>
							                 </a>  
							                 </td>
							                 <th ></th>
							                 <td ></td>
							                 </tr>
							                 </table>
							                 </div>
											 <!-- 
											 
											  反应人信息
											 
											  -->										
								    		 <div class="tab-pane active" id="fyr" >
								             <%@ include file="reflectingDetailn.jsp" %> 
								 		     </div>	 
								    		 <!-- 
								    		 
								    		 线索情况
								    		 
								    		  -->
								             <div class="tab-pane active" id="profile" >
								               <%@ include file="cauesDetailn.jsp" %> 
								 		     </div>
								 		     
								 		     <!-- 
								 		     
								 		             监督室直办情况
								 		     
								 		      -->
								 		       <div class="tab-pane active" id="jds"  >
								 		       
								               </div>
								             <!-- 
								             
								                                  监督室交办情况
								             
								              -->  
								 		      <div class="tab-pane active" id="jdss"  >
								 		      <table class="table  table-bordered table-hover">
								 		      <tr>
								 		      <th>承办人</th>   
											  <td>
											  <input id="cbr2"  type="text" class="cxls form-control" style="width: 220px; height: 30px;"  value="" disabled/>
											  </td>
											  <th>下发到基层单位</th>
                                              <td>
                                              <select id="jcdw" name="problemClues.organId" class="form-control" style="width: 220px; height: 30px;"  disabled>
                                              <option value="0">暂无</option>
                                              <c:forEach var="act" items="${jc}">
                                              <option value="${act.id }">${act.name}</option>
                                              </c:forEach>
                                              </select>
                                              </td>
                                                                                           
                                              <th>交办日期</th>
                                              <td>
                                              <a class='input-group date' id='c32' > <input type='text'
                                              id="giveTime_jds" class="form-control" name='problemClues.giveTime_jds' style="width: 170px; height: 30px;" value="${problemClues.giveTime_jds }"  disabled/>
                                              <span class="input-group-addon" style="width: 50px; height: 34px;">
                                              <span class="glyphicon glyphicon-calendar" ></span>
                                              </span>
                                              </a>
                                              </td>
								 		      </tr>
								 		          
								 		          
								 		      <tr>
								 		      <th>领办人</th>
                                              <td>
                                              <input type='text' id="givePersion_jds" class="form-control" name='problemClues.givePersion_jds' style="width: 220px; height: 30px;"  value="${problemClues.givePersion_jds}" disabled/>
                                              </td>           
                                              <th>是否要结果</th>
                                              <td>
                                              <select id="isResult_jds" name="problemClues.isResult_jds" class="form-control" style="width: 220px; height: 30px;" onChange="openTime(this.options[this.options.selectedIndex].value);" disabled>
                                              <c:if test="${ problemClues.isResult_jds==1}">
                                              <option value="1">是</option>
                                              </c:if>
                                              <c:if test="${ problemClues.isResult_jds==2}">
                                              <option value="2">否</option>
                                              </c:if>
                                              <c:if test="${ problemClues.isResult_jds==0}">
                                              <option value="0">暂无</option>
                                              </c:if>
                                              </select>
                                              </td>
                                              <th>办理期限</th>
                                              <td>
                                              <a class='input-group date' id='c32' > <input type='text'id="resultTime_jds" class="form-control" style="width: 170px; height: 30px;" name='problemClues.resultTime_jds'
                                              value="${problemClues.resultTime_jds}" disabled/> <span class="input-group-addon" style="width: 50px; height: 34px;">
                                              <span class="glyphicon glyphicon-calendar" ></span>
                                              </span>
                                              </a>
						                      </td>
								 		      </tr>
								 		      </table>
								 		      </div>
								 		      <!-- 
								 		      
								 		                审查室案件处理情况
								 		      
								 		       -->
								 		      <div class="tab-pane active" id="scs" >
								              <table class="table  table-bordered table-hover">  
								              <tr>
								              <th>受理时间</th>
										      <td>
											  <a class='input-group date' > <input type='text' disabled id="scstime" disabled="disabled" class="form-control"  style="width: 170px; height: 30px;"	value="" /> 
											  <span class="input-group-addon" style="width: 50px; height: 30px;">
											  <span class="glyphicon glyphicon-calendar"></span>
											  </span>
											  </a>
											  </td>               
								              <th>初核时间</th>
											  <td>
											  <a class='input-group date' > <input type='text' disabled id="chtime" disabled="disabled" class="form-control"  style="width: 170px; height: 30px;"	value="" /> 
											  <span class="input-group-addon" style="width: 50px; height: 30px;">
											  <span class="glyphicon glyphicon-calendar"></span>
											  </span>
											  </a>
											  </td>  
											  <th>处置建议</th>
											  <td>											
								    		  <select  class="form-control"  data-s="select-one" id="scJY1" style="width: 220px; height: 34px;" name="progress[0].detail" disabled >
			                                  <option value="0">暂无</option>
									          <c:forEach var="act" items="${scsMethod}">
											  <option value="${act.value }">${act.name}</option>
					                          </c:forEach>
			                                  </select>			                                            
										      </td>
										      <th></th>
										      <td></td>
											  </tr>
											  <tr>   		
										      <th>领导审批意见</th>
									    	  <td>	
									    	  <select id="spyjWhere" class="form-control"  data-s="select-one" style="width: 220px; height: 34px;"  name="progress[1].detail" disabled >
			                                  <option value="0">暂无</option>
									          <c:forEach var="act" items="${scsMethod}">
											  <option value="${act.value }">${act.name}</option>
					                          </c:forEach>
			                                  </select>
			                                  </td>
			                                  <th>审批时间</th>
											  <td>	
											  <a class='input-group date' > <input type='text' id="sptime"  disabled class="form-control" name='progress[1].timeForday' style="width: 170px; height: 30px;"	value="" /> 
											  <span class="input-group-addon" style="width: 50px; height: 30px;">
											  <span class="glyphicon glyphicon-calendar"></span>
											  </span>
											  </a>
											  </td> 
											   <th>市委主要领导签批时间：</th>
											  <td>
											  <a class='input-group date'> <input id="qpTime" type='text' class="form-control" name='progress[2].timeForday' style="width: 170px; height: 34px;" /> 
											  <span class="input-group-addon" style="width: 50px; height: 34px;">
											  <span class="glyphicon glyphicon-calendar"></span>
											  </span>
											  </a>
											  </td>
								              </tr> 
								              <tr> 
											   <th>执纪审查会时间：</th>
										      <td>
											  <a class='input-group date'> <input type='text' id="zjscTime2" class="form-control" name='progress[3].timeForday' style="width: 170px; height: 34px;"	/> 
											  <span class="input-group-addon" style="width: 50px; height: 34px;">
											  <span class="glyphicon glyphicon-calendar"></span>
											  </span>
											  </a>
											  </td>
											   <th>会议决定：</th>
											  <td>													
								    		  <select  id="spyjWhere2" class=" form-control" style="width: 220px; height: 34px;"   name="progress[3].detail"  onChange="return spyj()">
			                                  <option value="0" selected="selected" >暂无</option>
									          <c:forEach var="act" items="${scsMethod}">
									          <option value="${act.value }">${act.name}</option>									                      
					                          </c:forEach>
			                                  </select>			                                            
											  </td>	
											    <th>党组织：</th>
											  <td>
											  <input type="text" class="form-control"	style="width: 220px; height: 34px;"	 id="ysPartyOrgan" name="reflectedPerson.ysPartyOrgan" placeholder="请输入名称" value="${problemClues.reflectedPerson.ysPartyOrgan}" />                                            
											  </td>   			                                 
			                                  </tr>          
			                                  <tr> 
			                                  <th>办案小组组长：</th>
									    	  <td>
									    	  <input id="zu_zhang" class="form-control"  style="width: 220px; height: 34px;" name="group[0].userId"/>
			                                  </td>
			                                  <th>办案小组成员：</th>
										      <td>
											  <input id="zu_yuan"   class=" form-control" style="width: 220px; height: 34px;" />
											  </td>
			                                 
			                                  </tr>   
								              <tr> 
								              <th>初核报告</th>
											  <td colspan="5">
											  <label id="chbg_s_l" for="chbg_s">文件下载</label>
											  <input id="chbg_s" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">		
								              </td>         
								              </tr>
								              <tr> 
								              <th>初核方案</th>
											    	<td colspan="5">
											           <label id="chfa_s_l" for="chfa_s">文件下载</label>
											           <input id="chfa_s" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">		
								                    </td>         
								              </tr>
								              <tr> 
								              <th>上传初核请示</th>
											    	<td colspan="5">
											           <label id="chqs_s_l" for="chqs_s">文件下载</label>
											           <input id="chqs_s" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">		
								                    </td>         
								             </tr>
								             <tr> 
								             <th>审查报告</th>
											    	<td colspan="5">
											           <label id="scbg_s_l" for="scbg_s">文件下载</label>
											           <input id="scbg_s" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">		
								                    </td>         
								             </tr>  
								             <tr> 
								             <th>审查工作方案报告</th>
											    	<td colspan="5">
											           <label id="scgzfa_s_l" for="scgzfa_s">文件下载</label>
											           <input id="scgzfa_s" data-title="审查室初核报告" style="visibility:hidden"  value=""  onclick="download(this)">		
								                    </td>         
								             </tr> 
								             </table>
								             </div>
								             <!-- 
								             
								                                    审查室立案审查
								             
								              -->
								              <div class="tab-pane active" id="scs2">
								              <table class="table  table-bordered table-hover">
								              <tr>
								              <th>纪委立案时间</th>
											   <td>
												 	 <a class='input-group date'> <input id="jiwla"  disabled type='text' class="form-control" style="width: 170px; height: 30px;" value=""  /> 
														<span class="input-group-addon" style="width: 50px; height: 30px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
								              <th>监委立案时间</th>
											   <td>
												 	 <a class='input-group date'> <input id="jianwla"  disabled type='text' class="form-control" style="width: 170px; height: 30px;" value=""  /> 
														<span class="input-group-addon" style="width: 50px; height: 30px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
											   <th style="width: 250px; height: 34px;">是否其他纪检监察机关立案后移送：</th>
													<td style="width: 280px; height: 34px;" >
													<div style="display:inline-block;float:left" ><select id="isOtherTransfer" name="reflectedPerson.isOtherTransfer" style="width: 100px; height: 34px;" class="form-control" >
															<option value="0">请选择</option>
															<c:if test="${'1' ==problemClues.reflectedPerson.isOtherTransfer}">
																<option selected="selected" value="1">是</option>
																<option value="2">否</option>
															</c:if>
															<c:if test="${'2' ==problemClues.reflectedPerson.isOtherTransfer}">
																<option value="1">是</option>
																<option selected="selected" value="2">否</option>
															</c:if>
															<c:if
																test="${'2' !=problemClues.reflectedPerson.isOtherTransfer && '1' !=problemClues.reflectedPerson.isOtherTransfer}">
																<option value="1">是</option>
																<option value="2">否</option>
															</c:if>
														</select>
														</div>
														<c:forEach var="act" items="${otherTransfer}">
																<c:if test="${act.value ==problemClues.reflectedPerson.otherTransfer}">
																	<div style="display:inline-block;float:right" title="${act.name}">
																</c:if>
																<c:if test="${act.value != problemClues.reflectedPerson.otherTransfer }">
																	<div style="display:inline-block;float:right">
																</c:if>
															</c:forEach>
														<select class="form-control" id="otherTransfer"  style="width: 140px; height: 34px;" name="reflectedPerson.otherTransfer" disabled="disabled">
															<option value="0">请选择</option>
															<c:forEach var="act" items="${otherTransfer}">
																<c:if
																	test="${act.value ==problemClues.reflectedPerson.otherTransfer}">
																	<option selected="selected" value="${act.value }">${act.name}</option>
																</c:if>
																<c:if
																	test="${act.value != problemClues.reflectedPerson.otherTransfer }">
																	<option value="${act.value }">${act.name}</option>
																</c:if>
															</c:forEach>
														</select>
													</div>
													</td>  
		                                   </tr>
		                                    <tr>
												 <td style="text-align:center;" colspan="6">--处置建议--</td>
																 
											 </tr> 
		                                   <tr>
		                                  
		                                        <th>党纪处分</th>
		                                        <td>
		                                             <select id="djcf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${blResult_djcf}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                      </select>
		                                       </td>  
		                                     
		                                       <th>政务处分</th>
		                                       <td>
		                                             <select id="zjcf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${blResult_zjcf}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                     </select>
		                                       </td> 
		                                       <th>组织处理</th>
		                                       <td>
		                                             <select id="zzcl"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${blResult_zzcl}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                     </select>
		                                       </td> 
		                                      </tr>
		                                      <tr>
		                                       <th>移送司法</th>
		                                       <td>
		                                             <select id="yssf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${blResult_yjsf}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                     </select>
		                                       </td> 
		                                       <th>其他处理</th>
		                                       <td>
		                                             <select id="qtcl"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${blResult_qtcl}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                     </select>
		                                       </td>
		                                      </tr>
		                                     <tr>
													<td colspan="6"></td>
											 </tr>
		                                      <tr>
		                                     	    <th>审查调查小组组长：</th>
									    			<td>
									    			<input id="zu_zhang2" type="text" style="width:200px;" class="form-control"
													name="" placeholder="请输入调查组组长">
									    			</td>
			                                        <th>办案小组成员：</th>
													<td><input id="zu_yuan2" type="text" style="width:200px;" class="form-control"
													name="" placeholder="请输入调查组组员">
													</td>
													<th>违纪行为</th>	
								    				<td>
								    				<select style="width:200px;"  id="zyViolation" name=""
														class="selectpicker form-control" multiple data-s="multiple">
						
														<c:forEach var="act" items="${fyZyViolation}">
															<option value="${act.dictId }">${act.name}</option>
														</c:forEach>
													</select>
								    			</td>
		                                      </tr>
		                                       <tr>
												 <td style="text-align:center;" colspan="6">--涉法行为--</td>
																 
											 </tr> 
		                                      <tr>
		                                      			<th>职务违法犯罪行为</th>
								    					<td colspan="5">
								    						<select id="zwfz1" name="" 
																class="selectpicker form-control" multiple data-s="multiple" >
								
																<c:forEach var="act" items="${legalAct1}">
																	<option value="${act.dictId }">${act.name}</option>
																</c:forEach>
															</select>
								    					</td>
		                                      
		                                      </tr>
		                                      <tr>
		                                      		<th>贪污贿赂</th>
								    				<td>
								    					<select id="twhl2" name="" 
															class="selectpicker form-control" multiple data-s="multiple" >
															<c:forEach var="act" items="${lawTWHL}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>
								    				<th>受贿</th>
								    				<td>
								    					<select id="sh3" name=""
															class="selectpicker form-control" style="width:130px;" multiple data-s="multiple">
															<c:forEach var="act" items="${lawSH}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>
								    				<th>行贿</th>
								    				<td>
								    					<select id="xh3" 
															class="selectpicker form-control"  style="width:130px;" multiple data-s="multiple">
															<c:forEach var="act" items="${lawXH}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>	
		                                      </tr>
		                                      <tr>
		                                      		<th>渎职侵权</th>
								    				<td>
								    					<select id="dzqq2" name=""
															class="selectpicker form-control" multiple data-s="multiple">
															<c:forEach var="act" items="${lawDZQQ}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>
								    				<th>其他职务违法犯罪行为</th>
								    				<td>
								    					<select id="qtzwfz2" name=""
															class="selectpicker form-control" multiple data-s="multiple">
															<c:forEach var="act" items="${lawQTFZ}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>
		                                      
		                                      </tr>
		                                      <tr>
		                                      		<th>其他违法犯罪行为</th>
								    					<td colspan="5">
								    						<select id="qtfz1" name=""
																class="selectpicker form-control" multiple data-s="multiple" >
								
																<c:forEach var="act" items="${legalAct2}">
																	<option value="${act.dictId }">${act.name}</option>
																</c:forEach>
															</select>
								    					</td>
		                                      </tr>
		                                      <tr>
		                                      		<th>侵犯财产</th>
								    				<td>
								    					<select id="qfcc2" name=""
															class="selectpicker form-control" multiple data-s="multiple">
															<c:forEach var="act" items="${lawQFCC}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>
								    				<th>破坏社会主义市场经济秩序</th>
								    				<td>
								    					<select id="phzx2" name=""
															class="selectpicker form-control" multiple data-s="multiple">
															<c:forEach var="act" items="${lawPHZX}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>
								    				<th>妨害社会管理秩序</th>
								    				<td>
								    					<select id="fhzx2" name=""
															class="selectpicker form-control" multiple data-s="multiple">
															<c:forEach var="act" items="${lawFHZX}">
																<option value="${act.dictId }">${act.name}</option>
															</c:forEach>
														</select>
								    				</td>
		                                      </tr>
		                                       <tr>
													<td colspan="6"></td>
											 </tr>
											   <tr>
												 <td style="text-align:center;" colspan="6">--留置--</td>
																 
											 </tr> 
		                                      <tr>
		                                      		 <th>是否采取留置措施</th>									    			
										            <td>
										            <select id="measures" name="problemClues.measures"  class="form-control" data-s="select-one" style="width: 220px; height: 34px;" disabled>
		                                              <option value="0" >暂无</option>
		                                              <c:if test="${'1' ==problemClues.reflectedPerson.measures}">
										              <option selected="selected" value="1">是</option>  
										              <option   value="2">否</option>
										              </c:if>
										              <c:if test="${'2' ==problemClues.reflectedPerson.measures}"> 
										              <option  value="1">是</option>  
										               <option selected="selected"  value="2">否</option>
										               </c:if>
										               <c:if test="${'2' !=problemClues.reflectedPerson.measures && '1' !=problemClues.reflectedPerson.measures}"> 
										               <option  value="1">是</option>  
										               <option   value="2">否</option>
										               </c:if>
		                                               </select>
		                                            </td>
								                    <th>留置时间</th>
											     	<td>
											     	<a class='input-group date'> <input id="lienTime" type='text' class="form-control" name='' style="width: 170px; height: 30px;"	value="" disabled /> 
														<span class="input-group-addon" style="width: 50px; height: 30px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
													<th>留置依据：</th>
                                            		<td>
                                            		<input id="lienBasis" type="text" class="form-control" name="" style="width: 220px; height: 34px;" placeholder="请输入留置依据"  value="" disabled>
													</td>      
		                                      
		                                      </tr>
		                                      <tr>
		                                      		 <th>留置解除时间：</th>
											     	<td>
											     	<a class='input-group date'> <input id="lienRelieveTime" type='text' class="form-control" name='' style="width: 170px; height: 30px;"	value="" disabled /> 
														<span class="input-group-addon" style="width: 50px; height: 30px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
		                                      		<th>是否延期留置：</th>									    			
										            <td><select id="delayLien" name="" class="form-control" style="width: 220px;" disabled>
		                                            <option selected="selected" value="0" >暂无</option>
										            <option  value="1">是</option>  
										            <option   value="2">否</option>
		                                            </select>
		                                            </td>
		                                            <th>延期留置时间：</th>
											     	<td><a class='input-group date'> <input id="delayLienTime" name=""  type='text' class="form-control"  style="width: 170px; height: 34px;" /> 
														<span class="input-group-addon" style="width: 50px; height: 34px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
		                                     	
		                                      </tr>
		                                       <tr>
													<td colspan="6"></td>
											 </tr>
		                                      <tr>
		                                      		 <th>是否涉嫌犯罪：</th>									    			
										            <td>
		                                             <select id="crime2" name="problemClues.crime"  class="form-control" data-s="select-one" style="width: 220px; height: 34px;" disabled>
		                                              <option value="0" >暂无</option>
		                                              <c:if test="${'1' ==problemClues.reflectedPerson.crime}">
										              <option selected="selected" value="1">是</option>  
										            <option   value="2">否</option>
										            </c:if>
										            <c:if test="${'2' ==problemClues.reflectedPerson.crime}"> 
										            <option  value="1">是</option>  
										            <option selected="selected"  value="2">否</option>
										            </c:if>
										            <c:if test="${'2' !=problemClues.reflectedPerson.crime && '1' !=problemClues.reflectedPerson.crime}"> 
										            <option  value="1">是</option>  
										            <option   value="2">否</option>
										            </c:if>
		                                            </select>
		                                            
		                                            
		                                            </td>	 
		                                      <th>贪污贿赂犯罪</th>
		                                      <td>
		                                             <select id="twhl"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${twhl}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                      </select>
		                                     </td> 
		                                      <th>渎职犯罪</th>
		                                      <td>
		                                             <select id="dzfz"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${dzfz}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                      </select>
		                                      </td>
		                                      </tr>
		                                      <tr>
		                                       <th>利用职权侵犯公民人身权利、民主权利犯罪</th>
		                                           <td>
		                                             <select id="qfgm"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${qfgm}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                    </select>
		                                            </td> 
		                                            <th>其他利用职权犯罪</th>
		                                            <td>
		                                             <select id="qtly"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
						                              <c:forEach var="act" items="${qtly}">
								                         <option value="${act.dictId }">${act.name}</option>
		                                              </c:forEach>
                                                    </select>
		                                            </td> 
		                                            <th></th>
		                                            <td></td>
		                                       </tr>
		                                       <tr>
		                                            <th></th>
		                                            <td></td>
		                                       </tr>
		                                       <tr>
		                                       		<th>是否在逃</th>								    			
										            <td>
										            <select id="escape" name="problemClues.escape"  class="form-control" style="width: 220px; height: 34px; data-s="select-one" disabled>
		                                            <option value="0" >暂无</option>
		                                            <c:if test="${'1' ==problemClues.reflectedPerson.escape}">
										            <option selected="selected" value="1">是</option>  
										            </c:if>
										            <c:if test="${'2' ==problemClues.reflectedPerson.escape}"> 
										            <option selected="selected"  value="2">否</option>
										            </c:if>
		                                            </select>
		                                            </td>
		                                             <th>是否通缉</th>									    			
										            <td>
										             <select id="wanted" name="problemClues.wanted"  class="form-control" style="width: 220px; height: 34px; data-s="select-one" disabled>
		                                              <option value="0" >暂无</option>
		                                              <c:if test="${'1' ==problemClues.reflectedPerson.wanted}">
										              <option selected="selected" value="1">是</option>  
										               </c:if>
										              <c:if test="${'2' ==problemClues.reflectedPerson.wanted}"> 
										                   <option selected="selected"  value="2">否</option>
										              </c:if>
		                                            </select>
		                                            </td> 
		                                            <th>通缉时间：</th>
													<td><a class='input-group date'> <input type='text' name="reflectedPerson.wantedTime" class="form-control" style="width: 170px; height: 34px;" value="${problemClues.reflectedPerson.wantedTime }" /> 
														<span class="input-group-addon" style="width: 50px; height: 34px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
		                                       </tr>
		                                       <tr>
		                                       		 <th>涉案金额：</th>
                                            	 <td  class="input-group"><input type="text" style="width: 170px; height: 34px;"class="form-control" name="reflectedPerson.involvedMoney" placeholder="请输入金额"  value="${problemClues.reflectedPerson.involvedMoney}">
                                            	 <span class="input-group-addon" style="width: 50px; height: 34px;">
																万元
														</span></td>
                                    	         <th>搜查时间：</th>
											     	<td><a class='input-group date'><input id="searchTime" name="reflectedPerson.searchTime" type='text' class="form-control" style="width: 170px; height: 34px;"value="${problemClues.reflectedPerson.searchTime }"/> 
														<span class="input-group-addon" style="width: 50px; height: 34px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
		                                       		 <th>首次违纪违法时间：</th>
											     	<td><a class='input-group date'><input id="firstViolationTime" name="reflectedPerson.firstViolationTime" type='text' class="form-control" style="width: 170px; height: 34px;" value="${problemClues.reflectedPerson.firstViolationTime }"/> 
														<span class="input-group-addon" style="width: 50px; height: 34px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
		                                       </tr>
		                                       <tr>
		                                       		<th>末次违纪违法时间：</th>
											     	<td><a class='input-group date'><input id="finalViolationTime" name="reflectedPerson.finalViolationTime" type='text' class="form-control" style="width: 170px; height: 34px;" value="${problemClues.reflectedPerson.finalViolationTime }"/> 
														<span class="input-group-addon" style="width: 50px; height: 34px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
		                                       		<th>其他违纪违法时间：</th>
											     	<td><a class='input-group date'><input id="otherViolationTime" name="reflectedPerson.otherViolationTime" type='text' class="form-control" style="width: 170px; height: 34px;" value="${problemClues.reflectedPerson.otherViolationTime }"/> 
														<span class="input-group-addon" style="width: 50px; height: 34px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
                                    	         <th>调查中止时间：</th>
											     	<td><a class='input-group date'><input id="researchEndTime" name="reflectedPerson.researchEndTime" type='text' class="form-control" style="width: 170px; height: 34px;" value="${problemClues.reflectedPerson.researchEndTime }"/> 
														<span class="input-group-addon" style="width: 50px; height: 34px;">
																<span class="glyphicon glyphicon-calendar"></span>
														</span>
														</a>
													</td>
		                                       </tr>
		                                          
		                                           
								                    <tr> 
								                    
								                    <th></th>
											     	 <td>
													</td>	
								                    <th></th>
                                            		<td>
													</td> 
								             
								             
								                    </tr>
								                    <tr>
								                    <th>立案审查(调查)报告)</th>     
								                    <td colspan="5">
								                    <label id="lascdc_bg_l" for="lascdc_bg">文件下载</label>
					                                <input id="lascdc_bg" data-title="立案审查(调查报告)" style="visibility:hidden"  value=""  onclick="download(this)" disabled>		
								                    </td>
								                    </tr>
								                    <tr>
								                   <th>审查(调查)报告</th>
								    	             <td colspan="5">
											           <label id="scdc_bg_l" for="scdc_bg">文件下载</label>
											           <input id="scdc_bg" data-title="审查(调查)报告" style="visibility:hidden"  value=""  onclick="download(this)">		
								         			</td>
								                
								                
								                </tr>
								               </table>
								               </div>
								               <!-- 
								                
								                                          审理室案件处理 
								               
								                -->
								               <div class="tab-pane active" id="sls">
								               <table class="table  table-bordered table-hover">     
								               <tr>   
											   <th>受理时间</th>
								               <td>
																		   <a class='input-group date' > <input id="slsSLTime" disabled type='text' class="form-control" name='slsSLTime' style="width: 170px; height: 34px;"	value="" disabled/> 
																				<span class="input-group-addon" style="width: 50px; height: 34px;">
																					<span class="glyphicon glyphicon-calendar"></span>
																				</span>
																			 </a>
																		 </td>
																		  <th>审结时间</th>
																		 <td>
																		 <a class='input-group date' > <input id="sjTime"  disabled type='text' class="form-control" name='progress[0].timeForday' style="width: 170px; height: 34px;"	value="" /> 
																				<span class="input-group-addon" style="width: 50px; height: 34px;">
																					<span class="glyphicon glyphicon-calendar"></span>
																				</span>
																			 </a>
																		 </td>
																		 <%--  <th>审理建议</th>
																         	<td>
																         	<!-- <select id="sljy" class="form-control" data-s="select-one" name="progress[0].detail" disabled>
											                                             <option value="0">暂无</option>
											                                    </select> -->
											                                    <select  class="form-control" id="sljy" data-s="select-one" name="progress[0].detail" disabled>
			                                                             			<option value="0">暂无</option>
																                    <c:forEach var="act" items="${slMethod}">
																		                             <option value="${act.value }">${act.name}</option>
												                                    </c:forEach>
			                                           							</select>
											                                </td> --%>
																 </tr>
																 <tr>
																 <td style="text-align:center;" colspan="6">--处置建议--</td>
																 
																 </tr> 
																  <tr>							                                  
							                                        <th>党纪处分</th>
							                                        <td>
							                                             <select id="sljydjcf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_djcf}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                      </select>
							                                       </td>  
							                                     
							                                       <th>政务处分</th>
							                                       <td>
							                                             <select id="sljyzjcf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_zjcf}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                     </select>
							                                       </td> 
							                                       <th>组织处理</th>
							                                       <td>
							                                             <select id="sljyzzcl"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_zzcl}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                     </select>
							                                       </td> 
							                                      </tr>
							                                      <tr>
							                                       <th>移送司法</th>
							                                       <td>
							                                             <select id="sljyyssf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_yjsf}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                     </select>
							                                       </td> 
							                                       <th>其他处理</th>
							                                       <td>
							                                             <select id="sljyqtcl"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_qtcl}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                     </select>
							                                       </td>
							                                      </tr>
							                                      <tr>
																 	<td colspan="6"></td>
																 </tr>
																 <tr>
																 	<th>市委常委会议时间：</th>
																	 <td>
																	 <a class='input-group date'> <input type='text' id="swcwh" class="form-control" name='' style="width: 170px; height: 34px;"	value="" /> 
																			<span class="input-group-addon" style="width: 50px; height: 34px;">
																				<span class="glyphicon glyphicon-calendar"></span>
																			</span>
																		 </a>
																	 </td>
																	 <th>纪委监委会议时间：</th>
																	 <td>
																	 <a class='input-group date'> <input type='text' id="jwjwh" class="form-control" name='' style="width: 170px; height: 34px;"	value="" /> 
																			<span class="input-group-addon" style="width: 50px; height: 34px;">
																				<span class="glyphicon glyphicon-calendar"></span>
																			</span>
																		 </a>
																	 </td>
														         	 <th>收缴涉案金额：</th>
						                                             <td  class="input-group"><input type="text" style="width: 170px; height: 34px;"class="form-control" name="reflectedPerson.collectionMoney" placeholder="请输入金额"  value="${problemClues.reflectedPerson.collectionMoney}">
						                                            	 <span class="input-group-addon" style="width: 50px; height: 34px;">
																						万元
																		 </span>
																     </td>	
																 
																 </tr> 
																  <tr>
														         	<th>挽回经济损失：</th>
						                                             <td  class="input-group"><input type="text" style="width: 170px; height: 34px;"class="form-control" name="reflectedPerson.toSaveMoney" placeholder="请输入金额"  value="${problemClues.reflectedPerson.toSaveMoney}">
						                                            	 <span class="input-group-addon" style="width: 50px; height: 34px;">
																						万元
																		 </span>
																     </td>
														         	<th>结案时间：</th>
																	 <td>
																	 <a class='input-group date'> <input id="jaTime" type='text' class="form-control" name='' style="width: 170px; height: 34px;"	value="" /> 
																			<span class="input-group-addon" style="width: 50px; height: 34px;">
																				<span class="glyphicon glyphicon-calendar"></span>
																			</span>
																		 </a>
																	 </td>
																	 <th>是否属于问责</th>
																	<td><select id="isAccountability" name="reflectedPerson.isAccountability" class="form-control" disabled>
																			<option value="0">请选择</option>
																			<c:if test="${'1' ==problemClues.reflectedPerson.isAccountability}">
																				<option selected="selected" value="1">是</option>
																				<option value="2">否</option>
																			</c:if>
																			<c:if test="${'2' ==problemClues.reflectedPerson.isAccountability}">
																				<option value="1">是</option>
																				<option selected="selected" value="2">否</option>
																			</c:if>
																			<c:if
																				test="${'2' !=problemClues.reflectedPerson.isAccountability && '1' !=problemClues.reflectedPerson.isAccountability}">
																				<option value="1">是</option>
																				<option value="2">否</option>
																			</c:if>
																		</select>
																	</td>
														         </tr>
														          <tr>
																 <td style="text-align:center;" colspan="6">--处理结果--</td>
																 
																 </tr> 
																  <tr>							                                  
							                                        <th>党纪处分</th>
							                                        <td>
							                                             <select id="sljgdjcf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_djcf}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                      </select>
							                                       </td>  
							                                    
												    				<th>处分决定生效时间：</th>
																	<td>
																	<a class='input-group date'> <input id="cfsxTime" type='text' class="form-control" name='reflectedPerson.takeEffectTime' style="width: 170px; height: 34px;" value="${problemClues.reflectedPerson.takeEffectTime}" disabled/> 
																			<span class="input-group-addon" style="width: 50px; height: 34px;">
																				<span class="glyphicon glyphicon-calendar"></span>
																			</span>
																		 </a>
																	 </td>
																	 <th>留党察看年限：</th>
																	<td>
																	<a class='input-group date'> <input id="ldckTime" type='text' class="form-control" name='reflectedPerson.stayTerm' style="width: 170px; height: 34px;" value="${problemClues.reflectedPerson.stayTerm}" disabled/> 
																			<span class="input-group-addon" style="width: 50px; height: 34px;">
																				<span class="glyphicon glyphicon-calendar"></span>
																			</span>
																		 </a>
																	 </td>
							                                      
							                                      </tr>
							                                      <tr>
							                                      		 <th>政务处分</th>
									                                       <td>
									                                             <select id="sljgzjcf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
													                              <c:forEach var="act" items="${blResult_zjcf}">
															                         <option value="${act.dictId }">${act.name}</option>
									                                              </c:forEach>
							                                                     </select>
									                                       </td> 
							                                       
							                                      		<th>解除处分：</th>									    			
															            <td>
							                                            <select id="jccf" name="reflectedPerson.relievePunishment" class="form-control">
																			<option value="0">请选择</option>
																			<c:if test="${'1' ==problemClues.reflectedPerson.relievePunishment}">
																				<option selected="selected" value="1">是</option>
																				<option value="2">否</option>
																			</c:if>
																			<c:if test="${'2' ==problemClues.reflectedPerson.relievePunishment}">
																				<option value="1">是</option>
																				<option selected="selected" value="2">否</option>
																			</c:if>
																			<c:if
																				test="${'2' !=problemClues.reflectedPerson.relievePunishment && '1' !=problemClues.reflectedPerson.relievePunishment}">
																				<option value="1">是</option>
																				<option value="2">否</option>
																			</c:if>
																		</select>
							                                            
							                                            </td>
							                                            <th>恢复党员权力：</th>									    			
															            <td>
							                                            <select id="hfql1" name="reflectedPerson.recoveryPower" class="form-control" disabled>
																			<option value="0">请选择</option>
																			<c:if test="${'1' ==problemClues.reflectedPerson.recoveryPower}">
																				<option selected="selected" value="1">是</option>
																				<option value="2">否</option>
																			</c:if>
																			<c:if test="${'2' ==problemClues.reflectedPerson.recoveryPower}">
																				<option value="1">是</option>
																				<option selected="selected" value="2">否</option>
																			</c:if>
																			<c:if
																				test="${'2' !=problemClues.reflectedPerson.recoveryPower && '1' !=problemClues.reflectedPerson.recoveryPower}">
																				<option value="1">是</option>
																				<option value="2">否</option>
																			</c:if>
																		</select>
							                                            </td>
							                                            
							                                      </tr>
							                                      <tr>
							                                      <th>组织处理</th>
							                                       		<td>
							                                             <select id="sljgzzcl"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_zzcl}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                     </select>
							                                       </td> 
							                                       <th>移送司法</th>
							                                       <td>
							                                             <select id="sljgyssf"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
											                              <c:forEach var="act" items="${blResult_yjsf}">
													                         <option value="${act.dictId }">${act.name}</option>
							                                              </c:forEach>
					                                                     </select>
							                                       </td> 
							                                       <th>移送司法机关时间：</th>
																	 <td><a class='input-group date'> <input id="ysTime" type='text' class="form-control" name='reflectedPerson.ysTime' style="width: 170px; height: 34px;"	value="${problemClues.reflectedPerson.ysTime}" disabled/> 
																			<span class="input-group-addon" style="width: 50px; height: 34px;">
																				<span class="glyphicon glyphicon-calendar"></span>
																			</span>
																		 </a>
																	 </td>
							                                      
							                                      </tr>
																 <tr>
																 		 <th>其他处理</th>
									                                       <td>
									                                             <select id="sljgqtcl"   style=" width: 220px; height: 34px;" class="selectpicker form-control" multiple  data-s="multiple" disabled>
													                              <c:forEach var="act" items="${blResult_qtcl}">
															                         <option value="${act.dictId }">${act.name}</option>
									                                              </c:forEach>
							                                                     </select>
									                                       </td>
																 </tr>
															<%-- 	 <tr>
																        <c:if test="${not empty reflectedPerson.charge}">
																        <th>移送罪名</th>
																		<td>
																		<input type="text" disabled class="form-control"style="width: 220px; height: 34px;" data-options="width:'100%'" id="charge" name="problemClues.charge" value="${reflectedPerson.charge}" />
																		</td>
																		</c:if>	 
																		<c:if test="${not empty reflectedPerson.ysTime}">				
																		 <th>移送时间</th>
																		<td><a class='input-group date' > <input id="ysTime" disabled type='text' class="form-control" name='problemClues.ysTime' style="width: 170px; height: 34px;"	value="${reflectedPerson.ysTime }" /> 
																				<span class="input-group-addon" style="width: 50px; height: 34px;">
																					<span class="glyphicon glyphicon-calendar"></span>
																				</span>
																			</a>
																		 </td>	
																		</c:if> 
																   </tr> --%>
																                 <tr> 
																                 <th>审理报告</th>
																	         	 <td colspan="5">
																	             <label  id="slbg_l"  for="slbg">文件下载</label>
																	             <input id="slbg"     value="" data-title="审理报告"  style="visibility:hidden"  onclick="download(this)">		
																	             </td>
																	             </tr> 
																	             <tr>          
																	             <th>处分决定</th>
																	        	 <td colspan="5">
																	             <label id="cfjd_l" for="cfjd">文件下载</label>
																	             <input id="cfjd" data-title="处分决定" style="visibility:hidden"  value="" onclick="download(this)">							       
																	             </td>
																	             </tr>
																	             <tr>         
																	             <th>起诉意见书</th>
																	         	 <td colspan="5">
																	         	 <label id="qsyj_s_l" for="qsyj_s">文件下载</label>
																	             <input id="qsyj_s" data-title="起诉意见书" style="visibility:hidden"  value=""  onclick="download(this)">		
																	         	 </td>
																	             </tr>
																	             </table>
												                                 </div>     
								                    
								             </div>
								 		     </div>
								 		     </div>
								             </form>
								             
								           
								     
								        
								 		 
								       
						</div>    		   
								       
								   
								
                          
	<script type="text/javascript">
	   $(function(){
		   var iSupervision = $('#iSupervision').selectpicker('val');
	   	   if(iSupervision=="1"){
	   		   $("#supervision").removeAttr("disabled"); 
	   	   }else{
	   		   $('#supervision').attr("disabled","disabled"); 
	   	   }
	   	   
	   	   var isPMSupervisoryObject = $('#isPMSupervisoryObject').selectpicker('val');
	   	   if(isPMSupervisoryObject=="1"){
	   		   $("#pMSupervisoryObject").removeAttr("disabled"); 
	   	   }else{
	   		   $('#pMSupervisoryObject').attr("disabled","disabled"); 
	   	   }
		   $("#xl").val($("#xlData").val());  
    	   $("#mz").val($("#mzData").val());  
	   });
	   $('#myTab a:first').tab('show');
	   
	   $(function(){
		   $('#myTab a').click(function (e) {
			   e.preventDefault();
			   $(this).tab('show');
			 }) 
	   });
	   
	   $("a.input-group.date").each(function(){
		    $(this).datetimepicker({
                language: 'zh-CN',//显示中文
                format: 'yyyy-mm-dd',//显示格式
                minView: "month",//设置只显示到月份
                initialDate: new Date(),
                autoclose: true,//选中自动关闭
                todayBtn: true,//显示今日按钮
                locale: moment.locale('zh-cn')
            })
	   });
	   function  thisPage(orgaName){
		   $.ajax({
			  url:"${path}/probleClues/getProgress" ,
			  data:{cluesId:$("#cluesId").val(),orgaName:orgaName},
			  type:"post",
			  dataType:"json",
			  success:function(result){
				  console.log(result);
				   if(result.success){
					  
					  var jdsNext;
					  var jdsCbId;
					  if(!result.obj.progress){
					  if(orgaName=="jds"){
						   $("#jds").html("");
						   var html=
					        	'<table  class="table  table-bordered table-hover p">'+
					        	  '<th>受案时间</th>'+
					 	             '<td><a class="input-group date" id=c8" >'+
					 	                '<input id="jdsTime" type="text" class="form-control" style="width: 150px; height: 30px;" value=""  disabled/>'+
					 	                '<span class="input-group-addon" style="width: 50px; height: 30px;">'+
					 	                              '<span class="glyphicon glyphicon-calendar" disabled></span>'+
					 	                '</span>'+
					 	                '</a></td> '+
					 	                '</tr>'+
					    	    '<th>处置建议</th>'+
					            '<td><select id="cccp" class="form-control"  data-cccp="sss" disabled  data-s="select-one" name="progress[1].detail" onChange="return change()">'+
			                    '<option value="0">暂无</option>'+
			                    '<option value="1">谈话函询</option>'+
			                    '<option value="2">初步核实</option>'+
			                    '<option value="3">暂存待查</option>'+
			                    '<option value="4">予以了结</option>'+
			                    '<option value="7">给予组织处理</option>'+
			                    '</select></td>'+
			                    
			                    '</table>';
					    	   $("#jds").append(html);
					   }
					  
					 
						  return false;
					  }
					  if(orgaName=="jds"){
						  $("#jds").html("");
					  }
					  var jdsTime="";
					  $.each( result.obj.progress, function(index, content){
						  /**
						  *案管室办理情况
						  */
						  var name = content.pointName;
						  if(content.pointName.indexOf("案管室")!=-1){
							  if(content.pointName=="案管室分办"){
								  if(content.detail=="执纪审查专题会议"){
									  $("#zjscTime").val(content.timeForday.substring(0,10));	
								  }else if(content.detail<"18"){
									  $("#fbTime").val(content.timeForday.substring(0,10));
									  $("#organId1").val(content.detail);
								  }else{
									  $("#fbTime").val(content.timeForday.substring(0,10));
									  $("#organId1").val(content.detail);
									  /*  $("#fbTimeAagain").val(content.timeForday.substring(0,10));
									  $("#organId2").val(content.detail); */
								  }
								 
							  }
							  if(content.pointName=="案管室受理"){
								  $("#agslTime").val(content.time.substring(0,10));
							  }
							  if(content.pointName=="返回案管室"){
								  $("#agslTime_again").val(content.time.substring(0,10));
							  }
							  if(content.pointName=="案管室受理再分办"){
								  $("#agslTime").val(content.time.substring(0,10));
								    if(content.detail=="执纪审查专题会议"){
								    	 $("#zjscTime_again").val(content.timeForday.substring(0,10));
								    }else{
								    	 $("#fbTime_again").val(content.timeForday.substring(0,10));
								    	 $("#organId1_again").val(content.detail);
								    }
							  }
						  }
						  
						  if(name=="各区县受理"){
							  $("#xqcbTime").val(content.time.substring(0,10));
						  }
						  
						  
						  
						  if(content.pointName.indexOf("审查室")!=-1||content.pointName=="立案审查"||content.pointName=="审理室受理"||content.pointName=="市委主要领导签批意见"){
							  
							  if(content.pointName=="审查室处置建议"){
								  $("#scJY1").val(content.detail);
								  $("#chtime").val(content.timeForday.substring(0,10));
							  }
							  if(content.pointName=="审查室领导审批意见"){
								  $("#spyjWhere").val(content.detail);
								  $("#sptime").val(content.timeForday);
							  }
							  
							  if(content.pointName=="审查室受理"){
								  $("#scstime").val(content.time.substring(0,10));
							  }
							 
							  /* if(content.pointName=="审理室受理"){
								  $("#chtime").val(content.time.substring(0,10));
							  } */
							  if(content.pointName=="市委主要领导签批意见"){
								  
								  $("#qpyj1").val(content.detail);
								  $("#qpTime").val(content.timeForday.substring(0,10));
							  }
							  if(content.pointName=="审查室会议决定"){
								  $("#zjscTime2").val(content.timeForday.substring(0,10));
								  $("#spyjWhere2").val(content.detail);
							  }
							  //审查室小组
							  if(result.obj.zu_yuan){
					         	    $('#zu_yuan').val(result.obj.zu_yuan.groupName);
							  }
							  if(result.obj.zu_zhang){
									  $("#zu_zhang").val(result.obj.zu_zhang.groupName);
							  }
							  if(result.obj.chbg){
								  if(result.obj.chbg){
                                  $("#chbg_s_l").css("color","green");									  
								  $("#chbg_s").val(result.obj.chbg.url);
								  }
								  
							  }
							  if(result.obj.chfa){
								  if(result.obj.chfa){
                                  $("#chfa_s_l").css("color","green");									  
								  $("#chfa_s").val(result.obj.chfa.url);
								  }
								  
							  }
							  if(result.obj.chqs){
								  if(result.obj.chqs){
                                  $("#chqs_s_l").css("color","green");									  
								  $("#chqs_s").val(result.obj.chqs.url);
								  }
								  
							  }
							  if(result.obj.scbg){
								  if(result.obj.scbg){
                                  $("#scbg_s_l").css("color","green");									  
								  $("#scbg_s").val(result.obj.scbg.url);
								  }
								  
							  }
							  if(result.obj.scgzfa){
								  if(result.obj.scgzfa){
                                  $("#scgzfa_s_l").css("color","green");									  
								  $("#scgzfa_s").val(result.obj.scgzfa.url);
								  }
								  
							  }
							  //留置
						      if(result.obj.lien1){
						    	  lien1=result.obj.lien1;
						    	  $("#lienTime").val(lien1.lienTime);
						    	  $("#lienBasis").val(lien1.lienBasis);
						    	  $("#lienRelieveTime").val(lien1.lienRelieveTime);
						    	  $("#delayLien").val(lien1.delayLien);
						    	  $("#delayLienTime").val(lien1.delayLienTime);
						    	  
						      }
							  
						  }
						  if(content.pointName=="立案审查（调查）"){
							  if(content.pointDetail="纪委立案时间"){
								  $("#jiwla").val(content.timeForday.substring(0,10));
							  }
							  if(content.pointDetail="监委立案时间"){
								  $("#jianwla").val(content.timeForday.substring(0,10));
							  }
							  
							      if(result.obj.dcbg){
                                  $("#scdc_bg_l").css("color","green");									  
								  $("#scdc_bg").val(result.obj.dcbg.url);
								  }
							      if(result.obj.lascbg){
                                  $("#lascdc_bg_l").css("color","green");									  
								  $("#lascdc_bg").val(result.obj.lascbg.url);
								  }
							      $("#sc_czjy").val(content.detail);
							      console.log(":::::::::"+result);
							      //党纪处分
							      if(result.obj.djcf){
									    var arr=result.obj.djcf.split(',');
									    $("#djcf").selectpicker('val', arr);
								  }
							      if(result.obj.zjcf){
									    var arr=result.obj.zjcf.split(',');
									    $("#zjcf").selectpicker('val', arr);
								  }
							      if(result.obj.zzcl){
									    var arr=result.obj.zzcl.split(',');
									    $("#zzcl").selectpicker('val', arr);
								  }
							      if(result.obj.yssf){
									    var arr=result.obj.yssf.split(',');
									    $("#yssf").selectpicker('val', arr);
								  }
							      if(result.obj.qtcl){
									    var arr=result.obj.qtcl.split(',');
									    $("#qtcl").selectpicker('val', arr);
								  }
							      console.log("+++++++++"+result);
							      //罪名
							      if(result.obj.twhl){
							    	     var arr=result.obj.twhl.split(',');
									    $("#twhl").selectpicker('val', arr);
							      }
							      if(result.obj.dzfz){
							    	     var arr=result.obj.dzfz.split(',');
									    $("#dzfz").selectpicker('val', arr);
							      }
							      if(result.obj.qfgm){
							    	     var arr=result.obj.qfgm.split(',');
									    $("#qfgm").selectpicker('val', arr);
							      }
							      if(result.obj.qtly){
							    	     var arr=result.obj.qtly.split(',');
									    $("#qtly").selectpicker('val', arr);
							      }
							      //违纪行为
							      if(result.obj.zyViolation1){
							    	     var arr=result.obj.zyViolation1.split(',');
									    $("#zyViolation").selectpicker('val', arr);
							      }
							      //涉法行为
							      if(result.obj.zwfz1){
							    	     var arr=result.obj.zwfz1.split(',');
									    $("#zwfz1").selectpicker('val', arr);
							      }
							      if(result.obj.twhl2){
							    	     var arr=result.obj.twhl2.split(',');
									    $("#twhl2").selectpicker('val', arr);
							      }
							      if(result.obj.sh3){
							    	     var arr=result.obj.sh3.split(',');
									    $("#sh3").selectpicker('val', arr);
							      }
							      if(result.obj.xh3){
							    	     var arr=result.obj.xh3.split(',');
									    $("#xh3").selectpicker('val', arr);
							      }
							      if(result.obj.dzqq2){
							    	     var arr=result.obj.dzqq2.split(',');
									    $("#dzqq2").selectpicker('val', arr);
							      }
							      if(result.obj.qtzwfz2){
							    	     var arr=result.obj.qtzwfz2.split(',');
									    $("#qtzwfz2").selectpicker('val', arr);
							      }
							      if(result.obj.qtfz1){
							    	     var arr=result.obj.qtfz1.split(',');
									    $("#qtfz1").selectpicker('val', arr);
							      }
							      if(result.obj.qfcc2){
							    	     var arr=result.obj.qfcc2.split(',');
									    $("#qfcc2").selectpicker('val', arr);
							      }
							      if(result.obj.phzx2){
							    	     var arr=result.obj.phzx2.split(',');
									    $("#phzx2").selectpicker('val', arr);
							      }
							      if(result.obj.fhzx2){
							    	     var arr=result.obj.fhzx2.split(',');
									    $("#fhzx2").selectpicker('val', arr);
							      }
							      //审查室小组
								  if(result.obj.zu_yuan2){
						         	      $('#zu_yuan2').val(result.obj.zu_yuan2.groupName);
								  }
								  if(result.obj.zu_zhang2){
										  $("#zu_zhang2").val(result.obj.zu_zhang2.groupName);
								  }
						  }
						  if(content.pointName.indexOf("审理室")!=-1){
							  if(content.pointName=="审理室审理建议"){
								  $("#sljy").val(content.detail);
								  $("#sjTime").val(content.timeForday.substring(0,10));
							  }
							  if(content.pointName=="审理室处理结果"){
								  $("#jaTime").val(content.timeForday.substring(0,10));
								  $("#jg").val(content.detail);
							  }
							  if(content.pointName=="审理室受理时间"){
								  $("#slsSLTime").val(content.timeForday.substring(0,10));
							  }
							  if(content.pointName=="审理室受理"){
								  $("#slsSLTime").val(content.time.substring(0,10));
								  
							  }
							  
							      if(result.obj.cfjd){
                                  $("#cfjd_l").css("color","green");									  
								  $("#cfjd").val(result.obj.cfjd.url);
								  }
							      if(result.obj.slbg){
                                  $("#slbg_l").css("color","green");									  
								  $("#slbg").val(result.obj.slbg.url);
								  }
							      if(result.obj.yjs){
                                  $("#qsyj_s_l").css("color","green");									  
								  $("#qsyj_s").val(result.obj.yjs.url);
								  }
							      //党纪处分
							      if(result.obj.sljydjcf){
									    var arr=result.obj.sljydjcf.split(',');
									    $("#sljydjcf").selectpicker('val', arr);
								  }
							      if(result.obj.sljyzjcf){
									    var arr=result.obj.sljyzjcf.split(',');
									    $("#sljyzjcf").selectpicker('val', arr);
								  }
							      if(result.obj.sljyzzcl){
									    var arr=result.obj.sljyzzcl.split(',');
									    $("#sljyzzcl").selectpicker('val', arr);
								  }
							      if(result.obj.sljyyssf){
									    var arr=result.obj.sljyyssf.split(',');
									    $("#sljyyssf").selectpicker('val', arr);
								  }
							      if(result.obj.sljyqtcl){
									    var arr=result.obj.sljyqtcl.split(',');
									    $("#sljyqtcl").selectpicker('val', arr);
								  }
							      //市委常委会时间、纪委监委会时间
							      if(content.pointName=="审理室市委常委会"){
									  $("#swcwh").val(content.timeForday.substring(0,10));
								  }
							      if(content.pointName=="审理室纪委监委会"){
									  $("#jwjwh").val(content.timeForday.substring(0,10));
								  }
							      if(result.obj.sljgdjcf){
									    var arr=result.obj.sljgdjcf.split(',');
									    $("#sljgdjcf").selectpicker('val', arr);
								  }
							      if(result.obj.sljgzjcf){
									    var arr=result.obj.sljgzjcf.split(',');
									    $("#sljgzjcf").selectpicker('val', arr);
								  }
							      if(result.obj.sljgzzcl){
									    var arr=result.obj.sljgzzcl.split(',');
									    $("#sljgzzcl").selectpicker('val', arr);
								  }
							      if(result.obj.sljgyssf){
									    var arr=result.obj.sljgyssf.split(',');
									    $("#sljgyssf").selectpicker('val', arr);
								  }
							      if(result.obj.sljgqtcl){
									    var arr=result.obj.sljgqtcl.split(',');
									    $("#sljgqtcl").selectpicker('val', arr);
								  }
						  }
						 
						  if(content.pointName=="监督室处置决定"||content.pointName=="监督室处置建议"){
							     console.log("=========");
							      console.log(content.pointName);
							      if(content.pointName=="监督室处置决定"){
									  jdsTime=content.time.substring(0,10);
									  console.log(jdsTime+"=========");
									  $("#jdsTime").val(jdsTime);
								  }
							      if(content.pointName=="监督室处置建议"&&content.detail.length<4){
							        var html=
							        	'<table  class="table  table-bordered table-hover p">'+
							        	'<tr>'+
							        	'<th>受案时间</th>'+
							 	        '<td><a class="input-group date" id=c8">'+
							 	        '<input id="jdsTime" type="text" class="form-control" style="width: 170px; height: 30px;" value="" />'+
							 	        '<span class="input-group-addon" style="width: 50px; height: 30px;">'+
							 	        '<span class="glyphicon glyphicon-calendar"></span>'+
							 	        '</span>'+
							 	        '</a></td> '+
							 	        '</tr>'+
							    	    '<th>处置建议</th>'+
							            '<td><select id="'+content.id+'cccp" class="form-control"  data-cccp="sss" disabled  data-s="select-one" name="progress[1].detail" style="width: 220px; height: 30px;" onChange="return change()">'+
					                    '<option value="0">暂无</option>'+
					                    '<option value="1">谈话函询</option>'+
					                    '<option value="2">初步核实</option>'+
					                    '<option value="3">暂存待查</option>'+
					                    '<option value="4">予以了结</option>'+
					                    '<option value="7">给予组织处理</option>'+
					                    '</select></td>'+
					                    '<tr>'+
					                    '<th>承办人</th>'+
					                    '<td>'+
					                    '<input id="cbr" class="form-control" type=text value="" style="width: 220px; height: 30px;" disabled/>'+
					                    '</td>'+
					                    
					                   
					                    '</tr>'+
					                    '</table>';
							    	   $("#jds").append(html);
							    	   //添加他的下一级
							    	   if(content.detail=="1"){
							    		   //谈话函询
							    		   jdsNext="thhx";
							    		   return true; 
							    	   }
							    	   //初步核实的
							     	  if(content.detail=="2"){
							     		    jdsNext="cbhs";
							     		   return true; 
							     	  }
							     	  if(content.detail=="3"){
							     		  //暂存待查
							     		    jdsNext="zcdc";
							     		   return true; 
							     	  }
							     	 if(content.detail=="4"){
							     		  //予以了结
							     		  jdsNext="yylj";
							     		 return true; 
							     	  }
							     	 if(content.detail=="7"){
							     		 //组织处理
							     		  jdsNext="zzcl";
							     		 return true; 
							     	  }
							      }
							      if(content.pointName=="监督室处置决定"&&content.detail.length<4){
							    	      //加载下一级
							     	   if(jdsNext=="thhx"){
							    		   //谈话函询
							    	        var html=makeThhx(content.id);
							    		    $("#jds").append(html);
							    		    if(content.detail=="2"){
								     		    jdsNext="cbhs";
								     		   return true; 
								     	    }
								     	    if(content.detail=="3"){
								     		  //暂存待查
								     		    jdsNext="zcdc";
								     		   return true; 
								     	     }
								     	      if(content.detail=="4"){
								     		  //予以了结
								     		  jdsNext="yylj";
								     		 return true; 
								     	      }
								     	      if(content.detail=="7"){
								     		 //组织处理
								     		  jdsNext="zzcl";
								     		 return true; 
								     	      }
							    	   }
							    	      
							    	   //初步核实的
							     	  if(jdsNext=="cbhs"){
							     		 var html=makeCbhs(content.id);
							     		$("#jds").append(html);
							     		 if(content.detail=="1"){
								    		   //谈话函询
								    		   jdsNext="thhx";
								    		   return true; 
								    	   }
								    	   //初步核实的
								     	  if(content.detail=="2"){
								     		    jdsNext="cbhs_ok";
								     		    jdsCbId=content.id;
								     		   return true; 
								     	  }
								     	  if(content.detail=="3"){
								     		  //暂存待查
								     		    jdsNext="zcdc";
								     		   return true; 
								     	  }
								     	 if(content.detail=="4"){
								     		  //予以了结
								     		  jdsNext="yylj";
								     		 return true; 
								     	  }
								     	 if(content.detail=="7"){
								     		 //组织处理
								     		  jdsNext="zzcl";
								     		 return true; 
								     	  }
							     		
							     	  }
							     	  if(jdsNext=="zcdc"){
							     		 var html=makeOther("暂存待查",content.id);
							     		$("#jds").append(html);
							     		return true; 
							     	  }
							     	 if(jdsNext=="yylj"){
							     		 var html=makeOther("予以了结",content.id);
							     		$("#jds").append(html);
							     		return true; 
							     	  }
							     	 if(jdsNext=="zzcl"){
							     		 var html=makeOther("给予组织处理",content.id);
							     		$("#jds").append(html);
							     		return true; 
							     	
							     		
							     	  }
							     	  
							     	 
							      }
							    
								  
							    
						  }
						  if(content.pointName=='监督室下发案件'){
							  console.log(content);
							  $("#cbr2").val(content.user.name);
							  $("#jcdw").val(content.detail);
						  }
						  
					  });
					  
					  
					  if(jdsTime!=""){
						  $("#jdsTime").val(jdsTime);
					  }
					  
					    //再遍历一遍数据为刚才创建的html赋值
				       $.each( result.obj.progress, function(index, content){
				    	   if($("#"+content.id+"cccp").data("cccp")){
				    		   //处置建议
				    		
				    		   $("#"+content.id+"cccp").val(content.detail);
				    		   if(content.user){
				    			   $("#cbr").val(content.user.name);
				    		   }
				    		   
				    	   }
				    	   if($("#"+content.id+"other").data("title")){
				    		   //暂存待查等
				    		   
				    	   }
				    	   if($("#"+content.id+"czfs").data("czfs")){
				    		   //初步核实
				    		   $("#"+content.id+"czfs").val(content.detail);
				    		   //
				    		   $("#"+content.id+"zjTime").val(content.timeForday.substring(0,10));
				    		   
				    	   }
				    	   if($("#"+content.id+"ThhxCzfs").data("thhx")){
				    		   //谈话函询
				    		
				    		
				    		 
				    		   //谈话函询时间
				    		   $("#"+content.id+"ThhxTime").val(content.timeForday.substring(0,10));
				    		   //处置方式
				    		    
				    		    $("#"+content.id+"ThhxCzfs").val(content.detail);
				    		   //处置情况报告
				    		   
				    		   //处置方案
				    	   }
				    	   if(jdsNext=="cbhs_ok"&&content.detail=="初步核实时间"){
				    		   $("#cbhsTime").val(content.timeForday.substring(0,10));
				    	   }
				       });
				   
				  }
				   
				   if(orgaName=="jds"){
				   var  thhx_num=0;
				   var  chbg_num=0;
				   var  thhx_fa_num=0;
				   //加载文件
				   $(".file").each(function(){
					   var title=$(this).data("title");
					   if(title=="谈话函询处置情况报告"){
						   if(result.obj.thhx_bg){
						       console.log(result.obj.thhx_bg);
							   $(this).val(result.obj.thhx_bg[thhx_num].url);
							   thhx_num+=1;
							   $("#"+this.id+"_l").css("color","green");
							   
						   }
					   }
					   if(title=="谈话函询处置方案"){
						   if(result.obj.thhx_fa){
						
						   $(this).val(result.obj.thhx_fa[thhx_fa_num].url);
						   thhx_fa_num+=1;
						   $("#"+this.id+"_l").css("color","green");
						   
						   }
				       }
					   if(title=="初步核实处置情况报告"){
						       if(result.obj.cbhs_bg){
						    	   
						      
						       $(this).val(result.obj.cbhs_bg[chbg_num].url);
						       chbg_num+=1;
						       $("#"+this.id+"_l").css("color","green");
						       }
					   }
					   if(title=="暂存待查处置情况报告"){
						       if(result.obj.zcdc_bg){
						    	
						       $(this).val(result.obj.zcdc_bg[zcdc_num].url);
						       zcdc_num+=1;
						       $("#"+this.id+"_l").css("color","green");
						       
						       }
					   }
					   if(title=="给予组织处理处置情况报告"){
						       if(result.obj.zzcl_bg){
						    	
						       $(this).val(result.obj.zzcl_bg[zzcl_num].url);
						       zzcl_num+=1;
						       $("#"+this.id+"_l").css("color","green");
						       
						       }
					   }
					   if(title=="予以了结处置情况报告"){
						       if(result.obj.yylj_bg){
						    	
						       $(this).val(result.obj.yylj_bg[chbg_num].url);
						       chbg_num+=1;
						       $("#"+this.id+"_l").css("color","green");
						       
						       }
					   }
					   
				   });
				   }
			  }
		   });
	   }
	   
	   
	   
	   
	   
	   
	   
	   
       function download(obj){
    	   //先判断文件是否存在
    	   $.ajax({
    		  url:"${path}/probleClues/isHaveFile",
    		  data:{filePath:obj.value},
    		  dataType:'json',
    		  type:'post',
    		  success:function(result){
    			  if(result.success){
    				  if(obj.value==""){
    			           field("您还没有上传文件");
    			    	   }else{
    			    	   downloadFile("${path}/probleClues/accDownload",obj.value,obj.getAttribute("data-title"));
    			    	   }
    			  }else{
    				  field('文件不存在或已删除！');
    			  }
    		  }
    	   });
    	   
    	   
    	   
    	 
    	  
       }
	   
       function downloadFile(actoinURL,filePath,fileName){  
       	
       	
  	     var form = $("<form>");     
  	    $('body').append(form);    
  	        form.attr('style','display:none');     
  	        form.attr('target','');  
  	        form.attr('method','post');  
  	        form.attr('action',actoinURL);//下载文件的请求路径  
  	          
  	          
  	        var input1 = $('<input>');   
  	        input1.attr('type','hidden');   
  	        input1.attr('name','filePath');   
  	        input1.attr('value',filePath);  
  	        form.append(input1);    
  	        var input2 = $('<input>');   
  	        input2.attr('type','hidden');   
  	        input2.attr('name','fileName');   
  	        input2.attr('value',fileName);  
  	        form.append(input2);  
  	          
  	        form.submit();      
  	      
  	    }; 
		   
	   function makeOther(text,index){
		   var name=text+'处置情况报告';
		         var html=
		        	 
		        	  '<table   class="table  table-bordered table-hover p">'+
					  '<tr><td colspan="6">'+text+'</td></tr>'	+
					  '<tr>'+
			          '<th>处置情况报告</th>'+
			          '<td colspan="5">'+
			          '<input  id="'+index+'other"  style="visibility:hidden"  class="file"  data-title="'+text+'处置情况报告"  value="" onclick="download(this);"/>'+
			          '<label  id="'+index+'other_l" for="'+index+'other">文件下载</label>'+
			          '</td>'+
				      '</tr></table>';	 
		   
		        return html;
		   
	   }
	 
 	   function makeCbhs(index){
 			 //加载初步核实的操作
 				var html=
 					'<table  class="table  table-bordered table-hover p">'+
 		            '<th>执纪监督专题会议时间</th>'+
 		            '<td>  <a class="input-group date" id="c52">'+
 	                   '<input id="'+index+'zjTime"  type="text" class="form-control"  style="width: 170px; height: 30px;" />'+
 	                   '<span class="input-group-addon" style="width: 50px; height: 30px;">'+
 	                                 '<span class="glyphicon glyphicon-calendar"></span>'+
 	                   '</span>'+
 	                   '</a>'    +  
 	               '</td>'+
 	               '</tr>'+
 	               '<tr>'+
 	               '<th>初步核实处置方式</th>' +   
 	                   '<td><select id="'+index+'czfs"  data-czfs="czfs" class=" cxls form-control"   style="width: 300px; height: 45px;"  disabled>'+
 	                    '<option value="0">暂无</option>'+
 	                    '<option value="1">谈话函询</option>'+
 	                    '<option value="2">初步核实</option>'+
 	                    '<option value="3">暂存待查</option>'+
 	                    '<option value="4">予以了结</option>'+
 	                    '<option value="7">给予组织处理</option>'+
 	              '</select>'+
 	              '</td>'+
 		        '</tr>'	+
 		        '<tr>'+
 		        '<th>处置情况报告</th>'+
 		        '<td colspan="5">'+
 		        '<label id="'+index+'cbhs_bg_l" for="'+index+'cbhs_bg">文件下载</label>'+
 		        '<input  id="'+index+'cbhs_bg" style="visibility:hidden" class="file" data-title="初步核实处置情况报告" value="" onclick="download(this);"/>'+
 		        '</td>'+
 		        '</tr></table>';
 		        return html;
 		   };
 		   
 	
 		   
 		   function makeThhx(index){
 			    //谈话函询
 				var html=
 			    '<table  class="table  table-bordered table-hover p">'+
 			    '<tr>'+
 	            '<th>谈话函询时间</th>'+
 	             '<td><a class="input-group date" id=c8">'+
 	                '<input id="'+index+'ThhxTime" type="text" class="form-control" style="width: 300px; height: 45px;" value="" />'+
 	                '<span class="input-group-addon" style="width: 50px; height: 30px;">'+
 	                              '<span class="glyphicon glyphicon-calendar"></span>'+
 	                '</span>'+
 	                '</a></td> '+
 	                '</tr>'+
 	                '<tr>'+
 	                '<th>谈话函询处置方式</th>'+ 
 	                   '<td><select  id="'+index+'ThhxCzfs"  data-thhx="thhx" class=" cxls form-control"  >'+
 	                  '<option value="0">暂无</option>'+
 	                    '<option value="2">初核</option>'+
 	                    '<option value="1">再次谈话函询</option>'+
 	                    '<option value="4">了结澄清</option>'+
 	                    '<option value="3">谈话提醒</option>'+
 	                    '<option value="0">责令检查批评教育</option>'+
 	                    '<option value="5">诫勉谈话</option>'+
 	            '</select>'+
 	             '</td>'+
 	             '</tr>'+
 	             '<tr>'+
 	             '<th>处置情况报告</th>'+
 	             '<td colspan="5">'+
 	             '<label id="'+index+'thhx_bg_l" for="'+index+'thhx_bg">文件下载</label>'+
 	             '<input  id="'+index+'thhx_bg" style="visibility:hidden"  class="file" data-title="谈话函询处置情况报告" value="" onclick="download(this);"/>'+
 	             '</td>'+
 		         '</tr>'+
 		          '<tr>'+
 		         '<th>处置方案</th>'+
 		         '<td colspan="5">'+
 		         '<label id="'+index+'thhx_fa_l" for="'+index+'thhx_fa">文件下载</label>'+
 		         '<input  id="'+index+'thhx_fa" style="visibility:hidden"  class="file" data-title="谈话函询处置方案" value="" onclick="download(this);"/>'+
 		         '</td>'+
 			      '</tr></table>';
 			     
 		      return html;
 			   
 			   
 			   
 		   }
      
	</script>
	
<script type="text/javascript" src="${staticPath }/static/nation.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/xueli.js" charset="utf-8"></script>
</body>
</html>