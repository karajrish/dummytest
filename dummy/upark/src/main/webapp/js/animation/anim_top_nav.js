// implement the animations of top nav bar
$(document).ready(function(){
	$("#sign-in").click(function(){
		$("#modal-sign-in").modal().css({'margin-top': function(){return ($(this).height()/2-120);}});
	});
});