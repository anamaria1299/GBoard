apiProfile= (function(){

	return{
		getUser: function(nickname,callback){
			$.get("/users/"+nickname,function(data){callback(data);});
		},
		
		putProfile: function(profile,nickname){
			this.getUser(nickname,function(data){
				data.profile= profile;
				$.ajax({
					type: "PUT",
					url: "/users/"+nickname,
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function() {
						toastr["success"]("Se modifico correctamente tu perfil"); 
						$("#info-profile").text(profile) 
					},
					error: function(data){toastr["error"](data.responseText);}
				});
			});
		},
		
		setGender: function(gender,nickname){
			this.getUser(nickname,function(data){
				data.gender= gender;
				$.ajax({
					type: "PUT",
					url: "/users/"+nickname,
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function() {
						toastr["success"]("Se modifico correctamente tu perfil"); 
						$("#more-gender").text(gender); 
					},
					error: function(data){toastr["error"](data.responseText);},
					complete: function(){
						$("#more-gender-button").text("Editar");
						$("#more-gender-button").attr("onclick","profile.setInfo('more-gender',$('#nickname').val(),'Gender')")
					}
				});
			});
		},
		
		setWeb: function(web,nickname){
			this.getUser(nickname,function(data){
				data.webPage= web;
				$.ajax({
					type: "PUT",
					url: "/users/"+nickname,
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function() {
						toastr["success"]("Se modifico correctamente tu perfil"); 
						$("#more-web").text(web); 
					},
					error: function(data){toastr["error"](data.responseText);},
					complete: function(){
						$("#more-web-button").text("Editar");
						$("#more-web-button").attr("onclick","profile.setInfo('more-web',$('#nickname').val(),'Web')")
					}
				});
			});
		},
		
		setMail: function(mail,nickname){
			this.getUser(nickname,function(data){
				data.email= mail;
				//alert(JSON.stringify(data));
				$.ajax({
					type: "PUT",
					url: "/users/"+nickname,
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function() {
						toastr["success"]("Se modifico correctamente tu perfil"); 
						$("#more-mail").text(mail); 
					},
					error: function(data){toastr["error"](data.responseText);},
					complete: function(){
						$("#more-mail-button").text("Editar");
						$("#more-mail-button").attr("onclick","profile.setInfo('more-mail',$('#nickname').val(),'Mail')");
					}
				});
			});
		},
		
		setLocation: function(locat,nickname){
			this.getUser(nickname,function(data){
				data.country= locat;
				$.ajax({
					type: "PUT",
					url: "/users/"+nickname,
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function() {
						toastr["success"]("Se modifico correctamente tu perfil"); 
						$("#more-location").text(locat); 
					},
					error: function(data){toastr["error"](data.responseText);},
					complete: function(){
						$("#more-location-button").text("Editar");
						$("#more-location-button").attr("onclick","profile.setInfo('more-location',$('#nickname').val(),'Location')");
					}
				});
			});
		},

		getRoomByOwner: function (nickname,callback) {
			$.get("/rooms/owner/"+nickname,function(data){callback(data);});
		},

		getRoomByMember: function (nickname,callback) {
			$.get("/rooms/member/"+nickname,function(data){callback(data);});
		}
	}
})();