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



<title>线索添加和编辑</title>
<style>
#tongan {
	/* margin-top: 20px; */
	
}

.page-header {
	margin-top: 20px;
}
</style>



</head>

<body>
	<input type="hidden" id="caseIdHidden" value="0" />
	<div id="wrapper">

		<div class="col-lg-12">
			<form id="myForm" class="form-horizontal">
				<div class="form-group">
					    <input type="hidden" id="probleCluesId" name="id" value="${problemClues.id}" /> 
						<input type="hidden" id="caseId2"  name="cluesNum" value="${problemClues.cluesNum}" /> 
						<input type="hidden" name="reflectedPerson.id" value="${problemClues.reflectedPerson.id}" /> 
						<input type="hidden" name="reflectingPerson.id" value="${problemClues.reflectingPerson.id}" />
						<input type="hidden" name="reflectingPerson.id" value="${problemClues.reflectingPerson.id}" />
					<!-- 是否添加的是同案人员 -->
					<input type="hidden" id="whereForm" name="whereFrom" value="putong" />


					<h4 class="page-header">被反映人信息  &nbsp;&nbsp;&nbsp;
					<c:if test="${problemClues.id!=null&&problemClues.id!=''}">
                         (<span >${problemClues.cluesNum })</span>
                    </c:if> 
                    </h4>                   
					<div class="form-group">
						<label class="col-md-1 control-label"> <span style="color: red; font-size: 22px">*</span>姓名
						</label>
						<div class="col-md-2">
							<input type="text" class="form-control" id="reflectedName"
								name="reflectedPerson.reflectedName" placeholder="请输入名称"
								value="${problemClues.reflectedPerson.reflectedName}"  onblur="check('reflectedName','noEmpty')"/><span id="reflectedNamespan" style="color:red"></span>
						</div>
						<label class="col-md-2 control-label">性别</label>
						<div class="col-md-2">
							<select id="sex" name="reflectedPerson.sex" class="form-control">
								<option value="0">请选择</option>
								<c:if test="${'1' ==problemClues.reflectedPerson.sex}">
									<option selected="selected" value="1">男</option>
									<option value="2">女</option>
								</c:if>
								<c:if test="${'2' ==problemClues.reflectedPerson.sex}">
									<option value="1">男</option>
									<option selected="selected" value="2">女</option>
								</c:if>
								<c:if test="${'2' !=problemClues.reflectedPerson.sex && '1' !=problemClues.reflectedPerson.sex}">
									<option value="1">男</option>
									<option value="2">女</option>
								</c:if>
							</select>
						</div>

						<label class="col-md-2 control-label">民族</label>
						<div class="col-md-2">
							<input id="mzData" type="hidden"
								value="${problemClues.reflectedPerson.nation }" /> <select
								id="mz" class="form-control" name="reflectedPerson.nation">


							</select>

						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">证件类型</label>
						<div class="col-md-2">
							<select id="documentType" class="form-control" name="reflectedPerson.documentType">
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
						</div>
						<label class="col-md-2 control-label">证件号码</label>
						<div class="col-md-2">
							<input type="text" class="form-control" id="identificationNumber"
								name="reflectedPerson.identificationNumber" placeholder="请输入证件号码"
								value="${problemClues.reflectedPerson.identificationNumber}"/>
						</div>
						<label class="col-md-2 control-label">出生年月</label>
						<div class="col-md-2">
							<a class='input-group date' id='c2'"> <input
								name="reflectedPerson.birthday" type='text' class="form-control"
								id='birthday' value="${problemClues.reflectedPerson.birthday}" />
								<span class="input-group-addon"
								style="width: 50px; height: 34px;"> <span
									class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</div>
					</div>
					<div class="form-group">

						<label class="col-md-1 control-label">学历</label>
						<div class="col-md-2">
							<input id="xlData" type="hidden"
								value="${problemClues.reflectedPerson.xl }" /> <select id="xl"
								class="form-control" name="reflectedPerson.xl">


							</select>
						</div>
						<label class="col-md-2 control-label">工作单位</label>
						<div class="col-md-2">
							<input type="text" class="form-control"
								name="reflectedPerson.workPosition" placeholder="请输入名称"
								value="${problemClues.reflectedPerson.workPosition}">
						</div>
						<label class="col-md-2 control-label">职务</label>
						<div class="col-md-2">
							<input type="text" class="form-control" placeholder="请输入名称"
								name="reflectedPerson.duty"
								value="${problemClues.reflectedPerson.duty}">
						</div>
					</div>

					<div class="form-group">
						

						<label class="col-md-1 control-label"> <span
							style="color: red; font-size: 22px">*</span>职级
						</label>
						<div class="col-md-2">
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
						</div>
						<label class="col-md-2 control-label">任现职时间</label>
						<div class="col-md-2">
							<a class='input-group date' id='c3'"> <input type='text'
								id="worktime" class="form-control"
								name='reflectedPerson.worktime'
								value="${problemClues.reflectedPerson.worktime}" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</div>
						<label class="col-md-2 control-label">是否中共党员</label>
						<div class="col-md-2">
							<select id="partyMember" name="reflectedPerson.partyMember" class="form-control">
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
						</div>
					
					</div>
					<div class="form-group">

						<label class="col-md-1 control-label">政治面貌</label>
						<div class="col-md-2">
							<select class="form-control" name="reflectedPerson.political">
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
						</div>
						<label class="col-md-2 control-label">入党时间</label>
						<div class="col-md-2">
							<a class='input-group date' id='c4'"> <input id="intime"
								type='text' class="form-control" name='reflectedPerson.intime'
								value="${problemClues.reflectedPerson.intime }" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</div>
						<label class="col-md-2 control-label">是否非党员非监察对象</label>
						<div class="col-md-1">
							<select id="isPMSupervisoryObject" name="reflectedPerson.isPMSupervisoryObject" style="width: 90px; height: 34px;" class="form-control" onChange="return isP()">
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
							<div class="col-md-2">
							<select class="form-control" id="pMSupervisoryObject"  style="width: 150px; height: 34px;" name="reflectedPerson.pMSupervisoryObject" disabled="disabled">
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
					</div>
					<div class="form-group">						
						<label class="col-md-1 control-label">身份</label>
						<div class="col-md-2">
							<select class="form-control" name="reflectedPerson.civilServant">
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
						</div>
						<label class="col-md-2 control-label">部门分类</label>
						<div class="col-md-2">
							<select class="form-control" name="reflectedPerson.departmenType">
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
						</div>
						<label class="col-md-2 control-label">企业性质</label>
						<div class="col-md-2">
							<select class="form-control"
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
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">企业级别</label>
						<div class="col-md-2">
							<select class="form-control"
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
						</div>
						<label class="col-md-2 control-label">岗位</label>
						<div class="col-md-2">
							<select class="form-control" name="reflectedPerson.post">
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
						</div>
						<label class="col-md-2 control-label ">企业人员类别</label>
						<div class="col-md-2">
							<select class="form-control" name="reflectedPerson.posType">
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
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">一把手违纪违法</label>
						<div class="col-md-2">
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
						</div>
						<label class="col-md-2 control-label">干部管理权限</label>
						<div class="col-md-2">
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
						</div>
						<label class="col-md-2 control-label">中共代表</label>
						<div class="col-md-2">
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
						</div>
					</div>
					<div class="form-group">
						
						<label class="col-md-1 control-label">人大代表</label>
						<div class="col-md-2">
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
						</div>
						<label class="col-md-2 control-label">党委委员</label>
						<div class="col-md-2">
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
						</div>
						<label class="col-md-2 control-label">纪委委员</label>
						<div class="col-md-2">
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
						</div>
						
					</div>
					<div  class="form-group">
						<label class="col-md-1 control-label">政协委员</label>
						<div class="col-md-2">
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
						</div>
						<label class="col-md-2 control-label">是否违反中央八项规定精神</label>
						<div class="col-md-2">
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
							</div>
						<label class="col-md-2 control-label">是否国家监察对象</label>
						<div class="col-md-1">
							<select id="iSupervision" name="reflectedPerson.iSupervision" style="width: 90px; height: 34px;" class="form-control" onChange="return isup()">
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
							<div class="col-md-2">
							<select class="form-control" id="supervision"  style="width: 150px; height: 34px;" name="reflectedPerson.supervision" disabled="disabled">
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
					</div>


					<h4 class="page-header">线索信息</h4>

					<div class="form-group">
						<label class="col-md-1 control-label"> <span
							style="color: red; font-size: 22px">*</span>线索来源
						</label>
						<div class="col-md-2">
							<select id="clues" name="clues" class="form-control"
								onChange="return cluesType(),check('clues','choice')" >
								<option value="0">请选择</option>
								<c:forEach var="act" items="${clues}">
									<c:if test="${act.value ==problemClues.clues }">
										<option selected="selected" value="${act.value }">${act.name}</option>
									</c:if>
									<c:if test="${act.value !=problemClues.clues }">
										<option value="${act.value }">${act.name}</option>
									</c:if>
								</c:forEach>
							</select><span id="cluesspan" style="color:red;"></span>
						</div>	
						<%--  <label class="col-md-1 control-label">案件编号</label>	
					    <div class="col-md-2">
					         <c:if test='${problemClues.receiveDate!=null}'>
							   <input id="caseId" type="text" class="form-control" placeholder="请输入名称" value="${fn:replace(problemClues.receiveDate,'-','')}${fn:substring(problemClues.cluesNum,5,10)}" disabled>
							 </c:if>
							  <c:if test='${problemClues.receiveDate==null}'>
							   <input id="caseId" type="text" class="form-control" placeholder="请输入名称" value="${fn:substring(fn:replace(problemClues.createTime,'-',''),0,8)}${fn:substring(problemClues.cluesNum,5,10)}" disabled>
							  </c:if>
						</div>  --%>



						<label class="col-md-2 control-label"> <span
							style="color: red; font-size: 22px">*</span>收件日期
						</label>
						<div class="col-md-2">
							<a class='input-group date' id='c1'> <input 
								name="receiveDate" type='text' class="form-control"
								id='receiveDate' style="height: 34px;"
								value="${problemClues.receiveDate }" onChange="check('receiveDate','noEmpty,date');" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;" >
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a><span id="receiveDatespan" style="color:red"></span>
						</div>
						<%-- <label class="col-md-2 control-label">受理日期
						</label>
						<div class="col-md-2">
							<a class='input-group date'> <input
								name="progress.time" type='text' class="form-control"
								id='proTime' style="height: 34px;"
								value="${agsSLTime}" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</div>	 --%>				
					</div>
					<div class="form-group">
					<label class="col-md-1 control-label">问题属地</label>
						<div class="col-md-2">
							<select id="problemLand" name="problemLand" class="form-control">
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
						</div>
					<label class="col-md-2 control-label">所属领域</label>
						<div class="col-md-2">
							<select id="fields" name="fields"
								class="selectpicker form-control" multiple data-s="multiple">

								<c:forEach var="act" items="${fields}">
									<option value="${act.value }">${act.name}</option>
								</c:forEach>
							</select>
						</div>
						<label class="col-md-2 control-label">专项行动</label>
						<div class="col-md-2">
							<select id="special" name="special" class="form-control">
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
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">是否单位或事件事故</label>
						<div class="col-md-2">
							<select id="belongToId" name="belongToId" class="form-control">
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
							</select>
						</div>
					<label class="col-md-2 control-label">是否重要问题</label>
						<div class="col-md-2">
							<select id="isImport" name="isImport" class="form-control">
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
							</select>
						</div>
					<label class="col-md-2 control-label">是否要结果</label>
						<div class="col-md-2">
							<select id="isResult" name="isResult" class="form-control"
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
							</select>
						</div>

					</div>
					

					<div class="form-group">

						<label class="col-md-1 control-label" id="res1"
							style="display: none">办理期限</label>
						<div class="col-md-2" id="res2" style="display: none">
							<a class='input-group date' id='c32'"> <input type='text'
								id="resultTime" class="form-control" name='resultTime'
								value="${problemClues.resultTime}" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</div>

						<label class="col-md-2 control-label" id="res3"
							style="display: none">类型</label>
						<div class="col-md-2" id="res4" style="display: none">
							<select id="type" name="type" class="form-control">
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
							</select>
						</div>
						<label class="col-md-2 control-label" id="res5"
							style="display: none">监督检查活动时间</label>
						<div class="col-md-2" id="res6" style="display: none">
						<a class='input-group date' "> <input type='text'
								id="superviseTestTime" class="form-control" name='resultTime'
								value="${problemClues.superviseTestTime}" /> <span
								class="input-group-addon" style="width: 50px; height: 34px;">
									<span class="glyphicon glyphicon-calendar"></span>
							</span>
							</a>
						</div>
					</div>


					<div class="form-group">

						<label class="col-md-1 control-label">问题描述</label>
						<div class="col-md-9">
							<textarea name="problemDescribe" class="form-control" rows="3">${problemClues.problemDescribe}</textarea>
						</div>

					</div>
					<div class="form-group">
						<label class="col-md-1 control-label">备注</label>
						<div class="col-md-9">
							<textarea name="remarks" class="form-control" rows="3">${problemClues.remarks}</textarea>
						</div>
					</div>
					<h4 class="page-header">反映人信息</h4>
					<div class="form-group">
						<label class="col-md-1 control-label">反映人姓名</label>
						<div class="col-md-2">
							<input type="text" class="form-control"
								name="reflectingPerson.reflectingName" placeholder="请输入名称"
								value="${problemClues.reflectingPerson.reflectingName}">
						</div>
						<label class="col-md-2 control-label">反映人单位</label>
						<div class="col-md-2">
							<input type="text" class="form-control"
								name="reflectingPerson.reflectingDept" placeholder="请输入单位"
								value="${problemClues.reflectingPerson.reflectingDept}">
						</div>
						<label class="col-md-2 control-label">联系方式</label>
						<div class="col-md-2">
							<input type="text" class="form-control"
								name="reflectingPerson.tell" placeholder="请输入联系方式"
								value="${problemClues.reflectingPerson.tell}">
						</div>
					</div>
				</div>
			</form>

		</div>
	</div>
	<script type="text/javascript">
		var tz="案件办理("+$("#reflectedName").val()+")";
      //
        $(".selectpicker").selectpicker({  
            noneSelectedText : '请选择'//默认显示内容  
        });  
           $(".selectpicker").selectpicker({  
           		noneSelectedText : '请选择'//默认显示内容  
	       }); 
           
           //
           
		   $("a.input-group.date").each(function(){
			    $(this).datetimepicker({
	                language: 'zh-CN',//显示中文
	                format: 'yyyy-mm-dd',//显示格式
	                minView: "month",//设置只显示到月份
	                initialDate: new Date(),
	                autoclose: true,//选中自动关闭
	                todayBtn: true,//显示今日按钮
	                locale: moment.locale('zh-cn')
	            }); 
		   });
            var caseId = document.getElementById("caseId");
  			var idHidden = document.getElementById('caseIdHidden').value;
            if($("#probleCluesId").val()==""||$("#probleCluesId").val()==null||$("#probleCluesId").val()==0){
            	$("#tongan").hide();
            	//produceId();
            }else{
         	    var str='${problemClues.fields}';
         	    var arr=str.split(',');
         	    $('#fields').selectpicker('val', arr);
            }
           
            //增加同案人员
            function addTonan(){
            	//获取存储的案件编号和线索id
            	 var cluesNum=$("#caseId2").val();
            	 var cid=$("#probleCluesId").val();
            	 parent.addTab("添加同案人员"+cluesNum,"${path}/probleClues/tongan?id="+cid);
            	 /* //清空表单
            	 //ClearForm("myForm");
            	 $("#caseId").val(cluesNum);
            	 $("#title").text("增加同案人员");
            	 //删除存储的id
            	 $("#probleCluesId").val("");
            	 $("#whereFrom").val("tongan_"+cid); */
            }
            
           
			
            
           //表单提交
           function save(){  
        	 var proTime = $("#proTime").val();
        	 var r=validator("reflectedName",["noEmpty"]);
        	 var r2=validator("receiveDate",["date","noEmpty"]);
        	 var r3=validator("rank",["noEmpty"]);
        	 var r4=validator('clues',['noEmpty']);
        	 if(r&&r2&&r3&&r4){
        	   var data= $('#myForm').serialize();
        	   var submitData=decodeURIComponent(data,true);
        	   console.log("submitDataLLLLLL："+submitData);
        	 //submitData是解码后的表单数据，结果同上
        	 var url="";
             if($("#probleCluesId").val()!=""){
            	 url='${path}/probleClues/edit';
             }else{
            	 url='${path}/probleClues/add?proTime='+proTime;
             }
        	 $.ajax({
        	     url:url,
        	     data:submitData,
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
        	        	 console.log(result.obj);
        	        	    if(result.obj!=null&&result.obj!=""){
        	        	    	tz="添加新线索";
        	        	    	$("#probleCluesId").val(result.obj);
        	        	    	success("添加成功");
        	        	    	//刷新datagrid
        	        	    	//parent.version2Name.datagrid('reload');
        	        	    	//parent.$("#dataGrid").datagrid('reload');  
        	        	    	 //window.parent.reloadDatagrid2();
        	        	    	 //window.parent.reloadDatagrid("新案件",window.top.reload_taskTab);

        	        	    	//parent.refreshTabData( "新案件" );
        	        	    }else{
        	        	    	 success(result.msg);
        	        	    }
        					$("#next").show();
        					$("#save1").hide();
        				    //ClearForm("myForm");
        	         }else{
        	        	field(result.msg);
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
           //关闭当前界面
           function cancel(){
    		   var index_tabs=parent.index_tabs;
    		   var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
               var tab = index_tabs.tabs('getTab', index);
               if (tab.panel('options').closable) {
                   index_tabs.tabs('close', index);
               }
    	   }           
           //线索分办页
           function fBNext(){ 
        	   var id = $("#probleCluesId").val();
        	   parent.updateTab(tz,"线索分办("+$("#reflectedName").val()+")","${path}/probleClues/fbClues?id="+id);       	  
        	
           }
	       //是否要结果加期限
           function isResult2(){
        	   var zm = $('#isResult').selectpicker('val');
      			if(zm=="1"){
              		//显示
              		document.getElementById("res1").style.display="block";
              		document.getElementById("res2").style.display="block";
              		
      			}else{
      				document.getElementById("res1").style.display="none";
      				document.getElementById("res2").style.display="none";
      			}
      			yc();
      		}
	       
	        //来源类型
           function cluesType(){
      			var clues = $('#clues').selectpicker('val');
      			
      			if(clues=="15"||clues=="16"||clues=="23"){
      				document.getElementById("res5").style.display="none";
      				document.getElementById("res6").style.display="none";   
      				document.getElementById("res3").style.display="block";
              		document.getElementById("res4").style.display="block";              		
              		yc();
              		
      			}else if(clues=="24"){
      				document.getElementById("res3").style.display="none";
      				document.getElementById("res4").style.display="none";
              		document.getElementById("res5").style.display="block";
              		document.getElementById("res6").style.display="block";
              		yc();
              	
      			}else{
      				document.getElementById("res3").style.display="none";
      				document.getElementById("res4").style.display="none";
      				document.getElementById("res5").style.display="none";
      				document.getElementById("res6").style.display="none";
      			}
      		}
	        function yc(){
	        	var n = $('#res1').css("display");
  				var m = $('#res3').css("display");
  				if(n=='none'&&m=='none'){
  					document.getElementById("res5").setAttribute("class","col-md-1 control-label"); 
  				}else if(n=='none'){
  					document.getElementById("res3").setAttribute("class","col-md-1 control-label");   
  					document.getElementById("res5").setAttribute("class","col-md-2 control-label");  
  				}else{
  					document.getElementById("res3").setAttribute("class","col-md-2 control-label");  
  					document.getElementById("res5").setAttribute("class","col-md-2 control-label");  
  				}
  			//document.getElementById("tr").setAttribute("class","styleclass");  
	        }
           $(function(){
        	   var proTime = $("#proTime").val();
        	   if(proTime!=null&&proTime!=""){
        		   $("#proTime").attr("disabled",true);
        	   }
        	   var resultTime = '${problemClues.resultTime}';
     			if(resultTime!=""&&resultTime!=null){      			
     				document.getElementById("res1").style.display="block";
             		document.getElementById("res2").style.display="block";
     			}
        	   var type = '${problemClues.type}';
     			if(type!=""&&type!=null){
     				var n = $('#res1').css("display");
     				if(n=='none'){
     					document.getElementById("res3").setAttribute("class","col-md-1 control-label");  
     				}else{
     					document.getElementById("res3").setAttribute("class","col-md-2 control-label");  
     				} 
     				document.getElementById("res3").style.display="block";
             		document.getElementById("res4").style.display="block";
     			}
     			
     			 //给民族和学历选中选项
         	   $("#xl").val($("#xlData").val());  
         	   $("#mz").val($("#mzData").val());  
               
           });
           function check(id,valitors){
        	   var vs= valitors.split(",");
        	   validator(id,vs);
           }
      
 </script>
	<script type="text/javascript" src="${staticPath }/static/nation.js"
		charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/xueli.js"
		charset="utf-8"></script>

</body>

</html>
