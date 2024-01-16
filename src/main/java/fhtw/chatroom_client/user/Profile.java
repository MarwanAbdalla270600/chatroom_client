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
        this.privateChats.clear();
        this.privateChats.addAll(privateChats);
    }
}
