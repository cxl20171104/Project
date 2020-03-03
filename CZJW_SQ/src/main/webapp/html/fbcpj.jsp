<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
	 <%@ include file="/commons/bootstrap.jsp" %>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="${staticPath }/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${staticPath }/css/bootstrap-select.min.css"/>
		<link rel="stylesheet" type="text/css" href="${staticPath }/css/common.css"/>
		<link rel="stylesheet" type="text/css" href="${staticPath }/css/bootstrap-datetimepicker.min.css"/>
		<style type="text/css">
			.table>tbody>tr>th{ line-height: 2.42857143;vertical-align:middle;text-align: center; }
		</style>
	</head>
	<body>
		<form>
			<h1 style="text-align: center;" class="page-header">市纪委监委问题线索分办呈批笺</h1>
			<table class="table  table-bordered table-hover">
				<tr>
					<th>主要领导批示:</th>
					<td colspan="3"><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>分管领导意见:</th>
					<td colspan="3"><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>主管领导意见:</th>
					<td colspan="3"><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>分办意见:</th>
					<td colspan="3"><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>受理时间:</th>
					<td>
						<a class='input-group date' id='c1'> 
							<input name="receiveDate" type='text' class="form-control" id='receiveDate' style="width: 150px; height: 30px;" /> 
							<span class="input-group-addon" style="width: 50px; height: 30px;">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</a>
					</td>
					<th>线索来源:</th>
					<td>
						<select name="clues" class="selectpicker" data-style="btn-info" data-s="select-one">
							<option value="0">请选择</option>
							<option>中纪委交办</option>
							<option>省纪委交办</option>
							<option>市纪委直收</option>
							<option>中纪委交办</option>
							<option>巡视中发现</option>
							<option>执纪审查中发现</option>
							<option>审计移送</option>
							<option>司法移送</option>
							<option>下级纪委报送</option>
							<option>县纪委直收</option>
							<option>其他</option>
							<option>巡查中发现</option>
							<option>专项工作中发现</option>
							<option>派驻机构中报送</option>
							<option>市纪委交办</option>
							<option>信访室</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>被反映人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>单位职务:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>主要问题摘要:</th>
					<td colspan="3"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					<th>编号:</th>
					<td><input type="text" class="form-control" placeholder="" disabled=""></td>
					<th>承办人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
				<tr>
					<th>审核人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>签报人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
				</tr>
				</tr>
			</table>
		</form>
		<script type="text/javascript" src="${staticPath }/js/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${staticPath }/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${staticPath }/js/bootstrap-select.min.js"></script>
		<script type="text/javascript" src="${staticPath }/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript">
			$("#receiveDate").datetimepicker({
				format: 'yyyy-mm-dd', //显示格式
				todayHighlight: 1, //今天高亮
				minView: "month", //设置只显示到月份
				startView: 2,
				forceParse: 0,
				showMeridian: 1,
				autoclose: 1 //选择后自动关闭
			});
		</script>
	</body>
</html>
