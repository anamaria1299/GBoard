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
		},
		sendCredential : function(){
			$.ajax({
				url : $('#login').attr('action'),
				type : $('#login').attr('method'),
				data : $('#login').serialize(),
				success: function(data) {window.location.replace("/")},
				error : function(data){$("#modalError").show();$("#modalError").text(data.responseText)}
			});
		}
	}

})();

$(document).ready(function(){
	$('#login').submit(function(e){
		e.preventDefault()
		apiclient.sendCredential()
	});
})

