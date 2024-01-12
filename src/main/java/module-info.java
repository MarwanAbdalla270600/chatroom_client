module fhtw.chatroom_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires static lombok;


    opens fhtw.chatroom_client to javafx.fxml;
    exports fhtw.chatroom_client;
    exports fhtw.chatroom_client.controller;
    exports fhtw.chatroom_client.user;
    exports fhtw.chatroom_client.chat;
    exports fhtw.chatroom_client.cells;
    exports fhtw.chatroom_client.message;
    opens fhtw.chatroom_client.controller to javafx.fxml;
}