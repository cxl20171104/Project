function validator(id,validators){
	var result=false;
	$.each( validators, function(index, content){
		  var value=document.getElementById(id).value;
          if(content=="noEmpty"){
        	  if(value){
        		  result=true;
        	  }else{
        		  document.getElementById(id+"span").innerHTML="内容不可为空";
        		  return false;
        	  }
          }
          if(content=="date"){
        	  var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;  
        	  if(!reg.test(value)){
        		  document.getElementById(id+"span").innerHTML="请输入日期";
        		  return false;
        	  }else{
        		  result=true;
        	  }
          }
          if(content=="number"){
        	  var reg=/^[1-9]\d*$/;
        	  if(!reg.test(value)){
        		  document.getElementById(id+"span").innerHTML="请输入数字";
        		  return false;
        	  }else{
        		  result=true;
        	  }
          }
          if(content=="phone"){
        	  var reg=/^1[34578]\d{9}$/;
        	  if(!reg.test(value)){
        		  document.getElementById(id+"span").innerHTML="请输入正确的手机号";
        		  return false;
        	  }else{
        		  result=true;
        	  }
          }
          if(content=="choice"){
        	  var obj=document.getElementById(id);
        	  var index = obj.selectedIndex; // 选中索引
        	  var text = obj.options[index].text; // 选中文本
        	  var value = obj.options[index].value; // 选中值
        	  if(text=="请选择"||value=="0"){
        		  document.getElementById(id+"span").innerHTML="请选择其中一项";
        		  return false;
        	  }else{
        		  result=true;
        	  }
          }
          
          
	});
	if(result){
		document.getElementById(id+"span").innerHTML="";
	}
	return result;
}
