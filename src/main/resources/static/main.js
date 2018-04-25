var stompClient = null;

function connect() {
    var socket = new WebSocket("ws://" + location.host + "/endpoint");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        document.getElementById("chat").innerHTML = "";
        stompClient.subscribe("/topic/messages", function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
}

function send() {
    var name = document.getElementById("name").value;
    var message = document.getElementById("message").value;
    stompClient.send("/app/endpoint", {}, JSON.stringify({"name": name, "message": message}));
}

function showMessage(data) {
    $("#chat").append("<p>" + data.name + "<br>" + data.message + "</p>");
}

connect();

window.onbeforeunload = function (){
    disconnect();
    return "disconnect";
};
