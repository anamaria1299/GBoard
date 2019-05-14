var chat= (function(){

    var topciName =  window.location.pathname.split("/")[2];
    var stompClient= null;

    function connect() {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            //console.info("connected: "+frame);
            stompClient.subscribe("/topic/message."+topciName, function (response) {
                //console.log(response);
                var data= JSON.parse(response.body);
                if(data.from!=$("#nick").text()){
                    draw("left", data.message, data.from);
                }else{
                    draw("right", data.message, data.from);
                }
            })
        });
    }

    function sendMessage() {
        var chatMessage = {
            message: $("#message_text").val(),
            from: $("#nick").text(),
            room: topciName
        };
        stompClient.send("/app/message."+topciName,{},JSON.stringify(chatMessage));
    }

    function draw(side, text,user){
        //console.log("drawing ...");
        var $message;
        $message= $($('.message_template').clone().html());
        $message.addClass(side).find('.text').html(text);
        $message[2].textContent = user;
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
