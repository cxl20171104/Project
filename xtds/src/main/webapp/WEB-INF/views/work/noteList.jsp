<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>笔记统计</title>
</head>
<script type="text/javascript">
   $(function(){
	     $('#noteNum').datagrid({
	    	    url : '${path }' + '/cata/cataNotelist',
				fit : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				selectOnCheck : false,
				checkOnSelect : false,
				border : false,
				idField : 'id',
				sortOrder : 'asc',
				columns : [ [
						{
							width : '200',
							title : '名称',
							field : 'name',
							halign : 'center',
							align:  'center',
							sortable : false
						},						
						{
							width : '100',
							title : '笔记数量',
							field : 'showonindex',
							halign : 'center',
							align:  'center',
							sortable : false
						},
						{
							width : '300',
							title : '统计时间',
							field : '_d',
							halign : 'center',
							align:  'center',
							sortable : false,
							formatter:function(value, row, index){
								return getNowFormatDate();
							}
						},
						{
							field : 'id',
							title : '操作',
							halign : 'center',
							align:  'center',
							width : 200,
							formatter : function(value, row, index) {
								var str = '';
								str += $.formatString(
												'<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="searchFun(\'{0}\');" >查看详情</a>',
												row.id);
								return str;
							}
						} ] ],
				loadFilter : function(data) {
					if (data.obj) {
						return data.obj;
					} else {
						return data;
					}
				}
			}); 
	     })
	   function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    }
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;		            
		    return currentdate;
		}
     function searchFun(id){
    	 $('#userNote').datagrid({
	    	    url : '${path }' + '/cata/cataNotelist',
				fit : true,
				fitColumns : true,
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				border : false,
				idField : 'id',
				sortOrder : 'asc',
				queryParams: {
					id:id
				},
				columns : [ [
						{
							width : '200',
							title : '姓名',
							field : 'name',
							halign : 'center',
							align:  'center',
							sortable : false
						},						
						{
							width : '100',
							title : '笔记数量',
							field : 'liveness',
							halign : 'center',
							align:  'center',
							sortable : false
						}						
						 ] ],
				loadFilter : function(data) {
					if (data.obj) {
						return data.obj;
					} else {
						return data;
					}
				}
			}); 
    	 
    	 
     }
</script>
<body class="easyui-layout">    
    <div data-options="region:'east',iconCls:'icon-reload',title:'详情统计'" style="width:500px;">
       <div  id="userNote">
       
       
       </div>    
    </div>
    <div  data-options="region:'center',title:'笔记统计'" style="padding:5px;">
       <div id="noteNum"></div>
    
    </div>   
</body> 
</html>