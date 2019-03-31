var stompClient = null;
var drawer = null;
var drawJson = null;

function onMessageReceived(payload) {
	drawer._startEditing();
    var message = JSON.parse(payload.body);
    if(message.content){
        drawer.api.loadCanvasFromData(message.content);
    }
}

function sendMessage() {
    if(stompClient) {
        var chatMessage = {
            user: "buena",
            content: drawJson
        };
        stompClient.send("/app/chat.sendMessage2", {}, JSON.stringify(chatMessage));
    }
}

function connect() {
	var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
    stompClient.debug = null;
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public2', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser2",{},JSON.stringify({user: "buena"}));

    var fun  = function(event){
        sendMessage();
    }
    
    $("#send").click(function(){
    	drawJson = drawer.api.getCanvasAsJSON();
    	sendMessage();
    });


}


function onError(error) {
	console.log(error);
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}

window.onload = function() {
    connect();
};


$(document).ready(function () {
    drawer = new DrawerJs.Drawer(null, {
        plugins: drawerPlugins,
        pluginsConfig: drawerPluginsConfig,
        defaultActivePlugin : { name : 'Pencil', mode : 'lastUsed'},
    }, 600, 600);
    $('#canvas-editor').append(drawer.getHtml());
    drawer.onInsert();
    
});