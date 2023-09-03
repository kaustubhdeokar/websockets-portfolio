var stompClient = null;
var notificationCount = 0;

$(document).ready(function () {
    console.log("Index page is ready");
    connect();

    $("#send").click(function () {
        sendMessage();
    });

    $("#send-private").click(function () {
        sendPrivateMessage();
    });

    $("#send-private-user").click(function () {
        sendPrivateMessageToUser();
    })
});

function connect() {
    var socket = new SockJS('/websocket-point');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        // updateNotificationDisplay();
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/user/topic/private-messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    console.log("sending message");
    stompClient.send("/ws/message", {}, JSON.stringify({'messageContent': $("#message").val()}));
}

function sendPrivateMessage() {
    console.log("sending private message");
    stompClient.send("/ws/private-message", {}, JSON.stringify({'messageContent': $("#private-message").val()}));
}

function sendPrivateMessageToUser() {
    let userid = $("#userid").val();
    console.log("sending private message to user" + userid);
    stompClient.send("/ws/private-message-user", {}, JSON.stringify({
        'userid': userid,
        'messageContent': $("#private-message-user").val()
    }));
}

// function updateNotificationDisplay() {
//     if (notificationCount == 0) {
//         $('#notifications').hide();
//     } else {
//         $('#notifications').show();
//         $('#notifications').text(notificationCount);
//     }
// }

// function resetNotificationCount() {
//     notificationCount = 0;
//     updateNotificationDisplay();
// }