package fhtw.chatroom_client.controller;

import fhtw.chatroom_client.CommunicationService;
import fhtw.chatroom_client.cells.ChatListCell;
import fhtw.chatroom_client.cells.ChatUpdateListener;
import fhtw.chatroom_client.cells.MessageListCell;
import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.message.PrivateChatMessage;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;

import static fhtw.chatroom_client.MainApplication.profile;

public class MainController implements ChatUpdateListener {
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
        privateChatMessageList.setSelectionModel(null);
        privateChatMessageList.addEventFilter(MouseEvent.ANY, MouseEvent::consume);

        CommunicationService.setChatUpdateListener(this);

    }

    // Call this method when new chat data is received from the server.
    public void updateChatList(List<PrivateChat> chats) {
       /* // Use Platform.runLater() if this method is called from outside the JavaFX Application Thread.
        javafx.application.Platform.runLater(() -> {
            // Update the items of the privateChatList ListView.
            privateChatList.setItems(FXCollections.observableArrayList(chats));

            // Optionally, select the first chat if the list is not empty.
            if (!chats.isEmpty()) {
                privateChatList.getSelectionModel().selectFirst();
                activeChat = privateChatList.getSelectionModel().getSelectedItem();
                updateMessagesForActiveChat();
            }
        });*/
        PrivateChat currentSelection = privateChatList.getSelectionModel().getSelectedItem();
        privateChatList.setItems(FXCollections.observableArrayList(chats));
        if(currentSelection != null) {
            // Attempt to reselect the previously selected chat
            for (PrivateChat chat : chats) {
                if (chat.getChatId() == currentSelection.getChatId()) {
                    privateChatList.getSelectionModel().select(chat);
                    break;
                }
            }
        }
    }
    private void updateMessagesForActiveChat() {
        if (activeChat != null) {
            privateChatMessageList.setItems(FXCollections.observableArrayList(activeChat.getChatMessages()));
        }
    }

    @Override
    public void onChatListUpdated(List<PrivateChat> chats) {
        updateChatList(chats);
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
