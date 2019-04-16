profile= (function(){
	
	return {
		getUserByNickname: function(nickname){
			return apiProfile.getUser(nickname,function(data){
				document.getElementById('name').innerHTML= data.name+" "+data.lastName;
				document.getElementById('nickname').innerHTML= data.nickName;
				document.getElementById('lastDate').innerHTML= data.lastDate;
				console.log(data);
			});
		}
	}
})();