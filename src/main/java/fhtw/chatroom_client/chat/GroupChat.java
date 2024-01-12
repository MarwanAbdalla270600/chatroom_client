package fhtw.chatroom_client.chat;

import fhtw.chatroom_client.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class GroupChat extends Chat {
    private String groupName;
    private List<User> members;
    private int maxMembers;

    public GroupChat(String groupName, List<User> members, int maxMembers) {
        super();
        this.groupName = groupName;
        this.members = members;
        this.maxMembers = maxMembers;
    }
}