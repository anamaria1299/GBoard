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
				document.getElementById('more-web').innerHTML= '<a href="'+data.webPage+'">'+data.webPage+'</a>';
				document.getElementById('more-mail').innerHTML= data.email;
				document.getElementById('more-location').innerHTML= data.country;
				
				//info about who am I?
				if(data.profile != null){
					document.getElementById('info-profile').innerHTML= data.profile;
					var profile= $("#profile-text");
					profile.append("</br><div class='form-group'> <p style='text-align: left;font-size: smaller;font-weight: 700;'>Si desea puede modificar su perfil, debe tener en cuenta que solo son permitidos 700 caracteres como máximo.</p> <textarea class='form-control' rows='5'id='textarea-profile'onkeyup='profile.countChar(this)'>"+data.profile+"</textarea><div id='charNum'></div></br><button type='button' class='btn btn-outline-primary' onclick="+'"'+"apiProfile.putProfile($("+"'"+"#textarea-profile"+"'"+").val(),"+"'"+data.nickName+"'"+")"+'">Modificar</button></div>');
				}else{
					var profile= $("#profile-text");
					profile.append("<div class='form-group'> <p style='text-align: left;'>En este espacio podrás escribir lo que quieres que las personas conozcan de ti, debes tener en cuenta que solo son permitidos 700 caracteres como máximo.</p> <textarea class='form-control' rows='5'id='textarea-profile'></textarea><div id='charNum'></div></br><button type='button' class='btn btn-outline-primary' onclick="+'"'+"apiProfile.putProfile($("+"'"+"#textarea-profile"+"'"+").val(),"+"'"+data.nickName+"'"+")"+'">enviar</button></div>');
				}
				
				console.log(data);
			});
		},
		
		countChar: function countChar(val) {
			var len = val.value.length;
			if (len >= 700) {
			  val.value = val.value.substring(0, 700);
			} else {
			  $('#charNum').text(700 - len);
			}
		},
		
		setInfo: function setGender(component,nickName, type){
			$("#"+component)[0].innerHTML = '<input id="'+component+'-1" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">';
			$("#"+component+"-button")[0].innerHTML= 'Guardar';
			//with a static user
			$("#"+component+"-button")[0].setAttribute('onclick','apiProfile.set'+type+'($("#'+component+'-1").val(),'+'"anamaria1299"'+')');
		}
		
	}
})();


$(document).ready(function(){
	profile.getUserByNickname("anamaria1299");
});