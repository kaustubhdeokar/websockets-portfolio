'use strict';

/* Controllers */

angular.module('springChat.controllers', ['toaster'])
    .controller('ChatController', ['$scope', '$location', '$interval', 'toaster', 'ChatSocket', function ($scope, $location, $interval, toaster, chatSocket) {

        $scope.username = '';
        $scope.sendTo = 'everyone';
        $scope.participants = [];
        $scope.messages = [];
        $scope.newMessage = '';

        $scope.usercount = 0;

        $scope.sendMessage = function () {
            var destination = "/ws/chat.message";

            if ($scope.sendTo !== "everyone") {
                destination = "/ws/chat.private." + $scope.sendTo;
                $scope.messages.unshift({message: $scope.newMessage, username: 'you', priv: true, to: $scope.sendTo});
            }

            chatSocket.send(destination, {}, JSON.stringify({message: $scope.newMessage}));
            $scope.newMessage = '';
        };

        $scope.startTyping = function () {
            console.log("inside start typing");
        }


        $scope.privateSending = function (username) {
            $scope.sendTo = (username != $scope.sendTo) ? username : 'everyone';
        };

        var initStompClient = function () {
            chatSocket.init('/websocket-point');

            chatSocket.connect(function (frame) {

                $scope.username = frame.headers['user-name'];

                chatSocket.subscribe("/ws/chat.participants", function (message) {
                    $scope.participants = JSON.parse(message.body);
                });

                chatSocket.subscribe("/topic/chat.login", function (message) {
                    $scope.participants.unshift({username: JSON.parse(message.body).username, typing: false});
                });

                chatSocket.subscribe("/topic/chat.logout", function (message) {
                    var username = JSON.parse(message.body).username;
                    for (var index in $scope.participants) {
                        if ($scope.participants[index].username == username) {
                            $scope.participants.splice(index, 1);
                        }
                    }
                });


                chatSocket.subscribe("/topic/chat.message", function (message) {
                    $scope.messages.unshift(JSON.parse(message.body));
                });

                chatSocket.subscribe("/user/exchange/amq.direct/chat.message", function(message) {
                    var parsed = JSON.parse(message.body);
                    parsed.priv = true;
                    $scope.messages.unshift(parsed);
                });

                chatSocket.subscribe("/user/exchange/amq.direct/errors", function(message) {
                    toaster.pop('error', "Error", message.body);
                });

            }, function (error) {
                toaster.pop('error', 'Error', 'Connection error ' + error);
            });
        };

        initStompClient();
    }]);
	