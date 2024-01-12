package fhtw.chatroom_client.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
public abstract class Message {
    protected final String data;
    protected final LocalDateTime time;
    protected boolean fromMe;


    public Message(String data, boolean fromMe) {
        this.fromMe = fromMe;
        this.time = LocalDateTime.now();
        this.data = data;
    }
}
