//implement the animations of sidebar
$(document).ready(function(){
	$("#link-sidebar-news").mouseover(function(){
		var top = $("#sidebar").offset().top;
		var left = parseInt($("#sidebar").css("width").slice(0,-2))+parseInt($("#sidebar").offset().left);
		$(".extended-page").stop();
		$(".extended-page").hide();
		$("#extended-page-news").css({"display":"block","top":top,"left":left+'px'});
  		$("#extended-page-news").css("width","0px").animate({width:'700px'},"slow",function(){ });
	
	});	
	$("#link-sidebar-tips").mouseover(function(){
		var top = $("#sidebar").offset().top;
		var left = parseInt($("#sidebar").css("width").slice(0,-2))+parseInt($("#sidebar").offset().left);
		$(".extended-page").stop();
		$(".extended-page").hide();
		$("#extended-page-tips").css({"display":"block","top":top,"left":left+'px'});
  		$("#extended-page-tips").css("width","0px").animate({width:'700px'},"slow",function(){});
	
	});	
	$("#link-sidebar-gift-card").mouseover(function(){
		var top = $("#sidebar").offset().top;
		var left = parseInt($("#sidebar").css("width").slice(0,-2))+parseInt($("#sidebar").offset().left);
		$(".extended-page").stop();
		$(".extended-page").hide();
		$("#extended-page-gift-card").css({"display":"block","top":top,"left":left+'px'});
  		$("#extended-page-gift-card").css("width","0px").animate({width:'700px'},"slow",function(){});
	
	});	
	$("#sidebar"&&".extended-page").mouseleave(function(){
		$(".extended-page").stop(true,true);
		$(".extended-page").css("display","none");
		$(".extended-page").css("width","0px");
	});
});