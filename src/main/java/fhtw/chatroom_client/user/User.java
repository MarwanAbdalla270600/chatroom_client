package fhtw.chatroom_client.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class User implements Serializable {
    private final String username;
    private final String password;
    private final Character gender;
    private boolean online = false;

    public User(String username, String password, Character gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    public User(String username, String password, Character gender, boolean isOnline) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.online = isOnline;
    }

    public User fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, User.class);
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

}
