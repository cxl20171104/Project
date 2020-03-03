$(function(){
	flyTextBox();
});

function flyTextBox(){
	$(".flyTextBox span").eq(0).animate({left:'10px',top:'30px'},900);
	$(".flyTextBox span").eq(1).animate({left:'29px',top:'55px'},900);
	$(".flyTextBox span").eq(2).animate({left:'47px',top:'95px'},900);
	$(".flyTextBox span").eq(3).animate({left:'102px',top:'10px'},900);
	$(".flyTextBox span").eq(4).animate({left:'80px',top:'44px'},900);
	$(".flyTextBox span").eq(5).animate({left:'87px',top:'74px'},900);
	$(".flyTextBox span").eq(6).animate({left:'103px',top:'105px'},900);
	$(".flyTextBox span").eq(7).animate({left:'179px',top:'20px'},900);
	$(".flyTextBox span").eq(8).animate({left:'138px',top:'47px'},900);
	$(".flyTextBox span").eq(9).animate({left:'155px',top:'90px'},900);
	$(".flyTextBox span").eq(10).animate({left:'140px',top:'127px'},900);
	$(".flyTextBox span").eq(11).animate({left:'235px',top:'30px'},900);
	$(".flyTextBox span").eq(12).animate({left:'210px',top:'60px'},900);
	$(".flyTextBox span").eq(13).animate({left:'227px',top:'99px'},900);
}
function menuAlert(){
	$(".menuBox").slideToggle(500,function(){
		var mB = document.getElementsByClassName('menuBox')[0];
		var mBdis = mB.style.display;
		var src=$('#src').val();
		if(mBdis == "block"){
			console.log($('#src').val()+"================");
			
			$('.menuBtn').attr('src',src+"/static/images/index_menuIconSelected.png");
		}else if(mBdis == "none"){
			$('.menuBtn').attr('src',src+'/static/images/index_menuIcon.png');
		}
	});
}
