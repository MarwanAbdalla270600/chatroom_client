package fhtw.chatroom_client.user;

import fhtw.chatroom_client.chat.PrivateChat;
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

    private String password;
    private Set<User> friendList;
    //private Set<PrivateChat> privateChats;
    private ObservableList<PrivateChat> privateChats = FXCollections.observableArrayList();

    //private Set<GroupChat> groupChats;

    //private List<FriendRequest> friendRequests;


    public Profile(String username, String password, Character gender) {
        super(username, password, gender);
        this.password = password;
        this.friendList = new HashSet<>();
        //this.groupChats = new HashSet<>();
        //this.friendRequests = new ArrayList<>();
    }

    public void addPrivateChat(PrivateChat chat) {
        privateChats.add(chat);
    }

    public void setPrivateChats(Collection<PrivateChat> privateChats) {
        this.privateChats.clear();
        this.privateChats.addAll(privateChats);
    }

}
