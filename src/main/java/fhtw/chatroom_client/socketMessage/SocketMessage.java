package fhtw.chatroom_client.socketMessage;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class SocketMessage<T> {
    private String method;

    private Object object;

    public SocketMessage(String method, Object object) {
        this.method = method;
        this.object = object;
    }

    public SocketMessage fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, SocketMessage.class);
    }
    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}
