<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>专家库管理</title>
    <script type="text/javascript">
    $(function() {
        $('#zjkTable').datagrid({
            url : '${path }' + '/zjk/findZjk',
            fit : true,
            fitColumns : true,
            striped : true,
            rownumbers : false,
            pagination : true,
            singleSelect : true,
            selectOnCheck : false,
            checkOnSelect : true,
            border : false,
            idField : 'id',
            sortName : 'createTime',
            sortOrder : 'asc',
            columns : [ [ {
                title : 'id',
                field : 'id',
                checkbox : true
            }, {
                width : '60',
                title : '姓名',
                field : 'name',
                halign : 'center',
                sortable : true
           
            }, {
                width : '40',
                title : '姓别',
                field : 'sex',
                halign : 'center',
                sortable : true,
                formatter:function(value,row,index){
                if(value=="1"){
                    return "男";
                    }
                if(value=="2"){
                    return "女";
                    }
                }
            } ,

            {
                width : '40',
                title : '民族',
                field : 'nation',
                halign : 'center'
            } ,

             {
                width : '100',
                title : '出生年月',
                field : 'birthday',
                halign : 'center'
          /*       formatter:function(value,row,index){
                if(value!=null){
                var birth= value.substr(0,4);
               var nowDate = new Date();
              var Dates= nowDate.getFullYear()
              var age=Dates-birth;
                return age;
                }else{
                return value;
                }
                } */
               
            } ,  {
                width : '150',
                title : '籍贯',
                field : 'place',
                halign : 'center'
            } , {
                width : '150',
                title : '出生地',
                field : 'bir_place',
                halign : 'center'
           
            } ,
            {
                width : '100',
                title : '从业时间',
                field : 'working_time',
                halign : 'center'
            },

            {
            	
                width : '150',
                title : '工作单位',
                field : 'over_unit',
                halign : 'center'
            } ,  {
                width : '50',
                title : '职级',
                field : 'rank',
                halign : 'center'
            },  /*  {
                width : '50',
                title : '是否党员',
                field : 'party_member',
                halign : 'center',
                formatter:function(value,row,index){
                if(value==0){
                return'否';
                }else if(value==1){
                return '是';
                }else{
                		return value;
                	}
                }
            },  */
            {
                width : '50',
                title : '职务',
                field : 'post',
                halign : 'center'
            },{
                width : '100',
                title : '入党时间',
                field : 'party_time',
                halign : 'center'
            },{
                width : '100',
                title : '联系方式',
                field : 'tell',
                halign : 'center'
            },{
            	width : '130',
                title : '目前情况',
                field : 'nowstate',
                halign : 'center'
            },{
                width : '50',
                title : '状态',
                field : 'delete_status',
                halign : 'center',
                formatter:function(value,row,index){
                if(value==0){
                return '<a href="javaScript:editFun();" style="color:blue">正常</a>'
                }else if(value==1){
                return '<a href="javaScript:editFun();" style="color:blue">已删除</a>'
                }else{
                		return value;
                	}
                }
            } ] ],
            loadFilter: function(data){
                console.log(data);
				if (data.obj){
					return data.obj;
				} else {
					return data;
				}
			}
        });
 //统计界面
        $('#tjck').dialog({
        	width:600,
        	height:380,
        	title:'统计分析',
        	closed:true,
        	buttons:[{
	        	text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$('#tjck').dialog('close');
					}
        	}]
        });
        $('#companyDialog').dialog({
        	width:800,
        	height:460,
        	title:'专家信息',
        	closed:true,
        	buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					save();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$('#companyDialog').dialog('close');
				}
			}]
        });
         $('#ecxlein').dialog({
        	width:500,
        	height:200,
        	title:'导入专家信息',
        	closed:true,
        	
        });

         $('#searchDialog').dialog({
        	 width:800,
         	height:315,
         	title:'查询条件填写',
         	closed:true,
         	buttons:[{
 				text:'开始查询',
 				iconCls:'icon-save',
 				handler:function(){
 					searchStart();
 					$('#searchDialog').dialog('close');
 				}
 			},{
 				text:'取消',
 				iconCls:'icon-cancel',
 				handler:function(){
 					$('#searchDialog').dialog('close');
 				}
 			}]
         });
    });
    
    	function editFun(){
		var row = $('#zjkTable').datagrid('getSelected');
		if(row != null){
			$('#companyDialog').dialog('refresh','${path }/zjk/info?id='+row.id);
		    $('#companyDialog').dialog('open');
		}else{
			$.messager.show({
				title:'消息提示',
				msg:'请选择一条专家信息!!',
				timeout:3000,
				showType:'slide'
				
			});
		}
		
	}
	
     function importFun(){
         $("#fileData").val("");
		$('#fileData').click();
	}
    function importExcel(){
		var formData = new FormData();
		if($("#fileData")[0] != undefined){
			formData.append("fileData",$("#fileData")[0].files[0]);
		}
		$.ajax({
			url:'${path }/zjk/importZjk',
			data:formData,
			dataType:'json',
			type:'post',
			processData : false, 
			contentType : false,
			success:function(result){
				if(result.success){
					refreshTable();
				}
				$.messager.show({
					title:'消息提示',
					msg:result.msg,
					timeout:3000,
					showType:'slide'
				});
			}
		});
	}
	
	function deleteFun(){
		var rows = $('#zjkTable').datagrid('getChecked');
		if(rows.length>0){
			$.messager.confirm('确认对话框', '您确认要删除吗？', function(r){
				if (r){
					var ids = new Array();
					for(var i=0;i<rows.length;i++){
						ids[ids.length] = rows[i].id;
					}
					$.ajax({
						url:'${path }' + '/zjk/delete',
						data:{ids:ids.join()},
						dataType:'json',
						async : true,
						type:'post',
						success:function(result){
							if(result.success){
								$('#zjkTable').datagrid('load');
							}
							$.messager.show({
								title:'消息提示',
								msg:result.msg,
								timeout:3000,
								showType:'slide'
							});
						}
					});
				}
			});
			
		}else{
			$.messager.show({
				title:'消息提示',
				msg:'请勾选要删除的专家信息',
				timeout:3000,
				showType:'slide'
			});
		}
	}
	function refreshTable(){
		$('#zjkTable').datagrid('load');
	}
	function closeDialog(){
		$('#companyDialog').dialog('close');
	}
	function searchFun(){
		$('#zjkTable').datagrid('load',
		{name:$('#searchName').textbox('getValue'),
		 xl:$('#xl').combobox('getValue'),
		 zy:$('#zy').combobox('getValue'),
		 starttime:document.getElementById("starttime").value,
		 endtime:document.getElementById("endtime").value,
		 delete_status:$('#delete_status').combobox('getValue')
		});
	}
	function addFun(){
		$('#companyDialog').dialog('refresh','${path }/zjk/info');
		$('#companyDialog').dialog('open');
	}
	function ecxleFun(){
		$('#ecxlein').dialog('refresh','${path }/excle/excle');
		$('#ecxlein').dialog('open');
	}
	function exportFun(){
		var rows = $('#zjkTable').datagrid('getChecked');
		var ids="";
        for(var i=0;i<rows.length;i++){
             ids+=rows[i].id+",";
        }
		if(ids==""){
			$.messager.show({
				title:'消息提示',
				msg:'请勾选要导出的专家信息',
				timeout:3000,
				showType:'slide'
			});
		}else{
			//alert(ids);
			  var form=$("<form></form>");
              form.attr("style","dispaly:none");
              form.attr("targer"," ");
              form.attr("action",'${path}/zjk/exportZjk');
              var input=$("<input>");
 		      input.attr("type","hidden");
 		      input.attr("name","ids");
 		      input.attr("value",ids);
 		      $("body").append(form);
		      form.append(input);
		      form.submit();			
		}
		/* if(rows.length>0){
			$.messager.confirm('确认对话框', '您确认要导出选中数据吗？', function(r){
				if (r){
					var ids = new Array();
					for(var i=0;i<rows.length;i++){
						ids[ids.length] = rows[i].id;
					}
					$.ajax({
						url:'${path }' + '/zjk/exportZjk',
						data:{name:$('#searchName').textbox('getValue'),
							  xl:$('#xl').combobox('getValue'),
							  zy:$('#zy').combobox('getValue'),
							  starttime:document.getElementById("starttime").value,
							  endtime:document.getElementById("endtime").value,
							  delete_status:$('#delete_status').combobox('getValue')},
						dataType:'json',
						async : true,
						type:'post',
						success:function(result){
							if(result.success){
								$('#zjkTable').datagrid('load');
							}
							$.messager.show({
								title:'消息提示',
								msg:result.msg,
								timeout:3000,
								showType:'slide'
							});
						}
					});
				}
			});
			
		} else{
			$.messager.show({
				title:'消息提示',
				msg:'请勾选要导出的专家信息',
				timeout:3000,
				showType:'slide'
			});
		}*/
	}
	
	
	function tjfx(){
		parent.addTab('案件线索统计',"${path}/zjk/tj?lx=in");

	}
	function searchFun(){
		$('#searchDialog').dialog('refresh','${path }/zjk/zjkSearch');
		$('#searchDialog').dialog('open');
	}
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
     <div class="dropdown-menu dropdown-anchor-top-left dropdown-has-anchor" id="dropdown-standard">
					<ul>
						<li><a href="#"  onclick="addFun();">手动添加</a></li>
						<li class="divider"></li>
						<li><a href="#"  onclick="importFun();">批量导入</a></li>
					</ul>
	 </div>  
    <div data-options="region:'north',split:false,border:false" style="height:35px;padding-top:4px;border-bottom:1px solid #d3d3d3">
  	    <shiro:hasPermission name="/zjk/add">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-add'"   data-dropdown="#dropdown-standard"  >添加</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/zjk/import">
            <!-- <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="importFun();" >导入</a> -->
            <form id="importForm" method="post" enctype="multipart/form-data" style="display: none;">
            	<input type="file" id="fileData" name="fileData" onchange="importExcel()"  value="cxl"/>
            </form>
        </shiro:hasPermission>
        <shiro:hasPermission name="/zjk/import">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="exportFun();" >导出</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/zjk/edit">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="editFun();" >编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="/zjk/delete">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-del'" onclick="deleteFun();" >删除</a>
        </shiro:hasPermission>
        
       
         <a style="margin-left:10px;" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();" >查询</a>
        
        	
       <!--  <span style="float: right;">
            <a>请输入专家名称:</a>
        	<input type="text" class="easyui-textbox" data-options="" id="searchName" />
        	<a>学历:</a>
        	<select id="xl" type="combobox" name="xl" class="easyui-combobox" style="width:65px;">
				<option value='' selected>全部</option>
				<option value='0'>博士</option>
				<option value='1'>硕士</option>
				<option value='2'>本科</option>
				<option value='3'>大专</option>
				<option value='4'>其他</option>
       		</select>
        	<a>专业:</a>
        	<select id="zy" type="combobox" name="zy" class="easyui-combobox" style="width:65px;">
				<option value='' selected>全部</option>
				<option value='0'>计算机</option>
				<option value='1'>法律</option>
				<option value='2'>中文</option>
				<option value='3'>财务</option>
				<option value='4'>其他</option>
       		</select>
        	<a>年龄段:</a>
        	<input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="starttime" name="starttime" value="" />
        		到
        	<input type="text" class="laydate-icon"  onClick="javascript:WdatePicker({dateFmt:'yyyy-MM-dd'});" data-options="width:'100%',required:true"  id="endtime" name="endtime" value="" />
        	<a>专家状态:</a>
        	<select id="delete_status" type="combobox" name="delete_status" class="easyui-combobox" style="width:65px;">
				<option value='0' selected>正常</option>
				<option value='1'>已删除</option>
				<option value=''>全部</option>
       		</select>
	        <a class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchFun();" >查询</a>
        </span> -->
    </div>
    <div data-options="region:'center',border:false">
        <table id="zjkTable"></table>
    </div>
    <div id="companyDialog"></div>
     <div id="ecxlein"></div>
     <div id = "tjck"></div>
      <div id="searchDialog"></div>
</body>
</html>