app = (function() {

	var f = function(info) {
		return "<td>" + info + "</td>";
	}

	var onSucces = function(data) {
		location.href = "/boardB";

	}

	var onErr = function(data) {
		alert("El usuario o la contraseña no son correctos.")
	}

	return {
		getCredentials : function(name, callback) {
			var x = $("#inputNick").val();
			var y = $("#inputPass").val();
			apiclient.getCredentials(x, y, onSucces, onErr)
		},
		onSucces : function(data) {
			console.log(JSON.parse(data))
		}
	}

})();