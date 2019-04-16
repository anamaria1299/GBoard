apiProfile= (function(){

	return{
		getUser: function(nickname,callback){
			$.get("/users/"+nickname,function(data){callback(data);});
		}
	}
})();