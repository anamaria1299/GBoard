var stompClient = null;
var canvasimg =  null;
var canvasJs =  null;
var observer =  null;
var config = { attributes: true};

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    console.log(message);
}

function sendMessage() {
    if(stompClient) {
        console.log("ahi vamos");
        var chatMessage = {
            user: "buena",
            content: canvasimg.attr('data-canvas-serialized')
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
    }
}

function connect() {
	var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
	stompClient.connect({}, onConnected, onError);
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",{},JSON.stringify({user: "buena"}));

    canvasJs = document.querySelector(".editable-canvas-not-edited");
    canvasimg = $(".editable-canvas-image");
    observer = new MutationObserver(function(){
        sendMessage();
    });
    observer.observe(canvasJs, config);
}


function onError(error) {
    console.log('Could not connect to WebSocket server. Please refresh this page to try again!');
}

window.onload = function() {
    connect();

};