package fhtw.chatroom_client.chat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fhtw.chatroom_client.MainApplication;
import fhtw.chatroom_client.message.PrivateChatMessage;
import fhtw.chatroom_client.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString

public class PrivateChat implements Serializable {
    private int chatId;
    private String firstMember;
    private String secondMember;
    private List<PrivateChatMessage> chatMessages;


    public PrivateChat() {
    }



    public String getFriend() {
        String firstMember = this.firstMember.substring(0, this.firstMember.length()-1);
        if (!firstMember.equals(MainApplication.profile.getUsername())) {
            return this.firstMember;
        } else {
            return this.secondMember;
        }
    }





    public void setChatMessages(List<PrivateChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public void addMessage(PrivateChatMessage privateChatMessage) {
        chatMessages.add(privateChatMessage);
    }


    public static List<PrivateChat> fromJsonToList(String json) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, new TypeReference<List<PrivateChat>>() {});
        }



    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}