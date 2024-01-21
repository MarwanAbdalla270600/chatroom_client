package fhtw.chatroom_client.controller;

import fhtw.chatroom_client.CommunicationService;
import fhtw.chatroom_client.cells.ChatListCell;
import fhtw.chatroom_client.cells.MessageListCell;
import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.message.PrivateChatMessage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

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
        privateChatList.setItems(profile.getPrivateChats());
        privateChatList.getSelectionModel().selectFirst();
        activeChat = privateChatList.getSelectionModel().getSelectedItem();
        this.loggedUser.setText(profile.getUsername());
        startPollingForMessages();
    }

    private void startPollingForMessages() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            try {
                CommunicationService.initData(); // Assuming this method fetches the latest chat data
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
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
        //System.out.println("list clicked");
        try{
            String tmp = privateChatList.getSelectionModel().getSelectedItem().getFriend();
            tmp = tmp.substring(0, tmp.length()-1);
            activeChatLabel.setText(tmp);
            activeChat = privateChatList.getSelectionModel().getSelectedItem();
            privateChatMessageList.setItems(FXCollections.observableArrayList(activeChat.getChatMessages()));
        } catch (Exception e) {
            System.out.println("list is empty");
        }

    }

    @FXML
    public void sendMessage() throws IOException {
        /*if(messageField.getText().isEmpty()) {
            return ;
        }
        CommunicationService.sendMessage(messageField.getText(), activeChat);
        System.out.println(messageField.getText());
        activeChat.addMessage(new PrivateChatMessage(messageField.getText()));
        messageField.clear();*/
        if(messageField.getText().isEmpty()) {
            return;
        }
        final String messageText = messageField.getText();
        PrivateChatMessage message = new PrivateChatMessage(profile.getUsername(), messageText, activeChat.getChatId());

        // Assuming activeChat's chatMessages is an ObservableList
        activeChat.getChatMessages().add(message); // Directly add the message to the chat

        // Update UI immediately
        javafx.application.Platform.runLater(() -> {
            privateChatMessageList.setItems(FXCollections.observableArrayList(activeChat.getChatMessages()));
        });

        // Send message to server
        CommunicationService.sendMessage(messageText, activeChat);

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
