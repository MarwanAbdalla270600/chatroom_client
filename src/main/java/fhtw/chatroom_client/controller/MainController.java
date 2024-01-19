package fhtw.chatroom_client.controller;

import fhtw.chatroom_client.CommunicationService;
import fhtw.chatroom_client.cells.ChatListCell;
import fhtw.chatroom_client.cells.MessageListCell;
import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.message.PrivateChatMessage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

import static fhtw.chatroom_client.MainApplication.profile;

public class MainController {
    @FXML
    public TextField messageField;

    @FXML
    public TextField friendField;

    @FXML
    public ListView<PrivateChat> privateChatList = new ListView<>();

    @FXML
    public ListView<PrivateChatMessage> privateChatMessageList = new ListView<>();

    @FXML
    public Label activeChatLabel;

    public PrivateChat activeChat;

    @FXML
    public Label loggedUser;


    @FXML
    public void initialize() {
        setCustomCells();
        if (profile.getPrivateChats() != null) {
            privateChatList.setItems(FXCollections.observableArrayList(profile.getPrivateChats()));
            privateChatList.getSelectionModel().selectFirst();
        }
        activeChat = privateChatList.getSelectionModel().getSelectedItem();
        this.loggedUser.setText(profile.getUsername());
    }


    @FXML
    public void settings() {
        System.out.println("settings");
    }

    public void setCustomCells() {
        privateChatList.setCellFactory(new ChatListCell());
        privateChatMessageList.setCellFactory(new MessageListCell());
    }

    @FXML
    public void clickChatList() {
        try {
            PrivateChat selectedChat = privateChatList.getSelectionModel().getSelectedItem();
            if (selectedChat != null) {
                String tmp = selectedChat.getFriend();
                tmp = tmp.substring(0, tmp.length() - 1);
                activeChatLabel.setText(tmp);
                activeChat = selectedChat;
                privateChatMessageList.setItems(FXCollections.observableArrayList(activeChat.getChatMessages()));
            }
        } catch (Exception e) {
            // Consider showing an alert or logging the error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error selecting chat.");
            alert.show();
        }
    }

    @FXML
    public void sendMessage() throws IOException {
        if(messageField.getText().isEmpty()) {
            return ;
        }
        CommunicationService.sendMessage(messageField.getText(), activeChat);
        System.out.println(messageField.getText());
        activeChat.addMessage(new PrivateChatMessage(messageField.getText()));
        messageField.clear();
    }

    @FXML
    public void addFriend() throws IOException, ClassNotFoundException {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        if (CommunicationService.addFriend(friendField.getText())) {
            CommunicationService.initData();
            infoAlert.setContentText("added friend");
        } else {
            infoAlert.setAlertType(Alert.AlertType.ERROR);
            infoAlert.setContentText("Could not find Friend");
        }

        friendField.clear();
        infoAlert.show();
    }

}
