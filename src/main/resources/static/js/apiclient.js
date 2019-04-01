apiclient = (function() {

	return {
		getCredentials : function(user, pass, suc, err) {
			console.log(user,pass);
			$.ajax({
				url : "/users/" + user + "/" + pass,
				type : "GET",
				success : suc,
				error : err
			});
		}
	}

})();