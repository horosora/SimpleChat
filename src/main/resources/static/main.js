var socket = new WebSocket("ws://" + location.host + "/endpoint");
var stompClient = Stomp.over(socket);

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
    chat.innerHTML += data.date + "&nbsp;&nbsp;&nbsp;&nbsp;" + data.name + "<br>" + data.message + "<br><br>";
}
