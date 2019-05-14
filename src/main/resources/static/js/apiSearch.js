apiSearch = (function() {

	var join =  function(data){
		$("#searchInput").attr('disabled','disabled');
		$("#sendCreate").attr('disabled','disabled');
		$("#loader").show();
		$.ajax({
			url: "/join",
			type: "POST",
			data:  {'name' : data},
			success: function(data){window.location.replace("/room/"+data)},
			error: function(data){alert("No tienes sesion iniciada ):")},
			complete : function(){
				$("#searchInput").removeAttr('disabled'); 
				$("#sendCreate").removeAttr('disabled'); 
				$("#loader").hide();
			}
		})
	}
	
	var create =  function(){
		$("#searchInput").attr('disabled','disabled');
		$("#sendCreate").attr('disabled','disabled');
		$("#loader").show();
		$.ajax({
			url: "/create",
			type: "POST",
			data:  $('#searchForm').serialize(),
			success: function(data){window.location.replace("/room/"+data)},
			error: function(data){alert("No tienes sesion iniciada ):");console.log(data);},
			complete : function(){
				$("#searchInput").removeAttr('disabled'); 
				$("#sendCreate").removeAttr('disabled'); 
				$("#loader").hide();
			}
		})
	}

	var search = function(){
		var data = $('#searchForm').serialize();
		$("#searchInput").attr('disabled','disabled');
		$("#sendCreate").attr('disabled','disabled');
		$("#roomsContent").empty();
		$("#loader").show();
		$.ajax({
			url: "/searcher",
			type: "GET",
			data:  data,
			success: function(data){rooms(data)},
			error: function(data){alert("No tienes sesion iniciada ):");},
			complete : function(){
				$("#searchInput").removeAttr('disabled'); 
				$("#searchInput").focus();
				$("#sendCreate").removeAttr('disabled'); 
				$("#loader").hide();
			}
		})
	}

	var rooms = function(data){
		$("#roomsContent").empty();
		for(var i=0 ; i < data.length; i++){
			var offsetClass = 'col-3 col-lg-2';
			/*if(i % 2 == 0)
				offsetClass += ' offset-lg-3'*/
			/*else
				offsetClass += ' offset-right-3'*/
			if( i%3 == 0)
				offsetClass += ' offset-3'
			if((i+1)%3 == 0)
				offsetClass += ' offset-lg-right-3'	
			
			var room = data[i];
			var roomStr = 	'<div class="'+offsetClass+'">'+
			                	'<div class="card infocontainer">'+
									'<div class="card-body text-center">'+
										'<h5 class="card-title">'+room.title+'</h5>'+
										'<h6 class="card-subtitle mb-2 text-muted">Dueño: '+room.owner.name+'</h6>'+
										'<p class="card-text">Miembros: '+room.numberMembers+'</p>'+
										'<p class="card-text">Creación: '+room.creationDate+'</p>'+
										'<button class="btn btn-success join">Unirme</button>'
									'</div>'+
								'</div>'+
							'</div>'


			$("#roomsContent").append(roomStr);
		}

		$(".join").click(function(){
			apiSearch.join( $(this).parent().find('h5').text());
		});
	}


	
	return {
		search : search,
		create : create,
		join : join
	}

})();


$(document).ready(function(){
	$("#searchInput").on('input',function(){
		if($(this).val() != ""){
			apiSearch.search();
		}
	})
	
	$("#sendCreate").click(function(){
		apiSearch.create()
	});

});