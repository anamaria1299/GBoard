profile= (function(){
	
	return {
		getUserByNickname: function(nickname){
			return apiProfile.getUser(nickname,function(data){
				document.getElementById('name').innerHTML= data.name+" "+data.lastName;
				document.getElementById('nickname').innerHTML= data.nickName;
				document.getElementById('lastDate').innerHTML= data.lastDate;
				
				//info about more information about media
				document.getElementById('more-name').innerHTML= data.name+" "+data.lastName;
				document.getElementById('more-gender').innerHTML= data.gender;
				document.getElementById('more-web').innerHTML= data.webPage;
				document.getElementById('more-mail').innerHTML= data.email;
				document.getElementById('more-location').innerHTML= data.country;
				
				//info about who am I?
				if(data.profile != null){
					document.getElementById('info-profile').innerHTML= data.profile;
				}else{
					var profile= $("#profile-text");
					profile.append("<div class='form-group'> <p style='text-align: left;'>En este espacio podrás escribir lo que quieres que las personas conozcan de ti, debes tener en cuenta que solo son permitidos 100 caracteres como máximo.</p> <textarea class='form-control' rows='5'id='textarea-profile'></textarea></br><button type='button' class='btn btn-outline-primary' onclick="+'"'+"profile.setProfileInfo($("+"'"+"#textarea-profile"+"'"+").val())"+'">enviar</button></div>"');
				}
				
				console.log(data);
			});
		},
		
		setProfileInfo: function(info){
			alert("Se va a cambiar la info"+info);
		}
	}
})();


$(document).ready(function(){
	profile.getUserByNickname("anamaria1299");
});