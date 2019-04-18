drawerBoard = (function() {

	var drawer = null;
	var initializeDrawerJS = function(boardContainer){
		drawer = new DrawerJs.Drawer(null, {
	        plugins: drawerPlugins,
	        pluginsConfig: drawerPluginsConfig,
	        defaultActivePlugin : { name : 'Pencil', mode : 'lastUsed'},
	    }, 600, 600);
	    boardContainer.append(drawer.getHtml());
	    drawer.onInsert();
	    drawer._startEditing();
	}
	
	var download = function(){
		var file = $("<a />", {
		    "download": "data.json",
		    "href" : "data:application/json;charset=utf-8," + encodeURIComponent(drawer.api.getCanvasAsJSON()),
		  });
		file[0].click();
	}
	
	var upload = function(){
		$("body").append('<input id="inputUpload" type="file" style="display:none">');
		$("#inputUpload").click();
		var fr = new FileReader();
		  
		  fr.onload = function(e) { 
		    var result = JSON.parse(e.target.result);
		    var formatted = JSON.stringify(result, null, 2);
		    drawer._startEditing();
		    drawer.api.loadCanvasFromData(formatted);
		    $("#inputUpload").remove();
		    drawer.trigger("CONTROLED_JSON");
		  }
		
		$("#inputUpload").change(function(){
			fr.readAsText($("#inputUpload").prop('files').item(0));
		});
		  
	}
	
	return {
		drawer : function(){return drawer},
		initialize : initializeDrawerJS,
		download : download,
		upload : upload
	}

})();