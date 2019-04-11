var stompClient = null;
var drawer = null;
var onMouseUp = false;
var onModified = false;

function onMessageReceived(payload) {
	drawer.off(drawer.EVENT_OBJECT_ADDED);
    var message = JSON.parse(payload.body);
    if(message.content){
        drawer.api.loadCanvasFromData(message.content);
    }
    
    drawer.on(drawer.EVENT_OBJECT_ADDED,function(){
    	onModified =  true;
    	//console.log(onMouseUp + onModified+"mod")
    	sendMessage();
    });
}

function sendMessage() {
    if(stompClient && onMouseUp && onModified) {
    	onMouseUp = false;
        var chatMessage = {
            user: "buena",
            content: drawer.api.getCanvasAsJSON()
        };
        stompClient.send("/topic/tablero", {}, JSON.stringify(chatMessage));
    }
    onModified = false;
}

function connect() {
	var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
    stompClient.debug = null;
}

function onConnected() {
    stompClient.subscribe('/topic/tablero', onMessageReceived);

    drawer.on(drawer.EVENT_OBJECT_ADDED,function(){
    	onModified =  true;
    	//console.log(onMouseUp + onModified+"mod")
    	sendMessage();
    });
    
}


function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}

window.onload = function() {
    connect();
};



$(document).ready(function () {
	drawerBoard.initialize($('#canvas-editor'));
	drawer = drawerBoard.drawer()
    
    $(".canvas-container").mouseup(function(){
    	onMouseUp = true;
    	//console.log(onMouseUp + onModified+"up")
    	sendMessage();
    });
    
    
});