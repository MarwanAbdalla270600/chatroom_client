package fhtw.chatroom_client.message;

import fhtw.chatroom_client.chat.GroupChat;
import fhtw.chatroom_client.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString

public class GroupChatMessage extends Message {
    private User sender;
    private GroupChat receiver;

    public GroupChatMessage(String data, boolean fromMe, User sender, GroupChat receiver) {
        super(data, fromMe);
        this.sender = sender;
        this.receiver = receiver;
    }

}