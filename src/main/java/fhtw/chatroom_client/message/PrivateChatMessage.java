package fhtw.chatroom_client.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhtw.chatroom_client.MainApplication;
import fhtw.chatroom_client.chat.PrivateChat;
import fhtw.chatroom_client.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PrivateChatMessage implements Serializable {
    private String senderUsername;
    private String messageText;

    public PrivateChatMessage(String messageText) {
        this.senderUsername = MainApplication.profile.getUsername();
        this.messageText = messageText;
    }

    public boolean isFromMe() {
        return senderUsername.equals(MainApplication.profile.getUsername());
    }

    public PrivateChatMessage() {

    }

    public static PrivateChatMessage fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, PrivateChatMessage.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}