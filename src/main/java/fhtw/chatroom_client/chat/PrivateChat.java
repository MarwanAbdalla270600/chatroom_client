package fhtw.chatroom_client.chat;


import fhtw.chatroom_client.message.PrivateChatMessage;
import fhtw.chatroom_client.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PrivateChat extends Chat {
    private final User member;

    public PrivateChat(User member) {
        super();
        this.member = member;
    }

    public void addMessage(PrivateChatMessage privateChatMessage) {
        messages.add(privateChatMessage);
    }
}