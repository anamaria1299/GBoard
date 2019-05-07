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
	$('#signup').submit(function(e) {
		e.preventDefault()
		apiclient.signup()
	});
})

