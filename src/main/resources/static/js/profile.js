profile= (function(){
	
	return {
		getUserByNickname: function(){		
				//info about who am I?
				console.log($("#info-profile").text());
				if($("#info-profile").text() != ""){
					document.getElementById('info-profile').innerHTML= $("#info-profile").text();
					var profile= $("#profile-text");
					profile.append("</br><div class='form-group'> <p style='text-align: left;font-size: smaller;font-weight: 700;'>Si desea puede modificar su perfil, debe tener en cuenta que solo son permitidos 700 caracteres como máximo.</p> <textarea class='form-control' rows='5'id='textarea-profile'onkeyup='profile.countChar(this)'>"+$("#info-profile").text()+"</textarea><div id='charNum'></div></br><button type='button' class='btn btn-outline-primary' onclick="+'"'+"apiProfile.putProfile($("+"'"+"#textarea-profile"+"'"+").val(),"+"$('#nickname').text()"+")"+'">Modificar</button></div>');
				}else{
					var profile= $("#profile-text");
					profile.append("<div class='form-group'> <p style='text-align: left;'>En este espacio podrás escribir lo que quieres que las personas conozcan de ti, debes tener en cuenta que solo son permitidos 700 caracteres como máximo.</p> <textarea class='form-control' rows='5'id='textarea-profile'></textarea><div id='charNum'></div></br><button type='button' class='btn btn-outline-primary' onclick="+'"'+"apiProfile.putProfile($("+"'"+"#textarea-profile"+"'"+").val(),"+"$('#nickname').text()"+")"+'">enviar</button></div>');
				}
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
			$("#"+component)[0].innerHTML = '<input id="'+component+'-1" value="'+$("#"+component).text()+'" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">';
			$("#"+component+"-button")[0].innerHTML= 'Guardar';
			//with a static user
			$("#"+component+"-button")[0].setAttribute('onclick','apiProfile.set'+type+'($("#'+component+'-1").val(),'+"$('#nickname').text()"+')');
		},

		setInfoRooms: function setInfoRoom(){
			var room = $("#rooms");
			apiProfile.getRoomByMember($('#nickname').text(), function (data) {
				var rooms= '', date= '';
				for(var i= 0; i < data.length ; i++){
					rooms+='<div><a href="/room/'+data[i].title+'" style="color: #864603;">'+data[i].title+'</a></div>';
					date += '<div>'+data[i].creationDate+'</div>';
				}
				room.append('<br><div class="row"><div class="col-2"></div><div class="col-4">'+rooms+'</div><div class="col-4">'+date+'</div><div class="col-2"></div>');
			});
		} ,

		setInfoMyRooms: function setInfoMyRooms(){
			var room = $("#myRooms");
			apiProfile.getRoomByOwner($('#nickname').text(), function (data) {
				var rooms= '', date= '';
				for(var i= 0; i < data.length ; i++){
					rooms+='<div><a href="/room/'+data[i].title+'" style="color: #864603;">'+data[i].title+'</a></div>';
					date += '<div>'+data[i].creationDate+'</div>';
				}
				room.append('<br><div class="row"><div class="col-2"></div><div class="col-4">'+rooms+'</div><div class="col-4">'+date+'</div><div class="col-2"></div>');
			});
		}
		
	}
})();


$(document).ready(function(){
	profile.getUserByNickname();
	profile.setInfoRooms();
	profile.setInfoMyRooms();
});
