package fhtw.chatroom_client.user;

import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.message.PrivateChatMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Profile extends User {

    private Set<User> friendList;
    private ObservableList<PrivateChat> privateChats = FXCollections.observableArrayList();


    public Profile(String username, String password) {
        super(username, password);
        this.friendList = new HashSet<>();
    }

    public void addPrivateChat(PrivateChat chat) {
        privateChats.add(chat);
    }


    public void setPrivateChats(Collection<PrivateChat> privateChats) {
        for (PrivateChat newChat : privateChats) {
            // Check if the chat with the same ID already exists
            Optional<PrivateChat> existingChat = this.privateChats.stream()
                    .filter(chat -> chat.getChatId() == (newChat.getChatId()))
                    .findFirst();

            if (existingChat.isPresent()) {
                // Update existing chat attributes
                PrivateChat existingChatInstance = existingChat.get();
                existingChatInstance.setOnline(newChat.isOnline());

                // Append new messages to the existing chat
                List<PrivateChatMessage> existingMessages = existingChatInstance.getChatMessages();
                List<PrivateChatMessage> newMessages = newChat.getChatMessages();
                int oldMessageCount = existingMessages.size();

                if (newMessages.size() > oldMessageCount) {
                    List<PrivateChatMessage> additionalMessages = newMessages.subList(oldMessageCount, newMessages.size());
                    existingMessages.addAll(additionalMessages);
                }
            } else {
                // Add new chat to the list
                this.privateChats.add(newChat);
            }
        }
    }
}
