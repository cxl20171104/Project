<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">


$('#mmxxxxx').combotree({
  	width:'100%',
      url: '${path }/role/tree',//'${path }/dict/dictCombotree?dictPid=0105',
      multiple: true,
      required: true,
      panelHeight : 'auto'
      
  });


</script>
</head>
<body>
<td><select id="mmxxxxx" name="roleIds"></select></td>


</body>
</html>