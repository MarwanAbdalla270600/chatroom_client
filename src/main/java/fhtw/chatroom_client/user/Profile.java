package fhtw.chatroom_client.user;

import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.message.PrivateChatMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
            // Check if the chat already exists in the list
            boolean chatExists = false;
            for (PrivateChat existingChat : this.privateChats) {
                if (existingChat.getChatId() == newChat.getChatId()) {
                    // Chat already exists, update its information
                    existingChat.setFirstMember(newChat.getFirstMember());
                    existingChat.setSecondMember(newChat.getSecondMember());
                    existingChat.setOnline(newChat.isOnline());

                    // Add new chat messages to the existing chat
                    for (PrivateChatMessage newMessage : newChat.getChatMessages()) {
                        if (!existingChat.getChatMessages().contains(newMessage)) {
                            existingChat.getChatMessages().add(newMessage);
                        }
                    }
                    chatExists = true;
                    break;
                }
            }

            // If the chat doesn't exist, add it to the list
            if (!chatExists) {
                this.privateChats.add(newChat);
            }
        }
    }
}
