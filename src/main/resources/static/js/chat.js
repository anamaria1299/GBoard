var chat= (function(){

    var stompClient= null;
    var idChat= null;

    function connect() {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.info("connected: "+frame);
            stompClient.subscribe("/topic/chat", function (response) {
                //console.log(response);
                var data= JSON.parse(response.body);
                draw("left", data.message);
            })
        });
    }

    function sendMessage() {
        console.log("voy a enviar "+ $("#message_text").val());
        var chatMessage = {
            message: $("#message_text").val()
        };
        stompClient.send("/topic/chat",{},JSON.stringify(chatMessage));
    }

    function draw(side, text){
        console.log("drawing ...");
        var $message;
        $message= $($('.message_template').clone().html());
        $message.addClass(side).find('.text').html(text);
        $('.messages').append($message);
        return setTimeout(function () {
            return $message.addClass('appeared');
        },0);
    }

    return{

        connect: function () {
            connect();
        },

        send: function () {
            sendMessage();
        }
    }

})();