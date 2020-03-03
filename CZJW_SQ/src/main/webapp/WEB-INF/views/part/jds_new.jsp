<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="jds_lc" type="button" class="btn btn-default" onclick="jds_save('JDSLC');">保存</button>
</c:if>
<c:if test="${fn:contains(roleId,'18')!=false||(pageName=='having'||pageName=='initial'||pageName=='returnList')||pageName=='newClues'||pageName=='tongan'}">
<button id="jds_tj" type="button" class="btn btn-default" onclick="jds_save('JDSTJ');">提交</button>
</c:if>
<div id="history_jds" style="margin-top:5%">



</div>
<form id="jdsForm" name="jdsForm" >
<input id="cluesId_jds" name="problemClues.id" value="${problemClues.id}" type="hidden" />
<input id="lc_jds" name="problemClues.state" value="${problemClues.state}" type="hidden" />
<input  type="hidden" name="problemClues.isXf" value="${problemClues.isXf}" />
<input  type="hidden" name="resultTime" value="${problemClues.resultTime}" />
<input  type="hidden" name="problemClues.fromId" value="${problemClues.fromId}" />
<input  type="hidden" name="problemClues.organId" value="${problemClues.organId}" />
<table class="cxl">
</table>
<div id="abc"  class="table  table-bordered table-hover" >
</div>
<div id="cbhs"  class="table  table-bordered table-hover" >
</div>
</form>
<script>
//进度的索引值
var my_index=0;
//创建并添加处置建议html
function add_czjy(id){
	var html='<table id="'+my_index+'" class="cxl"><tr><tr><td>处置建议:</td><td>'+
		     '<input type="hidden"  name="progress['+my_index+'].causeId" value="${problemClues.id}">'+
	         '<input type="hidden"  name="progress['+my_index+'].pointValue" value="3.5">'+
	         '<input type="hidden"  name="progress['+my_index+'].pointName" value="监督室处置建议">'+
	         '<input type="hidden"  name="progress['+my_index+'].type" value="监督室处置建议">'+
	         '<input type="hidden" id="userId"  name="progress['+my_index+'].user.id" value="">'+
	         '<select   id="'+my_index+'czjy"        name="progress['+my_index+'].detail" class=" cxls form-control"    style="width:300px;height:45px;"'+
	         '>'+
	                        '<option value="0">请选择</option>'+
	                        '<option value="1">谈话函询</option>'+
	                        '<option value="2">初步核实</option>'+
	                        '<option value="3">暂存待查</option>'+
	                        '<option value="4">予以了结</option>'+
	                  '</select>'+
	                  '</td>'+
	                  '<td>'+
	                  '备注'+
	                  '</td>'+
	                  '<td>'+
	                  '<input  id="'+my_index+'remark"  name="progress['+my_index+'].remark" style="width:100%;height:45px;"/>'+
	                  '</td>'+
	                  '<td>'+
	                  '</td>'+
	                '</tr>'+
	                '<tr><td colspan="5"><hr style="border:5px solid #ccc;"/></td></tr>'+
	                  '</table>';
	                my_index+=1;
	          $(id).append(html);
}
//执纪监督会议时间
function add_zjTime(id){
	var html=
	 '<table id="'+my_index+'" class="cxl table p">'+
	 '<tr>'+
	 '<input    name="progress['+my_index+'].pointValue" type="hidden" value="3.6"/>'+
     '<input    name="progress['+my_index+'].detail" type="hidden" value="执纪监督专题会议"/>'+
     '<input    name="progress['+my_index+'].pointName" type="hidden" value="执纪监督专题会议"/>'+
     '<input    name="progress['+my_index+'].type" type="hidden" value="执纪监督专题会议"/>'+
     '<td>执纪监督专题会议时间</td>'+
     '<td><input  id="'+my_index+'zjTime"  type="text" name="progress['+my_index+'].timeForday" class="databox"  style="width:300px;height:45px;" ></input>'+  
     '</td>'+
     '<td>'+
     '</td>'+
     '<td>'+
     '</td>'+
     '<td>'+
     '</td>'+
     '</tr>'+
     '<tr>'+	
     '</table>';
	 $(id).append(html);
	 $("#"+my_index+"zjTime").datebox({});
	 my_index+=1;
}
//监督室会议决定
function add_czjd(id){
	var html='<table id="'+my_index+'" class="cxl"><tr><tr><td>监督室执纪监督会议决定:</td><td>'+
		     '<input type="hidden"  name="progress['+my_index+'].causeId" value="${problemClues.id}">'+
	         '<input type="hidden"  name="progress['+my_index+'].pointValue" value="3.6">'+
	         '<input type="hidden"  name="progress['+my_index+'].pointName" value="监督室执纪监督会议决定">'+
	         '<input type="hidden"  name="progress['+my_index+'].type" value="监督室执纪监督会议决定">'+
	         '<input type="hidden" id="userId"  name="progress['+my_index+'].user.id" value="">'+
	         '<select   id="'+my_index+'czjd"        name="progress['+my_index+'].detail" class=" cxls form-control"    style="width:300px;height:45px;"'+
	         'onchange="gradeChange('+my_index+',this.value)">'+
	                        '<option value="0">请选择</option>'+
	                        '<option value="1" >谈话函询</option>'+
	                        '<option value="2">初步核实</option>'+
	                        '<option value="3">暂存待查</option>'+
	                        '<option value="4">予以了结</option>'+
	                  '</select>'+
	                  '</td>'+
	                  '<td>'+
	                  '</td>'+
	                  '<td>'+
	                  '</td>'+
	                  '<td>'+
	                  '</td>'+
	                '</tr>'+
	                '<tr><td colspan="5"><hr style="border:5px solid #ccc;"/></td></tr>'+
	                  '</table>';
	                my_index+=1;
	          $(id).append(html);
}
//创建并添加谈话函询html
function add_czjd_thhx(id){
    var name="谈话函询处置情况报告";
    var name2="谈话函询处置方案";
	var html=
    '<table id="'+my_index+'" class="cxl table p">'+
    '<tr><td colspan="5">谈话函询</td></tr>'+
	'<tr>                        <input  name="progress['+my_index+'].pointValue" type="hidden" value="3.6"/></tr>'+
	                            '<input   name="progress['+my_index+'].pointName" type="hidden" value="监督室处置决定"/>'+
	                            '<input   name="progress['+my_index+'].type" type="hidden" value="谈话函询"/>'+
	                            '<input   name="progress['+my_index+'].detail" type="hidden" value="谈话函询时间"/>'+
	'<tr>'+
    '<td>谈话函询时间</td>'+
      '<td><input id="'+my_index+'ThhxTime"   name="progress['+my_index+'].timeForday"   style="width:300px;height:45px;" ></input>'+
      '</td> '+
       '<td>'+
       '</td>'+
       '<td>'+
       '</td>'+
       '<td>'+
       '</td>'+
        '</tr>'+
        '<tr>'+
        '<td>处置情况报告：</td>'+
        '<td>'+
        '<label id="'+my_index+'label2"  for="'+my_index+"_"+name+'">上传</label>'+
        '<input  id="'+my_index+"_"+name+'" type="file"   multiple="multiple"  onchange="checkField(\''+my_index+"_"+name+'\')"/>'+
         '</td>'+
         '<td>'+
        '<label id="' +my_index+'thhx_bg_A" for="'+my_index+'thhx_bg_B">文件下载</label>'+
        '&nbsp;&nbsp;&nbsp;&nbsp;'+
        '<label id="' +my_index+'thhx_bg_C" for="'+my_index+'thhx_bg_D">文件删除</label>'+
        '<input  id="'+my_index+'thhx_bg_B" style="visibility:hidden"  class="file" data-title="谈话函询处置情况报告" value="" onclick="download(this);"/>'+
        '<input  id="'+my_index+'thhx_bg_D" style="visibility:hidden"  data-title="谈话函询处置情况报告" value="" onclick="deleteFile(this);"/>'+
        '</td>'+
        '<td>'+
        '</td>'+
        '<td>'+
        '</td>'+
         '</tr>'+
         '<tr>'+
         '<td>谈话函询方案：</td>'+
         '<td>'+
         '<label id="'+my_index+'label" for="'+my_index+"_"+name2+'">上传</label>'+
         '<input  id="'+my_index+"_"+name2+'" type="file"   multiple="multiple"  onchange="checkField(\''+my_index+"_"+name2+'\')"/>'+
         '</td>'+
         '<td>'+
         '<label id="'+my_index+'thhx_fa_A" for="'+my_index+'thhx_fa_B">文件下载</label>'+
         '&nbsp;&nbsp;&nbsp;&nbsp;'+
         '<label id="' +my_index+'thhx_fa_C" for="'+my_index+'thhx_bg_D">文件删除</label>'+
          '<input  id="'+my_index+'thhx_fa_D" style="visibility:hidden"     value="" onclick="deleteFile(this);"/>'+
         '</td>'+
          '<td>'+
          '处置决定'+
         '</td>'+
         '<td>'+
         '<input    name="progress['+parseInt(my_index+1)+'].pointValue" type="hidden" value="3.6"/>'+
         '<input    name="progress['+parseInt(my_index+1)+'].pointName" type="hidden" value="监督室处置决定"/>'+
         '<input   name="progress['+parseInt(my_index+1)+'].type" type="hidden" value="谈话函询"/>'+
         '<select   id="'+my_index+'thhx"        name="progress['+parseInt(my_index+1)+'].detail" class=" cxls form-control"    style="width:300px;height:45px;"'+
         'onchange="gradeChange('+my_index+',this.value)">'+
                        '<option value="0">请选择</option>'+
                        '<option value="1" >谈话函询</option>'+
                        '<option value="2">初步核实</option>'+
                        '<option value="3">暂存待查</option>'+
                        '<option value="4">予以了结</option>'+
                        '<option value="5">给予组织处理</option>'+
                        '<option value="6">了结澄清</option>'+
                        '<option value="7">谈话提醒</option>'+
                        '<option value="8">诫勉谈话</option>'+
                        '<option value="9">责令检查批评教育</option>'+
                  '</select>'+
         '</td>'+
         '</tr>'+
         '<tr><td colspan="5"><hr style="border:5px solid #ccc;"/></td></tr>'+
     '</table>';
    
	 $(id).append(html);
	 $("#"+my_index+"ThhxTime").datebox({});
	 my_index+=2;
}

//创建并添加初步核实html
function add_czjd_cbhs(id){
	    var name="初步核实处置情况报告";
	    var name2="关于初步核实的处置意见";
		var html=
			'<table id="'+my_index+'" class="cxl table p">'+
			'<tr ><td colspan="5">初步核实      <input    name="progress['+my_index+'].pointValue" type="hidden" value="3.6"/>'+
		                                 '<input    name="progress['+my_index+'].detail" type="hidden" value="初步核实时间"/>'+
		                                 '<input    name="progress['+my_index+'].pointName" type="hidden" value="监督室处置决定"/>'+
		                                 '<input    name="progress['+my_index+'].type" type="hidden" value="初步核实"/>'+
			'</td></tr>'	+
			'<tr>'+
        '<td>初步核实时间</td>'+
        '<td><input  id="'+my_index+'cbhsTime"  type="text" name="progress['+my_index+'].timeForday" class="databox"  style="width:300px;height:45px;"></input>'+  
        '</td>'+
        '<td>'+
        '</td>'+
        '<td>'+
        '</td>'+
        '<td>'+
        '</td>'+
       '</tr>'+
	   '<tr>'+	         
    '<td>处置情况报告:</td>'+
    '<td>'+
    '<label  id="'+my_index+'label" for="'+my_index+"_"+name+'">上传</label>'+
    '<input  id="'+my_index+"_"+name+'" type="file"   data-title="初步核实处置情况报告" multiple="multiple"  onchange="checkField(\''+my_index+"_"+name+'\')" />'+
    '</td>'+
    '<td>'+
    '<label id="'+my_index+'cbhs_bg_A" for="'+my_index+'cbhs_bg_B">文件下载</label>'+
    '&nbsp;&nbsp;&nbsp;&nbsp;'+
    '<label id="' +my_index+'cbhs_bg_C" for="'+my_index+'thhx_bg_D">文件删除</label>'+
    '<input  id="'+my_index+'cbhs_bg_D" style="visibility:hidden"   value="" onclick="deleteFile(this);"/>'+
    '</td>'+
    '<td>'+
    '</td>'+
    '<td>'+
    '</td>'+
    '</tr>'+
    '<tr>'+
    '<td>关于初步核实的处置意见:</td>'+
    '<td>'+
    '<label id="'+my_index+'label2"  for="'+my_index+"_"+name2+'">上传</label>'+
    '<input  id="'+my_index+"_"+name2+'" type="file"  data-title="关于初步核实的处置意见" multiple="multiple"  onchange="checkField(\''+my_index+"_"+name2+'\')"/>'+
    '</td>'+
    '<td>'+
    '<label   id="'+my_index+'cbhs_czyj_A" for="'+my_index+'cbhs_czyj_B">文件下载</label>'+
    '&nbsp;&nbsp;&nbsp;&nbsp;'+
    '<label id="' +my_index+'cbhs_czyj_C" for="'+my_index+'cbhs_czyj_D">文件删除</label>'+
    '<input  id="'+my_index+'cbhs_czyj_D" style="visibility:hidden"    value="" onclick="deleteFile(this);" />'+
    '</td>'+
    '</tr>'+
    '<tr><td colspan="5"><hr style="border:5px solid #ccc;"/></td></tr>'+
    '</table>';
    
    $(id).append(html);
    $("#"+my_index+"cbhsTime").datebox({required:true});
    my_index+=1;
}

//创建并添加暂存待查html
function add_czjd_zcdc(id){
	    var name="暂存待查处置情况报告";
	    var name2="关于暂存待查的处置意见";
		var html=
			'<table id="'+my_index+'" class="cxl table p">'+
			'<tr ><td colspan="5">暂存待查<input      name="progress['+my_index+'].pointValue" type="hidden" value="3.6"/>'+
		                                 '<input    name="progress['+my_index+'].detail" type="hidden" value="暂存待查时间"/>'+
		                                 '<input    name="progress['+my_index+'].pointName" type="hidden" value="监督室处置决定"/>'+
		                                 '<input    name="progress['+my_index+'].type" type="hidden" value="暂存待查"/>'+
			'</td></tr>'	+
			'<tr>'+
      '<td>暂存待查时间</td>'+
      '<td><input  id="'+my_index+'zcTime"  type="text" name="progress['+my_index+'].timeForday" class="databox"  style="width:300px;height:45px;" ></input>'+  
      '</td>'+
      '<td>'+
      '</td>'+
      '<td>暂存待原因</td>'+
      '<td>'+
      '<input      name="progress['+parseInt(my_index+1)+'].pointValue" type="hidden" value="3.6"/>'+
      '<input    name="progress['+parseInt(my_index+1)+'].pointName" type="hidden" value="监督室处置决定"/>'+
      '<input    name="progress['+parseInt(my_index+1)+'].type" type="hidden" value="暂存待查"/>'+
      '<input  id="'+my_index+'reason"  name="progress['+parseInt(my_index+1)+'].detail"  value="" style="width:300px;height:45px;"/>'+
      '</td>'+
     '</tr>'+
	   '<tr>'+	         
  '<td>暂存待查处置情况报告:</td>'+
  '<td>'+
  '<label id="'+my_index+'label2"  for="'+my_index+"_"+name+'">上传</label>'+
  '<input  id="'+my_index+"_"+name+'" type="file" style="visibility:hidden"  data-title="暂存待查处置情况报告" multiple="multiple"  onchange="checkField(\''+my_index+"_"+name+'\')" />'+
  '</td>'+
  '<td>'+
  '<label id="'+my_index+'zcdc_bg_A" for="'+my_index+'zcdc_bg_B">文件下载</label>'+
  '<input  id="'+my_index+'zcdc_bg_B" style="visibility:hidden" class="file" data-title="暂存待查处置情况报告" value="" onclick="download(this);"/>'+
  '&nbsp;&nbsp;&nbsp;&nbsp;'+
  '<label id="' +my_index+'zcdc_bg_C" for="'+my_index+'zcdc_bg_D">文件删除</label>'+
  '<input  id="'+my_index+'zcdc_bg_D" style="visibility:hidden"   value="" onclick="deleteFile(this);" />'+
  '</td>'+
  '<td>'+
  '恢复查办时间:'+
  '</td>'+
  '<td>'+
  '<input  id="'+my_index+'hfcb"  type="text"  class="databox" name="progress['+parseInt(my_index+2)+'].timeForday"  style="width:300px;height:45px;"></input>'+ 
  '</td>'+
  '</tr>'+
  '<tr>'+
  '<td>关于暂存待查的处置意见:</td>'+
  '<td >'+
  '<label  id="'+my_index+'label" for="'+my_index+"_"+name2+'">上传</label>'+
  '<input  id="'+my_index+"_"+name2+'" type="file" style="visibility:hidden" data-title="关于暂存待查的处置意见" multiple="multiple"  onchange="checkField(\''+my_index+"_"+name2+'\')"/>'+
 
  '</td>'+
  '<td>'+
  '<label  id="'+my_index+'cbhs_czyj_A" for="'+my_index+'cbhs_czyj_B">文件下载</label>'+
  '<input  id="'+my_index+'cbhs_czyj_B" style="visibility:hidden"  class="file" data-title="关于暂存待查的处置意见" value="" onclick="download(this);" />'+
  '&nbsp;&nbsp;&nbsp;&nbsp;'+
  '<label id="' +my_index+'cbhs_czyj_C" for="'+my_index+'cbhs_czyj_D">文件删除</label>'+
  '<input  id="'+my_index+'cbhs_czyj_D" style="visibility:hidden"   value="" onclick="deleteFile(this);" />'+
  '</td>'+
  '<td>'+
  '处置决定'+
 '</td>'+
 '<td>'+
 '<input    name="progress['+parseInt(my_index+2)+'].pointValue" type="hidden" value="3.6"/>'+
 '<input    name="progress['+parseInt(my_index+2)+'].pointName" type="hidden" value="监督室处置决定"/>'+
 '<input    name="progress['+parseInt(my_index+2)+'].type" type="hidden" value="暂存待查"/>'+
 '<select   id="'+my_index+'zcdc"        name="progress['+parseInt(my_index+2)+'].detail" class=" cxls form-control"    style="width:300px;height:45px;"'+
 'onchange="gradeChange('+my_index+',this.value)">'+
                '<option value="0">请选择</option>'+
                '<option value="1" >谈话函询</option>'+
                '<option value="2">初步核实</option>'+
                '<option value="3">暂存待查</option>'+
                '<option value="4">予以了结</option>'+
                '<option value="5">给予组织处理</option>'+
                '<option value="6">了结澄清</option>'+
                '<option value="7">谈话提醒</option>'+
                '<option value="8">诫勉谈话</option>'+
                '<option value="9">责令检查批评教育</option>'+
          '</select>'+
 '</td>'+
  '</tr>'+
  '<tr><td colspan="5"><hr style="border:5px solid #ccc;"/></td></tr>'+
  '</table>';

  $(id).append(html);
  $("#"+my_index+"zcTime").datebox({});
  if(id=="#history_jds"){
	  $("#"+my_index+"hfcb").datebox({
		  onSelect: function(date){
			    hfcb(my_index,date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate());
	      }
	  });
  }
  if(id=="#cbhs"){
	  $("#"+my_index+"hfcb").datebox({});
  }
  my_index+=3;
}

//其他处置方式创建html
function add_czjd_other(name,id){
	
	var text=name+'处置情况报告';
    var html=
   	 
   	  '<table id="'+my_index+'"  class="cxl table p" >'+
		  '<tr><td colspan="5">'+name+'</td></tr>'	+
		  '<tr>'+
		  '<input    name="progress['+my_index+'].pointValue" type="hidden" value="3.6"/>'+
		  '<input    name="progress['+my_index+'].pointName"  type="hidden" value="监督室处置决定"/>'+
		  '<input    name="progress['+my_index+'].detail"  type="hidden" value="'+name+'时间"/>'+
		  '<input    name="progress['+my_index+'].type"  type="hidden" value="'+name+'"/>'+
         '<td>'+text+'</td>'+
         '<td>'+
         '<label id="'+my_index+'label"  for="'+text+'">上传</label>'+
         '<input  id="'+text+'" type="file" multiple="multiple" style="visibility:hidden"  class="file"  onchange="checkField(\''+text+'\')"/>'+
          '</td>'+
          '<td>'+
         '<input  id="'+text+'other" style="visibility:hidden"  class="file"  data-title="'+text+'"  value="" onclick="download(this);"/>'+
         '<label  id="'+text+'other_l_A"     for="'+text+'other_l_B">文件下载</label>'+
         '&nbsp;&nbsp;&nbsp;&nbsp;'+
         '<label id="' +my_index+'other_l_C" for="'+text+'other_l_D">文件删除</label>'+
         '<input  id="'+text+'other_l_D" style="visibility:hidden"    value="" onclick="deleteFile(this);"/>'+
         '</td>'+
         '<td>'+
         '结案时间:'+
         '</td>'+
         '<td>'+
         '<input  id="'+my_index+'ja"  type="text"  class="databox" name="progress['+my_index+'].timeForday"  style="width:300px;height:45px;"></input>'+ 
         '</td>'+
	      '</tr>'+
	      '<tr><td colspan="5"><hr style="border:5px solid #ccc;"/></td></tr>'+
         '</table>';	
	      $(id).append(html);
 	      $("#"+my_index+"ja").datebox({required:true});
	      my_index+=1;
	     
}

//创建收案日期和承办人html
function add_cbry(id){
	var html= '<table id="'+my_index+'" class="cxl"><tr><td> <label class="col-md-2 control-label" >收案日期</label></td>'+
	 	      '<td>'+
	           '<input type="hidden" name="progress['+my_index+'].pointName" value="收案时间"/>' +
	           '<input type="hidden" name="progress['+my_index+'].type" value="收案时间"/>' +
	           '<input type="hidden" name="progress['+my_index+'].pointValue" value="3.5"/>' +
	           '<input id="'+my_index+'getTime" type="text" name="progress['+my_index+'].timeForday" class="easyui-datebox"  style="width:300px;height:45px;" ></input>'+ 
		      '</td>'+
		      '<td><label class="col-md-2 control-label" >承办人</label></td>'+
		      '<td><input id="cbrSelect'+my_index+'" name="progress['+my_index+'].detail" style="width:300px;height:45px;" value="" ></input></td><td></td></tr>'+
		      '<tr><td colspan="5"><hr style="border:5px solid #ccc;"/></td></tr>'+
		      '</table>';
		       $(id).append(html);
		       $("#"+my_index+"getTime").datebox({});
		       my_index+=1; 
		      
}
//删除dom
function del(x){
	$("#"+x).remove();
	my_index-=1;
}

function hfcb(ix,time){
	console.log(parseInt(ix-1));
	html='<input type="hidden" name="progress['+parseInt(ix-1)+'].timeForday" value='+time+'>'+
	     '<input type="hidden" name="progress['+parseInt(ix-1)+'].pointName" value="恢复查办时间">';
	     $("#cbhs").append(html);
} 
function gradeChange(ix,value,text){
	    $(".table").each(function(){
		if($(this).attr("id")>ix){
			$(this).remove();
		}
		});
	    if(value=="1"){
	    	add_czjd_thhx('#cbhs');
	    }
		if(value=="2"){
			if(text	=="1"){
				return true;
			}else{
				add_czjd_cbhs('#cbhs');
			}
			
		}
		if(value=="3"){
			add_czjd_zcdc('#cbhs');
		}
		if(value=="4"){
			add_czjd_other('予以了结','#cbhs');
		}
        if(value=="5"){
        	add_czjd_other('给予组织处理','#cbhs');
		}
        if(value=="6"){
        	add_czjd_other('了结澄清','#cbhs');
		}
       if(value=="7"){
	        add_czjd_other('谈话提醒','#cbhs');		
		}
       if(value=="8"){
	       add_czjd_other('诫勉谈话','#cbhs');
        }
      if(value=="9"){
	       add_czjd_other('责令检查批评教育','#cbhs');
      }
      
      //修改进度为监督室处置决定 最大的
      $.getJSON("${path}/jds/updateCzjd?cluesId="+$("#cluesId_jds").val()+"&pointValue="+value,function(req){
    	      if(req.success){
    	    	  alert("处置决定已切换");
    	      }
      });
      
}
function jds_zc_data(){
	if(!$("#probleCluesId").val()){
		return false;
	}
	$("#jds_jb_jds").val($("#probleCluesId").val());
	if($("#state").val()){
		if($("#state").val().indexOf("LC")!=-1){
			$("#jds_lc").css('color','blue');
		}
		if($("#state").val().indexOf("TJ")!=-1){
			$("#jds_tj").css('color','blue');
		}
		
	}
	  $("#history_jds").html("");
	  $("#abc").html("");
	  $("#cbhs").html("");
	  $.ajax({
		  url:"${path}/jds/jds_zc",
		  data:{id:$("#probleCluesId").val(),ip:$("#dataSourceIp").val()},
		  type:"post",
		  dataType:"json",
		  success:function(result){
			    //当只有监督室受理这一条进度时
			    console.log(result);
			    if(result.obj.progress&&result.obj.progress[result.obj.progress.length-1].pointName=="监督室受理"){
			    	add_cbry('#abc');
					add_czjy('#abc');
					add_zjTime('#abc');
					add_czjd('#abc');
			    }
				
			  if(!result.obj.progress){
				  return false;
			  }
			  $.each( result.obj.progress, function(index, content){
				  if(content.pointName=="收案时间"){
					  console.log("#cbrSelect"+my_index);
					  add_cbry("#history_jds");
					  if(content.timeForday){
						  $("#"+parseInt(my_index-1)+'getTime').datebox('setValue',content.timeForday.substring(0,10));
					  }	
					  $("#cbrSelect"+parseInt(my_index-1)).val(content.detail);
					  
				  }
				  if(content.pointName=="监督室处置建议"){
					  add_czjy("#history_jds");
					  $("#"+parseInt(my_index-1)+"czjy").val(content.detail);
					  $("#"+parseInt(my_index-1)+"remark").val(content.remark);
				  }
				  if(content.pointName=="执纪监督专题会议"){
					  add_zjTime("#history_jds");
					  if(content.timeForday){
						  $("#"+parseInt(my_index-1)+'zjTime').datebox('setValue',content.timeForday.substring(0,10));
					  }	
				  }
				  if(content.pointName=="监督室执纪监督会议决定"){
					  add_czjd("#history_jds");
					  $("#"+parseInt(my_index-1)+"czjd").val(content.detail);
				  }
                  if(content.pointName=="监督室处置决定"){
					  if(content.detail=="暂存待查时间"){
						  add_czjd_zcdc("#history_jds");
						  if(content.timeForday){
							  $("#"+parseInt(my_index-3)+"zcTime").datebox('setValue',content.timeForday.substring(0,10));
						  }						  
						  $("#"+parseInt(my_index-3)+"label").html("");
						  $("#"+parseInt(my_index-3)+"label2").html("");
						  $("#"+parseInt(my_index-3)+"zcdc").val(result.obj.progress[index+2].detail);
						  $("#"+parseInt(my_index-3)+"reason").val(result.obj.progress[index+1].detail);
						  if(result.obj.progress[index+2].timeForday){
							  $("#"+parseInt(my_index-3)+"hfcb").datebox('setValue',result.obj.progress[index+2].timeForday.substring(0,10));
						  }						 
					  }else
                      if(content.detail=="初步核实时间"){
                    	  add_czjd_cbhs("#history_jds");
                    	  if(content.timeForday){
                    		  $("#"+parseInt(my_index-1)+"cbhsTime").datebox('setValue',content.timeForday.substring(0,10));  
                    	  } 
                    	  $("#"+parseInt(my_index-1)+"label").html("");
						  $("#"+parseInt(my_index-1)+"label2").html("");
					  }else
                      if(content.detail=="谈话函询时间"){
                    	  add_czjd_thhx("#history_jds");
                    	  if(content.timeForday){
                    		  $("#"+parseInt(my_index-2)+"ThhxTime").datebox('setValue',content.timeForday.substring(0,10));
                    	  }                    	 
                    	  $("#"+parseInt(my_index-2)+"label").html("");
						  $("#"+parseInt(my_index-2)+"label2").html("");
						  $("#"+parseInt(my_index-2)+"thhx").val(result.obj.progress[index+1].detail);
					  }/* else if(content.detail.indexOf("时间")>=0){
						 // $("#"+my_index+"ja").datebox("setValue",content.timeForday.substring(0,10));
					  } */
                      
                      
                      
                      else{
						  //加载其他的
						  if(content.detail.length>3&&content.type!="暂存待查"){
							  add_czjd_other(content.detail,"#history_jds");
							  $("#"+parseInt(my_index-1)+"ja").datebox("setValue",content.timeForday.substring(0,10));
							  $("#"+parseInt(my_index-1)+"label").html("");
						  }
						 
					  }
				  }
				  
			  });
			  console.log(result.obj.progress);
			  if(result.obj.progress){
			  $("#history_jds").append("<hr style='border:5px solid black'/>");
			  }
			   var  thhx_num=0;
			   var  thtx_num=0;
			   var  chbg_num=0;
			   var  czjy_num=0;
			   var  thhx_fa_num=0;
			   var  zcdc_bg_num=0;
			   var  zcdc_czyj_num=0;
			   var  zzcl_num=0;
			   var  lj_num=0;
			   var  jmth_num=0;
			   var  ljcq_num=0;
			   //加载文件
			   
			   $(".file").each(function(){
				   var title=$(this).data("title");
				   if(title=="谈话函询处置情况报告"){
					   if(result.obj.thhx_bg){
					       console.log(result.obj.thhx_bg);
						   $(this).val(result.obj.thhx_bg[thhx_num].url);
						   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.thhx_bg[thhx_num].id);
						   console.log("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D");
						   thhx_num+=1;
						   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
						   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
						   
					   }
				   }
				   if(title=="谈话函询处置方案"){
					   if(result.obj.thhx_fa){
					   $(this).val(result.obj.thhx_fa[thhx_fa_num].url);
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.thhx_fa[thhx_fa_num].id);
					   thhx_fa_num+=1;
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
					   
					   }
			       }
				   if(title=="初步核实处置情况报告"){
					       $("#33").css('display','block');
					       if(result.obj.cbhs_bg){
					       $(this).val(result.obj.cbhs_bg[chbg_num].url);
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.cbhs_bg[chbg_num].id);
					       chbg_num+=1;
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
						   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
					       }
				   }
				   if(title=="关于初步核实的处置意见"){
					   $("#32").css('display','block');
				       if(result.obj.cbhs_czjy){
				       $(this).val(result.obj.cbhs_czjy[czjy_num].url);
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.cbhs_czjy[czjy_num].id);
				       czjy_num+=1;
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
				       }
			       }
				   if(title=="暂存待查处置情况报告"){
					       if(result.obj.zcdc_bg){
					    	
					       $(this).val(result.obj.zcdc_bg[zcdc_bg_num].url);
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.zcdc_bg[zcdc_bg_num].id);
					       zcdc_bg_num+=1;
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
						   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
					       
					       }
				   }
				   if(title=="关于暂存待查的处置意见"){
					   console.log(result.obj);
				       if(result.obj.zcdc_czyj){
				       $(this).val(result.obj.zcdc_czyj[zcdc_czyj_num].url);
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.zcdc_czyj[zcdc_czyj_num].id);
				       zcdc_czyj_num+=1;
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
				       
				       }
			       }
				   if(title=="给予组织处理处置情况报告"){
					       if(result.obj.zzcl_bg){
					    	
					       $(this).val(result.obj.zzcl_bg[zzcl_num].url);
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.zzcl_bg[zzcl_num].id);
					       zzcl_num+=1;
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
						   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
					       
					       }
				   }
				   if(title=="予以了结处置情况报告"){
					       if(result.obj.yylj_bg){
					    	
					       $(this).val(result.obj.yylj_bg[lj_num].url);
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.yylj_bg[lj_num].id);
					       lj_num+=1;
					       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
						   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
					       }
					       
				   }
				   if(title=="诫勉谈话处置情况报告"){
				       if(result.obj.jmth_bg){
				       $(this).val(result.obj.jmth_bg[jmth_num].url);
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.jmth_bg[jmth_num].id);
				       lj_num+=1;
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
				       
				       }
			   }
				    
				   if(title=="谈话提醒处置情况报告"){
				       if(result.obj.thtx_bg){
				       $(this).val(result.obj.thtx_bg[thtx_num].url);
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.thtx_bg[thtx_num].id);
				       lj_num+=1;
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
				       
				       }
			   }
				   if(title=="了结澄清处置情况报告"){
				       if(result.obj.ljcq_bg){
				       $(this).val(result.obj.ljcq_bg[ljcq_num].url);
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_D").val(result.obj.ljcq_bg[ljcq_num].id);
				       lj_num+=1;
				       $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_A").css("color","green");
					   $("#"+this.id.substring(0,this.id.lastIndexOf("_"))+"_C").css("color","red");
				       
				       }
			   }
			   });
		  }
		  });
	

	
}
function jds_save(state){
	  $("#lc_jds").val(state+$("#organId").val());
	  var data=decodeURIComponent($("#jdsForm").serialize(),true);
	  var isValid = $("#jdsForm").form('validate');
	  if(isValid){
	   //保存进度并且保存处置方式
	   $.ajax({
		  url:"${path}/jds/jdsworking",
		  data:data,
		  dataType:"json",
		  type:"post",
		  success:function(data){
			  $.messager.alert('我的消息',data.msg,'info',function(){
				   //刷新树
	      	      treeReload();
			  });
		  }
	     }); 
	  }else{
		  $.messager.alert('我的消息','有必填项没有填写','info');
	  } 
	   
	  
}
</script>