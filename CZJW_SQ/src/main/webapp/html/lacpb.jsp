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
			<h1 style="text-align: center;" class="page-header">中共沧州市纪律检查委员会沧州市监察委员会立案呈批表</h1>
			<table class="table  table-bordered table-hover">
				<tr>
					<th>呈报单位:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>填表日期:</th>
					<td>
						<a class='input-group date' id='c1'> 
							<input name="receiveDate" type='text' class="form-control" id='receiveDate' style="width: 150px; height: 30px;" /> 
							<span class="input-group-addon" style="width: 50px; height: 30px;">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</a>
					</td>
				</tr>
				<tr>
					<th>姓名:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>性别:</th>
					<td>
						<select name="isSupervise" class="selectpicker" data-style="btn-info" data-s="select-one">
							<option value="0">请选择</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>政治面貌:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>单位及职务:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>职务级别:</th>
					<td>
						<select name="isSupervise" class="selectpicker" data-style="btn-info" data-s="select-one">
							<option value="0">请选择</option>
							<option value="1">厅局级</option>
						</select>
					</td>
					<th>是否监察对象:</th>
					<td>
						<select name="isSupervise" class="selectpicker" data-style="btn-info" data-s="select-one">
							<option value="0">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</td>
				</tr>
				<tr>
					<th rowspan="2">经初步核实认定的<br />主要违纪违法问题 :</th>
					<td colspan="3" rowspan="2"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					
				</tr>
				<tr>
					<th>立案依据:</th>
					<td colspan="3"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					<th>承办部门意见:</th>
					<td colspan="3"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					<th>主管领导意见:</th>
					<td colspan="3"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					<th>分管领导意见:</th>
					<td colspan="3"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					<th>本委主要领导批示:</th>
					<td colspan="3"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					<th>备注:</th>
					<td colspan="3"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr>
					<th>承办人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>审核人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>联系电话:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
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
