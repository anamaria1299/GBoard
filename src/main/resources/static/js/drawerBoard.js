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
	return {
		drawer : function(){return drawer},
		initialize : initializeDrawerJS
	}

})();