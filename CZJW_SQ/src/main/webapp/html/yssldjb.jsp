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
		<form action="">
			<h1 style="text-align: center;" class="page-header">沧州市纪委监委案件移送审理登记表</h1>
			<table class="table  table-bordered table-hover">
				<tr>
					<th>案件名称:</th>
					<td colspan="3"><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>立案机关:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>立案时间:</th>
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
					<th>政治面貌:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>单位及职务:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
				</tr>
				<tr>
					<th>承办室主要负责人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>签字时间:</th>
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
					<th>审理室主要负责人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>签字时间:</th>
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
					<th rowspan="2">移送材料目录 :</th>
					<td colspan="3" rowspan="2"><textarea class="form-control" rows="4"></textarea></td>
				</tr>
				<tr></tr>
				<tr>
					<th>承办人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>承办时间:</th>
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
					<th>接收人:</th>
					<td><input type="text" class="form-control" placeholder=""></td>
					<th>接收时间:</th>
					<td>
						<a class='input-group date' id='c1'> 
							<input name="receiveDate" type='text' class="form-control" id='receiveDate' style="width: 150px; height: 30px;" /> 
							<span class="input-group-addon" style="width: 50px; height: 30px;">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</a>
					</td>
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
