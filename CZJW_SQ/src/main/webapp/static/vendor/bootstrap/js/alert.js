                           
	                             
	                       
                      
document.write(' <div id="myAlert" class="alert alert-success">');
document.write('  <a href="#" class="close" data-dismiss="alert">&times;</a>');
document.write('  <strong ><span id="success"></span></strong>');
document.write('  </div>');
document.write('   <div id="myAlert2" class="alert alert-warning">');
document.write('   <a href="#" class="close" data-dismiss="alert">');
document.write(' &times; </a>');
document.write('  <strong ><span id="field"></span></strong>');
document.write('   </div>');


function  field (msg){
    $("#field").html(msg);
	    $('#myAlert2').fadeIn(1000,function(){
		setTimeout(function(){
			$('#myAlert2').fadeOut(1000);
		},1000);
	});
}

function  success(msg){
	$("#success").html(msg);
	$('#myAlert').fadeIn(1000,function(){
		setTimeout(function(){
			$('#myAlert').fadeOut(1000);
		},1000);
	});
	
}