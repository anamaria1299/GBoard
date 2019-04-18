apiProfile= (function(){

	return{
		getUser: function(nickname,callback){
			$.get("/users/"+nickname,function(data){callback(data);});
		},
		
		putProfile: function(profile,nickname){
			console.log(profile);
			console.log(nickname);
			this.getUser(nickname,function(data){
				data.profile= profile;
				alert(JSON.stringify(data));
				$.ajax({
					type: "PUT",
					url: "/users/"+nickname,
					contentType: 'application/json',
					data: JSON.stringify(data),
					success: function(data) {
						console.log(data);
					}
				});
			});
		}
	}
})();