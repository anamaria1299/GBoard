apiclient = (function() {

	return {
		sendCredential : function(){
			$.ajax({
				url : $('#login').attr('action'),
				type : $('#login').attr('method'),
				data : $('#login').serialize(),
				success: function(data) {window.location.replace("/")},
				error : function(data){$("#modalError").show();$("#modalError").text(data.responseText)}
			});
		},
		
		logout : function(){
			$.ajax({
				url : $('#logout').attr('action'),
				type : $('#logout').attr('method'),
				data : $('#logout').serialize(),
				success: function(data) {window.location.replace("/")},
				error : function(data){$("#modalError").show();$("#modalError").text(data.responseText)}
			});
		},
	
		signup : function() {
			$.ajax({
				url : $('#signup').attr('action'),
				type : $('#signup').attr('method'),
				data : $('#signup').serialize(),
				success : function(data) {window.location.replace("/")},
				error : function(data) {$('#modalError').show;$('#modalError').text(data.responseText)}
			});
		}
	}

})();

$(document).ready(function(){
	$('#login').submit(function(e){
		e.preventDefault()
		apiclient.sendCredential()
	});
	$('#logout').submit(function(e){
		e.preventDefault()
		apiclient.logout()
	});
	$('#signup').submit(function(e) {
		e.preventDefault()
		apiclient.signup()
	});
})

