var socket = new WebSocket("ws://" + location.host + "/endpoint");
var stompClient = Stomp.over(socket);
var formSize = $("form").height();

$(".chat-box").css("height", "calc(100vh - " + (90 + formSize) + "px)");

stompClient.connect({}, function () {
    document.getElementById("chat").innerHTML = "";
    stompClient.subscribe("/topic/messages", function (message) {
        show(JSON.parse(message.body));
    });
});

function send() {
    var name = document.getElementById("name").value;
    var message = document.getElementById("message").value;

    stompClient.send("/app/endpoint", {}, JSON.stringify({"name": name, "message": message}));
    document.getElementById("message").value = "";
}

function show(data) {
    chat.innerHTML += "<div class=\"chat-box-message\">" + "[" + data.date + "]" + "&nbsp;&nbsp;&nbsp;&nbsp;" + data.name + "<br>" + data.message + "</div>";
}

$(window).resize(function () {
    var formSize = $("form").height();
    $(".chat-box").css("height", "calc(100vh - " + (90 + formSize) + "px)");
});