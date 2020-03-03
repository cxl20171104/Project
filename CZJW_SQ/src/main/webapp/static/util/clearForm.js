function ClearForm(id) {
    var objId = document.getElementById(id);
    if (objId == undefined) {
        return;
    }
    for (var i = 1; i < objId.elements.length; i++) {
        if (objId.elements[i].type == "text") {
            objId.elements[i].value = "";
        }
        else if (objId.elements[i].type == "password") {
            objId.elements[i].value = "";
        }
        else if (objId.elements[i].type == "checkbox") {
            objId.elements[i].checked = false;
        }
        else if (objId.elements[i].getAttribute ('data-s') == "select-one") {
        	//清空单选
        	var id=objId.elements[i].id;
        	$('#'+id).selectpicker('val',0);
        	$('#'+id).selectpicker('refresh');
        }
        else if (objId.elements[i].getAttribute ('data-s') == "multiple") {
        	 //bootStrap清空多选
        	 var arr=new Array();
        	 arr[0]="0";
        	 var id=objId.elements[i].id;
        	 $('#'+id).selectpicker('val', arr);
           
        }
        else if (objId.elements[i].type == "textarea") {
            objId.elements[i].value = "";
        }else if(objId.elements[i].type == "file"){
        	objId.elements[i].value = "";
        }
        
    }
}