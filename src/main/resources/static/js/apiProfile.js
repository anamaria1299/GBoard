apiProfile= (function(){

	return{
		getUser: function(nickname,callback){
			$.get("/users/"+nickname,function(data){callback(data);});
		},
		
		putProfile: function(profile,nickname){
			this.getUser(nickname,function(data){
				data.profile= profile;
				//alert(JSON.stringify(data));
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
		},
		
		setGender: function(gender,nickname){
			this.getUser(nickname,function(data){
				data.gender= gender;
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
		},
		
		setWeb: function(web,nickname){
			this.getUser(nickname,function(data){
				data.webPage= web;
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
					success: function(data) {
						console.log(data);
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
					success: function(data) {
						console.log(data);
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