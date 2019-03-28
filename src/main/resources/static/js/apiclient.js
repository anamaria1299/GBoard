apiclient = (function(){
	return{
		getCredentials : function(nick,pass){
			$.get("users/"+nick+"/"+pass);
			console.log("Entraste muy bien");
		}
	}
})();