<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/commons/bootstrap.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">



<title>重复件处理</title>
<style>

</style>


</head>
<body>
                                
                                <button id="next" type="button" class="btn btn-default"onclick="add('DELETE');">取消添加</button>
						        <button id="save1" type="button" class="btn btn-default"onclick="add('DULI');">独立添加</button>
						        <h2>当前案件：</h2>
						        <ul class="nav nav-tabs" id="myTab">
								              <li><a href="#fyrx" data-toggle="tab" >反映人情况</a></li>
								              <li><a href="#profilex" data-toggle="tab">线索情况</a></li>
								              <li><a href="#bfrx" data-toggle="tab">被反映人情况</a></li>
							                  </ul>
						          <div class="tab-content">
							                 <!-- 
											 
											  反应人信息
											 
											  -->										
								    		 <div class="tab-pane " id="fyrx" >
								             <%@ include file="reflectingDetailn.jsp" %> 
								 		     </div>	 
								    		 <!-- 
								    		 
								    		 线索情况
								    		 
								    		  -->
								             <div class="tab-pane " id="profilex" >
								               <%@ include file="cauesDetailn.jsp" %> 
								 		     </div>
						                     <div class="tab-pane active" id="bfrx" >
								               <%@ include file="reflectedDetail.jsp" %> 
								 		     </div>
						        
						         </div>
						        
						        
						        
						        
						        
						        
						        
						        
						        
						        
                                <h2>重名案件：</h2>
                                <c:forEach var="act_zero" items="${repeater_clues}" varStatus="idxStatus">
                                              <input type="hidden" value="${act_zero.id}"/>
									          <ul class="nav nav-tabs" id="myTab">
								              <li><a href="#fyr${idxStatus.index }" data-toggle="tab" >反映人情况</a></li>
								              <li><a href="#profile${idxStatus.index }" data-toggle="tab">线索情况</a></li>
								              <li><a href="#bfr${idxStatus.index }" data-toggle="tab">被反映人情况</a></li>
								              <li><button id="save1" type="button" class="btn btn-default"onclick="add('SAVE','${act_zero.id}');">案件添加到此处</button></li>
							                  </ul>
							           <div class="tab-content">
							             <div class="tab-pane " id="fyr${idxStatus.index }" >
								           <table class="table  table-bordered table-hover">
                         				   <tr>
											<th>姓名</th>
											<td>
												<input type="text" style="width:200px;" class="form-control"
													name="reflectingPerson.reflectingName" placeholder="请输入名称"
													style="width: 220px; height: 34px;" value="${act_zero.reflectingPerson.reflectingName}" disabled>
											</td>
											<th>单位</th>
											<td>
												<input type="text" class="form-control"
													name="reflectingPerson.reflectingDept" placeholder="请输入单位"
													style="width: 220px; height: 34px;" value="${act_zero.reflectingPerson.reflectingDept}" disabled>
											</td>
											<th>联系方式</th>
											<td>
												<input type="text" class="form-control"
													name="reflectingPerson.tell" placeholder="请输入联系方式"
													style="width: 220px; height: 34px;"  value="${act_zero.reflectingPerson.tell}" disabled>
											</td>
											</tr>
										</table>
								 	</div>	 
								 	
								 	
								 	
								 	
								 		      <div class="tab-pane " id="profile${idxStatus.index }" >
                                        <table class="table  table-bordered table-hover">
                                            <tr>
                                            <th>收件日期</th>
                                            <td>
                                              <a class='input-group date' id='c1'>
                                                 <input name="receiveDate" type='text' class="form-control" id='receiveDate' style=" width: 170px; height: 34px;" value="${act_zero.receiveDate }" disabled/>
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
						                          <c:if test="${act.value ==act_zero.clues }">
						                                 <option selected="selected" value="${act.value }">${act.name}</option>
						                          </c:if>
						                           <c:if test="${act.value !=act_zero.clues }">
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
								                  <c:if test="${act.value ==act_zero.problemLand}">
						                                 <option selected="selected" value="${act.value }">${act.name}</option>
						                          </c:if>
						                           <c:if test="${act.value != act_zero.problemLand }">
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
								                 <c:if test="${act.value ==act_zero.special}">
						                                 <option selected="selected" value="${act.value }">${act.name}</option>
						                          </c:if>
						                           <c:if test="${act.value != act_zero.special }">
								                             <option value="${act.value }">${act.name}</option>
								                   </c:if>
		                                          </c:forEach>
                                            </select>
                                            </td>
                                             <th>是否单位或事件事故</th>
                                            <td>
                                            <select id="belongToId" style=" width: 220px; height: 34px;"name="belongToId"  class="form-control"  disabled>
                                             <option value="0" >请选择</option>
                                             <c:if test="${'1' ==act_zero.belongToId}">
								              <option selected="selected" value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
								            <c:if test="${'2' ==act_zero.belongToId}"> 
								            <option  value="1">是</option>  
								            <option selected="selected"  value="2">否</option>
								            </c:if>
								            <c:if test="${'2' !=act_zero.belongToId && '1' !=act_zero.belongToId}"> 
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
                                             <c:if test="${'1' ==act_zero.isImport}">
								              <option selected="selected" value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
								            <c:if test="${'2' ==act_zero.isImport}"> 
								            <option  value="1">是</option>  
								            <option selected="selected"  value="2">否</option>
								            </c:if>
								            <c:if test="${'2' !=act_zero.isImport && '1' !=act_zero.isImport}"> 
								            <option  value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
                                            </select>
                                            </td>
                                            <th>是否要结果</th>
                                              <td>
                                              	<select id="isResult" name="isResult" style=" width: 220px; height: 34px;" class="form-control"   onChange="return isResult2()" disabled>
                                              <option value="0" >请选择</option>
                                              <c:if test="${'1' ==act_zero.isResult}">
								              <option selected="selected" value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
								            <c:if test="${'2' ==act_zero.isResult}"> 
								            <option  value="1">是</option>  
								            <option selected="selected"  value="2">否</option>
								            </c:if>
								            <c:if test="${'2' !=act_zero.isResult && '1' !=act_zero.isResult}"> 
								            <option  value="1">是</option>  
								            <option   value="2">否</option>
								            </c:if>
                                            </select>
                                            </td>
                                            
                                            <th>办理期限</th>
                                             <td> <a class='input-group date' id='c32'>
                                                 <input type='text' id="ress" class="form-control" name='resultTime' style="width: 170px; height: 34px;" value="${act_zero.reflectedPerson.worktime}" disabled/>
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
		                                            <c:if test="${'1' ==act_zero.type}">
										            <option selected="selected" value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
										            <c:if test="${'2' ==act_zero.type}"> 
										            <option value="1">了解关注类</option>  
										            <option selected="selected" value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
										            <c:if test="${'3' ==act_zero.type}"> 
										            <option value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option selected="selected" value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
										            <c:if test="${'4' ==act_zero.type}"> 
										            <option value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option selected="selected" value="4">其他</option>
										            </c:if>
										            <c:if test="${'2' !=act_zero.type && '1' !=act_zero.type && '3' !=act_zero.type && '4' !=act_zero.type}"> 
										            <option value="1">了解关注类</option>  
										            <option value="2">进一步了解情况</option>
										            <option value="3">参考</option>
										            <option value="4">其他</option>
										            </c:if>
                                            </select>
                                           </td> 
                                                <c:if test="${act_zero.lwbh!=null}">
                                                 <th>来文编号</th>
                                                 <td>
                                                   
                                                    <input type="text" class="form-control"  placeholder="请输入名称" style="width: 220px; height: 34px;" value="${act_zero.lwbh}" disabled>   
                                                   
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
                                            <textarea name="problemDescribe" class="form-control" rows="3" disabled>${act_zero.problemDescribe}</textarea>
                                            </td>
                                            
                                            </tr>
                                            <tr>
                                            <th>备注</th>
                                            <td colspan="5">
                                            <textarea name="remarks" class="form-control" rows="3" disabled>${act_zero.remarks}</textarea>
                                            </td>
                                          
                                            </tr>
                                            </table>
								 		     </div>
			 		<div class="tab-pane active" id="bfr${idxStatus.index }" >
			 		
                     <table class="table  table-bordered table-hover">
                                          
                       <tr>
						<th>姓名</th>
						 <td><input type="text" class="form-control" id="reflectedName"
								name="reflectedPerson.reflectedName" placeholder="请输入名称"
								value="${act_zero.reflectedPerson.reflectedName}"  onblur="check('reflectedName','noEmpty')"/><span id="reflectedNamespan" style="color:red"></span>
						</td>
						<th>性别</th>
						<td><select id="sex" name="reflectedPerson.sex" class="form-control">
								<option value="0">请选择</option>
								<c:if test="${'1' ==act_zero.reflectedPerson.sex}">
									<option selected="selected" value="1">男</option>
									<option value="2">女</option>
								</c:if>
								<c:if test="${'2' ==act_zero.reflectedPerson.sex}">
									<option value="1">男</option>
									<option selected="selected" value="2">女</option>
								</c:if>
								<c:if
									test="${'2' !=act_zero.reflectedPerson.sex && '1' !=act_zero.reflectedPerson.sex}">
									<option value="1">男</option>
									<option value="2">女</option>
								</c:if>
							</select>
						</td>
                       
						<th>民族</th>
						<td><input id="mzData" type="hidden"
								value="${act_zero.reflectedPerson.nation }" /> <select
								id="mz" class="form-control" name="reflectedPerson.nation">


							</select>
						</td>
					</tr>
					<tr>
						 <th>证件类型</th>
						<td><select id="documentType" class="form-control" name="reflectedPerson.documentType">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${documentType}">
									<c:if test="${act.value ==act_zero.reflectedPerson.documentType}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.documentType }">
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
								value="${act_zero.reflectedPerson.identificationNumber}"/>
						</td>
						<th>出生年月</th>
						<td><a class='input-group date' id='c2'> <input
								name="reflectedPerson.birthday" type='text' class="form-control"
								id='birthday' value="${act_zero.reflectedPerson.birthday}" />
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
								value="${act_zero.reflectedPerson.xl }" /> <select id="xl"
								class="form-control" name="reflectedPerson.xl">


							</select>
						</td>
						<th>工作单位</th>
						<td><input type="text" class="form-control"
								name="reflectedPerson.workPosition" placeholder="请输入名称"
								value="${act_zero.reflectedPerson.workPosition}">
						</td>
						<th>职务</th>
						<td><input type="text" class="form-control" placeholder="请输入名称"
								name="reflectedPerson.duty"
								value="${act_zero.reflectedPerson.duty}">
						</td>
					</tr>

					<tr>
						

						<th>职级</th>
						<td>
							<select id="rank" class="form-control" name="reflectedPerson.rank" onChange="check('rank','choice');">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${rank}">
									<c:if test="${act.name ==act_zero.reflectedPerson.rank}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.rank }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select><span id="rankspan" style="color:red"></span>
						</td>
						<th>任现职时间</th>
						<td><a class='input-group date' id='c3'> <input type='text'
								id="worktime" class="form-control"
								name='reflectedPerson.worktime'
								value="${act_zero.reflectedPerson.worktime}" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</td>
						<th>是否中共党员</th>
						<td><select id="partyMember" name="reflectedPerson.partyMember" class="form-control">
								<option value="0">请选择</option>
								<c:if test="${'1' ==act_zero.reflectedPerson.partyMember}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==act_zero.reflectedPerson.partyMember}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=act_zero.reflectedPerson.partyMember && '1' !=act_zero.reflectedPerson.partyMember}">
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
										test="${act.value ==act_zero.reflectedPerson.political}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.political }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>入党时间</th>
						<td><a class='input-group date' id='c4'> <input id="intime"
								type='text' class="form-control" name='reflectedPerson.intime'
								value="${act_zero.reflectedPerson.intime }" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</td>
						<th>是否非党员非监察对象</th>
						<td style="width: 250px; height: 34px;" >
						<div style="display:inline-block;float:left" ><select id="isPMSupervisoryObject" name="reflectedPerson.isPMSupervisoryObject" style="width: 90px; height: 34px;" class="form-control" onChange="return isP()">
								<option value="0">请选择</option>
								<c:if test="${'1' ==act_zero.reflectedPerson.isPMSupervisoryObject}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==act_zero.reflectedPerson.isPMSupervisoryObject}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=act_zero.reflectedPerson.isPMSupervisoryObject && '1' !=act_zero.reflectedPerson.isPMSupervisoryObject}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select>
							</div>
							<c:forEach var="act" items="${pMSupervisoryObject}">
									<c:if test="${act.value ==act_zero.reflectedPerson.pMSupervisoryObject}">
										<div style="display:inline-block;float:right" title="${act.name}">
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.pMSupervisoryObject }">
										<div style="display:inline-block;float:right">
									</c:if>
								</c:forEach>
							<select class="form-control" id="pMSupervisoryObject"  style="width: 120px; height: 34px;" name="reflectedPerson.pMSupervisoryObject" disabled="disabled">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${pMSupervisoryObject}">
									<c:if
										test="${act.value ==act_zero.reflectedPerson.pMSupervisoryObject}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.pMSupervisoryObject }">
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
										test="${act.value ==act_zero.reflectedPerson.civilServant}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.civilServant }">
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
										test="${act.value ==act_zero.reflectedPerson.departmenType}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.departmenType }">
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
										test="${act.value ==act_zero.reflectedPerson.natureOfenterprise}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value !=act_zero.reflectedPerson.natureOfenterprise }">
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
										test="${act.value ==act_zero.reflectedPerson.classOfenterprise}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.classOfenterprise }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>岗位</th>
						<td><select class="form-control" name="reflectedPerson.post">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${post}">
									<c:if test="${act.value ==act_zero.reflectedPerson.post}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.post }">
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
										test="${act.value ==act_zero.reflectedPerson.posType}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.posType }">
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
										test="${act.value ==act_zero.reflectedPerson.topDiscipline}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.topDiscipline }">
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
									<c:if test="${act.value ==act_zero.reflectedPerson.cadre}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.cadre }">
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
									<c:if test="${act.value ==act_zero.reflectedPerson.partyRepresent}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.partyRepresent }">
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
									<c:if test="${act.value ==act_zero.reflectedPerson.np}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.np }">
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
									<c:if test="${act.value ==act_zero.reflectedPerson.dwMember}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.dwMember }">
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
									<c:if test="${act.value ==act_zero.reflectedPerson.jwMember}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.jwMember }">
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
									<c:if test="${act.value ==act_zero.reflectedPerson.zx}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.zx }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<th>是否违反中央八项规定精神</th>
						<td>
							<select id="eightSpirit" name="reflectedPerson.eightSpirit" class="form-control">
								<option value="0">请选择</option>
								<c:if test="${'1' ==act_zero.reflectedPerson.eightSpirit}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==act_zero.reflectedPerson.eightSpirit}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=act_zero.reflectedPerson.eightSpirit && '1' !=act_zero.reflectedPerson.eightSpirit}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select>
							</td>
						<th>是否国家监察对象</th>
						<td style="width: 250px; height: 34px;" >
						<div style="display:inline-block;float:left" ><select id="iSupervision" name="reflectedPerson.iSupervision" style="width: 90px; height: 34px;" class="form-control" onChange="return isup()">
								<option value="0">请选择</option>
								<c:if test="${'1' ==act_zero.reflectedPerson.iSupervision}">
									<option selected="selected" value="1">是</option>
									<option value="2">否</option>
								</c:if>
								<c:if test="${'2' ==act_zero.reflectedPerson.iSupervision}">
									<option value="1">是</option>
									<option selected="selected" value="2">否</option>
								</c:if>
								<c:if
									test="${'2' !=act_zero.reflectedPerson.iSupervision && '1' !=act_zero.reflectedPerson.iSupervision}">
									<option value="1">是</option>
									<option value="2">否</option>
								</c:if>
							</select>
							</div>
							<c:forEach var="act" items="${supervision}">
									<c:if test="${act.value ==act_zero.reflectedPerson.supervision}">
										<div style="display:inline-block;float:right" title="${act.name}">
									</c:if>
									<c:if test="${act.value != act_zero.reflectedPerson.supervision }">
										<div style="display:inline-block;float:right">
									</c:if>
								</c:forEach>
							<select class="form-control" id="supervision"  style="width: 120px; height: 34px;" name="reflectedPerson.supervision" disabled="disabled">
								<option value="0">请选择</option>
								<c:forEach var="act" items="${supervision}">
									<c:if
										test="${act.value ==act_zero.reflectedPerson.supervision}">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if
										test="${act.value != act_zero.reflectedPerson.supervision }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							           </select>
						            </td>         
                                      </tr> 
                                   </tbody>
                        </table> 
						</div> 
						</div>
					</c:forEach>
					 <script type="text/javascript" src="${staticPath }/static/nation.js" charset="utf-8"></script>
	                 <script type="text/javascript" src="${staticPath }/static/xueli.js"  charset="utf-8"></script>
                    <script type="text/javascript">
                    function add(order,id){
                    	$.ajax({
                    		url:"${path}/ags/add_repeat",
                    		data:{order:order,id:id},
                    		dataType:"json",
                    		type:"post",
                    		success:function(result){
                    			if(result.success){
                    				success(result.msg);
                    				cancel();
                    			}else{
                    				cancel();
                    			}
                    		}
                    		
                    	});
                    	
                    	
                    	
                    	
                    	
                    }
                    
                    function cancel(){
                       console.log("ddddd");
             		   var index_tabs=parent.index_tabs;
             		   var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                        var tab = index_tabs.tabs('getTab', index);
                        if (tab.panel('options').closable) {
                            index_tabs.tabs('close', index);
                        }
             	   }
                    </script>

</body>
</html>