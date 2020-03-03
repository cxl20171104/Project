                                        <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  
                         				<table class="table  table-bordered table-hover">
                         				   <tr>
											<th>姓名</th>
											<td>
												<input type="text" style="width:200px;" class="form-control"
													name="reflectingPerson.reflectingName" placeholder="请输入名称"
													style="width: 220px; height: 34px;" value="${problemClues.reflectingPerson.reflectingName}" disabled>
											</td>
											<th>单位</th>
											<td>
												<input type="text" class="form-control"
													name="reflectingPerson.reflectingDept" placeholder="请输入单位"
													style="width: 220px; height: 34px;" value="${problemClues.reflectingPerson.reflectingDept}" disabled>
											</td>
											<th>联系方式</th>
											<td>
												<input type="text" class="form-control"
													name="reflectingPerson.tell" placeholder="请输入联系方式"
													style="width: 220px; height: 34px;"  value="${problemClues.reflectingPerson.tell}" disabled>
											</td>
											</tr>
										</table>
									
                                 
                                         
                             
                             
<script type="text/javascript">
	
	$(".selectpicker").selectpicker({  
        noneSelectedText : '请选择'//默认显示内容  
    });
</script>