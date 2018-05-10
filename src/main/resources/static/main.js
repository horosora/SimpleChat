var socket = new WebSocket("ws://" + location.host + "/endpoint");
var stompClient = Stomp.over(socket);
var formSize = $("form").height();

$(".chat-box").css("height", "calc(100vh - " + (90 + formSize) + "px)");

stompClient.connect({}, function () {
    $("#chat").html("");
    stompClient.subscribe("/topic/messages", function (message) {
        show(JSON.parse(message.body));
    });
});

function send() {
    var name = $("#name").val();
    var message = $("#message").val();
    stompClient.send("/app/endpoint", {}, JSON.stringify({"name": name, "message": message}));
    $("#message").val("");
    $("#button").html("<button type=\"button\" class=\"btn btn-outline-secondary\" disabled onclick=\"send()\">send</button>");
}

function show(data) {
    $("#chat").append("<div class=\"px-2 px-md-5 py-4\">" + "[" + data.date + "]" + "&nbsp;&nbsp;&nbsp;&nbsp;" + data.name + "<br>" + data.message + "</div>");
}

$(window).resize(function () {
    formSize = $("form").height();
    $(".chat-box").css("height", "calc(100vh - " + (90 + formSize) + "px)");
});

$("#message").keyup(function(){
    var message = $("#message").val();
    if (message === "") {
        $("#button").html("<button type=\"button\" class=\"btn btn-outline-secondary\" disabled onclick=\"send()\">send</button>");
    } else {
        $("#button").html("<button type=\"button\" class=\"btn btn-outline-secondary\" onclick=\"send()\">send</button>");
    }
});