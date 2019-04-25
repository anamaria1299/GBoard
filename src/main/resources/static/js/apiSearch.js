apiSearch = (function() {

	var join =  function(){
		$.ajax({
			url: "/join",
			type: "POST",
			data:  $('#searchForm').serialize(),
			success: function(data){alert("funK"); window.location.replace("/rooms/"+data)},
			error: function(data){alert("No tienes sesion iniciada ):")}
		})
	}
	
	return {
		join : join
	}

})();


$(document).ready(function(){
	$("#sendSearch").click(function(){
		apiSearch.join()
	});
});