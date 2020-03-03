<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var special = '${special}';
var pageName="${pageName}"
var zddb="${zddb}"
	function search(){		
		var obj = $('#searForm').serializeObject();
		if (obj.receiveDate != "" && obj.receiveDate != null) {
			obj.startDate = DateUtil.dateToStr('yyyy-MM-dd HH:mm:ss', new Date(
					obj.receiveDate));
		}	
		if(obj.jd0==""){
			obj.jd0="NO";
		}
		if(obj.jd1==""){
			obj.jd1="NO";
		}
		if(obj.sc0==""){
			obj.sc0="NO";
		}
		if(obj.sc1==""){
			obj.sc1="NO";
		}
		if(obj.cf0==""){
			obj.cf0="NO";
		}
		if(obj.cf1==""){
			obj.cf1="NO";
		}
		var x=$("#cf").combotree('getValue');
		if(!x){
			obj.cf='NO';
		}
		parent.$.modalDialog.openner_dataGrid.datagrid('load', {
			//被反映人信息
			reflectedName:obj.reflectedName,
			sex:obj.sex,
			nation:obj.nation,
			birthday:obj.birthday,
			xl:obj.xl,
			workPosition:obj.workPosition,
			duty:obj.duty,
			rank:obj.rank,
			worktime:obj.worktime,
			political:obj.political,
			intime:obj.intime,
			civilServant:obj.civilServant,
			departmenType:obj.departmenType,
			natureOfenterprise:obj.natureOfenterprise,
			classOfenterprise:obj.classOfenterprise,
			post:obj.post,
			posType:obj.posType,
			topDiscipline:obj.topDiscipline,
			cadre:obj.cadre,
			np:obj.np,
			zx:obj.zx,
			jd:obj.jd+"_"+obj.jd0+"_"+obj.jd1,
			cf:obj.cf+"_"+obj.cf0+"_"+obj.cf1,
			sc:obj.sc+"_"+obj.sc0+"_"+obj.sc1,
			problemDescribe:obj.problemDescribe,
			remarks:obj.remarks,
			//线索信息
			cluesNum:obj.cluesNum,
			startDate : obj.startDate,
			endDate : obj.endDate,
			clues : obj.clues,
			problemLand : obj.problemLand,
			fields:obj.fields,
			special:obj.special,
			belongToId:obj.belongToId,
			isResult:obj.isResult,
			isImport:obj.isImport,
			organId:obj.organId,
			cbr_now:obj.cbr_now,
			//反映人信息
			reflectingName:obj.reflectingName,
			reflectingDept:obj.reflectingDept,
			tell:obj.tell,
			pageName:pageName,
			zddb:zddb,
			chongfu:$("input:checkbox:checked").val()
		});//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
        parent.$.modalDialog.handler.dialog('close');
		
	}
	 $('#searForm').form({
	  onSubmit : function() {
               search();
            }
		
	
	  });
	 
	 function ctt (value){
		 console.log(value);
		document.getElementById("org").value=value;
	 }
	 $(function(){
		 $('#cf').combotree({
		    	width:'100%',
		        url: '${path }/dict/dictCombotree',
		        multiple: true,
		        panelHeight : 'auto',
		        queryParams:{dictPid:'02'},
		        onDblClick:function(node){
		        	if (node.state == 'open') {
		        		var node = $('#cf').combotree('tree').tree('find', node.id);
			        	$('#cf').combotree('tree').tree('collapse', node.target);
		        	}else{
		        		var node = $('#cf').combotree('tree').tree('find', node.id);
			        	$('#cf').combotree('tree').tree('expand', node.target);
		        	}
		        	
		        },
		        onBeforeExpand:function(node){
		        	 var childrenArr = $('#cf').combotree('tree');  
		        	 console.log(node.dictId);
		        	 childrenArr.tree('options').queryParams={dictPid:node.dictId};
			    } ,loadFilter: function(data){  
			    	console.log(data+"=====");
			    	for(var x in data){
			    		data[x].id=data[x].dictId;
			    	}
			        if (data.d){   
			            return data.d;    
			        } else {    
			            return data;    
			        }    
			    }
		    });	 
	 });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="overflow-y:scroll;">
   <form id="searForm" method="post" >
        <table class="formTable">
        	<tr>		    		   	 
	    		<th colspan="3">
	      		 	<p align="center">--被反映人信息--</p>
	      		</th>
	      		<th colspan="1">
	      		    显示重复件
	      		</th>
	      		<th colspan="1">
	      		 <input id="chongfu" name="ck" type="checkbox" value="0" style="width:25px;height:25px;"/>	
	      		</th>
	      		<th colspan="1">
	      		</th>
	      	</tr>
	      	<tr>
	      		<th>被反映人姓名：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="beReflectName" name="reflectedName"/></td>  
	    		<th>被反映人性别：</th>
				<td>
				<select id="sex" name="sex" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px;'">   
							    <option value="" selected="true">请选择</option>
							     <option value="1" >男</option>
							     <option value="2" >女</option>
						 </select>
				</td>
				<th style="font-size: 15px;">被反映人民族：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="nation" name="nation"/></td>  
	    		
			</tr>
			<tr>	
				<th>出生年月：</th>
                <td>
                  <input class="easyui-datebox" data-options="width:'200px',editable:false" name="birthday" id='birthday'/>
                 </td> 
                 <th>学历：</th>
                 <td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="xl" name="xl"></td>
                 <th>工作单位：</th>
                 <td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="workPosition" name="workPosition"></td>
                                                                                                
			</tr>
			<tr>
				<th>被反映人职务：</th>
                <td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="duty" name="duty"></td>
                <th>职级：</th>
	    			<td>
	    				<select id="rank" name="rank" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${rank}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		  <th>任职时间：</th>
                  <td>
                  	<input class="easyui-datebox" data-options="width:'200px',editable:false" name="worktime" id='worktime'/>
                  </td>       
			</tr>
			<tr>
				<th>政治面貌：</th>
	    			<td>
	    				<select id="political" name="political" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${political}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>入党时间：</th>
                  <td>
                  	<input class="easyui-datebox" data-options="width:'200px',editable:false" name="intime" id='intime'/>
                  </td>  
                <th>身份：</th>
	    			<td>
	    				<select id="civilServant" name="civilServant" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${civilServant}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
			</tr>
			<tr>	
				<th>部门分类：</th>
	    			<td>
	    				<select id="departmenType" name="departmenType" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${departmenType}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>企业性质：</th>
	    			<td>
	    				<select id="natureOfenterprise" name="natureOfenterprise" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${natureOfenterprise}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>企业级别：</th>
	    			<td>
	    				<select id="classOfenterprise" name="classOfenterprise" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${classOfenterprise}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>	
			</tr>
			<tr>
				<th>岗位：</th>
	    			<td>
	    				<select id="post" name="post" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${post}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>企业人员类别：</th>
	    			<td>
	    				<select id="posType" name="posType" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${posType}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>一把手违纪：</th>
	    			<td>
	    				<select id="topDiscipline" name="topDiscipline" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${topDiscipline}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
			</tr>
			<tr>
				<th>干部管理权限：</th>
	    			<td>
	    				<select id="cadre" name="cadre" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${cadre}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>人大代表：</th>
	    			<td>
	    				<select id="np" name="np" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${np}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>政协委员：</th>
	    			<td>
	    				<select id="zx" name="zx" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${zx}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
			</tr>
			<tr>		    		   	 
	    		<th colspan="6">
	      		 	<p align="center">--线索信息--</p>
	      		</th>
	      	</tr>
	      	<tr>	
	      		<th>案件编号：</th>
                <td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="cluesNum" name="cluesNum"></td>
                <th>收件日期从：</th>
	    		<td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="receiveDate" name="startDate"/></td> 
	    		<th>到：</th>
	    		<td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="receiveDate" name="endDate"/></td> 
	    	</tr>
	    	<tr>
	    		<th>线索来源：</th>
	    			<td>
	    				<select id="clues" name="clues" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${clues}">
								       <option value="${act.value }">${act.name}</option>
		                        </c:forEach>
						 </select>
	    			</td>
	    		<th>问题属地：</th>
	    			<td>  
	    				<select id="problemLand" name="problemLand" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px'">   
							    <!-- <option value="" selected="true" disabled="true">请选择</option> -->
							    <option value="" selected="true"></option>
							   	<c:forEach var="act" items="${problemLand}">
								     <option value="${act.value }">${act.name}</option>
		                       	</c:forEach>
						 </select>
	    			</td>
	    		<th>线索所属领域：</th>
	    		<td>
	    			<select id="fields" name="fields" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px',">   
							    <!-- <option value="" selected="true" disabled="true">请选择</option> -->
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${fields}">
								      <option value="${act.value }">${act.name}</option>
		                         </c:forEach>
						 </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>专项行动：</th>
	    		<td>
	    			<select id="special" name="special" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px',">   
							    <!-- <option value="" selected="true" disabled="true">请选择</option> -->
							    <option value="" selected="true"></option>
							    <c:forEach var="act" items="${special}">
								      <option value="${act.value }">${act.name}</option>
		                         </c:forEach>
						 </select>
	    		</td>
	    		<th>是否单位或事件事故：</th>
	    		<td>
	    			<select id="belongToId" name="belongToId" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px',">   
							    <!-- <option value="" selected="true" disabled="true">请选择</option> -->
							    <option value="" selected="true"></option>
							    <option value="1">是</option>
								<option value="2">否</option>
						 </select>
	    		</td>
	    		<th>是否要结果：</th>
	    		<td>
	    			<select id="isResult" name="isResult" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px',">   
							    <!-- <option value="" selected="true" disabled="true">请选择</option> -->
							    <option value="" selected="true"></option>
							    <option value="1">是</option>
								<option value="2">否</option>
						 </select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<th>是否重要问题：</th>
	    		<td>
	    			<select id="isImport" name="isImport" class="easyui-combobox"  panelHeight="100" data-options="editable:false,width:'200px',">   
							    <!-- <option value="" selected="true" disabled="true">请选择</option> -->
							    <option value="" selected="true"></option>
							    <option value="1">是</option>
								<option value="2">否</option>
						 </select>
	    		</td>
	    		<th>市承办部门：</th>
	    		<td>
	    		<input id="org" type="hidden" name="organId" value=""/>
	    		<select   onchange="ctt(this.options[this.options.selectedIndex].value);" style="width:200px;">   
	    		        <option value="" selected="true"></option>
					    <option value="11">案管室</option>   
					    <option value="13">第一纪检监察室</option>   
					    <option value="14">第二纪检监察室</option>   
					    <option value="15">第三纪检监察室</option>   
					    <option value="16">第四纪检监察室</option>
					     <option value="17">第五纪检监察室</option>  
					      <option value="18">第六纪检监察室</option>  
					       <option value="19">第七纪检监察室</option>  
					        <option value="20">第八纪检监察室</option>  
					         <option value="21">第九纪检监察室</option>  
					          <option value="22">第十纪检监察室</option>  
					           <option value="23">第十一纪检监察室</option> 
					            <option value="24">第十二纪检监察室</option>  
					             <option value="25">第十三纪检监察室</option>
					             <option value="27">党风政风监察室</option>  
					              <option value="30">审理室</option>
					              <option value="27">党风室</option> 
					              <option value="26">干部监督室</option>        
				</select> 
	    		</td>  
	    		<th>县承办部门：</th>
	    		<td>	<select   onchange="ctt(this.options[this.options.selectedIndex].value);" style="width:200px;">   
	    		        <option value="" selected="true"></option>
					    <option value="33">任丘市</option>   
					    <option value="34">黄骅市</option>   
					    <option value="35">河间市</option>   
					    <option value="36">泊头市</option>   
					    <option value="37">沧县</option>
					     <option value="38">青县</option>  
					      <option value="39">献县</option>  
					       <option value="40">肃宁县</option>  
					        <option value="41">盐山县</option>  
					         <option value="42">海兴县</option>  
					          <option value="43">孟村县</option>  
					           <option value="44">南皮县</option> 
					            <option value="45">东光县</option>  
					             <option value="46">吴桥县</option>
					             <option value="47">运河区</option>  
					              <option value="48">新华区</option>    
				</select> </td>  
	    	</tr>
	    	<tr>
	    	  <th>承办人：</th>
	    	  <td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="cbr_now" name="cbr_now"/></td>  
	    	  <th>问题描述：</th>
	    	  <td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="problemDescribe" name="problemDescribe"/></td> 
	    	  <th>备注：</th>
	    	  <td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="remarks" name="remarks"/></td> 
	    	  </tr>
	          <!-- <tr> 
	    	  <th>程序查询：</th>
	    	  <td> 
	    	     <select id="finalState" name="finalState" class="easyui-combobox"   data-options="editable:false,width:'162px'">   
							    <option value=""></option>
								<option value="CH">初核</option>
		                        <option value="LA">立案</option>
		                        <option value="JA">结案</option>
		                        <option value="LZ">留置</option>
						 </select>
	    	  
	    	  </td>
	    	  <th>从：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'162px',editable:false" id="finalState0" name="finalState0"/></td>
	    	  <th>至：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'162px',editable:false" id="finalState1" name="finalState1"/></td>
	    	</tr> -->
	    	<tr>
	    	<th>监督室处理结果查询：</th>
	    	  <td><select id="jd" name="jd" class="easyui-combobox"   data-options="editable:false,width:'200px'">   
							    <option value=""></option>
		                        <option value="1">谈话函询</option>
		                        <option value="2">初步核实</option>
		                        <option value="3">暂存待查</option>
		                        <option value="4">予以了结</option>
		                        <option value="8">诫勉谈话</option>
		                        <option value="9">责令检查批评教育</option>
		                        <option value="7">谈话提醒</option>
		                        <option value="6">了结澄清</option>
		                        <option value="5">给予组织处理</option>
			   </select>
			  </td>
			  <th>从：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="jd0" name="jd0"/></td>
	    	  <th>至：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="jd1" name="jd1" /></td>
	    	</tr>
	    	<tr>
	    	<th>审查室处理结果查询：</th>
	    	  <td><select id="sc" name="sc" class="easyui-combobox"   data-options="editable:false,width:'200px'">   
							    <option value=""></option>
								<option value="1">立案审查（调查）</option>
		                        <option value="2">予以了结</option>
		                        <option value="3">谈话提醒</option>
		                        <option value="4">暂存待查</option>
		                        <option value="5">移送有关党组织处理</option>
		                        <option value="6">诫勉谈话</option>
			   </select>
			  </td>
			  <th>从：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="sc0" name="sc0"/></td>
	    	  <th>至：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="sc1" name="sc1"/></td>
	    	</tr>
	    	<tr>
	    	<th>处分结果查询：</th>
	    	<td><select id="cf" class="easyui-combotree" name="cf" style="width:300px;" ></select></td>
	    	  <th>从：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="cf0" name="cf0"/></td>
	    	  <th>至：</th>
	    	  <td><input type="text" class="easyui-datebox" data-options="width:'200px',editable:false" id="cf1" name="cf1"/></td>
	    	</tr>
	    	<tr>		    		   	 
	    		<th colspan="6">
	      		 	<p align="center">--反映人信息--</p>
	      		</th>
	      	</tr>
	      	<tr>
	      		<th>反映人姓名：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="reflectingName" name="reflectingName"/></td>  
	    		<th>反映人单位：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="reflectingDept" name="reflectingDept"/></td>  
	    		<th>反映人联系方式：</th>
	    		<td><input type="text" class="easyui-textbox" data-options="width:'200px'" id="tell" name="tell"/></td>  
	    	    
	      	</tr>
	   </table>
	</form>
</div>