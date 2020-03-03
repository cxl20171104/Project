
var nations = ["请选择","研究生(博士)","研究生(硕士)","本科","大专","中专","高中","初中","小学","其他"];
	    var nation = $("#xl");
	    for ( var i=0;i<nations.length;i++) {
	        var a=nations[i];
	        if(i==0){
	        	 nation.append('<option selected="selected"  value="'+a+'">'+a+'</option>');
	        }else{
	        	 nation.append('<option value="'+a+'">'+a+'</option>');
	        }
	        
	    }
var _1 =function(content){
	console.log(content);
}
	    