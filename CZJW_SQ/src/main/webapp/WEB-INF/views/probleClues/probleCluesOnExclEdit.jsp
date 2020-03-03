<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<script type="text/javascript">
    //导出时选中的条目
    var ids='${ids}';
    var sqlStore = '${sqlStore}';
	//验证多选框勾选
 	function onExcl(){
 		var obj = $('#probleCluesOnexclEditFrom').serializeObject();
		var str = new Array();  
		str = obj.fxk;
		console.log(obj);
		if(typeof(str)=="undefined"){
			$.messager.show({
				title:'消息提示',
				msg:'最少选择一项要到导出的信息',
				timeout:3000,
				showType:'slide'
			});
		} else if(ids=='-1'){
			var url = '${path }/probleClues/OnImportZip?columns='+str;
			if ($('#probleCluesOnexclEditFrom').form('validate')) {
				$('#probleCluesOnexclEditFrom').form(
						'submit',
						{
							url : url,
							onSubmit : function(param) {
								if (ids != null && ids != ''
										&& ids != 'undefined' && ids != '-1')
									param.choseIds = ids;
								    param.sqlStore=sqlStore;
							}
						});
			}
		}else {
			var url = '${path }/ags/OnImportExcel?columns=' + str;
			if ($('#probleCluesOnexclEditFrom').form('validate')) {
				$('#probleCluesOnexclEditFrom').form(
						'submit',
						{
							url : url,
							onSubmit : function(param) {
								if (ids != null && ids != ''
										&& ids != 'undefined' && ids != '-1')
									param.choseIds = ids;
								    param.sqlStore=sqlStore;
							}
						});
			}
		}
	}
	
   
</script>
<div  class="easyui-layout" data-options="fit:true,border:false" style="overflow-y:scroll">
		<form id="probleCluesOnexclEditFrom" method="post" style="width:'100%';height: '100%'">
				<table class="formTable">
				     <tr>
					  <td colspan="2">线索信息</td>
					</tr>
					<tr>
	    				<th><input  type="checkbox" id="receiveDate_fxk" name="fxk" value="receiveDate" style="margin-right: 5px;"/>受理时间：</th>
						<th><input  type="checkbox" id="receiveDate_fxk" name="fxk" value="receiveDate" style="margin-right: 5px;"/>收件日期：</th>
	    			</tr>
					<tr>
	    				<th><input  type="checkbox" id="clues_fxk" name="fxk" value="clues" style="margin-right: 5px;"/>线索来源：</th>
		    			<th><input  type="checkbox" id="problemLand_fxk" name="fxk" value="problemLand" style="margin-right: 5px;"/>问题属地：</th>
		    		</tr>
					<tr>
		    			<th><input  type="checkbox" id="problemDescribe_fxk" name="fxk" value="problemDescribe" style="margin-right: 5px;"/>问题描述：</th>		    		
					<th><input type="checkbox" id="special_fxk" name="fxk" value="special" style="margin-right: 5px;"/>专项行动：</th>
					</tr>
		    		<tr>
					<!-- 新增所属领域 -->
					 <th><input type="checkbox" id="fields_fields" name="fxk" value="fields" style="margin-right: 5px;"/>所属领域：</th>
	    		     
	    			   <th><input type="checkbox" id="remarks_fxk" name="fxk" value="remarks" style="margin-right: 5px;"/>备注：</th>
	    		   </tr>
	    		   
	    		   <tr>
					<!-- 线索状态 -->
					   <th><input type="checkbox" id="orgaName_fxk" name="fxk" value="orgaName" style="margin-right: 5px;"/>承办单位：</th>
	    			   <th><input type="checkbox" id="progress_fxk" name="fxk" value="progress" style="margin-right: 5px;"/>办理进度：</th>
	    		   </tr>
	    		   <tr>
					<!-- 线索状态 -->
					   <th><input type="checkbox" id="cluesNum_fxk" name="fxk" value="cluesNum" style="margin-right: 5px;"/>案件编号：</th>
	    		   </tr>
	    		   
					<tr>
					  <td colspan="2">被反映人信息</td>
					</tr>
					<tr>
		    			<th><input  type="checkbox" id="beReflectName_fxk" name="fxk" value="reflectedName" style="margin-right: 5px;"/>被反映人姓名：</th>
		    			<th><input  type="checkbox" id="beReflectRank_fxk" name="fxk" value="rank" style="margin-right: 5px;"/>被反映人职级：</th>
		    		</tr>
		    		<tr>
		    		     <th><input  type="checkbox" id="birthday_fxk" name="fxk" value="birthday" style="margin-right: 5px;"/>出生年月：</th>
		    		     <th><input  type="checkbox" id="partyMember_fxk" name="fxk" value="partyMember" style="margin-right: 5px;"/>是否中共党员：</th>
		    		</tr>
		    		<tr>
		    		     <th><input  type="checkbox" id="intime_fxk" name="fxk" value="intime" style="margin-right: 5px;"/>入党时间：</th>
		    		     <th><input  type="checkbox" id="iSupervision_fxk" name="fxk" value="iSupervision" style="margin-right: 5px;"/>是否国家监察对象：</th>
		    		
		    		</tr>
		    		<tr>
		    		     <th><input  type="checkbox" id="isPMSupervisoryObject_fxk" name="fxk" value="isPMSupervisoryObject" style="margin-right: 5px;"/>是否非党员非检查对象：</th>
		    		     <!-- <th><input  type="checkbox" id="iSupervision_fxk" name="fxk" value="iSupervision" style="margin-right: 5px;"/>是否国家监察对象：</th> -->
		    		
		    		     <th><input  type="checkbox" id="identificationNumber_fxk" name="fxk" value="identificationNumber" style="margin-right: 5px;"/>证件号码：</th>		    		
		    		</tr>
		    		<tr>
		    		     <th><input  type="checkbox" id="nation_fxk" name="fxk" value="nation" style="margin-right: 5px;"/>民族：</th>
		    			<th><input  type="checkbox" id="beReflectDuty_fxk" name="fxk" value="duty" style="margin-right: 5px;"/>被反映人职务：</th>
		    		</tr>
	    			<tr>
		    			<th><input  type="checkbox" id="workPosition_fxk" name="fxk" value="workPosition" style="margin-right: 5px;"/>工作单位：</th>
		    		    <th><input  type="checkbox" id="np_fxk" name="fxk" value="np" style="margin-right: 5px;"/>是否人大代表：</th>
		    		</tr>
		    		<tr>
		    			<th><input  type="checkbox" id="zx_fxk" name="fxk" value="zx" style="margin-right: 5px;"/>是否政协委员：</th>
		    		</tr>
		    		<tr>
					  <td colspan="2">反映人信息</td>
					</tr>
					<tr>
					    <th><input  type="checkbox" id="beflectName_fxk" name="fxk" value="reflectingName" style="margin-right: 5px;"/>反映人姓名：</th>
	    				<th><input  type="checkbox" id="beflectDept_fxk" name="fxk" value="reflectingDept" style="margin-right: 5px;"/>反映人单位：</th>		    		
					</tr>
	    			<tr>	
		    			<th><input  type="checkbox" id="beflectContact_fxk" name="fxk" value="tell" style="margin-right: 5px;"/>反映人电话：</th>
	    			</tr>
	    			<tr>
					  <td colspan="2">处置信息</td>
					</tr>
		    		
		    		
		    		<!-- <tr>
		    			<th><input  type="checkbox" id="blResult_djcf_fxk" name="fxk" value="blResult_djcf" style="margin-right: 5px;"/>党纪处分：</th>
		    			
		    			<th><input type="checkbox" id="blResult_zjcf_fxk" name="fxk" value="blResult_zjcf" style="margin-right: 5px;"/>政纪处分：</th>
		    			
		    		</tr> -->
		    		<!-- <tr>
	    			<th><input type="checkbox" id="blResult_zzcl_fxk" name="fxk" value="blResult_zzcl" style="margin-right: 5px;"/>组织处理：</th>
	    			
	    			<th><input type="checkbox" id="blResult_yjsf_fxk" name="fxk" value="blResult_yjsf" style="margin-right: 5px;"/>移送司法：</th>
	    		</tr> -->
	    		<!-- <tr>
	    			<th><input type="checkbox" id="zyViolation_fxk" name="fxk" value="zyViolation" style="margin-right: 5px;"/>主要违纪性质：</th>
	    			
						<th><input type="checkbox" id="cfTime_fxk" name="fxk" value="cfTime" style="margin-right: 5px;"/>处分时间：</th>
	    		</tr> -->
	    		<tr>
	    		        <th><input type="checkbox" id="toSaveMoney_fxk" name="fxk" value="toSaveMoney" style="margin-right: 5px;"/>挽回经济损失：</th>
						<th><input type="checkbox" id="collectionMoney_fxk" name="fxk" value="collectionMoney" style="margin-right: 5px;"/>收缴违纪资金：</th>
	    		</tr>
	    		<tr>
	    		        <th><input type="checkbox" id="zyName_fxk" name="fxk" value="zyName" style="margin-right: 5px;"/>违纪行为：</th>
						<th><input type="checkbox" id="legalact_zw_fxk" name="fxk" value="legalact_zw" style="margin-right: 5px;"/>职务违法犯罪行为：</th>
	    		</tr>
	    		<tr>
	    		        <th><input type="checkbox" id="legalact_qt_fxk" name="fxk" value="legalact_qt" style="margin-right: 5px;"/>其他违法犯罪行为：</th>
	    		         <th><input type="checkbox" id="czMethod_all_fxk" name="fxk" value="czMethod_all" style="margin-right: 5px;"/>处置方式：</th>
	    		</tr>
	    		<tr>
	    				<th><input type="checkbox" id="sls_meaResult_fxk" name="fxk" value="sls_meaResult" style="margin-right: 5px;"/>组织措施：</th>
	    		</tr>
		</table>
		</form>
</div>