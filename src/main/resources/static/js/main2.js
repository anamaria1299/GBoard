var topciName =  "prueba"//window.location.pathname.split("/")[1];

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    if(message.content){
        apiBoard.loadJson(LZString.decompress(message.content));
    } 
}

function sendMessage() {
    if(stompClient) {
    	onMouseUp = false;
        var chatMessage = {
            content: LZString.compress(apiBoard.getJson())
        };
        stompClient.send("/topic/tablero."+topciName, {}, JSON.stringify(chatMessage));
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
    stompClient.subscribe('/topic/tablero.'+topciName, onMessageReceived);
}

function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}

window.onload = function() {
    connect();
};

apiBoard.Socket(sendMessage);
