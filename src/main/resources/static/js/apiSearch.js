apiSearch = (function() {

	var join =  function(){
		$.ajax({
			url: "/join",
			type: "POST",
			data:  $('#searchForm').serialize(),
			success: function(data){alert("funK"); window.location.replace("/room/"+data)},
			error: function(data){alert("No tienes sesion iniciada ):")}
		})
	}
	
	var create =  function(){
		$.ajax({
			url: "/create",
			type: "POST",
			data:  $('#createForm').serialize(),
			success: function(data){alert("funK"); window.location.replace("/room/"+data)},
			error: function(data){alert("No tienes sesion iniciada ):");console.log(data);}
		})
	}
	
	return {
		join : join,
		create: create
	}

})();


$(document).ready(function(){
	$("#sendSearch").click(function(){
		apiSearch.join()
	});
	
	$("#sendCreate").click(function(){
		apiSearch.create()
	});
});