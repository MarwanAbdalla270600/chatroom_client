package fhtw.chatroom_client.message;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class PrivateChatMessage extends Message {
    public PrivateChatMessage(String data, boolean fromMe) {
        super(data, fromMe);
    }
}