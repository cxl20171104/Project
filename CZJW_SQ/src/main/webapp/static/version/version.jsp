<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
.cxl { width: 100%; min-height: 50px; line-height: 50px; text-align: center; border-color:#0000; border-collapse: collapse;} 
.cxl td{border:1px solid #ccc;font-size:14px;width:300px;}
</style>
<script type="text/javascript">

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
       <table class="cxl">
               <tr>
                   <td>序号</td>
                   <td>版本号</td>
                   <td>内容变化</td>
                   <td>发布时间</td>
               </tr>
               <tr>
                   <td>1</td>
                   <td>v0.0.1</td>
                   <td>[1]增加干部监督室分办[2]党风室添加独立办案功能[3]重复件查询[4]同案人员绑定功能</td>
                   <td>2018-08-17</td>
               </tr>
                <tr>
                   <td>2</td>
                   <td>v0.0.2</td>
                   <td>[1]退回方法的处理[2]区县线索库[3]添加适当处理[4]下发县区案件的查询调整[5]增加全网查询功能</td>
                   <td>2018-08-23</td>
               </tr>
                <tr>
                   <td>3</td>
                   <td>v0.0.3</td>
                   <td>[1]呈批签各区县区分（呈批件标题） 呈批签内容三号字</td>
                   <td>2018-09-04</td>
               </tr>
                <tr>
                   <td>4</td>
                   <td>v0.0.4</td>
                   <td>[1]全网查询的数据库连接刷新
                       [2]线索导入时空格问题处理</td>
                   <td>2018-09-18</td>
               </tr>
       </table>
</body>
</html>