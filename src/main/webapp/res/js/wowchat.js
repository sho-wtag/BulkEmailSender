/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var stompClient = null;

function setConnected(connected) {

    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('chat-history').innerHTML = '';
}

function connect(channelId) {

    var socket = new SockJS('/eHospital/chat/' + channelId);
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {

        //setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages/' + channelId, function (messageOutput) {

            showMessageOutput(JSON.parse(messageOutput.body));
        });
    });
}

function disconnect() {

    if (stompClient != null) {
        stompClient.disconnect();
    }

    //setConnected(false);
    console.log("Disconnected");
}

function sendMessage(txt) {
    var from = _USER_ID_;
    var text = txt;
    var channelId = _HOSPITAL_ID_;
    stompClient.send("/app/chat/" + channelId, {}, JSON.stringify({'from': from, 'text': text, 'channelId': channelId}));
}

function showMessageOutput(messageOutput) {
    try {
        angular.element('#serviceOrderListCtrl').scope().getServiceOrderList(1, angular.element('#serviceOrderListCtrl').scope().itemPerPage);
    }
    catch(err) {
        console.log(err.message);
    }
}